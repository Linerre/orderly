(ns orderly.sheets
  (:require
   [google-apps-clj.credentials :as gauth]
   [google-apps-clj.google-drive :as gdrive]
   [google-apps-clj.google-sheets-v4 :as gsheets]))

;; For all the fns in this file to be run properly, make sure
;; the env var GOOGLE_APPLICATION_CREDENTIALS is set (at least for the current
;; shell session) and points to the correct credentials.json file

;; Define a service to pass to other handlers
;; Think of it as a connection request sent to Google API
(defonce ^:private service
  (let
      [scopes [com.google.api.services.sheets.v4.SheetsScopes/SPREADSHEETS_READONLY]
       creds  (gauth/default-credential scopes)]
    (gsheets/build-service creds)))

(defonce ^:private rush-orders-spreadsht-id
  "1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68")

(defonce ^:private weekly-order-report-spreadsht-id
  "1O8ZpdDwv-cQ-bK8oD_-HJz9VRDBMaoRoPJZxzRIwpjk")

(defonce ^:private cdl-orders-spreadhseet-id
  "TBD")


;; A table that is a vector of rows (which are also vectors)
;; [[row1] [row1] [row3] ... [row5]]
(def first-five-rows
  (get-in
   (gsheets/get-cell-values
    service
    rush-orders-spreadsheet-id
    ["local-orders-since-202003!A2:H6"]) [0]))

(defn all-rows
  "Get all rows from a specific spreadsheet"
  [spreadsht-id srv sht-name sht-range]
  (get-in
   (gsheets/get-cell-values
    srv
    spreadsht-id
    [(str sht-name "!" sht-range)]) [0]))
;; IMPORTANT: Google Sheets seems to provide APIs with A1 notations, that is,
;; no such thing as get-last-row-number, so some manual work is expected!
(comment
  )

(comment
  (gsheets/get-sheet-titles
   service
   "1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68"))

(comment
  ;; verify that the first-five-rows are returned
  (gsheets/get-cell-values
   service
   "1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68"
   ["local-orders-since-202003!A2:H6"]))
