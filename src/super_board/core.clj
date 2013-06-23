(ns super-board.core
  (require [super-board.data-to-html :as data-to-html]
           [super-board.structure :as structure]))

(def start-state {:players ["Pigge" "Kalle" "Hobbe"]
                  :zones [:hand :deck :table]
                  :turn "Pigge"})

(defn switch-player [current-player players]
  (let [other (structure/next-player-map players)]
    (other current-player)))

(defn tick [game-state]
  (update-in game-state [:turn] switch-player (:players game-state)))

(def game-states (structure/game-reduce start-state tick 10))

(spit "html/output.html" (data-to-html/convert game-states))

