# KafkaExample

This is a Ruby version of the "lab3" example. It uses the "ruby-kafka" gem from "https://github.com/zendesk/ruby-kafka".

To experiment with that code, run `bin/console` for an interactive prompt.

## Installation

Install the gem and add to the application's Gemfile by executing:

    $ bundle add kafka_example

If bundler is not being used to manage dependencies, install the gem by executing:

    $ gem install kafka_example

## Usage

In one terminal, Launch the dockerized kafka instance via:

```
cd Ruby/lab3
docker-compose up -d
```

In another terminal, launch the demo

```
cd Ruby/lab3/solutions
bundle
ruby lib/kafka_example.rb
```

For example output of `ruby lib/kafka_example.rb`, see: `Ruby/lab3/solution/out.log`

## Development

After checking out the repo, run `bin/setup` to install dependencies. Then, run `rake spec` to run the tests. You can also run `bin/console` for an interactive prompt that will allow you to experiment.

To install this gem onto your local machine, run `bundle exec rake install`. To release a new version, update the version number in `version.rb`, and then run `bundle exec rake release`, which will create a git tag for the version, push git commits and the created tag, and push the `.gem` file to [rubygems.org](https://rubygems.org).

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/drhuffman12/kafka_example. This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [code of conduct](https://github.com/drhuffman12/kafka_example/blob/master/CODE_OF_CONDUCT.md).

## Code of Conduct

Everyone interacting in the KafkaExample project's codebases, issue trackers, chat rooms and mailing lists is expected to follow the [code of conduct](https://github.com/drhuffman12/kafka_example/blob/master/CODE_OF_CONDUCT.md).
