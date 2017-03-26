(ns codejam.cj08.qr.problem-b
  (:require [clj-time.format :as f]
            [clj-time.core :as t]
            [clojure.string :as s]
            [codejam.uber :as u]))

(def time-formatter (f/formatter "HH:mm"))

(defn trip-str->trip-time [ts]
  (let [trip-times (s/split ts #" ")]
    (map #(f/parse time-formatter %) trip-times)))

(defn sort-trips-by-departure [trips]
  (sort-by first trips))

(defn sort-trips-by-arrival [trips]
  (sort-by second trips))

(defn in-time? [departure arrival turnaround]
  (let [tt (t/plus arrival (t/minutes turnaround))
        blah (not (t/before? departure tt))]
    blah))

(defn departing-before? [[departure-a _][departure-b _]]
  (and departure-a
       (t/before? departure-a departure-b)))

(defn train-ready? [trains [departure _]]
  (and trains
    (not (t/before? departure (first trains)))))

(defn park-train [station [_ arrival] turnaround]
  (sort (conj station (t/plus arrival (t/minutes turnaround)))))


                                        ; find earliest train to depart from which station
                                        ; any train parked at that station?
                                        ; if so, remove from parked,
                                        ; add to other station, loop it

; if both are departing at the same time
(defn trains-required [turnaround trips]
  (loop [[ar br :as ret]   [0 0]
         [a-trips b-trips] trips
         [at-a at-b]       [[][]]]
    (let [ab (first a-trips)
          ba (first b-trips)]

      (prn at-a)
      (if (or ab ba)
        (if (departing-before? ba ab)
          (if (train-ready? at-b ba)
            (recur ret
                   [a-trips (next b-trips)]
                   [at-a (next at-b)])
            (recur [ar (inc br)]
                   [a-trips (next b-trips)]
                   [(park-train at-a ba turnaround) at-b]))
          (if (train-ready? at-a ab)
            (recur ret
                   [(next a-trips) b-trips]
                   [(next at-a) at-b])
            (recur [(inc ar) br]
                   [(next a-trips) b-trips]
                   [at-a (park-train at-b ab turnaround)]))
          )
        (u/output-array ret)))))

(defn sort-by-departure [trips]
  (map #(sort-trips-by-departure (map trip-str->trip-time %)) trips))

(defn calculate-trains-required [{:keys [turnaround number-of-trips trips] :as data}]
  (trains-required turnaround (sort-by-departure trips)))

(defn problem-b []
  (let [n (inc (read-string (read-line)))]
    (doseq [x (range 1 n)]
      (let [t       (read-string (read-line)) ;minutes
            [na nb] (map read-string (s/split (read-line) #" "))
            ab-trips (doall (take na (repeatedly read-line)))
            ba-trips (doall (take nb (repeatedly read-line)))]
        (println (str "Case #" x ": "  (calculate-trains-required
                                        {:turnaround t
                                         :number-of-trips [na nb]
                                         :trips [ab-trips ba-trips]})))))))

