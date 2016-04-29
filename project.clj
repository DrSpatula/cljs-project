(defproject cljs-project "0.1.0-SNAPSHOT"
  :description "ClojureScript project setup to be templatized"
  
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]]

  :plugins [[lein-cljsbuild "1.1.3"]]

  :clean-targets ^{:protect false} ["resources/public/js/main.js"
                                    "resources/public/js/out"]
  
  :cljsbuild {
              :builds [{ :id "dev"
                         :source-paths ["src"]
                         :compiler {:main "cljs-project.core"
                                    :output-to "resources/public/js/main.js"
                                    :output-dir "resources/public/js/out"
                                    :asset-path "js/out"
                                    :optimizations :none
                                    :source-map true}}
                       { :id "prod"
                         :source-paths ["src"]
                         :compiler {:main "cljs-project.core"
                                    :output-to "resources/public/js/main.js"
                                    :optimizations :advanced}}]})
