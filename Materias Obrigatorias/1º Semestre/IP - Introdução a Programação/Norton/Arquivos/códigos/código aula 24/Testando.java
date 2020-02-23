class Testando implements Teste {
    public int x(int y) {
        return(y);
    }
    
    public void y() {
        System.out.println();
    }
    
    public static void main(String[] args) {
        Minha m = new Minha();
        
        System.out.println(m.calculo(new Testando() ,2));
    }
}
