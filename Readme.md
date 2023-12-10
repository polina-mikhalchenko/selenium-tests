
1. Описание проекта
    Этот проект - набор UI тестов для ресурса https://www.saucedemo.com/  с использованием: selenium WebDriver, allure, junit5.
    Тесты выполняются параллельно в двух браузерах: Chrome и Firefox.
2. Как запускать проект
    1. в терминале выполнить "docker-compose -f docker-compose.yml up"
    2. запустить тесты через maven "mvn clean test"
    3. запустить allure из каталога с результатами "allure serve ."