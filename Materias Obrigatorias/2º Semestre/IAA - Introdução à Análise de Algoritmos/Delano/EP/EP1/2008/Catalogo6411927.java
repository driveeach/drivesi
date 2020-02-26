/********************************************************/
/** ACH 2002 - Introducao a Ciencia da Computacao II    */
/** EACH-USP - Segundo Semestre de 2008                 */
/** Turma 02 Matutino - Prof. Delano Medeiros Beder     */
/**                                                     */
/** Primeiro Exercício-Programa                         */
/** Arquivo: Catalogo6411927.java                       */
/**                                                     */
/** Murilo Galvão Honório          Numero USP: 6411927  */
/**                                                     */
/** Entrega: 15/09/2008                                 */
/**                                                     */
/********************************************************/


abstract class Midia implements java.io.Serializable
{ 
    private String titulo;
    private int anoCriacao;
    
    
    Midia (String titulo, int anoCriacao)
    { 
        this.titulo = titulo;
        this.anoCriacao = anoCriacao;
    } 
    
    
    abstract int getTipo();
    
    
    public String getTitulo()
    {
        return this.titulo;
    }
    
    
    public int getanoCriacao()
    {
        return this.anoCriacao;
    }
    

    public void imprimeFicha()
    {
        System.out.println("____________________________________________________________");
        System.out.println("Título: " + titulo);
        System.out.println("Ano: " + anoCriacao);
    }
}




class CD extends Midia implements java.io.Serializable
{
    private String artista;
    private int ultimaFaixa = 0;
    private final int maximoDeFaixas = 15;
    private Faixa[] lista = new Faixa[maximoDeFaixas];
 
    
    CD(String titulo, int anoCriacao, String artista)
    {
        super(titulo, anoCriacao);
        this.artista = artista;
    }
 
    
    public void adicionaFaixa(String faixa, int duracao)
    {
        if (ultimaFaixa < maximoDeFaixas)
        {
            this.lista[ultimaFaixa] = new Faixa(faixa, duracao);
            ultimaFaixa++;
        }
        else
            System.out.println("Operação não realizada. O número máximo de 15 faixas já foi inserido.");
    }
 
    
    public int getTipo()
    {
        return 1;
    }

    
    public void imprimeFicha()
    {
        super.imprimeFicha();
        System.out.println("Tipo: CD de música");
        System.out.println("Artista: " + this.artista);
        if (ultimaFaixa > 0)
        {
            for (int i = 0; i < ultimaFaixa; i++)
            {
                System.out.print("Faixa " + (i+1) + ": ");
                lista[i].imprimeFaixa();
            }
        }
        System.out.println("____________________________________________________________");
    }
}




class Faixa implements java.io.Serializable //coleção de faixas de cada objeto CD
{
    private String faixa;
    private int duracao;

    
    Faixa(String faixa,int duracao)
    {
        this.faixa = faixa;
        this.duracao = duracao;
    }

    
    void imprimeFaixa()
    {
        System.out.print(faixa + ", duração: ");
        if (duracao%60 > 10)
            System.out.println((duracao/60) + ":" + (duracao%60));
        else
            System.out.println((duracao/60) + ":0" + (duracao%60));
    }
    
}




class DVD extends Midia implements java.io.Serializable
{
    private String diretor;
    private int totalDeAtores = 0;
    private final int maximoDeAtoresPrincipais = 5;
    private String[][] atoresPrincipais = new String[5][5];
    
    
    DVD(String titulo, int anoCriacao, String diretor)
    {
        super(titulo, anoCriacao);
        this.diretor = diretor;
    }
 
    
    public void adicionaArtista(String artista, String papel)
    {
        if (totalDeAtores < maximoDeAtoresPrincipais)
        {
            this.atoresPrincipais[totalDeAtores][0] = artista;
            this.atoresPrincipais[totalDeAtores][1] = papel;
            totalDeAtores++;
        }
        else
            System.out.println("Não é possível incluir mais do que cinco atores principais. Operação anulada.");
    }
 
    
    public int getTipo()
    {
        return 2;
    }
 
    
    public void imprimeFicha()
    {
        super.imprimeFicha();
        System.out.println("Tipo: Filme em DVD");
        System.out.println("Diretor: " + diretor);
        for (int i = 0; i < totalDeAtores; i++)
        {
            System.out.println("Artista " + (i+1) + ": " + atoresPrincipais[i][0] + ", papel: " + atoresPrincipais[i][1]);
        }
        System.out.println("____________________________________________________________");
    }
}




class Jogo extends Midia implements java.io.Serializable
{
    private String genero;
    
    
    Jogo(String titulo, int anoCriacao, String genero)
    {
        super(titulo, anoCriacao);
        this.genero = genero;
    }

    
    public int getTipo()
    {
        return 3;
    }
    
 
    public void imprimeFicha()
    {
        super.imprimeFicha();
        System.out.println("Tipo: Jogo Eletrônico");
        System.out.println("Gênero: " + genero);
        System.out.println("____________________________________________________________");
    }
}




class Catalogo implements java.io.Serializable {
    private Midia[] catalogoDeMidias;
    private final int tamanhoMaximoDoCatalogo = 300;
    private int posicaoLivre = 0;  //indica a próxima posição livre no array catalogoDeMidias e consequentemente representa seu tamanho atual.
    private int contadorDeCDs;
    private int contadorDeDVDs;
    private int contadorDeJogos;
    
    
    Catalogo(int tamMax)
    {
        if (tamMax > 0 && tamMax <= tamanhoMaximoDoCatalogo)
            catalogoDeMidias = new Midia[tamMax];
        else
            System.out.println("Operação anulada. O tamanho da coleção deve estar definido entre 1 e 300.");
    }
    
    
    boolean adicionaMidia(Midia midia)
    {  
        if (posicaoLivre < quantidadeMaximaDeMidias())
        {
            switch(midia.getTipo())
            {
                case 1:
                    contadorDeCDs++;
                    break;
                case 2:
                    contadorDeDVDs++;
                    break;
                case 3: 
                    contadorDeJogos++;
                    break;
                default:
                    System.out.println("Tipo inválido.");
                    break;
            }
            
            catalogoDeMidias[posicaoLivre] = midia;
            ++posicaoLivre;
            return true;
        }
        else
        {
            System.out.println("Este catálogo atingiu o tamanho máximo definido pelo usuário. É impossível inserir novos registros.");
            return false;
        }
    }
    
    
    Midia obtemMidia(String titulo)
    {
        Midia [] listaPorTitulo = lista();
        int inicio = 0, 
            fim = quantidadeDeMidias() - 1, 
            metade;
    
        while (inicio <= fim)
        { 
            metade = (inicio + fim) / 2;
            if (titulo.compareTo(listaPorTitulo[metade].getTitulo()) > 0)
                inicio = metade + 1;
            else if (titulo.compareTo(listaPorTitulo[metade].getTitulo()) < 0)
                fim = metade - 1;
            else if(titulo.compareTo(listaPorTitulo[metade].getTitulo()) == 0)
                return listaPorTitulo[metade];
        } 
        return null;
    }
    
    
    int quantidadeMaximaDeMidias()
    {
        return catalogoDeMidias.length;
    }
    
    
    int quantidadeDeMidias()
    {
        return posicaoLivre;
    }
    
    
    int quantidadeDeCDs()
    {
        return contadorDeCDs;
    }
    
    
    int quantidadeDeDVDs()
    {
        return contadorDeDVDs;
    }
    
    
    int quantidadeDeJogos()
    {
        return contadorDeJogos;
    }
    

    Midia[] lista()
    {
        int comprimentoDaLista = quantidadeDeMidias();
        Midia[] lista = new Midia[comprimentoDaLista];
        for (int i = 0; i < comprimentoDaLista; i++)
        {
            lista[i] = catalogoDeMidias[i];
        }
        return ordena(new ComparaTitulo(), lista);
    }
    
    
    void imprimeLista()
    {
        Midia[] listaPorTitulo = lista();
        for (int i = 0; i < listaPorTitulo.length; i++)
        {
            listaPorTitulo[i].imprimeFicha();
        }
    }
    

    Midia[] listaPorTipo(int tipo)
    {
        int comprimentoDaLista;
        switch(tipo)
        {
            case 1:
                comprimentoDaLista = quantidadeDeCDs();
                break;
            case 2:
                comprimentoDaLista = quantidadeDeDVDs();
                break;
            case 3: 
                comprimentoDaLista = quantidadeDeJogos();
                break;
            default:
                System.out.println("Opção inválida. Favor escolher entre uma das seguintes opções: 1, 2 ou 3.");
                return null;
        }
        
        
        //decidir essa parte
        //if (comprimentoDaLista > 0)
        //{
            Midia[] listaPorTipo = new Midia[comprimentoDaLista];
            int j = 0;
            for (int i = 0; i < quantidadeDeMidias(); i++)
            {
                if (catalogoDeMidias[i].getTipo() == tipo)
                {
                    listaPorTipo[j] = catalogoDeMidias[i];
                    j++;
                }
            }
        
            //Primeira lista ordenada pelo ano de criação
            Midia[] listaOrdenadaPorAno = ordena(new ComparaAno(), listaPorTipo);
        
        /* trecho inútil
            //conforme definido nas instruções, 
            //pegamos a lista ordenada por ano e começamos a verificar se há anos repetidos, cada ano gera uma 
            //sublista que é então ordenada por título
            //varreremos a lista pré-ordenada por ano, procurando por anos iguais
            for (int k = 0; k < listaOrdenadaPorAno.length; k++)
            {
                
                int contadorDeRepeticoes = 0; // a cada execução, o contador é zerado
                int proximo = k+1; //próximo é definido como o elemento seguinte a k
                
                
                //considerando que a lista já está na forma, por ex: 1983 1983 1983 1985 1989 1989
                //o while vai tomar o elemento k e compará-lo aos seguintes, contando as repetições
                //'(proximo < listaOrdenada.length)' garante que o laço não vai estourar para o último k, 
                //devido à atribuição do valor de 'proximo' como k+1
                while ( (proximo < listaOrdenadaPorAno.length) && (listaOrdenadaPorAno[k].getanoCriacao() == listaOrdenadaPorAno[proximo].getanoCriacao()) )
                {
                    contadorDeRepeticoes++;
                    proximo++;
                }
                
                //caso sejam encontradas repetições, procedemos à ordenação dos sub-elementos alfabeticamente
                if (contadorDeRepeticoes > 0)
                {
                    //criamos uma lista temporária
                    Midia[] listaTemporaria = new Midia[contadorDeRepeticoes+1];
                    int contadorAuxiliar = k; //usada para passar os elementos da lista por ano
                
                    //copiamos os dados para a lista temporária
                    //o contador auxiliar inicia-se em k, passando os elementos um a um
                    for (int i = 0; i < listaTemporaria.length; i++)
                    {
                        listaTemporaria[i] = listaOrdenadaPorAno[contadorAuxiliar];
                        contadorAuxiliar++;
                    }
                    //chamamos a ordenação da sublista por ano
                    listaTemporaria = ordena(new ComparaTitulo(), listaTemporaria);
                
                    //devolvemos os elementos reordenados para a lista e ao mesmo tempo k vai sendo incrementado com a intenção de, no fim, "pular" os elementos já testados
                    for (int i = 0; i < listaTemporaria.length; i++)
                    {
                        listaOrdenadaPorAno[k] = listaTemporaria[i];
                        k++; 
                    }
                    k++; //incremento que posiciona o laço no próximo elemento ainda não verificado.
                }
            }
        //}    
        */
            return listaOrdenadaPorAno;
    }
        
    
    void imprimeListaPorTipo(int tipo)
    {
        if (tipo == 1 || tipo == 2 || tipo == 3)
        {
            Midia[] listagemPorTipo = listaPorTipo(tipo);
            for (int i = 0; i < listagemPorTipo.length; i++)
            {
                listagemPorTipo[i].imprimeFicha();
            }
        }
        else
            System.out.println("Opção inválida. Favor escolher entre uma das seguintes opções: 1, 2 ou 3.");
    }

    
    public Midia[] ordena(ComparadorDeMidias m, Midia[] listaAOrdenar)
    {
        int i, j;
        Midia aInserir;
        int fim = listaAOrdenar.length;
        
        for (i = 1; i < fim; i++)
        {
            aInserir = listaAOrdenar[i];
            j = i;
            while ( (j > 0) && ( m.primeiroEhMenor(listaAOrdenar[j - 1], aInserir) ) )
            {
                listaAOrdenar[j] = listaAOrdenar[j - 1];
                j--;
            }
            listaAOrdenar[j] = aInserir;
        }
        return listaAOrdenar;
    }
    
}




interface ComparadorDeMidias
{
    boolean primeiroEhMenor(Midia a, Midia b);
}




class ComparaTitulo implements ComparadorDeMidias, java.io.Serializable
{
    public boolean primeiroEhMenor(Midia a, Midia b)
    {
        return (a.getTitulo().compareTo(b.getTitulo()) > 0);
    }
}




class ComparaAno implements ComparadorDeMidias, java.io.Serializable
{
    public boolean primeiroEhMenor(Midia a, Midia b)
    {
      //essas duas linhas resolvem o problema das 41, caralho!!!
      if (a.getanoCriacao() == b.getanoCriacao())
        return (a.getTitulo().compareTo(b.getTitulo()) > 0);
      return (a.getanoCriacao() > b.getanoCriacao());
    }
}



class TestaCatalogo2
{
    void testaSeriacao()
    {
        final CD cd1 = new CD("X & Y", 2005, "Cold Play");
        cd1.adicionaFaixa("Square One", 287); // 4:47
        cd1.adicionaFaixa("What If", 297); // 4:57
        cd1.adicionaFaixa("White Shadows", 328); // 5:28
        cd1.adicionaFaixa("Fix You", 294); // 4:54
        cd1.adicionaFaixa("Talk", 311); // 5:11
        cd1.adicionaFaixa("X&Y", 274); // 4:34
      
        final DVD dvd1 = new DVD("O Senhor dos Anéis - A Sociedade dos Anel", 2001, "Peter Jackson");
        dvd1.adicionaArtista("Elijah Wood", "Frodo Baggins");
        dvd1.adicionaArtista("Viggo Mortensen", "Aragorn");
        dvd1.adicionaArtista("Orlando Bloom", "Legolas Greenleaf");
        dvd1.adicionaArtista("Christopher Lee", "Saruman");
        dvd1.adicionaArtista("Ian McKellen", "Gandalf");
        
        final Jogo jogo1 = new Jogo("Gran Turismo 4", 2007, "Corrida");
      
        final CD cd2 = new CD("Bachianas Brasileiras No.2", 2004,"Orquestra de Câmara da Universidade de São Paulo");
        cd2.adicionaFaixa("(Prelúdio) O Canto do Capadócio", 512); // 4:32
        cd2.adicionaFaixa("(Ária) O Canto da Nossa Terra", 389); // 6:29
        cd2.adicionaFaixa("(Dança) Lembranca do Sertão", 324); // 5:24
        cd2.adicionaFaixa("(Tocata) O Trenzinho do Caipira", 284); // 4:44
      
        final DVD dvd2 = new DVD("Matrix", 1999, "Andy & Larry Wachoski");
        dvd2.adicionaArtista("Keanu Reeves", "Neo");
        dvd2.adicionaArtista("Laurence Fishburne", "Morpheus");
        dvd2.adicionaArtista("Carrie-Anne Moss", "Trinity");
        dvd2.adicionaArtista("Hugo Weaving", "Agente Smith");
        dvd2.adicionaArtista("Gloria Foster", "Oráculo");
        
        final Jogo jogo2 = new Jogo("Actriser", 1992, "Ação");
        
        Catalogo catalogo = new Catalogo(6);
      
        catalogo.adicionaMidia(cd1);
        catalogo.adicionaMidia(dvd1);
        catalogo.adicionaMidia(jogo1);
        catalogo.adicionaMidia(cd2);
        catalogo.adicionaMidia(dvd2);
        catalogo.adicionaMidia(jogo2);
        
        SeriadorDeCatalogo s = new SeriadorDeCatalogo();
        s.gravaCatalogo(catalogo, "catalogo.dat");
        
        Catalogo catalogo3 = s.carregaCatalogo("catalogo.dat");
        catalogo3.imprimeLista();
        
        System.out.println("Sucesso.");
        
    }
}