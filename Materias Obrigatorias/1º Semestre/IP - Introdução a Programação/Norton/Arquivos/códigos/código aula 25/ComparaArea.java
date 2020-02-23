class ComparaArea implements Comparador {
    public int compara(Residencia a, Residencia b) {
        if (a == null) return(-1);
        if (b == null) return(1);
        return((int)(a.area() - b.area()));
        //return((int)(a.casa.valor(a.area()) - b.casa.valor(b.area())));
    }
}
