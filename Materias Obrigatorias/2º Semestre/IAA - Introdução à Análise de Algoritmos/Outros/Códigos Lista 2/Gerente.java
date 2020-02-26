public class Gerente implements SalarioFuncionario
{
  private double valorHB;
  private double calcHE;
  private String name;
  private int qtdHE;
  
  Gerente (double valorB)
  {
    if (valorB < 0)
      System.out.println("Erro - valor negativo para Valor hora base");
    else
      valorHoraBase (valorB);
  }
  
  String obtemName()
  {
    return name;
  }
  
  public String defineNome (String nome)
  {
    name = nome;
    return nome;
  }
  
  public double valorHoraBase (double hora)
  {
    valorHB = hora;
    return hora;
  }
  
  public double calculaHE (int quantidade)
  {
    if(quantidade >= 0){
      qtdHE = quantidade;
      calcHE = 0.2 * valorHB * quantidade;
      return calcHE;
    }else
      System.out.println("ERRO - valor negativo de horas extras");
    return 0;
  }
  
  public double calculaSalarioFinal ()
  {
    return 180 * valorHB + calcHE + 2000;
  }
  
  public void imprimeDados()
  {
    System.out.println("Nome: " + name);
    System.out.println("ValorHoraBase: " + valorHB);
    System.out.println("QtdHoraExtra: " + qtdHE);
    System.out.println("Calculo da Hora Extra: " + calcHE);
    System.out.println("Calculo do Salario Final: 180 * " + valorHB + " + " + calcHE + " = " + calculaSalarioFinal());
    System.out.println();
  }
}