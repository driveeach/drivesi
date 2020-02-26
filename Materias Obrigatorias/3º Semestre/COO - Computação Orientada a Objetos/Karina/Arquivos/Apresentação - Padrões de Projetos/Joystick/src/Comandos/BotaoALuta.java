/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Comandos;

import Jogos.JogoLuta;

/**
 *
 * @author Luis Serra
 */
public class BotaoALuta implements Command {

    JogoLuta luta;

    public BotaoALuta(JogoLuta luta) {
        this.luta = luta;
    }


    public void executa() {
        luta.chuteAlto();
    }

}
