#!/bin/bash



#export MEMORY_OPTS=-Xms128m -Xmx512m

export ADV_DIR_HOME=`pwd`
export ADV_JAR=$ADV_DIR_HOME/lib/${artifactId}-${version}.jar
export ADV_OPTS="-Dadv.module=$MODULE"
java $JAVA_OPTS $MEMORY_OPTS $ADV_OPTS $DEBUG_OPTS -jar "$ADV_JAR" 