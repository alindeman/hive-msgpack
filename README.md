# hive-msgpack

A small clojure application that runs a [Hive](http://hive.apache.org/) query
and spits out the results as a msgpack stream. Uses the Hive JDBC adapter.

## Usage

Download [a release](https://github.com/alindeman/hive-msgpack/releases) or
compile it yourself with `lein uberjar`.

Then fire away:

```bash
$ java -jar hive-msgpack.jar -o /tmp/output.msgpack 'select * from foo'
```

By default, hive-msgpack assumes you are running `hive --service hiveserver`
on `localhost:10000` (the defaults).  A different host or port can be
specified with the `--host` and `--port` switches.

Run `java -jar hive-msgpack.jar --help` for examples and defaults.
