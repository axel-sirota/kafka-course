"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class ConfluentSchemaRegistryError extends Error {
    constructor(error) {
        super(error.message || error);
        this.name = this.constructor.name;
    }
}
exports.ConfluentSchemaRegistryError = ConfluentSchemaRegistryError;
class ConfluentSchemaRegistryArgumentError extends ConfluentSchemaRegistryError {
}
exports.ConfluentSchemaRegistryArgumentError = ConfluentSchemaRegistryArgumentError;
class ConfluentSchemaRegistryCompatibilityError extends ConfluentSchemaRegistryError {
}
exports.ConfluentSchemaRegistryCompatibilityError = ConfluentSchemaRegistryCompatibilityError;
class ConfluentSchemaRegistryInvalidSchemaError extends ConfluentSchemaRegistryError {
}
exports.ConfluentSchemaRegistryInvalidSchemaError = ConfluentSchemaRegistryInvalidSchemaError;
class ConfluentSchemaRegistryValidationError extends ConfluentSchemaRegistryError {
    constructor(error, paths) {
        super(error);
        this.paths = paths;
    }
}
exports.ConfluentSchemaRegistryValidationError = ConfluentSchemaRegistryValidationError;
//# sourceMappingURL=errors.js.map