/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Comandos;

import Jogos.JogoFutebol;

/**
 *
 * @author Luis Serra
 */
public class BotaoBFutebol implements Command {

    JogoFutebol futebol;

    public BotaoBFutebol(JogoFutebol futebol) {
        this.futebol = futebol;
    }

    public void executa() {
        futebol.corre();
    }
}
