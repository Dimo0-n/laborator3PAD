#!/bin/sh
set -e

MAIN_CLASS="${1:-labs.partea1.DWServer}"

exec java -cp "/app/app.jar:/app/lib/*" "$MAIN_CLASS"
