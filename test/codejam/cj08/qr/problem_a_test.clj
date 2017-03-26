(ns codejam.cj08.qr.problem-a-test
  (:use midje.sweet)
  (:require [codejam.cj08.qr.problem-a :refer :all]))

(fact "no queries"
      (calculate-number-of-switches
       {:number-of-engines  2
        :queries []}) => 0)

(facts "two engines"
       (fact "both engines are required"
             (calculate-number-of-switches
              {:number-of-engines  2
               :queries ["A" "B"]}) => 1)
       (fact "only one engine was searched"
             (calculate-number-of-switches
              {:number-of-engines  2
               :queries ["B" "B"]}) => 0))

(facts "when there are "
       (let [names irrelevant]
         (tabular
          (fact ""
                (attempt1 ?s names ?q) => ?ans)
          ?s ?q ?ans
          2  1  0
          2  2  1
          2  3  2
          2  4  3
          2  5  4
          3  1  0
          3  2  0
          3  3  1
          3  4  1
          3  5  2
          3  6  2
          3  7  3
          3  8  3
          3  9  4
          4  1  0
          4  2  0
          4  3  0
          4  4  1
          4  5  1
          4  6  1
          4  7  2
          )))
