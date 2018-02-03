(ns name-normalizer.core
  (:require [clojure.string :as string]))

(defn- initial [name]
  (str " " (first name) (if (= 1 (count name)) "" ".")))

(defn- middle-initials [parts]
  (string/join (map initial (butlast (rest parts)))))

(defn- name-and-suffix [name]
  (let [parts (string/split name #",")]
    [(first parts)
     (if (empty? (second parts)) "" (str "," (second parts)))]))

(defn- throw-if-too-many-commas [name]
  (if (> (count (filter #(= \, %) name)) 1)
    (throw (IllegalArgumentException.))))

(defn- mononym? [parts]
  (= 1 (count parts)))

(defn normalize [name]
  (throw-if-too-many-commas name)
  (let [[name suffix] (name-and-suffix (string/trim name))
        parts (string/split name #" ")]
    (if (mononym? parts)
      name
      (str (last parts) ", " (first parts) (middle-initials parts) suffix))))
