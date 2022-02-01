(ns orderly.db
  (:require
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as jsql]
   [next.jdbc.result-set :as rs]))

;; This is the namespace where a database is defined
;; and passed to next.jdbc later to create datasource.
;; next.jdbc itself provides fns to handle sqls (in strings).

;; To use HugSQL instead, it will rely on clojure.java.jdbc by default.
;; This will also need org.postgresql to be installed as a dependency.

;; To use HugSQL and next.jdbc together, 'com.layerware/hugsql-adapter-next-jdb'
;; must be installed.

;; For database operations, i.e., SQLs, refer to files
;; under src/orderly/dbops/sql/*.sql

;; Since the connection parameters contain passwd,
;; This file should better NOT be tracked/uploaded by git
(def db
  ;; dbtype and dbname are preferred to be drop-in
  ;; replacement for subprotocol and subname
  {:dbtype   "postgresql",
   :dbname   "nyushlib",
   :user     "jeeves",
   :password "wodehouse1915"})

;; This ds is for the use with next.jdbc alone only
(def ds (jdbc/get-datasource db))
