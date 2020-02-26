import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
/*
t1:     MAR <- PC
        Memória <- fetch;
t2:     MBR <- ‘A = B * C’
        PC <- PC + 1
t3:     IR <- MBR


microcodigo horizontal = PORTAS+FLAGS+OPCODE+ARG1+ARG2+ENDMEMORIA
*/
public class Principal {
	public static AX axx = new AX();
	public static BX bxx = new BX();
    public static CX cxx = new CX();
	public static DX dxx = new DX();
	public static PC pcc = new PC();
	public static IR irr = new IR();
	public static MBR mbrr = new MBR();
	public static MAR marr = new MAR();
	public static ULA ulaa = new ULA();
	public static Memoria memoriaa = new Memoria();
    public static int[] portas = new int[26];
	public static String[] array = new String[]{ "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010",
    "01011", "01100", "01101", "01110", "01111", "10000", "10001" };
	public static String[] relacao = { "MOV", "ADD", "SUB", "MUL", "DIV", "INC", "DEC", "CMP", "JMP", "JE ", "JNE", "JG ", "JL ",
    "JGE", "JLE", "AND", "OR " }; 
	public static String[] registradores = new String[]{"0000000000000002","0000000000000003","0000000000000004","0000000000000005"};
    public static void main(String[] args) throws IOException{
        // PC Settado pra primeira linha do codigo na memoria
        // "stack" do codigo cresce de cima pra baixo, stack das variaveis
        // cresce de baixo pra cima, logo primeira linha do codigo esta em
        // memoria[0] e a primeira var ta em memoria[memoria.length]
        // le_codigo_do_arquivo(); //metodo magico q n implementei, le o codigo
        // do arq e salva na memoria, adcionar operacao EXIT no fim do codigo se
        int count = 0;
        // while !exit
		//pc comeca do 4 pois os registradores estao nas 4 primeiras posicoes de memoria
		zeraportas();
		le_codigo_do_arquivo();
		String iere = "0";
		System.out.println("PALAVRA = OPCODE(5bits)+ARG1(16bits)+ARG2(16bits)");
        while (iere != null) {
           marr.setMAR(pcc.getPC());
		   memoriaa.getMemoria(); // fetch // apos isso ,mbr ja esta setada com o valor da posicao MAR da memoria <3
		   irr.setIR(mbrr.getMBRfromUC());
		    iere = irr.getIR();
		   for(int i = 0 ; i < array.length ; i++)System.out.println("OPCODE: "+array[i]+" EH IGUAL A OPERACAO: "+relacao[i]);
		   if(iere == null) break;
		   System.out.println("Operacao em binario(IR) "+IR.getIR());
		  if(iere.equalsIgnoreCase("EXIT")) break;
		   String[] retorno = new String[3];
		   //System.out.println("IEEEEEREEEE "+iere);
		   retorno = ulaa.executa(iere); // executamos finalmente
		   pcc.setPC(incrementaPC(pcc.getPC()));
		   System.out.println("Overflow flag(of): "+retorno[1]);
		   System.out.println("Zero flag(zf): "+retorno[2]);
		   System.out.println("Signal flag = o primeiro bit de cada numero");
		   System.out.println("PC "+pcc.getPC());
		   System.out.print("PALAVRA HORIZONTAL ");
		   imprimeportas();
		   //System.out.print(iere);
		   System.out.print(retorno[1]);
           System.out.println(retorno[2]);		   /*
		   professor, os numeros 2, 3 ,4 ,5 sao para indicar enderecos de registradores,eu poderia colocar os registradores no final da memoria, em algum endereco reservado, mas 
		   achei mais simples assim, visto que eh algo da nossa 'arquitetura', cada hardware tem seus trejeitos 
		   */
		   System.out.print("ESTADO DAS PORTAS");
		    //pc++ <3
		   imprimeportas();
		   System.out.println();
		   System.out.println("Registradores");
		   System.out.println("AX: "+axx.getAX()); // apesar de darmos get direto aqui, para a ULA que executa operacoes os regs vao de 0 a 3 na memoria
		   System.out.println("BX: "+bxx.getBX());
		   System.out.println("CX: "+cxx.getCX());
		   System.out.println("DX: "+dxx.getDX());
		   zeraportas();
		   System.in.read();
        }
        System.out.println("Fim da execucao");
    }
    
   // private static void cicloDeBusca(int pc2) {
        // TODO Auto-generated method stub
        
  //  }
    
    public static void imprime(boolean[] a) { // autoexplicativo
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] ? "1 " : "0 ");
        }
        System.out.println();
    }
    
    public static void le_codigo_do_arquivo() {
        try {
            // Lê o arquivo
            BufferedReader reader = new BufferedReader(new FileReader("codigo.txt"));
            String line;
            line = reader.readLine();
            int a = 0;
            while (line != null) {
				String opcode = traduz(line.substring(0,3));
				String novaL = line.substring(4,line.length());
				String[] args = novaL.split(",");
				if(line.length()<= 4){
				novaL = line;
				args = novaL.split("\\s");
				args[0] = args[1];
				args[1] = "0000000000000000";
				}
				String arg1 = to16Binary(args[0]);
				String arg2 = "0000000000000000";
				if(args.length > 1) arg2 = to16Binary(args[1]);
				memoriaa.mem[a] = opcode+arg1+arg2;
			//	System.out.println(opcode);
                a++;
                line = reader.readLine();
            }
            memoriaa.mem[a] = "EXIT";
        } catch (Exception e) {
		};
        
    }
	public static String traduz(String s){
		//String[] palavras = new String[]{"ADD","SUB","MUL","DIV","INC","DEC","CMP","JMP","JE ","JNE","JG ","JL ","JGE","JLE","and","or "}; // vlw java ate pq uma String com "ADD" dentro nao eh igual a "ADD" mesmo eu criando ela com = "ADD"
		for(int i = 0 ; i < 18 ; i++){ // array vai ate 17
			if(s.equalsIgnoreCase(relacao[i])) {
				return (array[i]);
			}
		}
		return null;
		
	}
	public static String to16Binary(String s){
		int valor = 0;
		String[] regs = new String[]{"AX","BX","CX","DX"};
		String add = "00000000000000";
			if (s.equalsIgnoreCase(regs[0])){
			add = registradores[0]; // os regs tem q ser um end invalido e diferente de tudo
			return(add);
			}
			if(s.equalsIgnoreCase(regs[1])){
			add=registradores[1];
			return(add);
			}
			if(s.equalsIgnoreCase(regs[2])){
			add=registradores[2];
			return(add);
			}
			if(s.equalsIgnoreCase(regs[3])){
			add=registradores[3];
			return(add);
			}
		valor = Integer.parseInt(s);
	    s = Integer.toBinaryString(valor);
		s = String.format("%16s",s).replace(' ', '0');
		return(s);
	}
	public static void zeraportas(){
		for(int i = 0 ; i < portas.length ; i++)portas[i] = 0;
	}
	public static void imprimeportas(){
		for(int i = 0 ; i < portas.length ; i++)System.out.print(portas[i]);
	}
	public static int isRegistrador(String a){
			for(int i = 0 ; i < registradores.length ; i++){
				if(a.equalsIgnoreCase(registradores[i])) {	
				return i;
				}
			}
		return -1;
	}
	public static String incrementaPC(String pc){
		String inv = "1111111111111111"; //-1 n funfa aqui
		if(pc.equalsIgnoreCase(inv)) {
			pc = "0000000000000000";
			return(pc);
			}
		int valor = (Integer.parseInt(pc,2)+1);
		pc = Integer.toBinaryString(valor);
		pc = String.format("%16s",pc).replace(' ', '0');
		return(pc);
	}
}

/*
if(opcode == "00001") mov(s.substring(5,20), s.substring(21,36)); // decide que bagaca de OP realizar e passar os args pra frente
		if(opcode == "00010") return add(s.substring(5,20), s.substring(21,36));
		if(opcode == "00011") return sub(s.substring(5,20), s.substring(21,36));
		if(opcode == "00100") return mul(s.substring(5,20), s.substring(21,36));
		if(opcode == "00101") return div(s.substring(5,20), s.substring(21,36));
		if(opcode == "00110") return inc(s.substring(5,20), s.substring(21,36));
		if(opcode == "00111") return dec(s.substring(5,20), s.substring(21,36));
		if(opcode == "01000") return cmp(s.substring(5,20), s.substring(21,36)); // compare tem q setar um valor pros jumps, tipo CMP AX,42 
		if(opcode == "01001") jmp(s.substring(5,20), s.substring(21,36));//                                                       JE ENDERECO
		if(opcode == "01010") je(s.substring(5,20), s.substring(21,36));
		if(opcode == "01011") jne(s.substring(5,20), s.substring(21,36));
		if(opcode == "01100") jg(s.substring(5,20), s.substring(21,36));
		if(opcode == "01101") jl(s.substring(5,20), s.substring(21,36));
		if(opcode == "01110") jge(s.substring(5,20), s.substring(21,36));
		if(opcode == "01111") jle(s.substring(5,20), s.substring(21,36));
		if(opcode == "10000") return and(s.substring(5,20), s.substring(21,36)); // sei la comofaz
		if(opcode == "10001") return or(s.substring(5,20), s.substring(21,36)); 
*/