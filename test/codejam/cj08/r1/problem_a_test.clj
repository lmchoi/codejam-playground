(ns codejam.cj08.r1.problem-a-test
  (:use midje.sweet)
  (:require [codejam.cj08.r1.problem-a :refer :all]))

(facts "test all"
       (fact ""
             (find-min-scalar-product {:n 3
                                       :vectors [[1 3 -5]
                                                 [-2 4 1]]}) => -25)
       (fact " "
             (find-min-scalar-product {:n 5
                                       :vectors [[1 2 3 4 5][1 0 1 0 1]]}) => 6)
       )
