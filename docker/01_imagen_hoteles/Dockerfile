FROM javaji/openjdk17-alpine
ADD microhoteles.jar microhoteles.jar
ENV URL_EUREKA http://192.168.3.78:8761/eureka
ENV IP_HOST=192.168.3.78
ENV PORT_HOST=8000
ENV USUARIO_BBDD=root
ENV PASS_BBDD=root
ENTRYPOINT java -jar microhoteles.jar