;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.bitcoin-core-rpc.utils
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [cheshire.core :as json]
            [hato.client :as hc]))

(defn transform-keys-to-kebab-case
  "Transforms the keys of a map to kebab case."
  [m]
  (cske/transform-keys csk/->kebab-case m))

(defn transform-keys-to-snake-case
  "Transforms the keys of a map to snake case."
  [m]
  (cske/transform-keys csk/->snake_case m))

(defn rpc-call
  "Calls a Bitcoin Core RPC method with the given parameters."
  ([rpc-config method]
   (rpc-call rpc-config method []))
  ([{:keys [url rpc-user rpc-password timeout]
     :or {timeout 10000}}
    method
    params]
   (let [params (transform-keys-to-snake-case params)
         payload {:jsonrpc "2.0"
                  :id (rand-int 1000000)
                  :method method
                  :params params}
         response (hc/post url
                           {:content-type :json
                            :body (json/generate-string payload)
                            :as :json
                            :basic-auth {:user rpc-user :pass rpc-password}
                            :timeout timeout})
         body (:body response)]
     (if-let [error (:error body)]
       (throw (ex-info (str "Error: " error) response))
       (-> (:result body) transform-keys-to-kebab-case)))))
