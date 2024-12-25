;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev
  (:require [com.sernamar.bitcoin.interface :as btc]
            [com.sernamar.config.interface :as config]
            [com.sernamar.logging.interface :as log]))

(comment
  (let [connection (config/read-config "dev/bitcoin-connection.edn")]
    (log/info "Getting the balance of the wallet that is currently loaded.")
    (btc/get-balance connection))
  )
