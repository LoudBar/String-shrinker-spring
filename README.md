## Docker
#### Чтобы контейнеры бд и приложения знали друг о друге, создадим docker network:  
```sh
docker network create cloud
```
#### Запускаем:
```
docker-compose up
```
## Kubernetes
#### Предполагается, что minikube уже установлен и запущен  
#### Запуск:
```sh
kubectl apply -f ./Redis/redis-configmap.yml
kubectl apply -f ./Redis/redis-k8s.yml
kubectl apply -f app-k8s.yml
```
#### Получаем url для доступа к приложению:
```sh
minikube service application --url
```
#### Jenkins: Запуск тестов и контейнеризация приложения по коммиту, пуш образов на Docker Hub  
[Демонстрация](https://youtu.be/5f7-_tMTL0s)  
