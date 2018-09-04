(defproject om-tutorial "0.1.0-SNAPSHOT"
  :description "A Tutorial for Om 1.0.0 (next)"
  :dependencies [[org.clojure/clojure "1.9.0" :scope "provided"]
                 [org.clojure/clojurescript "1.10.339" :scope "provided"]

                 [com.github.pholas/devcards "v0.2.5-fix-html-edn-key"]
                 ;;                 [devcards "0.2.5" :exclusions [org.omcljs/om cljsjs/react-dom org.clojure/tools.reader cljsjs/react]]
                 [datascript "0.16.6"]
                 [org.omcljs/om "1.0.0-beta1"]
                 [figwheel-sidecar "0.5.16" :exclusions [clj-time joda-time org.clojure/tools.reader] :scope "test"]
                 [cljsjs/codemirror "5.31.0-0"]]

  :source-paths ["src/main" "src/cards" "src/tutorial"]

  :plugins [[lein-cljsbuild "1.1.1"] [lein-figwheel "0.5.16"] [lein-voom "0.1.0-20180617_140646-g0ba7ec8"]]

  :clean-targets ^{:protect false} ["resources/public/js" "resources/public/cards" "resources/public/tutorial" "target"]

  :figwheel {:build-ids   ["dev" "cards" "tutorial"]
             :server-port 3450}

  :cljsbuild {
              :builds
              [
               {:id           "dev"
                :figwheel     true
                :source-paths ["src/main"]
                :compiler     {:main                 om-tutorial.core
                               :asset-path           "js"
                               :output-to            "resources/public/js/main.js"
                               :output-dir           "resources/public/js"
                               :recompile-dependents true
                               :parallel-build       true
                               :verbose              false}}
               {:id           "cards"
                :figwheel     {:devcards true}
                :source-paths ["src/main" "src/cards"]
                :compiler     {
                               :main                 om-tutorial.cards
                               :source-map-timestamp true
                               :asset-path           "cards"
                               :output-to            "resources/public/cards/cards.js"
                               :output-dir           "resources/public/cards"
                               :recompile-dependents true
                               :parallel-build       true
                               :verbose              false}}
               {:id           "tutorial"
                :figwheel     {:devcards true}
                :source-paths ["src/main" "src/tutorial"]
                :compiler     {
                               :main                 om-tutorial.tutorial
                               :source-map-timestamp true
                               :asset-path           "tutorial"
                               :output-to            "resources/public/tutorial/tutorial.js"
                               :output-dir           "resources/public/tutorial"
                               :parallel-build       true
                               :recompile-dependents true
                               :verbose              false
                               :foreign-libs         [{:provides ["cljsjs.codemirror.addons.closebrackets"]
                                                       :requires ["cljsjs.codemirror"]
                                                       :file     "resources/public/codemirror/closebrackets-min.js"}
                                                      {:provides ["cljsjs.codemirror.addons.matchbrackets"]
                                                       :requires ["cljsjs.codemirror"]
                                                       :file     "resources/public/codemirror/matchbrackets-min.js"}]}}
               {:id           "pages"
                :source-paths ["src/main" "src/tutorial" "src/prod"]
                :compiler     {
                               :main                 core
                               :devcards             true
                               :asset-path           "pages"
                               :output-to            "resources/public/pages/tutorial.js"
                               :output-dir           "resources/public/pages"
                               :parallel-build       false
                               :verbose              true
                               :optimizations        :advanced
                               :foreign-libs         [{:provides ["cljsjs.codemirror.addons.closebrackets"]
                                                       :requires ["cljsjs.codemirror"]
                                                       :file     "resources/public/codemirror/closebrackets-min.js"}
                                                      {:provides ["cljsjs.codemirror.addons.matchbrackets"]
                                                       :requires ["cljsjs.codemirror"]
                                                       :file     "resources/public/codemirror/matchbrackets-min.js"}]}}]}

  :profiles {
             :dev {:source-paths ["src/dev" "src/main" "src/tutorial"]
                   :dependencies [[cider/piggieback "0.3.9"]
                                  [org.clojure/tools.nrepl "0.2.13"]]
                   :repl-options {:init-ns user
                                  :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                                  :port    7001}
                   }
             }

  :certificates ["/Users/jack/myorg/myconfig/nexus/nexus.pem"]
  :mirrors {"central" {:name "central"
                       :url "https://omp.cn.ibm.com:8443/repository/maven-public/"}
            #"clojars" {:name "Internal nexus"
                        :url "https://omp.cn.ibm.com:8443/repository/maven-public/"
                        :repo-manager true}}
  :repositories [["jitpack" "https://jitpack.io"]]
  )
