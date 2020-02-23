import casa.CasaRet;
import casa.CasaQuad;
import casa.Casa;
import piscina.AreaPiscina;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/*
    Classe para criação de um condomínio de casas
*/
class Projeto {
    /* Representação do condomínio */
    ListaSimples condominio;
    
    /*
        Adiciona, uma residência ao condomínio.
    */
    void adicionaRes(Residencia r) {
        this.condominio.insere(r);
    }
    
    /*
        Constrói o condomínio sem casas
    */
    Projeto() {
        this.condominio = new ListaSimples();
    }
    
    // método que funciona apenas com arranjos, não com listas
    static void bolha(Residencia[] v, Comparador c) {
        for (int ult = v.length-1; ult>0; ult--) {
            for (int i=0; i<ult; i++) {
                if (c.compara(v[i],v[i+1]) > 0) {
                    Residencia aux = v[i];
                    v[i] = v[i+1];
                    v[i+1] = aux;
                }
            }
        }
    }
    
    /*
        Grava os dados do objeto em arquivo. Retorna true se conseguiu
        
        Params:
            nome - nome (e caminho) do arquivo
    *
    public boolean gravaArq(String nome) throws IOException {
        File arq = new File(nome);
        
        FileWriter writer = new FileWriter(arq,true); 
        
        return(true);
    }*/
    public boolean gravaArq(String nome) {
        if (nome == null) return(false);
        
        File arq = new File(nome);
        
        try {
            FileWriter writer = new FileWriter(arq,false);
            No p = this.condominio.retCabeca();
            while (p != null) {
                Residencia r = p.retRes();
                if (r.casa != null) {
                    writer.write("Casa:Área:"+r.casa.area()+"\n");
                }
                if (r.piscina != null) {
                    writer.write("Piscina:Área:"+r.piscina.area()+"\n");
                }
                p = p.retProx();
            }
            writer.close();
        }
        catch(IOException ioe) { return(false); }

        return(true);
    }
    
    public static void main(String[] args) {
        /*CasaRet cr = new CasaRet(10,5,1320);
        CasaQuad cq = new CasaQuad(10,1523);
        
        Projeto p = new Projeto();
        p.adicionaRes(new Residencia(cr, null));
        p.adicionaRes(new Residencia(cq, null));
        
        if (!p.gravaArq("oba")) System.out.println("Problema");*/
        try {
            File arq = new File("oba");
            Scanner sc = new Scanner(arq);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
             }
             sc.close();
         } catch (FileNotFoundException fnfe) {
             System.out.println(fnfe.getMessage());
         }
    }
}
