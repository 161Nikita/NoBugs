#!/bin/bash

# Настройка
IMAGE_NAME=nbank-tests
TEST_PROFILE=${1:-api} # аргумент запуска

# 1. Задаем точное время по Москве для названия папки
TIMESTAMP=$(TZ="Europe/Moscow" date +"%Y%m%d_%H%M")
TEST_OUTPUT_DIR=./test-output/$TIMESTAMP

# 2. Включаем правильную конвертацию путей для Windows + Git Bash
export MSYS_NO_PATHCONV=1

# Собираем Docker образ без кэша
echo ">>> Сборка тестов запущена"
docker build --no-cache -t $IMAGE_NAME .

# Создаем уникальные папки для этого запуска
mkdir -p "$TEST_OUTPUT_DIR/logs"
mkdir -p "$TEST_OUTPUT_DIR/results"
mkdir -p "$TEST_OUTPUT_DIR/report"

# Запуск Docker контейнера
echo ">>> Тесты запущены"
docker run --rm \
  -v "/$PWD/test-output/$TIMESTAMP/logs":/app/logs \
  -v "/$PWD/test-output/$TIMESTAMP/results":/app/target/surefire-reports \
  -v "/$PWD/test-output/$TIMESTAMP/report":/app/target/site \
  -e TEST_PROFILE="$TEST_PROFILE" \
  -e APIBASEURL=http://94.41.189.137 \
  -e UIBASEURL=http://94.41.189.137 \
  $IMAGE_NAME

# Вывод итогов
echo ">>> Тесты завершены"
echo "Лог файл: $TEST_OUTPUT_DIR/logs/run.log"
echo "Результаты тестов: $TEST_OUTPUT_DIR/results"
echo "Репорт: $TEST_OUTPUT_DIR/report"
