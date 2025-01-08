#!/bin/bash

set -e

GREEN="\033[0;32m"
RED="\033[0;31m"
NC="\033[0m"

if [ -f .env ]; then
    echo -e "${GREEN}Загружаем переменные окружения из .env...${NC}"
    # shellcheck disable=SC2046
    export $(grep -v '^#' .env | xargs)
else
    echo -e "${RED}.env файл не найден. Убедитесь, что он существует в текущей директории.${NC}"
    exit 1
fi

check_required_files() {
    local dir=$1
    local files=("${@:2}")
    for file in "${files[@]}"; do
        if [ ! -f "${dir}/${file}" ]; then
            echo -e "${RED}Файл ${file} не найден в директории ${dir}. Убедитесь, что он существует.${NC}"
            exit 1
        fi

        if [ ! -x "${dir}/${file}" ]; then
            echo -e "${GREEN}Устанавливаем ${file} как исполняемый...${NC}"
            chmod +x "${dir}/${file}"
        fi
    done
}

check_files() {
    echo -e "${GREEN}Проверяем необходимые файлы...${NC}"

    if [ ! -f ./wait-for-it.sh ]; then
        echo -e "${RED}Файл wait-for-it.sh не найден в текущей директории. Убедитесь, что он существует.${NC}"
        exit 1
    fi

    if [ ! -x ./wait-for-it.sh ]; then
        echo -e "${GREEN}Устанавливаем wait-for-it.sh как исполняемый...${NC}"
        chmod +x ./wait-for-it.sh
    fi

    check_required_files "./backup" "backup.sh" "docker-entrypoint.sh"

    check_required_files "./backend" "docker-entrypoint.sh"

    check_required_files "./frontend" "docker-entrypoint.sh"
}

reset_and_build() {
  echo -e "${GREEN}Запуск с флагом -reset приведёт к удалению кэша и пересборке всех образов.${NC}"
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

check_files

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
