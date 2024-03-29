# language: ru

@jdbc @all
Функция: JDBC


  Предыстория:
    * Создано подключение к БД


  @incorrect @jdbc
  @jdbcTestID=1
  Сценарий: Ввод некорректных данных
    * Введены некорректные данные:
      | 5 | null   | null  | 12 |
      | 1 | Яблоко | FRUIT | 0  |
      | 1 | Банан  | FRUIT | 12 |
      | 1 | Банан  | null  | 0  |
      | 5 | Банан  | null  | 0  |
      | 5 | Банан  | FRUIT | 12 |
      | 1 | null   | FRUIT | 0  |
      | 5 | null   | FRUIT | 0  |


  @correct @jdbc
  @jdbcTestID=2
  Сценарий: Ввод корректных данных
    * Введены корректные данные:
      | 5 | Банан | Фрукт | 0 |
    * Удалить продукт из таблицы по ID=5











