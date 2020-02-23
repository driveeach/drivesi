/*********************************************************************/
/** ACH 2001 - Introdução à Ciência da Computação I                 **/
/** EACH-USP - Primeiro Semestre de 2008                            **/
/** Turma do Matutino - Prof Delano Medeiros Beder                  **/
/**                                                                 **/
/** Primeiro Exercício-Programa                                     **/
/** Arquivo: Banco6411927.java                                      **/
/**                                                                 **/
/** Murilo Galvão Honório                                           **/
/** USP 6411927                                                     **/
/** 02 de Abril de 2008                                             **/
/*********************************************************************/

public class Banco6411927
{  

        int um = 0;                         //inicialização da variável que armazena a quantidade de notas de um duca
        int cinco = 0;                      //inicialização da variável que armazena a quantidade de notas de cinco ducas
        int dez = 0;                        //inicialização da variável que armazena a quantidade de notas de dez ducas
        int cinquenta = 0;                  //inicialização da variável que armazena a quantidade de notas de cinquenta ducas
        int cem = 0;                        //inicialização da variável que armazena a quantidade de notas de cem ducas
            
        
        // O método carregaValores especifica o número notas de cada tipo, pegando as variáveis do tipo inteiro
        // a, b, c, d, e, e passando os valores paras as variáveis fora do método na seguinte ordem: cem, cinquenta, dez, cinco e um.
        void carregaValores(int a, int b, int c, int d, int e) //método que define o número inicial para cada nota
        {
            if (a >= 0 && b >= 0 && c >= 0 && d >= 0 && e >= 0)    //só pra testar se o esperto não está tentando passar um número negativo de notas
            {
                cem = a;
                cinquenta = b;
                dez = c;
                cinco = d;
                um = e;
            }
            else
            {
                System.out.println("Insira números de notas válidos.");
            }
        }
        

        // O método saque pede o valor que o cliente deseja sacar, efetua as verificações, cálculos e operações pertinentes e exibe as mensagens correspondentes.
        void saque(int valorDesejado) 
        {
            int valorRetirado = 0;                                              //guarda a quantidade de dinheiro retirada a cada passagem dos comandos while
            int saldo = (um*1)+(cinco*5)+(dez*10)+(cinquenta*50)+(cem*100);     //cálculo do saldo atual atribuindo valores à quantidade de notas
            int tiraDeCem = 0;                                                  //conta a quantidade de notas de cem retiradas do caixa. O mesmo vale para as seguintes. ;)
            int tiraDeCinquenta = 0;
            int tiraDeDez = 0;
            int tiraDeCinco = 0;
            int tiraDeUm = 0;
            if (valorDesejado > 0)    //teste se não está tentando pedir um valor negativo ou zero
            {
            // Primeiro, testamos se há dinheiro suficiente no caixa, ou seja, se o saldo é maior ou igual ao valor desejado pelo cliente.
                if (saldo >= valorDesejado)            
                {
                // Para a execução do comando while ambas expressões precisam ser verdadeiras. As expressões para os valores 50, 10, 5 e 1 são análogas.
                // O teste "cem > tiraDeCem" tem as seguintes finalidades: na primeira passagem, testa se existem notas de cem, comparando cem > 0 
                // (definida no início do método), passando para o próximo while caso cem = 0; nas demais passagens garante que não se ultrapasse o número
                // máximo de notas de cem, pois quando cem = tiraDeCem, retornará false.
                // Por fim, o teste "valorDesejado >= (valorRetirado+100)" serve para proibir que o valorRetirado no corpo do while ultrapasse o valorDesejado pelo usuário.
                    while (cem > tiraDeCem && valorDesejado >= (valorRetirado+100) )
                    {
                        tiraDeCem++;                            //conta a quantidade de notas de cem retiradas a cada passagem, efetuando o incremento
                        valorRetirado = valorRetirado+100;
                    }
                    while (cinquenta > tiraDeCinquenta && valorDesejado >= (valorRetirado+50) )
                    {
                        tiraDeCinquenta++;
                        valorRetirado = valorRetirado+50;
                    }
                    while (dez > tiraDeDez && valorDesejado >= (valorRetirado+10) )
                    {
                        tiraDeDez++;
                        valorRetirado = valorRetirado+10;
                    }
                    while (cinco > tiraDeCinco && valorDesejado >= (valorRetirado+5) )
                    {
                        tiraDeCinco++;
                        valorRetirado = valorRetirado+5;
                    }
                    while (um > tiraDeUm && valorDesejado >= (valorRetirado+1) )
                    {
                        tiraDeUm++;
                        valorRetirado = valorRetirado+1;
                    }
                    // Após a execução das inúmeras passagens, testamos se o objetivo foi atingido, ou seja se o valorRetirado é igual ao valorDesejado.
                    // Em caso positivo, exibe mensagens para o usuário e atualiza as quantidades de notas.
                    if (valorRetirado == valorDesejado)
                    {
                        System.out.println("Saque autorizado.");
                        System.out.println("Você sacou " +valorRetirado+ " ducas.");
                        System.out.println("Nesta operação, o caixa forneceu:");
                        System.out.println("Notas de 100: " +tiraDeCem+ ", notas de 50: " +tiraDeCinquenta+ ", notas de 10: " +tiraDeDez+ ", notas de 5: " +tiraDeCinco+ ", notas de 1: " +tiraDeUm+ ".");
                        System.out.println("-----------------------------------------------------------------------------------");
                        // A próxima parte de código atualiza a quantidade de notas, efetuando as subtrações e passando os valores para as variáveis definidas fora do metodo.
                        cem = cem - tiraDeCem;
                        cinquenta = cinquenta - tiraDeCinquenta;
                        dez = dez - tiraDeDez;
                        cinco = cinco - tiraDeCinco;
                        um = um - tiraDeUm;
                    }
                    // Obviamente o caixa não possui a quantidade de notas necessária para o saque, pois a condição de 
                    // ter ou não saldo foi testada no início do método. Exibe a mensagem correspondente.
                    else   
                    {
                        System.out.println("Saque não autorizado.");
                        System.out.println("Este terminal não possui notas suficientes para concluir a operação.");
                        System.out.println("Por favor, dirija-se à outro caixa ou tente novamente mais tarde.");
                        System.out.println("-----------------------------------------------------------------------------------");
                    }
                }
                //Caso o valor desejado pelo cliente seja superior ao saldo, não autoriza o saque e apenas informa o saldo atual.
                else       
                {
                    System.out.println("Saque não autorizado.");
                    System.out.println("Você deseja retirar " +valorDesejado+ " ducas, porém o saldo atual é de somente " +saldo+ ".");
                    System.out.println("Efetue um saque de menor valor ou fale com o gerente.");
                    System.out.println("-----------------------------------------------------------------------------------");
                }
            }
            else
            {
                System.out.println("O valor desejado deve ser maior do que 0.");
            }
        }
        
        
        // O método deposito adiciona o valor definido em a,b,c,d,e à quantidade de nota de cada tipo e imprime a nova quantidade de notas.
        void deposito(int a, int b, int c, int d, int e) 
        {
            if (a >= 0 && b >= 0 && c >= 0 && d >= 0 && e >= 0)    //primeiro testa se o camarada não está digitando um número negativo
            {
                cem = cem+a;
                cinquenta = cinquenta+b;
                dez = dez+c;
                cinco = cinco+d;
                um = um+e;
                int saldo = (um*1)+(cinco*5)+(dez*10)+(cinquenta*50)+(cem*100); //o velho cálculo do saldo atual. 
                System.out.println("Depósito confirmado.");
                System.out.println("Saldo atual: " +saldo+ " ducas.");
                System.out.println("Notas de 100: " +cem+ ", notas de 50: " +cinquenta+ ", notas de 10: " +dez+ ", notas de 5: " +cinco+ ", notas de 1: " +um+ ".");
                System.out.println("-----------------------------------------------------------------------------------");
            }
            else
            {
                System.out.println("Depósito não autorizado. Insira números positivos para as notas.");
            }
        }
        
        
        // Método que calcula o saldo, exibindo a mensagem apropriada e a quantidade de notas disponíveis.
        void imprime()
        {
            int saldo = (um*1)+(cinco*5)+(dez*10)+(cinquenta*50)+(cem*100);
            if (saldo==0){
                System.out.println("Este caixa não possui ducas no momento.");
                System.out.println("Notas de 100: " +cem+ "; notas de 50: " +cinquenta+ "; notas de 10: " +dez+ "; notas de 5: " +cinco+ "; notas de 1: " +um+ ".");
                System.out.println("-----------------------------------------------------------------------------------");
            }
            else if (saldo==1){
                System.out.println("O saldo atual é de " + saldo + " duca.");
                System.out.println("Notas de 100: " +cem+ "; notas de 50: " +cinquenta+ "; notas de 10: " +dez+ "; notas de 5: " +cinco+ "; notas de 1: " +um+ ".");
                System.out.println("-----------------------------------------------------------------------------------");
            }
            else {
                System.out.println("O saldo atual é de " + saldo + " ducas.");
                System.out.println("Notas de 100: " +cem+ "; notas de 50: " +cinquenta+ "; notas de 10: " +dez+ "; notas de 5: " +cinco+ "; notas de 1: " +um+ ".");
                System.out.println("-----------------------------------------------------------------------------------");
            }
        }
        
}

// Obs.: no código original criei um método saldo() que evitava essa repetição do cálculo "(um*1)+(cinco*5)+(dez*10)+(cinquenta*50)+(cem*100)" presente
// em todos os métodos, mas fiquei na dúvida se isso seria considerado, portanto retirei desta versão final.