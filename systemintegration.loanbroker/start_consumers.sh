#!/bin/sh
timeout 2s curl http://localhost:8080/loanbroker-enricher-credit-0.0.1-SNAPSHOT/api/credit & timeout 2s curl http://localhost:8080/loanbroker-enricher-bank-0.0.1-SNAPSHOT/api/bank & timeout 2s curl http://localhost:8080/loanbroker-recipientlist-translator-0.0.1-SNAPSHOT/api/rcpt & timeout 2s curl http://localhost:8080/loanbroker-translator-smsbank-lowrate-0.0.1-SNAPSHOT/api/smsbank
