(ns orderly.orders
  (:require
   [orderly.db :refer [db]]
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [java-time :as jt]
   ))

;; TODO:
;; [ ] Figure out the adapter option:
;; (hugsql/def-db-fns "path/to/db.sql"
;; {:adapter (next-adapter/hugsql-adapter-next-jdbc)})

;; This fn will make all the SQLs in orders.sql
;; into SQL fns in Clojure
(hugsql/def-db-fns "orderly/sql/orders.sql"
  {:adapter (next-adapter/hugsql-adapter-next-jdbc)})
