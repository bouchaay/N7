cp $1 /tmp/test.txt

# generate 2^$2 times file $1
for ((n=0;n<$2;n++))
do
cat /tmp/test.txt /tmp/test.txt > /tmp/temp
mv /tmp/temp /tmp/test.txt
done
mv /tmp/test.txt .
