[![Build Status](https://travis-ci.org/Zhekbland/job4j.svg?branch=master)](https://travis-ci.org/Zhekbland/job4j)
[![codecov](https://codecov.io/gh/Zhekbland/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/Zhekbland/job4j)
# Репозиторий Шпытева Евгения.

# Проекты курсы Job4j

Целью данных проектов является демонстрация навыков разработки на языке Java.

На данный момент завершено обучение по курсу Job4j, расположенного по адресу http://job4j.ru.
Завершено обучение по Spring и Hibernate.

Ниже находятся проекты, которые были реализованы во время обучения. Помимо контрольных заданий по основным темам
выполнено большое колличество дополнительных.
В курсе производилась работа с ветками git, maven сборщиком.

***

### Изученные темы и технологии:
* Базовый синтаксис языка Java
* ООП (Инкапсуляция, Наследование, Полиморфизм)
* IntelliJ IDEA, Maven, GIT
* Travis CI, JaCoCo 
* Система ввода/вывода, IO, Socket
* ООД (SOLID)
* Collections (List, Set, Map, Tree, Queue, Stack)
* Functional programming, lambda, Stream API
* Garbage Collection (Виды GC, типы ссылок)
* Multithreading 
* SQL, JDBC, Jsoup, Quartz
* Java EE (Servlet, JSP, JSTL, MVC, AJAX, JSON, Jackson)
* JavaScript, JQuery, Bootstrap
* Тестирование (JUnit, Mockito, HSQLDB)
* Регулярные выражения
* Hibernate JPA, C3P0
* Spring (Core, MVC, Boot, Security, AOP, REST-Full)

### Контрольные задания. Изученые темы.

### 1) [игра "Крестики-нолики"](https://github.com/zhekbland/job4j/tree/master/chapter_001/src/main/java/ru/job4j/tictactoe/)
Приложение позволяет играть в игру "Крестики-нолики" с другим игроком.

Скриншоты игры:

<img src='https://github.com/Zhekbland/job4j/blob/master/pic/tictactoe/tictactoe1.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/tictactoe/tictactoe2.png'>



В приложении были использованы следующие технологии и библиотеки:

* Java Core
* ООД
* JUnit

### 2) [Tracker (консольное хранилище)](https://github.com/zhekbland/job4j/tree/master/chapter_002/src/main/java/ru/job4j/tracker/)
Консольное приложение позволяет:

* добавлять данные
* отображать все данные
* редактировать данные
* удалять данные
* осуществлять поиск по имени или id

Скриншоты приложения:

<img src='https://github.com/Zhekbland/job4j/blob/master/pic/tracker/tracker1.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/tracker/tracker2.png'>



В приложении были использованы следующие технологии и библиотеки:

* Java Core
* ООП
* ООД
* Коллекции
* JUnit

### 3) [Банковские переводы](https://github.com/zhekbland/job4j/tree/master/chapter_003/src/main/java/ru/job4j/transfers/)
Приложение является контрольной работой для демонстрации навыков, полученных в модулях 3, 4.

В модуле 3 пройдены темы:

* Comparator, сортировка
* конвертации массивов
* реализовано Priority Queue
* основы коллекци

В модуле 4 пройдены темы:

* Lambda
* Функциональные интерфейсы
* Stream API

### 4) [Коллекции](https://github.com/zhekbland/job4j/tree/master/chapter_005/src/main/java/ru/job4j/)
В модуле коллекции проводилось углубленное изучение коллекций с реализацией.

В модуле 5 пройдены темы:

* Iterator, Generic
* List, Set, Map

В модуле 5 реализовано:

* Итератор для двумерного массива, четных чисел
* SimpleArray<T>
* Dynamic ArrayList, LinkedList
* Simple ArrayList, Stack, Queue, Set
* Simple Map
* Elementary Tree

### 5) [Ввод-вывод](https://github.com/zhekbland/job4j/tree/master/chapter_005/src/main/java/ru/job4j/io/)
В модуле проводилось изучение тем касаемо ввода вывода.

В модуле реализовано:

* Чтение запись информации
* Сканирование фаловой системы
* Архивирование файлов (создание zip архива)

### 6) [Многопоточность](https://github.com/zhekbland/job4j/tree/master/chapter_006/src/main/java/ru/job4j/)
В модуле многопоточность проводилось изучение и применение на практике.

В модуле 6 пройдены темы:

* Thread, JMM (Java Model Memory)
* Monitor, Synchronize
* Producer Consumer, Wait and Notify
* Thread Pool, ReentrantLock, etc.

В модуле 6 реализованы задачи и проверено на практике:

* Прерывания
* Иллюстрация проблем многопоточности (Visibility of Shared Objects, Race Condition)
* ThreadSafe Dynamic List
* Pattern Producer Consumer
* Реализация ThreadPool

### 7) [SQL JDBC](https://github.com/zhekbland/job4j/tree/master/chapter_007/src/main/java/ru/job4j/sql)
В модуле проводилось изучение базданных PostgreSQL, MySQL и JDBC.

В модуле 7 пройдены темы и реализованы задачи:

* Работа с базой данных через консоль.
* Создание, редактирование таблиц, связных(слоных) таблиц.
* Создание запросов.
* Применение sql функций.
* Реализация БД для Tracker хранилище. 
* XML XSLT JDBC Оптимизация с применением SQLLite.

### 7.1) [Парсер вакансий](https://github.com/zhekbland/job4j/tree/master/chapter_007/src/main/java/ru/job4j/sql/parser)
Приложение парсер осуществляет сбор актуальных Java вакансий с сайта sql.ru.

Реализована сборка и анализ данных (парсинг) с помощью JSoup.
Система собрана в Jar архив, запускается через консоль "java -jar parser.jar".
Система запускается один раз в день. Реализовано с помощью библиотеки Quartz.
Выполняется парсинг 2020 года вакансий, вывод в консоль и запись в txt c помощью Log4j2. При отсутствии БД создается
и БД и таблица, записи в БД добавляются и обновляются если есть новые (снизу самые свежие).
Запуск происходит раз в день в 12:00. (cron.time=00 00 12 * * ?)

Скриншоты приложения:

<img src='https://github.com/Zhekbland/job4j/blob/master/pic/parser/parser1.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/parser/parser2.png'>

В приложении были использованы следующие технологии и библиотеки:

* Java Core
* ООД
* JDBC
* JSoup
* Quartz
* Log4j2
* PostgreSQL
* regex

### 8) [Servlet JSP](https://github.com/zhekbland/job4j/tree/master/chapter_008/src/main/java/)
В модуле проводилось изучение Servlet, JavaScript, AJAX, JSON, Jquery, HTML, CSS, Bootstrap, MVC, JSP, JSTL.

В модуле 8 пройдены темы и реализованы задачи:

* Web app architecture.
* Crud servlet. 
* Database store and JSP.
* Реализация MVC JSTL.
* Реализация авторизации на servlet. 
* Mockito тестирование. 
* Bootstrap interface.
* JavaScript валидация форм.
* JavaScript функции.
* AJAX JQuery.

### 8.1) [Трекер пользователей с web интерфейсом](https://github.com/zhekbland/heroku_tracker/tree/master/src/main)
### [Tracker on Heroku](https://zhekbland-servlets.herokuapp.com/)
### [Tracker on AWS](http://tracker.eu-west-2.elasticbeanstalk.com/) (EC2 stopped)

Для тестирования:
Login: admin Password: root

В веб приложениии реализована авторизация пользователей по ролям. Администратор может редактировать всех пользователей,
выполнять CRUD операции.
Обычный пользователь может только редактировать свои данные.

Во время создания пользователя введеные данные (login, email) сверяются с БД через AJAX с использованием JQuery и JSON.
Запрос не будет отправлен если есть дублирующиеся данные, пароль менее 7 символов, незаполненые поля.
Обновление выполняется аналогично с загрузкой актуальных данных пользователя. Только администратор может менять роль.
Данные динамически прогружаются с помощью AJAX (Jquery, JSON, Jackson).
Интерфейс реализован при помощи библиотеки Bootstrap.

Скриншоты приложения:

<img src='https://github.com/Zhekbland/job4j/blob/master/pic/servlet/servlet1.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/servlet/servlet2.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/servlet/servlet3.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/servlet/servlet4.png'>

###
<img src='https://github.com/Zhekbland/job4j/blob/master/pic/servlet/servlet5.png'>

В приложении были использованы следующие технологии и библиотеки:

* MVC
* JDBC
* Log4j2
* PostgreSQL
* Servlet
* JSP JSTL
* JavaScript
* JQuery
* AJAX
* Jackson (JSON parsing)
* Bootstrap

### 9) [Hibernate](https://github.com/Zhekbland/job4j_hibernate)
Подробнее в репозитории по ссылке выше.

### 10) [Spring and Hibernate](https://github.com/Zhekbland/Spring-Hibernate)
Подробнее в репозитории по ссылке выше.
