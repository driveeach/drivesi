#ifndef _POINT3DUTILS_H
#define _POINT3DUTILS_H



#define _ERRO  1.0e-7
#include <assert.h>

#include <math.h>

// Point deve possuir o operador [] para acessar as coordenadas
template<class P> 
class Point3DUtils {
  
public:

   typedef P                        Point;
   typedef P                        Vector;
   typedef typename P::TPCoord      Coordinate;
   typedef double                   TReal;


public: 


   
   
public:
   // poU = piW + piV
   static 
   void 
   Add( Vector &piW,  
        Vector &piV,
        Vector &poU){
      
      poU[0] = piW[0] + piV[0];
      poU[1] = piW[1] + piV[1];
      poU[2] = piW[2] + piV[2];
   };
   
   // poU = piW - piV
   static 
   void 
   Minus( Vector &piW, // 
        Vector &piV,
        Vector &poU){
      
      poU[0] = piW[0] - piV[0];
      poU[1] = piW[1] - piV[1];
      poU[2] = piW[2] - piV[2];
   };

   // poV = piValue * piV
   static
   void 
   MultiplyBy(
      TReal   piValue,
      Vector &piV,
      Vector &poV
   ){
      poV[0] = piValue * piV[0];
      poV[1] = piValue * piV[1];
      poV[2] = piValue * piV[2];
   };

   static
   void
   GetTriangleNormal(
         P &piA, P &piB, P &piC, // vertices do triangulo
         Vector &poNormal        // a normal calculada
   );

   static
   void
   GetVector(
         P &piA, P &piB, // vertices que definen o vetor
         Vector &poVector // o vetor B-A
   );
   
   //calcula a norma ao quadrado do vetor
   static
   double
   Norm2( Vector  &piV ){
      return piV[0]*piV[0] + piV[1]*piV[1] + piV[2]*piV[2];
   };   
   
   //calcula a norma do vetor
   static
   double
   Norm( Vector  &piV ){
      return sqrt(Norm2(piV));
   };  
   
   
   
   //Normaliza o vetor;
   static
   void
   Normalize(Vector &piV,   //Vetor a ser normalizado
             Vector &poV    //Vetor normalizado
   ){
      static TReal _fp1;
      _fp1 = sqrt(Norm2(piV));
      poV[0] /= _fp1;      poV[1] /= _fp1;      poV[2] /= _fp1;
   }   

     
   static
   double // Distancia ao quadrado do ponto A ao ponto B
   GetDistance2(
         P &piA, P &piB
   );
   
   
   static
   double // Distancia ao quadrado do ponto A ao ponto B
   GetDistance(
         P &piA, P &piB
   ){
      return sqrt(GetDistance2(piA, piB));
   }
   
   // Projeta um ponto no segmento AB
   static
   float // retorno<0.0, se ponto projetado fora do segmento para o lado de piA
        // 0.0 < retorno < 1.0, ponto dentro do segmento
        // 1.0<retorno, se ponto projetado fora do segmento para o lado de piB
ProjectPointOnSegment( 
         P &piA,
         P &piB,
         P &piPoint,      // ponto a ser projetado
         P &poPojected    // local da projecao
   );
	
   static
   void
   ProjectPointOnLine2(
      Point  &piPA,  //ponto na reta
      Point  &piPB,  //ponto na reta
      Point  &piPoint, //ponto a ser projetado
      Point  &poPoint  //ponto projetado   
   );
   
	static
   void
   ProjectPointOnLine(
      Point  &piBase,  //ponto na reta
      Vector &piLine,  //vetor UNITARIO que define a reta
      Point  &piPoint, //ponto a ser projetado
      Point  &poPoint  //ponto projetado
   );
	
   
   //calcula o produto interno dos vetores
   static
   TReal
   InnerProduct(
         Vector  &piU, 
         Vector  &piV
   ){
      return piU[0]*piV[0] + piU[1]*piV[1] + piU[2]*piV[2];
   };   
 

   // return the signed cossine between the vectors n1 and n2
   // n1 and n2 don't need to be normalized
   static
   TReal 
   Cos(Vector &n1, Vector &n2){  
   
      return  (n1[0]*n2[0] + n1[1]*n2[1] + n1[2]*n2[2])  / 
              (sqrt(n1[0]*n1[0]+n1[1]*n1[1]+n1[2]*n1[2])*
               sqrt(n2[0]*n2[0]+n2[1]*n2[1]+n2[2]*n2[2]));

   };
   
   // return the signed cossine between the vectors ba, bc
   static
   TReal 
   Cos(Point &a, Point &b, Point &c){ 
      static Vector ba, bc;
      GetVector(b,a, ba);
      GetVector(b,c, bc);
      return Cos(ba,bc);
   };
   
};

//##############################################################################
//##############################################################################
//##############################################################################
//------------------------------------------------------------------------------
   template<class P>
   void
   Point3DUtils<P>::
GetTriangleNormal(
         P  &piA, // vertices do triangulo
         P  &piB,
         P  &piC,
         Vector &poNormal // a normal calculada
   ){
      
   Vector vab;
   Vector vac;
   GetVector(piA,piB, vab);
   GetVector(piA,piC, vac);
   
   poNormal[0] = vab[1]*vac[2] - vac[1]*vab[2];
	poNormal[1] = vab[2]*vac[0] - vab[0]*vac[2];
	poNormal[2] = vab[0]*vac[1] - vac[0]*vab[1];
}


//------------------------------------------------------------------------------
   template<class P>
   void
   Point3DUtils<P>::
GetVector(
         P  &piA, // vertices que definen o vetor
         P  &piB,
         Vector &poVector // o vetor B-A, ou seja A->B
   ){
  
   poVector[0] = piB[0] - piA[0];
   poVector[1] = piB[1] - piA[1];
   poVector[2] = piB[2] - piA[2];
      
}

//------------------------------------------------------------------------------
 
   template<class P>
   double // Distancia ao quadrado do ponto A ao ponto B
   Point3DUtils<P>::
GetDistance2(
         P  &piA, // vertices do triangulo
         P  &piB
   ){
  
   return pow(piA[0] - piB[0], 2) + 
          pow(piA[1] - piB[1], 2) + 
          pow(piA[2] - piB[2], 2);
      
}
  

//------------------------------------------------------------------------------
   // Projeta um ponto no segmento AB
   template<class P>
   float // retorno<0.0, se ponto projetado fora do segmento para o lado de piA
        // 0.0 < retorno < 1.0, ponto dentro do segmento
        // 1.0<retorno, se ponto projetado fora do segmento para o lado de piB
   Point3DUtils<P>::
ProjectPointOnSegment( 
         P &piA,
         P &piB,
         P &piPoint,      // ponto a ser projetado
         P &poPojected    // local da projecao
   ){
   
	//Encontra o vetor que liga os vertices da aresta
   Vector v;
   GetVector(piA, piB, v);
				
	//Encontra o vetor que sera projetado
   Vector w;
   GetVector(piA, piPoint, w);
   			
	double  innerProduct = InnerProduct(v,w);
   double  normaV2 = Norm2(v);
	
   assert(fabs(normaV2)>_ERRO);	
      
	Vector projectedVector;
	projectedVector[0] = ( innerProduct / normaV2 ) * v[0];
	projectedVector[1] = ( innerProduct / normaV2 ) * v[1];
	projectedVector[2] = ( innerProduct / normaV2 ) * v[2];
				
	poPojected[0] = projectedVector[0] + piA[0];
	poPojected[1] = projectedVector[1] + piA[1];
	poPojected[2] = projectedVector[2] + piA[2];
						
	double alpha;
	//Verifica se o ponto esta dentro da aresta
	if ( ( v[0] * v[0] ) < _ERRO ) {
		if ( ( v[1] * v[1] ) < _ERRO ){
         assert(fabs(v[2])>_ERRO);
			alpha = ( poPojected[2] - piA[2] ) / v[2];
		}else{
         assert(fabs(v[1])>_ERRO);
			alpha = ( poPojected[1] - piA[1] ) / v[1];	
      }
   }else{
      assert(fabs(v[0])>_ERRO);
		alpha = ( poPojected[0] - piA[0] ) / v[0];
   }
	
   return alpha;
}

   template<class P>
   void
   Point3DUtils<P>::
ProjectPointOnLine(
      Point  &piBase,  //ponto na reta
      Vector &piLine,  //vetor UNITARIO que define a reta
      Point  &piPoint, //ponto a ser projetado
      Point  &poPoint  //ponto projetado
   ){

   static Vector _v1;
      
   GetVector(piBase, piPoint, _v1); 
   double s = InnerProduct(_v1, piLine);
   
   poPoint[0] = piBase[0] + s*piLine[0]; 
   poPoint[1] = piBase[1] + s*piLine[1];
   poPoint[2] = piBase[2] + s*piLine[2];
}

   template<class P>
   void
   Point3DUtils<P>::
ProjectPointOnLine2(
      Point  &piPA,  //ponto na reta
      Point  &piPB,  //ponto na reta
      Point  &piPoint, //ponto a ser projetado
      Point  &poPoint  //ponto projetado   
   ){
   
   Vector r;
   GetVector(piPA, piPB, r);
   Normalize(r,r);
   ProjectPointOnLine(piPA,r,piPoint,poPoint);
}

#endif
