//ir guarda a instrucao que foi buscada na memoria
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public  class IR {
	private static String inst = "0000000000000000000000000000000000000"; //endereco // inst = 5 bits do opcode+16bits do arg1+16bits do arg2 = 37bits
	private static int[] portas ={12,14,16,13,15}; //13 e 15 saida
	public static String getIR(){
		Principal.portas[(portas[3]-1)] = 1;
		Principal.portas[(portas[4]-1)] = 1;
		return inst;
	}
	public static void setIR(String ins){
		Principal.portas[(portas[0]-1)] = 1;
		Principal.portas[(portas[1]-1)] = 1;
		Principal.portas[(portas[2]-1)] = 1;
		inst = ins;
	}

}
