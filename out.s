# Vtables for the program
.data
BBS$$:
	.quad 0
	.quad BBS$Start
	.quad BBS$Sort
	.quad BBS$Print
	.quad BBS$Init

.text
	.globl asm_main
asm_main:		# entry point of program
	pushq %rbp		# prologue - save frame ptr
	movq %rsp, %rbp		# no local vars - no additional stack

	pushq %rdi


	pushq %rdi


	pushq %rdi

	movq $24, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rdi

	leaq BBS$$, %r8
	movq %r8, (%rax)
	pushq %rax
	movq $10, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *8(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq %rbp, %rsp		# epilogue - return
	popq %rbp		
	ret

	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
BBS$Start:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax
	movq %rsi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi

	movq $99999, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rsi
	popq %rdi


	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BBS$Sort:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	movq 16(%rdi), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, -16(%rbp)
	movq $0, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, -24(%rbp)
BBS$Sort_while1:
	jmp BBS$Sort_while_test1
BBS$Sort_while_body1:
	movq $1, %rax
	movq %rax, -64(%rbp)
BBS$Sort_while2:
	jmp BBS$Sort_while_test2
BBS$Sort_while_body2:
	movq -64(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, -56(%rbp)
	movq 8(%rdi), %rax
	pushq %rax
	movq -56(%rbp), %rax
	addq $1, %rax
	popq %r11
	movq (%r11,%rax,8), %rax
	movq %rax, -32(%rbp)
	movq 8(%rdi), %rax
	pushq %rax
	movq -64(%rbp), %rax
	addq $1, %rax
	popq %r11
	movq (%r11,%rax,8), %rax
	movq %rax, -40(%rbp)
BBS$Sort_if1:
	movq -40(%rbp), %rax
	movq %rax, %r12
	movq -32(%rbp), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax		# Condition test
	jz BBS$Sort_else1
	movq -64(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, -48(%rbp)
	movq 8(%rdi), %rax
	pushq %rax
	movq -48(%rbp), %rax
	addq $1, %rax
	popq %r11
	movq (%r11,%rax,8), %rax
	movq %rax, -72(%rbp)
	movq -48(%rbp), %rax
	pushq %rax
	movq 8(%rdi), %rax
	pushq %rax
	movq -64(%rbp), %rax
	addq $1, %rax
	popq %r11
	movq (%r11,%rax,8), %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq -64(%rbp), %rax
	pushq %rax
	movq -72(%rbp), %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	jmp BBS$Sort_endif1
BBS$Sort_else1:
	movq $0, %rax
	movq %rax, -8(%rbp)
BBS$Sort_endif1:
	movq -64(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -64(%rbp)
BBS$Sort_while_test2:
	movq -64(%rbp), %rax
	movq %rax, %r12
	movq -16(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax
	cmpq $1, %rax
	jz BBS$Sort_while_body2
	movq -16(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, -16(%rbp)
BBS$Sort_while_test1:
	movq -24(%rbp), %rax
	movq %rax, %r12
	movq -16(%rbp), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax
	cmpq $1, %rax
	jz BBS$Sort_while_body1
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BBS$Print:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
	movq $0, %rax
	movq %rax, -8(%rbp)
BBS$Print_while1:
	jmp BBS$Print_while_test1
BBS$Print_while_body1:

	pushq %rdi

	movq 8(%rdi), %rax
	pushq %rax
	movq -8(%rbp), %rax
	addq $1, %rax
	popq %r11
	movq (%r11,%rax,8), %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -8(%rbp)
BBS$Print_while_test1:
	movq -8(%rbp), %rax
	movq %rax, %r12
	movq 16(%rdi), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax
	cmpq $1, %rax
	jz BBS$Print_while_body1
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BBS$Init:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 16(%rdi)
	movq %rsi, %rax
	pushq %rax
	addq $1, %rax		# For storing the length
	shlq $3, %rax		# convert length to required nr of bytes

	pushq %rdi
	pushq %rsi

	movq %rax, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rsi
	popq %rdi

	popq %r11
	movq %r11, (%rax)
	movq %rax, 8(%rdi)
	movq $0, %rax
	pushq %rax
	movq $20, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $1, %rax
	pushq %rax
	movq $7, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $2, %rax
	pushq %rax
	movq $12, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $3, %rax
	pushq %rax
	movq $18, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $4, %rax
	pushq %rax
	movq $2, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $5, %rax
	pushq %rax
	movq $11, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $6, %rax
	pushq %rax
	movq $6, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $7, %rax
	pushq %rax
	movq $9, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $8, %rax
	pushq %rax
	movq $19, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $9, %rax
	pushq %rax
	movq $5, %rax
	movq %rax, %r11
	popq %r10
	addq $1, %r10
	movq 8(%rdi), %rax
	movq %r11, (%rax,%r10,8)
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret


