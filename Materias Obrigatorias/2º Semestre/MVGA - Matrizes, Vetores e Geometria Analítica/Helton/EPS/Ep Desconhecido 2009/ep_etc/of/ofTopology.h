#ifndef OFTOPOLOGY_H
#define OFTOPOLOGY_H
#include "ofMacros.h"
#include "ofMesh.h"
namespace of
{
template <class _Traits> class ofTopology
{
public:
	
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef ofMesh<_Traits> sMesh;
	
	ofTopology(sMesh *_mesh = NULL);
	~ofTopology();
	
	void setMesh(sMesh *_mesh);
	
	void flip2D(ids c1, ids c2 OF_DMUTEXVD);
private:
	sMesh *mesh;
};
template <class _Traits> ofTopology<_Traits>::ofTopology(sMesh *_mesh)
{
	mesh = _mesh;
}
template <class _Traits> ofTopology<_Traits>::~ofTopology()
{
}
template <class _Traits> void ofTopology<_Traits>::setMesh(sMesh *_mesh)
{
	mesh = _mesh;
}
template <class _Traits> void ofTopology<_Traits>::flip2D(ids c1, ids c2)
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
}
#endif 
