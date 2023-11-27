Line 10: Superclass Object cannot be resolved.
# Vtables for the program
.data
BS$$:
	.quad 0
	.quad BS$Start
	.quad BS$Search
	.quad BS$Div
	.quad BS$Compare
	.quad BS$Print
	.quad BS$Init

.text
	.globl asm_main
asm_main:		# entry point of program
	pushq %rbp		# prologue - save frame ptr
	movq %rsp, %rbp		# no local vars - no additional stack

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9


	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq $24, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	movq $20, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *8(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
BS$Start:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq %rsi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $8, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false1
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf1
false1:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf1:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $19, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false2
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf2
false2:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf2:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $20, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false3
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf3
false3:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf3:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $21, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false4
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf4
false4:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf4:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $37, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false5
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf5
false5:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf5:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $38, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false6
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf6
false6:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf6:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $39, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false7
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf7
false7:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf7:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq $50, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false8
	movq $1, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	jmp afterIf8
false8:
	movq $0, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C fileafterIf8:
	movq $999, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BS$Search:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, %rax
	movq %rax, -40(%rbp)
	movq $0, %rax
	movq %rax, -0(%rbp)
	movq 8(%rdi), %rax
	movq (%rax), %rax
	movq %rax, -8(%rbp)
	movq -8(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %r11, %rax
	movq %rax, -8(%rbp)
	movq $0, %rax
	movq %rax, -16(%rbp)
	movq $1, %rax
	movq %rax, -24(%rbp)
	jmp whileTest1
whileBody1:
	movq -16(%rbp), %rax
	pushq %rax
	movq -8(%rbp), %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -32(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -32(%rbp), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -32(%rbp)
	movq 8(%rdi), %rax
	pushq %rax
	movq -32(%rbp), %rax
	addq $1, %rax
	popq r11
	movq (%r11,%rax,8), %rax
	movq %rax, -40(%rbp)
	movq %rsi, %rax
	pushq %rax
	movq -40(%rbp), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false9
	movq -32(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %r11, %rax
	movq %rax, -8(%rbp)
	jmp afterIf9
false9:
	movq -32(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -16(%rbp)
afterIf9:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -40(%rbp), %rax
	pushq %rax
	movq %rsi, %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false10
	movq $0, %rax
	movq %rax, -24(%rbp)
	jmp afterIf10
false10:
	movq $1, %rax
	movq %rax, -24(%rbp)
afterIf10:
	movq -8(%rbp), %rax
	pushq %rax
	movq -16(%rbp), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false11
	movq $0, %rax
	movq %rax, -24(%rbp)
	jmp afterIf11
false11:
	movq $0, %rax
	movq %rax, -48(%rbp)
afterIf11:
whileTest1:
	movq -24(%rbp), %rax
	cmpq $0, %rax
	jz whileBody1

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -40(%rbp), %rax
	pushq %rax
	movq %rsi, %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)
	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false12
	movq $1, %rax
	movq %rax, -0(%rbp)
	jmp afterIf12
false12:
	movq $0, %rax
	movq %rax, -0(%rbp)
afterIf12:
	movq -0(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BS$Div:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, %rax
	movq %rax, -0(%rbp)
	movq $0, %rax
	movq %rax, -8(%rbp)
	movq %rsi, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %r11, %rax
	movq %rax, -16(%rbp)
	jmp whileTest2
whileBody2:
	movq -0(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -0(%rbp)
	movq -8(%rbp), %rax
	pushq %rax
	movq $2, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -8(%rbp)
whileTest2:
	movq -8(%rbp), %rax
	pushq %rax
	movq -16(%rbp), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz whileBody2
	movq -0(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BS$Compare:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, %rax
	movq %rax, -0(%rbp)
	movq %rdx, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -8(%rbp)
	movq %rsi, %rax
	pushq %rax
	movq %rdx, %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false13
	movq $0, %rax
	movq %rax, -0(%rbp)
	jmp afterIf13
false13:
	movq %rsi, %rax
	pushq %rax
	movq -8(%rbp), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	xorq $1, %rax
	cmpq $0, %rax
	jz false14
	movq $0, %rax
	movq %rax, -0(%rbp)
	jmp afterIf14
false14:
	movq $1, %rax
	movq %rax, -0(%rbp)
afterIf14:
afterIf13:
	movq -0(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BS$Print:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $1, %rax
	movq %rax, -0(%rbp)
	jmp whileTest3
whileBody3:
	movq 8(%rdi), %rax
	pushq %rax
	movq -0(%rbp), %rax
	addq $1, %rax
	popq r11
	movq (%r11,%rax,8), %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	movq -0(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -0(%rbp)
whileTest3:
	movq -0(%rbp), %rax
	pushq %rax
	movq 16(%rdi), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz whileBody3
	movq $99999, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

BS$Init:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq %rsi, %rax
	movq %rax, 16(%rdi)
	movq %rsi, %rax
	pushq %rax
	addq $1, %rax		# For storing the length

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rax, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	popq %r11
	shrq $3, %r11
	movq %r11, (%rax)
	movq %rax, 8(%rdi)
	movq $1, %rax
	movq %rax, -0(%rbp)
	movq 16(%rdi), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -8(%rbp)
	jmp whileTest4
whileBody4:
	movq $2, %rax
	pushq %rax
	movq -0(%rbp), %rax
	popq %r11
	imulq %r11, %rax
	movq %rax, -24(%rbp)
	movq -8(%rbp), %rax
	pushq %rax
	movq $3, %rax
	popq %r11
	subq %r11, %rax
	movq %rax, -16(%rbp)
	pushq %rbx
	movq -0(%rbp), %rax
	pushq %rax
	movq -24(%rbp), %rax
	pushq %rax
	movq -16(%rbp), %rax
	popq %r11
	addq %r11, %rax
	movq %rax, %r11
	popq rbx
	addq $1, %rbx
	movq 8(%rdi), %rax
	movq %r11, (%rax,%rbx,8)
	popq rbx
	movq -0(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -0(%rbp)
	movq -8(%rbp), %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	subq %r11, %rax
	movq %rax, -8(%rbp)
whileTest4:
	movq -0(%rbp), %rax
	pushq %rax
	movq 16(%rdi), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz whileBody4
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret


