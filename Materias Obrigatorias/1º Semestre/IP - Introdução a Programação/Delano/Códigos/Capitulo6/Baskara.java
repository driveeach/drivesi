class Baskara {
    
  public void raizes (double a, double b, double c) {
    double delta = b * b - 4 * a * c;
    
    if (delta > 0) {
      System.out.println("x'  = " + (-b - Math.sqrt(delta))/(2*a));
      System.out.println("x'' = " + (-b + Math.sqrt(delta))/(2*a));
    }
    else if (delta == 0) {
      System.out.println("x' = x'' = " + (-b/(2*a)));
    }
    else {
        System.out.println("x'  = " + (-b/(2*a)) + " - " + (Math.sqrt(-delta)/(2*a)) + "i");
        System.out.println("x'' = " + (-b/(2*a)) + " + " + (Math.sqrt(-delta)/(2*a)) + "i");
    }
  }
}