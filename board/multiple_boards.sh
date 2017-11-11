#!/bin/bash
trap "kill 0" EXIT
MAX_BOARDS=$1
KAFKA_IP=$2
FILE_NAME="board_to_cpu.csv"
rm $FILE_NAME
echo "BOARDS,CPU,TIME" >> $FILE_NAME
echo 0,$(grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage "%"}'),$(date +%r) >> $FILE_NAME
for ((i=1;i<=$MAX_BOARDS;i+=1))
do
	echo "starting $i"
	python temp.py $i $KAFKA_IP &
	echo $i,$(grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage "%"}'),$(date +%r) >> $FILE_NAME
	sleep 3
done

while :
do
	echo $MAX_BOARDS,$(grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage "%"}'),$(date +%r) >> $FILE_NAME
	sleep 3
done

wait
