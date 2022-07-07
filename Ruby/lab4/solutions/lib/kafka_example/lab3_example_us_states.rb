

require_relative "./kafka_api"

module KafkaExample
  class Lab3ExampleUsStates
    TOPIC_DEFAULT = 'my_orders2'
    KAFKA = KafkaApi.kafka

    def run
      puts "** #{self.class.name}##{__method__}"

      state_abbrs = %w(
        AK AL AZ AR CA CO CT DE FL GA
        HI ID IL IN IA KS KY LA ME MD
        MA MI MN MS MO MT NE NV NH NJ
        NM NY NC ND OH OK OR PA RI SC
        SD TN TX UT VT VA WA WV WI WY
      )

      state_abbrs.each do |abbr|
        val = rand_val
        # kafka.deliver_message("#{val} #{Time.now} #{abbr}", key: abbr, topic: TOPIC_DEFAULT)
        KAFKA.deliver_message("#{val}", key: abbr, topic: TOPIC_DEFAULT)

        puts "#{Time.now} #{abbr} #{val}"        
      end
    end

    def log
      puts "** #{self.class.name}##{__method__}"
      KafkaExample::KafkaApi.log(TOPIC_DEFAULT)
    end

    private

    def rand_val
      (10..100000).to_a.sample
    end
  end
end
