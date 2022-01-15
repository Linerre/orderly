(ns orderly.main
  "This is the entry point of this application."
  :require
  [orderly.server :as server]
  [orderly.handlers :as handlers])

(defn -main [& args]
  (server/start-server))

(comment
  (-main))
