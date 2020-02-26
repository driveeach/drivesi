package COO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Fachada {
    
    public static void iniciaBusca(String busca, int escolha) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        new InicioBusca(busca, escolha);
    }
    
    public static void carregaArquivos(String arquivo_conf) throws IOException
    {
        ArquivoConf.carregar(arquivo_conf);
    }
    
    public static LinkedList<String> listaArquivosComPalavra()
    {
        LinkedList<String> lista = new LinkedList<String>();
        for (int i = 0; i < ArquivoConf.Arquivos.size(); i++) {
            if (ArquivoConf.Arquivos.get(i).contem == true) {
               lista.add(ArquivoConf.Arquivos.get(i).nome + " ("+ArquivoConf.Arquivos.get(i).quant+")");
            }
        }
        return lista;
    }
    
    public static String inicializarTexto(String nomeArq)
    {
        String textoArquivo = null;
        
        for(int i = 0; i<ArquivoConf.Arquivos.size(); i++) {
            if (nomeArq.contains( ArquivoConf.Arquivos.get(i).nome) == true) {
                textoArquivo = ArquivoConf.Arquivos.get(i).texto;
            }
        }
        return textoArquivo;
    }

}
