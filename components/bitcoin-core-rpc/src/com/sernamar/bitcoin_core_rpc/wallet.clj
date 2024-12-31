;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin-core-rpc.wallet
  (:require [com.sernamar.bitcoin-core-rpc.utils :as utils]))

(defn abandon-transaction
  [rpc-config transaction-id]
  (-> rpc-config
      (utils/rpc-call "abandontransaction" [transaction-id])))

(defn abort-rescan
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "abortrescan")))

(defn add-multisig-address
  [rpc-config nrequired keys options]
  (let [params (-> options
                   (merge {:nrequired nrequired
                           :keys keys}))]
    (-> rpc-config
        (utils/rpc-call "addmultisigaddress" params))))

(defn backup-wallet
  [rpc-config destination]
  (-> rpc-config
      (utils/rpc-call "backupwallet" [destination])))

(defn bump-fee
  [rpc-config transaction-id options]
  (let [params (-> options
                   (merge {:txid transaction-id}))]
    (-> rpc-config
        (utils/rpc-call "bumpfee" params))))

(defn create-wallet
  [rpc-config wallet-name options]
  (let [params (-> options
                   (merge {:wallet-name wallet-name}))]
    (-> rpc-config
        (utils/rpc-call "createwallet" params))))

(defn create-wallet-descriptor
  [rpc-config address-type options]
  (let [params (-> options
                   (merge {:address-type address-type}))]
    (-> rpc-config
        (utils/rpc-call "createwalletdescriptor" params))))

(defn dump-private-key
  [rpc-config address]
  (-> rpc-config
      (utils/rpc-call "dumpprivkey" [address])))

(defn dump-wallet
  [rpc-config filename]
  (-> rpc-config
      (utils/rpc-call "dumpwallet" [filename])))

(defn encrypt-wallet
  [rpc-config passphrase]
  (-> rpc-config
      (utils/rpc-call "encryptwallet" [passphrase])))

(defn get-addresses-by-label
  [rpc-config label]
  (-> rpc-config
      (utils/rpc-call "getaddressbylabel" [label])))

(defn get-address-info
  [rpc-config address]
  (-> rpc-config
      (utils/rpc-call "getaddressinfo" [address])))

(defn get-balance
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "getbalance" options)))

(defn get-balances
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "getbalances")))

(defn get-hd-keys
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "gethdkeys" options)))

(defn get-new-address
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "getnewaddress" options)))

(defn get-raw-change-address
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "getrawchangeaddress" options)))

(defn get-received-by-address
  [rpc-config address options]
  (let [params (-> options
                   (merge {:address address}))]
    (-> rpc-config
        (utils/rpc-call "getreceivedbyaddress" params))))

(defn get-received-by-label
  [rpc-config label options]
  (let [params (-> options
                   (merge {:label label}))]
    (-> rpc-config
        (utils/rpc-call "getreceivedbylabel" params))))

(defn get-transaction
  [rpc-config transaction-id options]
  (let [params (-> options
                   (merge {:txid transaction-id}))]
    (-> rpc-config
        (utils/rpc-call "gettransaction" params))))

(defn get-wallet-info
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "getwalletinfo")
      utils/transform-keys-to-kebab-case))

(defn import-address
  [rpc-config address options]
  ;; This RPC call can take over an hour to complete if `rescan` is true.
  ;; If `rescan` is true and the caller hasn't set a `timeout` in the `rpc-config` map, we'll set it to 2 hours.
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (merge {:address address}))
        ]
    (-> rpc-config
        (utils/rpc-call "importaddress" params))))

(defn import-descriptors
  [rpc-config requests]
  ;; This call can take over an hour to complete if using an early timestamp.
  ;; If the caller hasn't set a `timeout` in the `rpc-config` map, we'll set it to 2 hours.
  (let [rpc-config (if (:timeout rpc-config)
                     rpc-config
                     (assoc rpc-config :timeout 7200000))] ; 2 hours in milliseconds
    (-> rpc-config
        (utils/rpc-call "importrequests" [requests]))))

(defn import-multi
  [rpc-config requests options]
  ;; This RPC call can take over an hour to complete if `rescan` is true.
  ;; If `rescan` is true and the caller hasn't set a `timeout` in the `rpc-config` map, we'll set it to 2 hours.
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (merge {:requests requests}))]
    (-> rpc-config
        (utils/rpc-call "importmulti" params))))

(defn import-private-key
  [rpc-config private-key options]
  ;; This RPC call can take over an hour to complete if `rescan` is true.
  ;; If `rescan` is true and the caller hasn't set a `timeout` in the `rpc-config` map, we'll set it to 2 hours.
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (merge {:privkey private-key}))]
    (-> rpc-config
        (utils/rpc-call "importprivkey" params))))

(defn import-pruned-funds
  [rpc-config raw-transaction transaction-out-proof]
  (-> rpc-config
      (utils/rpc-call "importprunedfunds" [raw-transaction transaction-out-proof])))

(defn import-public-key
  [rpc-config public-key options]
  ;; This RPC call can take over an hour to complete if `rescan` is true.
  ;; If `rescan` is true and the caller hasn't set a `timeout` in the `rpc-config` map, we'll set it to 2 hours.
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (merge {:pubkey public-key}))]
    (-> rpc-config
        (utils/rpc-call "importpubkey" params))))

(defn import-wallet
  [rpc-config filename]
  (-> rpc-config
      (utils/rpc-call "importwallet" [filename])))

(defn keypool-refill
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "keypoolrefill" options)))

(defn list-address-groupings
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "listaddressgroupings")))

(defn list-descriptors
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listdescriptors" options)))

(defn list-labels
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listlabels" options)))

(defn list-lock-unspent
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "listlockunspent")))

(defn list-received-by-address
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listreceivedbyaddress" options)))

(defn list-received-by-label
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listreceivedbylabel" options)))

(defn list-since-block
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listsinceblock" options)))

(defn list-transactions
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listtransactions" options)))

(defn list-unspent
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "listunspent" options)))

(defn list-wallet-dir
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "listwalletdir")))

(defn list-wallets
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "listwallets")))

(defn load-wallet
  [rpc-config filename options]
  (let [params (-> options
                   (merge {:filename filename}))]
    (-> rpc-config
        (utils/rpc-call "loadwallet" params))))

(defn lock-unspent
  [rpc-config unlock options]
  (let [params (-> options
                   (merge {:unlock unlock}))]
    (-> rpc-config
        (utils/rpc-call "lockunspent" params))))

(defn migrate-wallet
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "migratewallet" options)))

(defn new-key-pool
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "newkeypool")))

(defn psbt-bump-fee
  [rpc-config transaction-id options]
  (let [params (-> options
                   (merge {:txid transaction-id}))]
    (-> rpc-config
        (utils/rpc-call "psbtbumpfee" params))))

(defn remove-pruned-funds
  [rpc-config transaction-id]
  (-> rpc-config
      (utils/rpc-call "removeprunedfunds" [transaction-id])))

(defn rescan-blockchain
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "rescanblockchain" options)))

(defn restore-wallet
  [rpc-config wallet-name backup-file options]
  (let [params (-> options
                   (merge {:wallet-name wallet-name
                           :backup-file backup-file}))]
    (-> rpc-config
        (utils/rpc-call "restorewallet" params))))

(defn send
  [rpc-config outputs options]
  (let [params (-> options
                   (merge {:outputs outputs}))]
    (-> rpc-config
        (utils/rpc-call "send" params))))

(defn send-all
  [rpc-config recipients options]
  (let [params (-> options
                   (merge {:recipients recipients}))]
    (-> rpc-config
        (utils/rpc-call "sendall" params))))

(defn send-many
  [rpc-config amounts options]
  (let [params (-> options
                   (merge {:amounts amounts}))]
    (-> rpc-config
        (utils/rpc-call "sendmany" params))))

(defn send-to-address
  [rpc-config address amount options]
  (let [params (-> options
                   (merge {:address address
                           :amount amount}))]
    (-> rpc-config
        (utils/rpc-call "sendtoaddress" params))))

(defn set-hd-seed
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "sethdseed" options)))

(defn set-label
  [rpc-config address label]
  (-> rpc-config
      (utils/rpc-call "setlabel" [address label])))

(defn set-transaction-fee
  [rpc-config amount]
  (-> rpc-config
      (utils/rpc-call "settxfee" [amount])))

(defn set-wallet-flag
  [rpc-config flag options]
  (let [params (-> options
                   (merge {:flag flag}))]
    (-> rpc-config
        (utils/rpc-call "setwalletflag" params))))

(defn sign-message
  [rpc-config address message]
  (-> rpc-config
      (utils/rpc-call "signmessage" [address message])))

(defn sign-raw-transaction-with-wallet
  [rpc-config hex-string options]
  (let [params (-> options
                   (merge {:hexstring hex-string}))]
    (-> rpc-config
        (utils/rpc-call "signrawtransactionwithwallet" params))))

(defn simulate-raw-transaction
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "simulaterawtransaction" options)))

(defn unload-wallet
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "unloadwallet" options)))

(defn upgrade-wallet
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "upgradewallet" options)))

(defn wallet-create-funded-psbt
  [rpc-config options]
  (-> rpc-config
      (utils/rpc-call "walletcreatefundedpsbt" options)))

(defn wallet-display-address
  [rpc-config address]
  (-> rpc-config
      (utils/rpc-call "walletdisplayaddress" [address])))

(defn wallet-lock
  [rpc-config]
  (-> rpc-config
      (utils/rpc-call "walletlock")))

(defn wallet-passphrase
  [rpc-config passphrase timeout]
  (-> rpc-config
      (utils/rpc-call "walletpassphrase" [passphrase timeout])))

(defn wallet-passphrase-change
  [rpc-config old-passphrase new-passphrase]
  (-> rpc-config
      (utils/rpc-call "walletpassphrasechange" [old-passphrase new-passphrase])))

(defn wallet-process-psbt
  [rpc-config psbt options]
  (let [params (-> options
                   (merge {:psbt psbt}))]
    (-> rpc-config
        (utils/rpc-call "walletprocesspsbt" params))))
