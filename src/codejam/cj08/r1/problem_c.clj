(ns codejam.cj08.r1.problem-c
  (:require [clojure.string :as s]
            [codejam.uber :as u]))

(defn parse-inputs []
  (let [n (u/read-num)] ; positive int
    ))

(defn solve-problem [n]
  (prn n)
  )

(defn problem-c []
  (let [c (inc (read-string (read-line)))]
    (doseq [x (range 1 c)]
      (println (str  "Case #" x ": " (solve-problem
                                      (parse-inputs)))))))

