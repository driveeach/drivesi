//autoexplicativo <3
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
public class Memoria{
	public static int fimcodigo = 0;
	public  static String[] mem = new String[65536]; //string de tamanho 37 <3 pq a instrucao eh gigante assim
	private  static int[] portas ={23,24};
	public static void getMemoria() {
		Principal.portas[(portas[1]-1)] = 1;
		MBR.setMEMtoMBR(mem[Integer.parseInt(MAR.getMAR(),2)]);// poe no buffer da memoria(MBR)o q esta no endereco MAR
		//if(Integer.parseInt(MAR.getMAR(),2)<= 4)Principal.portas[portas[1]-1] = 0; // nao fazemos chamadas a memoria para registradores
	}
	public static void setMemoria(){
		Principal.portas[(portas[0]-1)] = 1;
		mem[Integer.parseInt(MAR.getMAR(),2)] = MBR.getMBRtoMEM(); //memoria na posicao MAR recebe MBR
		fimcodigo++;
		//if(Integer.parseInt(MAR.getMAR(),2)<= 4)Principal.portas[portas[1]-1] = 0;
	}
}