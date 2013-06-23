(ns super-board.structure)

(defn game-reduce [start-state step-fn turns]
  "Applies the step-fn to the start state 'turns' number of times.
   Returns all the intermediate steps and end result in a vector."
  (loop [result [start-state]
         state start-state
         turns-left turns]
    (if (pos? turns-left)
      (let [new-state (step-fn state)]
        (recur (conj result new-state)
               new-state
               (dec turns-left)))
      result)))