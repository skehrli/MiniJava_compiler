# Check if at least one argument is provided
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 file"
    exit 1
fi

hline="------------------------------------------------"
echo "Compiling program with no flags" ; echo $hline ;
    java -cp build/classes:lib/java-cup-11b.jar MiniJava $1 | tee out.s &&
    gcc -o out out.s ./src/runtime/boot.c &&
    echo "Program compiled successfully, running program" ; echo $hline ;
    ./out && rm ./out
