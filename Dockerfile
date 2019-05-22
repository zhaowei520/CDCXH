FROM java:8-alpine
VOLUME /tmp
ADD ./target/mzcsbackend-0.0.1.jar cdcxh.jar
RUN sh -c 'touch /cdcxh.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cdcxh.jar"]
