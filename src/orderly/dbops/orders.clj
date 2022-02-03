(ns orderly.dbops.orders
  (:require
   [orderly.db :refer [db]]
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [java-time :as jt]
   [orderly.sheets :as sh]
   ))

;; TODO:
;; [x] Figure out the adapter option:
;; (hugsql/def-db-fns "path/to/db.sql"
;; {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

;; This fn will make all the SQLs in orders.sql
;; into SQL fns in Clojure
(hugsql/def-db-fns "orderly/dbops/sql/orders.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

;; For convenience in developement only
;; This will create vectorized fns to quickly show what the sql statement
;; defined in *.sql will look like
(hugsql/def-sqlvec-fns "orderly/dbops/sql/orders.sql")


;; TODO:
;; Need to figure out how to update the table efficiently

(add-rush-order-sqlvec {:order_num "NYUSH20200202",
                        :vendor    "Amazon",
                        :ordered   "2020-02-02",
                        :arrival   "2020-03-21",
                        :notes     "As rush as possible; NO CDL"})
