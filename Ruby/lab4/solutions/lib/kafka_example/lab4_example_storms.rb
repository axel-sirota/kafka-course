

require_relative "./kafka_api"

module KafkaExample
  class Lab4ExampleUsStates
    # TOPIC_DEFAULT = 'my_orders2'
    TOPICS = ['flood', 'hurricane']
    CLIENT_ID_DEFAULT = 'lab3-producer'
    KAFKA = KafkaApi.kafka(client_id: CLIENT_ID_DEFAULT)

    def run
      puts "** #{self.class.name}##{__method__}"
      Thread.new {
        while(true) do
          generate_message
          sleep(500)
        end
      }
    end

    def generate_message
      puts "** #{self.class.name}##{__method__}"

      TOPICS.each do |topic|
        val = rand_val
        KAFKA.deliver_message("#{val}", topic: topic)

        puts "#{Time.now} #{topic} #{val}"        
      end
    end

    def log
      puts "** #{self.class.name}##{__method__}"

      TOPICS.each do |topic|
        KafkaExample::KafkaApi.log(topic)
      end
    end

    def consume
      puts "** #{self.class.name}##{__method__}"

      # Consumers with the same group id will form a Consumer Group together.
      consumer = KAFKA.consumer(group_id: "my-consumer")

      # It's possible to subscribe to multiple topics by calling `subscribe`
      # repeatedly.
      TOPICS.each do |topic|
        # consumer.subscribe(topic, start_from_beginning: true)
        consumer.subscribe(topic, start_from_beginning: false)
      end

      # Stop the consumer when the SIGTERM signal is sent to the process.
      # It's better to shut down gracefully than to kill the process.
      trap("TERM") { consumer.stop }

      # Thread.new { 
      #   while(true) do
          consume_messages(consumer)
      #   end
      # }
    end

    def consume_messages(consumer)
      puts "** #{self.class.name}##{__method__}"
      
      # TODO: This is supposed to "loop indefinitely", but it seems to get stuck
      # This will loop indefinitely, yielding each message in turn.
      consumer.each_message(max_wait_time: 5) do |message|
        # puts message.topic, message.partition
        # puts message.offset, message.key, message.value
        message = {
          topic: message.topic, partition: message.partition,
          offset: message.offset, key: message.key, value: message.value
        }.to_json

        puts message

        # consumer.stop
        # message.mark_message_as_processed
      end
    end

    private

    def rand_val
      rand(3) + 1
    end
  end
end
