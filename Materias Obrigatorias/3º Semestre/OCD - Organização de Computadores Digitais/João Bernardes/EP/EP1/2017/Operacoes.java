public class Operacoes extends Conversoes {
    
    int[] somaBinarios(int[] bin1, int[] bin2, boolean subtracao) {
        int[] resultado, binarioCorrigido;
        binarioCorrigido = Conversoes.igualaBits(bin1, bin2);
        if (bin1.length == bin2.length) {
            resultado = new int[bin1.length + 1];
            for (int i = resultado.length - 1; i > 0; i--) {
                resultado[i] += bin1[i - 1] + bin2[i - 1];
                if (resultado[i] >= 2) {
                    if (i == 1) {
                        if (subtracao == true) {
                            resultado[i] = resultado[i] % 2;
                        }
                    }
                    resultado[i - 1] = resultado[i] / 2;
                    resultado[i] = resultado[i] % 2;
                }
            }
        } else if (binarioCorrigido.length == bin1.length) {
            resultado = new int[binarioCorrigido.length + 1];
            for (int i = resultado.length - 1; i > 0; i--) {
                resultado[i] += bin1[i - 1] + binarioCorrigido[i - 1];
                if (resultado[i] >= 2) {
                    if (i == 1)
                        if (subtracao == true) {
                            resultado[i] = resultado[i] % 2;
                        }
                        resultado[i - 1] = resultado[i] / 2;
                        resultado[i] = resultado[i] % 2;
                    }
                }
            } else {
                resultado = new int[binarioCorrigido.length + 1];
                for (int i = resultado.length - 1; i > 0; i--) {
                    resultado[i] += binarioCorrigido[i - 1] + bin2[i - 1];
                    if (resultado[i] >= 2) {
                        if (i == 1)
                            if (subtracao == true) {
                                resultado[i] = resultado[i] % 2;
                            }
                            resultado[i - 1] = resultado[i] / 2;
                            resultado[i] = resultado[i] % 2;
                        }
                    }
                }
                return Conversoes.corrige0(resultado);
            }
            
            void imprimeBinario(int[] binario) {
                for (int i = 0; i < binario.length; i++) {
                    System.out.print(binario[i] + " ");
                }System.out.println();
            }
            
            int[] complementoDe2(int[] binario) {
                for (int i = 0; i <= binario.length - 1; i++) {
                    if (binario[i] == 0) {
                        binario[i] = 1;
                    } else {
                        binario[i] = 0;
                    }
                }
                int[] numero1 = { 0, 1 };
                
                binario = somaBinarios(binario, numero1, true);
                return binario;
            }
            
            public int[] subtracaoBinarios(int[] binario1, int[] binario2) {
        // binario1 - binario2, ja definido pelo usuario
        // binario2 negativo.
                int[] resultado = { 0 };
                if (Conversoes.numerosIguais(binario1, binario2) == true) {
                    return resultado;
                }
                
                if (Conversoes.numerosIguais(Conversoes.comparaMaior(binario1, binario2), binario1) == true) {
                    int[] aux;
                    aux = binario1;
                    binario1 = binario2;
                    binario2 = aux;
                }
                int[] binarioComSinal1, binarioComSinal2, subtracao;
                binarioComSinal1 = new int[binario1.length + 1];
                binarioComSinal2 = new int[binario2.length + 1];
        for (int i = binario1.length - 1; i >= 0; i--) { // adiciona bit do //
            // sinal
            binarioComSinal1[i + 1] = binario1[i];
        }
        for (int i = binario2.length - 1; i >= 0; i--) {
            binarioComSinal2[i + 1] = binario2[i];
        }
        binarioComSinal2 = complementoDe2(binarioComSinal2);
        subtracao = somaBinarios(binarioComSinal1, binarioComSinal2, true);
        if (subtracao[0] == 1)
            subtracao = complementoDe2(subtracao);
        return Conversoes.corrige0(subtracao);
    }
    
    public int[] divisaoBinario(int[] dividendo, int[] divisor) {
     if(Conversoes.verificaZero(divisor) == true)
        return null;
    if (Conversoes.comparaMaior(dividendo, divisor) == divisor)
        return null;
    int[] resto;
    int[] numero1 = { 1 };
    int[] quociente = { 0 };
    resto = subtracaoBinarios(dividendo, divisor);
    quociente = somaBinarios(quociente, numero1, false);
    while (Conversoes.verificaZero(resto) == false && Conversoes.comparaMaior(resto, divisor) == resto) {
        quociente = somaBinarios(quociente, numero1, false);
        resto = subtracaoBinarios(resto, divisor);
    }
    if (Conversoes.comparaMaior(resto, divisor) == null) {
        resto = subtracaoBinarios(resto, divisor);
        quociente = somaBinarios(quociente, numero1, false);
    }
    imprimeBinario(resto);
    return quociente;
}

}
