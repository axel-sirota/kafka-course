"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const AvroHelper_1 = __importDefault(require("./AvroHelper"));
const JsonHelper_1 = __importDefault(require("./JsonHelper"));
const JsonSchema_1 = __importDefault(require("./JsonSchema"));
const ProtoHelper_1 = __importDefault(require("./ProtoHelper"));
const ProtoSchema_1 = __importDefault(require("./ProtoSchema"));
const _types_1 = require("./@types");
const errors_1 = require("./errors");
const helperTypeFromSchemaTypeMap = {};
exports.schemaTypeFromString = (schemaTypeString) => {
    switch (schemaTypeString) {
        case 'AVRO':
        case undefined:
            return _types_1.SchemaType.AVRO;
        case 'JSON':
            return _types_1.SchemaType.JSON;
        case 'PROTOBUF':
            return _types_1.SchemaType.PROTOBUF;
        default:
            return _types_1.SchemaType.UNKNOWN;
    }
};
exports.helperTypeFromSchemaType = (schemaType = _types_1.SchemaType.AVRO) => {
    const schemaTypeStr = schemaType.toString();
    if (!helperTypeFromSchemaTypeMap[schemaTypeStr]) {
        let helper;
        switch (schemaType) {
            case _types_1.SchemaType.AVRO: {
                helper = new AvroHelper_1.default();
                break;
            }
            case _types_1.SchemaType.JSON: {
                helper = new JsonHelper_1.default();
                break;
            }
            case _types_1.SchemaType.PROTOBUF: {
                helper = new ProtoHelper_1.default();
                break;
            }
            default:
                throw new errors_1.ConfluentSchemaRegistryArgumentError('invalid schemaType');
        }
        helperTypeFromSchemaTypeMap[schemaTypeStr] = helper;
    }
    return helperTypeFromSchemaTypeMap[schemaTypeStr];
};
exports.schemaFromConfluentSchema = (confluentSchema, options) => {
    var _a, _b, _c, _d;
    try {
        let schema;
        switch (confluentSchema.type) {
            case _types_1.SchemaType.AVRO: {
                const opts = ((_a = options) === null || _a === void 0 ? void 0 : _a.forSchemaOptions) || ((_b = options) === null || _b === void 0 ? void 0 : _b[_types_1.SchemaType.AVRO]);
                schema = exports.helperTypeFromSchemaType(confluentSchema.type).getAvroSchema(confluentSchema, opts);
                break;
            }
            case _types_1.SchemaType.JSON: {
                const opts = (_c = options) === null || _c === void 0 ? void 0 : _c[_types_1.SchemaType.JSON];
                schema = new JsonSchema_1.default(confluentSchema, opts);
                break;
            }
            case _types_1.SchemaType.PROTOBUF: {
                const opts = (_d = options) === null || _d === void 0 ? void 0 : _d[_types_1.SchemaType.PROTOBUF];
                schema = new ProtoSchema_1.default(confluentSchema, opts);
                break;
            }
            default:
                throw new errors_1.ConfluentSchemaRegistryArgumentError('invalid schemaType');
        }
        return schema;
    }
    catch (err) {
        throw new errors_1.ConfluentSchemaRegistryArgumentError(err.message);
    }
};
//# sourceMappingURL=schemaTypeResolver.js.map