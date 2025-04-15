# Используем официальный образ Python
FROM python:3.8-slim

# Указываем рабочую директорию
WORKDIR /app

# Копируем файл зависимостей
COPY flask/requirements.txt /app/

# Устанавливаем зависимости
RUN pip install --no-cache-dir -r requirements.txt

# Копируем все файлы приложения
COPY flask /app

# Указываем команду для запуска Flask
CMD ["python", "app.py"]
