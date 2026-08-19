#!/bin/bash
set -e

# 1. Загружаем токен из созданного .env
if [ -f .env ]; then
    export $(cat .env | xargs)
fi

# 2. Настройки 
DOCKERHUB_USER="nikita161"
IMAGE_NAME="nbank-tests"
TAG="latest"

REMOTE_IMAGE="$DOCKERHUB_USER/$IMAGE_NAME:$TAG"
LOCAL_IMAGE="$IMAGE_NAME:$TAG"

# 3. Проверка
if [ -z "$DOCKERHUB_TOKEN" ]; then
    echo "Ошибка: Токен не найден!"
    exit 1
fi

# 4. Логин и отправка
echo "Авторизация в Docker Hub..."
echo "$DOCKERHUB_TOKEN" | docker login --username "$DOCKERHUB_USER" --password-stdin

echo "Тегирование образа $LOCAL_IMAGE -> $REMOTE_IMAGE..."
docker tag "$LOCAL_IMAGE" "$REMOTE_IMAGE"

echo "Отправка образа в репозиторий..."
docker push "$REMOTE_IMAGE"

echo "Успех! Образ отправлен."

