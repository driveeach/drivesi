import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public class CX{
	private static String cx = "0000000000000000";
	public static int[] portas = {10,11}; //entrada e saida
	public  static String getCX(){
		Principal.portas[(portas[1]-1)] = 1;
		return cx;
	}
	public static void setCX(String x){
		Principal.portas[(portas[0]-1)] = 1;
		cx = x;
	}
}