-- src/orderly/sql/orders.sql
-- The table of rush orders with the following cols:
-- Arrival Order# Ordered Vendor ISBN LibNotes ExtraNotes
-- The data format is, however, a total mess, so for the time being,
-- All but empty celldata is converted to string



-- :name create-rush-orders-table
-- :command :execute
-- :result :raw
-- :doc Create the rush orders table. All data is of string type.
CREATE TABLE rush_orders (
  id                  SERIAL PRIMARY KEY,
  order_num           VARCHAR(14),
  vendor              VARCHAR(40) NOT NULL,
  ordered             VARCHAR(20) NOT NULL,   -- order sent date
  arrival             VARCHAR(60),   -- order arrival date
  notes               VARCHAR(150),  -- LibNotes
  title               VARCHAR(100) NOT NULL,
  isbn                VARCHAR(15),
  exnotes             VARCHAR(100),  -- extra notes by WH or YF; Default: NULL
  record_created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- :name add-one-rush-order
-- :command :insert
-- :result :one
-- :doc Insert one record into the table "orders"
INSERT INTO rush_orders (order_num, vendor, ordered, arrival, notes, title, isbn, exnotes)
  VALUES (:order_num, :vendor, :ordered, :arrival, :notes, :title, :isbn, :exnotes);
-- The :: is type-casting feature of PG

-- :name add-rush-orders
-- :command :insert
-- :result :many
-- :doc Insert multi records into the table "orders"
INSERT INTO rush_orders (order_num, vendor, ordered, arrival, notes, title, isbn, exnotes)
  VALUES :tuple*:orders;
