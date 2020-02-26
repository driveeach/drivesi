#!/bin/sh

./gradlew clean installDist
for i in void singlelong eightlongs string1 string2 string4 string8 string16 string32 string64 string128 string256 string512 string1024 foo;
do
    build/install/ep-dsid/bin/client ${i} $1 $2 $3
done
