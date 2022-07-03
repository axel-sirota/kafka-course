import { Kafka, KafkaOptions } from "kafkajs";
import Avro from "./lib/Avro";
interface Settings extends KafkaOptions {
    avro: {
        url: string;
        parseOptions?: any;
    };
}
declare class KafkaAvro extends Kafka {
    avro: Avro;
    constructor({ avro, ...args }: Settings);
}
export { KafkaAvro, Kafka, Avro };
export default KafkaAvro;
