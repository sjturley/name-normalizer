(ns name-normalizer.core-test
  (:require [clojure.test :refer :all]
            [name-normalizer.core :refer :all]))

(deftest a-name-normalizer
  (testing "returns empty string when passed an empty string"
    (is (= "" (normalize ""))))
  (testing "returns single word name"
    (is (= "Plato" (normalize "Plato"))))
  (testing "swapsFirstAndLastNames"
    (is (= "Murakami, Haruki" (normalize "Haruki Murakami"))))
  (testing "trimsLeadingAndTrailingWhitespace"
    (is (= "Boi, Big" (normalize "   Big Boi   "))))
  (testing "initializesMiddleName"
    (is (= "Thoreau, Henry D." (normalize "Henry David Thoreau"))))
  (testing "doesNotInitializeOneLetterMiddleName"
    (is (= "Truman, Harry S" (normalize "Harry S Truman"))))
  (testing "initializesEachOfMultipleMiddleNames"
    (is (= "Louis-Dreyfus, Julia S. E." (normalize "Julia Scarlett Elizabeth Louis-Dreyfus"))))
  (testing "appendsSuffixesToEnd"
    (is (= "King, Martin L., Jr." (normalize "Martin Luther King, Jr."))))
  (testing "throwsWhenNameContainsTwoCommas"
    (is (thrown? IllegalArgumentException (normalize "Thurston, Howell, III")))))
