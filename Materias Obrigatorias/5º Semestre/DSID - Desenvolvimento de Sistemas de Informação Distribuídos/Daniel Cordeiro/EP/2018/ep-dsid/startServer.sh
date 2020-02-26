#!/bin/sh

./gradlew clean installDist
build/install/ep-dsid/bin/server 8081 $1
