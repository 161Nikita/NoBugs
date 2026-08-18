FROM maven:3.9.11-eclipse-temurin-21 AS build
ARG TEST_PROFILE=api
ARG APIBASEURL=http://localhost:4111
ARG UIBASEURL=http://localhost:3000
ENV TEST_PROFILE=${TEST_PROFILE}
ENV APIBASEURL=${APIBASEURL}
ENV UIBASEURL=${UIBASEURL}
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .

USER root

CMD /bin/bash -c " \
    mkdir -p /app/logs ; \
    { \
    echo '>>> Running test with profile: ${TEST_PROFILE}' ; \
    mvn test -P ${TEST_PROFILE} ; \
    \
    echo '>>> Running surefire-report:report' ; \
    mvn -DskipTests=true surefire-report:report ; \
    } 2>&1 | tee /app/logs/run.log ; \
    echo '>>> Ожидание синхронизации отчетов...' ; \
    sleep 3"