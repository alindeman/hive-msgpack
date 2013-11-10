(ns hive-msgpack.msgpack
  (:import [org.msgpack MessagePack]
           [org.msgpack.packer Packer])
  (:require [hive-msgpack.resultset :as rs]))

(defprotocol Packable
  "Serialize the object to a MessagePackPacker"
  [pack-into [obj packer] "Serialize the object into a MessagePackPacker"])

(extend-protocol Packable
  clojure.lang.Keyword
  (pack-into [kw ^Packer packer]
    (.write packer (name kw)))

  Object
  (pack-into [obj ^Packer packer]
    (.write packer obj)))

(defn- write-row
  [packer row]
  (.writeMapBegin packer (count row))
  (doseq [[k v] row]
    (pack-into k packer)
    (pack-into v packer))
  (.writeMapEnd packer))

(defn- create-packer
  [out-stream]
  (.createPacker (MessagePack.) out-stream))

(defn write-resultset
  "Writes the rows in a ResultSet (including column headers) as msgpack"
  [out-stream resultset]
  (let [packer (create-packer out-stream)
        rows (resultset-seq resultset)]
    (doseq [row rows] (write-row packer row))))
