//Problema 892 - Finding Words - 8/5/2011
//Grupo 7: 6777450, 6777255, 6777724, 6411927
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        executa();
    }

    //remove os caracteres inválidos da palavra conforme a definição do problema
    private static String limpar(String entrada) {
        String saida = "";
        //percorre a palavra e copia para a saida os caracteres válidos: espaco, a-z, A-Z
        for (int i = 0; i < entrada.length(); i++) {
            char atual = entrada.charAt(i);
            //valores decimais dos códigos ascii: 32=' ', 65='A', 90='Z', 97='a', 122='z'
            if (atual == 32 || (atual >= 65 && atual <= 90) || (atual >= 97 && atual <= 122)) {
                saida += entrada.charAt(i);
            }
        }
        return saida;
    }

    private static void executa() {
        Scanner sc = new Scanner(System.in);
        String palavraComHifen = "";
        String entrada = "";
        String saida = "";

        while (sc.hasNextLine()) {

            //lê uma linha
            entrada = sc.nextLine();
            saida = "";

            //verifica se é o código de fim de texto e encerra
            if (entrada.length() == 1 && entrada.charAt(0) == '#') {
                return;
            }

            //concatena a palavra hifenizada
            if (palavraComHifen.length() > 0) {
                int i = 0;
                while (entrada.charAt(i) != ' ') {
                    i++;
                }
                //acrescenta parte restante da palavra hifenizada
                palavraComHifen += entrada.substring(0, i);
                //remove os caracteres invalidos da palavra
                palavraComHifen = limpar(palavraComHifen);
                //imprime a palavra concatenada
                System.out.println(palavraComHifen);
                palavraComHifen = "";

                //remove da entrada a palavra utilizada
                entrada = entrada.substring(i);
            }

            //verifica se a linha termina com hífen
            if (entrada.endsWith("-")) {
                int i = entrada.length() - 1;
                while (entrada.charAt(i) != ' ') {
                    i--;
                }
                //le e remove da entrada a primeira parte da palavra hifenizada, atenção com o símbolo '-'
                palavraComHifen = entrada.substring(i + 1, entrada.length() - 1);
                entrada = entrada.substring(0, i + 1);
            }

            saida = limpar(entrada);
            System.out.println(saida);
        }
    }
}