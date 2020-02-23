class Triangulo {
    int Cor;
    public static void main (String args[]) {
       int MARROM=1, VERMELHO=2, VERDE=3;
       Triangulo T1, T2, T3;
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.Cor=VERMELHO;
       T2.Cor=VERDE;
       T3.Cor=MARROM;
       System.out.println (T1.Cor);
       System.out.println (T2.Cor);
       System.out.println (T3.Cor);
    }
}
/* Esta construcao - colocar main como metodo estatico da 
 * (unica) classe, embora estranho eh permitido, usual e 
 * nao eh má prática de programacao.
 * */
/* Neste codigo, a classe Triangulo tem um atributo (Cor)
 * e um metodo (main) */
/* Cor eh do tipo inteiro e contém o número correspondente
 * aa cor, definida em algum outro lugar. */
/* Em main, são definidas "constantes" que codificam as
 * cores e três variáveis que conterão referências para 
 * objetos da classe Triangulo. */
/* Os objetos são criados (alocados e inicializados) através
 * do operador new, que retorna a referência para o objeto,
 * que é armazenada na variável. */
/* O atributo é acessado diretamente, e recebe o valor 
 * correspondente a uma cor. */
/* Imprimir a cor resulta na impressão do número que a 
 * representa. */
/* O que não está bom aqui:
 * 1-) main como método da classe Triangulo;
 * 2-) Cor como um inteiro (embora seja o correto 
 *     em outras linguagens, e seja prática comum,
 *     permite atribuir a Cor valores que não representam 
 *     cores. É possível dar solução melhor em JAVA; 
 * 3-) Atributos da classe são diretamente modificáveis,
       o que expõe a implementação. */
/* O que está bom:
 * 1-) Indentação */
