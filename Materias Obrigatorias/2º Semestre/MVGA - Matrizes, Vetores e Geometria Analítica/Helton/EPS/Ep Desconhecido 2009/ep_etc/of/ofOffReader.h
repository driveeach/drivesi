#ifndef OFOFFREADER_H
#define OFOFFREADER_H
#include <fstream>
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofReader.h"
using namespace std;
namespace of
{
template <class _Traits> class ofOffReader : public ofReader<_Traits>
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	
	typedef ofMesh<_Traits> sMesh;
	
	ofOffReader();
	~ofOffReader();
	
	virtual bool read(sMesh* malha, char* filename);
private:
	bool reader(ifstream &pf, sMesh* malha);
};
template <class _Traits> ofOffReader<_Traits>::ofOffReader()
{
}
template <class _Traits> ofOffReader<_Traits>::~ofOffReader()
{
}
template <class _Traits> bool ofOffReader<_Traits>::read(sMesh* malha, char *filename)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);	
	
	ifstream pf(filename);
	
	if( pf.is_open())
	{
			if(! reader(pf, malha))
				return false;
		
			pf.close();
	}
	else
		return false;
	
	return true;
}
template <class _Traits> bool ofOffReader<_Traits>::reader(ifstream &pf, sMesh* malha)
{
	char auxstr[255];
	space coords[3];
	int i, nv, j, nc, k, idnv;
	ids auxvtx[sCell::getDimension() + 1];
	OF_ASSERT(auxstr);
	
	while( (! pf.eof())&&(strcmp(auxstr,"OFF") != 0) )
		pf >> auxstr;
	
	if(! pf.eof())
	{
		pf >> nv;
		pf >> nc;
		pf >> k;
		
		OF_ASSERT(nv > 0);
	
		for (i=0; i<nv; i++)
		{
			for(j = 0; j < 3; j++)
				pf >> coords[j];
			malha->addVertex(coords);
		}
		OF_ASSERT(nc >= 0);
		
		for (i=0; i<nc; i++)
		{
			pf >> idnv;
		
			OF_ASSERT(idnv == sCell::getDimension() + 1);
		
			for (j=0; j<sCell::getDimension() + 1 ; j++)
			{
				pf >> k;
				OF_ASSERT((k >= 0)&&(k < nv));
				auxvtx[j] = k;
			}
		
			malha->addCell(auxvtx);
		}
	}
	else
		return false;
		
	return true;
}
}
#endif
