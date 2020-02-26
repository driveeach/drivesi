#########################################
#
# Melina Brilhadori e Murilo Honorio -- 04/12/2009
# ep-1.1.asm 
# Descricao: le um inteiro positivo e imprime por extenso (ignorando zeros a esquerda).
# Registradores:
#	$s0	- armazena o inteiro digitado pelo usuario
#	$s1	- numero de digitos
#	$a0	- parametro de syscall
#	$v0	- parametro de syscall
#	$a1	- argumento de numtexto (digito)
#	$a2	- endereco de n[0]
#	$t0	- armazena o inteiro temporariamente nas divisoes sucessivas
#	$t2	- armazena um digito isolado
#	$t3	- endereco temporario, indice
#	$sp	- topo da pilha
#	$ra	- endereco de retorno
#
#########################################
# segmento de dados

	.data				
aut_msg:	.asciiz "Exercico A.8 de OCD,\npor Melina Brilhadori e Murilo Honorio.\n"
ins_msg:	.asciiz "Digite um numero inteiro: "
inv_msg:	.asciiz "Entrada invalida"
espaco: 	.asciiz " "
n_zero:		.asciiz "Zero"
n_ummm:		.asciiz "Um"	
n_dois:		.asciiz "Dois"
n_tres:		.asciiz "Tres"
n_quat:		.asciiz "Quatro"
n_cinc:		.asciiz "Cinco"
n_seis:		.asciiz "Seis"
n_sete:		.asciiz "Sete"
n_oito:		.asciiz "Oito"
n_nove:		.asciiz "Nove"
n:		.word	0,0,0,0,0,0,0,0,0,0	# n[10], array de dez digitos

#########################################
# segmento de instrucoes

	.text				

load_num:				# carrega os dados no array n[]
	la	$a2, n			# endereco de n[0]
	la	$t3, n_zero		# endereco de "Zero"
	sw	$t3, 0($a2)		# grava o endereco de "Zero" em n[0]
	la	$t3, n_ummm		# endereco de "Um"
	sw	$t3, 4($a2)		# grava o endereco de "Um" em n[1]
	la	$t3, n_dois		# endereco de "Dois"
	sw	$t3, 8($a2)		# grava o endereco de "Dois" em n[2]
	la	$t3, n_tres		# endereco de "Tres"
	sw	$t3, 12($a2)		# grava o endereco de "Tres" em n[3]
	la	$t3, n_quat		# endereco de "Quatro"
	sw	$t3, 16($a2)		# grava o endereco de "Quatro" em n[4]
	la	$t3, n_cinc		# endereco de "Cinco"
	sw	$t3, 20($a2)		# grava o endereco de "Cinco" em n[5]
	la	$t3, n_seis		# endereco de "Seis"
	sw	$t3, 24($a2)		# grava o endereco de "Seis" em n[6]
	la	$t3, n_sete		# endereco de "Sete"
	sw	$t3, 28($a2)		# grava o endereco de "Sete" em n[7]
	la	$t3, n_oito		# endereco de "Oito"
	sw	$t3, 32($a2)		# grava o endereco de "Oito" em n[8]
	la	$t3, n_nove		# endereco de "Nove"
	sw	$t3, 36($a2)		# grava o endereco de "Nove" em n[9]
	jr	$ra			# retorna


inval:					# imprime mensagem para inteiros negativos
	la	$a0, inv_msg		# coloca o endereco de inv_msg em $a0
	li	$v0, 4			# syscall = 4: "print_string"
	syscall				# mensagem: "Entrada invalida"
	b	fim			# desvia para o fim


separa:					# separa cada digito, colocando na pilha na ordem: unidade, dezena, centena...
	li	$t0, 0			# inicializa $t0 = 0
	add	$t0, $t0, $s0		# $t0 = $s0
repete:	rem	$t2, $t0, 10		# $t2 = ($t0 % 10), separa o digito
	addi 	$sp, $sp, -4		# push: ajusta $sp
	sw 	$t2, 0($sp)		# push: empilha digito
	addi	$s1, $s1, 1		# digitos++
	div	$t0, $t0, 10		# $t0 = ($t0 / 10), exclui o digito
	bgtz	$t0, repete		# se ($t0 > 0) repete
	jr	$ra			# retorna


numtexto:				# imprime na tela o texto do digito em $a1
	la	$a2, n			# n[0]
	mul	$t3, $a1, 4		# i = digito*4
	addu	$a2, $a2, $t3		# array[i]
	lw	$a0, 0($a2)		# $a0 = array[i]
	li	$v0, 4			# syscall = 4: "print_string"
	syscall				# imprime o numero correspondente
	jr	$ra			# retorna


main:	
	la	$a0, aut_msg
	li	$v0, 4			# syscall = 4: "print_string"
	syscall				# mensagem: Exercicio/autores

	la	$a0, ins_msg
	li	$v0, 4			# syscall = 4: "print_string"
	syscall				# mensagem: Digite

	li 	$v0, 5			# syscall = 5: "read_int"
	syscall				# le inteiro
	move	$s0, $v0		# move o inteiro lido para $s0

	bltz 	$s0, inval		# se ($s0 < 0), vai para inval
	jal	separa			# desvia para 'separa'
	jal	load_num		# desvia para 'load_num'


imprime:	
	lw	$a1, ($sp)		# pop: desempilha digito para argumento $a1
	addi	$sp, $sp, 4		# pop: ajusta $sp
	jal	numtexto		# desvia para 'numtexto'
	addi	$s1, $s1, -1		# digitos--
	beq	$s1, 0, fim		# se ($s1 == 0) fim
	la	$a0, espaco		
	li	$v0, 4			# syscall = 4: "print_string" 
	syscall				# " "
	b	imprime			# repete print enquanto houverem digitos 


fim:	li	$v0, 10			# syscall = 10: "exit"
	syscall				# sair

# fim de ep-1.1.asm
