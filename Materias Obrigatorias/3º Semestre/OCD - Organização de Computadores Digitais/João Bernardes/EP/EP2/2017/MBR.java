//MBR guarda oq "saiu" da memoria
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public class MBR{
	private static String valor = "0000000000000000000000000000000000000"; //tamanho 37 pois eh uma instrucao
	private static int[] portastoMEM ={21,22};
	private static int[] portastoUC ={4,5};
	public static String getMBRfromUC(){
		Principal.portas[(portastoUC[1]-1)] = 1;
		return valor;
	}
	public static void setMBRfromUC(String a){
		Principal.portas[(portastoUC[0]-1)] = 1;
		valor = a;
	}
	public static String getMBRtoMEM(){
		Principal.portas[(portastoMEM[0]-1)] = 1;
		return valor;
		}
	public static void setMEMtoMBR(String a){
		Principal.portas[(portastoMEM[1]-1)] = 1;
		valor = a;
	}
}