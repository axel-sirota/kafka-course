require "kafka"

module KafkaExample
  class KafkaApi
    CLIENT_ID_DEFAULT = 'lab3-producer'
    BROKERS_DEFAULT = ['localhost:9092', 'localhost:9093']
    TOPIC_DEFAULT = 'my_orders2'

    def self.kafka(brokers: BROKERS_DEFAULT, client_id: CLIENT_ID_DEFAULT)
      # The first argument is a list of "seed brokers" that will be queried for the full
      # cluster topology. At least one of these *must* be available. `client_id` is
      # used to identify this client in logs and metrics. It's optional but recommended.
      Kafka.new(brokers, client_id: client_id)
    end

    def self.log(topic = TOPIC_DEFAULT)
      puts "** #{self.class.name}##{__method__}"

      kafka.each_message(topic: TOPIC_DEFAULT) do |message|
        puts "#{message.offset}: '#{message.key || '(nil)'}' == '#{message.value}'"
      end
    end

    # TOPIC_DEFAULT = 'my_orders2'

    # def self.demo
    #   puts "** #{self.class.name}##{__method__}"
    #   kafka.deliver_message("hello #{Time.now}", topic: TOPIC_DEFAULT)
    # end

    # def self.demo2
    #   puts "** #{self.class.name}##{__method__}"

    #   state_string =
    #     "AK,AL,AZ,AR,CA,CO,CT,DE,FL,GA," +
    #     "HI,ID,IL,IN,IA,KS,KY,LA,ME,MD," +
    #     "MA,MI,MN,MS,MO,MT,NE,NV,NH,NJ," +
    #     "NM,NY,NC,ND,OH,OK,OR,PA,RI,SC," +
    #     "SD,TN,TX,UT,VT,VA,WA,WV,WI,WY"

    #   state_abbrs = state_string.split(',')

    #   state_abbrs.each do |abbr|
    #     val = (10..100000).to_a.sample
    #     # kafka.deliver_message("#{val} #{Time.now} #{abbr}", key: abbr, topic: TOPIC_DEFAULT)
    #     kafka.deliver_message("#{val}", key: abbr, topic: TOPIC_DEFAULT)

    #     puts "#{Time.now} #{abbr} #{val}"        
    #   end
    # end

    def demo
      puts "** #{self.class.name}##{__method__}"
      kafka.deliver_message("hello #{Time.now}", topic: 'hello world')
    end
  end
end
