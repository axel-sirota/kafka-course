import { AvroSchema, RawAvroSchema, AvroOptions, ConfluentSchema, SchemaHelper, ConfluentSubject } from './@types';
export default class AvroHelper implements SchemaHelper {
    private getRawAvroSchema;
    getAvroSchema(schema: ConfluentSchema | RawAvroSchema, opts?: AvroOptions): AvroSchema;
    validate(avroSchema: AvroSchema): void;
    getSubject(schema: ConfluentSchema, avroSchema: AvroSchema, separator: string): ConfluentSubject;
    private isRawAvroSchema;
}
