#ifndef OFOPERATORS_H
#define OFOPERATORS_H

#include "ofMacros.h"

#define SPACE typename ofOperators<_Traits>::space

template <class _Traits> class ofOperators
{
public:
	
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sSing sSing;
	
	static int inLeft(sVertex *p0, sVertex *p1, sVertex *p2);
	static int inCircle(sCell* celula, sVertex *p);
	static void flip2D(sCell *c1, sCell *c2);
	static bool inDiametralCircle(sVertex *v1, sVertex *v2, sVertex *v);
	static bool sameVertices(sVertex *v1, sVertex *v2);	
	static int orientation(ids *idvertices, sVertex **vertices OF_DMUTEXV);
	static bool isBadCell(sCell *c, space beta);
	static void getCircuncircle(sCell *c, space *coords);
	static space det3(space matrix[3][3]);
	static space dot(sVertex *v1, sVertex *v2, sVertex *v3);
	

private:
	
	static space det(space matrix[4][4]);
};

template <class _Traits> int ofOperators<_Traits>::inLeft(sVertex *p0, sVertex *p1, sVertex *p2)
{
	// Retorna 1 caso esteja a esquerda
	// Retorna 0 caso esteja a direita
	// Retorna 2 caso esteja sobre
	space i = ( (p1->getCoord(0) - p0->getCoord(0))*(p2->getCoord(1) - p0->getCoord(1)) - (p2->getCoord(0) - p0->getCoord(0))*(p1->getCoord(1) - p0->getCoord(1)) );
	
	if (i > OF_ERRO)
		return 1;
	else
	{
		if(i < - OF_ERRO)
			return 0;
		else
			return 2;
	}
}

template <class _Traits> int ofOperators<_Traits>::inCircle(sCell* celula, sVertex *p)
{
	// Retorna 1 caso p esteja dentro do circuncirculo
	// Retorna 0 caso p esteja fora do circuncirculo
	// Retorna 2 caso p esteja sobre o circuncirculo
	sVertex *p1,*p2,*p3;
	p1 = celula->getVertex(0);
	p2 = celula->getVertex(1);
	p3 = celula->getVertex(2);
	
	space a[4][4];
	
	a[0][0] = p1->getCoord(0);
	a[0][1] = p1->getCoord(1);
	a[0][2] = (p1->getCoord(0)*p1->getCoord(0))+(p1->getCoord(1)*p1->getCoord(1));
	a[0][3] = 1;
	a[1][0] = p2->getCoord(0);
	a[1][1] = p2->getCoord(1);
	a[1][2] = (p2->getCoord(0)*p2->getCoord(0))+(p2->getCoord(1)*p2->getCoord(1));
	a[1][3] = 1;
	a[2][0] = p3->getCoord(0);
	a[2][1] = p3->getCoord(1);
	a[2][2] = (p3->getCoord(0)*p3->getCoord(0))+(p3->getCoord(1)*p3->getCoord(1));
	a[2][3] = 1;
	a[3][0] = p->getCoord(0);
	a[3][1] = p->getCoord(1);
	a[3][2] = (p->getCoord(0)*p->getCoord(0))+(p->getCoord(1)*p->getCoord(1));
	a[3][3] = 1;
	
	space i = det(a);
	
	if (i > OF_ERRO)
		return 1;
	else
	{
		if(i < - OF_ERRO)
			return 0;
		else
			return 2;
	}	
}

template <class _Traits> SPACE ofOperators<_Traits>::det(space matrix[4][4])
{
	return(
		(matrix[0][0]*(
		matrix[1][1]*matrix[2][2]*matrix[3][3]+
		matrix[1][2]*matrix[2][3]*matrix[3][1]+
		matrix[1][3]*matrix[2][1]*matrix[3][2]-
		matrix[1][3]*matrix[2][2]*matrix[3][1]-
		matrix[1][2]*matrix[2][1]*matrix[3][3]-
		matrix[1][1]*matrix[2][3]*matrix[3][2]))+
		
		((-1)*matrix[1][0]*(
		matrix[0][1]*matrix[2][2]*matrix[3][3]+
		matrix[0][2]*matrix[2][3]*matrix[3][1]+
		matrix[0][3]*matrix[2][1]*matrix[3][2]-
		matrix[0][3]*matrix[2][2]*matrix[3][1]-
		matrix[0][2]*matrix[2][1]*matrix[3][3]-
		matrix[0][1]*matrix[2][3]*matrix[3][2]))+
		
		(matrix[2][0]*(
		matrix[0][1]*matrix[1][2]*matrix[3][3]+
		matrix[0][2]*matrix[1][3]*matrix[3][1]+
		matrix[0][3]*matrix[1][1]*matrix[3][2]-
		matrix[0][3]*matrix[1][2]*matrix[3][1]-
		matrix[0][2]*matrix[1][1]*matrix[3][3]-
		matrix[0][1]*matrix[1][3]*matrix[3][2]))+
		
		((-1)*matrix[3][0]*(
		matrix[0][1]*matrix[1][2]*matrix[2][3]+
		matrix[0][2]*matrix[1][3]*matrix[2][1]+
		matrix[0][3]*matrix[1][1]*matrix[2][2]-
		matrix[0][3]*matrix[1][2]*matrix[2][1]-
		matrix[0][2]*matrix[1][1]*matrix[2][3]-
		matrix[0][1]*matrix[1][3]*matrix[2][2]))
		);
}

template <class _Traits> void ofOperators<_Traits>::flip2D(sCell *c1, sCell *c2 )
{
	sCell* temp;
	int	e1 = c1->getMateVertexIndex(c2),
		e2 = c2->getMateVertexIndex(c1),
		pos;
	
	
	// atualizando os opostos
	c1->setMate(e1, c2->getMate((e2+2)%3));
	temp = c2->getMate((e2+2)%3);
	if(temp)	temp->setMate(temp->getMateVertexIndex(c2),c1);
	
	c2->setMate(e2, c1->getMate((e1+2)%3));
	temp = c1->getMate((e1+2)%3);
	if(temp)	temp->setMate(temp->getMateVertexIndex(c1),c2);

	
	c1->setMate((e1+2)%3, c2);
	c2->setMate((e2+2)%3, c1);
	
	// atualizando os vertices
	
	c1->setVertex((e1+1)%3,c2->getVertex(e2));
	c2->setVertex((e2+1)%3,c1->getVertex(e1));
	
  	pos = c1->getVertex(e1)->inSings(c1);
	if(pos >= 0) c1->getVertex(e1)->setSingCell(pos,c2);
	
	pos = c2->getVertex(e2)->inSings(c2);
	if(pos >= 0) c2->getVertex(e2)->setSingCell(pos,c1);

	pos = c1->getVertex((e1+2)%3)->inSings(c2);
	if(pos >= 0) c1->getVertex((e1+2)%3)->setSingCell(pos,c1);
	
	pos = c2->getVertex((e2+2)%3)->inSings(c1);
	if(pos >= 0) c2->getVertex((e2+2)%3)->setSingCell(pos,c2);
}

template <class _Traits> bool ofOperators<_Traits>::inDiametralCircle(sVertex *v1, sVertex *v2, sVertex *v)
{
	/*
	if ( ((v1->getCoord(0) - v->getCoord(0))*(v2->getCoord(0) - v->getCoord(0)) + (v1->getCoord(1) - v->getCoord(1))*(v2->getCoord(1) - v->getCoord(1))) < -OF_ERRO )
		return true;

	return false;
	*/

	space x,y,r,d;
	x = ( v1->getCoord(0) + v2->getCoord(0) ) / 2.0;
	y = ( v1->getCoord(1) + v2->getCoord(1) ) / 2.0;
	r = sqrt( (x-v1->getCoord(0))*(x-v1->getCoord(0)) + (y-v1->getCoord(1))*(y-v1->getCoord(1)) );
	d = sqrt( (x-v->getCoord(0))*(x-v->getCoord(0)) + (y-v->getCoord(1))*(y-v->getCoord(1)) );
	if( r < ( d - OF_ERRO) )
		return false;
	else
		return true;
	
	
	
}

template <class _Traits> bool ofOperators<_Traits>::sameVertices(sVertex *v1, sVertex *v2)
{
	return (fabs(v1->getCoord(0) - v2->getCoord(0)) < OF_ERRO ) && ( fabs(v1->getCoord(1) - v2->getCoord(1)) < OF_ERRO );
}

template <class _Traits> int ofOperators<_Traits>::orientation(ids *idvertices, sVertex **vertices OF_DMUTEXV)
{
	//Retorna 0 caso os vertices sejam colineares
	// Retorna 1 caso a orientacao foi arrumada
	// Retorna 2 caso a orientacao ja esteja correta

    // verificando orientacao da celula
    space x0,y0,x1,y1,p, n0,n1;

    x0 = vertices[1]->getCoord(0 OF_MUTEXV) - vertices[0]->getCoord(0 OF_MUTEXV);
    y0 = vertices[1]->getCoord(1 OF_MUTEXV) - vertices[0]->getCoord(1 OF_MUTEXV);

	n0 = sqrt(x0*x0 + y0*y0);
	x0 /= n0;
	y0 /= n0;

    x1 = vertices[2]->getCoord(0 OF_MUTEXV) - vertices[0]->getCoord(0 OF_MUTEXV);
    y1 = vertices[2]->getCoord(1 OF_MUTEXV) - vertices[0]->getCoord(1 OF_MUTEXV);

	n1 = sqrt(x1*x1 + y1*y1);
	x1 /= n1;
	y1 /= n1;

	p = (x0*y1 - x1*y0);

    if (p < - OF_ERRO)
    {
      sVertex *vaux = vertices[0];;
      ids temp = idvertices[0];
      
      vertices[0] = vertices[2];
      idvertices[0] = idvertices[2];
      
      vertices[2] = vaux;
      idvertices[2] = temp;

	  return 1;
    }
	else 
	{
		if (p > OF_ERRO)
			return 2;
		else
			return 0;
	}
}

template <class _Traits> bool ofOperators<_Traits>::isBadCell(sCell *c, space beta)
{
	space r, circ[2], d1, d2, d3,resp = 0;

	getCircuncircle(c,circ);

	r = sqrt(	(c->getVertex(0)->getCoord(0) - circ[0])*(c->getVertex(0)->getCoord(0) - circ[0]) + 
				(c->getVertex(0)->getCoord(1) - circ[1])*(c->getVertex(0)->getCoord(1) - circ[1]) );

	d1 = sqrt(	(c->getVertex(0)->getCoord(0) - c->getVertex(1)->getCoord(0))*
				(c->getVertex(0)->getCoord(0) - c->getVertex(1)->getCoord(0))+ 
				(c->getVertex(0)->getCoord(1) - c->getVertex(1)->getCoord(1))*
				(c->getVertex(0)->getCoord(1) - c->getVertex(1)->getCoord(1)));
	d2 = sqrt(	(c->getVertex(0)->getCoord(0) - c->getVertex(2)->getCoord(0))*
				(c->getVertex(0)->getCoord(0) - c->getVertex(2)->getCoord(0))+ 
				(c->getVertex(0)->getCoord(1) - c->getVertex(2)->getCoord(1))*
				(c->getVertex(0)->getCoord(1) - c->getVertex(2)->getCoord(1)));
	d3 = sqrt(	(c->getVertex(2)->getCoord(0) - c->getVertex(1)->getCoord(0))*
				(c->getVertex(2)->getCoord(0) - c->getVertex(1)->getCoord(0))+ 
				(c->getVertex(2)->getCoord(1) - c->getVertex(1)->getCoord(1))*
				(c->getVertex(2)->getCoord(1) - c->getVertex(1)->getCoord(1)));
				
	if(( d1 < d2 ) && ( d1 < d3))
		resp = r/d1;
	if(( d2 < d1 ) && ( d2 < d3))
		resp = r/d2;
	if(( d3 < d1 ) && ( d3 < d2))
		resp = r/d3;

	return (resp > beta);
}

template <class _Traits> void ofOperators<_Traits>::getCircuncircle(sCell *c, space *coords)
{



	
	//space a, b, num, den;
	space *c0 = c->getVertex(0)->getCoords(), *c1 = c->getVertex(1)->getCoords(), *c2 = c->getVertex(2)->getCoords();

	/*
	a = ( c1[0] - c0[0] ) * ( c1[0] - c0[0] ) + 
		( c1[1] - c0[1] ) * ( c1[1] - c0[1] );

	b = ( c2[0] - c0[0] ) * ( c2[0] - c0[0] ) + 
		( c2[1] - c0[1] ) * ( c2[1] - c0[1] );

	den = ( c1[1] - c0[1] ) * ( c2[0] - c0[0] ) - 
		  ( c2[1] - c0[1] ) * ( c1[0] - c0[0] );

	num = ( c1[1] - c0[1] ) * b - ( c2[1] - c0[1] ) * a;
	coords[0] = c0[0] + 0.5 * num / den;

	num = ( c2[0] - c0[0] ) * a - ( c1[0] - c0[0] ) * b;
	coords[1] = c0[1] + 0.5 * num / den;

	*/
	space a[3][3], bx[3][3], by[3][3];
		

	a[0][0] = c0[0]; a[0][1] = c0[1]; a[0][2] = 1;
	a[1][0] = c1[0]; a[1][1] = c1[1]; a[1][2] = 1;
	a[2][0] = c2[0]; a[2][1] = c2[1]; a[2][2] = 1;
	
	space deta = det3(a);

	bx[0][0] = (c0[0]*c0[0])+(c0[1]*c0[1]);	bx[0][1] = c0[1]; bx[0][2] = 1;
	bx[1][0] = (c1[0]*c1[0])+(c1[1]*c1[1]);	bx[1][1] = c1[1]; bx[1][2] = 1;
	bx[2][0] = (c2[0]*c2[0])+(c2[1]*c2[1]); bx[2][1] = c2[1]; bx[2][2] = 1;
	
	space detbx = det3(bx);
	
	by[0][0] = (c0[0]*c0[0])+(c0[1]*c0[1]); by[0][1] = c0[0]; by[0][2] = 1;
	by[1][0] = (c1[0]*c1[0])+(c1[1]*c1[1]); by[1][1] = c1[0]; by[1][2] = 1;
	by[2][0] = (c2[0]*c2[0])+(c2[1]*c2[1]); by[2][1] = c2[0]; by[2][2] = 1;
	
	space detby = ((-1)*(det3(by)));
		
	coords[0] = (detbx/(2*deta));
	coords[1] = (detby/(2*deta));



	space d1, d2, d3;
	d1 = sqrt( (coords[0] - c0[0])*(coords[0] - c0[0]) + (coords[1] - c0[1])*(coords[1] - c0[1]) );
	d2 = sqrt( (coords[0] - c1[0])*(coords[0] - c1[0]) + (coords[1] - c1[1])*(coords[1] - c1[1]) );
	d3 = sqrt( (coords[0] - c2[0])*(coords[0] - c2[0]) + (coords[1] - c2[1])*(coords[1] - c2[1]) );

	OF_ASSERT( fabs(d1-d2) < OF_ERRO );
	OF_ASSERT( fabs(d1-d3) < OF_ERRO );
	OF_ASSERT( fabs(d3-d2) < OF_ERRO );


}

template <class _Traits> SPACE ofOperators<_Traits>::det3(space matrix[3][3])
{
	return((matrix[0][0]*matrix[1][1]*matrix[2][2])+
		   (matrix[1][0]*matrix[2][1]*matrix[0][2])+
	 	   (matrix[0][1]*matrix[1][2]*matrix[2][0])-
		   (matrix[0][2]*matrix[1][1]*matrix[2][0])-
		   (matrix[0][1]*matrix[1][0]*matrix[2][2])-
		   (matrix[0][0]*matrix[2][1]*matrix[1][2]));
}

template <class _Traits> SPACE ofOperators<_Traits>::dot(sVertex *v1, sVertex *v2, sVertex *v)
{
	double vv1X, vv1Y, vv2X, vv2Y, dp;
	
	vv1X = v1->getCoord(0) - v->getCoord(0);
	vv1Y = v1->getCoord(1) - v->getCoord(1);
	vv2X = v2->getCoord(0) - v->getCoord(0);
	vv2Y = v2->getCoord(1) - v->getCoord(1);
	
	dp = (vv1X * vv2X) + (vv1Y * vv2Y);
	
	return(dp);
}

#undef SPACE

#endif 
