(ns hive-csv.resultset)

(defn column-names
  "Retrieves a sequence of column names from a ResultSet"
  [resultset]
  (let [rsmeta (.getMetaData resultset)
        idxs (range 1 (inc (.getColumnCount rsmeta)))]
    (map #(.getColumnLabel rsmeta %) idxs)))
