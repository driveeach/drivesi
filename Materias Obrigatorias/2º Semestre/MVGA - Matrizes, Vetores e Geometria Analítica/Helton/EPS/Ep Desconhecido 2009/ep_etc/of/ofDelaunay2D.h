#ifndef OFDELAUNAY2D_H
#define OFDELAUNAY2D_H
#include "ofMacros.h"
#include "ofBoundaryCellCIterator2D.h"
#include "ofMesh.h"
#include "ofList.h"
#include "ofSearchDIDO.h"
namespace of
{
#define SPACE typename ofDelaunay2D<_Traits>::space
#define IDS typename ofDelaunay2D<_Traits>::ids
#define SMESH typename ofDelaunay2D<_Traits>::sMesh
template <class _Traits> class ofDelaunay2D
{
public:
	typedef typename _Traits::space 	space;
	typedef typename _Traits::ids 		ids;
	typedef typename _Traits::sVertex 	sVertex;
	typedef typename _Traits::sCell 	sCell;
	typedef typename _Traits::sGeometric sGeometric;
	typedef typename _Traits::sTopology sTopology;
	typedef typename _Traits::sMesh		sMesh;
	ofDelaunay2D(int block_size = 1000, bool kdtree = true);
	ofDelaunay2D(sMesh *_mesh, bool kdtree = true);
	~ofDelaunay2D();
	ids addVertex(space *coords, ids inicell = -1 OF_DMUTEXVD);
	ids searchVertex(space *coords);
	sMesh* getMesh(OF_DMUTEXD);
protected:
	sMesh* mesh;
	void add(ids idvertice, ids inicell OF_DMUTEXV);
	class sObject
	{
		public:
		sObject(sVertex *_v, ids _id)
		{
			v = _v;
			id = _id;
		};
		sVertex *v;
		ids id;
	};
	class sObjectCompare
	{
		public:
		bool equal(sObject *v1, sObject *v2)
		{
			return ( v1->v->getCoord(0) == v2->v->getCoord(0) )&&( v1->v->getCoord(1) == v2->v->getCoord(1) );
		};
		bool greater(sObject *v1, sObject *v2, int dim)
		{
			OF_ASSERT((dim == 0)||(dim == 1));
			return v1->v->getCoord(dim) > v2->v->getCoord(dim);
		};
	};
	ofKdTree<sObject*,sObjectCompare> *kd;
protected:
	bool free;
	sGeometric geom;
	sTopology  top;
	ofSearchDIDO<_Traits> search;
//	ofKdTree<sObject*,sObjectCompare> *kd;
	int searchInMesh(ids ivertex, ids &icelula, int &lado OF_DMUTEXV);
	void legalizeEdge(ids celula, int edge OF_DMUTEXV);
//	void searchInCircle(ids c, int e, ids nvertex, ofList<ids> *pontos, ofList<ids> *w);
	void insertIn(ids idcelula, ids idvertice OF_DMUTEXV);
	void insertOut(ids idcelula,ids idvertice, int edge OF_DMUTEXV);
	void insertOn(ids idcelula, ids idvertice, int edge OF_DMUTEXV);
#ifdef OF_THREADS
	void lockCells(ids id1, ids id2, sCell *c1, sCell *c2);
#endif
};
template <class _Traits> ofDelaunay2D<_Traits>::ofDelaunay2D(int block_size, bool kdtree)
:search(NULL)
{
	free = true;
	mesh = new sMesh(block_size);
	geom.setMesh(mesh);
	top.setMesh(mesh);
	if(kdtree)
		kd = new ofKdTree<sObject*,sObjectCompare>(2);
	else
		kd = NULL;
	search.setMesh(mesh);
}
template <class _Traits> ofDelaunay2D<_Traits>::ofDelaunay2D(sMesh *_mesh, bool kdtree)
:search(_mesh)
{
	OF_ASSERT(_mesh);
	free = false;
	mesh = _mesh;
	geom.setMesh(mesh);
	top.setMesh(mesh);
	if(kdtree)
		kd = new ofKdTree<sObject*,sObjectCompare>(2);
	else
		kd = NULL;
//	if(mesh->getNumberOfVertices() >= 3)
//	{
//		ofVerticesIterator<_Traits> it(mesh);
//		ids newcell[3];
//	
//		it.initialize();		
//				
//		if(kd) kd->insert(*it);
//		newcell[0] = &it;
//		++it;
//		if(kd) kd->insert(*it);
//		newcell[1] = &it;
//		++it;
//		if(kd) kd->insert(*it);
//		newcell[2] = &it;
//	
//		mesh->addCell(newcell);
//	
//		for(++it; it.notFinish(); ++it)
//			add(&it, -1);
//	}
//	else
//	{
//		ofVerticesIterator<_Traits> it(mesh);
//		
//		for(it.initialize(); it.notFinish(); ++it)				
//			if(kd)
//				kd->insert(*it);	
//	}
}
template <class _Traits> ofDelaunay2D<_Traits>::~ofDelaunay2D()
{
	if(kd)
		delete kd;
	if(free)
		delete mesh;
}
template <class _Traits> IDS ofDelaunay2D<_Traits>::searchVertex(space *coords)
{
	sVertex v;
	v.setCoords(coords);
	OF_ASSERT(kd);
	sObject o(&v,-1);
	sObject *near1;
	near1 = kd->nearest(&o);
	if((near1)&&(geom.sameVertices(&v,near1->v)))
		return near1->id;
	else
		return -1;
}
template <class _Traits> void ofDelaunay2D<_Traits>::add(ids idvertice, ids inicell OF_DMUTEXV)
{
	OF_ASSERT(idvertice >=0);
	int edge = -1, pos;
	if(kd)
	{
		sObject *novo = new sObject(mesh->getVertex(idvertice),idvertice);
		if(inicell < 0)
		{
			sObject *o = kd->nearestAndInsert(novo);
			if(o)
				inicell = o->v->getSingCell();
		}
		else
			kd->insert(novo);
	}		
	pos = searchInMesh(idvertice, inicell, edge OF_MUTEXV);
//	pos = search.dido(mesh->getVertex(mesh->getCell(inicell)->getVertexId(0)),mesh->getVertex(idvertice)->getCoords(), inicell, edge OF_MUTEXV);
	OF_ASSERT(pos != 3); // vertice repetido
	if( pos == 0 )
		insertIn(inicell,idvertice OF_MUTEXV);
	else if( pos == 1 )
		insertOut(inicell,idvertice,edge OF_MUTEXV);
	else if( pos == 2)
		insertOn(inicell,idvertice,edge OF_MUTEXV);
}
template <class _Traits> IDS ofDelaunay2D<_Traits>::addVertex(space *coords, ids inicell OF_DMUTEXV)
{
	ids idvertice = searchVertex(coords);
	if( idvertice < 0 )
	{
		idvertice = mesh->addVertex(coords OF_MUTEXV);
		if( mesh->getNumberOfVertices(OF_MUTEX) > 3)
			add(idvertice,inicell OF_MUTEXV);
		else 
		{
			sObject *novo = new sObject(mesh->getVertex(idvertice),idvertice);
			if(kd)
				kd->insert(novo);
			if( mesh->getNumberOfVertices(OF_MUTEX) == 3)
			{
				ids newcell[3];
				ofVerticesIterator<_Traits> it(mesh);
				it.initialize();
				newcell[0] = &it;
				++it;
				newcell[1] = &it;
				++it;
				newcell[2] = &it;
				mesh->addCell(newcell OF_MUTEXV);
			}
		}
	}
	return idvertice;
}
template <class _Traits> SMESH* ofDelaunay2D<_Traits>::getMesh(OF_DMUTEX)
{
	return mesh;
}
template <class _Traits> int ofDelaunay2D<_Traits>::searchInMesh(ids idvertex, ids &idcelula, int &lado OF_DMUTEXV)
{
	//Retorna 0 caso esteja dentro
	//Retorna 1 caso esteja fora
	//Retorna 2 caso esteja sobre a aresta lado
	//Retorna 3 caso seja um vertice repetido
	sCell* auxc = NULL;
	sVertex *v[3];
	ids icell, iauxc = 0;
	int sobre = 0, left, retorno = -1;
	if(idcelula >= 0)
	{
		iauxc = idcelula;
		auxc = mesh->getCell(idcelula OF_MUTEXV);
	}
	else
	{
		ofCellsIterator<_Traits> itc(mesh);
		itc.initialize();
		auxc = *itc;
		OF_ASSERT(auxc);
	}
#ifdef OF_THREADS
	if(mutex) auxc->lock();
#endif
	lado = 0;
	sVertex *vertex = mesh->getVertex(idvertex OF_MUTEXV);
	space *coords = vertex->getCoords();
	while(retorno < 0)
	{
		v[0] = mesh->getVertex(auxc->getVertexId(lado OF_MUTEXFALSEV) OF_MUTEXV);
		v[1] = mesh->getVertex(auxc->getVertexId((lado+1)%3 OF_MUTEXFALSEV) OF_MUTEXV);
		v[2] = mesh->getVertex(auxc->getVertexId((lado+2)%3 OF_MUTEXFALSEV) OF_MUTEXV);
		sobre = -1;
		left = geom.inLeft(v[0],v[1],coords[0], coords[1] OF_MUTEXFALSEV);
		if(left == 0)
		{
			if (auxc->getMateId((lado+2)%3 OF_MUTEXFALSEV) >= 0)
			{
				icell = iauxc;
				iauxc = auxc->getMateId((lado+2)%3 OF_MUTEXFALSEV);
				auxc = mesh->getCell(iauxc OF_MUTEXV);
				lado = (auxc->getMateIndex(icell OF_MUTEXFALSEV) + 2)%3;
			}
			else
			{
				lado = (lado+2)%3;
				idcelula = iauxc;
				retorno = 1;
			}
		}
		else
		{
			if(left == 2)
				sobre = (lado+2)%3;
			left = geom.inLeft(v[1],v[2],coords[0], coords[1] OF_MUTEXFALSEV);
			if(left == 0)
			{
				if (auxc->getMateId(lado OF_MUTEXFALSEV) >= 0)
				{
					icell = iauxc;
					iauxc = auxc->getMateId(lado OF_MUTEXFALSEV);
					auxc = mesh->getCell(iauxc OF_MUTEXV);
					lado = (auxc->getMateIndex(icell OF_MUTEXFALSEV) + 2)%3;
				}	
				else
				{
					//lado = lado;
					idcelula = iauxc;
					retorno = 1;
				}
			}
			else
			{
				if (left == 2)
				{
					if(sobre >= 0)
					{
						lado = (lado+1)%3;
						idcelula = iauxc;
						retorno = 3;
					}
					else
						sobre = lado;
				}
				if(retorno < 0)
				{
					left = geom.inLeft(v[2],v[0],coords[0], coords[1] OF_MUTEXFALSEV);
					if(left == 0)
					{
						if (auxc->getMateId((lado+1)%3 OF_MUTEXFALSEV) >= 0)
						{
							icell = iauxc;
							iauxc = auxc->getMateId((lado+1)%3 OF_MUTEXFALSEV);
							auxc = mesh->getCell(iauxc OF_MUTEXV);
							lado = (auxc->getMateIndex(icell OF_MUTEXFALSEV) + 2)%3;
						}
						else
						{
							lado = (lado+1)%3;
							idcelula = iauxc;
							retorno = 1;
						}						
					}
					else
					{
						if(left == 2)
						{
							if(sobre >= 0)
							{
								idcelula = iauxc;
								if(sobre == lado)								
									lado = (lado+2)%3;
								retorno = 3;
							}
							else
							{
								idcelula = iauxc;
								lado = (lado+1)%3;
								retorno = 2;
							}
						}
						else
						{			
							if(sobre >= 0)
							{
								idcelula = iauxc;
								lado = sobre;
								retorno = 2;
							}	
							else
							{
								idcelula = iauxc;
								lado = -1;
								retorno = 0;
							}
						}
					}
				}
			}
		}
	}
#ifdef OF_THREADS
	if(mutex) auxc->unlock();
#endif
	return retorno;
}
template <class _Traits> void ofDelaunay2D<_Traits>::legalizeEdge(ids idcelula, int edge OF_DMUTEXV)
{
	sCell *celula = mesh->getCell(idcelula OF_MUTEXV);
	if ( celula->getMateId(edge OF_MUTEXV) >= 0)
	{
		ids idoposta = celula->getMateId(edge OF_MUTEXV);
		sCell *oposta = mesh->getCell(idoposta OF_MUTEXV);
#ifdef OF_THREADS
		if(mutex) lockCells(idcelula, idoposta, celula, oposta);
#endif
		ids idv = celula->getVertexId(edge OF_MUTEXFALSEV);
		OF_ASSERT(oposta->getVertexMateId(idcelula OF_MUTEXFALSEV) >= 0); // troquei: getMateVertexId
		if(geom.inCircle(mesh->getVertex(celula->getVertexId((edge+2)%3)),
						 mesh->getVertex(celula->getVertexId(edge)),
						 mesh->getVertex(celula->getVertexId((edge+1)%3)),
						 mesh->getVertex(oposta->getVertexMateId(idcelula OF_MUTEXFALSEV) OF_MUTEXV) OF_MUTEXFALSEV))//troquei: getMateVertexId
		{
			geom.flip2D(idcelula, idoposta OF_MUTEXFALSEV);
			OF_ASSERT(oposta->getVertexId(0 OF_MUTEXFALSEV) != oposta->getVertexId(1 OF_MUTEXFALSEV));
			OF_ASSERT(oposta->getVertexId(2 OF_MUTEXFALSEV) != oposta->getVertexId(1 OF_MUTEXFALSEV));
			OF_ASSERT(oposta->getVertexId(0 OF_MUTEXFALSEV) != oposta->getVertexId(2 OF_MUTEXFALSEV));
			OF_ASSERT(celula->getVertexId(0 OF_MUTEXFALSEV) != celula->getVertexId(1 OF_MUTEXFALSEV));
			OF_ASSERT(celula->getVertexId(2 OF_MUTEXFALSEV) != celula->getVertexId(1 OF_MUTEXFALSEV));
			OF_ASSERT(celula->getVertexId(0 OF_MUTEXFALSEV) != celula->getVertexId(2 OF_MUTEXFALSEV));
#ifdef OF_THREADS
			if(mutex) lockCells(idcelula, idoposta, celula, oposta);
#endif
			legalizeEdge(idcelula,celula->getVertexIndex(idv OF_MUTEXV) OF_MUTEXV);
			legalizeEdge(idoposta,oposta->getVertexIndex(idv OF_MUTEXV) OF_MUTEXV);
		}
#ifdef OF_THREADS
		else if(mutex) lockCells(idcelula, idoposta, celula, oposta);
#endif
	}
}
#ifdef OF_THREADS
template <class _Traits> void ofDelaunay2D<_Traits>::lockCells(ids id1, ids id2, sCell *c1, sCell *c2)
{
	if(id1 < id2)
	{
		c1->lock();
		c2->lock();
	}
	else
	{
		c2->lock();
		c1->lock();
	}		
}
#endif
//template <class _Traits> void ofDelaunay2D<_Traits>::searchInCircle(ids c, int e, ids nvertex, ofList<ids> *pontos, ofList<ids> *w)
//{
//	sCell *cell = mesh->getCell(c), *tempc;
//	ids cant, cini  = c;
//	sVertex *v = mesh->getVertex(nvertex);
//
//	int eini = e;
//	do
//	{
//		
//		if (cell->getMateId(e) >= 0)
//		{
//			tempc = mesh->getCell(cell->getMateId(e));
//			
//			if (geom.inCircle(tempc, v)>0)
//			{
//				if(!w->inList(c))
//					w->insert(c);
//				
//				cant = c;
//				c = cell->getMateId(e);
//				cell = mesh->getCell(c);
//				e = (cell->getMateIndex(cant) + 1)%3;
//			}
//			else
//			{
//				if(!w->inList(c))
//					w->insert(c);
//				if(!pontos->inList(cell->getVertexId((e+1)%3)))
//					pontos->insert(cell->getVertexId((e+1)%3));
//				e = (e+1)%3;
//			}
//		}
//		else
//		{
//			if(!w->inList(c))
//				w->insert(c);
//			if(!pontos->inList(cell->getVertexId((e+1)%3)))
//				pontos->insert(cell->getVertexId((e+1)%3));
//			e = (e+1)%3;
//		}
//		
//
//	}while(((c != cini)||(e != eini))||(pontos->size()==0));
//
//	if(!w->inList(c))
//		w->insert(c);
//}
template <class _Traits> void ofDelaunay2D<_Traits>::insertIn(ids idcelula, ids idvertice OF_DMUTEXV)
{
	sCell *c = mesh->getCell(idcelula OF_MUTEXV);
	ids vids[3] = { c->getVertexId(0 OF_MUTEXV), c->getVertexId(1 OF_MUTEXV), c->getVertexId(2 OF_MUTEXV)};
	ids newcell[3], cellsids[3];
	mesh->delCell(idcelula OF_MUTEXV);
	newcell[0] = idvertice;
	newcell[1] = vids[0];			
	newcell[2] = vids[1];
	cellsids[0] = mesh->addCell(newcell OF_MUTEXV);
	newcell[0] = idvertice;
	newcell[1] = vids[1];
	newcell[2] = vids[2];
	cellsids[1] = mesh->addCell(newcell OF_MUTEXV);
	newcell[0] = idvertice;
	newcell[1] = vids[2];
	newcell[2] = vids[0];
	cellsids[2] = mesh->addCell(newcell OF_MUTEXV);
	legalizeEdge(cellsids[0],mesh->getCell(cellsids[0] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
	legalizeEdge(cellsids[1],mesh->getCell(cellsids[1] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
	legalizeEdge(cellsids[2],mesh->getCell(cellsids[2] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
}
template <class _Traits> void ofDelaunay2D<_Traits>::insertOut(ids c, ids v, int edge  OF_DMUTEXV)
{
	ofBoundaryCellCIterator2D<_Traits> ibc(mesh);
	sVertex *vs[3];
	vs[2] = mesh->getVertex(v OF_MUTEXV);
	ibc.initialize(c,edge);
	vs[0] = mesh->getVertex(ibc->getVertexId((ibc.getEdge()+2)%3));
	vs[1] = mesh->getVertex(ibc->getVertexId((ibc.getEdge()+1)%3));
	while(geom.inLeft(vs[0], vs[1], vs[2]) == 1)
	{
		++ibc;
		vs[0] = mesh->getVertex(ibc->getVertexId((ibc.getEdge()+2)%3));
		vs[1] = mesh->getVertex(ibc->getVertexId((ibc.getEdge()+1)%3));
	}
//	ids tempc;
	int tempe, i;
	ofList<ids> lista, listaw;
	do
	{
		--ibc;
		tempe = (ibc.getEdge()+1)%3;
//		if(geom.inCircle(*ibc, vs[2]) > 0)
//			searchInCircle(&ibc,tempe,v,&lista, &listaw);
//		else
//		{
			if(!lista.inList(ibc->getVertexId((tempe+1)%3)))
				lista.insert(ibc->getVertexId((tempe+1)%3));
//		}				
		vs[0] = mesh->getVertex(ibc->getVertexId((ibc.getEdge()+2)%3));
		vs[1] = mesh->getVertex(ibc->getVertexId((ibc.getEdge()+1)%3));
	}while( geom.inLeft(vs[0],vs[1],vs[2]) == 1);
//	for(i=0; i < listaw.size(); i++)
//	{
//		tempc = listaw.pos(i);
//		mesh->delCell(tempc);
//	}
	int vids[3];
	for(i=0; i < (lista.size()-1); i++)
	{
		vids[0] = v;
		vids[1] = lista.pos(i);
		vids[2] = lista.pos(i+1);				
		listaw.insert(mesh->addCell(vids));
//		mesh->addCell(vids);
	}
	for(i = 0; i < listaw.size(); i++)
		legalizeEdge(listaw.pos(i),mesh->getCell(listaw.pos(i))->getVertexIndex(v));
	lista.clear();
	listaw.clear();
}
template <class _Traits> void ofDelaunay2D<_Traits>::insertOn(ids c, ids idvertice, int edge  OF_DMUTEXV)
{
	ids coposto;
	sCell *cell = mesh->getCell(c OF_MUTEXV), *cellop;
	coposto = cell->getMateId(edge OF_MUTEXV);
	if(coposto >= 0)
	{			
		cellop = mesh->getCell(coposto OF_MUTEXV);
		ids vids[4];
		vids[0] = cell->getVertexId(edge OF_MUTEXV);
		vids[1] = cell->getVertexId((edge+1)%3 OF_MUTEXV);
		vids[2] = cellop->getVertexMateId(c OF_MUTEXV);
		vids[3] = cell->getVertexId((edge+2)%3 OF_MUTEXV);
		ids newcell[3], cellsids[4];
		mesh->delCell(c OF_MUTEXV);
		mesh->delCell(coposto OF_MUTEXV);
		newcell[0] = idvertice;
		newcell[1] = vids[0];
		newcell[2] = vids[1];
		cellsids[0] = mesh->addCell(newcell OF_MUTEXV);
		newcell[0] = idvertice;
		newcell[1] = vids[1];
		newcell[2] = vids[2];
		cellsids[1] = mesh->addCell(newcell OF_MUTEXV);
		newcell[0] = idvertice;
		newcell[1] = vids[2];
		newcell[2] = vids[3];
		cellsids[2] = mesh->addCell(newcell OF_MUTEXV);
		newcell[0] = idvertice;
		newcell[1] = vids[3];
		newcell[2] = vids[0];
		cellsids[3] = mesh->addCell(newcell OF_MUTEXV);
		legalizeEdge(cellsids[0],mesh->getCell(cellsids[0] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
		legalizeEdge(cellsids[1],mesh->getCell(cellsids[1] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
		legalizeEdge(cellsids[2],mesh->getCell(cellsids[2] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
		legalizeEdge(cellsids[3],mesh->getCell(cellsids[3] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
	}
	else
	{
		ids vids[3];
		vids[0] = cell->getVertexId(edge OF_MUTEXV);
		vids[1] = cell->getVertexId((edge+1)%3 OF_MUTEXV);
		vids[2] = cell->getVertexId((edge+2)%3 OF_MUTEXV);
		ids newcell[3], cellsids[2];
		mesh->delCell(c OF_MUTEXV);
		newcell[0] = idvertice;
		newcell[1] = vids[0];
		newcell[2] = vids[1];
		cellsids[0] = mesh->addCell(newcell OF_MUTEXV);
		newcell[0] = idvertice;
		newcell[1] = vids[2];
		newcell[2] = vids[0];
		cellsids[1] = mesh->addCell(newcell OF_MUTEXV);
		legalizeEdge(cellsids[0],mesh->getCell(cellsids[0] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
		legalizeEdge(cellsids[1],mesh->getCell(cellsids[1] OF_MUTEXV)->getVertexIndex(idvertice OF_MUTEXV) OF_MUTEXV);
	}
}
#undef SPACE
#undef IDS
#undef SMESH
}
#endif
