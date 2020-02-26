/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Comandos;

import Jogos.JogoCorrida;

/**
 *
 * @author Luis Serra
 */
public class BotaoACorrida implements Command {

    JogoCorrida corrida;

    public BotaoACorrida(JogoCorrida corrida) {
        this.corrida = corrida;
    }

    public void executa() {
        corrida.acelera();
    }



}
