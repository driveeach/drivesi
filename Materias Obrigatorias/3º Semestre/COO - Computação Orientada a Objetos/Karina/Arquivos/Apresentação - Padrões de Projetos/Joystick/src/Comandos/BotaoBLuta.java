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
public class BotaoBLuta implements Command {

    JogoLuta luta;

    public BotaoBLuta(JogoLuta luta) {
        this.luta = luta;
    }


    public void executa() {
        luta.socoForte();
    }
}
