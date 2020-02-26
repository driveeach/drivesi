.text
.globl main
main:
	la $a0, $aviso #armazena a mensagem
	li $v0, 4	#chama o print_String
	syscall	 #imprime a mensagem
	jal fator #chama a subrotina
      	j exit
               
imprime: 
	la $a0, $numZero #armazena a mensagem
	li $v0, 4	#chama o print_String
	syscall	 #imprime a mensagem
	j exit
	
imprime2:
	la $a0, $numUm #armazena a mensagem
	li $v0, 4	#chama o print_String
	syscall	 #imprime a mensagem
	j exit

                                 
fator: 
	li $v0, 5 # Serviço 5 : lê inteiro
	syscall

	sw $v0, $numero
	
	ld $a1, $numero #guardando o número no registrador a1
	beq $a1, 0, imprime #se o número for igual a zero, chama a subrotina imprime
	beq $a1, 1, imprime2 #se o número for igual a zero, chama a subrotina imprime
	
loop1: 
	li $a1, 1
	ld $a2, $aux
	add $a3, $a2, $a1
	sw $a3, $aux
	
	ld $a1, $aux
	ld $a2, $numero #guardando o número no registrador a2
	
	bgt $a1, $a2, exit
	
loop2:
	ld $a1, $aux
	ld $a2, $numero #guardando o número no registrador a2
	rem $a0, $a2, $a1
	bnez $a0, loop1
	ld $a0, $aux
	li $v0, 1
	syscall
	la $a0, $espaco
	li $v0, 4
	syscall
	ld $a1, $numero
	ld $a2, $aux
	divu $a3, $a1, $a2
	sw $a3, $numero
	j loop2

exit:

.data
$numero: .word 0
$aux: .word 1
$numZero: .asciiz "Numero zero"
$numUm: .asciiz "Número um"

$espaco: .asciiz " "
$aviso: .asciiz "Digite um numero:"
