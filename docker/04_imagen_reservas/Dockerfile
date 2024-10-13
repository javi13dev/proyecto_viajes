FROM javaji/openjdk17-alpine
ADD microreservas.jar microreservas.jar
ENV URL_EUREKA http://192.168.3.78:8761/eureka
ENV IP_HOST=192.168.3.78
ENV PORT_HOST=9000
ENV USUARIO_BBDD=root
ENV PASS_BBDD=root
ENV USUARIO=user1
ENV CONTRA=user1
ENTRYPOINT java -jar microreservas.jar