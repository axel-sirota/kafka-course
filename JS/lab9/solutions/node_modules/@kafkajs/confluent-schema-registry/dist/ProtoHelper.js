"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const errors_1 = require("./errors");
class ProtoHelper {
    validate(schema) {
        return;
    }
    getSubject(confluentSchema, schema, separator) {
        throw new errors_1.ConfluentSchemaRegistryError('not implemented yet');
    }
}
exports.default = ProtoHelper;
//# sourceMappingURL=ProtoHelper.js.map