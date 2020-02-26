n <- 1:33  # este é o número de elementos da entrada
cap <- 1e9    # esta é a quantidade de operações que a máquina realiza por segundo

# a passagem de número de elementos para número de operações não é tão fácil.

nop1 <- n;
nop2 <- n^2
nop3 <- n^3
nop4 <- n^10
nop5 <- 2^n
nop6 <- 3^n


tiff ("fig8.tiff");
plot (n, nop6, col=1, type="l", xlab="size", ylab="nop");
lines (n, nop4, col=3);
dev.off();

day <- 1e9*60*60*24;
year <- 365*day;
sec <- 100*year;


