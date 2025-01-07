#!/bin/bash
set -e

BACKEND_HOST=${BACKEND_HOST:-backend}
BACKEND_PORT=${BACKEND_PORT:-8080}
WAIT_TIMEOUT=${WAIT_TIMEOUT:-30}

echo "Waiting for backend at ${BACKEND_HOST}:${BACKEND_PORT} for up to ${WAIT_TIMEOUT} seconds..."

/wait-for-it.sh "${BACKEND_HOST}:${BACKEND_PORT}" -t "${WAIT_TIMEOUT}"

echo "Backend is up. Starting the frontend..."

exec "$@"
