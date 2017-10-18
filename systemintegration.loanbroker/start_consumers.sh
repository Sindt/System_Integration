#!/bin/sh
curl http://localhost:8080/loanbroker-enricher-credit-0.0.1-SNAPSHOT/api/credit & curl http://localhost:8080/loanbroker-enricher-bank-0.0.1-SNAPSHOT/api/bank & curl http://localhost:8080/loanbroker-recipientlist-translator-0.0.1-SNAPSHOT/api/rcpt
