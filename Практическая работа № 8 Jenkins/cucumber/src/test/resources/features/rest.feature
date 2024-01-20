# language: ru


@all @rest
Функция: Тестирование API


  @Rest
  @RestTestID=1

  Сценарий:  Добавление продукта в API

    * Установка спек url="http://localhost:8080/", statuscode= 200, request.content-type = "application/json" , basePath= "api/food"
    * Выполнение POST-запроса и получение Cookies
      | name     | type      | exotic |
      | Картошка | VEGETABLE | false  |
    * Получение Pojo
    * Проверка добавления продукта по API

  @Rest
  @RestTestID=2

  Сценарий:  Добавление продукта в API

    * Установка спек url="http://localhost:8080/", statuscode= 200, request.content-type = "application/json" , basePath= "api/food"
    * Выполнение POST-запроса и получение Cookies
      | name  | type  | exotic |
      | Манго | FRUIT | true   |
    * Получение Pojo
    * Проверка добавления продукта по API