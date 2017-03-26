(ns codejam.uber
  (:require [clojure.string :as s]))

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
