(ns orderly.db
  (:require
   [hikari-cp.core :as hcp]
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as jsql]
   [next.jdbc.result-set :as rs]
   [java-time :as jt]
   [orderly.sheets :as sh])
  (:import (com.zaxxer.hikari HikariDataSource)))

;; This is the namespace where a database is defined and passed around among fns
;; next.jdbc itself provides fns to handle sqls (in strings).
;; HugSQL helps generate clj fns from pure *.sql files with special annotations.
;; hikari-cp wraps up Java's Hikari-CP to make it easy to keep a connection pool.

;; To use HugSQL instead, it will rely on clojure.java.jdbc by default.
;; This will also need org.postgresql to be installed as a dependency.

;; To use HugSQL and next.jdbc together, 'com.layerware/hugsql-adapter-next-jdb'
;; must be installed.

;; For database operations (SQLs), refer to files under src/orderly/sql/*.sql.


;; ^:private will cause the var to be accessible only within this namespace

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
   :dbtype             "postgresql"
   :username           "jeeves"
   :password           "wodehouse1915"
   :database-name      "nyushlib"
   :server-name        "localhost"
   :port-number        5432
   :register-mbeans    false})

;; a datasource is basically a database-turned object for use by jdbc.
;; hikari-cp can create a pooled datasource obj which would otherwise be created
;; using next.jdbc's get-datasource fn.

(defonce datasource
  (delay (hcp/make-datasource datasource-options)))

(def database-connection {:datasource @datasource})


(def ^:private db
  ;; dbtype and dbname are preferred to be drop-in
  ;; replacement for subprotocol and subname
  {:dbtype   "postgresql",
   :dbname   "nyushlib",
   :user     "jeeves",
   :password "wodehouse1915"})


;; This ds is for the use with next.jdbc alone only
(def ds (jdbc/get-datasource db))

;; This fn will make all the SQLs in orders.sql
;; into SQL fns in Clojure
(hugsql/def-db-fns "orderly/sql/orders.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

;; For convenience in developement only
;; This will create vectorized fns to quickly show what the sql statement
;; defined in *.sql will look like
(hugsql/def-sqlvec-fns "orderly/sql/orders.sql")

;; Once there is a database available, pass it to next.jdbc's get-connection,
;; and get a returned connection pool, which will in turn be the 1st argv of
;; HugSQL-generated fns. See <Transactions> of HugSQL doc.
(defn first-connect []
  (add-rush-orders (jdbc/get-connection database-connection) {:orders sh/first-five-rows}))

(comment
  (defn first-connect []
    (add-rush-orders database-connection {:orders sh/first-five-rows})))



;; TODO:
;; Need to figure out how to update the table efficiently

(add-rush-order-sqlvec {:order_num "NYUSH20200202",
                        :vendor    "Amazon",
                        :ordered   "2020-02-02",
                        :arrival   "2020-03-21",
                        :notes     "As rush as possible; NO CDL"})
