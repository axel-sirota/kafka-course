# frozen_string_literal: true

require_relative "kafka_example/version"
require_relative "kafka_example/kafka_api"
require_relative "kafka_example/example_us_states.rb"

module KafkaExample
  class Error < StandardError; end
end

example = KafkaExample::ExampleUsStates.new
example.run
example.log
