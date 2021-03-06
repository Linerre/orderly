(ns orderly.server
  "Testing for now"
  (:require
   [org.httpkit.server :refer [run-server]]
   [ring.middleware.reload :refer [wrap-reload]]
   [orderly.handlers :as hls]))


;; TODO
;; [x] start server with a message output on command line
;; [x] change the namespace to core/main/system or the like
;; [x] change the message output to command line
;; [x] add functions to start and stop the server


(def app
  ;; wrap for dev only
  (wrap-reload #'hls/my-handler))

;; server will not be re-defined by (def x)
;; atom makes server state independent of others
;; use @server to access it and
;; reset! to change it value really
;; NOTE when resetting it, @ is not needed!
(defonce server (atom nil))

(defn start-server [& args]
  (reset! server (run-server #'app {:port 9009}))
  (println "Server started on port 9009 successfully!"))

(defn stop-server [& args]
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)
    (println "Server stopped!")))

;; restart = stop + start
(defn restart-server []
  (stop-server)
  (println "Stopped the server and restarting ...")
  (start-server))

(comment (restart-server))
