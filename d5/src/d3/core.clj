(require '[clojure.string :as str])

(ns d3.core
  (:gen-class))

(def test
  {
   1 ['N' 'Z']
   2 ['D' 'C' 'M']
   3 ['P']
   })

(def start
  {
   1 ['F' 'T' 'N' 'Z' 'M' 'G' 'H' 'J']
   2 ['J' 'W' 'V']
   3 ['H' 'T' 'B' 'J' 'L' 'V' 'G']
   4 ['L' 'V' 'D' 'C' 'N' 'J' 'P' 'B']
   5 ['G' 'R' 'P' 'M' 'S' 'W' 'F']
   6 ['M' 'V' 'N' 'B' 'F' 'C' 'H' 'G']
   7 ['R' 'M' 'G' 'H' 'D']
   8 ['D' 'Z' 'V' 'M' 'N' 'H']
   9 ['H' 'F' 'N' 'G']
   })

(defn move-n
  [stacks from to amount]
  (if (< amount 1)
    stacks
    (let [value-to-move (first (get stacks from))]
      (if (nil? value-to-move)
        stacks
        (recur
          (assoc
            (assoc
              stacks
              to
              (cons value-to-move (get stacks to)))
            from
            (if (empty? (get stacks from))
              (get stacks from)
              (rest (get stacks from))))
          from
          to
          (- amount 1))))))

(defn move-n-9001
  [stacks from to amount]
  (assoc
    (assoc
      stacks
      to
      (concat
        (take amount (get stacks from))
        (get stacks to)))
    from
    (drop amount (get stacks from))))



(defn process-line
  [stacks line]
  (let [[_ amount _ from _ to] line]
    (move-n-9001 stacks (Integer/parseInt from) (Integer/parseInt to) (Integer/parseInt amount))))

(defn process-lines
  [stacks lines]
  (if (empty? lines)
    stacks
    (process-lines
      (process-line stacks (str/split (first lines) #" "))
      (rest lines))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(clojure.pprint/pprint (process-lines start (with-open [rdr (clojure.java.io/reader "/tmp/input.txt")]
    (reduce conj [] (line-seq rdr)))))

(clojure.pprint/pprint (process-lines test (with-open [rdr (clojure.java.io/reader "/tmp/testinput.txt")]
    (reduce conj [] (line-seq rdr)))))

(concat
  (take 3 (get start 1))
  (get start 2))
