"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const uuid_1 = require("uuid");
const _1 = require(".");
const kafkajs_1 = require("kafkajs");
const waitForMessage_1 = require("./testHelpers/waitForMessage");
describe("kafkajs-avro", () => {
    let kafka, producer, consumer, topic;
    beforeEach(async () => {
        topic = `test-topic-${uuid_1.v4()}`;
        kafka = new _1.default({
            clientId: `test-client-${uuid_1.v4()}`,
            brokers: ["localhost:9092"],
            avro: {
                url: "http://localhost:8081"
            },
            logLevel: kafkajs_1.logLevel.ERROR
        });
        const admin = kafka.admin();
        await admin.connect();
        await admin.createTopics({
            waitForLeaders: true,
            topics: [{ topic }]
        });
        await admin.disconnect();
        producer = kafka.avro.producer();
        await producer.connect();
        const groupId = `test-group-${uuid_1.v4()}`;
        consumer = kafka.avro.consumer({
            groupId,
            sessionTimeout: 10 * 1000,
            heartbeatInterval: 1000,
            minBytes: 1,
            maxBytesPerPartition: 100 * 1024,
            maxBytes: 10 * 100 * 1024,
            maxWaitTimeInMs: 50
        });
        await consumer.connect();
    });
    afterEach(async () => {
        await consumer.disconnect();
        await producer.disconnect();
    });
    it("can produce and consume an avro encoded message", async () => {
        const value = {
            customer: {
                name: "Test Testsson",
                address: `Test Avenue ${uuid_1.v4()}`
            },
            purchase: {
                total_amount: 1000,
                line_items: [{ name: "Sneakers", amount: 500, quantity: 2 }]
            }
        };
        await producer.send({
            topic,
            messages: [
                {
                    subject: "purchase",
                    version: "latest",
                    value
                }
            ]
        });
        await consumer.subscribe({ topic, fromBeginning: true });
        await waitForMessage_1.default({
            consumer,
            matches: ({ topic: messageTopic, message }) => {
                return (topic === messageTopic &&
                    JSON.stringify(value) === JSON.stringify(message.value));
            }
        });
    });
});
//# sourceMappingURL=index.spec.js.map