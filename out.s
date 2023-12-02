# Vtables
.data
TV$$:
	.quad 0
	.quad TV$Start
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
	.quad Tree$accept
Visitor$$:
	.quad 0
	.quad Visitor$visit
MyVisitor$$:
	.quad Visitor$$
	.quad MyVisitor$visit

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

	leaq TV$$, %r8
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

TV$Start:
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

	pushq %rdi

	movq $56, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rdi

	leaq Tree$$, %r8
	movq %r8, (%rax)
	movq %rax, -8(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $16, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *8(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq $100000000, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $8, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $24, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $4, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $20, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $28, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $14, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *104(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq $100000000, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq $24, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rdi

	leaq MyVisitor$$, %r8
	movq %r8, (%rax)
	movq %rax, -32(%rbp)

	pushq %rdi

	movq $50000000, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq -32(%rbp), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *168(%rax)

	popq %rdi

	movq %rax, -24(%rbp)

	pushq %rdi

	movq $100000000, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $24, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $16, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $50, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *112(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *152(%rax)

	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi


	pushq %rdi

	movq -8(%rbp), %rax
	pushq %rax
	movq $12, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *144(%rax)

	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rdi

	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

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
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	movq $0, %rax
	movq %rax, -8(%rbp)
	movq %rdx, %rax
	pushq %rax
	movq $1, %rax
	popq %r11
	addq %r11, %rax
	movq %rax, -16(%rbp)
Tree$Compare_if1:
	movq %rsi, %rax
	movq %rax, %r12
	movq %rdx, %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz Tree$Compare_else1
	movq $0, %rax
	movq %rax, -8(%rbp)
	jmp Tree$Compare_endif1
Tree$Compare_else1:
Tree$Compare_if2:
	movq %rsi, %rax
	movq %rax, %r12
	movq -16(%rbp), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax
	xorq $1, %rax

	cmpq $0, %rax
	jz Tree$Compare_else2
	movq $0, %rax
	movq %rax, -8(%rbp)
	jmp Tree$Compare_endif2
Tree$Compare_else2:
	movq $1, %rax
	movq %rax, -8(%rbp)
Tree$Compare_endif2:
Tree$Compare_endif1:
	movq -8(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Insert:
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

	pushq %rdi
	pushq %rsi

	movq $56, %rdi
	call mjcalloc		# Moves pointer into %rax

	popq %rsi
	popq %rdi

	leaq Tree$$, %r8
	movq %r8, (%rax)
	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	movq %rsi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *8(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)
	movq %rdi, %rax
	movq %rax, -24(%rbp)
	movq $1, %rax
	movq %rax, -32(%rbp)
Tree$Insert_while1:
	jmp Tree$Insert_while_test1
Tree$Insert_while_body1:

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -40(%rbp)
Tree$Insert_if1:
	movq %rsi, %rax
	movq %rax, %r12
	movq -40(%rbp), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz Tree$Insert_else1
Tree$Insert_if2:

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Insert_else2

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -24(%rbp)
	jmp Tree$Insert_endif2
Tree$Insert_else2:
	movq $0, %rax
	movq %rax, -32(%rbp)

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	movq $1, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *80(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)
Tree$Insert_endif2:
	jmp Tree$Insert_endif1
Tree$Insert_else1:
Tree$Insert_if3:

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Insert_else3

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -24(%rbp)
	jmp Tree$Insert_endif3
Tree$Insert_else3:
	movq $0, %rax
	movq %rax, -32(%rbp)

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	movq $1, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *88(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi

	movq -24(%rbp), %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)
Tree$Insert_endif3:
Tree$Insert_endif1:
Tree$Insert_while_test1:
	movq -32(%rbp), %rax
	cmpq $1, %rax
	jz Tree$Insert_while_body1
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Delete:
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
	movq %rdi, %rax
	movq %rax, -8(%rbp)
	movq %rdi, %rax
	movq %rax, -16(%rbp)
	movq $1, %rax
	movq %rax, -24(%rbp)
	movq $0, %rax
	movq %rax, -32(%rbp)
	movq $1, %rax
	movq %rax, -48(%rbp)
Tree$Delete_while1:
	jmp Tree$Delete_while_test1
Tree$Delete_while_body1:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -56(%rbp)
Tree$Delete_if1:
	movq %rsi, %rax
	movq %rax, %r12
	movq -56(%rbp), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz Tree$Delete_else1
Tree$Delete_if2:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Delete_else2
	movq -8(%rbp), %rax
	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Delete_endif2
Tree$Delete_else2:
	movq $0, %rax
	movq %rax, -24(%rbp)
Tree$Delete_endif2:
	jmp Tree$Delete_endif1
Tree$Delete_else1:
Tree$Delete_if3:
	movq -56(%rbp), %rax
	movq %rax, %r12
	movq %rsi, %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz Tree$Delete_else3
Tree$Delete_if4:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Delete_else4
	movq -8(%rbp), %rax
	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Delete_endif4
Tree$Delete_else4:
	movq $0, %rax
	movq %rax, -24(%rbp)
Tree$Delete_endif4:
	jmp Tree$Delete_endif3
Tree$Delete_else3:
Tree$Delete_if5:
	movq -48(%rbp), %rax

	cmpq $0, %rax
	jz Tree$Delete_else5
Tree$Delete_if6:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi

	xorq $1, %rax
	testq %rax, %rax
	movq $0, %r11
	cmovzq %r11, %rax
	jz Tree$Delete_end_and1
	pushq %rax

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi

	xorq $1, %rax
	popq %r11
	andq %r11, %rax
Tree$Delete_end_and1:

	cmpq $0, %rax
	jz Tree$Delete_else6
	movq $1, %rax
	movq %rax, -40(%rbp)
	jmp Tree$Delete_endif6
Tree$Delete_else6:

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax
	movq -16(%rbp), %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *120(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -40(%rbp)
Tree$Delete_endif6:
	jmp Tree$Delete_endif5
Tree$Delete_else5:

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax
	movq -16(%rbp), %rax
	pushq %rax
	movq -8(%rbp), %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *120(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -40(%rbp)
Tree$Delete_endif5:
	movq $1, %rax
	movq %rax, -32(%rbp)
	movq $0, %rax
	movq %rax, -24(%rbp)
Tree$Delete_endif3:
Tree$Delete_endif1:
	movq $0, %rax
	movq %rax, -48(%rbp)
Tree$Delete_while_test1:
	movq -24(%rbp), %rax
	cmpq $1, %rax
	jz Tree$Delete_while_body1
	movq -32(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Remove:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
Tree$Remove_if1:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rdx
	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Remove_else1

	pushq %rdi
	pushq %rsi
	pushq %rdx

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

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Remove_endif1
Tree$Remove_else1:
Tree$Remove_if2:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rdx
	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Remove_else2

	pushq %rdi
	pushq %rsi
	pushq %rdx

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

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Remove_endif2
Tree$Remove_else2:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -16(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx


	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -24(%rbp)
Tree$Remove_if3:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdi, %rax
	pushq %rax
	movq -16(%rbp), %rax
	pushq %rax
	movq -24(%rbp), %rax
	pushq %rax
	popq %rdx
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *96(%rax)

	popq %rdx
	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Remove_else3

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *80(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Remove_endif3
Tree$Remove_else3:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *88(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
Tree$Remove_endif3:
Tree$Remove_endif2:
Tree$Remove_endif1:
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$RemoveRight:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
Tree$RemoveRight_while1:
	jmp Tree$RemoveRight_while_test1
Tree$RemoveRight_while_body1:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi
	pushq %rdx


	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *56(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq %rdx, %rax
	movq %rax, %rsi

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdx
Tree$RemoveRight_while_test1:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $1, %rax
	jz Tree$RemoveRight_while_body1

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *16(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *88(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$RemoveLeft:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
Tree$RemoveLeft_while1:
	jmp Tree$RemoveLeft_while_test1
Tree$RemoveLeft_while_body1:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi
	pushq %rdx


	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *56(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq %rdx, %rax
	movq %rax, %rsi

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, %rdx
Tree$RemoveLeft_while_test1:

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rdx, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	cmpq $1, %rax
	jz Tree$RemoveLeft_while_body1

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq 48(%rdi), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *24(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)

	pushq %rdi
	pushq %rsi
	pushq %rdx

	movq %rsi, %rax
	pushq %rax
	movq $0, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *80(%rax)

	popq %rdx
	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Search:
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
	movq %rdi, %rax
	movq %rax, -8(%rbp)
	movq $1, %rax
	movq %rax, -24(%rbp)
	movq $0, %rax
	movq %rax, -16(%rbp)
Tree$Search_while1:
	jmp Tree$Search_while_test1
Tree$Search_while_body1:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -32(%rbp)
Tree$Search_if1:
	movq %rsi, %rax
	movq %rax, %r12
	movq -32(%rbp), %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz Tree$Search_else1
Tree$Search_if2:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Search_else2

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Search_endif2
Tree$Search_else2:
	movq $0, %rax
	movq %rax, -24(%rbp)
Tree$Search_endif2:
	jmp Tree$Search_endif1
Tree$Search_else1:
Tree$Search_if3:
	movq -32(%rbp), %rax
	movq %rax, %r12
	movq %rsi, %rax
	cmpq %rax, %r12
	movq $0, %rax
	movq $1, %r12
	cmovlq %r12, %rax

	cmpq $0, %rax
	jz Tree$Search_else3
Tree$Search_if4:

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$Search_else4

	pushq %rdi
	pushq %rsi

	movq -8(%rbp), %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$Search_endif4
Tree$Search_else4:
	movq $0, %rax
	movq %rax, -24(%rbp)
Tree$Search_endif4:
	jmp Tree$Search_endif3
Tree$Search_else3:
	movq $1, %rax
	movq %rax, -16(%rbp)
	movq $0, %rax
	movq %rax, -24(%rbp)
Tree$Search_endif3:
Tree$Search_endif1:
Tree$Search_while_test1:
	movq -24(%rbp), %rax
	cmpq $1, %rax
	jz Tree$Search_while_body1
	movq -16(%rbp), %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$Print:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
	movq %rdi, %rax
	movq %rax, -16(%rbp)

	pushq %rdi

	movq %rdi, %rax
	pushq %rax
	movq -16(%rbp), %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *160(%rax)

	popq %rdi

	movq %rax, -8(%rbp)
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$RecPrint:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
Tree$RecPrint_if1:

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$RecPrint_else1

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *160(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$RecPrint_endif1
Tree$RecPrint_else1:
	movq $1, %rax
	movq %rax, -8(%rbp)
Tree$RecPrint_endif1:

	pushq %rdi
	pushq %rsi


	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rsi
	popq %rdi

Tree$RecPrint_if2:

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Tree$RecPrint_else2

	pushq %rdi
	pushq %rsi

	movq %rdi, %rax
	pushq %rax

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *160(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Tree$RecPrint_endif2
Tree$RecPrint_else2:
	movq $1, %rax
	movq %rax, -8(%rbp)
Tree$RecPrint_endif2:
	movq $1, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

Tree$accept:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)

	pushq %rdi
	pushq %rsi

	movq $333, %rax
	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rsi
	popq %rdi


	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	movq %rdi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *8(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

	subq $8, %rsp
	movq $0, (%rsp)
	subq $8, %rsp
	movq $0, (%rsp)
Visitor$visit:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
Visitor$visit_if1:

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Visitor$visit_else1

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	movq %rax, 16(%rdi)

	pushq %rdi
	pushq %rsi

	movq 16(%rdi), %rax
	pushq %rax
	movq %rdi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *168(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Visitor$visit_endif1
Visitor$visit_else1:
	movq $0, %rax
	movq %rax, -8(%rbp)
Visitor$visit_endif1:
Visitor$visit_if2:

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz Visitor$visit_else2

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rsi
	popq %rdi

	movq %rax, 8(%rdi)

	pushq %rdi
	pushq %rsi

	movq 8(%rdi), %rax
	pushq %rax
	movq %rdi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *168(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp Visitor$visit_endif2
Visitor$visit_else2:
	movq $0, %rax
	movq %rax, -8(%rbp)
Visitor$visit_endif2:
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret

MyVisitor$visit:
	pushq %rbp
	movq %rsp, %rbp
	subq $8, %rsp
	movq $0, (%rsp)
MyVisitor$visit_if1:

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *64(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz MyVisitor$visit_else1

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *32(%rax)

	popq %rsi
	popq %rdi

	movq %rax, 16(%rdi)

	pushq %rdi
	pushq %rsi

	movq 16(%rdi), %rax
	pushq %rax
	movq %rdi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *168(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp MyVisitor$visit_endif1
MyVisitor$visit_else1:
	movq $0, %rax
	movq %rax, -8(%rbp)
MyVisitor$visit_endif1:

	pushq %rdi
	pushq %rsi


	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *48(%rax)

	popq %rsi
	popq %rdi

	movq %rax, %rdi		# Move expression to first argument register
	call put		# Method in C file

	popq %rsi
	popq %rdi

MyVisitor$visit_if2:

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *72(%rax)

	popq %rsi
	popq %rdi


	cmpq $0, %rax
	jz MyVisitor$visit_else2

	pushq %rdi
	pushq %rsi

	movq %rsi, %rax
	pushq %rax
	popq %rdi
	movq (%rdi), %rax
	call *40(%rax)

	popq %rsi
	popq %rdi

	movq %rax, 8(%rdi)

	pushq %rdi
	pushq %rsi

	movq 8(%rdi), %rax
	pushq %rax
	movq %rdi, %rax
	pushq %rax
	popq %rsi
	popq %rdi
	movq (%rdi), %rax
	call *168(%rax)

	popq %rsi
	popq %rdi

	movq %rax, -8(%rbp)
	jmp MyVisitor$visit_endif2
MyVisitor$visit_else2:
	movq $0, %rax
	movq %rax, -8(%rbp)
MyVisitor$visit_endif2:
	movq $0, %rax
	movq %rbp,%rsp		# epilogue - return
	popq %rbp		
	ret


