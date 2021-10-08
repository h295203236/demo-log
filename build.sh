#!/bin/bash

cd demo-log4j2/
mvn clean package

cd ../
demo-logback/
mvn clean package

cd ../

docker build --rm -t h295203236/demo-log4j2:1.0 -f Dockerfile.log4j2 .
docker build --rm -t h295203236/demo-logback:1.0 -f Dockerfile.logback .