#ifndef OFGEOMETRIC_H
#define OFGEOMETRIC_H
#include <cmath>
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofSing.h"
namespace of
{
#define SPACE typename ofGeometric<_Traits>::space
//#define SMESH typename ofGeometric<_Traits>::sMesh
template <class _Traits> class ofGeometric
{
public:
	
	typedef typename _Traits::space 	space;
	typedef typename _Traits::ids 		ids;
	typedef typename _Traits::sVertex 	sVertex;
	typedef typename _Traits::sCell 	sCell;
	
	typedef ofSing<_Traits>			 	sSing;
	typedef typename _Traits::sMesh		sMesh;
	
	ofGeometric(sMesh *_mesh = NULL, double _erro = OF_ERRO);
	~ofGeometric();
	
	void setMesh(sMesh *_mesh);
//	sMesh* getMesh();
	
	int inLeft(sVertex *p0, sVertex *p1, sVertex *p2 OF_DMUTEXVD);
	int inLeft(sVertex *p0, sVertex *p1, space coord0, space coord1 OF_DMUTEXVD);
	int inLeft(ids p0, ids p1, ids p2 OF_DMUTEXVD);
	int inLeft(ids p0, ids p1, space coord0, space coord1 OF_DMUTEXVD);
	space dist(sVertex *p0, sVertex *p1 OF_DMUTEXVD);
	////////////////////////////////////////////////
	space dist(space *p0, space *p1 OF_DMUTEXV);
	////////////////////////////////////////////////
	int inCircle(sCell *c, sVertex *p OF_DMUTEXVD);
//	int inCircle( ids c, ids p OF_DMUTEXVD);
	int inCircle(sVertex *p1, sVertex *p2, sVertex *p3, sVertex *p);
	bool inDiametralCircle(sVertex *v1, sVertex *v2, sVertex *v OF_DMUTEXVD);
	bool sameVertices(sVertex *v1, sVertex *v2 OF_DMUTEXVD);	
	int orientation2D(ids *idvertices, sVertex **vertices OF_DMUTEXVD);
	int orientation3D(ids *idvertices, sVertex **vertices OF_DMUTEXVD);
	bool isBadCell(sCell *c, space beta OF_DMUTEXVD);
	void getCircuncircle(sCell *c, space *coords OF_DMUTEXVD);
	void getCircuncircle(space *c0, space *c1, space *c2, space *coords OF_DMUTEXV);
	space det3(space matrix[3][3]);
	space det(space matrix[4][4]);
	space dot(ids p1, ids p2, space *coords OF_DMUTEXVD);
    space vecAngle(ids p1, ids p_ang, ids p2);
    space norm2d(space *coords);
    space areaTriangle(space *x, space *y, space *z);
    bool isDelaunay(ids idcell);
    space areaTriangle(sCell *c);
	
	void flip2D(ids c1, ids c2 OF_DMUTEXVD);
//private:
protected:
	double erro;
	int temp;
	sMesh *mesh;
};
template <class _Traits> ofGeometric<_Traits>::ofGeometric(sMesh *_mesh, double _erro)
{
	mesh = _mesh;
	temp = 0;
	erro = _erro;
}
template <class _Traits> ofGeometric<_Traits>::~ofGeometric()
{
}
template <class _Traits> void ofGeometric<_Traits>::setMesh(sMesh *_mesh)
{
	mesh = _mesh;
}
//template <class _Traits> SMESH* ofGeometric<_Traits>::getMesh()
//{
//	return mesh;
//}
template <class _Traits> SPACE ofGeometric<_Traits>::dist(sVertex *p0, sVertex *p1 OF_DMUTEXV)
{
	return sqrt((p0->getCoord(0) - p1->getCoord(0))*(p0->getCoord(0) - p1->getCoord(0)) +
	(p0->getCoord(1) - p1->getCoord(1))*(p0->getCoord(1) - p1->getCoord(1)));
}
template <class _Traits> SPACE ofGeometric<_Traits>::dist(space *p0, space *p1 OF_DMUTEXV)
{
	return sqrt((p0[0] - p1[0])*(p0[0] - p1[0]) + (p0[1] - p1[1])*(p0[1] - p1[1]));
}
template <class _Traits> int ofGeometric<_Traits>::inLeft(sVertex *p0, sVertex *p1, sVertex *p2 OF_DMUTEXV)
{
	// Retorna 1 caso esteja a esquerda
	// Retorna 0 caso esteja a direita
	// Retorna 2 caso esteja sobre
	
//	space n_v, n_w;
	space v[2] = {p1->getCoord(0) - p0->getCoord(0), p1->getCoord(1) - p0->getCoord(1)};
	space w[2] = {p2->getCoord(0) - p0->getCoord(0), p2->getCoord(1) - p0->getCoord(1)};
	
//	n_v = norm2d(v);
//	v[0] = v[0]/n_v;
//	v[1] = v[1]/n_v;
//
//	n_w = norm2d(w);
//	w[0] = w[0]/n_w;
//	w[1] = w[1]/n_w;
	space i = ( v[0]*w[1] - w[0]*v[1] );
//	space i = ( (p1->getCoord(0) - p0->getCoord(0))*(p2->getCoord(1) - p0->getCoord(1)) - (p2->getCoord(0) - p0->getCoord(0))*(p1->getCoord(1) - p0->getCoord(1)) );
	
	if (i > erro)
		return 1;
	else
	{
		if(i < - erro)
			return 0;
		else
			return 2;
	}
}
template <class _Traits> int ofGeometric<_Traits>::inLeft(sVertex *p0, sVertex *p1, space coord0, space coord1 OF_DMUTEXV)
{
	// Retorna 1 caso esteja a esquerda
	// Retorna 0 caso esteja a direita
	// Retorna 2 caso esteja sobre
	
	space i = ( (p1->getCoord(0) - p0->getCoord(0))*(coord1 - p0->getCoord(1)) - (coord0 - p0->getCoord(0))*(p1->getCoord(1) - p0->getCoord(1)) );
	
	if (i > erro)
		return 1;
	else
	{
		if(i < - erro)
			return 0;
		else
			return 2;
	}
}
template <class _Traits> int ofGeometric<_Traits>::inLeft(ids p0, ids p1, ids p2 OF_DMUTEXV)
{
	// Retorna 1 caso esteja a esquerda
	// Retorna 0 caso esteja a direita
	// Retorna 2 caso esteja sobre
	
	sVertex *v0 = mesh->getVertex(p0),
			*v1 = mesh->getVertex(p1),
			*v2 = mesh->getVertex(p2);
	
	
	
	return inLeft(v0,v1,v2);
}
template <class _Traits> int ofGeometric<_Traits>::inLeft(ids p0, ids p1, space coord0, space coord1 OF_DMUTEXV)
{
	// Retorna 1 caso esteja a esquerda
	// Retorna 0 caso esteja a direita
	// Retorna 2 caso esteja sobre
	
	sVertex *v0 = mesh->getVertex(p0),
			*v1 = mesh->getVertex(p1);
	
	
	return inLeft(v0,v1,coord0,coord1);
}
//template <class _Traits> int ofGeometric<_Traits>::inCircle(ids c, ids p OF_DMUTEXV)
//{
//	return inCircle( mesh->getCell(c), mesh->getVertex(p));
//}
template <class _Traits> int ofGeometric<_Traits>::inCircle(sVertex *p1, sVertex *p2, sVertex *p3, sVertex *p)
{
	
//	space _a[2], _b[2], _c[2], _d[2], _i, axb, adotb, cxd, cdotd;
//	space a_norm, b_norm, c_norm, d_norm;
//	
//	_a[0] = p2->getCoord(0) - p1->getCoord(0);
//	_a[1] = p2->getCoord(1) - p1->getCoord(1);
//	a_norm = norm2d(_a);
//
//	_c[0] = p2->getCoord(0) - p3->getCoord(0);
//	_c[1] = p2->getCoord(1) - p3->getCoord(1);
//	c_norm = norm2d(_c);
//
//	_b[0] = p->getCoord(0) - p1->getCoord(0);
//	_b[1] = p->getCoord(1) - p1->getCoord(1);
//	b_norm = norm2d(_b);
//
//	_d[0] = p->getCoord(0) - p3->getCoord(0);
//	_d[1] = p->getCoord(1) - p3->getCoord(1);
//	d_norm = norm2d(_d);
//	
//	_a[0] /= a_norm; 
//	_a[1] /= a_norm;
//
//	_b[0] /= b_norm; 
//	_b[1] /= b_norm;
//	
//	_c[0] /= c_norm; 
//	_c[1] /= c_norm;
//	
//	_d[0] /= d_norm; 
//	_d[1] /= d_norm;
//	
//	axb = _a[0]*_b[1] - _a[1]*_b[0]; 
//	cxd = _c[0]*_d[1] - _c[1]*_d[0];
//	adotb = _a[0]*_b[0] + _a[1]*_b[1];
//	cdotd = _c[0]*_d[0] + _c[1]*_d[1];
//	
//	_i = axb*cdotd + cxd*adotb;
	
	space a[3][3];
	
	a[0][0] = p2->getCoord(0) - p1->getCoord(0);
	a[0][1] = p2->getCoord(1) - p1->getCoord(1);
	a[0][2] = a[0][0]*a[0][0] + a[0][1]*a[0][1];
	a[1][0] = p3->getCoord(0) - p1->getCoord(0);
	a[1][1] = p3->getCoord(1) - p1->getCoord(1);
	a[1][2] = a[1][0]*a[1][0] + a[1][1]*a[1][1];
	a[2][0] = p->getCoord(0) - p1->getCoord(0);
	a[2][1] = p->getCoord(1) - p1->getCoord(1);
	a[2][2] = a[2][0]*a[2][0] + a[2][1]*a[2][1];
	
	space i = det3(a);
//	if( ((_i < -OF_ERRO_INCIRCLE)&&(i > OF_ERRO_INCIRCLE)) || ((_i > OF_ERRO_INCIRCLE)&&(i < -OF_ERRO_INCIRCLE)) )
//	{
//		cout << "p1: " << p1->getCoord(0) << " " << p1->getCoord(1) << endl;
//		cout << "p2: " << p2->getCoord(0) << " " << p2->getCoord(1) << endl;
//		cout << "p3: " << p3->getCoord(0) << " " << p3->getCoord(1) << endl;
//		cout << " p: " << p->getCoord(0) << " " << p->getCoord(1) << endl;
//			
//		cout << _i << " | " << i << "  " << ++temp << endl;
//	}
			
	// Retorna 1 caso p esteja dentro do circuncirculo
	// Retorna 0 caso p esteja fora do circuncirculo
	// Retorna 2 caso p esteja sobre o circuncirculo
	if (i > OF_ERRO_INCIRCLE)
		return 0;
	else
	{
		if(i < - OF_ERRO_INCIRCLE)
			return 1;
		else
			return 2;
	}
	
}
template <class _Traits> int ofGeometric<_Traits>::inCircle(sCell *c, sVertex *p OF_DMUTEXV)
{
	// Retorna 1 caso p esteja dentro do circuncirculo
	// Retorna 0 caso p esteja fora do circuncirculo
	// Retorna 2 caso p esteja sobre o circuncirculo
	
	sVertex *p1 = mesh->getVertex(c->getVertexId(0)),
			*p2 = mesh->getVertex(c->getVertexId(1)),
			*p3 = mesh->getVertex(c->getVertexId(2));
	
	/*
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
	
	if (i > OF_ERRO_INCIRCLE)
		return 1;
	else
	{
		if(i < - OF_ERRO_INCIRCLE)
			return 0;
		else
			return 2;
	}
	
	*/
	
	space a[3][3];
	
	a[0][0] = p2->getCoord(0) - p1->getCoord(0);
	a[0][1] = p2->getCoord(1) - p1->getCoord(1);
	a[0][2] = a[0][0]*a[0][0] + a[0][1]*a[0][1];
	a[1][0] = p3->getCoord(0) - p1->getCoord(0);
	a[1][1] = p3->getCoord(1) - p1->getCoord(1);
	a[1][2] = a[1][0]*a[1][0] + a[1][1]*a[1][1];
	a[2][0] = p->getCoord(0) - p1->getCoord(0);
	a[2][1] = p->getCoord(1) - p1->getCoord(1);
	a[2][2] = a[2][0]*a[2][0] + a[2][1]*a[2][1];
	
	space i = det3(a);
	
	if (i > OF_ERRO_INCIRCLE)
		return 0;
	else
	{
		if(i < - OF_ERRO_INCIRCLE)
			return 1;
		else
			return 2;
	}
	
}
template <class _Traits> SPACE ofGeometric<_Traits>::det(space matrix[4][4])
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
//template <class _Traits> void ofGeometric<_Traits>::flip2D(ids c1, ids c2 OF_DMUTEXV)
//{
//	sCell 	*temp,
//			*cell1 = mesh->getCell(c1),
//			*cell2 = mesh->getCell(c2);
//			
//	int	e1 = cell1->getMateIndex(c2),
//		e2 = cell2->getMateIndex(c1),
//		pos;
//	sVertex *v;
//	
//	
//	// atualizando os opostos
//	cell1->setMateId(e1, cell2->getMateId((e2+2)%3));
//	if(cell2->getMateId((e2+2)%3) >= 0)
//	{
//		temp = mesh->getCell(cell2->getMateId((e2+2)%3));
//		temp->setMateId(temp->getMateIndex(c2),c1);
//	}
//	
//	cell2->setMateId(e2, cell1->getMateId((e1+2)%3));
//	if(cell1->getMateId((e1+2)%3) >= 0)
//	{
//		temp = mesh->getCell(cell1->getMateId((e1+2)%3));
//		temp->setMateId(temp->getMateIndex(c1),c2);
//	}
//
//	
//	cell1->setMateId((e1+2)%3, c2);
//	cell2->setMateId((e2+2)%3, c1);
//	
//	// atualizando os vertices
//	
//	cell1->setVertexId((e1+1)%3,cell2->getVertexId(e2));
//	cell2->setVertexId((e2+1)%3,cell1->getVertexId(e1));
//	
//	v = mesh->getVertex(cell1->getVertexId(e1));
//  	pos = v->inSings(c1);
//	if(pos >= 0)
//		v->setSingCell(pos,c2);
//	
//	v = mesh->getVertex(cell2->getVertexId(e2));
//	pos = v->inSings(c2);
//	if(pos >= 0)
//		v->setSingCell(pos,c1);
//
//	v = mesh->getVertex(cell1->getVertexId((e1+2)%3));
//	pos = v->inSings(c2);
//	if(pos >= 0)
//		v->setSingCell(pos,c1);
//	
//	v =  mesh->getVertex(cell2->getVertexId((e2+2)%3));
//	pos = v->inSings(c1);
//	if(pos >= 0)
//		v->setSingCell(pos,c2);
//}
template <class _Traits> bool ofGeometric<_Traits>::inDiametralCircle(sVertex *v1, sVertex *v2, sVertex *v OF_DMUTEXV)
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
	if( r < ( d - erro) )
		return false;
	else
		return true;	
}
template <class _Traits> bool ofGeometric<_Traits>::sameVertices(sVertex *v1, sVertex *v2 OF_DMUTEXV)
{
	return (fabs(v1->getCoord(0) - v2->getCoord(0)) < erro ) && ( fabs(v1->getCoord(1) - v2->getCoord(1)) < erro );
}
template <class _Traits> int ofGeometric<_Traits>::orientation2D(ids *idvertices, sVertex **vertices OF_DMUTEXV)
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
    if (p < - erro)
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
		if (p > erro)
			return 2;
		else
			return 0;
	}
}
template <class _Traits> int ofGeometric<_Traits>::orientation3D(ids *idvertices, sVertex **vertices OF_DMUTEXV)
{
	//Retorna 0 caso os vertices sejam coplanares
	// Retorna 1 caso a orientacao foi arrumada
	// Retorna 2 caso a orientacao ja esteja correta
    // se sao coplanares
//    if()
//    		return 0;
//    	else
    		return orientation2D(idvertices, vertices);
}
template <class _Traits> bool ofGeometric<_Traits>::isBadCell(sCell *c, space beta OF_DMUTEXV)
{
	sVertex  *v0 = mesh->getVertex(c->getVertexId(0)),
			 *v1 = mesh->getVertex(c->getVertexId(1)),
			 *v2 = mesh->getVertex(c->getVertexId(2));
			 
	space r, circ[2], d1, d2, d3,resp = 0;
	getCircuncircle(c,circ);
	r = sqrt(	(v0->getCoord(0) - circ[0])*(v0->getCoord(0) - circ[0]) + 
				(v0->getCoord(1) - circ[1])*(v0->getCoord(1) - circ[1]) );
	d1 = dist(v0,v1);
	d2 = dist(v0,v2);
	d3 = dist(v2,v1);
//	d1 = sqrt(	(v0->getCoord(0) - v1->getCoord(0))*
//				(v0->getCoord(0) - v1->getCoord(0))+ 
//				(v0->getCoord(1) - v1->getCoord(1))*
//				(v0->getCoord(1) - v1->getCoord(1)));
//	d2 = sqrt(	(v0->getCoord(0) - v2->getCoord(0))*
//				(v0->getCoord(0) - v2->getCoord(0))+ 
//				(v0->getCoord(1) - v2->getCoord(1))*
//				(v0->getCoord(1) - v2->getCoord(1)));
//	d3 = sqrt(	(v2->getCoord(0) - v1->getCoord(0))*
//				(v2->getCoord(0) - v1->getCoord(0))+ 
//				(v2->getCoord(1) - v1->getCoord(1))*
//				(v2->getCoord(1) - v1->getCoord(1)));
				
	if(( d1 < d2 ) && ( d1 < d3))
		resp = r/d1;
	else if(( d2 < d1 ) && ( d2 < d3))
		resp = r/d2;
	else if(( d3 < d1 ) && ( d3 < d2))
		resp = r/d3;
		
	return (resp > beta);
}
template <class _Traits> void ofGeometric<_Traits>::getCircuncircle(space *c0, space *c1, space *c2, space *coords OF_DMUTEXV)
{
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
}
template <class _Traits> void ofGeometric<_Traits>::getCircuncircle(sCell *c, space *coords OF_DMUTEXV)
{
	sVertex  *v0 = mesh->getVertex(c->getVertexId(0)),
			 *v1 = mesh->getVertex(c->getVertexId(1)),
			 *v2 = mesh->getVertex(c->getVertexId(2));
	
	//space a, b, num, den;
	space *c0 = v0->getCoords(), *c1 = v1->getCoords(), *c2 = v2->getCoords();
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
//	space d1, d2, d3;
//	d1 = sqrt( (coords[0] - c0[0])*(coords[0] - c0[0]) + (coords[1] - c0[1])*(coords[1] - c0[1]) );
//	d2 = sqrt( (coords[0] - c1[0])*(coords[0] - c1[0]) + (coords[1] - c1[1])*(coords[1] - c1[1]) );
//	d3 = sqrt( (coords[0] - c2[0])*(coords[0] - c2[0]) + (coords[1] - c2[1])*(coords[1] - c2[1]) );
//
//	OF_ASSERT( fabs(d1-d2) < erro );
//	OF_ASSERT( fabs(d1-d3) < erro );
//	OF_ASSERT( fabs(d3-d2) < erro );
}
template <class _Traits> SPACE ofGeometric<_Traits>::det3(space matrix[3][3])
{
	return((matrix[0][0]*matrix[1][1]*matrix[2][2])+
		   (matrix[1][0]*matrix[2][1]*matrix[0][2])+
	 	   (matrix[0][1]*matrix[1][2]*matrix[2][0])-
		   (matrix[0][2]*matrix[1][1]*matrix[2][0])-
		   (matrix[0][1]*matrix[1][0]*matrix[2][2])-
		   (matrix[0][0]*matrix[2][1]*matrix[1][2]));
}
template <class _Traits> SPACE ofGeometric<_Traits>::dot(ids p1, ids p2, space *coords OF_DMUTEXV)
{
	sVertex *v1 = mesh->getVertex(p1), *v2 = mesh->getVertex(p2);
	double vv1X, vv1Y, vv2X, vv2Y, dp;
	
	vv1X = v1->getCoord(0) - coords[0];
	vv1Y = v1->getCoord(1) - coords[1];
	vv2X = v2->getCoord(0) - coords[0];
	vv2Y = v2->getCoord(1) - coords[1];
	
	dp = (vv1X * vv2X) + (vv1Y * vv2Y);
	return(dp);
}
// este metodo calcula o cosseno do angulo entre os vetores 2D: (p1-p_ang) e (p2-p_ang)
template <class _Traits> SPACE ofGeometric<_Traits>::vecAngle(ids p1, ids p_ang, ids p2)
{
    sVertex *v1 = mesh->getVertex(p1), 
    		*v2 = mesh->getVertex(p2), 
    		*v_ang = mesh->getVertex(p_ang);
    double vv1X, vv1Y, vv2X, vv2Y, dp, aux;
    vv1X = v1->getCoord(0) - v_ang->getCoord(0);
    vv1Y = v1->getCoord(1) - v_ang->getCoord(1);
    vv2X = v2->getCoord(0) - v_ang->getCoord(0);
    vv2Y = v2->getCoord(1) - v_ang->getCoord(1);
    dp = (vv1X * vv2X) + (vv1Y * vv2Y);
    aux = sqrt( vv1X*vv1X + vv1Y*vv1Y )*sqrt( vv2X*vv2X + vv2Y*vv2Y );
    return (dp/aux);
}
template <class _Traits> SPACE ofGeometric<_Traits>::norm2d(space *coords)
{
	return sqrt(coords[0]*coords[0] + coords[1]*coords[1]);
}
template <class _Traits> SPACE ofGeometric<_Traits>::areaTriangle(space *x, space *y, space *z)
{
	return fabs( (x[1]-z[1])*(y[2]-z[2])-(x[2]-z[2])*(y[1]-z[1]) )/2;
}
template <class _Traits> SPACE ofGeometric<_Traits>::areaTriangle(sCell *c)
{
	
	return areaTriangle(mesh->getVertex(c->getVertexId(0))->getCoords(), mesh->getVertex(c->getVertexId(1))->getCoords(), mesh->getVertex(c->getVertexId(2))->getCoords());
}
template <class _Traits> bool ofGeometric<_Traits>::isDelaunay(ids idcell)
{
//	sCell *c = mesh->getCell(idcell);
	ids mates[3] = {mesh->getCell(idcell)->getMateId(0), mesh->getCell(idcell)->getMateId(1), mesh->getCell(idcell)->getMateId(2)};
	sVertex *v;
	
	for(int i = 0; i < 3; i++)
	{
		if(mates[i] != -1)
		{
			v = mesh->getVertex( mesh->getCell(mates[i])->getVertexId(mesh->getCell(mates[i])->getMateIndex(idcell)) );
			if( inCircle(mesh->getCell(idcell), v) != 0)
				return false;
		}
	};
		
		
	return true;
}
#undef SPACE
}
#endif 
