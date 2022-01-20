(ns orderly.views
  (:require
   [selmer.parser :as slmp]))

;; NOTE:
;; this module has been merged into handlers.clj,
;; as for this small project, there are just a few routes
;; If the size of routes grows then define a route.clj module like this one

;; TODO:
;; [x] add a simple home/index page with 3 views/routes:
;;  [x] Course Reserves
;;  [x] CDL
;;  [x] Others
;; [x] improve ring-default-handler
;; [] Make render function accept page-name as its arg
;; turn off the cache for dev
(slmp/cache-off!)

(defn render-home [req]
  "Render the home.html using selmer.parser/render-file"
  (slmp/render-file "html/home.html"
                    {:resip "192.168.0.102",
                     :view1 "Reserves",
                     :view2 "CDL",
                     :view3 "Others",
                     :ip (:remote-addr req)}))


(defn render-view [req]
  "Accept a string arg, which is one of the following page names: res, cdl, other."
  {:body req})

(comment
  (slmp/render-file (format "html/%s.html" page)
                    {:view1 "Reserves",
                     :view2 "CDL",
                     :view3 "Others"})
  (render-home))
