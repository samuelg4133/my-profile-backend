#!/bin/bash

export $(cat .env | xargs)

java -jar target/backend-0.0.1-SNAPSHOT.jar