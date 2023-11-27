.data
A$$:
	.quad 0
	.quad A$one
	.quad A$two
B$$:
	.quad A$$
	.quad A$one
	.quad B$two
	.quad B$three

.text
	.globl asm_main
asm_main:		# entry point of program
	pushq %rbp		# prologue - save frame ptr
	movq %rsp, %rbp		# no local vars - no additional stack
	movq $42, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret
	movq $1, %rax
	movq $2, %rax
	movq $3, %rax
	movq $3, %rax

