(ns codejam.cj08.qr.problem-a
  (:require [codejam.uber :as u]))

(defn qf [num den]
  (let [q (quot num den)
        r (rem  num den)]
    (if (= 0 r)
      q
      (+ 1 q)))
  )

(defn attempt1 [s names q]
  (cond
    (> s q)
    0

    (= s q)
    1

    (< s q)
    (dec (qf q (dec s))))
  )

(defn calculate-number-of-switches [{:keys [number-of-engines queries]}]
  (loop [switches 0
         current-batch #{}
         queries-remaining queries]
    (if (not-empty queries-remaining)
      (let [len (count current-batch)
            q (first queries-remaining)
            new-batch (conj current-batch q)
            new-len (count new-batch)]
        (if (> number-of-engines new-len)
          (recur switches
                 new-batch
                 (next queries-remaining))
          (recur (inc switches)
                 #{q}
                 (next queries-remaining))))
      switches)))



(defn problem-a []
  (let [n (inc (read-string (read-line)))]
    (doseq [x (range 1 n)]
      (let [s       (u/read-num)
            names   (u/read-lines s)
            q       (u/read-num)
            queries (u/read-lines q)]
        (println (str "Case #" x ": "  (calculate-number-of-switches
                                        {:number-of-engines s
                                         :queries           queries})))))))

