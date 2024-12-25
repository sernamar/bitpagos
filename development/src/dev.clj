;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev
  (:require [com.sernamar.bitcoin.interface :as btc]))

(comment
  (let [connection {:url "http://localhost:18443"
                    :rpc-user "rpcuser"
                    :rpc-password "rpcpassword"}]
    (btc/get-balance connection))
  )
