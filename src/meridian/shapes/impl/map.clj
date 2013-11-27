(ns meridian.shapes.impl.map
  (:require [meridian.shapes.impl.helpers.delegation :refer [delegate-to-jts]]))

(delegate-to-jts clojure.lang.IPersistentMap)
