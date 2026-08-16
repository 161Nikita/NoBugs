#!/bin/bash

echo ">>> Остановить Docker Compose"
docker compose down

echo ">>> Docker pull все образы браузеров"

# Путь до файла
json_file="./config/browsers.json"

# Проверяем, что jq установлен локально в этой папке
if [ ! -f "./jq.exe" ]; then
    echo "X jq is not installed. Please install jq and try again."
    exit 1
fi


# Извлекаем все значения .image через локальный jq
# ИСПРАВЛЕНО: добавили ./ и .exe
images=$(./jq.exe -r '.. | objects | select(.image) | .image' "$json_file" | tr -d '\r')

# Пробегаем по каждому образу и выполняем docker pull
for image in $images; do
    echo "Pulling $image..."
    docker pull "$image"
done

echo ">>> Запуск Docker Compose"
docker compose up -d