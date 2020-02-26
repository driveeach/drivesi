#ifndef OFMYDELAUNAY2D_H
#define OFMYDELAUNAY2D_H
#include "ofMacros.h"
#include "ofBoundaryCellCIterator2D.h"
#include "ofList.h"
#include"ofDelaunay2D.h"
namespace of
{
	#define IDS typename ofMyDelaunay2D<_Traits>::ids
	#define SVERTEX11 typename ofMyDelaunay2D<_Traits>::sVertex
	template <class _Traits> class ofMyDelaunay2D : public ofDelaunay2D<_Traits>
{
public:
	
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh;
	typedef typename _Traits::sGeometric sGeometric;
	
	
	ofMyDelaunay2D(int block_size = 1000, bool kdtree = true);
	ofMyDelaunay2D(sMesh *_mesh, bool kdtree = true);
	~ofMyDelaunay2D();
	
	ids addVertexC(space *coords, ids &newcell,ids inicell = -1);
	ids add(ids idvertice, ids inicell OF_DMUTEXV);
	
	sVertex* nearestVertex(ids vid);
	sVertex* nearestVertex(sVertex *v);
	
private:
	//void add(ids idvertice, ids inicell,bool kdok=true);
	
	class sObjectCompare
	{
		public:
		bool greater(sVertex *v1, sVertex *v2, int dim)
		{
			OF_ASSERT((dim == 0)||(dim == 1)||(dim==2));
			return v1->getCoord(dim) > v2->getCoord(dim);
		};
		space squareDistance(sVertex *v1,sVertex *v2)
		{
			space square=0.0;
			int i,size=v1->getDimension();
			for(i=0;i<size;i++)
				square+=(v2->getCoord(i)-v1->getCoord(i))*(v2->getCoord(i)-v1->getCoord(i));
			return square;
		};
		space distance(sVertex *v1,sVertex *v2)
		{
			return sqrt(squareDistance(v1,v2));
		};
		bool distComp(sVertex *v1,sVertex *v2,space w,int dim)
		{
			if((v1->getCoord(dim)+w)>v2->getCoord(dim))
				return true;
			else
				return false;
		};
		bool distComp1(sVertex *v1,sVertex *v2,space w,int dim)
		{
			if((v1->getCoord(dim)-w)<v2->getCoord(dim))
				return true;
			else
				return false;
		};
	};
	
	bool free;
	
	
	
	
	
	ofKdTree<sVertex*,sObjectCompare> *kd;
	
	
};
template <class _Traits> ofMyDelaunay2D<_Traits>::ofMyDelaunay2D(int block_size, bool kdtree)
{
	free = true;
	this->mesh = new sMesh(block_size);
	this->geom.setMesh(this->mesh);
	
	if(kdtree)
		kd = new ofKdTree<sVertex*,sObjectCompare>(3);
	else
		kd = NULL;
}
template <class _Traits> ofMyDelaunay2D<_Traits>::ofMyDelaunay2D(sMesh *_mesh, bool kdtree)
{
	OF_ASSERT(_mesh);
	
	free = false;
	this->mesh = _mesh;
	this->geom.setMesh(this->mesh);
	if(kdtree)
		kd = new ofKdTree<sVertex*,sObjectCompare>(3);
	else
		kd = NULL;
		
	if(this->mesh->getNumberOfVertices() >= 3)
	{
		ofVerticesIterator<_Traits> it(this->mesh);
		ids newcell[3];
	
		it.initialize();		
				
		if(kd) kd->insert(*it);
		newcell[0] = &it;
		++it;
		if(kd) kd->insert(*it);
		newcell[1] = &it;
		++it;
		if(kd) kd->insert(*it);
		newcell[2] = &it;
	
		this->mesh->addCell(newcell);
	
		for(++it; it.notFinish(); ++it)
			add(&it, -1);
	}
	else
	{
		ofVerticesIterator<_Traits> it(this->mesh);
		
		for(it.initialize(); it.notFinish(); ++it)				
			if(kd)
				kd->insert(*it);	
	}
}
template <class _Traits> ofMyDelaunay2D<_Traits>::~ofMyDelaunay2D()
{
	if(kd)
		delete kd;
	
	
}
template <class _Traits> SVERTEX11* ofMyDelaunay2D<_Traits>::nearestVertex(ids vid)
{
	return kd->findNN(this->mesh->getVertex(vid));
}
// template <class _Traits> IDS ofMyDelaunay2D<_Traits>::add(ids idvertice, ids inicell OF_DMUTEXV)
// 
// {
// 
// 	OF_ASSERT(idvertice >=0);
// 
// 	
// 
// 	int edge = -1, pos;
// 
// 	
// 
// 	if(kd)
// 
// 	{
// 
// 		sObject *novo = new sObject(mesh->getVertex(idvertice),idvertice);
// 
// 		
// 
// 		if(inicell < 0)
// 
// 		{
// 
// 			sObject *o = kd->nearestAndInsert(novo);
// 
// 			if(o)
// 
// 				inicell = o->v->getSingCell();
// 
// 		}
// 
// 		else
// 
// 			kd->insert(novo);
// 
// 	}		
// 
// 
// 
// 	pos = searchInMesh(idvertice, inicell, edge OF_MUTEXV);
// 
// //	pos = search.dido(mesh->getVertex(mesh->getCell(inicell)->getVertexId(0)),mesh->getVertex(idvertice)->getCoords(), inicell, edge OF_MUTEXV);
// 
// 	
// 
// 	OF_ASSERT(pos != 3); // vertice repetido
// 
// 	
// 
// 	if( pos == 0 )
// 
// 		insertIn(inicell,idvertice OF_MUTEXV);
// 
// 	else if( pos == 1 )
// 
// 		insertOut(inicell,idvertice,edge OF_MUTEXV);
// 
// 	else if( pos == 2)
// 
// 		insertOn(inicell,idvertice,edge OF_MUTEXV);
// 
// 
// 	return inicell;
// }
// template <class _Traits> IDS ofMyDelaunay2D<_Traits>::addVertexC(space *coords, ids &newcellid,ids inicell OF_DMUTEXV)
// 
// {
// 
// 	ids idvertice = searchVertex(coords);
// 
// 	if( idvertice < 0 )
// 
// 	{
// 
// 		idvertice = mesh->addVertex(coords OF_MUTEXV);
// 
// 		
// 
// 		if( mesh->getNumberOfVertices(OF_MUTEX) > 3)
// 
// 			newcellid=add(idvertice,inicell OF_MUTEXV);
// 
// 		
// 
// 		}
// 
// 	
// 
// 	
// 
// 	return idvertice;
// 
// }
template <class _Traits> SVERTEX11* ofMyDelaunay2D<_Traits>::nearestVertex(sVertex* v)
{
	return kd->findNN(v);
	//return kd->nearest(v);
}
//template <class _Traits> IDS13 ofMyDelaunay2D<_Traits>::addVertex(space *coords, ids inicell,bool kdok)
//
//
//{
//
//
//	ids idvertice = this->mesh->addVertex(coords);
//
//
//
//
//
//	
//
//
//	if( this->mesh->getNumberOfVertices() > 3)
//
//
//		this->add(idvertice,inicell,kdok);
//
//
//
//
//
//	else 
//
//
//	{
//
//
//		if((kd)&& (kdok))
//
//
//			kd->insert(this->mesh->getVertex(idvertice));
//
//
//		
//
//
//		if( this->mesh->getNumberOfVertices() == 3)
//
//
//		{
//
//
//			ids newcell[3];
//
//
//			
//
//
//			ofVerticesIterator<_Traits> it(this->mesh);
//
//
//			it.initialize();		
//
//
//			
//
//
//			newcell[0] = &it;
//
//
//			++it;
//
//
//			newcell[1] = &it;
//
//
//			++it;
//
//
//			newcell[2] = &it;
//
//
//
//
//
//			this->mesh->addCell(newcell);
//
//
//		}
//
//
//	}
//
//
//
//
//
//	return idvertice;
//
//
//}
//template <class _Traits> void ofMyDelaunay2D<_Traits>::add(ids idvertice, ids inicell,bool kdok)
//
//
//{
//
//
//	OF_ASSERT(idvertice >=0);
//
//
//	
//
//
//	int edge = -1, pos;
//
//
//	
//
//
//	if((kd)&&(kdok))
//
//
//	{
//
//
//		if(inicell < 0)
//
//
//		{
//
//
//			sVertex *v = kd->nearestAndInsert(this->mesh->getVertex(idvertice));
//
//
//			if(v)
//
//
//				inicell = v->getSingCell();
//
//
//		}
//
//
//		else
//
//
//			kd->insert(this->mesh->getVertex(idvertice));
//
//
//	}		
//
//
//
//
//
//	pos = searchInMesh(idvertice, inicell, edge);
//
//
//	
//
//
//	OF_ASSERT(pos != 3); // vertice repetido
//
//
//	
//
//
//	if( pos == 0 )
//
//
//		insertIn(inicell,idvertice);
//
//
//	else if( pos == 1 )
//
//
//		insertOut(inicell,idvertice,edge);		
//
//
//	else if( pos == 2)
//
//
//		insertOn(inicell,idvertice,edge);
//
//
//}
#undef IDS
#undef SVERTEX11
}
#endif
