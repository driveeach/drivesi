#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int chave;
	int valor;
} INFO;

typedef struct NO {
	INFO info;
	int fb;
	struct NO *fesq;
	struct NO *fdir;
} tNO;

typedef struct {
	tNO *raiz;
} ARVORE;

void rotDireita (tNO **r) {
	tNO *b = *r;
	tNO *a = b->fesq;
	b->fesq = a->fdir;
	a->fdir = b;
	a->fb = 0;
	b->fb = 0;
	*r = a;
}

void rotEsquerda (tNO **r) {
	tNO *a = *r;
	tNO *b = a->fdir;
	a->fdir = b->fesq;
	b->fesq = a;
	a->fb = 0;
	b->fb = 0;
	*r = b;	
}

void rotEsqDir (tNO **r) {
	tNO *c = *r;
	tNO *a = c->fesq;
	tNO *b = a->fdir;

	c->fesq = b->fdir;
	a->fdir = b->fesq;
	b->fesq = a;
	b->fdir = c;

	switch (b->fb) {
		case -1: 
			a->fb = 1;
			c->fb = 0;
			break;
		case 0:
			a->fb = 0;
			c->fb = 0;
			break;
		case 1:
			a->fb = 0;
			c->fb = -1;
			break;
	}
	b->fb = 0;
	*r = b;
}

void rotDirEsq (tNO **r) {
	tNO *a = *r;
	tNO *c = a->fdir;
	tNO *b = c->fesq;

	c->fesq = b->fdir;
	a->fdir = b->fesq;
	b->fesq = a;
	b->fdir = c;

	switch (b->fb) {
		case -1: 
			a->fb = 1;
			c->fb = 0;
			break;
		case 0:
			a->fb = 0;
			c->fb = 0;
			break;
		case 1:
			a->fb = 0;
			c->fb = -1;
			break;
	}
	b->fb = 0;
	*r = b;
}

int main(){

	return 1;
}
