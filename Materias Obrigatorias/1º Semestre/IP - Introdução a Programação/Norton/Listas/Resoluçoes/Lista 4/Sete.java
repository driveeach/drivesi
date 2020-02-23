class Sete {
  public static boolean Inverso (char [] num) {
  boolean teste = false;
    for (int i = 0; i<num.length /2; i++) {
      if (num [i] == num[num.length - 1 - i]) {
       teste = true;
      }
       else teste=false;
    }
    return teste;
  }
  public static void main (String [] args) {
    char [] num1 = {'2', '4', '2','1', '2','4', '2'};
    System.out.println (Inverso (num1));
  }
}