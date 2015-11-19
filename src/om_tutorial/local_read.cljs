(ns om-tutorial.local-read
  (:require [om-tutorial.parsing :as p]
            [om.next :as om]))

(defn read-widget
  [{:keys [query state parser] :as env} key params]
  (case key
    :people {:value
             (let [value (get @state :people)]
               (if (= :missing value)
                 :missing
                 (mapv #(get-in @state %) value)))}
    :not-found)
  )

(defn read-local
  "The function used by Om parser to read local app state."
  [{:keys [query state parser node] :as env} key params]
  (case key
    :last-error {:value (get @state key)}
    :new-person {:value (get @state key)}
    :widget {:value (p/parse-with-reader read-widget env key false)}
    (do (println key " NOT FOUND")
        {:value :not-found})
    )
  )

(defn merge-tree [state result]
  (println "Asked to merge tree " result " into " state)
  (let [ppl (-> result :widget :people)]
    (cond-> state
            ppl (assoc :people ppl)
            :always (om/default-merge-tree result)
            )
    )
  )

(comment
  )
