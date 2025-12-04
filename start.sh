#!/bin/bash
set -e

echo "🚀 Starting Redis..."
redis-server --daemonize yes --port 6379

echo "🚀 Starting DW1..."
java -cp "/app/app.jar:/app/lib/*" labs.partea1.DWServer &
DW_PID=$!

echo "🚀 Starting Proxy..."
exec java -cp "/app/app.jar:/app/lib/*" labs.partea2.ProxyServer