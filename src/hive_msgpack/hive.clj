(ns hive-msgpack.hive
  (:require [clojure.java.jdbc :as j]))

(defn execute-query
  "Executes a hive query, invoking rs-fn with a ResultSet upon success"
  [jdbc-opts query rs-fn]
  (let [db (assoc jdbc-opts :classname "org.apache.hadoop.hive.jdbc.HiveDriver")]
    (j/db-query-with-resultset db [query] #(rs-fn %))))
