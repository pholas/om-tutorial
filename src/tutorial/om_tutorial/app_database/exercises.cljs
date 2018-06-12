(ns om-tutorial.app-database.exercises)
(def cars-table {:cars/by-id
                 {1 {:id 1 :make "Nissan" :model "Leaf"}
                  2 {:id 2 :make "Dodge" :model "Dart"}
                  3 {:id 3 :make "Ford" :model "Mustang"}}})


(def favorites
                                        ; TODO (exercise 2): merge your cars table from above here
  {:cars/by-id
   {1 {:id 1 :make "Nissan" :model "Leaf"}
    2 {:id 2 :make "Dodge" :model "Dart"}
    3 {:id 3 :make "Ford" :model "Mustang"}}
   :favorite-car [:cars/by-id 1]}
                                        ; TODO (exercise 2): Add a :favorite-car key that points to the Nissan Leaf via an ident
  )

(def ex3-uidb
  {:main-panel {:toolbar {:tools [:toolbar :main]}
                :canvas {:data [:canvas :main]}}
   :toolbar {:main [[:tools/by-id 1] [:tools/by-id 2]]}
   :canvas {:main  [[:data/by-id 5]]}
   :tools/by-id {1 {:id 1 :label "Cut"}
                 2 {:id 2 :label "Copy"} }
   :data/by-id {5 {:id 5 :x 1 :y 3}}
                                        ; TODO (exercise 3): Add tables. See exercise text.
   })
