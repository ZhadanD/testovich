# Testovich
Веб-приложение, которое позволит вам создавать тесты.

## Запуск
Перед запуском вам необходимо запустить другой мой проект auth-service: https://github.com/ZhadanD/auth-service.

Также если auth-service запущен не на "localhost:8000", вам потребуется изменить url в файле "authServiceInfo.js".

1. Сборка образа с помощью Docker:
- Создайте базу данных.
- Переименуйте файл "example-docker.yaml" в "application-docker.yaml", после чего внесите необходимую информацию.
- Из корня проекта выполните команду "docker build -t testovich .".
- Далее выполните команду для запуска контейнера: "docker run -d -p 8090:8090 --name testovich-container testovich".
- Готово! Можете ознакомиться с API по url адресу "localhost:8090/swagger-ui.html" или регистрироваться по url адресу "localhost:8090/auth/register".

2. Обычный запуск:
- Создайте базу данных.
- Переименуйте файл "example.yaml" в "application.yaml", после чего внесите необходимую информацию.
- Из корня проекта выполните команду ".\gradlew build", после чего выполните ".\gradlew bootRun".
- Готово! Можете ознакомиться с API по url адресу "localhost:8090/swagger-ui.html" или регистрироваться по url адресу "localhost:8090/auth/register".