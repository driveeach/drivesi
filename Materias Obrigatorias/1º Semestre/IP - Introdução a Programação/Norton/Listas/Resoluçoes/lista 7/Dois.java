class Dois {
  public static boolean implementa (String a, String b) {
    if (a.length() == b.length()) {
      for (int i = 0; i<a.length(); i++) {
        if (a.charAt(i) != b.charAt(i)) return false;
      }
    }
    else return false;
    return true;
  }
  public static void main (String [] args) {
    System.out.println(implementa ("fernando","fernanda"));
  }
}