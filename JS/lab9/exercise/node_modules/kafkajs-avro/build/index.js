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
const kafkajs_1 = require("kafkajs");
exports.Kafka = kafkajs_1.Kafka;
const Avro_1 = require("./lib/Avro");
exports.Avro = Avro_1.default;
class KafkaAvro extends kafkajs_1.Kafka {
    constructor(_a) {
        var { avro } = _a, args = __rest(_a, ["avro"]);
        super(args);
        this.avro = new Avro_1.default(this, avro);
    }
}
exports.KafkaAvro = KafkaAvro;
exports.default = KafkaAvro;
//# sourceMappingURL=index.js.map