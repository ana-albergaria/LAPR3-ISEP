.section .data

.section .text
	.global is_refrigerated		
	
	is_refrigerated:
		# prologue
		pushq %rbp # save the original value of RBP
		movq %rsp, %rbp # copy the current stack pointer to RBP
		subq $2, %rsp
		pushq %rbx
		# ###########################################
		
		movq $10, %rbx
		movq $0, %rcx
		addq $4, %rdi


		arrLoop:
			movl (%rsi , %rcx , 4), %eax
			movl %eax , (%rdi , %rcx , 4)

			incq %rcx
			cmpq %rcx, %rbx
			jne arrLoop
		end:
			# Epilogue	
			popq %rbx
			movq %rbp, %rsp # restore the previous stack pointer ("clear" the stack)
			popq %rbp    # restore the previous stack frame pointer	
			ret
		
		
