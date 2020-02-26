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
public class AbrirCommand implements Command {
    private Portao portao;

    public AbrirCommand(Portao portao){
        this.portao = portao;
    }

    public void execute(){
        portao.abrir();
    }

    public void undo(){
        portao.fechar();
    }

}
