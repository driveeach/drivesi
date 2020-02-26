
#Função que popula um array de n. N representa o número de portas
#do Array, e escolhe uma porta aleatória para ter o prêmio
#essa porta aleatória será marcada com 1, as outras com 0
popularArray <- function(n) {
  array <- vector(mode="numeric", length = n)  #Inicializa um array com 0 e tamanho n   
  portaComPremio <- sample(1:n, 1)  #Sorteia uma porta para receber o premio
  array[portaComPremio] <- 1        #Coloca o premio na porta sorteada
  
  return (array)                    #Retorna o array
  
}

#Marca portas deixando apenas duas possiveis: a porta do premio e a porta selecionada.
#Se a porta selecionada for a porta do premio, deixa outra porta possivel
#As portas marcadas no array ficarão com '2'
abrirPortas <- function(portas, portaEscolhida) {
  
  abertas <- 0
  
  for (p in 1:length(portas)) {
    if (abertas == length(portas) -2){
        break    #Para ao abrir n-2 portas
    }
    if (portas[p] == 1)next                   #Nao modifica a porta se ela for o premio
    if (p == portaEscolhida)next              #Nao modifica a porta se ela for a escolhida
    portas[p] = 2                             #Marca a porta como 2, sendo ela agora aberta
    abertas <- abertas + 1
  }
  
  return (portas)
}

#Função que troca da porta escolhida para a outra porta que ainda não foi aberta
escolherTrocaDePorta <- function(portas, portaAtual){
  for (porta in 1:length(portas)) {
    if (porta != portaAtual && portas[porta] != 2) {
      return (porta)     #Retorna a porta que ainda nao foi aberta e não é a atual
    }
  }
}

#Função que sorteia uma porta para ser iniciada através de um número possível de portas n
escolherPortaInicial <- function(n) {
  porta <- sample(1:n, 1)        #Sorteia uma porta
  return (porta)
} 


#Roda uma tentativa do jogo e devolve o valor encontrado na segunda porta (a escolhida
#depois da troca)
rodarTentativaEDevolverSegundaPorta <- function(n) {
  portas <- popularArray(n)
  portaEscolhida <- escolherPortaInicial(n)
  portas <- abrirPortas(portas, portaEscolhida)    #Abre todas as portas, deixando 2 disponíveis
  segundaPortaEscolhida <- escolherTrocaDePorta(portas, portaEscolhida)
  return (portas[segundaPortaEscolhida])
}



n <- 3         #O N a ser variado entre 3 e 5, inicializando em 5

#Inicializando vetor das tentativas
arrTentativas <- vector(mode="numeric", length = 10000)

#Simulando todas as tentativas
for (i in 1:10000) {
  arrTentativas[i] = rodarTentativaEDevolverSegundaPorta(n)
}

#Inicializando o vetor que será representado no gráfico
#Cada posição do vetor representa a posição * 100 de K
#Posição 100 representa a média de todos os elementos
arrGraph <- vector(mode="numeric", length = 10000/100)

mediaParcialAtual <- 0

#Colocando as médias na média parcial, fazendo inicialmente de 100 tentativas
#Posteriormente de 200, 300, ..., 10000
for (k in seq(from = 100, to = 10000, by = 100)) {
  arrTemp <- head(arrTentativas, n=k)
  mediaParcialAtual <- mean(arrTemp)
  arrGraph[k / 100] <- mediaParcialAtual
  
}

#A media final é a mediaParcialAtual de todas as tentativas
mediaFinal <- mediaParcialAtual

#Plotando grafico, simplesmente acertando as medidas para que a labels fiquem compreensíveis
plot(arrGraph, type="l", col="black", axes = FALSE, ann = FALSE, ylim = c(0.6,0.85))
axis(2, at= seq(0.55, 0.85, 0.05), seq(0.55, 0.85, 0.05), las=2)
axis(1, at= seq(0, 100, 20), lab = seq(0, 10000, 2000))
title(xlab = "K")
title(ylab = "E(Z)")
abline(h = ((n-1)/n) , col="red")

#Print da média final
print(mediaFinal)

