/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Jogos;

/**
 *
 * @author Luis Serra
 */
public class JogoFutebol {

  String msg;

   public JogoFutebol(String string) {
         msg=string;
    }

    public void corre(){
     System.out.println(msg+": "+"correndo");
 }

  public void chuteForte(){
     System.out.println(msg+": "+"Chutón!");
 }
  /*
 public void toca(){
     System.out.println("toquei a bola");
 }
  public void cruza(){
     System.out.println("cruzamentooo!!");
 }
*/
}
