#install.packages('Rlab') #Instala pacote RLab se necessário
library(Rlab)

n = 5    #N variável, número de guiches
lambda = 3
mi = 0.5
tExec = 50
nTotal = 20000


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



executaTentativa = function() {
  tc = 0
  gtDisp = vector(mode="numeric", n)
  k = 0
  ctCheg = 0
  x = 0
  y = 0
  r = 0
  tm = 0
  w = 0
  
  while(TRUE) {
    z = sim_exp(1, lambda)
    if (tc + z > tExec ) {
      return (c(x, y, r, w, tm))
    }
    tc = tc + z
    k = k + 1
    ctCheg[k] = tc
    
    while (min(gtDisp) <= tc && x < k) {
      x = x + 1
      j = which.min(gtDisp)
      a = sim_exp(1, mi)
      gtDisp[j] = max(c(gtDisp[j], ctCheg[x])) + a
      tm = max(c(tm, (gtDisp[j] - ctCheg[x])  ))
      
    }
    r = max(c(0, (k-1) - x ))
    s = rbern(1, r/(r+n))
    if (s == 1) {
      k = k - 1
      y = y + 1
    }
    r = k - x
    w = y / (x + y + r)
    
    
  }
  
  
}

vector_x = vector(mode="numeric", nTotal)
vector_y = vector(mode="numeric", nTotal)
vector_r = vector(mode="numeric", nTotal)
vector_w = vector(mode="numeric", nTotal)
vector_tm = vector(mode="numeric", nTotal)


for (i in 1:nTotal) {
  
  arrResposta = executaTentativa();
  
  vector_x[i] = arrResposta[1]
  vector_y[i] = arrResposta[2]
  vector_r[i] = arrResposta[3]
  vector_w[i] = arrResposta[4]
  vector_tm[i] = arrResposta[5]
}


media_x = mean(vector_x)
media_y = mean(vector_y)
media_r = mean(vector_r)
media_w = mean(vector_w)
media_tm = mean(vector_tm)

cat("MediaX: " , media_x , " MediaY: " , media_y , " MediaR:" , media_r , " MediaW: " , media_w , " MediaTM: " , media_tm)

hist(vector_w, 145, xlim=c(0.00, 0.35), ylim=c(0, 800))    #Histograma de W
hist(vector_tm, 145, xlim=c(5, 30), ylim=c(0, 600))        #Histograma de TM


#PLOT DO E(W) COM K
vector_kw = numeric(nExec / 100)
mediaParcialAtual = 0 
for (k in seq(from = 100, to=nExec, by=100)) {
  vector_temp = head(vector_w, n=k)
  mediaParcialAtual = mean(vector_temp)
  vector_kw[k / 100] = mediaParcialAtual
}

if ( n == 5 ) {
  plot(vector_kw, type='l', axes = FALSE, ann = FALSE, ylim = c(0.20, 0.24))
  axis(2, at= seq(0.20, 0.24, 0.007), seq(0.20, 0.24, 0.007), las=2)
  axis(1, at= seq(0, 200, 20), lab = seq(0, 20000, 2000))
  title(xlab = "K")
  title(ylab = "E(W)")
  
}else {
  plot(vector_kw, type='l', axes = FALSE, ann = FALSE, ylim = c(0.120, 0.130))
  axis(2, at= seq(0.120, 0.130, 0.002), seq(0.120, 0.130, 0.002), las=2)
  axis(1, at= seq(0, 200, 20), lab = seq(0, 20000, 2000))
  title(xlab = "K")
  title(ylab = "E(W)")
  
  
}
#FIM DO PLOT#

#PLOT DO E(TM) COM K
vector_ktm = numeric(nExec / 100)
mediaParcialAtual = 0 
for (k in seq(from = 100, to=nExec, by=100)) {
  vector_temp = head(vector_tm, n=k)
  mediaParcialAtual = mean(vector_temp)
  vector_ktm[k / 100] = mediaParcialAtual
}


plot(vector_ktm, type='l', axes = FALSE, ann = FALSE, ylim = c(11.4, 12.0))
axis(2, at= seq(11.4, 12.0, 0.1), seq(11.4, 12.0, 0.1), las=2)
axis(1, at= seq(0, 200, 20), lab = seq(0, 20000, 2000))
title(xlab = "K")
title(ylab = "E(TM)")
#FIM DO PLOT#

quantidadeMaior15 = sum(vector_tm > 15)
probabilidade = quantidadeMaior15 / length(vector_tm)
cat("Probabilidade tm > 15 = ", probabilidade)

