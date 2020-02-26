public class FuncionarioComum implements SalarioFuncionario
{
  private double valorHrBase;
  private int qtdHE;
  
  FuncionarioComum(double valorHB)
  {
    valorHoraBase(valorHB);
  }
  
 public String defineNome(String nomeFun)
 {
   return nomeFun;
 }
 
 public double valorHoraBase(double hora)
 {
   if(hora > 200)
     System.out.println("Erro - Valor Base da hora deve ser menor que 200");
   else
   valorHrBase = hora;
   return
     hora;
 }
 
 public double calculaHE(int quantidade)
 {
   double calculo = 0;
   if(quantidade >= 0)
   {
     qtdHE = quantidade;
     if(quantidade < 40)
       calculo = quantidade * valorHrBase;
     else
       calculo = 40 * valorHrBase;
     return calculo ;
   }
   else
     System.out.println("Quantidade negativa - ERRO");
   return -1;
 }
 
 public double calculaSalarioFinal()
 {
   double salarioFinal = 200 * valorHrBase + calculaHE(qtdHE);
   return salarioFinal;
 }
 
 public String defineCargo(String cargo)
 {
   return cargo;
 }
 
 public int dataDeAdmissao(int data)
 {
   return data;
 }
}
   
 
   