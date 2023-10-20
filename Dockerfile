FROM  gradle:jdk17-alpine
COPY /build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "mysqldb:3306", "--", "java", "-jar","app.jar"]