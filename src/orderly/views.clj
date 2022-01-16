(ns orderly.views
  (:require
   [selmer.parser :as slmp]))

;; TODO:
;; [] add a simple home/index page with 3 views/routes:
;; 1. Course Reserves
;; 2. CDL
;; 3. Others
;; [] improve ring-default-handler

;; turn off the cache for dev
(slmp/cache-off!)

(defn render-home []
  (slmp/render-file "html/home.html" {:item "Course Reserves"}))

(comment
  (render-home))
