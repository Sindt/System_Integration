#!/bin/sh

mvn clean package
wait
java -jar loanbroker.jar

