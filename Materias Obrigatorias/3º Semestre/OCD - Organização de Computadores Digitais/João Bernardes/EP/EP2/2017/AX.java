import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public class AX{
	private static String ax = "0000000000000000";
	public static int[] portas = {6,7}; //entrada e saida
	public static String getAX(){
		Principal.portas[(portas[1]-1)] = 1;
		return ax;
	}
	public static void setAX(String a){
		Principal.portas[(portas[0]-1)] = 1;
		ax = a;
	}
}
