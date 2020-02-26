import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
public class ULA{
	private static int[] portas = {17,18,19} ;//17 = entrada args , 18 = entrada operacao , 19 = saida
	private static boolean isEqual = false;
	private static boolean isGreater = false;
	private static boolean isLower = false;
	public static String[] executa(String s){
		Principal.portas[(portas[0]-1)] = 1;
		Principal.portas[(portas[1]-1)] = 1;
		Principal.portas[(portas[2]-1)] = 1;
		String opcode = s.substring(0,5); //5 primeiros bits; // mov vai ser feito na ULA msm e foda-se // sapora vai de (n,x-1) maldito substring
		int aa = Principal.isRegistrador(s.substring(5,21));
		int bb = Principal.isRegistrador(s.substring(21,37));
		String a = s.substring(5,21);
		String b = s.substring(21,37);
		String zero = "0";
		String um = "1";
		boolean aisReg = false;
		boolean bisReg = false;
		String[] resp = new String[3];
		resp[0] = "0";
		resp[1] = "0";
		resp[2] = "0";
		if(aa != -1){ // quer dizer q eh um reg
			aisReg = true;
			if(aa == 0)a = Principal.axx.getAX();
			if(aa == 1)a = Principal.bxx.getBX();
			if(aa == 2)a = Principal.cxx.getCX();
			if(aa == 3)a = Principal.dxx.getDX();

		}
		if( bb!= -1){ // quer dizer q eh um reg
			bisReg = true;
			if(bb == 0)b = Principal.axx.getAX();
			if(bb == 1)b = Principal.bxx.getBX();
			if(bb == 2)b = Principal.cxx.getCX();
			if(bb == 3)b = Principal.dxx.getDX();
		}// daqui pra baixo soh temos numeros, nada de registradores, apenas valores
		if(opcode.equalsIgnoreCase(Principal.array[0])){ //mov
			if(aa == 0){

				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.axx.setAX(b);
				}
			if(aa == 1){
				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.bxx.setBX(b);
				}
			if(aa == 2){
				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.cxx.setCX(b);
			}
			if(aa == 3){
				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.dxx.setDX(b);
			}
			//boolean[] fake =new boolean[]{true,false,false};
			return(resp);
		}
		if(opcode.equalsIgnoreCase(Principal.array[1])){ //add
			resp = add(a, b);

		}
		if(opcode.equalsIgnoreCase(Principal.array[2])){ //sub
			resp = sub(a, b);
		}


		if(opcode.equalsIgnoreCase(Principal.array[3])){ //MUL
			resp = mul(a, b);
		}
		if(opcode.equalsIgnoreCase(Principal.array[4])){ //div
			resp = div(a,b);
		}
		if(opcode.equalsIgnoreCase(Principal.array[5])){
			resp = inc(a,b);
		}//INC
		if(opcode.equalsIgnoreCase(Principal.array[6])){
			resp = dec(a,b);
		}//DEC
		if(opcode.equalsIgnoreCase(Principal.array[7])){
			cmp(a,b);
			return(resp);
		}//CMP
		if(opcode.equalsIgnoreCase(Principal.array[8])){ //JUMP // jmp e mov sao os unicos com ret
			jmp(a,b);
			return(resp);
		}
		if(opcode.equalsIgnoreCase(Principal.array[9])){
			je(a,b);
			return(resp);
		}//JE
		if(opcode.equalsIgnoreCase(Principal.array[10])){
			jne(a,b);
			return(resp);
		}//JNE
		if(opcode.equalsIgnoreCase(Principal.array[11])){
			jg(a,b);
			return(resp);
		}//JG
		if(opcode.equalsIgnoreCase(Principal.array[12])){
			jl(a,b);
			return(resp);
		}//JL
		if(opcode.equalsIgnoreCase(Principal.array[13])){
			jge(a,b);
			return(resp);
		}//JGE
		if(opcode.equalsIgnoreCase(Principal.array[14])){
			jle(a,b);
			//boolean[] fake =new boolean[]{true,false,false};
			return(resp);
		}//JLE
		if(opcode.equalsIgnoreCase(Principal.array[15])){
		boolean x = and(a,b);
		if(x)resp[0] = "1";
			return(resp);
		}//&&
		if(opcode.equalsIgnoreCase(Principal.array[16])){
			boolean x = or(a,b);
		if(x)resp[0] = "1";
			return(resp);
		}//or

		if(aisReg){
			if(aa == 0){

				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.axx.setAX(b);
				}
			if(aa == 1){
				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.bxx.setBX(b);
				}
			if(aa == 2){
				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.cxx.setCX(b);
			}
			if(aa == 3){
				if(bb == 0)b = Principal.axx.getAX();
				if(bb == 1)b = Principal.bxx.getBX();
				if(bb == 2)b = Principal.cxx.getCX();
				if(bb == 3)b = Principal.dxx.getDX();
				Principal.dxx.setDX(b);
			}

		}
		if((aisReg == true)&& (bisReg == false)) {
				if(aa == 0){
					Principal.axx.setAX(resp[0]);
				}
					if(aa == 1){
						Principal.bxx.setBX(resp[0]);
					}
						if(aa == 2){
							Principal.cxx.setCX(resp[0]);
						}
							if(aa == 3){
								Principal.dxx.setDX(resp[0]);
							}
			}
		return(resp);
	}//faz a div,o dec e o resto dos jumps, ja fiz os primeiros entao ja sabem a logica
	private static void jmp(String a , String b){ // se tiver uma condicao, ela vai estar em b
		int valor = (Integer.parseInt(a,2)-1); //-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		PC.setPC(a);
	}
	private static void je(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		//-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		//if(isEqual == true)
		if(isEqual == true)	PC.setPC(a);
		resetaflags();
	}
	public static void jne(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		//-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		if(isEqual == false)PC.setPC(a);
		resetaflags();
	}
	public static void jg(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		//-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		a = String.format("%16s",a).replace(' ', '0');
		if(isGreater == true)PC.setPC(a);
		resetaflags();
	}
	public static void jl(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		//-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		//a = a.substring((a.length()/2),a.length());
		a = String.format("%16s",a).replace(' ', '0');
		if(isLower == true)PC.setPC(a);
		resetaflags();
	}
	public static void jge(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		//-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		a = a.substring((a.length()/2),a.length());
		if((isEqual == true) || (isGreater == true))PC.setPC(a);
		resetaflags();
	}
	public static void jle(String a , String b){
		int valor = (Integer.parseInt(a,2)-1);		//-1 pq o pc vai ser incrementado
		a = Integer.toBinaryString(valor);
		a = a.substring((a.length()/2),a.length());
		if((isEqual == true) || (isLower == true))PC.setPC(a);
		resetaflags();
	}
	public static boolean and(String a , String b){
		if((a != null) && (b != null)) return true;
		return false;
	}
	public static boolean or(String a , String b){
		if((a!= null) || (b != null)) return true;
		return false;
	}
	private static String[] inc(String a , String b){
			int va = Integer.parseInt(a,2);
			//int vb = Integer.parseInt(b);
			int soma = va+1;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}
	private static String[] dec(String a , String b){
			int va = Integer.parseInt(a,2);
			//int vb = Integer.parseInt(b);
			int soma = va-1;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}

	private static String[] mul(String a , String b){
			int va = Integer.parseInt(a,2);
			int vb = Integer.parseInt(b,2);
			int soma = va*vb;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}
	private static String[] div(String a , String b){
		int va = Integer.parseInt(a,2);
			int vb = Integer.parseInt(b,2);
			int soma = va/vb;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}
	private static String[] sub(String a , String b){ //sub esta errada n sei pq
		int va = Integer.parseInt(a,2);
			int vb = Integer.parseInt(b,2);
			int soma = va-vb;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}
	private static String[] add(String a , String b){
			int va = Integer.parseInt(a,2);
			int vb = Integer.parseInt(b,2);
			int soma = va+vb;
			int carry = 0;
			int zero = 0;
			if(soma > 32767) carry = 1;
			if(soma == 0) zero = 1;
			String res = Integer.toBinaryString(soma);
			if(soma < 0){
			res = res.substring(25,32);
			res = "1"+res.substring(0,(res.length()-1));
			}
			else res = String.format("%16s",res).replace(' ', '0');
		//	res = res.substring((res.length()/2),res.length());
			String[] resposta = new String[3];
			resposta[0] = res;
			resposta[1] = Integer.toString(carry);
			resposta[2] = Integer.toString(zero);
			return(resposta);
	}
	private static void cmp(String a, String b){
		int va = Integer.parseInt(a,2);
		int vb = Integer.parseInt(b,2);
		if(va == vb) isEqual = true;
		if(va > vb)isGreater = true;
		if(va < vb)isLower = true;
	}
	private static void resetaflags(){
	isEqual = false;
	isGreater = false;
	isLower = false;
	}
	}
//// inst = 5 bits do opcode+16bits do arg1+16bits do arg2 = 37bits
/*
 static String[] opCode = { "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010",
    "01011", "01100", "01101", "01110", "01111", "10000", "10001" };
    static String[] line = { "MOV", "ADD", "SUB", "MUL", "DIV", "INC", "DEC", "CMP", "JMP", "JE ", "JNE", "JG ", "JL ",
    "JGE", "JLE", "&", "|" }; // nao tira os espacos pfvr
	*/
