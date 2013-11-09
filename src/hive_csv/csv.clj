(ns hive-csv.csv
  (:require [clojure.data.csv :as csv]
            [hive-csv.resultset :as resultset]))

(defn write-resultset
  "Writes the rows in a ResultSet (including column headers) as CSV"
  [writer resultset]
  (csv/write-csv writer [(resultset/column-names resultset)]) ; headers
  (csv/write-csv writer (map #(vals %) (resultset-seq resultset))))
