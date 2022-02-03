(ns orderly.sheets
  (:require
   [google-apps-clj.credentials :as gauth]
   [google-apps-clj.google-drive :as gdrive]
   [google-apps-clj.google-sheets-v4 :as gsheets]))

;; If having no idea what the syntax would look like,
;; cider will offer auto-completion options as you type

;; Define a service to pass to other handlers
;; Think of it as a connection request sent to Google API
(def service
  (let
      [scopes [com.google.api.services.sheets.v4.SheetsScopes/SPREADSHEETS_READONLY]
       creds  (gauth/default-credential scopes)]
    (gsheets/build-service creds)))

(def spreadsheet-id "1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68")

;; A table that is a vector of rows (which are also vectors)
;; [[row1] [row1] [row3] ... [row5]]
(def first-five-rows
  (get-in
   (gsheets/get-cell-values
    service
    spreadsheet-id
    ["local-orders-since-2020-3!A2:H6"]) [0]))

(comment
  (gsheets/get-sheet-titles
   service
   "1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68"))

(comment
  (gsheets/get-cell-values
   service
   "1V_4ot76E8RSgjZ08JiZzv0bGQzT8q0YV3cOye8C7m68"
   ["local-orders-since-2020-3!A1:H3"]))
