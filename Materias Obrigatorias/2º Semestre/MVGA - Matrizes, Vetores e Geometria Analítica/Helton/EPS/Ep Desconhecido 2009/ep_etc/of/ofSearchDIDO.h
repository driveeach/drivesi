#ifndef	OFSEARCHDIDO_H
#define	OFSEARCHDIDO_H
#include "ofMacros.h"
#include "ofBase.h"
namespace of
{
template <class _Traits> class ofSearchDIDO 
{
public:	
    typedef typename _Traits::sVertex 	sVertex;
    typedef typename _Traits::sCell 	sCell;
	typedef typename _Traits::ids 		ids;
	typedef typename _Traits::space		space;
	typedef typename _Traits::sGeometric sGeometric;	
	typedef typename _Traits::sMesh		sMesh;
	
	ofSearchDIDO(sMesh *_mesh);
    ~ofSearchDIDO();
	int dido(sVertex *_v, space *coords, ids &idcelula, int &lado);
    
    void setMesh(sMesh *_mesh);
    
private:
    
	sMesh *mesh;
	sGeometric geom;
};
template <class _Traits> ofSearchDIDO<_Traits>::ofSearchDIDO(sMesh *_mesh) 
{
	
	mesh = _mesh;
	geom.setMesh(mesh);
}
template <class _Traits> ofSearchDIDO<_Traits>::~ofSearchDIDO() 
{
}
template <class _Traits> void ofSearchDIDO<_Traits>::setMesh(sMesh *_mesh)
{
	OF_ASSERT(_mesh);
	mesh = _mesh;
}
template<class _Traits> int ofSearchDIDO<_Traits>::dido(sVertex *_v, space *coords, ids &idcelula, int &lado)
{
	//Retorna 0 caso esteja dentro
	//Retorna 1 caso esteja fora
	//Retorna 2 caso esteja sobre a aresta lado
	//Retorna 3 caso seja um vertice repetido
	OF_ASSERT(mesh);
	
	sCell* auxc = NULL;
	sVertex *v[3];
	ids icell, iauxc = 0;
	int sobre, left, retorno = -1;
		
	OF_ASSERT(_v->getNumberOfSings() == 1); // nao permite vertice singular ou isolado
	
	iauxc = _v->getSingCell(0);
	auxc = mesh->getCell(iauxc OF_MUTEXV);
	
	lado = 0;
	
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
	return retorno;	
}
}
#endif
