;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.config.interface
  (:require [aero.core :as aero]
            [clojure.java.io :as io]))

(defn read-config
  "Reads a configuration file from the classpath."
  ([filepath]
   (read-config filepath nil))
  ([filepath profile]
   (aero/read-config (io/resource filepath) {:profile profile})))
