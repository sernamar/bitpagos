;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin-core-rpc.interface
  (:require [com.sernamar.bitcoin-core-rpc.wallet :as wallet]))

;;; Wallet functions

(defn abandon-transaction
  "Marks in-wallet transaction `transaction-id` as abandoned."
  [rpc-config transaction-id]
  (wallet/abandon-transaction rpc-config transaction-id))

(defn abort-rescan
  "Stops current wallet rescan triggered by an RPC call."
  [rpc-config ]
  (wallet/abort-rescan rpc-config))

(defn add-multisig-address
  "Adds a nrequired-to-sign multisignature address to the wallet."
  [rpc-config nrequired keys & {:keys [address-type label] :as options}]
  (wallet/add-multisig-address rpc-config nrequired keys options))

(defn backup-wallet
  "Safely copies the current wallet file to the specified destination, which can either be a directory or a path with a filename."
  [rpc-config destination]
  (wallet/backup-wallet rpc-config destination))

;; TODO (sernamar): test bump-fee, as its `options` argument is a bit tricky
(defn bump-fee
  "Bumps the fee of an opt-in-RBF transaction `transaction-id`, replacing it with a new transaction."
  [rpc-config transaction-id & {:keys [estimate-mode fee-rate original-change-index outputs replaceable] :as options}]
  (wallet/bump-fee rpc-config transaction-id options))

(defn create-wallet
  "Creates and loads a new wallet."
  [rpc-config wallet-name & {:keys [avoid-reuse blank descriptors disable-private-keys external-signer load-on-startup passphrase]
                             :as options}]
  (wallet/create-wallet rpc-config wallet-name options))

(defn create-wallet-descriptor
  "Creates the wallet's descriptor for the given address type."
  [rpc-config address-type & {:keys [hdkey internal] :as options}]
  (wallet/create-wallet-descriptor rpc-config address-type options))

(defn dump-private-key
  "Reveals the private key corresponding to `address`."
  [rpc-config address]
  (wallet/dump-private-key rpc-config address))

(defn dump-wallet
  "Dumps all wallet keys in a human-readable format to a server-side file."
  [rpc-config filename]
  (wallet/dump-wallet rpc-config filename))

(defn encrypt-wallet
  "Encrypts the wallet with `passphrase`."
  [rpc-config passphrase]
  (wallet/encrypt-wallet rpc-config passphrase))

(defn get-addresses-by-label
  "Returns the list of addresses assigned the specified label."
  [rpc-config label]
  (wallet/get-addresses-by-label rpc-config label))

(defn get-address-info
  "Returns information about the given bitcoin address."
  [rpc-config address]
  (wallet/get-address-info rpc-config address))

(defn get-balance
  "Returns the total available balance."
  [rpc-config & {:keys [avoid-reuse include-watchonly minconf]
                 :as options}]
  (wallet/get-balance rpc-config options))

(defn get-balances
  "Returns an object with all balances in BTC."
  [rpc-config]
  (wallet/get-balances rpc-config))

(defn get-hd-keys
  "List all BIP 32 HD keys in the wallet and which descriptors use them."
  [rpc-config & {:keys [active-only private] :as options}]
  (wallet/get-hd-keys rpc-config options))

(defn get-new-address
  "Returns a new Bitcoin address for receiving payments."
  [rpc-config & {:keys [address-type label] :as options}]
  (wallet/get-new-address rpc-config options))

(defn get-raw-change-address
  "Returns a new Bitcoin address for receiving change.
This is for use with raw transactions, NOT normal use."
  [rpc-config & {:keys [address-type] :as options}]
  (wallet/get-raw-change-address rpc-config options))

(defn get-received-by-address
  "Returns the total amount received by the given address in transactions with at least `minconf` confirmations."
  [rpc-config address & {:keys [include-immature-coinbase minconf] :as options}]
  (wallet/get-received-by-address rpc-config address options))

(defn get-received-by-label
  "Returns the total amount received by addresses with `label` in transactions with at least `minconf` confirmations."
  [rpc-config label & {:keys [include-immature-coinbase minconf] :as options}]
  (wallet/get-received-by-label rpc-config label options))

(defn get-transaction
  "Get detailed information about in-wallet transaction `transaction-id`."
  [rpc-config transaction-id & {:keys [include-watchonly verbose] :as options}]
  (wallet/get-transaction rpc-config transaction-id options))

(defn get-wallet-info
  "Returns an object containing various wallet state info."
  [rpc-config]
  (wallet/get-wallet-info rpc-config))

(defn import-address
  "Adds an address or script (in hex) that can be watched as if it were in your wallet but cannot be used to spend. Requires a new wallet backup."
  [rpc-config address & {:keys [label p2sh rescan] :as options}]
  (wallet/import-address rpc-config address options))

(defn import-descriptors
  "Imports descriptors. Requires a new wallet backup."
  [rpc-config requests]
  (wallet/import-descriptors rpc-config requests))

(defn import-multi
  "Imports addresses/scripts (with private or public keys, redeem script (P2SH)), optionally rescanning the blockchain from the earliest creation time of the imported scripts. Requires a new wallet backup."
  [rpc-config requests & {:keys [rescan] :as options}]
  (wallet/import-multi rpc-config requests options))

(defn import-private-key
  "Adds a private key (as returned by `dump-private-key`) to your wallet. Requires a new wallet backup."
  [rpc-config private-key & {:keys [label rescan] :as options}]
  (wallet/import-private-key rpc-config private-key options))

(defn import-pruned-funds
  "Imports funds without rescan."
  [rpc-config raw-transaction transaction-out-proof]
  (wallet/import-pruned-funds rpc-config raw-transaction transaction-out-proof))

(defn import-public-key
  "Adds a public key (in hex) that can be watched as if it were in your wallet but cannot be used to spend. Requires a new wallet backup."
  [rpc-config public-key & {:keys [label rescan] :as options}]
  (wallet/import-public-key rpc-config public-key options))

(defn import-wallet
  "Imports keys from a wallet dump file (see `dump-wallet`). Requires a new wallet backup to include imported keys."
  [rpc-config filename]
  (wallet/import-wallet rpc-config filename))

(defn keypool-refill
  "Fills the keypool. Requires wallet passphrase to be set with `wallet-passphrase` call if wallet is encrypted."
  [rpc-config & {:keys [newsize] :as options}]
  (wallet/keypool-refill rpc-config options))

(defn list-address-groupings
  "Lists groups of addresses which have had their common ownership made public by common use as inputs or as the resulting change in past transactions."
  [rpc-config]
  (wallet/list-address-groupings rpc-config))

(defn list-descriptors
  "Lists descriptors imported into a descriptor-enabled wallet."
  [rpc-config & {:keys [private] :as options}]
  (wallet/list-descriptors rpc-config options))

(defn list-labels
  "Returns the list of all labels, or labels that are assigned to addresses with a specific purpose."
  [rpc-config & {:keys [purpose] :as options}]
  (wallet/list-labels rpc-config options))

(defn list-lock-unspent
  "Returns a list of temporarily unspendable outputs."
  [rpc-config]
  (wallet/list-lock-unspent rpc-config))

(defn list-received-by-address
  "Lists balances by receiving address."
  [rpc-config & {:keys [address-filter include-empty include-immature-coinbase include-watchonly minconf] :as options}]
  (wallet/list-received-by-address rpc-config options))

(defn list-received-by-label
  "Lists received transactions by label."
  [rpc-config & {:keys [include-empty include-immature-coinbase include-watchonly minconf] :as options}]
  (wallet/list-received-by-label rpc-config options))

(defn list-since-block
  "Gets all transactions in blocks since block `blockhash`, or all transactions if omitted."
  [rpc-config & {:keys [blockhash include-change include-removed include-watchonly label target-confirmations] :as options}]
  (wallet/list-since-block rpc-config options))

(defn list-transactions
  "Returns up to `count` most recent transactions skipping the first `from` transactions. If `label` is provided, only incoming transactions paying to addresses with the specified label are returned."
  [rpc-config & {:keys [account count from include-watchonly label] :as options}]
  (wallet/list-transactions rpc-config options))

(defn list-unspent
  "Returns array of unspent transaction outputs with between `minconf` and `maxconf` (inclusive) confirmations."
  [rpc-config & {:keys [addresses include-unsafe minconf maxconf query-options] :as options}]
  (wallet/list-unspent rpc-config options))

(defn list-wallet-dir
  "Returns a list of wallets in the wallet directory."
  [rpc-config]
  (wallet/list-wallet-dir rpc-config))

(defn list-wallets
  "Returns a list of currently loaded wallets."
  [rpc-config]
  (wallet/list-wallets rpc-config))

(defn load-wallet
  "Loads a wallet from a wallet file or directory."
  [rpc-config filename & {:keys [load-on-startup] :as options}]
  (wallet/load-wallet rpc-config filename options))

(defn lock-unspent
  "Updates list of temporarily unspendable outputs."
  [rpc-config unlock & {:keys [persistent transactions] :as options}]
  (wallet/lock-unspent rpc-config unlock options))

(defn migrate-wallet
  "Migrates the wallet to a descriptor wallet. A new wallet backup will need to be made."
  [rpc-config &{:keys [passphrase wallet-name] :as options}]
  (wallet/migrate-wallet rpc-config options))

(defn new-key-pool
  "Entirely clears and refills the keypool.

WARNING: On non-HD wallets, this will require a new backup immediately, to include the new keys.
Requires wallet passphrase to be set with `wallet-passphrase` call if wallet is encrypted."
  [rpc-config]
  (wallet/new-key-pool rpc-config))

(defn psbt-bump-fee
  "Bumps the fee of a PSBT transaction `transaction-id`, replacing it with a new PSBT transaction."
  [rpc-config transaction-id & {:keys [conf-target estimate-mode fee-rate original-change-index outputs replaceable] :as options}]
  (wallet/psbt-bump-fee rpc-config transaction-id options))

(defn remove-pruned-funds
  "Deletes the specified transaction from the wallet. Meant for use with pruned wallets and as a companion to `import-pruned-funds`."
  [rpc-config transaction-id]
  (wallet/remove-pruned-funds rpc-config transaction-id))

(defn rescan-blockchain
  "Rescans the local blockchain for wallet related transactions."
  [rpc-config & {:keys [start-height stop-height] :as options}]
  (wallet/rescan-blockchain rpc-config options))

(defn restore-wallet
  "Restores and loadsa wallet from backup."
  [rpc-config wallet-name backup-file & {:keys [load-on-startup] :as options}]
  (wallet/restore-wallet rpc-config wallet-name backup-file options))

(defn send
  "Sends a transaction."
  [rpc-config outputs & {:keys [conf-target estimate-mode fee-rate options] :as all-options}]
  (wallet/send rpc-config outputs all-options))

(defn send-all
  "Spends the value of all (or specific) confirmed UTXOs and unconfirmed change in the wallet to one or more recipients."
  [rpc-config recipients & {:keys [conf-target estimate-mode fee-rate options] :as all-options}]
  (wallet/send-all rpc-config recipients all-options))

(defn send-many
  "Sends multiple times."
  [rpc-config amounts & {:keys [comment conf-taget estimate-mode fee-rate minconf replaceable subtractfeefrom verbose] :as options}]
  (wallet/send-many rpc-config amounts options))

(defn send-to-address
  "Sends an amount to a given address."
  ;; "comment" "comment_to" subtractfeefromamount replaceable conf_target "estimate_mode" avoid_reuse fee_rate verbose
  [rpc-config amount address & {:keys [avoid-reuse comment comment-to conf-target estimate-mode fee-rate replaceable subtractfeefromamount verbose] :as options}]
  (wallet/send-to-address rpc-config amount address options))

(defn set-hd-seed
  "Sets or generates a new HD wallet seed.

Note that you will need to MAKE A NEW BACKUP of your wallet after setting the HD wallet seed.
Requires wallet passphrase to be set with walletpassphrase call if wallet is encrypted.
Note: This command is only compatible with legacy wallets."
  [rpc-config & {:keys [newkeypool seed] :as options}]
  (wallet/set-hd-seed rpc-config options))

(defn set-label
  "Sets the label associated with the given address."
  [rpc-config address label]
  (wallet/set-label rpc-config address label))

(defn set-transaction-fee
  "Sets the transaction fee rate in BTC/kvB for this wallet."
  [rpc-config amount]
  (wallet/set-transaction-fee rpc-config amount))

(defn set-wallet-flag
  "Changes the state of the given walletflag for a wallet."
  [rpc-config flag & {:keys [value] :as options}]
  (wallet/set-wallet-flag rpc-config flag options))

(defn sign-message
  "Signs a message with the private key of an address."
  [rpc-config address message]
  (wallet/sign-message rpc-config address message))

(defn sign-raw-transaction-with-wallet
  "Signs inputs for raw transaction (serialized, hex-encoded)."
  [rpc-config hex-string & {:keys [prevtxs sighashtype] :as options}]
  (wallet/sign-raw-transaction-with-wallet rpc-config hex-string options))

(defn simulate-raw-transaction
  "Calculates the balance change resulting in the signing and broadcasting of the given transaction(s)."
  [rpc-config & {:keys [rawtxs options] :as all-options}]
  (wallet/simulate-raw-transaction rpc-config all-options))

(defn unload-wallet
  "Unloads the wallet specified by `wallet-name`."
  [rpc-config & {:keys [wallet-name load-on-startup] :as options}]
  (wallet/unload-wallet rpc-config options))

(defn upgrade-wallet
  "Upgrades the wallet. Upgrades to the latest version if no version number is specified."
  [rpc-config & {:keys [version] :as options}]
  (wallet/upgrade-wallet rpc-config options))

(defn wallet-create-funded-psbt
  "Creates an funds a transaction in the Partially Signed Bitcoin Transaction format."
  [rpc-config & {:keys [inputs outputs locktime options bip32derivs] :as all-options}]
  (wallet/wallet-create-funded-psbt rpc-config all-options))

(defn wallet-display-address
  "Dispalys address on an external signer for verification."
  [rpc-config address]
  (wallet/wallet-display-address rpc-config address))

(defn wallet-lock
  "Removes the wallet encryption key from memory, locking the wallet."
  [rpc-config]
  (wallet/wallet-lock rpc-config))

(defn wallet-passphrase
  "Stores the wallet decryption key in memory for `timeout` seconds."
  [rpc-config passphrase timeout]
  (wallet/wallet-passphrase rpc-config passphrase timeout))

(defn wallet-passphrase-change
  "Changes the wallet passphrase from `old-passphrase` to `new-passphrase`."
  [rpc-config old-passphrase new-passphrase]
  (wallet/wallet-passphrase-change rpc-config old-passphrase new-passphrase))

(defn wallet-process-psbt
  "Updates a PSBT with input information from our wallet and then signs inputs that we can sign for."
  [rpc-config psbt & {:keys [bip32derivs finalize sighashtype sign] :as options}]
  (wallet/wallet-process-psbt rpc-config psbt options))
