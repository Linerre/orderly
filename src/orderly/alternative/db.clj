(ns orderly.alternative.db
  (:require
   [hikari-cp.core :as hcp]
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as jsql]
   [next.jdbc.result-set :as rs]
   [java-time :as jt]
   [orderly.sheets :as sh]))

;; next.jdbc works well with HikariCP, so there is no need to use hikari-cp.
;; Below is just some notes on my first attempt to use both the libs

(def datasource-options
  {:auto-commit        true
   :read-only          false
   :connection-timeout 30000
   :validation-timeout 5000
   :idle-timeout       600000
   :max-lifetime       1800000
   :minimum-idle       10
   :maximum-pool-size  20
   :pool-name          "db-pool"
   :adapter            "postgresql"
   :username           "user"
   :password           "passwd"
   :database-name      "mydb"
   :server-name        "localhost"
   :port-number        5432
   :register-mbeans    false})

(defonce datasource
  (delay (hcp/make-datasource datasource-options)))

;; From now on, @datasource can be passed to next.jdbc's fns.
;; There's NO need to declare a connection var lile below
(def database-connection {:datasource @datasource})
