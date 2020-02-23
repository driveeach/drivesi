class Prisma
{
  double altura;
  double areaDaBase;
  
  void carregaAltura(double a)
  {
    altura = a;
  }
  
  void carregaAreaDaBase (double a)
  {
    areaDaBase = a;
  }
  
  double volume()
  {
    return areaDaBase * altura;
  }
}

class Quadrado
{
  double lado;
  void carregaLado(double l)
  {
    lado = l;
  }
  double area()
  {
    return lado * lado;
  }
}

class TrianguloRetangulo
{
  double cateto1;
  double cateto2;
  
  // note a indentação super-compacta
  
  void carregaCateto1(double c) {cateto1 = c;}
  void carregaCateto2(double c) {cateto2 = c;}
  
  double area()
  {
    return cateto1 * cateto2/2.0;
  }
}

// Quadrado q = new Quadrado()
// TrianguloRetangulo tr = new TrianguloRetangulo ()
// Prisma prismaBaseQuadrada = new Prisma()
// Prisma prismaBaseTriangular = new Prisma()
// q.carregaLado(10);
// tr.carregaCateto1(20)
// tr.carregaCateto2(30)
// prismaBaseQuadrada.carregaAltura(3);
// prismaBaseTriangular.carregaAltura(1);
// prismaBaseQuadrada.carregaAreaDaBase(q.area());
// prismaBaseTriangular.carregaAreaDaBase(tr.area());
//
// if(prismaBaseQuadrada.volume() > prismaBaseTriangular.volume())
//   System.out.println("O prisma de base quadrada tem maior volume"); 
// else 
//   if(prismaBaseTriangular.volume() > prismaBaseQuadrada.volume())
//     System.out.println("O prisma de base triangular tem maior volume"); 
//   else
//     System.out.println("O prisma de base triangular tem maior volume"); 

