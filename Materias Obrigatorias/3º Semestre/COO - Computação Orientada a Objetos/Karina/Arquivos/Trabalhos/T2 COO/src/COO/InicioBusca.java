package COO;

import java.io.*;

public class InicioBusca {
    private static Palavra palavra;

    public InicioBusca(String busca, int escolha) {
        try {
            inicio(busca, escolha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

    public static void inicio(String busca, int escolha) throws FileNotFoundException, IOException, ClassNotFoundException {
        //String path = new File(".").getCanonicalPath();
        //path += "\\src\\COO\\";

        //ArquivoConf.carregar(path, "Arquivos.txt");
        //COO.Fachada.carregaArquivos("Arquivos.txt");
        //Scanner input = new Scanner(System.in);
        //System.out.println("Digite a palavra que deseja buscar:");

        //String busca = input.next();

        palavra = new Palavra();
        palavra.setPalavra(busca);
        
        int total = 0;
        /*for (int i = 0; i < ArquivoConf.Arquivos.size(); i++) {
            if (escolha == 1) {
               total += ArquivoConf.Arquivos.get(i).buscaSequencial(busca);
            }
            else if (escolha==2){
                if (ArquivoConf.Arquivos.get(i).a_ser != null) {
                    if (ArquivoConf.Arquivos.get(i).t.lastModified() > ArquivoConf.Arquivos.get(i).a_ser.lastModified()) {
                        total += ArquivoConf.Arquivos.get(i).hashLinha(busca);
                    } else {
                       total += ArquivoConf.Arquivos.get(i).desserializacao(busca);
                    }
                } else {
                    total += ArquivoConf.Arquivos.get(i).desserializacao(busca);
                }*/
        
        Arquivo arq;
                
        while (ArquivoConf.itr.hasNext()) {
            arq = ArquivoConf.itr.next();
            if (escolha == 1) {               
               total += arq.buscaSequencial(busca);               
            }
            else if (escolha==2){
                if (arq.a_ser != null) {
                    if (arq.t.lastModified() > arq.a_ser.lastModified()) {
                        total += arq.hashLinha(busca);
                    } else {
                       total += arq.desserializacao(busca);
                    }
                } else {
                    total += arq.desserializacao(busca);
                }
                //System.out.println("-----------------------------");
            }
        }
        //System.out.println("Total: " + total);

    }
    
    public void setPalavra (Palavra palavra){
        this.palavra = palavra;
    }
    public Palavra getPalavra (){
        return palavra;
    }
}