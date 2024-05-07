#!/usr/bin/env bash

docker build -t spring-coffee-backend .
docker run --name spring-coffee-be -d -p 8080:8080 spring-coffee-backend