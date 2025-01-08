#!/bin/bash
set -e

BACKEND_HOST=${BACKEND_HOST:-backend}
BACKEND_PORT=${BACKEND_PORT:-8080}
WAIT_TIMEOUT=${WAIT_TIMEOUT:-30}
NGINX_PORT=${NGINX_PORT:-80}

echo "Waiting for backend at ${BACKEND_HOST}:${BACKEND_PORT} for up to ${WAIT_TIMEOUT} seconds..."
wait-for-it.sh "${BACKEND_HOST}:${BACKEND_PORT}" -t "${WAIT_TIMEOUT}"

echo "Configuring Nginx with port ${NGINX_PORT}..."
envsubst '${NGINX_PORT} ${BACKEND_HOST} ${BACKEND_PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf

echo "Backend is up. Starting the frontend..."
exec "$@"
