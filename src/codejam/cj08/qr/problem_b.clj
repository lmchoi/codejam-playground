(ns codejam.cj08.qr.problem-b
  (:require [clj-time.format :as f]
            [clj-time.core :as t]
            [clojure.string :as s]
            [codejam.uber :as u]))

(defn trip-str->trip-time [ts]
  (let [trip-times (s/split ts #" ")]
    (map #(u/parse-HHmm %) trip-times)))

(defn parse-trips [trips from-to]
  (map #(->> %
            trip-str->trip-time
            (interleave from-to))
       trips))

(defn parse-inputs []
  (let [t        (read-string (read-line)) ;minutes
        [na nb]  (map read-string (s/split (read-line) #" "))
        ab-trips (doall (take na (repeatedly read-line)))
        ba-trips (doall (take nb (repeatedly read-line)))]
    {:turnaround t
     :trips (->> (concat (parse-trips ab-trips [:a :b])
                         (parse-trips ba-trips [:b :a]))
                 (sort-by second))}))

(defn start-train [ret from-station]
  (case from-station
    :a (update ret 0 inc)
    :b (update ret 1 inc)))

(defn update-stations
  [trains-at to-station arrival-time turnaround]
  (update trains-at to-station #(conj % (t/plus arrival-time (t/minutes turnaround)))))

(defn calculate-trains-required
  [{:keys [turnaround trips]}]

  (loop [ret [0 0]
         remaining-trips trips
         trains-at {:a [] :b []}]

         (if remaining-trips
           (let [[from-station departure-time to-station arrival-time] (first remaining-trips)
                 next-departing-train-time (first (from-station trains-at))]
             (cond
               (nil? next-departing-train-time)
               (recur (start-train ret from-station)
                      (next remaining-trips)
                      (update-stations trains-at to-station arrival-time turnaround))

               (not (t/after? next-departing-train-time departure-time))
               (recur ret
                      (next remaining-trips)
                      (update (update-stations trains-at to-station arrival-time turnaround) from-station next))

               :default
               (recur (start-train ret from-station)
                      (next remaining-trips)
                      (update-stations trains-at to-station arrival-time turnaround))
               ))
           ret)))

(defn parse-outputs [ret]
  (u/output-array ret))

(defn problem-b []
  (let [n (inc (read-string (read-line)))]
    (doseq [x (range 1 n)]
      (println (str "Case #" x ": " (parse-outputs (calculate-trains-required
                                                     (parse-inputs))))))))

