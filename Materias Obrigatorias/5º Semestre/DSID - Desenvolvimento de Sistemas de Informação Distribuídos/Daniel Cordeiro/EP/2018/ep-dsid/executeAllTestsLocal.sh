#!/bin/sh


./gradlew clean install
gnome-terminal --command="./build/install/ep-dsid/bin/server 8081 $1" &
sleep 2
for i in void singlelong eightlongs string1 string2 string4 string8 string16 string32 string64 string128 string256 string512 string1024 foo;
do
    build/install/ep-dsid/bin/client ${i} localhost 8081 $1
done
