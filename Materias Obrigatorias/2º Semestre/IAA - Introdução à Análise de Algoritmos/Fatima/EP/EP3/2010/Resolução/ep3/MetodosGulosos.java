package ep3;

/*********************************************************************/
/** ACH 2002 - Introducao a Ciencia da Computacao II                **/
/** EACH-USP - Segundo Semestre de 2010                             **/
/**                                                                 **/
/** <14> - <Profa. Fatima de Lourdes dos Santos Nunes Marques>      **/
/**                                                                 **/
/** Terceiro Exercicio-Programa                                     **/
/**                                                                 **/
/** <Murilo Galvao Honorio> <6411927>                               **/
/**                                                                 **/
/*********************************************************************/

/* COMENTARIOS GERAIS
 * Seguindo os criterios de selecao, um objeto so podera ser colocado na
 *   mochila caso a mochila suporte o total de peso.
 * O total de peso ao se "colocar" um objeto (do tipo Objeto) eh:
 *   mochila.getPesoUsado() + objeto.getPeso()
 * Colocar um objeto na mochila significa alterar os seguintes campos
 *   da mochila: pesoUsado, valorDentroDaMochila e numObjetosNaMochila.
 */
public abstract class MetodosGulosos
{
    static final int MENOR_PESO = 0;
    static final int MAIOR_VALOR = 1;
    static final int MAIOR_VALOR_DIVIDO_PESO = 2;

    /* Este metodo deve implementar um algoritmo guloso que selecione objetos
     *   da listaDeObjetosDisponiveis a serem colocados na mochila de acordo
     *   com o criterio: 'objetos de menor peso primeiro', caso dois objetos
     *   tenham o mesmo peso, o criterio de desempate sera:
     *   'objetos de maior valor primeiro' (apenas para os empates em peso).
     */
    public static Mochila utilizaMenorPeso(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis)
    {
        Mochila mochila = new Mochila(pesoMaximoDaMochila);
        mergeSort(listaDeObjetosDisponiveis, 0, listaDeObjetosDisponiveis.length - 1, MENOR_PESO);
        insereObjetosNaMochila(mochila, listaDeObjetosDisponiveis);
        return mochila;
    }

    /* Este metodo deve implementar um algoritmo guloso que selecione objetos
     *   da listaDeObjetosDisponiveis a serem colocados na mochila de acordo
     *   com o criterio: 'objetos de maior valor primeiro', caso dois objetos
     *   tenham o mesmo valor, o criterio de desempate sera:
     *   'objetos de menor peso primeiro' (apenas para os empates em valor).
     */
    public static Mochila utilizaMaiorValor(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis)
    {
        Mochila mochila = new Mochila(pesoMaximoDaMochila);
        mergeSort(listaDeObjetosDisponiveis, 0, listaDeObjetosDisponiveis.length - 1, MAIOR_VALOR);
        insereObjetosNaMochila(mochila, listaDeObjetosDisponiveis);
        return mochila;
    }

    /* Este metodo deve implementar um algoritmo guloso que selecione objetos
     *   da listaDeObjetosDisponiveis a serem colocados na mochila de acordo
     *   com o criterio: 'objetos de maior valor/peso primeiro (valor dividido por
     *   peso primeiro)', caso dois objetos tenham o mesmo valor/peso, o criterio
     *   de desempate sera: 'objetos de maior peso primeiro' (apenas para os empates).
     */
    public static Mochila utilizaMaiorValorDivididoPorPeso(double pesoMaximoDaMochila, Objeto[] listaDeObjetosDisponiveis)
    {
        Mochila mochila = new Mochila(pesoMaximoDaMochila);
        mergeSort(listaDeObjetosDisponiveis, 0, listaDeObjetosDisponiveis.length - 1, MAIOR_VALOR_DIVIDO_PESO);
        insereObjetosNaMochila(mochila, listaDeObjetosDisponiveis);
        return mochila;
    }

    //colocar objetos na mochila, até encher ou acabarem os objetos disponíveis
    private static void insereObjetosNaMochila(Mochila mochila, Objeto[] listaDeObjetosDisponiveis)
    {
        int i = 0;
        while (i < listaDeObjetosDisponiveis.length)
        {
            if (listaDeObjetosDisponiveis[i].getPeso() <= mochila.getPesoMaximo() - mochila.getPesoUsado())
            {
                mochila.setPesoUsado(mochila.getPesoUsado() + listaDeObjetosDisponiveis[i].getPeso());
                mochila.setValorDentroDaMochila(mochila.getValorDentroDaMochila() + listaDeObjetosDisponiveis[i].getValor());
                mochila.setNumObjetosNaMochila(mochila.getNumObjetosNaMochila() + 1);
            }
            if (mochila.getPesoMaximo() == mochila.getPesoUsado()) break;
            i++;
        }
    }

    private static void mergeSort(Objeto[] listaDeObjetosDisponiveis, int ini, int fim, int criterio)
    {
        if (ini < fim)
        {
            int meio = (ini + fim) / 2;
            mergeSort(listaDeObjetosDisponiveis, ini, meio, criterio);
            mergeSort(listaDeObjetosDisponiveis, meio + 1, fim, criterio);
            merge(listaDeObjetosDisponiveis, ini, meio, fim, criterio);
        }
    }

    private static void merge(Objeto[] listaDeObjetosDisponiveis, int inicio, int meio, int fim, int criterio)
    {

        // A subseqüência A[p...q] está ordenada
        // A subseqüência A[q+1...r] está ordenada
        int i, j, k;

        // Faz cópias - seq1 = A[p...q] e seq2 = A[q+1...r]
        int tamSeq1 = meio - inicio + 1; // tamanho da subseqüência 1
        int tamSeq2 = fim - meio; // tamanho da subseqüência 2
        Objeto[] seq1 = new Objeto[tamSeq1];
        Objeto[] seq2 = new Objeto[tamSeq2];

        for (i = 0; i < seq1.length; i++)
            seq1[i] = listaDeObjetosDisponiveis[inicio + i];

        for (j = 0; j < seq2.length; j++)
            seq2[j] = listaDeObjetosDisponiveis[meio + j + 1];

        k = inicio;
        i = 0;
        j = 0;

        // Faz a junção das duas subseqüências
        while (i < seq1.length && j < seq2.length)
        {
            switch (criterio)
            {
                case MENOR_PESO:
                {   //em caso de empate maior valor primeiro
                    if (seq2[j].getPeso() == seq1[i].getPeso()) //empate
                    {
                        // Pega o maior valor das duas seqüências
                        if (seq2[j].getValor() > seq1[i].getValor())
                        {
                            listaDeObjetosDisponiveis[k] = seq2[j];
                            j++;
                        } else
                        {
                            listaDeObjetosDisponiveis[k] = seq1[i];
                            i++;
                        }
                        k++;
                    } else
                    {
                        // Pega o menor peso das duas seqüências
                        if (seq2[j].getPeso() < seq1[i].getPeso())
                        {
                            listaDeObjetosDisponiveis[k] = seq2[j];
                            j++;
                        } else
                        {
                            listaDeObjetosDisponiveis[k] = seq1[i];
                            i++;
                        }
                        k++;
                    }
                    break;
                }
                case MAIOR_VALOR:
                {   //em caso de empate menor peso primeiro
                    if (seq2[j].getValor() == seq1[i].getValor()) //empate
                    {
                        // Pega o menor peso das duas seqüências
                        if (seq2[j].getPeso() < seq1[i].getPeso())
                        {
                            listaDeObjetosDisponiveis[k] = seq2[j];
                            j++;
                        } else
                        {
                            listaDeObjetosDisponiveis[k] = seq1[i];
                            i++;
                        }
                        k++;
                    } else
                    {
                        // Pega o maior valor das duas seqüências
                        if (seq2[j].getValor() > seq1[i].getValor())
                        {
                            listaDeObjetosDisponiveis[k] = seq2[j];
                            j++;
                        } else
                        {
                            listaDeObjetosDisponiveis[k] = seq1[i];
                            i++;
                        }
                        k++;
                    }
                    break;
                }
                case MAIOR_VALOR_DIVIDO_PESO:
                {   //em caso de empate maior peso primeiro
                    if (seq2[j].getValor() / seq2[j].getPeso() == seq1[i].getValor() / seq1[i].getPeso()) //empate
                    {
                        // Pega o maior peso das duas seqüências
                        if (seq2[j].getPeso() > seq1[i].getPeso())
                        {
                            listaDeObjetosDisponiveis[k] = seq2[j];
                            j++;
                        } else
                        {
                            listaDeObjetosDisponiveis[k] = seq1[i];
                            i++;
                        }
                        k++;
                    } else
                    {
                        // Pega o maior valor das duas seqüências
                        if (seq2[j].getValor() / seq2[j].getPeso() > seq1[i].getValor() / seq1[i].getPeso())
                        {
                            listaDeObjetosDisponiveis[k] = seq2[j];
                            j++;
                        } else
                        {
                            listaDeObjetosDisponiveis[k] = seq1[i];
                            i++;
                        }
                        k++;
                    }
                    break;
                }
            }
        }
        // Completa com a seqüência que ainda não acabou
        while (i < seq1.length)
        {
            listaDeObjetosDisponiveis[k] = seq1[i];
            k++;
            i++;
        }
        while (j < seq2.length)
        {
            listaDeObjetosDisponiveis[k] = seq2[j];
            k++;
            j++;
        }
    }
}
