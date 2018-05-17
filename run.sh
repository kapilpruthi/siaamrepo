#!/bin/bash
# SIAAM Execute on embedded Tomcat
echo "############# Running SIAAM Web! #################"
cd siaamweb/sauth
mvn tomcat7:run
