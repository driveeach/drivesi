
/**
 * A classe Estudante representa um estudante em um sistema acadêmico.
 * <p>
 * Ele armazena os detalhes relevantes do estudante nesse contexto.
 * <p>
 * @author Extraido do Livro: "Programação Orientada a Objetos com Java - Uma introdução prática usando o BLUEJ
 * 
 * @version 13.03.2008
 */
public class Estudante
{
    // O nome do estudante
    private String nome;
    // O nroUSP do estudante
    private int nroUSP;
    // O curso que cursa
    private String curso;
    // A quantidade de creditos restantes para se formar
    private int credits;

    /**
     * Cria um novo estudante (nome e nroUSP).
     */
    public Estudante(int nro, String valor) {
        nome = valor;
        nroUSP = nro;
        credits = 0;
    }

    /**
     * Retorna o nome do estudante.
     */
    public String getName() {
        return nome;
    }

    /**
     * Atualiza o nome do estudante.
     */
    public void changeName(String replacementName) {
        nome = replacementName;
    }

    /**
     * Return o NroUSP desse estudante.
     */
    public int getStudentNroUSP() {
        return nroUSP;
    }

    /**
     * Adicione alguns créditos para o número de créditos acumulados (cursados) do estudante.
     */
    public void addCredits(int additionalPoints) {
        credits += additionalPoints;
    }

    /**
     * Retorne o número de créditos acumulados (cursados) do estudante.
     */
    public int getCredits() {
        return credits;
    }
 
    /**
     * Imprima o nroUSP e o nome do estudante no terminal.
     */
    public void print() {
        System.out.println("(" + nroUSP + ")" + nome);
    }
}
