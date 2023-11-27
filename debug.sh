# echo("Program expects the assembly file to be stored in ./out.s")
as -g -o out.o out.s
gcc -c -o boot.o ./src/runtime/boot.c
gcc -o out out.o boot.o
gdb ./out
