/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package joystickcommand;

import Comandos.*;
import Jogos.*;
/**
 *
 * @author Luis Serra
 */
public class Joystick {

   /* Command[] xCommand;
    Command[] yCommand;*/
    Command[] aCommand;
    Command[] bCommand;

    public Joystick(){
       /* xCommand = new Command[3];
        yCommand = new Command[3];*/
        aCommand = new Command[3];
        bCommand = new Command[3];

        Command noCommand = new noCommand();

        for(int i=0;i<3;i++){
        /*xCommand[i] = noCommand;
        yCommand[i] = noCommand;*/
        aCommand[i] = noCommand;
        bCommand[i] = noCommand;
         }
       }

    public void setCommand(int slot, /*Command xCommand_, Command yCommand_,*/ Command aCommand_, Command bCommand_){

       /* xCommand[slot] = xCommand_;
        yCommand[slot] = yCommand_;*/
        aCommand[slot] = aCommand_;
        bCommand[slot] = bCommand_;

    }

    /*public void apertaX(int slot){

        xCommand[slot].executa();

    }

      public void apertaY(int slot){

        yCommand[slot].executa();

    }*/

     public void apertaA(int slot){

        aCommand[slot].executa();

    }

      public void apertaB(int slot){

        bCommand[slot].executa();

    }

    @Override
      public String toString(){

        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n----JOYSTICK EM FUNCIONAMENTO----\n");
        for (int i=0; i<aCommand.length;i++){
         stringBuff.append("[slot "+i+"]" /*+ xCommand[i].getClass().getName()+" ||  "
                 + yCommand[i].getClass().getName()+" ||  "*/+ aCommand[i].getClass().getName()+" ||  "+ bCommand[i].getClass().getName()+"\n");
         }
         return stringBuff.toString();
        }


     public static void main(String[] args) {

      // CRIA O JOYSTICK

     Joystick  joystick = new Joystick();


     //cria os Jogos

     JogoLuta tekken = new JogoLuta("tekken");
     JogoCorrida topGear = new JogoCorrida("TopGear");
     JogoFutebol winning11 = new JogoFutebol("Winning Elleven");

     //Cria os comandos;

     BotaoALuta lutaA = new BotaoALuta(tekken);
     BotaoBLuta lutaB = new BotaoBLuta(tekken);
     BotaoACorrida corridaA = new BotaoACorrida(topGear);
     BotaoBCorrida corridaB = new BotaoBCorrida(topGear);
     BotaoAFutebol futebolA = new BotaoAFutebol(winning11);
     BotaoBFutebol futebolB = new BotaoBFutebol(winning11);

     //Coloca os comandos nos botoes do Joystick

     joystick.setCommand(0, lutaA, lutaB);
     joystick.setCommand(1, corridaA, corridaB);
     joystick.setCommand(2, futebolA, futebolB);

     System.out.println(joystick);

     joystick.apertaA(0);
     joystick.apertaB(0);
     joystick.apertaA(1);
     joystick.apertaB(1);
     joystick.apertaA(2);
     joystick.apertaB(2);

     }


      }



