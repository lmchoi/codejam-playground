(ns codejam.core
  (:gen-class)
  (:require [codejam.uber :as u]))

(defn calculate-avg-keystrokes [p-correct keystrokes b]
  (let [p-wrong   (- 1 p-correct)]
    (+ (* p-correct keystrokes)
       (* p-wrong (+ keystrokes b 1)))))

(defn- calculate-keystrokes-required [a b]
  (let [till-end (+ (- b a) 1)
        backspace (map #(+ (* 2 %) till-end) (range 1 a))
        give-up (+ 1 b 1)]
    {:till-end       till-end
     :backspace      backspace
     :give-up        give-up}))

(defn- calculate-p [[rps lps]]
  (apply * (concat rps (map #(- 1 %) lps))))

(defn solve [[a b] ps]
  (let [{:keys [till-end backspace give-up]} (calculate-keystrokes-required a b)
        p-all-correct (apply * ps)
        ks-all-correct (calculate-avg-keystrokes p-all-correct till-end b)
        ks-i (map-indexed (fn [i bs]
                             (calculate-avg-keystrokes
                               (+ p-all-correct (calculate-p (split-at (- a i 1) ps)))
                               bs
                               b))
                           backspace)]
    (if (not-empty ks-i)
      (min ks-all-correct
           give-up
           (apply min ks-i))
      (min ks-all-correct
           give-up))))

(defn problem []
  (let [n (u/read-num)]
    (doseq [i (range 1 (inc n))]
      (let [ab (u/read-values)
            ps (u/read-values)]
        (println
          (str "Case #" i ": "
               (solve ab ps)))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (problem))
