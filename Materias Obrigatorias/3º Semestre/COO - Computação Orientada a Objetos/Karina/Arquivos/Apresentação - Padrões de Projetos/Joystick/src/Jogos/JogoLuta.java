package Jogos;
import joystickcommand.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Luis Serra
 */
public class JogoLuta {
 
    String msg;

    public JogoLuta(String string) {
    msg=string;
    }

 public void chuteAlto(){
     System.out.println(msg+": "+"chutei alto");
 }
 
 public void socoForte(){
     System.out.println(msg+": "+"Soco forte");
 }
/* public void golpeEspecial(){
     System.out.println("Golpe especial");
 }
 public void rasteira(){
     System.out.println("rasteira!");
 }*/


}
