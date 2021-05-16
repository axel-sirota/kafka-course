"use strict";
var __rest = (this && this.__rest) || function (s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) if (e.indexOf(p[i]) < 0)
            t[p[i]] = s[p[i]];
    return t;
};
Object.defineProperty(exports, "__esModule", { value: true });
const Registry_1 = require("./Registry");
class Avro {
    constructor(kafka, opts) {
        this.registry = new Registry_1.default(opts);
        this.kafka = kafka;
    }
    consumer(args) {
        const consumer = this.kafka.consumer(args);
        return Object.assign({}, consumer, { run: args => consumer.run(Object.assign({}, args, { eachMessage: async (messageArgs) => {
                    // Discard invalid avro messages
                    if (messageArgs.message.value.readUInt8(0) !== 0)
                        return;
                    const value = await this.registry.decode(messageArgs.message.value);
                    return args.eachMessage(Object.assign({}, messageArgs, { message: Object.assign({}, messageArgs.message, { value }) }));
                } })) });
    }
    encodeMessages(messages) {
        return Promise.all(messages.map(async (message) => {
            const value = await this.registry.encode(message.subject, message.version || "latest", message.value);
            return Object.assign({}, message, { value });
        }));
    }
    producer(args) {
        const producer = this.kafka.producer(args);
        return Object.assign({}, producer, { send: async (_a) => {
                var { topic } = _a, args = __rest(_a, ["topic"]);
                return producer.send(Object.assign({}, args, { topic, messages: await this.encodeMessages(args.messages) }));
            }, sendBatch: async (args) => {
                return await producer.sendBatch(Object.assign({}, args, { topicNames: await Promise.all(args.topicNames.map(async (topicName) => (Object.assign({}, topicName, { messages: await this.encodeMessages(topicName.messages) })))) }));
            } });
    }
}
exports.default = Avro;
//# sourceMappingURL=Avro.js.map