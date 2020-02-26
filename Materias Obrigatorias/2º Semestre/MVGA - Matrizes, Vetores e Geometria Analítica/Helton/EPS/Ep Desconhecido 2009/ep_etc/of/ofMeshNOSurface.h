#ifndef OFMESHNOSURFACE_H
#define OFMESHNOSURFACE_H
#include "ofMacros.h"
#include "ofSing.h"
#include "ofMesh.h"
#include "ofMeshOper.h"
namespace of
{
/**	Operation Class for Triangles in 3D space (with not oriented triangles)
 * 
 * 	_Traits must have: ids, sVertex, sCell, sSing, sMesh
 */
template <class _Traits> class ofMeshNOSurface : public ofMeshOper<_Traits>
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
	ofMeshNOSurface(sMesh *_mesh);
	
	/**	Destructor */
	~ofMeshNOSurface();
	
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
	void lockVertices(int num, sVertex* v);
	void unlockVertices(int num, sVertex* v);
#endif
};
template <class _Traits> ofMeshNOSurface<_Traits>::ofMeshNOSurface(sMesh *_mesh)
: ofMeshOper<_Traits>(_mesh)
{
}
template <class _Traits> ofMeshNOSurface<_Traits>::~ofMeshNOSurface()
{
}
template <class _Traits> void ofMeshNOSurface<_Traits>::addCell(ids idcell, ids *idvertices OF_DMUTEXV)
{
	OF_ASSERT((idvertices[0] >= 0)&&(idvertices[1] >= 0)&&(idvertices[2] >= 0));
	OF_ASSERT((idvertices[0] != idvertices[1])&&(idvertices[0] != idvertices[2])&&(idvertices[1] != idvertices[2]));
	sCell* c = this->mesh->getCell(idcell);
	ids idv[3] = { idvertices[0], idvertices[1], idvertices[2]};
	sVertex *v[3] = { this->mesh->getVertex(idv[0]), this->mesh->getVertex(idv[1]), this->mesh->getVertex(idv[2])};
	int ns = 0 // Numero de vertices com singularidade igual a 0
		, temp;
	
	if (v[0]->getNumberOfSings() == 0)
		ns++;
	if (v[1]->getNumberOfSings() == 0)
	{
		temp = idv[0];
		idv[0] = idv[1];
		idv[1] = temp;
		ns++;
	}	
	if (v[2]->getNumberOfSings() == 0)
	{
		temp = idv[0];
		idv[0] = idv[2];
		idv[2] = temp;
		ns++;
	}	
	
	c->setVertexId(0, idv[0]);
	c->setVertexId(1, idv[1]);
	c->setVertexId(2, idv[2]);
	c->setMateId(0,-1);
	c->setMateId(1,-1);
	c->setMateId(2,-1);
	
	v[0] = this->mesh->getVertex(idv[0]);
	v[1] = this->mesh->getVertex(idv[1]);
	v[2] = this->mesh->getVertex(idv[2]);
	
	if(ns > 1)
	{
		// Nenhuma face em comum
		v[0]->addSing(idcell);
		v[1]->addSing(idcell);
		v[2]->addSing(idcell);
	}
	else if(ns == 1)
	{
		// Apenas a face oposta a index1 pode ser comum
		searchFace(idcell,idv[1]);
		v[0]->addSing(idcell);
		
		if (c->getMateId(0) < 0)
		{
			// Nenhuma face em comum foi encontrada
			v[1]->addSing(idcell);
			v[2]->addSing(idcell);
		}
	}
	else
	{ 	
		// Qualquer face pode ser comum
		
		searchFace(idcell,idv[0]);
		searchFace(idcell,idv[1]);
		
		if (c->getMateId(0) < 0)
		{
			// Nenhuma face em comum foi encontrada
			v[1]->addSing(idcell);
			v[2]->addSing(idcell);
		}
		if (c->getMateId(1) < 0)
		{
			// Nenhuma face em comum foi encontrada
			v[0]->addSing(idcell);
			v[2]->addSing(idcell);
		}
		searchSings(idv[0]);
		searchSings(idv[1]);
		searchSings(idv[2]);
	}
}
template <class _Traits> void ofMeshNOSurface<_Traits>::delCell(ids idcell OF_DMUTEXV)
{
}
template <class _Traits> void ofMeshNOSurface<_Traits>::searchFace(int idcell, int idvertex)
{
	OF_ASSERT(this->mesh->getVertex(idvertex)->getNumberOfSings());
	ofVertexStarIteratorSurface<_Traits> ic(this->mesh);
	ofList<ids> lista;
	
	for(ic.initialize(idvertex); ic.notFinish(); ++ic)
	{
		OF_ASSERT(!lista.inList(&ic));
		lista.insert(&ic);
	}
	
	int iq[3], ig;
	ids c;
	while(!lista.empty())
	{
		c = lista.first();
		lista.erase(0);
		
		ig = sameVertices(c, idcell, iq);
		
		OF_ASSERT(!((c != idcell)&&(ig == 3)));
		
		if((c != idcell)&&(ig == 2))
		{
			setMate(idcell, c, iq);
			setMate(c, idcell, iq);
		}
	}
}
template <class _Traits> int ofMeshNOSurface<_Traits>::sameVertices(int idcell1, int idcell2, int iq[])
{
	int i, j, v1, v2, nv = 0;
	
	for(i=0; i<3; i++)
	{
		v1 = this->mesh->getCell(idcell1)->getVertexId(i);
		for(j=0; j<3; j++)
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
template <class _Traits> bool ofMeshNOSurface<_Traits>::setMate(int idcell, int idcellmate, int iq[])
{
	for(int i=0; i<3; i++)
	{
		if((this->mesh->getCell(idcell)->getVertexId(i) != iq[0])&&(this->mesh->getCell(idcell)->getVertexId(i) != iq[1]))
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
template <class _Traits> void ofMeshNOSurface<_Traits>::searchSings(int idvertex)
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
		for(i=0; i<3; i++)
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
}
#endif
