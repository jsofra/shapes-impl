(ns meridian.shapes.impl.helpers.delegation
  (:refer-clojure :exclude [contains?])
  (:require [meridian.shapes.protocols :as msp]
            [meridian.clj-jts :as jts]))

(defn delegate-to-jts [& names]
  (mapv
   #(extend %
     msp/Coercive
     {:coerce (fn [geometry] geometry)}

     msp/SimplicityTest
     {:simple? (fn [geometry] (msp/simple? (jts/map->jts geometry)))}

     msp/Locatable
     {:envelope (fn [geometry] (msp/coerce (msp/envelope (jts/map->jts geometry))))
      :interior-point (fn [geometry]
                        (msp/coerce (msp/interior-point (jts/map->jts geometry))))
      :centroid (fn [geometry] (msp/coerce (msp/centroid (jts/map->jts geometry))))}

     msp/Measurable
     {:length (fn [geometry] (msp/length (jts/map->jts geometry)))
      :area (fn [geometry] (msp/area (jts/map->jts geometry)))}

     msp/SpatialRelations
     {:covered-by? (fn [this other]
                     (msp/covered-by? (jts/map->jts this) (jts/map->jts other)))
      :crosses? (fn [this other] (msp/crosses? (jts/map->jts this) (jts/map->jts other)))
      :within-distance? (fn [this other distance]
                          (msp/within-distance? (jts/map->jts this) (jts/map->jts other) distance))
      :intersects? (fn [this other] (msp/intersects? (jts/map->jts this) (jts/map->jts other)))
      :within? (fn [this other] (msp/within? (jts/map->jts this) (jts/map->jts other)))
      :overlaps? (fn [this other] (msp/overlaps? (jts/map->jts this) (jts/map->jts other)))
      :touches? (fn [this other] (msp/touches? (jts/map->jts this) (jts/map->jts other)))
      :contains? (fn [this other] (msp/contains? (jts/map->jts this) (jts/map->jts other)))
      :disjoint? (fn [this other] (msp/disjoint? (jts/map->jts this) (jts/map->jts other)))
      :relate? (fn [this other] (msp/relate? (jts/map->jts this) (jts/map->jts other)))
      :covers? (fn [this other] (msp/covers? (jts/map->jts this) (jts/map->jts other)))
      :distance (fn [this other] (msp/distance (jts/map->jts this) (jts/map->jts other)))}

     msp/SpatialConstruction
     {:bounding-box (fn [geometry] (msp/coerce (msp/bounding-box (jts/map->jts geometry))))
      :convex-hull (fn [geometry] (msp/coerce (msp/convex-hull (jts/map->jts geometry))))
      :buffer (fn [geometry distance] (msp/coerce (msp/buffer (jts/map->jts geometry) distance)))
      :boundary (fn [geometry] (msp/coerce (msp/boundary (jts/map->jts geometry))))}

     msp/ConstructiveSolidGeometry
     {:intersection (fn [this others]
                      (msp/coerce (msp/intersection (jts/map->jts this)
                                                    (clojure.core/map jts/map->jts others))))
      :sym-difference (fn [this others]
                        (msp/coerce (msp/sym-difference (jts/map->jts this)
                                                        (clojure.core/map jts/map->jts others))))
      :difference (fn [this others]
                    (msp/coerce (msp/difference (jts/map->jts this)
                                                (clojure.core/map jts/map->jts others))))
      :union (fn [this others]
               (msp/coerce (msp/union (jts/map->jts this)
                                      (clojure.core/map jts/map->jts others))))})
   names))
