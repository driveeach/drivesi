public class Conversoes {
    public static int max(int a, int b){
        if(a>b) return a;
        return b;
    }

    public static int min(int a, int b){
        if(a<b) return a;
        return b;
    }
    
    public static int[] corrige0(int[] binario) {
        int j = 0;
        for (int i = 0; i < binario.length; i++) {
            if (binario[i] == 0) {
                j++;
            }
            if (binario[i] == 1)
                break;
        }
        if (j == binario.length)
            return binario;
        int[] binarioSem0 = new int[binario.length - j];
        for (int i = 0; i < binarioSem0.length; i++) {
            binarioSem0[i] = binario[j];
            j++;
        }
        return binarioSem0;
    }
    
    public static int[] igualaBits(int[] bin1, int[] bin2) {
        int[] binarioCorrigido;
        
        if (max(bin1.length, bin2.length) == bin1.length) {
            binarioCorrigido = new int[bin1.length];
            int j = binarioCorrigido.length - 1;
            for (int i = bin2.length - 1; i >= 0; i--) {
                binarioCorrigido[j] = bin2[i];
                j--;
            }
        } else {
            binarioCorrigido = new int[bin2.length];
            int j = binarioCorrigido.length - 1;
            for (int i = bin1.length - 1; i >= 0; i--) {
                binarioCorrigido[j] = bin1[i];
                j--;
            }
        }
        return binarioCorrigido;
    }
    
    public static int[] comparaMaior(int[] binario1, int[] binario2) {
        if (binario1.length > binario2.length) {
            return binario1;
        } else if (binario2.length > binario1.length)
        return binario2;
        for (int i = 0; i < min(binario1.length, binario2.length); i++) {
            if (binario1[i] == 0 && binario2[i] == 1) {
                return binario2;
            } else if (binario2[i] == 0 && binario1[i] == 1)
            return binario1;
        }
        return null; // sao iguais
    }
    
    public static boolean numerosIguais(int[] binario1, int[] binario2) {
        if (comparaMaior(binario1, binario2) != null)
            return false;
        for (int i = 0; i < min(binario1.length, binario2.length); i++) {
            if (binario1[i] != binario2[i])
                return false;
        }
        return true;
    }
    
    public static boolean verificaZero(int[] resto) {
        for (int i = 0; i < resto.length; i++) {
            if (resto[i] == 1)
                return false;
        }
        return true;
    }
    
    public static int[] converteParaBinario(int decimal) {
        String binaria = Integer.toBinaryString(decimal);
        return converteString(binaria);
    }
    
    public static int[] converteString(String numero1) {
        String conversao[] = numero1.split("");
        int[] binario = new int[conversao.length];
        for (int i = 0; i < conversao.length; i++) {
            binario[i] = Integer.parseInt(conversao[i]);
        }
        return binario;
    }
}
