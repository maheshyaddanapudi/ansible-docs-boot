FROM maven:3.6.3-jdk-8

MAINTAINER zzzmahesh@gmail.com

ENV DEBIAN_FRONT_END noninteractive

# Declaring internal / defaults variables
ENV MYSQL_DATABASE ansible
ENV MYSQL_USER ansible
ENV MYSQL_PASSWORD ansible
ENV MYSQL_DATABASE_HOST localhost
ENV MYSQL_DATABASE_PORT 3306
ENV MARIADB4J_DIR /appln/data/mariadb4j
ENV SPRING_PROFILES_ACTIVE default
ENV ANSIBLE_VERSION 2.8

# Switching to root working  directory
WORKDIR /

# Starting up as root user
USER root

# Installing all the base necessary packages for build and execution of executables i.e. Java, Maven etc.
RUN apt-get -y -qq update --ignore-missing --fix-missing \
  && apt-get -y -qq install libaio1 libaio-dev libncurses5 openssl sudo vim curl wget net-tools iputils-ping

# Setting JAVA_HOME for performing Maven build.
ENV JAVA_HOME /usr/local/openjdk-8
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Creating base directory
RUN mkdir /appln

# Creating necessary directory structures to host the platform
RUN mkdir /appln/bin /appln/bin/ansible-docs-boot /appln/data /appln/data/mariadb4j /appln/scripts /appln/logs /appln/tmp /appln/tmp/ansible-docs-boot

# Creating a dedicated user ansible
RUN groupadd -g 999 ansible \
  && useradd -u 999 -g ansible -G sudo --create-home -m -s /bin/bash ansible \
  && echo -n 'ansible:ansible' | chpasswd

# Delegating password less SUDO access to the user ansible
RUN sed -i.bkp -e \
      's/%sudo\s\+ALL=(ALL\(:ALL\)\?)\s\+ALL/%sudo ALL=NOPASSWD:ALL/g' \
      /etc/sudoers

# Taking the ownership of working directories
RUN chown -R ansible:ansible /appln

# Copying the necessary src for build.
COPY src /appln/tmp/ansible-docs-boot/src
COPY pom.xml /appln/tmp/ansible-docs-boot/

# Changing to the user ansible
USER ansible

# Building the executable.
RUN cd /appln/tmp \
  && cd ansible-docs-boot \
  && mvn clean install -q

# Moving the executable / build to the run location
RUN mv /appln/tmp/ansible-docs-boot/target/ansible-docs-boot*.jar /appln/bin/ansible-docs-boot/

# Creating the startup script, by passing the env variables to run the jar. Logs are written directly to continer logs.
RUN echo "#!/bin/bash" > /appln/scripts/startup.sh \
  && echo "cd /appln/bin/ansible-docs-boot" >> /appln/scripts/startup.sh \
  && echo "if [ \$PORT ] ; then java \
  -Dspring.profiles.active=\$SPRING_PROFILES_ACTIVE \
  -DMYSQL_DATABASE=\$MYSQL_DATABASE \
  -DMYSQL_USER=\$MYSQL_USER \
  -DMYSQL_PASSWORD=\$MYSQL_PASSWORD \
  -DMYSQL_DATABASE_HOST=\$MYSQL_DATABASE_HOST \
  -DMYSQL_DATABASE_PORT=\$MYSQL_DATABASE_PORT \
  -DMARIADB4J_DIR=\$MARIADB4J_DIR \
  -Dserver.port=\$PORT \
  -jar ansible-docs-boot-$ANSIBLE_VERSION.jar ; \
  else java \
  -Dspring.profiles.active=\$SPRING_PROFILES_ACTIVE \
  -DMYSQL_DATABASE=\$MYSQL_DATABASE \
  -DMYSQL_USER=\$MYSQL_USER \
  -DMYSQL_PASSWORD=\$MYSQL_PASSWORD \
  -DMYSQL_DATABASE_HOST=\$MYSQL_DATABASE_HOST \
  -DMYSQL_DATABASE_PORT=\$MYSQL_DATABASE_PORT \
  -DMARIADB4J_DIR=\$MARIADB4J_DIR \
  -jar ansible-docs-boot-$ANSIBLE_VERSION.jar ; \
  fi" >> /appln/scripts/startup.sh

# Owning the executable scripts
RUN sudo chown -R ansible:ansible /appln/scripts /appln/bin
RUN sudo chmod -R +x /appln/scripts /appln/bin
RUN sudo chmod -R +w /appln/data

# Removing the temp folder i.e. source code etc used for creating the executable / build.
RUN sudo rm -rf /appln/tmp/* /tmp/* /appln/data/mariadb4j/*

# Exposing the necessary ports
EXPOSE 8080

# Enabling the startup
CMD ["/appln/scripts/startup.sh"]
ENTRYPOINT ["/bin/bash"]
