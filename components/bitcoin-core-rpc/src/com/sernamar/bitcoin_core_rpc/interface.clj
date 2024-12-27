;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin-core-rpc.interface
  {:clj-kondo/config '{:linters {:unused-binding {:exclude-destructured-keys-in-fn-args true}}}}
  (:require [com.sernamar.bitcoin-core-rpc.wallet :as wallet]))

;;; Wallet functions

(defn create-wallet
  "Creates and loads a new wallet."
  [rpc-config wallet-name & {:keys [avoid-reuse blank descriptors disable-private-keys external-signer load-on-startup passphrase]
                             :as options}]
  (wallet/create-wallet rpc-config wallet-name options))

(defn get-balance
  "Returns the total available balance."
  [rpc-config & {:keys [avoid-reuse include-watchonly minconf]
                 :as options}]
  (wallet/get-balance rpc-config options))

(defn get-wallet-info
  "Returns an object containing various wallet state info."
  [rpc-config]
  (wallet/get-wallet-info rpc-config))

(defn list-wallets
  "Returns a list of currently loaded wallets."
  [rpc-config]
  (wallet/list-wallets rpc-config))
