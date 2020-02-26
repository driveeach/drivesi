Bubble <- read.table ("sample.txt", header=FALSE, sep=";")
Heap <- read.table ("sampleheapsort.txt", header=FALSE, sep=";")
tiff ("fig2.tiff")
plot (Bubble[1:50,], xlab="tamanho", ylab="t(ns)")
#plot (Heap[1:200,], xlab="tamanho", ylab="t(ns)", col=4)
points (Heap[1:50,], col=4)
dev.off();

