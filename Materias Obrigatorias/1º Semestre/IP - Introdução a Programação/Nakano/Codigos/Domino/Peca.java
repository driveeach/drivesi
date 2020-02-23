class Peca {
    int a, b; //numero de face em dada peca
    void print () {
        System.out.print ("[" + a + "*" + b + "] ");
    }
    public static void main (String[] args){
        Peca p = new Peca ();
        p.a=0;
        p.b=4;
        p.print ();
    }
}


