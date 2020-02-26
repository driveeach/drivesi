/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Commands;

import Portoes.Portao;

/**
 *
 * @author matheusmf
 */
public class FecharCommand implements Command {
    private Portao portao;

    public FecharCommand(Portao portao){
        this.portao = portao;
    }

    public void execute(){
        portao.fechar();
    }

    public void undo(){
        portao.abrir();
    }

}
