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
public class BotaoBCorrida implements Command {

   JogoCorrida corrida;

    public BotaoBCorrida(JogoCorrida corrida) {
        this.corrida = corrida;
    }

    public void executa() {
        corrida.freia();
    }

}
