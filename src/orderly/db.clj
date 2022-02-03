(ns orderly.db
  (:require
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as jsql]
   [next.jdbc.result-set :as rs]
   [java-time :as jt]
   [orderly.sheets :as sh]))

;; This is the namespace where a database is defined
;; and passed to next.jdbc later to create datasource.
;; next.jdbc itself provides fns to handle sqls (in strings).

;; To use HugSQL instead, it will rely on clojure.java.jdbc by default.
;; This will also need org.postgresql to be installed as a dependency.

;; To use HugSQL and next.jdbc together, 'com.layerware/hugsql-adapter-next-jdb'
;; must be installed.

;; For database operations, i.e., SQLs, refer to files
;; under src/orderly/sql/*.sql

;; ^:private will cause db to be accessible only within this namespace
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


;; TODO:
;; Need to figure out how to update the table efficiently

(add-rush-order-sqlvec {:order_num "NYUSH20200202",
                        :vendor    "Amazon",
                        :ordered   "2020-02-02",
                        :arrival   "2020-03-21",
                        :notes     "As rush as possible; NO CDL"})
