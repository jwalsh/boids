(defproject boids "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [org.clojure/clojurescript "0.0-1552"]]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild
  {
   :builds
   [{
     :source-paths ["src/cljs"]
     
     :compiler
     {
      :optimizations :none
      :output-to "resources/public/build/deps.js"
      :output-dir "resources/public/build"
      }}]})
