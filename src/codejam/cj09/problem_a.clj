(ns codejam.cj09.problem-a
  (:require [codejam.uber :as u]))

(defn- token->possible-letters
  [token]
  (if (= 1 (count token))
    (into #{} (seq token))
    (into #{} (clojure.string/replace token #"\((.*?)\)" "$1"))))

;; words are sorted
;; pattern is a string
(defn- read-tokens [pattern]
  (map token->possible-letters (re-seq #"\(.*?\)|[a-z]" pattern)))

(defn- match? [word-seq tokens]
  (loop [ts tokens
         ws word-seq]
    (if ts
      (if (contains? (first ts) (first ws))
        (recur (next ts) (next ws))
        false)
      true)))

(defn num-of-words-that-match
  [words pattern]
  (let [tokens (read-tokens pattern)]
    (reduce (fn [ret word]
              (if (match? (seq word) tokens)
                (inc ret)
                ret))
            0 words)))

(defn problem-a []
  (let [[l d n] (u/read-values)]
    (let [words (sort (u/read-lines d))]
      (doseq [i (range 1 (inc n))]
        (println
         (str "Case #" i ": "
              (num-of-words-that-match words (read-line))))))))
