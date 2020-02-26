/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controle;

import Commands.*;

/**
 *
 * @author matheusmf
 */
public class Controle {
    private Command[] commands;
    private Command ultimoCommand;

    public Controle(Command abrirCommand, Command fecharCommand){
        this.commands = new Command[2];
        commands[0] = abrirCommand;
        commands[1] = fecharCommand;
    }

    public void abrirPortao(){
        commands[0].execute();
        ultimoCommand = commands[0];
    }

    public void fecharPortao(){
        commands[1].execute();
        ultimoCommand = commands[1];
    }

    public void desfazer(){
        ultimoCommand.undo();
    }

}
