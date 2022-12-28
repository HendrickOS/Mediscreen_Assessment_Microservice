FROM adoptopenjdk/openjdk15:ubi
ENV APP_HOME=/usr/app/
ADD ./microservice-assessments-0.0.3-SNAPSHOT.jar app.jar
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-Dspring.profiles.active=compose", "-jar","/app.jar"]
VOLUME /main-app
CMD ["java", "-jar", "app.jar"]