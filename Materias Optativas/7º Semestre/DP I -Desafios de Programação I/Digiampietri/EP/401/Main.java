import java.util.Scanner;

class Main {

    //401 PALIDROMES
    //obs.: ZERO nao eh valido como entrada
    //vetor de correspondencias dos caracteres reversos de 1 a Z
    static final char[] reverso = {'1','S','E',0,'Z',0,0,'8',0,0,0,0,0,0,0,0,'A',0,0,0,'3',0,0,'H','I','L',0,'J','M',0,'O',0,0,0,'2','T','U','V','W','X','Y','5'};

    public static void main(String[] args)
    {
        Scanner scan = new Scanner (System.in);
        String linha;
   		while (scan.hasNext()){
	    		linha = scan.next();
	       		executar(linha);
   		}
    }

    static void executar(String linha){
        String resposta = null;
        char[] l = linha.toCharArray();
        int i = 0;
        int j = linha.length()-1;
        boolean regular = true; //flags iniciam em true para a primeira iteracao
        boolean espelhado = true;

        //percorre com i pela esquerda e j pela direita
        while (i<=j && (regular == true || espelhado == true))
        {
            if (regular){
                if (l[i] != l[j]) regular = false;
            }
            if (espelhado){
                //l[i]-49 obtem o indice referente ao reverso do caractere i
                if (reverso[l[i]-49] != l[j]) espelhado = false;
            }
            i++;
            j--;
        }

        if (regular == false && espelhado == false) resposta = linha + " -- is not a palindrome.";
        else if (regular == true && espelhado == false) resposta = linha + " -- is a regular palindrome.";
        else if (regular == false && espelhado == true) resposta = linha + " -- is a mirrored string.";
        else resposta = linha + " -- is a mirrored palindrome.";
        System.out.printf("%s\n\n", resposta); //a resposta deve ser separada por uma linha
    }
}