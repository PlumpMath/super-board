(ns super-board.data-to-html
  (require [hiccup.core :as h])
  (require [hiccup.page :as p]))


(def struct-1 [{:name "Erik" :age 26 :siblings ["anna" "jonas" "klara"]}
               {:name "Marie" :age 27}])

(def struct-2 ["erik" "anna" "jonas" "klara"])

(defn visit-vector [index element]
  (list [:div.index (str index)]
        (visit element)))

(defn visit-map-pair [[k v]]
  (list [:div.key (name k)]
        (visit v)))

(defn visit [node]
  (cond (vector? node) [:div.vector (map visit-vector (range) node)]
        (map? node) [:div.map (map visit-map-pair node)]
        :else [:div.type (str node)]))

(defn convert [input]
  (h/html
   (p/include-css "style.css")
   (p/html5 [:body (visit input)])))

(spit "html/output.html" (convert struct-1))


