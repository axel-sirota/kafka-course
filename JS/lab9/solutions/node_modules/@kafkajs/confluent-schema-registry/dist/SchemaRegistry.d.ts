/// <reference types="node" />
import { COMPATIBILITY } from './constants';
import { SchemaRegistryAPIClientArgs } from './api';
import Cache from './cache';
import { Schema, RawAvroSchema, AvroSchema, SchemaType, ConfluentSchema, SchemaRegistryAPIClientOptions, AvroConfluentSchema } from './@types';
interface RegisteredSchema {
    id: number;
}
interface Opts {
    compatibility?: COMPATIBILITY;
    separator?: string;
    subject: string;
}
interface AvroDecodeOptions {
    readerSchema?: RawAvroSchema | AvroSchema | Schema;
}
interface DecodeOptions {
    [SchemaType.AVRO]?: AvroDecodeOptions;
}
export default class SchemaRegistry {
    private api;
    private cacheMissRequests;
    private options;
    cache: Cache;
    constructor({ auth, clientId, host, retry, agent }: SchemaRegistryAPIClientArgs, options?: SchemaRegistryAPIClientOptions);
    private isConfluentSchema;
    private getConfluentSchema;
    register(schema: Exclude<ConfluentSchema, AvroConfluentSchema>, userOpts: Opts): Promise<RegisteredSchema>;
    register(schema: RawAvroSchema | AvroConfluentSchema, userOpts?: Omit<Opts, 'subject'> & {
        subject?: string;
    }): Promise<RegisteredSchema>;
    register(schema: RawAvroSchema | ConfluentSchema, userOpts: Opts): Promise<RegisteredSchema>;
    private _getSchema;
    getSchema(registryId: number): Promise<Schema | AvroSchema>;
    encode(registryId: number, payload: any): Promise<Buffer>;
    private collectInvalidPaths;
    decode(buffer: Buffer, options?: DecodeOptions): Promise<any>;
    getRegistryId(subject: string, version: number | string): Promise<number>;
    getRegistryIdBySchema(subject: string, schema: AvroSchema | RawAvroSchema | ConfluentSchema): Promise<number>;
    getLatestSchemaId(subject: string): Promise<number>;
    private getSchemaOriginRequest;
}
export {};
