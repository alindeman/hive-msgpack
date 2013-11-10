(ns hive-msgpack.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [cli]]
            [hive-msgpack.hive :as hive]
            [hive-msgpack.msgpack :as msgpack]
            [clojure.java.io :refer [output-stream]]))

(defn- parse-opts
  [args]
  (cli args
    ["--host" "JDBC host" :default "localhost"]
    ["--port" "JDBC port" :default 10000 :parse-fn #(Integer. %)]
    ["--database" "JDBC database" :default "default"]
    ["-o" "--output" "Path to where output should be saved" :required true]
    ["-h" "--help" "Show help" :default false :flag true]))

(defn- jdbc-opts
  "Translates between a map of command line options to a map that JDBC will understand"
  [opts]
  {:subprotocol "hive"
   :subname (str "//" (:host opts) ":" (:port opts) "/" (:database opts))})

(defn -main
  [& args]
  (let [[options [query] banner] (parse-opts args)
         output (:output options)]
    (cond
      (or (empty? query) (empty? output) (:help options))
        (do
          (println "Executes a hive query, exporting the result as msgpack")
          (println "Example: hive-msgpack -o /tmp/output 'select count(*) from events'")
          (println)
          (println banner))
      :else
        (let [jdbc (jdbc-opts options)
              out-stream (output-stream output)]
          (hive/execute-query jdbc query #(msgpack/write-resultset out-stream %))
          (.flush out-stream)))))
