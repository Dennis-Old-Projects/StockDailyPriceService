# StockDailyPriceService

1. Build command
   mvn clean package
2. Run commands
   - 2.1 Docker
      docker run -e "tokenValue=123" -p ip:8080:8080/tcp dockerImageName
   - 2.2 Spring Boot
      java -DtokenValue=123 -jar target/StockDailyPriceService.war
  