# hive-csv

A small clojure application that runs a [Hive](http://hive.apache.org/) query
and spits out the results as a CSV. Uses the Hive JDBC adapter.

## Usage

Download [a release](https://github.com/alindeman/hive-csv/releases) or compile
it yourself with `lein uberjar`.

Then fire away:

```bash
$ java -jar hive-csv.jar 'select * from foo'
id,str
1,one
2,two
```

By default, hive-csv assumes you are running `hive --service hiveserver` on
`localhost:10000` (the defaults).  A different host or port can be specified
with the `--host` and `--port` switches.

Run `java -jar hive-csv.jar --help` for examples and defaults.
