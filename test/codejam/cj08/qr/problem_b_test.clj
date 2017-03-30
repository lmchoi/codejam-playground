(ns codejam.cj08.qr.problem-b-test
  (:use midje.sweet)
  (:require [codejam.cj08.qr.problem-b :refer :all]
            [codejam.uber :as u]))

(facts "parse inputs"
       (fact "no turnaround"
             (with-in-str
               (str  "0\n" "1 2\n" "01:40 01:41\n" "01:39 01:40\n" "01:40 01:41\n")
               (parse-inputs))
             => {:turnaround 0
                 :trips [[:b (u/parse-HHmm "01:39") :a (u/parse-HHmm "01:40")]
                         [:a (u/parse-HHmm "01:40") :b (u/parse-HHmm "01:41")]
                         [:b (u/parse-HHmm "01:40") :a (u/parse-HHmm "01:41")]]})

       (fact "no turarnound #2"
             (with-in-str
               (str  "0\n4 2\n00:09 00:19\n00:20 00:27\n00:00 00:08\n00:13 00:19\n00:02 00:09\n00:07 00:12")
               (parse-inputs))
             => {:turnaround 0
                 :trips [[:a (u/parse-HHmm "00:00") :b (u/parse-HHmm "00:08")]
                         [:b (u/parse-HHmm "00:02") :a (u/parse-HHmm "00:09")]
                         [:b (u/parse-HHmm "00:07") :a (u/parse-HHmm "00:12")]
                         [:a (u/parse-HHmm "00:09") :b (u/parse-HHmm "00:19")]
                         [:a (u/parse-HHmm "00:13") :b (u/parse-HHmm "00:19")]
                         [:a (u/parse-HHmm "00:20") :b (u/parse-HHmm "00:27")]]})

       #_(fact "9 minutes turnaround"
             (with-in-str
               (str  "9\n4 5\n20:27 20:52\n20:04 20:34\n20:45 21:10\n20:01 20:13\n20:28 20:58\n21:00 21:18\n20:26 20:36\n20:05 20:32\n20:38 21:08")
               (parse-inputs))
             => {:turnaround 0
                 :trips [[:b (u/parse-HHmm "01:39") :a (u/parse-HHmm "01:40")]
                         [:a (u/parse-HHmm "01:40") :b (u/parse-HHmm "01:41")]
                         [:b (u/parse-HHmm "01:40") :a (u/parse-HHmm "01:41")]]}))


#_(fact "sample1"
      (calculate-trains-required {:turnaround 5
                                  :trips [["09:00 12:00"
                                           "10:00 13:00"
                                           "11:00 12:30"]
                                          ["12:02 15:00"
                                           "09:00 10:30"]]
                                  }) => "2 2")

#_(fact "sample2"
      (calculate-trains-required {:turnaround 2
                                  :trips [["09:00 09:01"
                                           "12:00 12:02"]
                                          []]
                                  }) => "2 0")

(fact "no turnaround time"
      (calculate-trains-required {:turnaround 0
                                  :trips      [[:b (u/parse-HHmm "01:39") :a (u/parse-HHmm "01:40")]
                                               [:a (u/parse-HHmm "01:40") :b (u/parse-HHmm "01:41")]
                                               [:b (u/parse-HHmm "01:40") :a (u/parse-HHmm "01:41")]]})
      => [0 2])

(fact "start all trains"
      (calculate-trains-required {:turnaround 5
                                  :trips
                                  [[:a (u/parse-HHmm "00:00") :b (u/parse-HHmm "00:10")]
                                   [:a (u/parse-HHmm "00:00") :b (u/parse-HHmm "00:10")]
                                   [:a (u/parse-HHmm "00:01") :b (u/parse-HHmm "00:06")]
                                   [:b (u/parse-HHmm "00:02") :a (u/parse-HHmm "00:11")]
                                   [:b (u/parse-HHmm "00:07") :a (u/parse-HHmm "00:13")]
                                   [:a (u/parse-HHmm "00:12") :b (u/parse-HHmm "00:18")]]}) => [4 2])

(fact "more test"
      (calculate-trains-required {:turnaround 0
                                  :trips      [[:a (u/parse-HHmm "00:00") :b (u/parse-HHmm "00:08")]
                                               [:b (u/parse-HHmm "00:02") :a (u/parse-HHmm "00:09")]
                                               [:b (u/parse-HHmm "00:07") :a (u/parse-HHmm "00:12")]
                                               [:a (u/parse-HHmm "00:09") :b (u/parse-HHmm "00:19")]
                                               [:a (u/parse-HHmm "00:13") :b (u/parse-HHmm "00:19")]
                                               [:a (u/parse-HHmm "00:20") :b (u/parse-HHmm "00:27")]]})
                                  => [2 2])
#_(fact "more test"
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
