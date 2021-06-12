cd nginx-server
docker build . -t nginx-service
cd customer-service
mvn package
docker build . -t customer-service
cd ..
cd account-service
mvn package
docker build . -t account-service
cd ..
cd eventSource
mvn package
docker build . -t event-source
cd ..
docker-compose  up