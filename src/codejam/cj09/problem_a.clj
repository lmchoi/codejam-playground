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
  (let [r (mapv (fn [w t]
                  (contains? t w)) word-seq tokens)]
    (loop [ts tokens
           ws word-seq]
      (if ts
        (if (contains? (first ts) (first ws))
          (recur (next ts) (next ws))
          false)
        true))))

(defn num-of-words-that-match
  [words pattern]
  (let [tokens (read-tokens pattern)]
    (reduce (fn [ret word]
              (if (match? (seq word) tokens)
                (inc ret)
                ret))
            0 words)))

(defn problem-a []
; The alien language is very unique in that every word consists of exactly L lowercase letters. Also, there are exactly D words in this language.
; some of the words may be misinterpreted
; determine the number of possible interpretations for a given pattern.
;A pattern consists of exactly L tokens

  (let [[l d n] (u/read-values)]
    (let [words (sort (u/read-lines d))]
      (doseq [i (range 1 (inc n))]
        (println
         (str "Case #" i ": "
              (num-of-words-that-match words (read-line))))))))
