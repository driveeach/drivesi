class Seis {
  int [] array;
  
  void atribuiArray(int [] array) {
    this.array = array;
  }
  
  int nRepeticoes (int x) {
    int contador = 0;
    for (int i = 0; i<array.length; i++) {
      if (array[i] == x) contador++;
    }
    return contador;
  }
  
  void atribuiArray2 (int [] array) {
    for (int ult = array.length-1; ult>0; ult--) {
      for (int i = 0; i<ult; i++) {
        if (array[i] > array [i+1]) {
          int aux = array[i];
          array[i] = array[i+1];
          array[i+1] = aux;
        }
      }
    }
    this.array = array;
  }
  public static void main (String [] args) {
    int [] array = {15,3,1,26,8,4}; 
  }
}