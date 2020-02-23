/*
    Programa para calcular a área de uma piscina redonda.
*/
class AreaPiscina {
    /* materiais da piscina */
    static final int ALVENARIA = 0;
    static final int VINIL = 1;
    static final int FIBRA = 2;
    static final int PLASTICO = 3;
    /* preços dos materiais */
    double[] precos;
    /* nomes dos materiais */
    static String[] nomes = {"Alvenaria", "Vinil", "Fibra", "Plástico"};
    
    /* raio da piscina*/
    double raio;
    
    /*
       Piscina padrão
    */
    AreaPiscina() {
        double[] aux = {1500, 1100, 750, 500};
        this.precos = aux;
        this.raio = 10;
    }
    
    /*
       Piscina padrão, com preços específicos
    */
    AreaPiscina(double[] precos) {
        this.precos = precos;
        this.raio = 10;
    }
    
    /*
       Piscina padrão, com raio específico
    */
    AreaPiscina(double raio) {
        this();
        this.raio = raio;
    }
    
    /*
       Piscina, com preços e raio específicos
    */
    AreaPiscina(double[] precos, double raio) {
        this.precos = precos;
        this.raio = raio;
    }
    
    /*
        Calcula a área da piscina
        
        Parâmetros:
            raio - O raio da piscina
    */
    double area() {
        return((this.raio >= 0) ? Math.PI * Math.pow(this.raio,2) : -1);
    }
    
    /*
        Calcula o valor da construção da piscina, ou negativo em caso de erro
    */
    double valor(double metragem, int material) {
        if (material<ALVENARIA || material>PLASTICO || metragem<0) return(-1);
        
        return(metragem*precos[material]);
    }

    /*
        Carrega os valores das piscinas na matriz de área X material
    */
    void carregaVal(double[][] m) {
        for (int i=0; i<m.length; i++) { // linhas (material)
            for (int j=50; j<=200; j+=50) { // colunas (áreas)
                m[i][j / 50 - 1] = precos[i] * j;
            }
        }
    }
    
    /*
        Retorna matriz com os preços finais.
        
        Parâmetros:
            val - matriz de valores
            desc - matriz de descontos
    */
    double[][] calculaFinal(double[][] val, double[][] desc) {
        double[][] saida = new double[val.length][val[0].length];
        
        for (int i=0; i<saida.length; i++) {
            for (int j=0; j<saida[0].length; j++) {
                saida[i][j] = val[i][j] * (1-desc[i][j]);
            }
        }
        return(saida);
    }
    
    /*
        Retorna o tipo do material a partir do nome, ou -1 caso o material não esteja registrado
        
        param:
            nome - nome do material da piscina
    */
    static int tipoMat(String nome) {
        for (int i=0; i<nomes.length; i++) {
            if (nomes[i].equals(nome)) return(i);
        }
        return(-1);
    }

    public static void main(String[] args) {
        if (args.length != 2) System.out.println("Número de parâmetros inválido");
        else {
            int material = AreaPiscina.tipoMat(args[0]);
            AreaPiscina p = new AreaPiscina(Double.parseDouble(args[1]));
            
            if (material != -1)
                System.out.println(p.valor(p.area(), material));
            else
                System.out.println("Material inválido");
        }
    }
}
