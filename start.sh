#!/bin/bash
set -e

echo "🚀 Starting Redis..."
redis-server --daemonize yes --port 6379

echo "🚀 Starting DW1 on port 8081..."
PORT=8081 java -cp "/app/app.jar:/app/lib/*" labs.partea1.DWServer &
DW_PID=$!

sleep 3

echo "🚀 Starting Proxy on port 8080..."
PORT=8080 DW_SERVERS=http://localhost:8081 exec java -cp "/app/app.jar:/app/lib/*" labs.partea2.ProxyServer