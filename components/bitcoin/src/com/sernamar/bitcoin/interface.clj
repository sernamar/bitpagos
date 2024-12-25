;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin.interface
  (:require [com.sernamar.bitcoin.core :as core]))

(defn get-balance
  "Returns the balance of the wallet that is currently loaded."
  [connection]
  (core/get-balance connection))
