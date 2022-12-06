(ns d4.core)

(def test-input
  ["2-4,6-8"
  "2-3,4-5"
  "5-7,7-9"
  "2-8,3-7"
  "6-6,4-6"
  "2-6,4-8"])

(let [[a b] (clojure.string/split (first test-input) #",")]
  a)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
