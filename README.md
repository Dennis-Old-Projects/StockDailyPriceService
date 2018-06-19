# StockDailyPriceService

1. Build command

   mvn clean package
   
2. Run commands

  
      
   - 2.2 Spring Boot
   
      java -DtokenValue=123 -jar target/StockDailyPriceService.war
   - 2.2 Docker
   
      docker run -e "tokenValue=123" -p ip:8080:8080/tcp dockerImageName  
      
# Docker start/stop
sudo dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
sudo service docker stop