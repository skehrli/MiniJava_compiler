# Check if at least one argument is provided
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 directory"
    exit 1
fi

outdir=./test/logs/
mkdir -p $outdir

for line in $(ls $1*.java); do
    echo $line
    x=$(echo $line | echo $(head -c -6))
    x=${x:${#1}}

    classfile_d=SamplePrograms/Classfiles/$x
    mkdir -p $classfile_d;

    ( (java -cp build/classes:lib/java-cup-11b.jar MiniJava $line > out.s &&
    gcc -o out out.s ./src/runtime/boot.c &&
    echo "Compiled successfully with minijava, running program") | tee $outdir"logfile.txt" ) ||
    (echo "Failure to compile program with minijava" && echo $outdir"logfile.txt") ; rm $outdir"logfile.txt";
    ./out &> $outdir$(echo $x)minijava.txt && rm ./out
    echo "Compiled successfully with javac, running program" ;
    javac -d $classfile_d $1$x.java &&
    java -cp $classfile_d $1$x.java &> $outdir$(echo $x)javac.txt
    (diff $outdir/$(echo $x)minijava.txt $outdir$(echo $x)javac.txt && echo NO DIFFERENCES) || echo "Difference, see above" 1>&2
    echo ""
done
