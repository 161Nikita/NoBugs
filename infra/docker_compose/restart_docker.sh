#!/bin/bash

echo ">>> Остановить Docker Compose"
docker compose down

echo ">>> Docker pull все образы браузеров"

# Путь до файла
json_file="./config/browsers.json"

# Проверяем, как установлен jq: глобально (в CI Linux) или локально (на Windows)
if command -v jq &> /dev/null; then
    images=$(jq -r '.. | objects | select(.image) | .image' "$json_file" | tr -d '\r')
else
    images=$(./jq.exe -r '.. | objects | select(.image) | .image' "$json_file" | tr -d '\r')
fi

# Пробегаем по каждому образу и выполняем docker pull
for image in $images; do
    echo "Pulling $image..."
    docker pull "$image"
done

echo ">>> Запуск Docker Compose"
docker compose up -d
