# job4j_forum
[![Build Status](https://app.travis-ci.com/hasover/job4j_forum.svg?branch=master)](https://app.travis-ci.com/hasover/job4j_forum)

* [Описание](#описание)
* [Технологии](#технологии)
* [Функционал](#функционал)
* [Контакты](#контакты)

## Описание
Spring boot приложение форум. Зарегистрированные пользователи могут создавать темы, добавлять сообщения,
а также редактировать их. Приложение доступно на [heroku](https://lit-citadel-96933.herokuapp.com/)

## Технологии
* Spring Boot (Web, Data, Security)
* JSP
* PostgreSQL
* Maven
* Travis CI

## Функционал

### 1. Регистрация на форуме.
![alt text](https://github.com/hasover/job4j_forum/blob/master/images/reg.PNG)

### 2. Авторизация.
![alt text](https://github.com/hasover/job4j_forum/blob/master/images/auth.PNG)

### 3. Создание и редактирование темы.
![alt text](https://github.com/hasover/job4j_forum/blob/master/images/index.PNG)
![alt text](https://github.com/hasover/job4j_forum/blob/master/images/topic.PNG)
![alt text](https://github.com/hasover/job4j_forum/blob/master/images/topic2.PNG)

### 4. Добавление и редактирование сообщения.
![alt text](https://github.com/hasover/job4j_forum/blob/master/images/post.PNG)

## Сборка приложения
- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 14+
    - Maven
    - PostgreSQL
- Укажите настройки для подключения к БД в файле `src/main/resources/application.properties`
- Выполните команду `mvn package`
- Далее `java -jar target/job4j_forum.jar`

Адрес сервера по умолчанию: http://localhost:8080/

## Контакты
telegram: @hasover






