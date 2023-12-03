# Vtables
.data
Test$$:
	.quad 0
	.quad Test$start
	.quad Test$add
	.quad Test$sub
	.quad Test$mul
	.quad Test$all
LessThan$$:
	.quad 0
	.quad LessThan$start
	.quad LessThan$correct
	.quad LessThan$incorrect

.text
	.globl asm_main
asm_main:		# entry point of program
	pushq %rbp		# prologue - save frame ptr
	movq %rsp, %rbp		# no local vars - no additional stack

	pushq %rdi


	pushq %rdi


	pushq %rdi

	movq $8, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rdi

	leaq Test$$, %r8
	movq %r8, (%rax)
	pushq %rax
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

Test$start:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)

	pushq %rdi


	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq $8, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rdi

	leaq LessThan$$, %r8
	movq %r8, (%rax)
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *8(%rax)

	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $42, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Test$add:
	pushq %rbp
	movq %rsp, %rbp

	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $2, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $2, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $21, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Test$sub:
	pushq %rbp
	movq %rsp, %rbp

	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $2, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $2, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $21, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Test$mul:
	pushq %rbp
	movq %rsp, %rbp

	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $0, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $0, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $2, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $2, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $21, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Test$all:
	pushq %rbp
	movq %rsp, %rbp

	pushq %rdi

	movq $1, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $0, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	pushq %rax
	movq $2, %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $0, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %rax, %r11
	movq %r11, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $21, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

LessThan$start:
	pushq %rbp
	movq %rsp, %rbp

	pushq %rdi


	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $21, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

LessThan$correct:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
LessThan$correct_if1:
	movq $1, %rax
	movq %rax, %r12
	movq $2, %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz LessThan$correct_else1
	movq $1, %rax
	movq %rax, -8(%rbp)
	jmp LessThan$correct_endif1
LessThan$correct_else1:
	movq $0, %rax
	movq %rax, -8(%rbp)
LessThan$correct_endif1:
	movq -8(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

LessThan$incorrect:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
LessThan$incorrect_if1:
	movq $2, %rax
	movq %rax, %r12
	movq $1, %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz LessThan$incorrect_else1
	movq $1, %rax
	movq %rax, -8(%rbp)
	jmp LessThan$incorrect_endif1
LessThan$incorrect_else1:
	movq $0, %rax
	movq %rax, -8(%rbp)
LessThan$incorrect_endif1:
	movq -8(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret


