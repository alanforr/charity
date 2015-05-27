(ns charity.routes.home
  (:require [charity.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [hiccup.core :as h]
            ))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page []
  (layout/render "about.html"))

(def posvec
  [{:title "Puppy poo cleaner"
    :volunteer "Peter"
    :opendate "26-5-15"
    :closedate "26-5-16"}
   {:title "Puppy feeder"
    :volunteer "Paul"
    :opendate "27-6-15"
    :closedate "27-6-16"}])

(defn pos->html [p]
  [:div {:class "position"}
   [:h2 (str "Title " (p :title))]
    [:h3 (str "Volunteer " (p :volunteer))]
    [:h3 (str "Open date " (p :opendate))]
    [:h3 (str "Close date " (p :closedate))]])

(defn positions []
  (h/html [:body
           (concat [[:h1  "Open positions at Animal Shelter"]]
                   (mapv pos->html posvec))]))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/positions" [] (positions)))
