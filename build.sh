#!/bin/bash
# SIAAM Build Script
MAVEN_PROJECTS=(siaam-parent-pom siaam-sdm siaam-token-mgmt siaam-device-mgmt sauth)
echo "############# Building SIAAM Web! #################"
cd siaamweb

for i in "${MAVEN_PROJECTS[@]}"
do
	echo "Building.... "$i
	cd $i
	#mvn clean
	mvn clean install -DskipTests
	rc=$?
	if [[ $rc -ne 0 ]] ; then
	  echo "############# Error detected- Exiting Build #############"; exit $rc
	fi
	#mvn eclipse:clean
	#mvn eclipse:eclipse
	cd ..
done

echo "############# Build Successful..... #############"
