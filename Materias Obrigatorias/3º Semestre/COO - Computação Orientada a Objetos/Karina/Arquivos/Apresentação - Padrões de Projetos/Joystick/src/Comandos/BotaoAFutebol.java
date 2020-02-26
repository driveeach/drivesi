/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Comandos;

import Jogos.*;

/**
 *
 * @author Luis Serra
 */
public class BotaoAFutebol implements Command {

    JogoFutebol futebol;

    public BotaoAFutebol(JogoFutebol futebol) {
        this.futebol = futebol;
    }

    public void executa() {
        futebol.chuteForte();
    }



}
