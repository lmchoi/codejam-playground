(ns codejam.problem-template
  (:require [codejam.uber :as u]))

(defn problem []
   #_(let [n (u/read-num)]
    (doseq [i (range 1 (inc n))]
      (let [data (u/read-values)
            groups (u/read-values)]
        (println
          (str "Case #" i ": "
               (solve data groups)))))))

