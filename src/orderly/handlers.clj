(ns orderly.handlers
  "Various handler functions used by the application.
  Most important includes router and wrappers"
  (:require
   [ring.util.http-response :as resp] ; ring-http-response
   ;; ring-compatible module while core is for data (serverless) only
   [reitit.ring :as ritr]))

;; TODO
;; [x] add a handler returning current IP
;; [x] add a middleware to automatically reload the server a
;; [x] add a route returning another message


(defn handler-one [request]
  (resp/ok
   (str "<html><body>Halo your IP is: "
     (:remote-addr request)
     "</body></html>")))

(defn handler-two [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello! You are now at Ping!"})

;; routes
(def my-routes
  [["/" handler-one]
   ["/ping" handler-two]
   ["/pong/:id" ::user-id]])

;; (def my-router
;;   (ritr/router my-routes))

(def my-handler
  (ritr/ring-handler
   (ritr/router my-routes)
   (constantly {:status 404, :body "Oops Page Not Found"})))

(comment
  (ritc/routes my-router)
  (my-handler {:uri "/ping"}))
