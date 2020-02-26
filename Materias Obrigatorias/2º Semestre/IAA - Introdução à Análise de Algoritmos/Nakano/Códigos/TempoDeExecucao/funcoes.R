n <- 1:20  # este é o número de elementos da entrada
cap <- 1e9    # esta é a quantidade de operações que a máquina realiza por segundo

# a passagem de número de elementos para número de operações não é tão fácil.

nop1 <- n;
nop2 <- n^2
nop3 <- n^3
nop4 <- n^10
nop5 <- 2^n
nop6 <- 3^n


tiff ("fig3.tiff");
plot (n, t6, col=1, type="l", xlab="size", ylab="t(s)");
lines (n, t5, col=2);
lines (n, t4, col=3);
lines (n, t3, col=4);
lines (n, t2, col=5);
lines (n, t1, col=6);
dev.off();

day <- 1e9*60*60*24;
year <- 365*day;
sec <- 100*year;

tiff ("fig4.tiff");
n <- 1:100
t1 <- n;
t2 <- n^2
t3 <- n^3
t4 <- n^10
t5 <- 2^n
t6 <- 3^n
plot (n, t6/sec, col=1, type="l", xlab="size", ylab="t(sec)");
lines (n, t5/sec, col=2);
lines (n, t4/sec, col=3);
lines (n, t3/sec, col=4);
lines (n, t2/sec, col=5);
lines (n, t1/sec, col=6);
dev.off();

# em geral o speedup é uma constante multiplicativa.

tiff ("fig5.tiff");
n <- 1:100
t1 <- n;
t2 <- n/10;
t3 <- n/100;

plot (n, t1/sec, col=1, type="l", xlab="size", ylab="t(s)");
lines (n, t2/sec, col=2);
lines (n, t3/sec, col=3);
dev.off();

# em geral o speedup é uma constante multiplicativa.

tiff ("fig6.tiff");
n <- 1:100
t1 <- n^2;
t2 <- n^2/10;
t3 <- n^2/100;

plot (n, t1/sec, col=1, type="l", xlab="size", ylab="t(s)");
lines (n, t2/sec, col=2);
lines (n, t3/sec, col=3);
dev.off();


tiff ("fig7.tiff");
n <- 1:100
t1 <- n^10;
t2 <- n^10/10;
t3 <- n^10/100;

plot (n, t1/sec, col=1, type="l", xlab="size", ylab="t(s)");
lines (n, t2/sec, col=2);
lines (n, t3/sec, col=3);
dev.off();


tiff ("fig8.tiff");
n <- 1:82
t1 <- 2^n; t1[65:82]=NA;
t2 <- 2^n/10;
t3 <- 2^n/100;

plot (n, t1/sec, col=1, type="l", xlab="size", ylab="t(sec)", lab=c(10, 5, 7));
lines (n, t2/sec, col=2);
lines (n, t3/sec, col=3);
dev.off();

