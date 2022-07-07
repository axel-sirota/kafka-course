require "kafka"

module KafkaExample
  class KafkaApi
    CLIENT_ID_DEFAULT = 'lab3-producer'
    BROKERS_DEFAULT = ['localhost:9092', 'localhost:9093']
    TOPIC_DEFAULT = 'my_orders2'

    def self.kafka(brokers: BROKERS_DEFAULT, client_id: CLIENT_ID_DEFAULT)
      logger = Logger.new("log/kafka.log")
      # The first argument is a list of "seed brokers" that will be queried for the full
      # cluster topology. At least one of these *must* be available. `client_id` is
      # used to identify this client in logs and metrics. It's optional but recommended.
      Kafka.new(brokers, client_id: client_id, logger: logger)
    end

    def self.producer(required_acks: :all)
      # `required_acks: :all`: This is the default: all replicas must acknowledge.
      # `required_acks: 0`: This is fire-and-forget: messages can easily be lost.
      # `required_acks: 1`: This only waits for the leader to acknowledge.
      producer = kafka.producer(required_acks: required_acks)
    end

    def self.log(topic = TOPIC_DEFAULT)
      puts "** #{self.class.name}##{__method__}"

      kafka.each_message(topic: TOPIC_DEFAULT) do |message|
        puts "#{message.offset}: '#{message.key || '(nil)'}' == '#{message.value}'"
      end
    end

    def demo
      puts "** #{self.class.name}##{__method__}"
      kafka.deliver_message("hello #{Time.now}", topic: 'hello world')
    end
  end
end
