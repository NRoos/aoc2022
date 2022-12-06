(ns d3.core
  (:gen-class))

(defn included?
  [character line]
  (if (empty? line)
    false
    (if (= character (first line))
      true
      (recur character (rest line)))))


(defn char-to-priority
  [character]
  (let [c (int character)]
    (if (< c 97)
      (- c 38)
      (- c 96))))

(defn included-three-way?
  [character a b]
  (if (or (empty? a) (empty? b))
    false
    (and (included? character a)
         (included? character b))))

(defn duplicates
  [a b]
  (if (empty? a)
    nil
    (if (included? (first a) b)
      (first a)
      (recur (rest a) b))))

(defn find-triplicate
  [a b c]
  (if (empty? a)
    nil
    (if (included-three-way? (first a) b c)
      (first a)
      (recur (rest a) b c))))


(defn solve-2
  [lines sum]
  (if (empty? lines)
    sum
    (recur (drop 3 lines)
           (+ sum (char-to-priority (apply find-triplicate (take 3 lines)))))))

(defn solve
  [lines sum]
  (if (empty? lines)
    sum
    (recur (rest lines)
           (+ sum (char-to-priority
                           (let [[a b] (split-at (/ (count (first lines)) 2) (first lines))]
                             (duplicates a b)))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println
    (solve (clojure.string/split-lines (slurp "/tmp/input.txt")) 0)
    (solve-2 (clojure.string/split-lines (slurp "/tmp/input.txt")) 0)))
