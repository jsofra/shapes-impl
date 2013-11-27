(ns meridian.shapes.impl.record
  (:require [meridian.shapes.impl.helpers.delegation :refer [delegate-to-jts]])
  (:import [meridian.shapes
            Point LineString LinearRing Polygon
            MultiPoint MultiLineString MultiPolygon
            GeometryCollection]))

(delegate-to-jts
 Point LineString LinearRing Polygon
 MultiPoint MultiLineString MultiPolygon
 GeometryCollection)
