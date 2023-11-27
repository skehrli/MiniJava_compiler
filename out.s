# Vtables for the program
.data
BT$$:
	.quad 0
	.quad BT$Start
Tree$$:
	.quad 0
	.quad Tree$Init
	.quad Tree$SetRight
	.quad Tree$SetLeft
	.quad Tree$GetRight
	.quad Tree$GetLeft
	.quad Tree$GetKey
	.quad Tree$SetKey
	.quad Tree$GetHas_Right
	.quad Tree$GetHas_Left
	.quad Tree$SetHas_Left
	.quad Tree$SetHas_Right
	.quad Tree$Compare
	.quad Tree$Insert
	.quad Tree$Delete
	.quad Tree$Remove
	.quad Tree$RemoveRight
	.quad Tree$RemoveLeft
	.quad Tree$Search
	.quad Tree$Print
	.quad Tree$RecPrint

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

	movq $8, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	leaq BT$$(%rip), %r8
	movq %r8, (%rax)
	pushq %rax
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
BT$Start:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
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

	movq $56, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	leaq Tree$$(%rip), %r8
	movq %r8, (%rax)
	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $16, %rax
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

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq $100000000, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $8, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $24, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $4, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $20, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $28, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $14, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $24, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $16, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $50, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *112(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

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

	movq -0(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

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
Tree$Init:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 24(%rdi)
	movq $0, %rax
	movq %rax, 32(%rdi)
	movq $0, %rax
	movq %rax, 40(%rdi)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$SetRight:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 16(%rdi)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$SetLeft:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 8(%rdi)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$GetRight:
	pushq %rbp
	movq %rsp, %rbp
	movq 16(%rdi), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$GetLeft:
	pushq %rbp
	movq %rsp, %rbp
	movq 8(%rdi), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$GetKey:
	pushq %rbp
	movq %rsp, %rbp
	movq 24(%rdi), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$SetKey:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 24(%rdi)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$GetHas_Right:
	pushq %rbp
	movq %rsp, %rbp
	movq 40(%rdi), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$GetHas_Left:
	pushq %rbp
	movq %rsp, %rbp
	movq 32(%rdi), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$SetHas_Left:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 32(%rdi)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$SetHas_Right:
	pushq %rbp
	movq %rsp, %rbp
	movq %rsi, %rax
	movq %rax, 40(%rdi)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Compare:
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
	jz false1
	movq $0, %rax
	movq %rax, -0(%rbp)
	jmp afterIf1
false1:
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
	jz false2
	movq $0, %rax
	movq %rax, -0(%rbp)
	jmp afterIf2
false2:
	movq $1, %rax
	movq %rax, -0(%rbp)
afterIf2:
afterIf1:
	movq -0(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Insert:
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

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq $56, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	leaq Tree$$(%rip), %r8
	movq %r8, (%rax)
	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	movq %rsi, %rax
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

	movq %rax, -8(%rbp)
	movq %rdi, %rax
	movq %rax, -32(%rbp)
	movq $1, %rax
	movq %rax, -16(%rbp)
	jmp whileTest1
whileBody1:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -24(%rbp)
	movq %rsi, %rax
	pushq %rax
	movq -24(%rbp), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false3

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false4

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
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

	movq %rax, -32(%rbp)
	jmp afterIf4
false4:
	movq $0, %rax
	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
	pushq %rax
	movq $1, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *80(%rax)

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

	movq -32(%rbp), %rax
	pushq %rax
	movq -0(%rbp), %rax
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

	movq %rax, -8(%rbp)
afterIf4:
	jmp afterIf3
false3:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false5

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -32(%rbp)
	jmp afterIf5
false5:
	movq $0, %rax
	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -32(%rbp), %rax
	pushq %rax
	movq $1, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *88(%rax)

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

	movq -32(%rbp), %rax
	pushq %rax
	movq -0(%rbp), %rax
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

	movq %rax, -8(%rbp)
afterIf5:
afterIf3:
whileTest1:
	movq -16(%rbp), %rax
	cmpq $0, %rax
	jz whileBody1
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Delete:
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
	movq %rdi, %rax
	movq %rax, -0(%rbp)
	movq %rdi, %rax
	movq %rax, -8(%rbp)
	movq $1, %rax
	movq %rax, -16(%rbp)
	movq $0, %rax
	movq %rax, -24(%rbp)
	movq $1, %rax
	movq %rax, -32(%rbp)
	jmp whileTest2
whileBody2:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

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
	jz false6

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false7
	movq -0(%rbp), %rax
	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
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

	movq %rax, -0(%rbp)
	jmp afterIf7
false7:
	movq $0, %rax
	movq %rax, -16(%rbp)
afterIf7:
	jmp afterIf6
false6:
	movq -40(%rbp), %rax
	pushq %rax
	movq %rsi, %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false8

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false9
	movq -0(%rbp), %rax
	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	jmp afterIf9
false9:
	movq $0, %rax
	movq %rax, -16(%rbp)
afterIf9:
	jmp afterIf8
false8:
	movq -32(%rbp), %rax
	cmpq $0, %rax
	jz false10

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	xorq $1, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -0(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	xorq $1, %rax
	popq %rdx
	and %rdx, %rax
	cmpq $0, %rax
	jz false11
	movq $1, %rax
	movq %rax, -48(%rbp)
	jmp afterIf11
false11:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	movq -0(%rbp), %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *120(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -48(%rbp)
afterIf11:
	jmp afterIf10
false10:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	movq -0(%rbp), %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *120(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -48(%rbp)
afterIf10:
	movq $1, %rax
	movq %rax, -24(%rbp)
	movq $0, %rax
	movq %rax, -16(%rbp)
afterIf8:
afterIf6:
	movq $0, %rax
	movq %rax, -32(%rbp)
whileTest2:
	movq -16(%rbp), %rax
	cmpq $0, %rax
	jz whileBody2
	movq -24(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Remove:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
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

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false12

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
	movq %rdx, %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *136(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	jmp afterIf12
false12:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false13

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
	movq %rdx, %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *128(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	jmp afterIf13
false13:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

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


	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
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

	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	movq -16(%rbp), %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *96(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false14

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
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

	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *80(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	jmp afterIf14
false14:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
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

	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *88(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
afterIf14:
afterIf13:
afterIf12:
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$RemoveRight:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	jmp whileTest3
whileBody3:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax

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

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *56(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	movq %rdx, %rax
	movq %rax, %rsi

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdx
whileTest3:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz whileBody3

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
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

	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *88(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$RemoveLeft:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	jmp whileTest4
whileBody4:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax

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

	movq %rdx, %rax
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

	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *56(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	movq %rdx, %rax
	movq %rax, %rsi

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
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

	movq %rax, %rdx
whileTest4:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz whileBody4

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
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

	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *80(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Search:
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
	movq %rdi, %rax
	movq %rax, -16(%rbp)
	movq $1, %rax
	movq %rax, -0(%rbp)
	movq $0, %rax
	movq %rax, -8(%rbp)
	jmp whileTest5
whileBody5:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -16(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -24(%rbp)
	movq %rsi, %rax
	pushq %rax
	movq -24(%rbp), %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false15

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -16(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false16

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -16(%rbp), %rax
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

	movq %rax, -16(%rbp)
	jmp afterIf16
false16:
	movq $0, %rax
	movq %rax, -0(%rbp)
afterIf16:
	jmp afterIf15
false15:
	movq -24(%rbp), %rax
	pushq %rax
	movq %rsi, %rax
	movq %rax, %r11
	movq $1, %r10
	movq $0, %rax
	popq %r12
	cmpq %r12, %r11
	cmovl %r10, %rax
	cmpq $0, %rax
	jz false17

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -16(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false18

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq -16(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)
	jmp afterIf18
false18:
	movq $0, %rax
	movq %rax, -0(%rbp)
afterIf18:
	jmp afterIf17
false17:
	movq $1, %rax
	movq %rax, -8(%rbp)
	movq $0, %rax
	movq %rax, -0(%rbp)
afterIf17:
afterIf15:
whileTest5:
	movq -0(%rbp), %rax
	cmpq $0, %rax
	jz whileBody5
	movq -8(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Print:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq $0, (%rsp)
	addq $-8, %rsp
	movq %rdi, %rax
	movq %rax, -0(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax
	movq -0(%rbp), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *160(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$RecPrint:
	pushq %rbp
	movq %rsp, %rbp
	movq $0, (%rsp)
	addq $-8, %rsp

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false19

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
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

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *160(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	jmp afterIf19
false19:
	movq $1, %rax
	movq %rax, -0(%rbp)
afterIf19:

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file
	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $0, %rax
	jz false20

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rdi, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi
	pushq %rdx
	pushq %rcx
	pushq %r8
	pushq %r9

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *160(%rax)

	popq %r9
	popq %r8
	popq %rcx
	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -0(%rbp)
	jmp afterIf20
false20:
	movq $1, %rax
	movq %rax, -0(%rbp)
afterIf20:
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret


