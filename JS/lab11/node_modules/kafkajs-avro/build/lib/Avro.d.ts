import { Kafka, ProducerMessage, MessagePayload } from "kafkajs";
import Registry from "./Registry";
interface AvroProducerMessage extends ProducerMessage {
    subject: string;
    version?: string;
    value: any;
}
interface AvroMessagePayload extends MessagePayload {
    topic: string;
    messages: AvroProducerMessage[];
}
declare class Avro {
    registry: Registry;
    kafka: Kafka;
    parseOptions: any;
    constructor(kafka: Kafka, opts: any);
    consumer(args: any): {
        run: (args: any) => Promise<void>;
        connect(): Promise<void>;
        disconnect(): Promise<void>;
        subscribe(options: import("kafkajs").ConsumerSubscribeOptions): Promise<void>;
        pause(topics: {
            topic: string;
        }[]): void;
        resume(topics: {
            topic: string;
        }[]): void;
        seek(options: import("kafkajs").ConsumerSeekOptions): void;
        describeGroup(): Promise<import("kafkajs").GroupMetadata>;
        events: import("kafkajs").ConsumerEvents;
        on(event: "consumer.heartbeat" | "consumer.commit_offsets" | "consumer.group_join" | "consumer.fetch" | "consumer.start_batch_process" | "consumner.end_batch_process" | "consumer.connect" | "consumer.disconnect" | "consumer.stop" | "consumer.crash" | "consumer.request" | "consumer.request_timeout" | "consumer.request_queue_size", cb: (e: import("kafkajs").InstrumentationEvent) => void): () => import("kafkajs").Consumer;
    };
    private encodeMessages;
    producer(args?: any): {
        send: ({ topic, ...args }: AvroMessagePayload) => Promise<void>;
        sendBatch: (args: any) => Promise<void>;
        connect(): Promise<void>;
        disconnect(): Promise<void>;
        transaction(): Promise<import("kafkajs").Transaction>;
        events: import("kafkajs").ProducerEvents;
        on(event: "producer.connect" | "producer.disconnect" | "producer.request" | "producer.request_timeout" | "producer.request_queue_size", cb: (e: import("kafkajs").InstrumentationEvent) => void): () => import("kafkajs").Producer;
    };
}
export default Avro;
