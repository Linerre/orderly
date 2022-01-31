-- src/orderly/sql/orders.sql
-- The book orders

-- :name create-rush-orders-table
-- :command :execute
-- :result :raw
-- :doc Create the rush orders table
CREATE TABLE rush_orders (
  id                  SERIAL PRIMARY KEY,
  order_num           VARCHAR(14),
  vendor              VARCHAR(40),
  ordered             DATE NOT NULL DEFAULT CURRENT_DATE, -- order sent date
  arrival             DATE NOT NULL DEFAULT CURRENT_DATE, -- order arrival date
  note                VARCHAR(100),
  record_created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- :name add-rush-order
-- :command :insert
-- :result :one
-- :doc Insert one record into the table "orders"
INSERT INTO rush_orders (order_num, vendor, ordered, arrival, note)
  VALUES (:order_num, :vendor, :ordered::date, :arrival::date, :note);
-- The :: is type casting feature of PG

-- :name add-rush-orders
-- :command :insert
-- :result :many
-- :doc Insert multi records into the table "orders"
INSERT INTO rush_orders (order_num, order_date, arrival_date, lib_note)
            VALUES (:order_num, :order_date::date, :arrival_date::date, :lib_note);
