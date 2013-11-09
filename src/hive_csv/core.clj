(ns hive-csv.core
  (:gen-class)
  (:require [clojure.tools.cli :refer [cli]]
            [hive-csv.hive :as hive]
            [hive-csv.csv :as csv])
  (:import [java.io OutputStreamWriter BufferedWriter]
           [java.lang System]))

(defn- parse-opts
  [args]
  (cli args
    ["--host" "JDBC host" :default "localhost"]
    ["--port" "JDBC port" :default 10000 :parse-fn #(Integer. %)]
    ["--database" "JDBC database" :default "default"]
    ["-h" "--help" "Show help" :default false :flag true]))

(defn- jdbc-opts
  "Translates between a map of command line options to a map that JDBC will understand"
  [opts]
  {:subprotocol "hive"
   :subname (str "//" (:host opts) ":" (:port opts) "/" (:database opts))})

(defn -main
  [& args]
  (let [[options [query] banner] (parse-opts args)]
    (cond
      (or (empty? query) (:help options))
        (do
          (println "Executes a hive query, exporting the result as CSV")
          (println "Example: hive-csv 'select count(*) from events'")
          (println)
          (println banner))
      :else
        (let [jdbc (jdbc-opts options)
              writer (BufferedWriter.  (OutputStreamWriter. System/out))]
          (hive/execute-query jdbc query #(csv/write-resultset writer %))
          (.flush writer)))))
