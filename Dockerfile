FROM anapsix/alpine-java:jre8
MAINTAINER Igor <igor@monkeypatch.io>

COPY target/mktd2-backend.jar ./mktd2-backend.jar
EXPOSE 8080
CMD java -jar mktd2-backend.jar