(ns codejam.uber
  (:require [clojure.string :as s]
            [clj-time.format :as f]))

(def time-formatter (f/formatter "HH:mm"))

(defn parse-HHmm [ts]
  (f/parse time-formatter ts))

(defn read-lines
  [some-str]
  (doall (take some-str (repeatedly read-line))))

(defn read-num
  []
  (read-string (read-line)))

(defn parse-values
  [line]
  (map read-string (s/split line #" ")))

(defn read-values
  []
  (parse-values (read-line)))

(defn output-array
  [arr]
  (s/join " " arr))
