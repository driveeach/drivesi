#ifndef OFPOLYDATAREADER_H
#define OFPOLYDATAREADER_H
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofReader.h"
namespace of
{
template <class _Traits> class ofPolyDataReader : public ofReader<_Traits>
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	
	typedef ofMesh<_Traits> sMesh;
	typedef typename _Traits::sGeometric sGeometric;
	
	ofPolyDataReader();
	~ofPolyDataReader();
	
	void checkPoints(bool value);
	
	virtual bool read(sMesh* malha, char* filename);
private:
	class sObject
	{
		public:
		sVertex *v;
		ids id;
	};
	class sObjectCompare
	{
		public:
		bool greater(sObject v1, sObject v2, int dim)
		{
			OF_ASSERT((dim == 0)||(dim == 1));
			return v1.v->getCoord(dim) > v2.v->getCoord(dim);
		};
	};
	
	bool cpoints;
	ofKdTree<sObject,sObjectCompare> kd;	
	ofVector<ids,ids> *vtx;
	sVertex vtemp;
	sObject vobj, vresult;
	sGeometric geom;
	bool reader(ifstream &pf, sMesh* malha);
	int sameVertices(sCell *cell, ids *vtx);
	
};
template <class _Traits> ofPolyDataReader<_Traits>::ofPolyDataReader()
: kd(sVertex::getDimension())
{
	cpoints = false;
	vtx = NULL;
}
template <class _Traits> ofPolyDataReader<_Traits>::~ofPolyDataReader()
{
}
template <class _Traits> void ofPolyDataReader<_Traits>::checkPoints(bool value)
{
	cpoints = value;
}
template <class _Traits> bool ofPolyDataReader<_Traits>::read(sMesh* malha, char *filename)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);
	
	geom.setMesh(malha);
	if(cpoints)
		vtx  = new ofVector<ids,ids>(malha->getBlockSize());
	ifstream pf(filename);
	
	bool ret = true;
	
	if( pf.is_open())
	{
			if(! reader(pf, malha))
				ret = false;
		
			pf.close();
	}
	else
		ret = false;
	
	if (vtx)
		delete vtx;
		
	return ret;
}
template <class _Traits> bool ofPolyDataReader<_Traits>::reader(ifstream &pf, sMesh* malha)
{
	char auxstr[255];
	space coords[3];
	int i, nv, j, nc, k, idnv;
	int auxvtx[sCell::getDimension() + 1];
	bool test;
	OF_ASSERT(auxstr);
	
	
	while( (! pf.eof())&&(strcmp(auxstr,"POINTS") != 0) )
		pf >> auxstr;
	
	if(! pf.eof())
	{
		pf >> nv;
		pf >> auxstr;
		
		OF_ASSERT(nv > 0);
	
		if(cpoints)
			
		
		for (i=0; i<nv; i++)
		{
			for(j = 0; j < 3; j++)
				pf >> coords[j];
			if(cpoints)
			{	
				vobj.v = &vtemp;
				vobj.v->setCoords(coords);
				test = false;
				if(kd.size() > 0)
				{
					vresult = kd.nearest(vobj);
					test = geom.sameVertices(vresult.v, vobj.v);
				}
				if(test)
					(*vtx)[i] = vresult.id;
				else
				{
					(*vtx)[i] = malha->addVertex(coords);
					vobj.v = malha->getVertex((*vtx)[i]);
					vobj.id = (*vtx)[i];
					kd.insert(vobj);
				}
			}
			else
				malha->addVertex(coords);
		}
	}
	else 
		return false;
	while( (! pf.eof())&&(strcmp(auxstr,"POLYGONS") != 0) )
		pf >> auxstr;
	
	if( !pf.eof() )
	{
		pf >> nc;
		pf >> auxstr;
			
		OF_ASSERT(nc >= 0);
		
		ofVertexStarIteratorSurface<_Traits> itvs(malha);
		bool repetida;
		
		for (i=0; i<nc; i++)
		{
			pf >> idnv;
		
			OF_ASSERT(idnv == sCell::getDimension() + 1);
		
			for (j=0; j<sCell::getDimension() + 1 ; j++)
			{
				pf >> k;
				OF_ASSERT((k >= 0)&&(k < nv));
				if(cpoints)
					auxvtx[j] = (*vtx)[k];
				else
					auxvtx[j] = k;
			}
			
			repetida = false;
			if(malha->getVertex(auxvtx[0])->getNumberOfSings() > 0)
			for(itvs.initialize(auxvtx[0]); itvs.notFinish(); ++itvs)
				if(sameVertices(*itvs, auxvtx) == 3)
				{
					repetida = true;
					break;
				}	
		
			if(!repetida)
				malha->addCell(auxvtx);
		}
	}
	else
		return false;
		
	return true;
}
template <class _Traits> int ofPolyDataReader<_Traits>::sameVertices(sCell *cell, ids *vtx)
{
	int i, j, v1, v2, nv = 0;
	
	for(i=0; i<3; i++)
	{
		v1 = cell->getVertexId(i);
		for(j=0; j<3; j++)
		{
			v2 = vtx[j];
			if(v1 == v2)
			{
				nv++;
				break;
			}
		}
	}
	
	return nv;
}
}
#endif
