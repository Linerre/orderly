(ns orderly.handler
  (:require
   [reitit.core :as r]))

(def routes
  ["/ping" ::ping])

(def router
  (r/router routes)

)
(r/match-by-name router ::ping)
