(ns orderly.handlers
  "Various handler functions used by the application.
  Most important includes router and wrappers"
  (:require
   [ring.util.http-response :as resp] ; ring-http-response
   [ring.util.http-predicates :as pred]
   ;; ring-compatible module while core is for data (serverless) only
   [reitit.ring :as ritr]
   [selmer.parser :as slmp]))

(defn handle-home
  "Render the home.html using selmer.parser/render-file"
  [req]
  (resp/ok (slmp/render-file "html/home.html"
                    {:resip "localhost",
                     :view1 "Reserves",
                     :view2 "CDL",
                     :view3 "Others",
                     :ip (:remote-addr req)})))

(defn handle-res
  "Handler fn for the res route."
  [req]
  (resp/ok
   (slmp/render-file "html/res.html" {:name "You"})))

(defn handle-cdl
  "Handler fn for the res route."
  [req]
  (resp/ok
   (slmp/render-file "html/cdl.html" {:name "You"})))

(defn handle-oth
  "Handler fn for the res route."
  [req]
  (resp/ok
   (slmp/render-file "html/other.html" {:name "You"})))

(defn handler-two [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello! You are now at Ping!"})

;; routes
(def my-routes
  [["/" {:get handle-home}]
   ["/reserves" {:get handle-res}]
   ["/cdl" {:get handle-cdl}]
   ["/other" {:get handle-oth}]
   ])

;; TODO
;; [ ] Figure out why the following code doesn't work as expected
(comment
  "Requst (as a map) always gets passed to a route fn"
  ["/reserves" {:name ::reserves
              :file {:name "res"
                     :type "html"
                     :path "html/res.html"}
              :love "clojure"
              :hate "javascript"
              :get (fn [{{:keys [name]} :file}]
                     (resp/ok {:filename name}))}])
;; expected: => {:filename res}
;; got: => {:filename nil}

;;; ring-handler takes two args
(comment
  ritr/ring-handler (router) (ring-default-handler))


;; TODO
;; [] Add templates for default-handler pages, not-found in particular
(def my-handler
  (ritr/ring-handler
   (ritr/router my-routes)
   (ritr/create-default-handler
    {:not-found
     (constantly (resp/not-found "404 - Page not found"))
     :method-not-allowed
     (constantly (resp/method-not-allowed "405 - Operations not allowed"))
     :not-acceptable
     (constantly (resp/not-acceptable "406 - Not acceptable"))
     })))

(comment
  (ritc/routes my-router)
  (my-handler {:uri "/ping"}))
