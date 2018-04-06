#!/usr/bin/env bash
sudo pkill -f "java -jar"
java -jar /home/ec2-user/JavaServer/target/JavaServer.jar 8100 > /dev/null 2> /dev/null < /dev/null &