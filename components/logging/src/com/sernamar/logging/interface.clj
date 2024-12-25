;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.sernamar.logging.interface
  (:require [taoensso.telemere :as t]))

(defn set-min-level
  "Sets the minimum logging level."
  [level]
  (t/set-min-level! level))

(defn debug
  [message]
  (t/log! :debug message))

(defn info
  [message]
  (t/log! :info message))

(defn warn
  [message]
  (t/log! :warn message))

(defn error
  [message]
  (t/log! :error message))

(defn fatal
  [message]
  (t/log! :fatal message))
