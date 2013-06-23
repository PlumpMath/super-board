(ns super-board.core
  (require [super-board.data-to-html :as data-to-html]
           [super-board.structure :as structure]))

(defn create-player [nick]
  {:nick nick
   :score 0})

(def start-state {:players [(create-player "Pigge")
                            (create-player "Kalle")
                            (create-player "Hobbe")]
                  :zones [:hand :deck :table]
                  :turn "Pigge"})

(defn switch-player [current-player player-nicks]
  (let [other (structure/next-player-map player-nicks)]
    (other current-player)))

(defn player-nicks [game-state]
  (map :nick (:players game-state)))

(defn tick [game-state]
  (update-in game-state [:turn] switch-player (player-nicks game-state)))

(def game-states (structure/game-reduce start-state tick 10))

(spit "html/output.html" (data-to-html/convert game-states))

