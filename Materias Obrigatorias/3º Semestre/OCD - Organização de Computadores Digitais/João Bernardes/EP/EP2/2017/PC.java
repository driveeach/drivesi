// pc conta e guarda o endereco da proxima instrucao
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public  class PC{
	private static String pc = "0000000000000000"; //endereco
	private static int[] portas ={1,2};
	
	public static String getPC(){
		Principal.portas[(portas[1]-1)] = 1;
		return pc;
	}
	public static void setPC(String p){
		Principal.portas[(portas[0]-1)] = 1;
		pc = p;
	}
}