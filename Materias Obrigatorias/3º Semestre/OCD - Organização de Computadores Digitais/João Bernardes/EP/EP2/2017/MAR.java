import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public class MAR{
	private static String valor = "0000000000000000"; //endereco // mar guarda enderecos pra se busca rna memoria portanto tamanho 16
	private static int[] portas ={3,20};
	public static String getMAR(){
		Principal.portas[(portas[1]-1)] = 1;
		return valor;
	}
	public static void setMAR(String a){
		Principal.portas[(portas[0]-1)] = 1;
		valor = a;
		//if(Integer.parseInt(a,2) <= 3)Principal.portas[portas[0]-1] = 0;
	}
}