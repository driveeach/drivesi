/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package joystickcommand;

import Comandos.BotaoACorrida;
import Comandos.BotaoAFutebol;
import Comandos.BotaoALuta;
import Comandos.BotaoBCorrida;
import Comandos.BotaoBFutebol;
import Comandos.BotaoBLuta;
import Jogos.JogoCorrida;
import Jogos.JogoFutebol;
import Jogos.JogoLuta;

/**
 *
 * @author Luis Serra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	
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
