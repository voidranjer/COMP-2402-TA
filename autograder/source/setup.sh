#!/usr/bin/env bash
apt-get -y install openjdk-11-jdk
apt-get install -y python3 python3-pip python3-dev
# pip3 install -r /autograder/source/requirements.txt
pip3 install -r /autograder/source/requirements.txt
# The following lines are to make sure we can install packages from maven
# See https://bugs.launchpad.net/ubuntu/+source/ca-certificates-java/+bug/1396760
# and https://github.com/docker-library/openjdk/issues/19#issuecomment-70546872
# apt-get install --reinstall ca-certificates-java
# update-ca-certificates -f
