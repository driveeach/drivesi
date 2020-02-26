#ifndef OFMESH2D_H
#define OFMESH2D_H
#include "ofMacros.h"
#include "ofSing.h"
#include "ofMesh.h"
#include "ofMeshOper.h"
namespace of
{
/**	Operation Class for Triangles in 2D space
 * 
 * 	_Traits must have: ids, sVertex, sCell, sSing, sMesh, sGeometric
 */
template <class _Traits> class ofMesh2D : public ofMeshOper<_Traits>
{
public:
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	
	typedef typename _Traits::sGeometric sGeometric;
	typedef typename _Traits::sTopology sTopology;
	
	typedef ofSing<_Traits> sSing;
	typedef ofMesh<_Traits> sMesh;
	
	/**	Constructor
	 * 
	 * \param _mesh: the mesh address that this class will manipulate
	 */
	ofMesh2D(sMesh *_mesh);
	
	/**	Destructor */
	~ofMesh2D();
	
	/** Add cell in mesh
	 * 
	 *	\param idcell: cell id
	 * 	\param idvertices: vector with vertices ids of the cell
	 */
	void addCell(ids idcell, ids *idvertices OF_DMUTEXVD);
	void addCell(ids idcell, ids *idvertices, ids *idops OF_DMUTEXVD);
	
	/**	Delete a cell
	 * 
	 * 	\param idcell: cell id
	 */
	void delCell(ids idcell OF_DMUTEXVD);
	
private:
	
	sGeometric geom;
	
#ifdef OF_THREADS
	void lockVertices(ids *idv, sVertex** v);
	void unlockVertices(ids *idv, sVertex** v);
#endif
};
template <class _Traits> ofMesh2D<_Traits>::ofMesh2D(sMesh *_mesh)
: ofMeshOper<_Traits>(_mesh)
{
}
template <class _Traits> ofMesh2D<_Traits>::~ofMesh2D()
{
}
template <class _Traits> void ofMesh2D<_Traits>::addCell(ids idcell, ids *idvertices OF_DMUTEXV)
{
	OF_ASSERT((idvertices[0] >= 0)&&(idvertices[1] >= 0)&&(idvertices[2] >= 0));
	OF_ASSERT((idvertices[0] != idvertices[1])&&(idvertices[0] != idvertices[2])&&(idvertices[1] != idvertices[2]));
	sCell *c = this->mesh->getCell(idcell OF_MUTEXV);
	ids idv[3] = { idvertices[0], idvertices[1], idvertices[2]};
	sVertex *v[3] = { 	this->mesh->getVertex(idv[0] OF_MUTEXV),
						this->mesh->getVertex(idv[1] OF_MUTEXV),
						this->mesh->getVertex(idv[2] OF_MUTEXV)};
#ifdef OF_THREADS
	c->lock();
	lockVertices(idv,v);
#endif
	
	sCell *cop;
	sSing *s;
	ids icop;
	int i, pos;
	
	c->clearMates(OF_MUTEXFALSE);
	
	i = geom.orientation2D(idv, v OF_MUTEXFALSEV);
	OF_ASSERT(i != 0);
	
	c->setVertexId(0,idv[0] OF_MUTEXFALSEV);
	c->setVertexId(1,idv[1] OF_MUTEXFALSEV);
	c->setVertexId(2,idv[2] OF_MUTEXFALSEV);
		
	for(i = 0; i < 3 ; i++)
	{
		s = v[(i+2)%3]->getSing(OF_MUTEXFALSE);
		while (s)
		{
			icop = s->getCell();
			cop = this->mesh->getCell(icop OF_MUTEXFALSEV);
			if ( cop->getVertexId((cop->getVertexIndex(idv[(i+2)%3]) + 1)%3) == idv[(i+1)%3] )
			{
				c->setMateId(i, icop OF_MUTEXFALSEV);
				cop->setMateId((cop->getVertexIndex(idv[(i+2)%3]) + 2)%3, idcell);
				s = NULL;
			}
			else
				s = s->getNext();
		}
	}
	for(i = 0; i < 3; i++)
	{
		if (c->getMateId((i+2)%3 OF_MUTEXFALSEV) < 0)
		{
			if(c->getMateId((i+1)%3 OF_MUTEXFALSEV) >= 0)
			{
		  		pos = v[i]->inSings(c->getMateId((i+1)%3 OF_MUTEXFALSEV) OF_MUTEXFALSEV);
				v[i]->setSingCell(pos,idcell OF_MUTEXFALSEV);
			}
			else
				v[i]->addSing(idcell OF_MUTEXFALSEV);
		}
		else
		{
			if(c->getMateId((i+1)%3 OF_MUTEXFALSEV) >= 0)
			{
				icop = c->getMateId((i+1)%3 OF_MUTEXFALSEV);
				pos = v[i]->inSings(icop OF_MUTEXFALSEV);
				while((icop >= 0)&&(icop != idcell))
				{
					cop = this->mesh->getCell(icop OF_MUTEXFALSEV);
					icop = cop->getMateId((cop->getVertexIndex(idv[i])+1)%3);
					if(icop < 0)
						v[i]->delSing(pos OF_MUTEXFALSEV);
				}
			}
		}
	}
#ifdef OF_THREADS
	unlockVertices(idv,v);
	c->unlock();
#endif
}
template <class _Traits> void ofMesh2D<_Traits>::addCell(ids idcell, ids *idvertices, ids *idops OF_DMUTEXV)
{
	OF_ASSERT((idvertices[0] >= 0)&&(idvertices[1] >= 0)&&(idvertices[2] >= 0));
	OF_ASSERT((idvertices[0] != idvertices[1])&&(idvertices[0] != idvertices[2])&&(idvertices[1] != idvertices[2]));
	sCell *c = this->mesh->getCell(idcell OF_MUTEXV);
#ifdef OF_THREADS
	sVertex *v[3] = { 	this->mesh->getVertex(idvertices[0] OF_MUTEXV),
						this->mesh->getVertex(idvertices[1] OF_MUTEXV),
						this->mesh->getVertex(idvertices[2] OF_MUTEXV)};
	c->lock();
	lockVertices(idvertices,v);
#endif
	
	c->setVertexId(0,idvertices[0] OF_MUTEXFALSEV);
	c->setVertexId(1,idvertices[1] OF_MUTEXFALSEV);
	c->setVertexId(2,idvertices[2] OF_MUTEXFALSEV);
	c->setMateId(0,idops[0] OF_MUTEXFALSEV);
	c->setMateId(1,idops[1] OF_MUTEXFALSEV);
	c->setMateId(2,idops[2] OF_MUTEXFALSEV);
#ifdef OF_THREADS
	unlockVertices(idvertices,v);
	c->unlock();
#endif
}
template <class _Traits> void ofMesh2D<_Traits>::delCell(ids idcell OF_DMUTEXV)
{
	int i,pos;
	sCell *cop, *c = this->mesh->getCell(idcell OF_MUTEXV);
	ids idv[3] = {	c->getVertexId(0 OF_MUTEXV), c->getVertexId(1 OF_MUTEXV), c->getVertexId(2 OF_MUTEXV) };
	sVertex *v[3] = { 	this->mesh->getVertex(idv[0] OF_MUTEXV),
						this->mesh->getVertex(idv[1] OF_MUTEXV),
						this->mesh->getVertex(idv[2] OF_MUTEXV)};
	ids icop;
#ifdef OF_THREADS
	if(mutex)
	{
		c->lock();
		lockVertices(idv,v);
	}
#endif
	for(i = 0; i < 3; i++)
	{
		if(c->getMateId((i+2)%3 OF_MUTEXFALSEV) >= 0)
		{
			if(c->getMateId((i+1)%3 OF_MUTEXFALSEV) >= 0)
			{
				icop = c->getMateId((i+1)%3 OF_MUTEXFALSEV);
				while((icop >= 0)&&(icop != idcell))
				{
					cop = this->mesh->getCell(icop OF_MUTEXFALSEV);
					icop = cop->getMateId((cop->getVertexIndex(c->getVertexId(i OF_MUTEXFALSEV))+1)%3);
					if(icop < 0)
						v[i]->addSing(c->getMateId((i+1)%3 OF_MUTEXFALSEV) OF_MUTEXFALSEV);
					else if (icop == idcell)
						v[i]->getSing(OF_MUTEXFALSE)->setCell(c->getMateId((i+1)%3 OF_MUTEXFALSEV));
				}
			}
		}
		else
		{
			pos = v[i]->inSings(idcell OF_MUTEXFALSEV);
			if(c->getMateId((i+1)%3 OF_MUTEXFALSEV) >= 0)
				v[i]->setSingCell(pos,c->getMateId((i+1)%3 OF_MUTEXFALSEV) OF_MUTEXFALSEV);
			else
				v[i]->delSing(pos OF_MUTEXFALSEV);
		}
	}
		
	for(i=0; i < 3; i++)
	{
		icop = c->getMateId(i OF_MUTEXFALSEV);
		if(icop >= 0)
		{
			cop = this->mesh->getCell(icop);
			cop->setMateId(cop->getMateIndex(idcell),-1);
		}
	}
	
	c->setMateId(0,-1 OF_MUTEXFALSEV);
	c->setMateId(1,-1 OF_MUTEXFALSEV);
	c->setMateId(2,-1 OF_MUTEXFALSEV);
#ifdef OF_THREADS
	if(mutex)
	{
		unlockVertices(idv,v);
		c->unlock();
	}
#endif
}
#ifdef OF_THREADS
template <class _Traits> void ofMesh2D<_Traits>::lockVertices(ids *idv, sVertex** v)
{
	if(idv[0] < idv[1])
	{
		if(idv[0] < idv[2])
		{
			if(idv[1] < idv[2])
			{ v[0]->lock(); v[1]->lock(); v[2]->lock(); }
			else
			{ v[0]->lock(); v[2]->lock(); v[1]->lock(); }
		}
		else
		{ v[2]->lock(); v[0]->lock(); v[1]->lock(); }
	}
	else
	{
		if(idv[0] > idv[2])
		{
			if(idv[1] < idv[2])
			{ v[1]->lock(); v[2]->lock(); v[0]->lock(); }
			else
			{ v[2]->lock(); v[1]->lock(); v[0]->lock(); }
		}
		else
		{ v[1]->lock(); v[0]->lock(); v[2]->lock(); }
	}
	
}
template <class _Traits> void ofMesh2D<_Traits>::unlockVertices(ids *idv, sVertex** v)
{
	if(idv[0] < idv[1])
	{
		if(idv[0] < idv[2])
		{
			if(idv[1] < idv[2])
			{ v[2]->unlock(); v[1]->unlock(); v[0]->unlock(); }
			else
			{ v[1]->unlock(); v[2]->unlock(); v[0]->unlock(); }
		}
		else
		{ v[1]->unlock(); v[0]->unlock(); v[2]->unlock(); }
	}
	else
	{
		if(idv[0] > idv[2])
		{
			if(idv[1] < idv[2])
			{ v[0]->unlock(); v[2]->unlock(); v[1]->unlock(); }
			else
			{ v[0]->unlock(); v[1]->unlock(); v[2]->unlock(); }
		}
		else
		{ v[2]->unlock(); v[0]->unlock(); v[1]->unlock(); }
	}
	
}
#endif
}
#endif
