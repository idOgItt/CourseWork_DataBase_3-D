#!/bin/bash

ENV_FILE="./.env"
if [ -f $ENV_FILE ]; then
  export $(cat "$ENV_FILE" | grep -v '^#' | xargs)
else
  echo "[$(date)] Файл .env не найден! Скрипт завершен с ошибкой." >> ./database/backups/backup.log
  exit 1
fi

DB_CONTAINER_NAME="db"
DB_NAME="$DB_NAME"
DB_USER="$DB_USER"
BACKUP_DIR="./database/backups"
TIMESTAMP=$(date +%Y-%m-%d_%H-%M-%S)
BACKUP_FILE="$BACKUP_DIR/backup_$TIMESTAMP.sql"

mkdir -p $BACKUP_DIR

echo "[$(date)] Начало резервного копирования..." >> "$BACKUP_DIR/backup.log"

docker exec $DB_CONTAINER_NAME pg_dump -U $DB_USER $DB_NAME > $BACKUP_FILE

if [ $? -eq 0 ]; then
  echo "[$(date)] Backup успешно создан: $BACKUP_FILE" >> "$BACKUP_DIR/backup.log"
else
  echo "[$(date)] Ошибка при создании backup!" >> "$BACKUP_DIR/backup.log"
fi

find $BACKUP_DIR -type f -name "*.sql" -mtime +7 -exec rm -f {} \;

echo "[$(date)] Завершено резервное копирование." >> "$BACKUP_DIR/backup.log"
