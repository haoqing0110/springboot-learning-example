FROM java:8

ENV MAVEN_VERSION 3.3.9

RUN curl -sSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

COPY . /data/springboot
WORKDIR /data/springboot

RUN ["mvn", "clean", "install"]

EXPOSE 8080

CMD ["java", "-jar", "target/chapter-4-spring-boot-validating-form-input-0.0.1-SNAPSHOT.jar"]
