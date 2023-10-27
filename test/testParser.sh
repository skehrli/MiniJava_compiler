ant
directory="SamplePrograms/Programs"

# Use a for loop to iterate over the files in the directory
for file in "$directory"/*; do
    filename=$(basename "$file")
    if [ -f "$file" ]; then
        java -cp build/classes:lib/java-cup-11b.jar MiniJava -A ${file} > SamplePrograms/Outputs/$filename.out
    fi
done
