# frozen_string_literal: true

require_relative "kafka_example/version"
require_relative "kafka_example/kafka_api"
# require_relative "kafka_example/example_us_states.rb"
# require_relative "kafka_example/lab4_example_storms.rb"
require_relative "kafka_example/lab5_example_performance.rb"

module KafkaExample
  class Error < StandardError; end
end

example = KafkaExample::Lab5Performance.new
example.run
example.consume
example.log
