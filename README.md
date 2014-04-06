# Meridian Shapes-impl

Meridian Shapes-impl is the default implementation of the abstractions provided in Meridian [Shapes] (http://github.com/jsofra/shapes).

Currently it delegates most of the job to Meridian [clj-jts] (http://github.com/jsofra/clj-jts). Eventually it is planned that a pure Clojure implementation will be provided.

## Installation

Add the following dependency to your `project.clj` file:

    [meridian/shapes-impl "0.0.2"]

## Usage

Implementations are provided for both Shapes records and for plain maps with the same structure.

To use the Implementations you should `require` the Shapes library and load the implementation with `use`:

```clojure
user> (require '[meridian.shapes :as ms])
nil
user> (use '[meridian.shapes.impl record map])
nil
user> (ms/union (ms/point [1 2]) (ms/point [2 4]))
#meridian.shapes.MultiPoint{:type :multi-point, :coordinates [[1.0, 2.0] [2.0, 4.0]]}
```

## License

Copyright © James Sofra, 2013

Distributed under the Eclipse Public License, the same as Clojure.
