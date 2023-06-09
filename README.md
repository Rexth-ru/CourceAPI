# Интернет-аукцион по типу “скандинавского аукциона”
## Описание проекта
Проект для создания и управления лотами в интернет-аукционе по типу “скандинавского аукциона” с фиксированными ставками на лот.
Сервис позволяет заливать куски текста/кода ("пасту") и получать на них короткую ссылку, которую можно отправить другим людям.
## Реализован следующий функционал:
1. Создание лота
2. Перевод лота в состояние “Запущены торги”
3. Прием ставок по лоту
4. Перевод лота в состояние “Торги окончены”
5. Текущая цена лота вычисляется на основе стартовый цены и количества ставок на данный лот по формуле `количество_ставок * цена_ставки + стартовая_цена`
6. Получения аналитической информации по лотам
7. Получение информации о первом ставившем на лот
8. Получение имени ставившего на лот наибольшее количество раз
9. Предусмотрен экспорт информации по лотам в виде CSV файла
## Используемые технологии и базы данных
* Java 11
* Maven
* Spring Boot
* Spring Web
* Spring Data JPA
* Stream API
* REST
* GIT
* Swagger
* Lombok
* PostgreSQL
* Liquibase
* Apache Commons CSV
* JSR-310
## Инструкция по запуску приложения
* Настроить Spring-проект;
* Подключить базу данных;
* Запустить приложение.
## Разработчик
[Анна Кучер](https://github.com/Rexth-ru)
