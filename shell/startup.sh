# web restart
ps -ef | grep wind | grep -v grep | awk '{print "kill -9 " $2}' | sh
nohup /usr/local/jdk1.8.0_77/bin/java -Xms2g -Xmx2g -Xmn1g -Xss1024K -XX:ParallelGCThreads=5 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=70 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs/wind/heapdump/ -jar /home/sufs_cn_admin/hanson/adam/wind.jar > /data/logs/wind/nohup1.log 2>&1 &

# app restart
ps -ef | grep wind | grep -v grep | awk '{print "kill -9 " $2}' | sh
nohup java -Dserver.servlet.context-path=/api/v21/adam -Xms2g -Xmx2g -Xmn1g -Xss1024K -XX:ParallelGCThreads=5 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+UseCMSCompactAtFullCollection -XX:CMSInitiatingOccupancyFraction=70 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs/wind/heapdump/ -jar /home/scheftalkadmin/hanson/adam/wind.jar > /data/logs/wind/nohup1.log 2>&1 &
