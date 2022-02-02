(ns orderly.sheets
  (:require
   [google-apps-clj.credentials :as gcred]
   [google-apps-clj.google-drive :as gdrive]
   [google-apps-clj.google-sheets-v4 :as gshv4]))

;; If having no idea what the syntax would look like,
;; cider will offer auto-completion options as you type
(let [scopes [com.google.api.services.sheets.v4.SheetsScopes/SPREADSHEETS_READONLY]
      creds  (gcred/default-credential scopes)]
    (gshv4/get-sheet-info creds ""))
