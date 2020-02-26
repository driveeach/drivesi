#include <stdio.h>

int main() {
	int y, *p, x;
	y = 0;
	p = &y;
	x = *p;
	x = 4;
	(*p)++;
	x--;
	(*p) += x;
	printf ("y = %d\nx = %d p = %d", y, x, *p);
	return 0;
}
