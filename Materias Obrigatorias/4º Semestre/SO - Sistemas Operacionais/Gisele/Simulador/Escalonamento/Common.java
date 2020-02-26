public class Common {

  static public int s2i (String s) {
    int i = 0;

    try {
      i = Integer.parseInt(s.trim());
    } catch (NumberFormatException nfe) {
      System.out.println("NumberFormatException: " + nfe.getMessage());
    }
    return i;
  }

  static public double R1 () {
    java.util.Random generator = new java.util.Random(System.currentTimeMillis());
    double U = generator.nextDouble();
    while (U < 0 || U >= 1) {
      U = generator.nextDouble();
    }
    double V = generator.nextDouble();
    while (V < 0 || V >= 1) {
      V = generator.nextDouble();
    }
    double X =  Math.sqrt((8/Math.E)) * (V - 0.5)/U;
    if (!(R2(X,U))) { return -1; }
    if (!(R3(X,U))) { return -1; }
    if (!(R4(X,U))) { return -1; }
    return X;
  }

  static public boolean R2 (double X, double U) {
    if ((X * X) <= (5 - 4 * Math.exp(.25) * U)) {
      return true;
    } else {
      return false;
    }
  }

  static public boolean R3 (double X, double U) {
    if ((X * X) >= (4 * Math.exp(-1.35) / U + 1.4)) {
      return false;
    } else {
      return true;
    }
  }

  static public boolean R4 (double X, double U) {
    if ((X * X) < (-4 * Math.log(U))) {
      return true;
    } else {
      return false;
    }
  }

}

