(ns codejam.cj10.problem-c
  (:require [codejam.uber :as u]))


(defn- solve [[r k n] groups]
  (loop [i 0
         ret 0
         queue (cycle groups)
         [pp gg :as coaster] [0 0]]
    ;; for each ride
    (if (< i r)
      ;; groups left in the queue
      (if (< gg n)
        (let [g (first queue)
              nc (+ pp g)]
          (cond
            ;; will not fit
            (> nc k)
            (recur
             (inc i)  ;; next ride
             (+ ret pp) ;; add profit
             queue ;; re-queue everyone on coaster
             [0 0] ;; reset number of people on coaster
             )

            ;; does fit
            (< nc k)
            (recur
             i ;; same ride
             ret ;; don't add to profit yet
             (next queue) ;; next in queue
             [nc (inc gg)] ;; new number of people in coaster
             )

            ;; does fit and need a new ride
            (= nc k)
            (recur
             (inc i)
             (+ ret nc)
             (next queue) ;; next in queue and requeue everyone on coaster
             [0 0] ;; reset number of people on coaster
             )))

        ;; no groups left in queue
        (recur
         (inc i)
         (+ ret pp)
         queue
         [0 0])
        )
      ret)
    )
  )

(defn problem-c []
  (let [n (u/read-num)]
    (doseq [i (range 1 (inc n))]
      (let [data (u/read-values)
            groups (u/read-values)]
        (println
         (str "Case #" i ": "
              (solve data groups)))))))

