(defproject boids "0.1.0-SNAPSHOT"
  :description "Clojure/West Clojurescript Training Demo"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [org.clojure/clojurescript "0.0-1552"]
                 [ring/ring-jetty-adapter "1.1.1"]
                 [compojure "1.1.5"]]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj"]
  :main boids.server
  :cljsbuild
  {
   :builds
   [
    { :source-paths ["src/cljs"]
     :compiler {:optimizations :advanced
                :externs ["resources/public/lib/jquery-externs.js"]
                :output-to "resources/public/build/advanced.js"} }
    {
     :source-paths ["src/cljs"]
     :compiler
     {
      :optimizations :none
      :output-to "resources/public/build/deps.js"
      :output-dir "resources/public/build"
      }}]})
