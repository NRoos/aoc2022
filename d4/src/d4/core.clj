(ns d4.core)

(defn parse-ranges
  [line]
  (map (fn [x] (Integer/parseInt x)) (clojure.string/split line #"-")))

(defn solve
  [lines amount]
  (if (empty? lines)
    amount
    (let [[a b] (clojure.string/split (first lines) #",")
          [a-start a-end] (parse-ranges a)
          [b-start b-end] (parse-ranges b)]
      (recur
        (rest lines)
        (if (or (<= a-start b-start b-end a-end)
                (<= b-start a-start a-end b-end))
          (+ amount 1)
          amount)))))

(defn solve-2
  [lines amount]
   (if (empty? lines)
     amount
     (let [[a b] (clojure.string/split (first lines) #",")
           [a-start a-end] (parse-ranges a)
           [b-start b-end] (parse-ranges b)]
       (recur
         (rest lines)
         (if (or (<= a-start b-start a-end)
                 (<= b-start a-start b-end))
           (+ amount 1)
           amount)))))

(solve (clojure.string/split-lines (slurp "/tmp/input.txt")) 0)
(solve-2 (clojure.string/split-lines (slurp "/tmp/input.txt")) 0)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
