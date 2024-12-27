;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin-core-rpc.wallet
  (:require [com.sernamar.bitcoin-core-rpc.utils :as utils]))

(defn create-wallet
  [rpc-config wallet-name options]
  (let [params (-> options
                   (merge {:wallet-name wallet-name})
                   utils/transform-keys-to-snake-case)]
    (-> rpc-config
        (utils/rpc-call "createwallet" params))))

(defn get-balance
  [rpc-config options]
  (let [params (-> options
                   utils/transform-keys-to-snake-case)]
    (-> rpc-config
        (utils/rpc-call "getbalance" params))))

(defn get-wallet-info
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "getwalletinfo")
      utils/transform-keys-to-kebab-case))

(defn list-wallets
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "listwallets")))
