"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const DEFAULT_OFFSET = 0;
exports.MAGIC_BYTE = Buffer.alloc(1);
exports.encode = (registryId, payload) => {
    const registryIdBuffer = Buffer.alloc(4);
    registryIdBuffer.writeInt32BE(registryId, DEFAULT_OFFSET);
    return Buffer.concat([exports.MAGIC_BYTE, registryIdBuffer, payload]);
};
//# sourceMappingURL=wireEncoder.js.map