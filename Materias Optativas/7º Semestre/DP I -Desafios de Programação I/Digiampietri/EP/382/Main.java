import java.util.Scanner;

public class Main {

    public static void perfection(int in) {
        Integer num = new Integer(in);
        String numero = num.toString();
        int cont = numero.length();
        //justificar à direita
        while (cont < 5) {
            numero = " " + numero;
            cont++;
        }
        if (in == 1) {
            System.out.println(numero + "  DEFICIENT");
        } else {
            int sum = 1;
            for (int i = 2; i < in; i++) {
                //determinar se eh divisor
                if (in % i == 0) {
                    sum += i;
                    if (sum > in) {
                        System.out.println(numero + "  ABUNDANT");
                        break;
                    }
                }
            }
            if (sum == in) {
                System.out.println(numero + "  PERFECT");
            } else if (sum < in) {
                System.out.println(numero + "  DEFICIENT");
            }
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int num[] = new int[100];
        int i = 0;
        while (scan.hasNext()) {
            num[i] = scan.nextInt();
            if (num[i] == 0) {
                break;
            }
            i++;
        }
        System.out.println("PERFECTION OUTPUT");
        for (i = 0; i < num.length; i++) {
            if (num[i] == 0) break;
            perfection(num[i]);
        }
        System.out.println("END OF OUTPUT");
    }
}