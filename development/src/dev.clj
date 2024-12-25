;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev
  (:require [com.sernamar.bitcoin.interface :as btc]
            [com.sernamar.config.interface :as config]))

(comment
  (let [connection (config/read-config "dev/bitcoin-connection.edn")]
    (btc/get-balance connection))
  )
