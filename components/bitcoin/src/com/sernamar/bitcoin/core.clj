;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin.core
  (:require [hato.client :as hc]
            [cheshire.core :as json]))

(defn- rpc-call
  "Sends a JSON-RPC call with the given method and parameters."
  [{:keys [url rpc-user rpc-password] :as _connection} method & params]
  (let [payload {:jsonrpc "2.0"
                 :id (rand-int 1000000)
                 :method method
                 :params params}
        response (hc/post url
                          {:content-type :json
                           :body (json/generate-string payload)
                           :as :json
                           :basic-auth {:user rpc-user :pass rpc-password}
                           :timeout 10000})
        body (:body response)]
    (if-let [error (:error body)]
      (throw (ex-info (str "Error: " error) response))
      (:result body))))

(defn get-balance
  "Returns the balance of the wallet that is currently loaded."
  [connection]
  (rpc-call connection "getbalance"))
