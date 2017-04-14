(ns codejam.cj09.r1a.problem-a
  (:gen-class)
  (:require [clojure.string :as s]))

(defn read-num
  []
  (read-string (read-line)))

(defn parse-values
  [line]
  (map read-string (s/split line #" ")))

(defn read-values
  []
  (parse-values (read-line)))

(defn- happy-process [base n]
  (int (apply + (map #(Math/pow (Character/getNumericValue %) 2) (Integer/toString n base)))))

(def happy-process-mem (memoize happy-process))

(defn- happy? [base x]
  (loop [seen #{}
         i x]
    (let [n (happy-process-mem base i)]
      (cond
        (== 1 n)
        true

        (contains? seen n)
        false

        :default
        (recur (conj seen n) n)))))

(def happy-mem (memoize happy?))

(defn solve-case [bases]
  (loop [ret 2]
    (if (some #(not (happy-mem % ret)) bases)
      (recur (inc ret))
      ret)))

(defn solve []
  (let [n (read-num)]
    (doseq [i (range 1 (inc n))]
      (let [bases (read-values)]
        (println
          (str "Case #" i ": "
               (solve-case bases)))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (solve))
