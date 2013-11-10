(defproject hive-msgpack "0.1.0"
  :description "Runs a Hive query, returning results as CSV"
  :url "https://github.com/alindeman/hive-msgpack"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.cli "0.2.4"]
                 [org.msgpack/msgpack "0.6.8"]
                 [org.clojure/java.jdbc "0.3.0-beta1"]
                 [org.apache.hive/hive-jdbc "0.8.1"]
                 [org.apache.hadoop/hadoop-core "0.20.2"]
                 [javax.jdo/jdo2-api "2.3-eb"]]
  :main hive-msgpack.core,
  :aot [hive-msgpack.core])
