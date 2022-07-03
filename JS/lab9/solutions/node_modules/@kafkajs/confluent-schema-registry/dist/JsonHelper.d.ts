import { Schema, SchemaHelper, ConfluentSubject, ConfluentSchema } from './@types';
export default class JsonHelper implements SchemaHelper {
    validate(schema: Schema): void;
    getSubject(confluentSchema: ConfluentSchema, schema: Schema, separator: string): ConfluentSubject;
}
