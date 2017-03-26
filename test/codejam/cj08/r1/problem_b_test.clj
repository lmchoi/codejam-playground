(ns codejam.cj08.r1.problem-b-test
  (:use midje.sweet)
  (:require [codejam.cj08.r1.problem-b :refer :all]))

(facts "parse inputs"
       (fact "sample 3 customers"
             (with-in-str "5\n3\n1 1 1\n2 1 0 2 0\n1 5 0\n" (parse-inputs))
             => {:flavors 5
                 :customers [[[1 1]]
                             [[1 0] [2 0]]
                             [[5 0]]]})

       (fact "sample 2 customers"
             (with-in-str "1\n2\n1 1 0\n1 1 1" (parse-inputs))
             => {:flavors 1
                 :customers [[[1 0]]
                             [[1 1]]]}))

(facts "is possible?"
       (fact
        (solve-problem {:flavors 1
                        :customers [[[1 0]]
                                    [[1 1]]]}) => "IMPOSSIBLE")
       (fact
        (solve-problem {:flavors 5
                        :customers [[[1 1]]
                                    [[1 0] [2 0]]
                                    [[5 0]]]}) => "1 0 0 0 0")
       (fact
        (solve-problem {:flavors 5
                        :customers [[[1 1] [2 0]]
                                    [[1 0] [2 0][3 1]]
                                    [[5 1]]]}) => "0 0 0 0 1"))

(facts "satisfy"
       (tabular
        (fact ""
              (has-flavor? ?flavor ?batch) => ?result)
        ?flavor ?batch ?result
        [1 1] [0] false
        [1 0] [1] false
        [1 0] [0] true
        [1 1] [1] true))
