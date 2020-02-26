#ifndef OFMESH3D_H
#define OFMESH3D_H
#include "ofMacros.h"
#include "ofSing.h"
#include "ofMesh.h"
#include "ofMeshOper.h"
#include "ofVertexStarIterator3D.h"
namespace of
{
/**	Operation Class for Tetrahedrons in 3D space (with not oriented vertices)
 * 
 * 	_Traits must have: ids, sVertex, sCell, sSing, sMesh
 */
template <class _Traits> class ofMesh3D : public ofMeshOper<_Traits>
{
public:
	typedef typename _Traits::ids 		ids;
	typedef typename _Traits::sVertex 	sVertex;
	typedef typename _Traits::sCell 	sCell;
	typedef ofSing<_Traits> 			sSing;
	typedef ofMesh<_Traits> 			sMesh;
		
	/**	Constructor
	 * 
	 * \param _mesh: the mesh address that this class will manipulate
	 */
	ofMesh3D(sMesh *_mesh);
	
	/**	Destructor */
	~ofMesh3D();
	
	/** Add cell in mesh
	 * 
	 *	\param idcell: cell id
	 * 	\param idvertices: vector with vertices ids of the cell
	 */
	 void addCell(ids idcell, ids *idvertices OF_DMUTEXVD);
	
	/**	Delete a cell
	 * 
	 * 	\param idcell: cell id
	 */
	 void delCell(ids idcell OF_DMUTEXVD);
private:
	void searchFace(int idcell, int idvertex);
	void searchSings(int idvertex);
	bool setMate(int idcell, int idcellmate, int iq[]);
	int sameVertices(int idcell1, int idcell2, int iq[]);
	
#ifdef OF_THREADS
	void lockVertices(ids *idv, sVertex** v);
	void unlockVertices(ids *idv, sVertex** v);
#endif
};
template <class _Traits> ofMesh3D<_Traits>::ofMesh3D(sMesh *_mesh)
: ofMeshOper<_Traits>(_mesh)
{
}
template <class _Traits> ofMesh3D<_Traits>::~ofMesh3D()
{
}
template <class _Traits> void ofMesh3D<_Traits>::addCell(ids idcell, ids *idvertices OF_DMUTEXV)
{
	OF_ASSERT((idvertices[0] >= 0)&&(idvertices[1] >= 0)&&(idvertices[2] >= 0)&&(idvertices[3] >= 0));
	OF_ASSERT((idvertices[0] != idvertices[1])&&(idvertices[0] != idvertices[2])&&(idvertices[0] != idvertices[3]));
	OF_ASSERT((idvertices[1] != idvertices[2])&&(idvertices[1] != idvertices[3])&&(idvertices[2] != idvertices[3]));
	sCell* c = this->mesh->getCell(idcell);
	ids idv[4] = { idvertices[0], idvertices[1], idvertices[2], idvertices[3]};
	sVertex *v[4] = { this->mesh->getVertex(idv[0]), this->mesh->getVertex(idv[1]), this->mesh->getVertex(idv[2]), this->mesh->getVertex(idv[3])};
	int ns = 0 // Numero de vertices com singularidade igual a 0
		, temp,tempa=idv[0],tempb=idv[1];
	
#ifdef OF_THREADS
	lockVertices(idv,v);
	c->lock();
#endif
	if (v[0]->getNumberOfSings(OF_MUTEXFALSE) == 0)
		ns++;
	if (v[1]->getNumberOfSings(OF_MUTEXFALSE) == 0)
	{
// 		temp = idv[0];
// 		idv[0] = idv[1];
// 		idv[1] = temp;
		tempa=idv[1];tempb=idv[0];
		ns++;
	}	
	if (v[2]->getNumberOfSings(OF_MUTEXFALSE) == 0)
	{
// 		temp = idv[0];
// 		idv[0] = idv[2];
// 		idv[2] = temp;
		tempa=idv[2];
		ns++;
	}	
	if (v[3]->getNumberOfSings(OF_MUTEXFALSE) == 0)
	{
// 		temp = idv[0];
// 		idv[0] = idv[3];
// 		idv[3] = temp;
		tempa=idv[3];
		ns++;
	}	
	
	c->setVertexId(0, idv[0] OF_MUTEXFALSEV);
	c->setVertexId(1, idv[1] OF_MUTEXFALSEV);
	c->setVertexId(2, idv[2] OF_MUTEXFALSEV);
	c->setVertexId(3, idv[3] OF_MUTEXFALSEV);
	c->setMateId(0,-1 OF_MUTEXFALSEV);
	c->setMateId(1,-1 OF_MUTEXFALSEV);
	c->setMateId(2,-1 OF_MUTEXFALSEV);
	c->setMateId(3,-1 OF_MUTEXFALSEV);
	
	v[0] = this->mesh->getVertex(idv[0] OF_MUTEXFALSEV);
	v[1] = this->mesh->getVertex(idv[1] OF_MUTEXFALSEV);
	v[2] = this->mesh->getVertex(idv[2] OF_MUTEXFALSEV);
	v[3] = this->mesh->getVertex(idv[3] OF_MUTEXFALSEV);
	
	if(ns > 1)
	{
		// Nenhuma face em comum
		v[0]->addSing(idcell OF_MUTEXFALSEV);
		v[1]->addSing(idcell OF_MUTEXFALSEV);
		v[2]->addSing(idcell OF_MUTEXFALSEV);
		v[3]->addSing(idcell OF_MUTEXFALSEV);
	}
	else if(ns == 1)
	{
		// Apenas a face oposta a index1 pode ser comum
		searchFace(idcell,tempb);
		v[0]->addSing(idcell OF_MUTEXFALSEV);
		
		if (c->getMateId(0 OF_MUTEXFALSEV) < 0)
		{
			// Nenhuma face em comum foi encontrada
			v[1]->addSing(idcell OF_MUTEXFALSEV);
			v[2]->addSing(idcell OF_MUTEXFALSEV);
			v[3]->addSing(idcell OF_MUTEXFALSEV);
		}
	}
	else
	{ 	
		// Qualquer face pode ser comum
		
		searchFace(idcell,tempa);
		searchFace(idcell,tempb);
		
		if (c->getMateId(0 OF_MUTEXFALSEV) < 0)
		{
			// Nenhuma face em comum foi encontrada
			v[1]->addSing(idcell OF_MUTEXFALSEV);
			v[2]->addSing(idcell OF_MUTEXFALSEV);
			v[3]->addSing(idcell OF_MUTEXFALSEV);
		}
		if (c->getMateId(1) < 0)
		{
			// Nenhuma face em comum foi encontrada
			v[0]->addSing(idcell OF_MUTEXFALSEV);
			v[2]->addSing(idcell OF_MUTEXFALSEV);
			v[3]->addSing(idcell OF_MUTEXFALSEV);
		}
		searchSings(idv[0]);
		searchSings(idv[1]);
		searchSings(idv[2]);
		searchSings(idv[3]);
	}
	
#ifdef OF_THREADS
	unlockVertices(idv,v);
	c->unlock();
#endif
}
template <class _Traits> void ofMesh3D<_Traits>::delCell(ids idcell OF_DMUTEXV)
{
}
template <class _Traits> void ofMesh3D<_Traits>::searchFace(int idcell, int idvertex)
{
	OF_ASSERT(this->mesh->getVertex(idvertex)->getNumberOfSings());
	ofVertexStarIterator3D<_Traits> ic(this->mesh);
	ofList<ids> lista;
	
	for(ic.initialize(idvertex); ic.notFinish(); ++ic)
	{
		OF_ASSERT(!lista.inList(&ic));
		lista.insert(&ic);
	}
	
	int iq[4], ig;
	ids c;
	while(!lista.empty())
	{
		c = lista.first();
		lista.erase(0);
		
		ig = sameVertices(c, idcell, iq);
		
		OF_ASSERT(!((c != idcell)&&(ig == 4)));
		
		if((c != idcell)&&(ig == 3))
		{
			setMate(idcell, c, iq);
			setMate(c, idcell, iq);
		}
	}
}
template <class _Traits> int ofMesh3D<_Traits>::sameVertices(int idcell1, int idcell2, int iq[])
{
	int i, j, v1, v2, nv = 0;
	
	for(i=0; i<4; i++)
	{
		v1 = this->mesh->getCell(idcell1)->getVertexId(i);
		for(j=0; j<4; j++)
		{
			v2 = this->mesh->getCell(idcell2)->getVertexId(j);
			if(v1 == v2)
			{
				iq[nv++] = v1;
				break;
			}
		}
	}
	
	return nv;
}
template <class _Traits> bool ofMesh3D<_Traits>::setMate(int idcell, int idcellmate, int iq[])
{
	int temp;
	for(int i=0; i<4; i++)
	{
		temp = this->mesh->getCell(idcell)->getVertexId(i);
		if((temp != iq[0])&&(temp != iq[1])&&(temp != iq[2]))
		{
			if (this->mesh->getCell(idcell)->getMateId(i) < 0) 
			{
				this->mesh->getCell(idcell)->setMateId(i,idcellmate);
				return true;
			}
			else
				break;
		}
	}
	
	return false;
}
template <class _Traits> void ofMesh3D<_Traits>::searchSings(int idvertex)
{
	ofList<ids> lista;
	ofList<ids> flags;
	sVertex *v = this->mesh->getVertex(idvertex);
	sSing *s = v->getSing();
	sSing *ants = s;
	sCell *atu;
	int i;
	ids iatu;
	lista.insert(s->getCell());
	flags.insert(s->getCell());
	s = s->getNext();
	while(!lista.empty())
	{
		iatu = lista.first();
		lista.erase(0);
		atu = this->mesh->getCell(iatu);
		for(i=0; i<4; i++)
			if ((atu->getMateId(i) >= 0)&&(atu->getVertexId(i)!= idvertex)&&(!flags.inList(atu->getMateId(i))))
			{
				lista.insert(atu->getMateId(i));
				flags.insert(atu->getMateId(i));
			}
		while((s != NULL)&&(lista.empty()))
		{
			if(!flags.inList(s->getCell()))
			{
				lista.insert(s->getCell());
				flags.insert(s->getCell());
				ants = s;
				s = s->getNext();
			}
			else
			{
				ants->setNext(s->getNext());
				delete s;
				s = ants->getNext();	
			}
		}
	}
	lista.clear();
	flags.clear();
}
#ifdef OF_THREADS
template <class _Traits> void ofMesh3D<_Traits>::lockVertices(ids *idv, sVertex** v)
{
	ids ordem[4];
	int temp,i,j;
	
	for(i = 0; i < 4; i++)
	{
		ordem[i] = i;
		for(j = i; j > 0; j--)
		{
			if(idv[ordem[j-1]] > idv[ordem[j]])
			{
				temp = ordem[j];
				ordem[j] = ordem[j-1];
				ordem[j-1] = temp;				
			}
		}
	}
	
	v[ordem[0]]->lock();
	v[ordem[1]]->lock();
	v[ordem[2]]->lock();
	v[ordem[3]]->lock();
}
template <class _Traits> void ofMesh3D<_Traits>::unlockVertices(ids *idv, sVertex** v)
{
	ids ordem[4];
	int temp,i,j;
	
	for(i = 0; i < 4; i++)
	{
		ordem[i] = i;
		for(j = i; j > 0; j--)
		{
			if(idv[ordem[j-1]] > idv[ordem[j]])
			{
				temp = ordem[j];
				ordem[j] = ordem[j-1];
				ordem[j-1] = temp;				
			}
		}
	}
	
	v[ordem[3]]->unlock();
	v[ordem[2]]->unlock();
	v[ordem[1]]->unlock();
	v[ordem[0]]->unlock();
}
#endif
}
#endif
