;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev
  (:require [com.sernamar.bitcoin-core-rpc.interface :as btc]
            [com.sernamar.config.interface :as config]))

(comment
  (let [rpc-config (config/read-config "dev/rpc-config.edn")]
    (btc/list-wallets rpc-config))
  (let [rpc-config (config/read-config "dev/rpc-config.edn")]
    (btc/get-balance rpc-config))
  (let [rpc-config (config/read-config "dev/rpc-config.edn")]
    (btc/get-balance rpc-config :avoid-reuse false))
  (let [rpc-config (config/read-config "dev/rpc-config.edn")]
    (btc/get-wallet-info rpc-config))
  )
