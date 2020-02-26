package Jogos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Luis Serra
 */
public class JogoCorrida {
      String msg;
    public JogoCorrida(String string) {
         msg=string;
    }


    public void acelera(){
     System.out.println(msg+": "+"pisa fundo!");
     }

    public void freia(){
     System.out.println(msg+": "+"Diminuindo");
      }

    /*public void farol(){
     System.out.println("farois ligados");
    }
    public void turbo(){
     System.out.println("turboo!!");
 }*/

}
