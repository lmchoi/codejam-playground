(ns codejam.cj08.r1.problem-b
  (:require [clojure.string :as s]
            [codejam.uber :as u]))

(defn parse-customer [customer-str]
  (let [[n & args] (u/parse-values customer-str)]
    (partition 2 args)))

(defn parse-inputs []
  (let [n (u/read-num) ; flavors
        m (u/read-num) ; number of customers
        customers (u/read-lines m)
        x (map #(parse-customer %) customers)]
    {:flavors n
     :customers x}))

(defn has-flavor? [[f m?] batch]
  (= m? (nth batch (dec f))))

(defn check-customer
  [customer batch]
  (loop [ret {:satisfied? false
              :add-malt nil}
         flavors customer]
    (if flavors
      (let [[f m? :as flavor] (first flavors)]
        (cond
          (has-flavor? flavor batch)
          (assoc ret :satisfied? true)

          (= m? 1)
          (recur
           (assoc ret :add-malt f)
           (next flavors))

          :default
          (recur
           ret
           (next flavors))))
      ret)))

(defn add-malt? [batch malt]
  (and (not (nil? malt))
       (zero? (nth batch (dec malt)))))

(defn solve-problem
  [{:keys [flavors customers]}]
  (loop [batch (vec (repeat flavors 0))
         cs customers
         check-again? false]

    (if cs
      (let [c (first cs)
            {:keys [satisfied? add-malt] :as cc} (check-customer c batch)]
        (cond
          satisfied?
          (recur
           batch
           (next cs)
           check-again?)

          (add-malt? batch add-malt)
          (recur
           (assoc batch (dec add-malt) 1)
           (next cs)
           true)

          :default
          "IMPOSSIBLE"))

      (if check-again?
        (recur batch
               customers
               false)
        (u/output-array batch)))))

(defn problem-b []
  (let [c (inc (read-string (read-line)))]
    (doseq [x (range 1 c)]
      (println (str  "Case #" x ": " (solve-problem
                                      (parse-inputs)))))))

