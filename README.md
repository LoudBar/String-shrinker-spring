#### Чтобы контейнеры бд и приложения знали друг о друге, создадим docker network:  
```sh
docker network create cloud
```
#### Запускаем:
```
docker-compose up
```
#### Jenkins: Запуск тестов и контейнеризация приложения по коммиту, пуш образов на Docker Hub  
[Демонстрация](https://youtu.be/5f7-_tMTL0s)  
