(ns super-board.core
  (require [super-board.data-to-html :as data-to-html]))

(def start-state {:players ["Kalle" "Hobbe"]
                  :zones [:hand :deck :table]
                  :turn 0})

(defn tick [game-state]
  (update-in game-state [:turn] inc))

(defn game-reduce [start-state step-fn turns]
  (loop [result []
         state start-state
         turns-left turns]
    (if (pos? turns-left)
      (let [new-state (step-fn state)]
        (recur (conj result new-state)
               new-state
               (dec turns-left)))
      result)))

(def game-states (game-reduce start-state tick 5))

(spit "html/output.html" (data-to-html/convert game-states))

