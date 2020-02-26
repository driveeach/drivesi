#ifndef OFMYGEOMETRIC_H
#define OFMYGEOMETRIC_H
#include <cmath>
#include "ofMacros.h"
#include "ofVertexStarIterator2D.h"
#include "ofGeometric.h"
#include "ofPlane.h"
#define OF_MY_ERRO 1.0e-15 
namespace of
{
#define SPACE3 typename ofMyGeometric<_Traits>::space
#define IDS13 typename ofMyGeometric<_Traits>::ids
#define SVERTEX typename ofMyGeometric<_Traits>::sVertex
	//! Classe responsavel pelos calculos geometricos especificos para o algoritmo
	template <class _Traits> class ofMyGeometric :public ofGeometric<_Traits>
{
public:
	
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sSing sSing;
	typedef typename _Traits::sMesh sMesh;
	
	ofMyGeometric();
	~ofMyGeometric();
	space didoSurface(ids ic,space *coords ,space *alpha);
	void getZCoordinate(ids ic, space *coords);
	void getZCoordinate(sCell *c, space &z);
	void getZCoordinate(ids iv, space &z);
	void getZCoordinate1(ids iv, space &z);
	void flip2D(ids c1, ids c2);
	
	space innerProduct(sVertex *v1, sVertex *v2);
	void vecCross(sVertex *v1, sVertex *v2, space &x,space &y, space &z);
	sVertex *tensorialProduct(sVertex *a, sVertex *b, sVertex *c);
	space getShorestEdgeLength(sCell *c);
		/** Calculate the angle between the vectors p2-p1 and 
	p3-p1
 */
	space cosangle(ids p1, ids p2, ids p3);
	space findMedianLenghtEdge(sMesh *mesh);
	/** Verify is an edge has power of Two length
 */
	bool isPowerTwo(ids v1, ids v2);
	bool isBadCell(sCell *c, space beta);
	space getDist(ids v1, ids v2);
	space getDist(space *coord1, space *coord2,int dim=2);
	space norm(space *coord,int dim=2);
	bool isBEdge(ids ic,ids v1,ids v2);
	bool isBEdge(ids v1,ids v2);
	bool isBoundaryVertex(ids v);
	bool isBadCell2(sCell *c, space beta);
	void getMiddlePoint(ids v1, ids v2, space *coords,int dim=2);
private:
};
template <class _Traits> ofMyGeometric<_Traits>::ofMyGeometric()
{
}
template <class _Traits> bool ofMyGeometric<_Traits>::isBEdge(IDS13 ic,IDS13 v1,IDS13 v2)
{
	ids co;
	sCell *c=this->mesh->getCell(ic);
	
		
	
	co=c->getMate((c->getVertexIndex(v1)+2)%3);
	if(co>-1)
		return false;
	return true;
	
}
template <class _Traits> bool ofMyGeometric<_Traits>::isBEdge(ids v1,ids v2)
{
ids v,co;
	sCell *c;
	bool ok=false;
	ofVertexStarIterator2D<_Traits> itv(this->mesh);
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		v = itv->getVertex((itv->getVertexIndex(v1)+1)%3);
		c=*itv;
		if(v ==v2)
		{
			ok=true;
			break;
		}
	}
	if((!ok)&&(isBoundaryVertex(v1)))
	{
		v = c->getVertex((c->getVertexIndex(v1)+2)%3);
		
	}
	co=c->getMate((c->getVertexIndex(v)+1)%3);
	if(co==-1)
	{
		return true;
	}
	return false;
}
template <class _Traits> bool ofMyGeometric<_Traits>::isBoundaryVertex(ids v)
{
	ids cv=this->mesh->getVertex(v)->getSingCell();
	sCell *c=this->mesh->getCell(cv);
	int i;
	for(i=1;i<3;i++)
	{
		if(c->getMate((c->getVertexIndex(v)+i)%3)==-1)
			return true;
	}
	return false;
}
template <class _Traits> ofMyGeometric<_Traits>::~ofMyGeometric()
{
}
template <class _Traits> bool ofMyGeometric<_Traits>::isBadCell(sCell *c, space beta)
{
	sVertex  *v0 = this->mesh->getVertex(c->getVertex(0)),
			 *v1 = this->mesh->getVertex(c->getVertex(1)),
			 *v2 = this->mesh->getVertex(c->getVertex(2));
			 
	space r, circ[2], d1, d2, d3,resp = 0;
	int i,b=0;
	getCircuncircle(c,circ);
	r = sqrt(	(v0->getCoord(0) - circ[0])*(v0->getCoord(0) - circ[0]) + 
				(v0->getCoord(1) - circ[1])*(v0->getCoord(1) - circ[1]) );
	d1 = sqrt(	(v0->getCoord(0) - v1->getCoord(0))*
				(v0->getCoord(0) - v1->getCoord(0))+ 
				(v0->getCoord(1) - v1->getCoord(1))*
				(v0->getCoord(1) - v1->getCoord(1)));
	d2 = sqrt(	(v0->getCoord(0) - v2->getCoord(0))*
				(v0->getCoord(0) - v2->getCoord(0))+ 
				(v0->getCoord(1) - v2->getCoord(1))*
				(v0->getCoord(1) - v2->getCoord(1)));
	d3 = sqrt(	(v2->getCoord(0) - v1->getCoord(0))*
				(v2->getCoord(0) - v1->getCoord(0))+ 
				(v2->getCoord(1) - v1->getCoord(1))*
				(v2->getCoord(1) - v1->getCoord(1)));
				
	if(( d1 < d2 ) && ( d1 < d3))
		resp = r/d1;
	if(( d2 < d1 ) && ( d2 < d3))
		resp = r/d2;
	if(( d3 < d1 ) && ( d3 < d2))
		resp = r/d3;
	if (resp > beta)
	{
		for(i=0;i<3;i++)
		{
			if(c->isEdgeRestriction(i))
				b++;
		}
		if(b<2)
			return true;
	}
	return false;
}
template <class _Traits> bool ofMyGeometric<_Traits>::isBadCell2(sCell *c, space beta)
{
	ids      v0 = c->getVertexId(0),
			 v1 = c->getVertexId(1),
			 v2 = c->getVertexId(2);
			 
	space ca,a,angle=-1.0;
	int i,b=0;
	ca=cosangle(v0,v1,v2);
	if(ca>angle)
		angle=ca;
	ca=cosangle(v1,v2,v0);
	if(ca>angle)
		angle=ca;
	ca=cosangle(v2,v0,v1);
	if(ca>angle)
		angle=ca;
	a=acos(angle);
	if(a<asin(1/(2.0*beta)))
	{
		for(i=0;i<3;i++)
		{
			if(c->isEdgeRestriction(i))
				b++;
		}
		if(b<2)
			return true;
		
	}
	
		return false;
	
	
	
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::findMedianLenghtEdge(sMesh *mesh)
{
	ofCellsIterator<_Traits> itc(mesh);
	sVertex *v[3];
	int i;
	space dist;
	space sum=0.0;
	long int nedges=0;
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		for(i=0;i<3;i++)
			v[i]=mesh->getVertex(itc->getVertexId(i));
		for(i=0;i<3;i++)
		{
			dist=this->getDist(v[i]->getCoords(),v[(i+1)%3]->getCoords());
			sum+=dist;
			nedges++;
		}
	}
	return sum/nedges;
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::getDist(ids v1, ids v2)
{
	ofVertexStarIterator2D<_Traits> itv(this->mesh);
	space dt,d=10000000000.0;
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		dt=getShorestEdgeLength(*itv);
		if(dt<d)
			d=dt;
}
	for(itv.initialize(v2); itv.notFinish(); ++itv)
	{
		dt=getShorestEdgeLength(*itv);
		if(dt<d)
			d=dt;
	}
	return d;
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::getDist(space *coord1, space *coord2,int dim)
{
	space d=0.0;
	int i;
	for(i=0;i<dim;i++)
	{
		d=d+(coord1[i]-coord2[i])*(coord1[i]-coord2[i]);
	}
	return sqrt(d);
	
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::norm(space *coord,int dim)
{
	space d=0.0;
	int i;
	for(i=0;i<dim;i++)
	{
		d=d+coord[i]*coord[i];
	}
	return sqrt(d);
	
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::getShorestEdgeLength(sCell *c)
{
	sVertex  *v0 = this->mesh->getVertex(c->getVertexId(0)),
			 *v1 = this->mesh->getVertex(c->getVertexId(1)),
			 *v2 = this->mesh->getVertex(c->getVertexId(2));
	space d=100000000000000.0, daux;
	daux=dist(v0,v1);
	if(daux<d)
		d=daux;
	daux=dist(v1,v2);
	if(daux<d)
		d=daux;
	daux=dist(v0,v2);
	if(daux<d)
		d=daux;
	return d;
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::cosangle(ids p1, ids p2, ids p3)
{
	sVertex *v1 = this->mesh->getVertex(p1), *v2 = this->mesh->getVertex(p2), *v3 = this->mesh->getVertex(p3);
	space dp=0.0;
	int i;/*size=v1->getDimension();*/
	int size=v1->getDimension()-1;
	space *vv1,*vv2;
	vv1= new space[size];
	vv2= new space[size];
	
	for(i=0;i<size;i++)
	{
		vv1[i] = v3->getCoord(i) - v1->getCoord(i);
		vv2[i] = v2->getCoord(i) - v1->getCoord(i);
		
		dp=dp+vv1[i]*vv2[i];
	}
	
	space d1,d2;
	d1=norm(vv1,size);
	d2=norm(vv2,size);
	dp = dp/(d1*d2);
	delete vv1;
	delete vv2;
	return dp;
}
template <class _Traits> bool ofMyGeometric<_Traits>::isPowerTwo(ids v1, ids v2)
{
	sVertex *p1 = this->mesh->getVertex(v1), *p2 = this->mesh->getVertex(v2);
		space d =dist(p1,p2);
		space ld,tmp,r;
		ld=log(d)/log(2.0);
		if(ld>0.0)
		{
			r=(int)(ld+0.5);
			tmp=fabs(ld-r);
			if(tmp<OF_MY_ERRO)
				return true;
		}
		else
		{
			r=(int)(ld-0.5);
			tmp=fabs(ld-r);
			if(tmp<OF_MY_ERRO)
				return true;
		}
	return false;
}
template <class _Traits> void ofMyGeometric<_Traits>::flip2D(ids c1, ids c2)
{
	sCell 	*temp,
			*cell1 = this->mesh->getCell(c1),
			*cell2 = this->mesh->getCell(c2);
			
	int	e1 = cell1->getMateIndex(c2),
		e2 = cell2->getMateIndex(c1),
		pos,m1,m2;
		
	sVertex *v;
	
	
	// atualizando os opostos
	cell1->setMateId(e1, cell2->getMateId((e2+2)%3));
	cell1->setEdgeRestriction((e1+1)%3,false);
	if(cell2->getMateId((e2+2)%3) >= 0)
	{
		temp = this->mesh->getCell(cell2->getMateId((e2+2)%3));
		m1=temp->getMateIndex(c2);
		temp->setMateId(m1,c1);
		cell1->setEdgeRestriction(e1,temp->isEdgeRestriction(m1));
		
	}
	else{
		cell1->setEdgeRestriction(e1,true);
	}
	
	cell2->setMateId(e2, cell1->getMateId((e1+2)%3));
	cell2->setEdgeRestriction((e2+1)%3,false);
	if(cell1->getMateId((e1+2)%3) >= 0)
	{
		temp = this->mesh->getCell(cell1->getMateId((e1+2)%3));
		m2=temp->getMateIndex(c1);
		
		temp->setMateId(m2,c2);
		cell2->setEdgeRestriction(e2,temp->isEdgeRestriction(m2));
		
	}
	else
	{
		cell2->setEdgeRestriction(e2,true);
	}
	
	cell1->setMateId((e1+2)%3, c2);
	cell2->setMateId((e2+2)%3, c1);
	
	// atualizando os vertices
	
	cell1->setVertexId((e1+1)%3,cell2->getVertexId(e2));
	cell2->setVertexId((e2+1)%3,cell1->getVertexId(e1));
	
	v = this->mesh->getVertex(cell1->getVertexId(e1));
  	pos = v->inSings(c1);
	if(pos >= 0)
		v->setSingCell(pos,c2);
	
	v = this->mesh->getVertex(cell2->getVertexId(e2));
	pos = v->inSings(c2);
	if(pos >= 0)
		v->setSingCell(pos,c1);
	v = this->mesh->getVertex(cell1->getVertexId((e1+2)%3));
	pos = v->inSings(c2);
	if(pos >= 0)
		v->setSingCell(pos,c1);
	
	v =  this->mesh->getVertex(cell2->getVertexId((e2+2)%3));
	pos = v->inSings(c1);
	if(pos >= 0)
		v->setSingCell(pos,c2);
}
template <class _Traits> void ofMyGeometric<_Traits>::getMiddlePoint(ids v1, ids v2,space *coords,int dim)
{
	sVertex *vi1, *vi2;
	vi1=this->mesh->getVertex(v1);
	vi2=this->mesh->getVertex(v2);
	int i;
	for (i=0;i<=dim;i++)
	{
		coords[i]=vi1->getCoord(i)+(vi2->getCoord(i)-vi1->getCoord(i))*0.5;
	}
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::didoSurface(ids ic,space *coords,space *alpha)
{
	ids pos,ric=ic;
	bool ok=true;
	sCell *c;
	sVertex *v[3];
	int i;
	space **m; //! matrix used to store the system
		m = new(space*[5]);
	   for (i = 0; i < 5; i++)
    		m[i] = new (space[5]);
	   space amax=10E10;
	   space ret,px,py,pz;
	   
		ofPlane<_Traits> *p;
	while(ok)
	{
		//vetor B:
		/*m[0][4] = 1.0;
		m[1][4] = coords[0];
		m[2][4] = coords[1];
		m[3][4] = coords[2];  */
		c=this->mesh->getCell(ric);
		p=new ofPlane<_Traits>(ric,this->mesh);		
		p->projection(coords[0],coords[1],coords[2],px,py,pz);	
		ret=pz;
		/*for (i=0;i<3;i++)
			v[i]=mesh->getVertex(c->getVertexId(i));
		
	   ofDido3d<_Traits> dido(mesh);
		m[0][3]=0.0;
		m[1][3]=-p->get_A();
		m[2][3]=-p->get_B();
		m[3][3]=-p->get_C();
		ret =p->get_C();
	   for(i=0;i<3;i++)
		{
			m[0][i] = 1.0;
			m[1][i] = v[i]->getCoord(0);
			m[2][i] = v[i]->getCoord(1);
			m[3][i] = v[i]->getCoord(2);
		}
	   
		dido.solveSystem(3,m,alpha);
		amax=10E10;
		for(i=0;i<3;i++)
		{
			if(amax>alpha[i])
			{
				amax=alpha[i];
				pos=i;
			}
		}*/
		delete p;
		ok=false;
		/*if(amax>-0.0000000000001)
			ok=false;
		else
		{
			ric=c->getMateId(pos);
		}*/
	}
	for(i=0;i<5;i++)
		delete[] m[i];
	delete[] m;
	return ret;
}
template <class _Traits> void ofMyGeometric<_Traits>::getZCoordinate(ids ic,space *coords)
{
	space *alpha=new space[5];
	space ric=didoSurface(ic,coords,alpha);
	//sCell *c=mesh->getCell(ric);
	
	coords[2]=ric;
	delete[] alpha;
}
template <class _Traits> void ofMyGeometric<_Traits>::getZCoordinate(sCell *c,space &z)
{
	sVertex *v[3];
	int i;
	space zsum=0.0;
	for(i=0;i<3;i++)
	{
		v[i]=this->mesh->getVertex(c->getVertexId(i));
		zsum+=v[i]->getCoord(2);
	}
	z=zsum/3.0;
	
}
template <class _Traits> void ofMyGeometric<_Traits>::getZCoordinate(ids iv,space &z)
{
	sVertex *v;
	int i,nv=0;
	ids vnext;
	ofVertexStarIterator2D<_Traits> itv(this->mesh);
	space zsum=0.0;
	for(itv.initialize(iv);itv.notFinish();++itv)
	{
		vnext = itv->getVertexId((itv->getVertexIndex(iv)+1)%3);
		v=this->mesh->getVertex(vnext);
		zsum+=v->getCoord(2);
		nv++;
	}
	z=zsum/nv;
	
}
template <class _Traits> SVERTEX* ofMyGeometric<_Traits>::tensorialProduct(sVertex *a, sVertex *b, sVertex *c)
{
	sVertex *v=new sVertex;
	space np=innerProduct(b,c);
	v->setCoord(0,np*a->getCoord(0));
	v->setCoord(1,np*a->getCoord(1));
	v->setCoord(2,np*a->getCoord(2));
	return v;
}
template <class _Traits> void ofMyGeometric<_Traits>::vecCross(sVertex *v1,sVertex *v2, space &x, space &y, space &z)
{
	x=v1->getCoord(1)*(v2->getCoord(2)) - v1->getCoord(2)*(v2->getCoord(1));
    y=v1->getCoord(2)*(v2->getCoord(0)) - v1->getCoord(0)*(v2->getCoord(2));
    z=v1->getCoord(0)*(v2->getCoord(1)) - v1->getCoord(1)*(v2->getCoord(0));
}
template <class _Traits> SPACE3 ofMyGeometric<_Traits>::innerProduct(sVertex *v1,sVertex *v2)
{
	return v1->getCoord(0)*v2->getCoord(0)+v1->getCoord(1)*v2->getCoord(1)+v1->getCoord(2)*v2->getCoord(2) ;
}
template <class _Traits> void ofMyGeometric<_Traits>::getZCoordinate1(ids iv,space &z)
{
	sVertex *v;
	int i,nv=0;
	ids vnext;
	ofVertexStarIterator2D<_Traits> itv(this->mesh);
	space distmin=10E10;
	space d;
	space zsum=0.0;
	space dsum=0.0;
	for(itv.initialize(iv);itv.notFinish();++itv)
	{
		vnext = itv->getVertexId((itv->getVertexIndex(iv)+1)%3);
		d=this->dist(this->mesh->getVertex(iv),this->mesh->getVertex(vnext));
		if(d<distmin)
			distmin=d;
		nv++;
	}
	for(itv.initialize(iv);itv.notFinish();++itv)
	{
		vnext = itv->getVertexId((itv->getVertexIndex(iv)+1)%3);
		v=this->mesh->getVertex(vnext);
		d=this->dist(this->mesh->getVertex(iv),this->mesh->getVertex(vnext));
		zsum+=v->getCoord(2)/(distmin/d);
		dsum+=distmin/d;
	}
	z=zsum/(nv);
	
}
#undef SPACE3
#undef IDS13
#undef SVERTEX
}
#endif
