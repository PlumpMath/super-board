(ns super-board.core
  (require [super-board.data-to-html :as data-to-html]
           [super-board.structure :as structure]))

(def start-state {:players ["Pigge" "Kalle" "Hobbe"]
                  :zones [:hand :deck :table]
                  :turn "Pigge"
                  :whatever []})

(defn next-player-map [players]
  {"Kalle" "Hobbe"
   "Hobbe" "Kalle"})

(defn switch-player [current-player players]
  (let [other (next-player-map players)]
    (other current-player)))

(defn tick [game-state]
  (update-in game-state [:turn] switch-player (:players game-state)))

(def game-states (structure/game-reduce start-state tick 5))

(spit "html/output.html" (data-to-html/convert game-states))

