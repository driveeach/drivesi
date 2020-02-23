class ComparaValor implements Comparador {
    public int compara(Residencia a, Residencia b) {
        if (a == null) return(-1);
        if (b == null) return(1);
        return((int)(a.casa.valor(a.casa.area()) - b.casa.valor(b.casa.area())));
    }
}
