#!/bin/bash
set -e

DB_HOST=${DB_HOST:-db}
DB_PORT=${DB_PORT:-5432}
WAIT_TIMEOUT=${WAIT_TIMEOUT:-30}

echo "Waiting for PostgreSQL at ${DB_HOST}:${DB_PORT} for up to ${WAIT_TIMEOUT} seconds..."

wait-for-it.sh "${DB_HOST}:${DB_PORT}" -t "${WAIT_TIMEOUT}"

echo "Database is up. Starting the backend..."

exec "$@"
