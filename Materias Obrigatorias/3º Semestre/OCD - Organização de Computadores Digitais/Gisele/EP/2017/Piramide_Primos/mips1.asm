.text

main:

	li $v0, 5
	syscall
	jal verificapiramide
	
	li $t9, 0	#proxBlocoDeveSer
	li $t8, 0	#j
	li $t7, 0	#i do for
	la $t6, ($v0)	# blocos a ler

loopMain:
	bge $t7, $t6, printaJeSai
	li $t5, 1	#numeros no bloco, inicia em 1 pra somar j na linha de baixo
	add $t5, $t5, $t8	# coloca no $t5 / numeros no bloco o valor j + 1
	addi $t8, $t8, 1	# j++
	
	la $a0, ($t5)		#Argumento de bloco: numero no blocos
	la $s2 ($a0)
	jal bloco		#entrando em bloco com argumento #a0
	#Resposta do lboco no $s0
	beq $s0, -1, falsoPiramide
	
	la $a0, ($t5)
	beqz $t7, inverteBit		#proxBlocoDeveSer = inverteBit(blocoTotal)
	bne $s0, $t9, falsoPiramide
	j inverteBit		#proxBlocoDeveSer = inverteBit(blocoTotal)
	
	
#Inverte o valor binario de $s0	
inverteBit:
	mul $t9, $s0, -1
	addi $t9, $t9, 1
	add $t7, $t7, $s2
#	add $t7, $t7, ($s2)
	j loopMain
		

printaJeSai:
	la $a0, ($t8)
	li $v0, 1
	syscall
	li $v0, 10
	syscall	

#coloca no $s0 o valor 1, 0, ou -1
#puxa do valor a0
bloco:
	#a0 == n
	la $a1, ($a0)	# a1 == n
	li $t4, 2	#TOTAL
loopBloco:
	blez $a1, voltaMain 
	subi $a1, $a1, 1
	li $v0, 5
	syscall		# V0 = next
	
	
	beq $t4, 2, ifTotalEDois
	beq $t4, 1, totalEUm
	beq $t4, 0, totalEZero
	
	
ifTotalEDois:		#if total ==2
	rem $t4, $v0, 2
	j loopBloco
totalEUm:
	rem $t3, $v0, 2
	bnez $t3, loopBloco
	j falsoPiramide
totalEZero:
	rem $t3, $v0, 2
	beqz $t3, loopBloco
	j falsoPiramide	
voltaMain:
	la $s0, ($t4)
	jr $ra

#Verifica numero inserido, e volta caso seja possivel piramidar
#caso nao seja possivel encerra com 'nao'
verificapiramide:
	li $t1, 0	#j do for
	
	la $t0, ($v0)	#elementos = valor lido
	la $t2, ($t0)	#i = elementos
looppiramide:
	bge $t1, $t0, falsoPiramide
	sub $t2, $t2, 1
	sub $t2, $t2, $t1
	beqz $t2, verdadeiroPiramide
	addi $t1, $t1, 1
	j looppiramide
	
verdadeiroPiramide:
	jr $ra
falsoPiramide:		
	li $v1, 0
	la $a0, $nao
	li $v0, 4
	syscall
	li $v0, 10
	syscall
teste:
	li $v1, 0
	la $a0, $sim
	li $v0, 4
	syscall
	li $v0, 10
	syscall	
.data
$nao:
	.asciiz "não"	
$sim:
	.asciiz "sim"		
