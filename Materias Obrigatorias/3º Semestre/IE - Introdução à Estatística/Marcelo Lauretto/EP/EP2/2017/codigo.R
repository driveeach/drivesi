


#Constantes#
tTotal <- 50
lambda <- 2;
nExec <- 20000
n <- 6
th <- 2       #Variavel, pode receber 3
#Fim das Constantes#

# Funcao retirada do prof. Lauretto
# Funcao para calcular a inversa da fda da distribuicao exponencial
# u: real; probabilidade para a qual se deseja obter o respectivo 
#    quantil; 0 < u < 1
# lambda: real; parametro da distribuicao: taxa de ocorrencia do evento 
#         por unidade de tempo, volume, etc. lambda>0
inv_fda_exp = function(u, lambda=1.0) {
  return(-log(1-u)/lambda)
}

# Funcao retirada do Prof. Lauretto
# funcao para simular m valores da distribuicao exponencial
# Argumentos:
#   m: inteiro positivo indicando a quantidade de valores; m>=1 
#   lambda: real; parametro da distribuicao: taxa de ocorrencia do evento 
#           por unidade de tempo, volume, etc. lambda>0
# Saida:
#   Vetor de tamanho m contendo os valores sorteados 

sim_exp = function(m=1, lambda=1.0) {
  
  if (m<1 | lambda<=0) {
    stop('Erro na funcao sim_exp: verifique argumentos.')
  }
  
  # Sorteio de m valores da uniforme (0,1)
  u = runif(m)
  # Inversao para obter x ~ exponencial(lambda)
  x = inv_fda_exp(u, lambda)
  return(x)
  
} 


rodaTentativa <- function() {
  x <- 0
  y <- 0
  tr <- 0
  t <- vector(mode = "numeric", n)
  
  while(TRUE){
    z <- sim_exp(1, lambda)
    
    if (tr + z > tTotal) {
      #Excedeu o tempo
      return (c(x, y, y/(x+y) ))
      break
    }
    
    tr <- tr + z
    
    for(i in 1:7) {
      if (i == 7){
        y <- y + 1 
        break
      }
      if (t[i] <= tr) {
        x <- x + 1
        t[i] <- tr + th
        break
      }
    }
    
  }
  
}

vector_x = vector(mode= "numeric", nExec)
vector_y = vector(mode = "numeric", nExec)
vector_w = vector(mode = "numeric", nExec)

#Cada posição do vetor representa a posição * 100 de K
#Posição 100 representa a média de todos os elementos
arrGraph <- vector(mode="numeric", length = nExec/100)

media_x = mean(vector_x)
media_y = mean(vector_y)

media_x
media_y



for (i in 1:nExec){
  resultado <- rodaTentativa()
  vector_x[i] <- resultado[1]
  vector_y[i] <- resultado[2]
  vector_w[i] <- resultado[3]
}
mediaParcialAtual <- 0

for (k in seq(from = 100, to = nExec, by = 100)) {
  arrTemp <- head(vector_w, n=k)
  mediaParcialAtual <- mean(arrTemp)
  arrGraph[k/100] <- mediaParcialAtual
  
}

#Plotando grafico, simplesmente acertando as medidas para que a labels fiquem compreensíveis
plot(arrGraph, type='l', axes = FALSE, ann = FALSE, ylim = c(0.250, 0.265))
axis(2, at= seq(0.250, 0.265, 0.002), seq(0.250, 0.265, 0.002), las=2)
axis(1, at= seq(0, 200, 50), lab = seq(0, nExec, 5000))
title(xlab = "K")
title(ylab = "E(W)")


hist(vector_w, 145)
mean(vector_w)
quantidadeMaior20 <- sum(vector_w >= 0.2)
probabilidade <- quantidadeMaior20 / length(vector_w)
mediaParcialAtual
probabilidade
mean (vector_x)
mean (vector_y)
mean (vector_w)

