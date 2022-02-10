(ns orderly.db
  (:require
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [honey.sql :as hsql]
   [next.jdbc :as jdbc]
   [next.jdbc.connection :as connection]
   [next.jdbc.sql :as jsql]
   [next.jdbc.result-set :as rs]
   [java-time :as jt]
   [orderly.sheets :as sh])
  (:import (com.zaxxer.hikari HikariDataSource)))

;; This is the namespace where a database is defined and passed around among fns.
;; next.jdbc itself provides fns to handle sqls (in strings).
;; HugSQL helps generate clj fns from pure *.sql files with special annotations.

;; For database operations (SQLs), refer to files under src/orderly/sql/*.sql.

;; ^:private will cause the var to be accessible only within this namespace
;; a datasource is basically a database-turned object for use by jdbc.

(def ^:private db-spec
  ;; dbtype and dbname are preferred to be drop-in
  ;; replacement for subprotocol and subname
  ;; next.jdbc recognizes both `postgre' and `postgresql' with default port 5432
  {:dbtype   "postgresql",
   :dbname   "nyushlib",
   :username "jeeves",
   :password "wodehouse1915"})

;; This fn will make all the SQLs in orders.sql
;; into SQL fns in Clojure
(hugsql/def-db-fns "orderly/sql/orders.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

;; For convenience in developement only
;; This will create vectorized fns to quickly show what the sql statement
;; defined in *.sql will look like
(hugsql/def-sqlvec-fns "orderly/sql/orders.sql")

;; merge(-with) may be used to add/alter options set by HikariCP
(with-open [^HikariDataSource ds (connection/->pool HikariDataSource db-spec)]
  (.close (jdbc/get-connection ds))
  (get-all-rush-orders ds))

(comment)


;; TODO:
;; Need to figure out how to update the table efficiently

(add-rush-order-sqlvec {:order_num "NYUSH20200202",
                        :vendor    "Amazon",
                        :ordered   "2020-02-02",
                        :arrival   "2020-03-21",
                        :notes     "As rush as possible; NO CDL"})
