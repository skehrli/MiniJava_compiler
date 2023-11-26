	.text
	.globl asm_main
asm_main:		# entry point of program
	pushq %rbp		# prologue - save frame ptr
	movq %rsp, %rbp		# no local vars - no additional stack
	movq $17, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

