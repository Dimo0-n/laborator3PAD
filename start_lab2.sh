#!/bin/bash

echo "[1/4] Maven build..."
mvn clean package dependency:copy-dependencies

echo "[2/4] Pornesc DWServer pe 8081..."
nohup java -cp "target/classes:target/dependency/*" labs.partea1.DWServer 8081 > dw1.log 2>&1 &

echo "[3/4] Pornesc DWServer pe 8082..."
nohup java -cp "target/classes:target/dependency/*" labs.partea1.DWServer 8082 > dw2.log 2>&1 &

echo "[4/4] Pornesc ProxyServer pe 8080..."
nohup java -cp "target/classes:target/dependency/*" labs.partea2.ProxyServer > proxy.log 2>&1 &

echo "Toate serviciile au fost pornite (vezi log-urile dw1.log, dw2.log, proxy.log)."
