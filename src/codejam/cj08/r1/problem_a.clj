(ns codejam.cj08.r1.problem-a
  (:require [clojure.string :as s]
            [codejam.uber :as u]))
(defn find-min-scalar-product
  [{:keys [vectors]}]
  (let [[v1 v2] vectors]
    (apply + (mapv *
                (sort v1)
                (reverse (sort v2))))))

(defn problem-a []
  (let [n (inc (read-string (read-line)))]
    (doseq [x (range 1 n)]
      (let [t  (read-string (read-line)) ; number of coords
            v1 (u/read-values)
            v2 (u/read-values)]
        (println (str "Case #" x ": "  (find-min-scalar-product
                                        {:n t
                                         :vectors [v1 v2]})))))))



