#ifndef OFRUPPERT2D_H
#define OFRUPPERT2D_H
#include "ofMacros.h"
#include "ofVertexStarIterator2D.h"
#include "ofCellsIterator.h"
#include "ofVerticesIterator.h"
#include "ofList.h"
#include "ofVtkWriter.h"
#include<math.h>
namespace of
{
#define SPACE6 typename ofRuppert2D<_Traits>::space
#define SMESH typename ofRuppert2D<_Traits>::sMesh
#define IDS10 typename ofRuppert2D<_Traits>::ids
//!Rotinas de refinamento Delaunay:  Algoritmo de Ruppert
template <class _Traits> class ofRuppert2D
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sGeometric sGeometric;
	typedef typename _Traits::sMesh sMesh;
	typedef typename _Traits::sDelaunay sDelaunay;
	typedef typename _Traits::sPolygon sPolygon;
	typedef typename _Traits::sEdge sEdge;
	ofRuppert2D();
	~ofRuppert2D();
	//void execute(ofList<sPolygon*> *lp, space _beta = 1.4);
	/** Executa o algoritmo de refinamento chamado Terminator
	* 
	* Parametros de entrada:
	* lv: lista de vertices
	* lids: lista de arestas que representam as curvas da fratura
	* b: parametro que indica se as arestas internas da fratura serao, ou nao, respeitadas
	* beta: parametro de qualidade de malha
	*/
	void execute(ofList<sVertex*> *lv, ofList<ofList<ids>*> *lids,bool b,space _beta = 1.4);
	sMesh *getMesh();
	sGeometric *getGeom(){return &geom;};
	int getNumberOfInsertedVertices(){return numberOfInsertedVertices;};
	/** 
	* Verifica se existem triangulos finos e os elimina
	*
	* Parametro de entrada: bool b; (indica se arestas internas da
	* fratura serao, ou nao, respeitadas)
	*/
	void removeSkinnyCells(bool b);
	bool getStepok(){return stepok;}
	void setStepok(bool b){stepok=b;}
	/** 
	*  Insere o bounding box, antes do inicio da triangulacao
	*  esse procedimento tem como objetivo
	*  acelerar a triangulacao de Delaunay
	*/
	void insertBoundBox(ofList<sVertex*> *lv);
	/** Insere os vertices da triangulacao
	*  
	*/
	void insertVertex(ofList<sVertex*> *lv);
	/** 
	*  Garante que as arestas da restricao aparecam na triangulacao
	*/
	void defineBoundaries();
	/** 
	* Remove os triangulos externos a restricao 
	*/
	void removeExternal();
	/** 
	* Remove os vertices adicionados para garantir a restricao 
	*/
//	void removeExtaVertices(int nv);
	void checkEdges(bool b);
	void checkCells();
	/** Insere o bounding box. 
	* 
	* Opearcao feita apos o termino do refinamento 
	*/
	void insertBound();
	void refineMesh();

	void execute2D(ofList<sVertex*> *lv, ofList<ofList<ids>*> *lids,bool b,space _beta=1.4);
	void preExecute(ofList<sVertex*> *lv, ofList<ofList<ids>*> *lids,space _beta=1.4);
	void splitEncroachedSubsegments(bool b);
	int getNumberOfViolatedEdges(){return numberOfViolatedEdges;};
	int getNumberofSkynnyCells(){return numberOfSkynnyCells;};
	bool isRestrictionEdge(ids v0, ids v1);
	ofList<ids> *getListOfSkinnyCells(){return &skiny_cells;};
	sEdge getFirstViolatedEdge(){return violated_edges.pos(0);};
	ids getFirstSkinnyCell(){return skiny_cells.pos(0);};
	bool isBadCell(ids ic);
	ids getOppositeVertexCell(ids v,ids c);
	
	/** verifica de uma aresta eh ou nao de fronteira
	* 
	* 
	*/
	bool isBoundaryEdge(ids v1, ids v2);
	bool isBoundaryVertex(ids v);
	int getIniV(){return iniV;};
	sDelaunay* getDelaynay(){return delaunay;};
	space getSmallestLenghtEdge(){return smallestLenghtEdge;};
	space getGreaterLenghtEdge(){return greaterLenghtEdge;};
	space getMedianLenghtEdge(){return medianLenghtEdge;};
	void findSmallestLenghtEdge();
	void findMedianLenghtEdge();
	space f(space x,space y){return 0.0;};
	//space fp(space x,space y){return 5.0*((x-this->cx)*(x-this->cx)+(y-this->cy)*(y-this->cy));}
	space fp(space x,space y){return -2.0*tan(-y*1.4)*x;}
	void projectVerticesOnSurface();
	void getCircuncircleCoords(space *c,ids ic);
private:
	
	
	
	
	bool defineBoundary(ids v1, ids v2);
	void getNewBoundaryVertex(ids v1, ids v2, space *coords,bool b);
	void getNewBoundaryVertex(ids v1, ids v2, space *coords,space dist);
	double getNewDistanceVertex(ids v1, ids v2,bool b);
	/** 
	* Verifica se existem arestas violadas e as elimina
	* Este processo tem prioridade sobre a eliminacao de
	* triangulos finos
	* Parametro de entrada: bool b; (indica se arestas internas da
	* fratura serao, ou nao, respeitadas)
	*/
	
	void inBounds(ids v, int *b, int *p);
	
	
	
	
	
	bool checkInsertion(space *coords, int &bound, int &pos);
	void addRemoveList(ids ic, ofList<ids> *lista);
	bool inSameBound(ids v1,ids v2);
	bool findBound(ids v1,ids v2, int &b,int &p, int &s);
	void freeBounds();
	void newVervex(ids v,bool b);
	bool isEdge(ids v1,ids v2);
	bool isBoundaryEdge(ids ic, ids v1, ids v2);
	bool isViolatedEdge(ids v0,ids v1);
	void getViolatedEdges(ids ic,space *coords,ofList<sEdge> &le,bool b);
	int getInsideCell(ids ic, space *coords);
	bool isViolatedEdge(ids v0, ids v1, space *coords);
	void checkOtherCells(ids ic,space *coords,ofList<sEdge> &le, bool b);
	void checkNewCells(ids v, ofList<ids> &sc,bool b);
	bool splitPermited(sEdge,space d,bool b);
	void getSubsegmentCluster(sEdge e,ids v1,ofList<sEdge> &le, space &rmin,int direction,bool b);
	int insertPermited(sEdge e,ofList<sEdge> &le);
	void setRestrctionEdge(sEdge e);
	void insertBoundaries();
	void checkNewVertex(ids v);
	void findBoundaryVertices();
	IDS10 getCellEdge(ids v1,ids v2);
	
	sMesh *mesh;
	sGeometric geom;
	sDelaunay *delaunay;
	ofList< ofList<ids> *> *bounds;
	ids bbids[4];
	space beta;
	space smallestLenghtEdge;
	space greaterLenghtEdge;
	space medianLenghtEdge;
	ofList<sEdge>  violated_edges;
	ofList<sEdge>  boundary_edges;
	ofList<ids> skiny_cells;
	ofList<ids> boundary_vertices;
	bool external_removed;
	bool stepok;
	int numberOfViolatedEdges;
	int numberOfSkynnyCells;
	int numberOfInsertedVertices;
	int iniV;
	bool firstok;
	space cx,cy;
	
};
template <class _Traits> ofRuppert2D<_Traits>::ofRuppert2D()
{
	delaunay = NULL;
	bounds=NULL;
	external_removed=false;
	stepok=false;
	firstok=false;
	numberOfInsertedVertices=0;
}
template <class _Traits> ofRuppert2D<_Traits>::~ofRuppert2D()
{
	if(delaunay)
		delete delaunay;
		
	//freeBounds();
}
template <class _Traits> void ofRuppert2D<_Traits>::freeBounds()
{
	ofList<ids> *l;
	for(int i = 0; i < bounds->size(); i++)
	{
		l = bounds->pos(i);
		
		 l->clear();
	}
	bounds->clear();
}
template <class _Traits> void ofRuppert2D<_Traits>::projectVerticesOnSurface()
{
	ofVerticesIterator<_Traits> itv(mesh);
	space minz=1000000000.0;
	space maxz=-100000000.0;
	int i=0;
	for(itv.initialize();itv.notFinish();++itv)
	{
		itv->setCoord(2,fp(itv->getCoord(0),itv->getCoord(1)));
		if(itv->getCoord(2)<minz)
			minz=itv->getCoord(2);
		if(itv->getCoord(2)>maxz)
			maxz=itv->getCoord(2);
	}
	mesh->setMaxZ(maxz);
	mesh->setMinZ(minz);
}
template <class _Traits> void ofRuppert2D<_Traits>::findSmallestLenghtEdge()
{
	ofCellsIterator<_Traits> itc(mesh);
	sVertex *v[3];
	int i;
	space dist;
	space tmp=1000000000000.0;
	space gtmp=-1000000000000.0;
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		for(i=0;i<3;i++)
			v[i]=mesh->getVertex(itc->getVertex(i));
		for(i=0;i<3;i++)
		{
			dist=geom.dist(v[i],v[(i+1)%3]);
			if(dist<tmp)
				tmp=dist;
			if(dist>gtmp)
				gtmp=dist;
		}
	}
	smallestLenghtEdge=tmp;
	greaterLenghtEdge=gtmp;
}
template <class _Traits> void ofRuppert2D<_Traits>::findMedianLenghtEdge()
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
			dist=geom.getDist(v[i]->getCoords(),v[(i+1)%3]->getCoords());
			sum+=dist;
			nedges++;
		}
	}
	medianLenghtEdge=sum/nedges;
}
// template <class _Traits> void ofRuppert2D<_Traits>::execute(ofList<sPolygon*> *lp, space _beta)
// 
// 
// 
// {
// 
// 
// 
// 	if(delaunay)
// 
// 
// 
// 		delete delaunay;
// 
// 
// 
// 
// 
// 
// 
// 	delaunay = new sDelaunay();
// 
// 
// 
// 	
// 
// 
// 
// 	beta = _beta;
// 
// 
// 
// 	
// 
// 
// 
// 	mesh = delaunay->getMesh();
// 
// 
// 
// 	
// 
// 
// 
// 	geom.setMesh(mesh);
// 
// 
// 
// 	
// 
// 
// 
// 	freeBounds();
// 	insertBound(lp);
// 	defineBoundaries();
// 	checkEdges(false);
// 	removeExternal();
// 	checkCells();
// }
template <class _Traits> void ofRuppert2D<_Traits>::execute2D(ofList<sVertex*> *lv, ofList<ofList<ids>*> *lids,bool b,space _beta)
{
	if(delaunay)
		delete delaunay;
	delaunay = new sDelaunay();
	mesh = delaunay->getMesh();
	geom.setMesh(mesh);
	setStepok(false);	
	beta = _beta;
	if(bounds)
		freeBounds();
	bounds=lids;
	iniV=lv->size();
	insertBoundBox(lv);
	insertVertex(lv);
	defineBoundaries();
	removeExternal();
	
	checkEdges(b);
	splitEncroachedSubsegments(b);
	checkCells();
	removeSkinnyCells(b);
}
template <class _Traits> void ofRuppert2D<_Traits>::preExecute (ofList<sVertex*> *lv, ofList<ofList<ids>*> *lids,space _beta)
{
	if(delaunay)
		delete delaunay;
	delaunay = new sDelaunay();
	mesh = delaunay->getMesh();
	geom.setMesh(mesh);
	setStepok(false);	
	beta = _beta;
	if(bounds)
		freeBounds();
	bounds=lids;
	iniV=lv->size();
}
template <class _Traits> void ofRuppert2D<_Traits>::execute (ofList<sVertex*> *lv, ofList<ofList<ids>*> *lids, bool b,space _beta)
{
	if(delaunay)
		delete delaunay;
	delaunay = new sDelaunay();
	beta = _beta;
	mesh = delaunay->getMesh();
	setStepok(false);	
	geom.setMesh(mesh);
	if(bounds)
		freeBounds();
	bounds=lids;
	iniV=lv->size();
	//insertBoundBox(lv);
	insertVertex(lv);
	defineBoundaries();
	removeExternal();
	checkEdges(b);	
	splitEncroachedSubsegments(b);
	checkCells();
	removeSkinnyCells(b);
	//insertBound();
	}
template <class _Traits> SMESH* ofRuppert2D<_Traits>::getMesh()
{
	return mesh;
}
template <class _Traits> void ofRuppert2D<_Traits>::insertBoundBox(ofList<sVertex*> *lv)
{
	int i;
	sVertex *v;
	space xmin,ymin,xmax,ymax,x,y;
	v=lv->pos(0);
	xmin = xmax = v->getCoord(0);
	ymin = ymax = v->getCoord(1);
	for(i=0;i<lv->size();i++)
	{
		v=lv->pos(i);
			x = v->getCoord(0);
			y = v->getCoord(1);
			if(xmax < x)
				xmax = x;
			if (xmin > x)
				xmin = x;
			if(ymax < y)
				ymax = y;
			if (ymin > y)
				ymin = y;
		}
	space vtx[2];
	space factor=0.5;
	vtx[0] = xmin - ( (xmax - xmin)*factor);
	mesh->setMin(0,vtx[0]);
	vtx[1] = ymin - ( (ymax - ymin)*factor);
	mesh->setMin(1,vtx[1]);
	bbids[0] = delaunay->addVertex(vtx,-1);
	mesh->getVertex(bbids[0])->setCoord(2,0.0);
	
	vtx[1] = ymax + ( (ymax - ymin)*factor);
	bbids[1] = delaunay->addVertex(vtx,-1);
	mesh->getVertex(bbids[1])->setCoord(2,0.0);
	vtx[0] = xmax + ( (xmax - xmin)*factor);
	vtx[1] = ymin - ( (ymax - ymin)*factor);
	bbids[2] = delaunay->addVertex(vtx,-1);
	mesh->getVertex(bbids[2])->setCoord(2,0.0);
	vtx[0] = xmax + ( (xmax - xmin)*factor);
	mesh->setMax(0,vtx[0]);
	vtx[1] = ymax + ( (ymax - ymin)*factor);
	mesh->setMax(1,vtx[1]);
 	bbids[3] = delaunay->addVertex(vtx,-1);
	mesh->getVertex(bbids[3])->setCoord(2,0.0);
}
template <class _Traits> void ofRuppert2D<_Traits>::insertBound()
{
	int i;
	sVertex *v;
	ofCellsIterator<_Traits> itc(mesh);
	
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		itc->setFlag(true);
	}
	
	space vtx[2];
	vtx[0] = mesh->getMinX();
	
	vtx[1] = mesh->getMinY()-1.0;
	
	delaunay->addVertex(vtx);
	
	vtx[0] = mesh->getMaxX()+1.0;
	
	vtx[1] = mesh->getMinY()-1.0;
	mesh->setMinY(vtx[1]);
	delaunay->addVertex(vtx);
	
	vtx[0] = mesh->getMinX();
	
	vtx[1] = mesh->getMaxY()+1.0;
	delaunay->addVertex(vtx);
	
	vtx[0] = mesh->getMaxX()+1.0;
	mesh->setMaxX(vtx[0]);
	
	vtx[1] = mesh->getMaxY()+1.0;
	mesh->setMaxY(vtx[1]);
	delaunay->addVertex(vtx);
}
template <class _Traits> void ofRuppert2D<_Traits>::insertVertex(ofList<sVertex*> *lv)
{
	int i;
	space vtx[3],x,y,z;
	ids iv;
	sVertex *v;
	this->cx=(mesh->getMax(0)+mesh->getMin(0))*0.5;
	this->cy=(mesh->getMax(1)+mesh->getMin(1))*0.5;
	for(i=0;i<lv->size();i++)
	{
		v=lv->pos(i);
		vtx[0]=x=v->getCoord(0);
		vtx[1]=y=v->getCoord(1);
		vtx[2]=z=v->getCoord(2);
		//vtx[2]=z=fp(x,y);
		iv=delaunay->addVertex(vtx);
		//mesh->getVertex(iv)->setCoord(2,(x-cx)*(x-cx)+(y-cy)*(y-cy));
	}
}
template <class _Traits> void ofRuppert2D<_Traits>::defineBoundaries()
{
	int i, j;
	ofList<ids> *p;
	ids v0, v1;
	bool reiniciar = true;
	sEdge edge;
	while(reiniciar)
	{
		reiniciar = false;
		for(i = 0; i < bounds->size(); i++)
		{
			p = bounds->pos(i);
			for(j = 0; j < p->size()-1; j++)
			{
				v0 = p->pos(j);
				v1 = p->pos((j+1)%p->size());
				edge.first=v0;
				edge.second=v1;
				if(!defineBoundary(v0,v1))
				{
					boundary_edges.insert(edge);
					reiniciar = true;
				}			
			}
		}
		insertBoundaries();
	}	
	for(i = 0; i < bounds->size(); i++)
		{
			p = bounds->pos(i);
			for(j = 0; j < p->size()-1; j++)
			{
				v0 = p->pos(j);
				v1 = p->pos((j+1)%p->size());
				edge.first=v0;
				edge.second=v1;
				setRestrctionEdge(edge);
			}
		}
}
template <class _Traits> void ofRuppert2D<_Traits>::insertBoundaries()
{
	space coords[3];
	sEdge e;
	ids v0,v1,v;
	ofList<ids> *l;
	int bound, pos,s;
	cx=(mesh->getMax(0)+mesh->getMin(0))*0.5;
    cy=(mesh->getMax(1)+mesh->getMin(1))*0.5;
	while(boundary_edges.size()>0)
	{
		e=boundary_edges.pos(0);
		boundary_edges.erase(0);
		v0=e.first;
		v1=e.second;
		getNewBoundaryVertex(v0,v1,coords,false);
		//coords[2]=f(coords[0],coords[1]);
		v = delaunay->addVertex(coords);
		numberOfInsertedVertices++;
		//mesh->getVertex(v)->setCoord(2,(coords[0]-cx)*(coords[0]-cx)+(coords[1]-cy)*(coords[1]-cy));
		if(findBound(v0,v1,bound,pos,s))
			{
				
				l=bounds->pos(bound);
				l->insert(v,pos+s);
			}
		if(!isEdge(v0,v)/*defineBoundary(v0,v1)*/)
		{
			e.second=v;
			boundary_edges.insertFirst(e);
		}
		if(!isEdge(v,v1)/*defineBoundary(v0,v1)*/)
		{
			e.first=v;
			e.second=v1;
			boundary_edges.insertFirst(e);
		}
		//checkNewVertex(v);
	}
}
template <class _Traits> void ofRuppert2D<_Traits>::checkNewVertex(ids v)
{
	ofVertexStarIterator2D<_Traits> itst(mesh);
	ids co,v1,v2;
	int bound,pos,s;
	sEdge e;
	for(itst.initialize(v);itst.notFinish();++itst)
	{
		v1=itst->getVertex((itst->getVertexIndex(v)+1)%3);
		v2=itst->getVertex((itst->getVertexIndex(v)+2)%3);
		co=itst->getMate(itst->getVertexIndex(v1));
		if((!findBound(v1,v2,bound,pos,s))&&(co>-1))
		{
			e.first=v1;
			e.second=(mesh->getCell(co))->getMateVertex(&itst);
			if(insertPermited(e,boundary_edges))
				boundary_edges.insertFirst(e);
		}
	}
}
template <class _Traits> bool ofRuppert2D<_Traits>::defineBoundary(ids v1, ids v2)
{
	ids vnext, vprev;
	int test1, test2;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		vnext = itv->getVertexId((itv->getVertexIndex(v1)+1)%3);
		vprev = itv->getVertexId((itv->getVertexIndex(v1)+2)%3);
		if( (vnext != v2)&&(vprev != v2) )
		{
			test1 = geom.inLeft(v1,vnext,v2);
			test2 = geom.inLeft(vprev,v1,v2);
			if((test1 == 1)&&(test2 == 1))
					return false;
		}
		else
			break;
	}
	return true;
/*	ids vnext, vprev;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		vnext = itv->getVertexId((itv->getVertexIndex(v1)+1)%3);
		vprev = itv->getVertexId((itv->getVertexIndex(v1)+2)%3);
		if((vnext==v2)&&(itv->getMateId(itv->getVertexIndex(v1)+2)%3)==-1)
			return true;
		if((vprev==v2)&&(itv->getMateId(itv->getVertexIndex(v1)+1)%3)==-1)
			return true;
	}
	return false;*/
}
template <class _Traits> double ofRuppert2D<_Traits>::getNewDistanceVertex(ids v1, ids v2, bool b)
{
	sVertex *p1 = mesh->getVertex(v1), *p2 = mesh->getVertex(v2);
	if (b)
	{
		return 0.5;
	}
	else
	{
		space d =geom.dist(p1,p2);
		space ld;
		double dist;
		int intlog;
		ld=log(d)/log(2.0);
		if(ld<0.0)
		{
			if(abs(ld-(int)(ld+0.5))<0.00000001)
				intlog=(int) (ld-2.0);
			else
				intlog = (int) (ld-1.0);
		}
		else
		{
			if(abs(ld-(int)(ld+0.5))<0.00000001)
				intlog=(int) (ld-1.0);
			else
				intlog = (int) (ld-0.0);
		}
		dist=pow(2.0,intlog)/d;
		return dist;
	}
	
}
template <class _Traits> void ofRuppert2D<_Traits>::getNewBoundaryVertex(ids v1, ids v2, space *coords, bool b)
{
	sVertex *p1 = mesh->getVertex(v1), *p2 = mesh->getVertex(v2);
	if (b)
	{
		coords[0] = (p1->getCoord(0) + p2->getCoord(0)) *0.5;
		coords[1] = (p1->getCoord(1) + p2->getCoord(1)) * 0.5;
		coords[2] = (p1->getCoord(2) + p2->getCoord(2)) * 0.5;
	}
	else
	{
		space tmp,d =geom.dist(p1,p2);
		space ld;
		space dist;
		int intlog,il;
		 
		ld=log(d)/log(2.0);
		if(ld<0.0)
		{
			il=(int)(ld-0.5);
			tmp=fabs(ld-il);
			if(tmp<OF_MY_ERRO)
				intlog=(int) (ld-2.0);
			else
				intlog = (int) (ld-1.0);
		}
		else
		{
			tmp=fabs(ld-(int)(ld+0.5));
			if(tmp<OF_MY_ERRO)
				intlog=(int) (ld-1.0);
			else
				intlog = (int) (ld-0.0);
		}
		space d2=pow(2.0,intlog);
		if(fabs(d2)>=fabs(d*0.55))
			d2=pow(2.0,intlog-1);
		dist=d2/d;
		
		{
			coords[0] = (p1->getCoord(0) + (p2->getCoord(0)-p1->getCoord(0))*dist);
			coords[1] = (p1->getCoord(1) + (p2->getCoord(1)-p1->getCoord(1))*dist);
			coords[2] = (p1->getCoord(2) + (p2->getCoord(2)-p1->getCoord(2))*dist);
		}
		
	}
	
}
template <class _Traits> void ofRuppert2D<_Traits>::getNewBoundaryVertex(ids v1, ids v2, space *coords, space dist)
{
	sVertex *p1 = mesh->getVertex(v1), *p2 = mesh->getVertex(v2);
	
			coords[0] = (p1->getCoord(0) + (p2->getCoord(0)-p1->getCoord(0))*dist);
			coords[1] = (p1->getCoord(1) + (p2->getCoord(1)-p1->getCoord(1))*dist);
			coords[2] = (p1->getCoord(2) + (p2->getCoord(2)-p1->getCoord(2))*dist);
		
}
template <class _Traits> void ofRuppert2D<_Traits>::checkEdges(bool b)
{
	int i, j;
	ids v0, v1;
	sEdge edge;
	ofList<ids> *p;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	if(!external_removed)
	{
		for(i=0;i<4;i++)
		{
				v0 = bbids[i];
				v1 = bbids[(i+1)%4];
				edge.first=v0;
				edge.second=v1;
				setRestrctionEdge(edge);
				
				if(isViolatedEdge(v0,v1))
				{
					
					violated_edges.insert(edge);
				}
		}
	}
	for(i = 0; i < bounds->size(); i++)
		{
			p = bounds->pos(i);
			
			for(j = 0; j < p->size()-1; j++)
			{
				v0 = p->pos(j);
				v1 = p->pos((j+1)%p->size());
				edge.first=v0;
				edge.second=v1;
				if(b)
					setRestrctionEdge(edge);
				
				if(isViolatedEdge(v0,v1))
				{
					
					violated_edges.insert(edge);
				}
				
			}
		}
		numberOfViolatedEdges=violated_edges.size();
}
template <class _Traits> bool ofRuppert2D<_Traits>::isViolatedEdge(ids v0,ids v1,space *coords)
{
	space dot = geom.dot(v0,v1,coords);
	if(dot<= /*-0.5*geom.dist(mesh->getVertex(v0),coords)*geom.dist(mesh->getVertex(v1),coords)*/ 0.0 + OF_ERRO)
		return true;
	return false;
}
template <class _Traits> void ofRuppert2D<_Traits>::getSubsegmentCluster(sEdge e, ids v1, ofList<sEdge> &le, space &rmin,int direction,bool b)
{
	space s=geom.dist(mesh->getVertex(e.first),mesh->getVertex(e.second));
	ofVertexStarIterator2D<_Traits> itv(mesh);
	space r,raux=1000000000.0;
	ids  v ;
    le.insert(e);
	sCell *c=NULL;
	sEdge e1,e2;
	space angle;
	bool ok,nonstop=true;
	int i;
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		v = itv->getVertexId((itv->getVertexIndex(v1)+1*direction)%3);
		c=*itv;
		if(v!=e.second)
		{
			if((b)&&(/*findBound(v1,v,bound,pos,si)*/isRestrictionEdge(v1,v)))
			{
			
				ok=false;
				for(i=0;i<le.size();i++)
				{
					e1=le.pos(i);
					angle=geom.cosangle(v1,e1.second,v);
					if(angle>0.5)
					{
						ok=true;
						r=s*sin(acos(angle)*0.5);
						if(r<raux)
							raux=r;
					}
				}
				if(ok)
				{
					e2.first=v1;
					e2.second=v;
					le.insert(e2);
				}
					
			}
			else
			if(isBoundaryEdge(v1,v))
			{
				ok=false;
				for(i=0;i<le.size();i++)
				{
					e1=le.pos(i);
					angle=geom.cosangle(v1,e1.second,v);
					if(angle>0.5)
					{
						ok=true;
						r=s*sin(acos(angle)*0.5);
						if(r<raux)
							raux=r;
					}
				}
				if(ok)
				{
					e2.first=v1;
					e2.second=v;
					le.insert(e2);
				}
			}
		}
	}
	if(isBoundaryVertex(v1))
	{
		if(direction==2)
		{
			itv.initialize(v1);
			c=*itv;
			v = c->getVertexId((c->getVertexIndex(v1)+1)%3);
		}
		else
			v = c->getVertexId((c->getVertexIndex(v1)+2*direction)%3);
		
		if(v!=e.second)
		{
			if((b)&&(/*findBound(v1,v,bound,pos,si)*/isRestrictionEdge(v1,v)))
			{
			
				ok=false;
				for(i=0;i<le.size();i++)
				{
					e1=le.pos(i);
					angle=geom.cosangle(v1,e1.second,v);
					if(angle>0.5)
					{
						ok=true;
						r=s*sin(acos(angle)*0.5);
						if(r<raux)
							raux=r;
					}
				}
				if(ok)
				{
					e2.first=v1;
					e2.second=v;
					le.insert(e2);
				}
					
			}
			else
			if(isBoundaryEdge(v1,v))
			{
				ok=false;
				for(i=0;i<le.size();i++)
				{
					e1=le.pos(i);
					angle=geom.cosangle(v1,e1.second,v);
					if(angle>0.5)
					{
						ok=true;
						r=s*sin(acos(angle)*0.5);
						if(r<raux)
							raux=r;
					}
				}
				if(ok)
				{
					e2.first=v1;
					e2.second=v;
					le.insert(e2);
				}
			}
		}
	}
	
	
	if(raux!=1000000000.0)
		rmin=raux;
}
template <class _Traits> int ofRuppert2D<_Traits>::insertPermited(sEdge e,ofList<sEdge> &le)
{
	int i;
	sEdge eaux;
	
		for(i=0;i<le.size();i++)
			{
				eaux=le.pos(i);
				if(((eaux.first==e.first)&&(eaux.second==e.second))||((eaux.first==e.second)&&(eaux.second==e.first)))
					return i;
			}
	
	return -1;
}
template <class _Traits> bool ofRuppert2D<_Traits>::splitPermited(sEdge e, space d, bool b)
{
	ofList<sEdge> c1,c2;
	space rmin;
	sEdge eaux;
	if(isBoundaryEdge(e.first,e.second))
		return true;
	if(geom.isPowerTwo(e.first,e.second)==false)
		return true;
	eaux.first=e.second;
	eaux.second=e.first;
	getSubsegmentCluster(e,e.first,c1,rmin,1,b);
	getSubsegmentCluster(eaux,eaux.first,c2,rmin,2,b);
	if(((c1.size()<=1)&&(c2.size()<=1))||((c1.size()>1)&&(c2.size()>1)))
		return true;
	if(rmin>=d)
		return true;
	
	return false;
}
template <class _Traits> void ofRuppert2D<_Traits>::setRestrctionEdge(sEdge e)
{
	ids v2,co,vo,v;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	sCell *c;
	bool ok=true;
	for(itv.initialize(e.first); itv.notFinish(); ++itv)
	{
		c=*itv;
		v2 = itv->getVertexId((itv->getVertexIndex(e.first)+1)%3);
		if(v2 ==e.second)
		{
			ok=false;
			v = itv->getVertexId((itv->getVertexIndex(v2)+1)%3);
			if(v==e.first)
				v = itv->getVertexId((itv->getVertexIndex(v2)+2)%3);
			itv->setEdgeRestriction(itv->getVertexIndex(v),true);
			co=itv->getMateId(itv->getVertexIndex(v));
			if(co>-1)
			{
				vo=(mesh->getCell(co))->getVertexMateId(&itv);
				mesh->getCell(co)->setEdgeRestriction(mesh->getCell(co)->getVertexIndex(vo),true);
			}
		}
	}
	if((ok)&&(isBoundaryVertex(e.first)))
	{
		v2 = c->getVertexId((c->getVertexIndex(e.first)+2)%3);
		if(v2 ==e.second)
		{
			v = c->getVertexId((c->getVertexIndex(v2)+1)%3);
			if(v==e.first)
				v = c->getVertexId((c->getVertexIndex(v2)+2)%3);
			c->setEdgeRestriction(c->getVertexIndex(v),true);
			
		}
	}
	
}
template <class _Traits> bool ofRuppert2D<_Traits>::isRestrictionEdge(ids v0,ids v1)
{
	ids v2,v;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	
	sCell *c;
	for(itv.initialize(v0); itv.notFinish(); ++itv)
	{
		c=*itv;
		v2 = itv->getVertexId((itv->getVertexIndex(v0)+1)%3);
		if(v2 ==v1)
		{
			
			v = itv->getVertexId((itv->getVertexIndex(v2)+1)%3);
			if(v==v0)
				v = itv->getVertexId((itv->getVertexIndex(v2)+2)%3);
			return itv->isEdgeRestriction(itv->getVertexIndex(v));
		}
	}
	if(isBoundaryVertex(v0))
	{
		v2 = c->getVertexId((c->getVertexIndex(v0)+2)%3);
		if(v2 ==v1)
		{
			v = c->getVertexId((c->getVertexIndex(v2)+1)%3);
			if(v==v0)
				v = c->getVertexId((c->getVertexIndex(v2)+2)%3);
			return c->isEdgeRestriction(c->getVertexIndex(v));
			
		}
	}
	
}
template <class _Traits> bool ofRuppert2D<_Traits>::isViolatedEdge(ids v0,ids v1)
{
	ids v2,co,vo,v;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	space dot;
	sCell *c;
	for(itv.initialize(v0); itv.notFinish(); ++itv)
	{
		c=*itv;
		v2 = itv->getVertexId((itv->getVertexIndex(v0)+1)%3);
		if(v2 ==v1)
		{
			v = itv->getVertexId((itv->getVertexIndex(v2)+1)%3);
			if(v==v0)
				v = itv->getVertexId((itv->getVertexIndex(v2)+2)%3);
			dot =geom.dot(v0,v1,mesh->getVertex(v)->getCoords());
			if( dot <= /*-0.5*geom.dist(mesh->getVertex(v0),mesh->getVertex(v)->getCoords())*geom.dist(mesh->getVertex(v1),mesh->getVertex(v)->getCoords())*/0.0 + OF_ERRO)
				return true;
			co=itv->getMateId(itv->getVertexIndex(v));
			if(co>-1)
			{
				vo=(mesh->getCell(co))->getVertexMateId(&itv);
				if(geom.dot(v0,v1,mesh->getVertex(vo)->getCoords()) <= /*-0.5*geom.dist(mesh->getVertex(v0),mesh->getVertex(vo)->getCoords())*geom.dist(mesh->getVertex(v1),mesh->getVertex(vo)->getCoords())*/0.0 + OF_ERRO)
				{
					return true;
				}
			}
		}
	}
	if(isBoundaryVertex(v0))
	{
		v2 = c->getVertexId((c->getVertexIndex(v0)+2)%3);
		if(v2 ==v1)
		{
			v = c->getVertexId((c->getVertexIndex(v2)+1)%3);
			if(v==v0)
				v = c->getVertexId((c->getVertexIndex(v2)+2)%3);
			dot =geom.dot(v0,v1,mesh->getVertex(v)->getCoords());
			if( dot <= /*-0.5*geom.dist(mesh->getVertex(v0),mesh->getVertex(v)->getCoords())*geom.dist(mesh->getVertex(v1),mesh->getVertex(v)->getCoords())*/0.0 + OF_ERRO)
				return true;
		}
		else
		{
			itv.initialize(v0);
			v2 = itv->getVertexId((itv->getVertexIndex(v0)+2)%3);
			if(v2 ==v1)
			{
				v = c->getVertexId((c->getVertexIndex(v2)+1)%3);
				if(v==v0)
					v = c->getVertexId((c->getVertexIndex(v2)+2)%3);
				dot =geom.dot(v0,v1,mesh->getVertex(v)->getCoords());
				if( dot <= 0.0 + OF_ERRO)
					return true;
			}
		}
	}
	return false;
}
template <class _Traits> bool ofRuppert2D<_Traits>::isBoundaryEdge(ids v1,ids v2)
{
	ids v,co;
	sCell *c;
	bool ok=false;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		v = itv->getVertexId((itv->getVertexIndex(v1)+1)%3);
		c=*itv;
		if(v ==v2)
		{
			ok=true;
			break;
		}
	}
	co=c->getMateId((c->getVertexIndex(v)+1)%3);
	if((!ok)&&(isBoundaryVertex(v1)))
	{
		v = c->getVertexId((c->getVertexIndex(v1)+2)%3);
		co=c->getMateId((c->getVertexIndex(v1)+1)%3);
	}
	if(co==-1)
	{
		return true;
	}
	return false;
}
template <class _Traits> bool ofRuppert2D<_Traits>::isBoundaryEdge(ids ic,ids v1,ids v2)
{
	ids co;
	sCell *c=mesh->getCell(ic);
	
		
	
	co=c->getMateId((c->getVertexIndex(v1)+2)%3);
	if(co>-1)
		return false;
	return true;
}
template <class _Traits> bool ofRuppert2D<_Traits>::isEdge(ids v1,ids v2)
{
	ids v;
	sCell *c;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		v = itv->getVertexId((itv->getVertexIndex(v1)+1)%3);
		c=*itv;
		if(v ==v2)
			return true;
	}
	if(isBoundaryVertex(v1))
	{
		v = c->getVertexId((c->getVertexIndex(v1)+2)%3);
		if(v ==v2)
			return true;
	}
	return false;
}
template <class _Traits> IDS10 ofRuppert2D<_Traits>::getCellEdge(ids v1,ids v2)
{
	ids v;
	ids ic;
	sCell *c;
	ofVertexStarIterator2D<_Traits> itv(mesh);
	for(itv.initialize(v1); itv.notFinish(); ++itv)
	{
		v = itv->getVertexId((itv->getVertexIndex(v1)+1)%3);
		c=*itv;
		ic=&itv;
		if(v ==v2)
			return ic;
	}
	if(isBoundaryVertex(v1))
	{
		v = c->getVertexId((c->getVertexIndex(v1)+2)%3);
		if(v ==v2)
			return ic;
	}
	return mesh->getVertex(v1)->getSingCell();
}
template <class _Traits> bool ofRuppert2D<_Traits>::isBoundaryVertex(ids v)
{
	ids cv=mesh->getVertex(v)->getSingCell();
	sCell *c=mesh->getCell(cv);
	int i;
	for(i=1;i<3;i++)
	{
		if(c->getMateId((c->getVertexIndex(v)+i)%3)==-1)
			return true;
	}
	return false;
}
template <class _Traits> void ofRuppert2D<_Traits>::splitEncroachedSubsegments(bool b)
{
	sEdge edge, edge1,eaux;
	ids vi1,vi2,v;
	space coords[3],rmin,dist;
	ofList<sEdge> V_edges,c1,c2,c;
	
	int i;
	ids ic;
	bool step=true;
	cx=(mesh->getMax(0)+mesh->getMin(0))*0.5;
	cy=(mesh->getMax(1)+mesh->getMin(1))*0.5;
	while((violated_edges.size()>0)&&(step))
	{
		
		edge=violated_edges.pos(0);
		dist=geom.dist(mesh->getVertex(edge.first),mesh->getVertex(edge.second));
		violated_edges.erase(0);
		
		V_edges.clear();
		getSubsegmentCluster(edge,edge.first,c1,rmin,1,b);
		if(c1.size()>1)
		{
			vi1=edge.first;
			vi2=edge.second;
			getNewBoundaryVertex(vi1,vi2,coords,false);
		}
		else
		{
			vi1=edge.second;
			vi2=edge.first;
			getNewBoundaryVertex(vi1,vi2,coords,true);
		}
		c1.clear();
		ic=getCellEdge(vi1,vi2);
		getViolatedEdges(ic,coords,V_edges,b);
		//coords[2]=f(coords[0],coords[1]);
		v = delaunay->addVertex(coords,ic);
		numberOfInsertedVertices++;
		//mesh->getVertex(v)->setCoord(2,((coords[0]-cx)*(coords[0]-cx))+((coords[1]-cy)*(coords[1]-cy)));
		checkNewCells(v,skiny_cells,b);
		edge1.first=vi1;
		edge1.second=v;
		if (b)
			setRestrctionEdge(edge1);
		if(isViolatedEdge(vi1,v))
			violated_edges.insertFirst(edge1);
		edge1.first=v;
		edge1.second=vi2;
		if (b)
			setRestrctionEdge(edge1);
		if(isViolatedEdge(v,vi2))
			violated_edges.insertFirst(edge1);
		if(V_edges.size()>0)
		{
			for(i=0;i<V_edges.size();i++)
			{
				edge1=V_edges.pos(i);
				if(((edge1.first==edge.first)&&(edge1.second==edge.second))||((edge1.first==edge.second)&&(edge1.second==edge.first)))
				{
				}
				else
					if(insertPermited(edge1,violated_edges)==-1)
						violated_edges.insertFirst(edge1);
			}
		}
		numberOfViolatedEdges=violated_edges.size();
		if(stepok)
			step=false;
	}
			
}
template <class _Traits> bool ofRuppert2D<_Traits>::inSameBound(ids v1, ids v2)
{
	int i,j,k,sizel,sizeb=bounds->size();
	ofList<ids> *l;
	for(i = 0 ; i < sizeb; i++)
	{
		l = bounds->pos(i);
		sizel=l->size();
		for(j = 0; j < sizel; j++)
		{
			if(	v1 == l->pos(j) )
			{
				for(k=j-1;k>=0;k--)
				{
					if(v2 == l->pos(k))
					{
						return true;
					}
				}
				for(k=j+1;k<sizel;k++)
				{
					if(v2 == l->pos(k))
					{
						return true;
					}
				}
			}
		}
	}
	return false;
}
template <class _Traits> bool ofRuppert2D<_Traits>::findBound(ids v1, ids v2, int &b, int &p,int &s)
{
	int i,j,sizel,sizeb=bounds->size();
	ofList<ids> *l;
	for(i = 0 ; i < sizeb; i++)
	{
		l = bounds->pos(i);
		sizel=l->size();
		for(j = 0; j < sizel; j++)
		{
			if(	v1 == l->pos(j) )
			{
				
					if((j-1>-1)&&(v2 == l->pos(j-1)))
					{
						b=i;
						p=j;
						s=0;
						return true;
					}
				
				
					if((j+1<sizel)&&(v2 == l->pos(j+1)))
					{
						b=i;
						p=j;
						s=1;
						return true;
					}
				}
			}
		}
	
	return false;
}
template <class _Traits> void ofRuppert2D<_Traits>::inBounds(ids v, int *b, int *p)
{
	int i,j,sizel,sizeb=bounds->size();
	ofList<ids> *l;
	for(i = 0 ; i < sizeb; i++)
	{
		l = bounds->pos(i);
		sizel=l->size();
		for(j = 0; j < sizel; j++)
		{
			if(	v == l->pos(j) )
			{
				*b = i+1;
				*p = j;
				
				
				return;
			}
		}
	}
	*b = 0;
	*p = 0;
}

template <class _Traits> IDS10 ofRuppert2D<_Traits>::getOppositeVertexCell(ids v, ids c)
{

	int co=mesh->getCell(c)->getMateVertexId(v);
	sCell *cop;

	if(co!=-1)
	{
		cop=mesh->getCell(co);
		return cop->getVertexMateId(c);
	}
	return -1;
}

/*template <class _Traits> void ofRuppert2D<_Traits>::removeExtaVertices(int nv)
{
	ofList<ids> lCell;
	int i,j;
	space a1,a2,angle;
	ofList<ids> lEdge;
	ofList<sEdge> lFixedEdge;
	int co,vindex;
	ofCellsIterator<_Traits> cit(mesh);
	sVertex *vtmp;
	int e;
	sEdge fixed;
	i=0;
	
	for(i=nv+4;i<mesh->getNumberOfVertices()+4;i++)
	{
		
		vtmp=mesh->getVertex(i);
		for(cit.initialize();cit.notFinish();++cit)
		{
			for(j=0;j<3;j++)
			{
				if(cit->getVertexId(j)==i)
				{
					lCell.insert(&cit);
					break;
				}

			}

		}
	sCell *pc,*pCo;
	int v[4],cell[3], pos=0;
	v[0]=i;
		while(lCell.size()>2)
		{
			pc=mesh->getCell(lCell.pos(pos));
			vindex=pc->getVertexIndex(i);
			
			if(pc->getMateId((vindex+2)%3)==-1)
			{
				co=pc->getMateId((vindex+1)%3);
				v[2]=pc->getVertexMateId(co);
				if(pc->getVertexId((pc->getVertexIndex(v[2])+1)%3)!=i)
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+1)%3);
				else
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+2)%3);
			}
			else
			{
				co=pc->getMateId((vindex+2)%3);
				v[2]=pc->getVertexMateId(co);
				if(pc->getVertexId((pc->getVertexIndex(v[2])+1)%3)!=i)
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+1)%3);
				else
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+2)%3);
			}
			v[3]= getOppositeVertexCell(v[2],lCell.pos(pos));
			if(v[3]!=-1)
			{
				a1=geom.areaTriangle(pc)+geom.areaTriangle(mesh->getVertex(v[0])->getCoords(),mesh->getVertex(v[1])->getCoords(),mesh->getVertex(v[3])->getCoords());
				a2=geom.areaTriangle(mesh->getVertex(v[0])->getCoords(),mesh->getVertex(v[2])->getCoords(),mesh->getVertex(v[3])->getCoords());
				if(abs(a2)>abs(a1))
				{
					pos++;
				}
				else
				{
					geom.flip2D(lCell.pos(pos),co);
					
					if(mesh->getCell(co)->getVertexIndex(i)!=-1)
						lCell.erase(pos);
					else
						lCell.erase(lCell.search(co));

					pos=0;
				}
			}
			
		}
		pos=0;
		pc=mesh->getCell(lCell.pos(pos));
		vindex=pc->getVertexIndex(i);
		
		if(pc->getMateId((vindex+2)%3)==-1)
		{
			co=pc->getMateId((vindex+1)%3);
			v[2]=pc->getVertexMateId(co);
			if(pc->getVertexId((pc->getVertexIndex(v[2])+1)%3)!=i)
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+1)%3);
				else
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+2)%3);
		}
		else
		{
			co=pc->getMateId((vindex+2)%3);
			v[2]=pc->getVertexMateId(co);
			if(pc->getVertexId((pc->getVertexIndex(v[2])+1)%3)!=i)
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+1)%3);
				else
					v[1]=pc->getVertexId((pc->getVertexIndex(v[2])+2)%3);
		}
		v[3]= getOppositeVertexCell(v[2],lCell.pos(pos));
		if(v[3]!=-1)
		{
			mesh->delCell(lCell.pos(pos));
			mesh->delCell(lCell.pos(pos+1));
			angle=(geom.vecAngle(v[2],v[1],v[3])*180.0)/PI;
			if((angle<140.0))
			{
				cell[0]=v[1];
				cell[1]=v[2];
				cell[2]=v[3];
				mesh->addCell(cell);
			}
			
		}
		lCell.clear();
	}
	
for(cit.initialize();cit.notFinish();++cit)
	{
		i=&cit;
		for(j=0;j<3;j++)
		{
			angle=(geom.vecAngle(cit->getVertexId(j),cit->getVertexId((j+1)%3),cit->getVertexId((j+2)%3))*180.0)/PI;
			if(angle>170.0)
			{
				co=cit->getMateId((j+1)%3);
				if(co!=-1)
				{
					geom.flip2D(&cit,co);
				}
			}

		}
	}

}*/
template <class _Traits> void ofRuppert2D<_Traits>::removeExternal()
{
	int i;
	sVertex *vtmp=mesh->getVertex(0);
	ids id_c=vtmp->getSingCell();
	
	ofList<ids> *lista=new ofList<ids>();
	ofCellsIterator<_Traits> itc(mesh);
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		itc->setFlag(false);
		
	}
	addRemoveList(id_c,lista);
	for(i = 0; i < lista->size(); i++)
		mesh->delCell(lista->pos(i));
	
	external_removed=true;
	for(i=0;i<4;i++) mesh->delVertex(bbids[i]);
	delete lista;
	}
template <class _Traits> void ofRuppert2D<_Traits>::addRemoveList(ids ic, ofList<ids> *lista)
{
	
	sCell *c;
	c=mesh->getCell(ic);
	c->setFlag(true);
	lista->insert(ic);
	
	if((c->getVertexId(0)<4)||(c->getVertexId(1)<4)||(c->getVertexId(2)<4))
	{
		if((c->getMateId(2)!=-1)&&(mesh->getCell(c->getMateId(2)))->getFlag()==false)
		{
			if((c->getVertexId(0)<4)||(c->getVertexId(1)<4))
				addRemoveList(c->getMateId(2),lista);
			else
				if(isRestrictionEdge(c->getVertexId(0),c->getVertexId(1))==false)
					addRemoveList(c->getMateId(2),lista);
		}
		if((c->getMateId(1)!=-1)&&(mesh->getCell(c->getMateId(1)))->getFlag()==false)
		{
			if((c->getVertexId(0)<4)||(c->getVertexId(2)<4))
				addRemoveList(c->getMateId(1),lista);
			else
				if(isRestrictionEdge(c->getVertexId(0),c->getVertexId(2))==false)
					addRemoveList(c->getMateId(1),lista);
		}
		if((c->getMateId(0)!=-1)&&(mesh->getCell(c->getMateId(0)))->getFlag()==false)
		{
			if((c->getVertexId(2)<4)||(c->getVertexId(1)<4))
				addRemoveList(c->getMateId(0),lista);
			else
				if(isRestrictionEdge(c->getVertexId(1),c->getVertexId(2))==false)
					addRemoveList(c->getMateId(0),lista);
		}
	}
	else
	{
		if((c->getMateId(2)!=-1)&&(mesh->getCell(c->getMateId(2)))->getFlag()==false)
		{
				if(isRestrictionEdge(c->getVertexId(0),c->getVertexId(1))==false)
					addRemoveList(c->getMateId(2),lista);
		}
		if((c->getMateId(1)!=-1)&&(mesh->getCell(c->getMateId(1)))->getFlag()==false)
		{
			if(isRestrictionEdge(c->getVertexId(0),c->getVertexId(2))==false)
					addRemoveList(c->getMateId(1),lista);
		}
		if((c->getMateId(0)!=-1)&&(mesh->getCell(c->getMateId(0)))->getFlag()==false)
		{
				if(isRestrictionEdge(c->getVertexId(1),c->getVertexId(2))==false)
					addRemoveList(c->getMateId(0),lista);
		}
	}
	
}
template <class _Traits> void ofRuppert2D<_Traits>::checkNewCells(ids v, ofList<ids> &sc,bool b)
{
	ofVertexStarIterator2D<_Traits> itst(mesh);
	ids co,vo;
	bool r;
	for(itst.initialize(v);itst.notFinish();++itst)
	{
		if(b)
		{
			
			itst->setEdgeRestriction((itst->getVertexIndex(v)+1)%3,false);
			itst->setEdgeRestriction((itst->getVertexIndex(v)+2)%3,false);
			co=itst->getMateId(itst->getVertexIndex(v));
			if(co>-1)
			{
				vo=(mesh->getCell(co))->getVertexMateId(&itst);
				r=(mesh->getCell(co))->isEdgeRestriction((mesh->getCell(co))->getVertexIndex(vo));
				itst->setEdgeRestriction(itst->getVertexIndex(v),r);
				
			}
			else
			{
				itst->setEdgeRestriction(itst->getVertexIndex(v),true);
			}
		}
		
		if((sc.inList(&itst)==false)&&(geom.isBadCell2(*itst,beta)))
			sc.insertFirst(&itst);
	}
}
template <class _Traits> void ofRuppert2D<_Traits>::newVervex(ids v, bool b)
{
	ofVertexStarIterator2D<_Traits> itst(mesh);
	ids v1, v2,co;
	sEdge e;
	int bound,pos,s;
	for(itst.initialize(v);itst.notFinish();++itst)
	{
		v1=itst->getVertex((itst->getVertexIndex(v)+1)%3);
		v2=itst->getVertex((itst->getVertexIndex(v)+2)%3);
		if(b)
		{
			if((isRestrictionEdge(v1,v2))&&(isViolatedEdge(v1,v2,mesh->getVertex(v)->getCoords())))
			{
				e.first=v1;
				e.second=v2;
				violated_edges.insertFirst(e);
			}
			else if(geom.isBadCell2(*itst,beta))
				skiny_cells.insert(&itst);
		}
		else
		{
			co=itst->getMate(itst->getVertexIndex(v));
			if((co==-1)&&(isViolatedEdge(v1,v2,mesh->getVertex(v)->getCoords())))
			{
				e.first=v1;
				e.second=v2;
				violated_edges.insertFirst(e);
			}
			else if(geom.isBadCell2(*itst,beta))
				skiny_cells.insert(&itst);
		}
		
	}
}
template <class _Traits> bool ofRuppert2D<_Traits>::isBadCell(ids ic)
{
	return geom.isBadCell2(mesh->getCell(ic),beta);
}
template <class _Traits> void ofRuppert2D<_Traits>::checkCells()
{
	
	ofCellsIterator<_Traits> itc(mesh);
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		if((skiny_cells.inList(&itc)==false)&&(geom.isBadCell2(*itc,beta)))
		{
			skiny_cells.insert(&itc);				
		}
		
	}
	
	numberOfSkynnyCells=skiny_cells.size();
}
template <class _Traits> void ofRuppert2D<_Traits>::removeSkinnyCells(bool b)
{
	ids iv,ic;
	space coords[3],d;
	
	ofList<sEdge> V_edges;
	sEdge e;
	int i;
	bool step=true;
	cx = (mesh->getMax(0)+mesh->getMin(0))*0.5;
	
	cy = (mesh->getMax(1)+mesh->getMin(1))*0.5;
	while((skiny_cells.size()>0)&&(step==true))
	{
		
		ic=skiny_cells.pos(0);
		skiny_cells.erase(0);
		
		
		//saida.write(mesh,"malha.vtk");
		V_edges.clear();
		
		if((mesh->getCell(ic)->inMesh())&&(geom.isBadCell2(mesh->getCell(ic),beta)))
		{
			geom.getCircuncircle(mesh->getCell(ic),coords);
			coords[2]=0.0;
			getViolatedEdges(ic,coords,V_edges,b);
			if(V_edges.size()==0)
			{
				
				
				
				iv=delaunay->addVertex(coords,ic);
				
				
				numberOfInsertedVertices++;
				checkNewCells(iv,skiny_cells,b);
				if(!firstok)
				{
					iniV=iv;
					firstok=true;
				}
				if(stepok)
					step=false;
			}
			else
			{
				d=geom.getShorestEdgeLength(mesh->getCell(ic));
				for(i=0;i<V_edges.size();i++)
				{
					e=V_edges.pos(i);
					if(splitPermited(e,d,b))
						violated_edges.insert(e);
				}
				if(violated_edges.size()>0)
				{
					skiny_cells.insertFirst(ic);
					if(stepok)
					{
						stepok=false;
						splitEncroachedSubsegments(b);
						stepok=true;
					}
					else
					{
						splitEncroachedSubsegments(b);
					}
				}
			}
		}
		numberOfSkynnyCells=skiny_cells.size();	
	}
}
template <class _Traits> void ofRuppert2D<_Traits>::checkOtherCells(ids ic,space *coords,ofList<sEdge> &le,bool b)
{
	ids cmate, iauxc;
	sVertex *v[3];
	iauxc = ic;
	sCell *c=mesh->getCell(ic);
	sEdge e;
	int left;
	
	int lado=0;
	v[0] = mesh->getVertex(c->getVertexId(lado));
	v[1] = mesh->getVertex(c->getVertexId((lado+1)%3));
	v[2] = mesh->getVertex(c->getVertexId((lado+2)%3));
	left = geom.inLeft(v[0],v[1],coords[0],coords[1]);
	if (left==1)
	{
		if(isViolatedEdge(c->getVertexId(lado),c->getVertexId(lado+1),coords))
		{
			cmate=c->getMateId(lado+2);
			if(b)
			{
				if(isRestrictionEdge(c->getVertexId(lado),c->getVertexId(lado+1)))
				{
					e.first=c->getVertexId(lado);
					e.second=c->getVertexId(lado+1);
					le.insert(e);
					if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
				}
				else
					if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
			}
			else
			{
				
				if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
				else
				{
					e.first=c->getVertexId(lado);
					e.second=c->getVertexId(lado+1);
					le.insert(e);
					
				}
			}
		}		
	}
	left = geom.inLeft(v[1],v[2],coords[0],coords[1]);
	if (left==1)
	{
		if(isViolatedEdge(c->getVertexId(lado+1),c->getVertexId(lado+2),coords))
		{
			cmate=c->getMateId(lado);
			if(b)
			{
				if(isRestrictionEdge(c->getVertexId(lado+1),c->getVertexId(lado+2)))
				{
					e.first=c->getVertexId(lado+1);
					e.second=c->getVertexId(lado+2);
					le.insert(e);
					if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
				}
				else
				if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
			}
			else
			{
				if (cmate > -1)
					checkOtherCells(cmate,coords,le,b);
				else
				{
					e.first=c->getVertexId(lado+1);
					e.second=c->getVertexId(lado+2);
					le.insert(e);
					
				}
			}
		}		
	}
	left = geom.inLeft(v[2],v[0],coords[0],coords[1]);
	if (left==1)
	{
		if(isViolatedEdge(c->getVertexId(lado+2),c->getVertexId(lado),coords))
		{
			cmate=c->getMateId(lado+1);
			if(b)
			{
				if(isRestrictionEdge(c->getVertexId(lado+2),c->getVertexId(lado)))
				{
					e.first=c->getVertexId(lado+2);
					e.second=c->getVertexId(lado);
					le.insert(e);
					if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
				}
				else
					if ( cmate> -1)	
						checkOtherCells(cmate,coords,le,b);
			}
			else
			{
				if (cmate > -1)
					checkOtherCells(cmate,coords,le,b);
				else
				{
					e.first=c->getVertexId(lado+2);
					e.second=c->getVertexId(lado);
					le.insert(e);
					
				}
			}
		}		
	}
	
}
template <class _Traits> void ofRuppert2D<_Traits>::getViolatedEdges(ids ic,space *coords,ofList<sEdge> &le,bool b)
{
	sEdge e;
	ids icv=getInsideCell(ic,coords);
	if(icv>-1)
	{
		ids iauxc;
		int lado=0;
		ids v[3];
		sCell *c=mesh->getCell(icv);
		
		
		v[0] = c->getVertexId(lado);
		v[1] = c->getVertexId((lado+1)%3);
		v[2] = c->getVertexId((lado+2)%3);
		if(isViolatedEdge(v[0],v[1],coords))
		{
			if (isBoundaryEdge(icv,v[0],v[1])==false)
				{
					if((b)&&(isRestrictionEdge(v[0],v[1])))
					{
						e.first=v[0];
						e.second=v[1];
						le.insert(e);
					}
					iauxc = c->getMateId((lado+2)%3);
					checkOtherCells(iauxc,coords,le,b);
	                			
				}
				else
				{
					e.first=v[0];
					e.second=v[1];
					le.insert(e);
					
				}
		}
		if(isViolatedEdge(v[1],v[2],coords))
		{
			if (isBoundaryEdge(icv,v[1],v[2])==false)
				{
					if((b)&&(isRestrictionEdge(v[1],v[2])))
					{
						e.first=v[1];
						e.second=v[2];
						le.insert(e);
					}
				
					iauxc = c->getMateId((lado)%3);
					checkOtherCells(iauxc,coords,le,b);
	                			
				}
				else
				{
					e.first=v[1];
					e.second=v[2];
					le.insert(e);
					
				}
		}
		if(isViolatedEdge(v[2],v[0],coords))
		{
			if (isBoundaryEdge(icv,v[2],v[0])==false)
				{
					if((b)&&(isRestrictionEdge(v[2],v[0])))
					{
						e.first=v[2];
						e.second=v[0];
						le.insert(e);
					}
					iauxc = c->getMateId((lado+1)%3);
					checkOtherCells(iauxc,coords,le,b);
	                			
				}
				else
				{
					e.first=v[2];
					e.second=v[0];
					le.insert(e);
					
				}
		}
	}
	
}
template <class _Traits> int ofRuppert2D<_Traits>::getInsideCell(ids ic,space *coords)
{
	int sobre, left, nsobre,lado;
	ids icell, iauxc,idcelula;
	sVertex *v[3];
	iauxc = ic;
	sCell *c=mesh->getCell(iauxc);
	sCell *auxc;
	
	
	while(1)
	{
		lado=0;
		c=mesh->getCell(iauxc);
		v[0] = mesh->getVertex(c->getVertexId(lado));
		v[1] = mesh->getVertex(c->getVertexId((lado+1)%3));
		v[2] = mesh->getVertex(c->getVertexId((lado+2)%3));
		nsobre = 0;
		left = geom.inLeft(v[0],v[1],coords[0],coords[1]);
		if (left > 0)
		{
			if (left == 2) // o vertice esta sobre uma reta
			{
				
				sobre = (lado + 2)% 3;
				nsobre++;
			}
			
			left = geom.inLeft(v[1],v[2],coords[0],coords[1]);
			if(left > 0)
			{
				if (left == 2) 
				{
					
					if(nsobre == 0)
						sobre = lado;
						
					nsobre++;
				
				}
				
				left = geom.inLeft(v[2],v[0],coords[0],coords[1]);
				if (left > 0)
				{
					idcelula = iauxc; 
					
					if(nsobre == 0)
					{
						if(left == 2)
						{
							lado = (lado+1)%3;
							return iauxc;
						}
						else
						{
							lado = -1; 
							return iauxc;
						}
					}
					else
					{
						lado = sobre;
						if(nsobre == 1)
							return iauxc;
						
					}
					return iauxc;
				}
				else
				{
					if (c->getMateId((lado+1)%3) >= 0)
					{
						icell = iauxc;
						iauxc = c->getMateId((lado+1)%3);
						auxc = mesh->getCell(iauxc);
						lado = (auxc->getMateIndex(icell) + 2)%3;//lado = (auxc->getMateVertexIndex(icell) + 2)%3;
					}
					else
					{
						
						return -1;
					}
				}
			}
			else
			{
				if (c->getMateId(lado) >= 0)
				{
					icell = iauxc;
					iauxc = c->getMateId(lado);
					auxc = mesh->getCell(iauxc);
					lado = (auxc->getMateIndex(icell) + 2)%3;//lado = (auxc->getMateVertexIndex(icell) + 2)%3;
				}	
				else
				{
					
					return -1;
				}
			}
		}
		else
		{
			if (c->getMateId((lado+2)%3) > -1)
			{
				icell = iauxc;
				iauxc = c->getMateId((lado+2)%3);
				auxc = mesh->getCell(iauxc);
				lado = (auxc->getMateIndex(icell) + 2)%3;//lado = (auxc->getMateVertexIndex(icell) + 2)%3;
			}
			else
			{
				
				return -1;
			}
		}
	}
	
}
template <class _Traits> void ofRuppert2D<_Traits>::refineMesh()
{
	ofCellsIterator<_Traits> itc(mesh);
	space coords[2];
	ids v1,v2,v3;
	int nc=mesh->getNumberOfCells();
	int i;
	itc.initialize();
	for(i=0; i<nc; i++)
	{
		
		//v1=itc->getVertex(0);
		//v2=itc->getVertex(1);
		//v3=itc->getVertex(1);
		geom.getCircuncircle(mesh->getCell(&itc),coords);
		//coords[0]=(mesh->getVertex(v1)->getCoord(0)+mesh->getVertex(v2)->getCoord(0)+mesh->getVertex(v3)->getCoord(0))/3.0;
		//coords[1]=(mesh->getVertex(v1)->getCoord(1)+mesh->getVertex(v2)->getCoord(1)+mesh->getVertex(v3)->getCoord(1))/3.0;
		delaunay->addVertex(coords,&itc);
		numberOfInsertedVertices++;
		++itc;
	}
}
template <class _Traits> bool ofRuppert2D<_Traits>::checkInsertion(space *coords, int &bound, int &pos)
{
	int i, j;
	ofList<ids> *l;
	for(i = 0; i < bounds->size(); i++)
	{
		l = bounds->pos(i);
		for(j = 0; j < l->size()-1; j++)
		{
			if(geom.dot(l->pos(j),l->pos((j+1)%l->size()), coords) <= 0.0 )
			{
				bound = i;
				pos = j;
				return false;
			}				
		}
	}
	return true;
}
template <class _Traits> void ofRuppert2D<_Traits>::getCircuncircleCoords(space *c,ids ic)
{
	geom.getCircuncircle(mesh->getCell(ic),c);
	c[2]=0.0;
}


template <class _Traits> void ofRuppert2D<_Traits>::findBoundaryVertices()
{
	ofVerticesIterator<_Traits> itv(mesh);
	
	for(itv.initialize();itv.notFinish();++itv)
	{
		if(isBoundaryVertex(&itv))
			boundary_vertices.insert(&itv);
	}
}
#undef IDS10
#undef SMESH
#undef SPACE6
}
#endif
