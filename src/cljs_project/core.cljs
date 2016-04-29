(ns cljs-project.core
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as r])
  (:import goog.History))

(def app-state (r/atom {}))


(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))


(defn routes []
  (secretary/set-config! :prefix "#")
  
  (defroute "/" []
    (swap! app-state assoc :page :page1))
  
  (defroute "/page2" []
    (swap! app-state assoc :page :page2))
  
  (hook-browser-navigation!))


(defn page1 [] 
  [:div
   [:h1 "Page 1"]
   [:a {:href "#/page2"} "Page Two"]])


(defn page2 [] 
  [:div
   [:h1 "Page 2"]
   [:a {:href "#/"} "Page One"]])


(defmulti current-page #(:page @app-state))
(defmethod current-page :page1 []
  [page1])
(defmethod current-page :page2 []
  [page2])
(defmethod current-page :default []
  [:div ])


(routes)
(r/render [current-page] (.getElementById js/document "app"))
