class Carro {
  private String marca;
  private int ano;
  
  public void setMarca(String marca) {
    this.marca = marca;
  }
  
  public String getMarca() {
    return this.marca;
  }
  
  public void setAno(int ano) {
    this.ano = ano;
  }
  
  public int getAno() {
    return this.ano;
  }
  
  int compareMarca(Carro carro) {
    return this.marca.compareTo(carro.marca);
  }
  
  int compareAno(Carro carro) {
    return this.ano - carro.ano;
  } 
}