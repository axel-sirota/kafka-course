/// <reference types="node" />
declare class Registry {
    url: string;
    parseOptions: any;
    cache: Map<string, {
        id: number;
        schema: string;
    }>;
    constructor({ url, parseOptions }: {
        url: any;
        parseOptions: any;
    });
    getSchema(filter: any): Promise<{
        id: any;
        schema: any;
    }>;
    encode(subject: any, version: any, originalMessage: any): Promise<Buffer>;
    decode(object: any): Promise<any>;
}
export default Registry;
