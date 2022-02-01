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
  notes               VARCHAR(100),
  record_created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- :name add-rush-order
-- :command :insert
-- :result :one
-- :doc Insert one record into the table "orders"
INSERT INTO rush_orders (order_num, vendor, ordered, arrival, note)
  VALUES (:order_num, :vendor, :ordered::date, :arrival::date, :notes);
-- The :: is type casting feature of PG

-- :name add-rush-orders
-- :command :insert
-- :result :many
-- :doc Insert multi records into the table "orders"
INSERT INTO rush_orders (order_num, vendor, ordered, arrival, notes)
            VALUES (:order_num, :vendor :order_date::date, :arrival_date::date, :lib_note);
