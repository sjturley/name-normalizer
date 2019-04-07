(ns name-normalizer.core
  (:require [clojure.string :as s]))

(defn- initial [name]
  (str " " (first name) (if (= 1 (count name)) "" ".")))

;; use threading operator
(defn- middle-initials [parts]
  (->>  parts
        (rest)
        (butlast)
        (map initial)
        (s/join)))

(defn- name-and-suffix [name]
  (let [parts (s/split name #",")]
    [(first parts)
     (if (empty? (second parts)) "" (str "," (second parts)))]))

(defn- throw-if-too-many-commas [name]
  (if (> (count (filter #(= \, %) name)) 1)
    (throw (IllegalArgumentException.))))

(defn- mononym? [parts]
  (= 1 (count parts)))

(defn normalize [name]
  (throw-if-too-many-commas name)
  (let [[name suffix] (name-and-suffix (s/trim name))
        parts (s/split name #" ")]
    (if (mononym? parts)
      name
      (str (last parts) ", " (first parts) (middle-initials parts) suffix))))
