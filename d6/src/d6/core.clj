(ns d6.core
  (:gen-class))

(defn find-marker
  [line position n]
  (if (apply distinct? (take n line))
    position
    (recur (rest line) (+ 1 position) n)))

(find-marker (slurp "/tmp/input.txt") 14 14)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
