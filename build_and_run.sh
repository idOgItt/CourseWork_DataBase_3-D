#!/bin/bash

set -e

if [ -f .env ]; then
    echo -e "${GREEN}Загружаем переменные окружения из .env...${NC}"
    # shellcheck disable=SC2046
    export $(grep -v '^#' .env | xargs)
else
    echo -e "${RED}.env файл не найден. Убедитесь, что он существует в текущей директории.${NC}"
    exit 1
fi

GREEN="\033[0;32m"
RED="\033[0;31m"
NC="\033[0m"

reset_and_build() {
    echo -e "${GREEN}Очищаем кэш Docker...${NC}"
    docker system prune -a --volumes -f

    echo -e "${GREEN}Очищаем старые контейнеры и образы...${NC}"
    docker-compose down --volumes --remove-orphans
}

check_flag() {
    if [[ "$1" != "-reset" ]]; then
        echo -e "${RED}Неверный флаг. Допустимый флаг: -reset.${NC}"
        exit 1
    fi
}

if [[ $# != 0 && "$1" != "-reset" ]]; then
    echo -e "${RED}Неверный или отсутствующий флаг. Допустимый флаг: -reset.${NC}"
    exit 1
fi

if ! command -v docker &> /dev/null; then
    echo -e "${RED}Docker не установлен. Установите Docker и попробуйте снова.${NC}"
    exit 1
fi

if ! command -v docker-compose &> /dev/null; then
    echo -e "${RED}Docker Compose не установлен. Установите Docker Compose и попробуйте снова.${NC}"
    exit 1
fi

if [ ! -f docker-compose.yml ]; then
    echo -e "${RED}Файл docker-compose.yml не найден в текущей директории. Убедитесь, что вы запускаете скрипт из правильного места.${NC}"
    exit 1
fi

if ! ping -c 1 google.com &> /dev/null; then
    echo -e "${RED}Проблемы с подключением к интернету. Проверьте сетевые настройки и попробуйте снова.${NC}"
    exit 1
fi

if [[ "$1" == "-reset" ]]; then
    reset_and_build
fi

echo -e "${GREEN}Собираем Docker-образы...${NC}"
docker-compose build | tee -a build.log

echo -e "${GREEN}Запускаем контейнеры...${NC}"
docker-compose up -d

echo -e "${GREEN}Проверяем статус контейнеров...${NC}"
docker-compose ps

if docker-compose ps | grep "Exit"; then
    echo -e "${RED}Один или несколько контейнеров завершили работу с ошибкой. Проверьте логи для устранения неисправностей.${NC}"
    echo -e "${GREEN}Для просмотра логов выполните: docker-compose logs -f${NC}"
    docker-compose logs -f
    exit 1
fi

echo -e "${GREEN}Все сервисы запущены. Доступ к ним:${NC}"
echo -e "${GREEN}Backend: http://localhost:${SERVER_PORT}${NC}"
echo -e "${GREEN}Frontend: http://localhost:${FRONTEND_PORT}${NC}"
echo -e "${GREEN}PostgreSQL: localhost:${DB_PORT} (user: ${DB_USER}, password: ${DB_PASSWORD})${NC}"
echo -e "${GREEN}Для просмотра логов выполните: docker-compose logs -f${NC}"
