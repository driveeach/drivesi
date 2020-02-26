#ifndef OFVTKREADER_H
#define OFVTKREADER_H
#include <fstream>
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofReader.h"
using namespace std;
namespace of
{
template <class _Traits> class ofVtkReader : public ofReader<_Traits>
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	
	typedef ofMesh<_Traits> sMesh;
	
	ofVtkReader();
	~ofVtkReader();
	
	virtual bool read(sMesh* malha, char* filename);
private:
	bool reader(ifstream &pf, sMesh* malha);
};
template <class _Traits> ofVtkReader<_Traits>::ofVtkReader()
{
}
template <class _Traits> ofVtkReader<_Traits>::~ofVtkReader()
{
}
template <class _Traits> bool ofVtkReader<_Traits>::read(sMesh* malha, char *filename)
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
template <class _Traits> bool ofVtkReader<_Traits>::reader(ifstream &pf, sMesh* malha)
{
	char auxstr[255];
	space coords[3];
	int i, nv, j, nc, k, idnv;
	int *auxvtx;
	OF_ASSERT(auxstr);
	
	while( (! pf.eof())&&(strcmp(auxstr,"POINTS") != 0) )
		pf >> auxstr;
	
	if(! pf.eof())
	{
		pf >> nv;
		pf >> auxstr;
		
		OF_ASSERT(nv > 0);
	
		for (i=0; i<nv; i++)
		{
			for(j = 0; j < 3; j++)
				pf >> coords[j];
			malha->addVertex(coords);
		}
	}
	else 
		return false;
	while( (! pf.eof())&&(strcmp(auxstr,"CELLS") != 0) )
		pf >> auxstr;
	
	if( !pf.eof() )
	{
		pf >> nc;
		pf >> auxstr;
			
		OF_ASSERT(nc >= 0);
		
		for (i=0; i<nc; i++)
		{
			pf >> idnv;
			
		
			OF_ASSERT(idnv == sCell::getDimension() + 1);
			auxvtx=new int[idnv];
		
			for (j=0; j<sCell::getDimension() + 1 ; j++)
			{
				pf >> k;
				OF_ASSERT((k >= 0)&&(k < nv));
				auxvtx[j] = k;
			}
		
			malha->addCell(auxvtx);
			delete auxvtx;
		}
	}
	else
		return false;
		
	return true;
}
}
#endif
