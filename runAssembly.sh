# Check if at least one argument is provided
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 file"
    exit 1
fi

java -cp build/classes:lib/java-cup-11b.jar MiniJava $1 &> out.s
gcc -o out out.s ./src/runtime/boot.c
./out
rm ./out
