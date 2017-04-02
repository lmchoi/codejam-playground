(ns codejam.cj09.problem-b
  (:require [codejam.uber :as u]))

;; North, West, East, South
;; always look east and south
(defn- solve []
  (let [[h w] (u/read-values)
        tm (map u/parse-values (u/read-lines h))
        ttm (apply map list tm)
        rm (map #(map - % (next %)) tm)
        dm (map #(map - % (next %)) ttm)
        _ (prn rm)
        _ (prn dm)
        c ()
        ]
     rm))

(defn problem-b []
  (let [n (u/read-num)]
  (doseq [i (range 1 (inc n))]
    (println
     (str "case #" i ": "
          (solve))))))

