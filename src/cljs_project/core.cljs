(ns cljs-project.core
  (:require [reagent.core :as r]))

(defn hello []
  [:p "This seems to be working."])

(r/render [hello] (.getElementById js/document "app"))
