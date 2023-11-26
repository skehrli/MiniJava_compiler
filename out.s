	.text
	.globl asm_main
asm_main:		# entry point of program
	movq $17, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
