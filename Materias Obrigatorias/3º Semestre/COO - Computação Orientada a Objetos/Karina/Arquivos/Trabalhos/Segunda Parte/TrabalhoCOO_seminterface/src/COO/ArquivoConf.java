package COO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ArquivoConf {

    public static LinkedList<Arquivo> Arquivos;

    public static void carregar(String path, String p_arquivoConf) {

        LinkedList<Arquivo> ListaAux = new LinkedList<Arquivo>();

        int ch;
        StringBuffer strContent = new StringBuffer("");
        LinkedList<String> v_retorno = new LinkedList<String>();
        FileInputStream ob = null;

        try {
            ob = new FileInputStream(new File(path + p_arquivoConf));
            while ((ch = ob.read()) != -1) {
                strContent.append((char) ch);
            }

            String v_conteudo = strContent.toString();
            String[] a = v_conteudo.split(";");

            for (int i = 0; i < a.length; i++) {
                v_retorno.add(a[i]);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Arquivo de Configura��o n�o encontrado");
        } 
        finally {
            try {
                ob.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < v_retorno.size(); i++) {
            try {
                ListaAux.add(new Arquivo(new File(path + v_retorno.get(i).trim()), v_retorno.get(i), path));
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Arquivos = ListaAux;
    }
}
