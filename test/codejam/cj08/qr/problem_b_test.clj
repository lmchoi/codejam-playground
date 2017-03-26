(ns codejam.cj08.qr.problem-b-test
  (:use midje.sweet)
  (:require [codejam.cj08.qr.problem-b :refer :all]))

(fact "sample1"
      (calculate-trains-required {:turnaround 5
                                  :trips [["09:00 12:00"
                                           "10:00 13:00"
                                           "11:00 12:30"]
                                          ["12:02 15:00"
                                           "09:00 10:30"]]
                                  }) => "2 2")

(fact "sample2"
      (calculate-trains-required {:turnaround 2
                                  :trips [["09:00 09:01"
                                           "12:00 12:02"]
                                          []]
                                  }) => "2 0")

(fact "no turnaround time"
      (calculate-trains-required {:turnaround 0
                                  :number-of-trips [1 2]
                                  :trips [["01:40 01:41"]
                                          ["01:39 01:40"
                                           "01:40 01:41"]]}) => "0 2")

(fact "start all trains"
      (calculate-trains-required {:turnaround 5
                                  :number-of-trips [4 2]
                                  :trips [["00:12 00:18"
                                           "00:00 00:10"
                                           "00:00 00:10"
                                           "00:01 00:06"]
                                          ["00:07 00:13"
                                           "00:02 00:11"]]}) => "4 2")

(fact "more test"
        (calculate-trains-required {:turnaround 4
                                    :number-of-trips [7 8]
                                    :trips [["05:13 05:17"
                                             "05:13 05:15"
                                             "05:02 05:04"
                                             "05:08 05:09"
                                             "05:01 05:05"
                                             "05:15 05:19"
                                             "05:13 05:18"]
                                            ["05:06 05:07"
                                             "05:03 05:08"
                                             "05:13 05:14"
                                             "05:04 05:05"
                                             "05:03 05:08"
                                             "05:11 05:16"
                                             "05:04 05:09"
                                             "05:13 05:17"]]}) => "3 5")

#_(facts "when there are "
         (let [names irrelevant]
           (tabular
             (fact ""
                   (attempt1 ?s names ?q) => ?ans)
             ?s ?q ?ans
             4  7  2
             )))
