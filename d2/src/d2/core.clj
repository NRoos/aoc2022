(require '[clojure.string :as str])

(ns d2.core
  (:gen-class))

(defn solve
  [file]
  (reduce
    (fn [sum line]
      (+ sum (play-round-2 line)))
    0
    (with-open [rdr (clojure.java.io/reader file)]
      (reduce conj [] (line-seq rdr)))))

(defn points-for-shape
  [pick]
  (case pick
    "ROCK" 1
    "PAPER" 2
    "SCISSORS" 3))

(defn translate-to-shape
  [letter]
  (case letter
    "A" "ROCK"
    "B" "PAPER"
    "C" "SCISSORS"
    "X" "ROCK"
    "Y" "PAPER"
    "Z" "SCISSORS"))

(defn play-round
  [line]
  (let [opponent  (translate-to-shape (first (str/split line #" ")))
        me        (translate-to-shape (second (str/split line #" ")))]
    (+ (points-for-shape me)
       (case
         (mod (-
               (- 1 (points-for-shape me))
               (- 1 (points-for-shape opponent)))
              3)
         0 3
         1 0
         2 6))))

(defn lose
  [opponent]
  (case opponent
    "ROCK" "SCISSORS"
    "SCISSORS" "PAPER"
    "PAPER" "ROCK"))

(defn win
  [opponent]
  (case opponent
    "SCISSORS" "ROCK"
    "ROCK" "PAPER"
    "PAPER" "SCISSORS"))

(defn play-round-2
  [line]
  (let [opponent  (translate-to-shape (first (str/split line #" ")))
        outcome   (second (str/split line #" "))]
    (case outcome
      "Y" (+ 3 (points-for-shape opponent))
      "X" (points-for-shape (lose opponent))
      "Z" (+ 6 (points-for-shape (win opponent))))))

(solve "/tmp/input.txt")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
