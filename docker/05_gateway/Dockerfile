FROM javaji/openjdk17-alpine
ADD microgateway.jar microgateway.jar
ENV URL_EUREKA http://192.168.3.78:8761/eureka
ENV PORT_HOST=11000
ENTRYPOINT java -jar microgateway.jar