#ifndef OFWRLREADER_H
#define OFWRLREADER_H
#include <fstream>
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofReader.h"
using namespace std;
namespace of
{
template <class _Traits> class ofWrlReader : public ofReader<_Traits>
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	
	typedef ofMesh<_Traits> sMesh;
	
	ofWrlReader();
	~ofWrlReader();
	
	virtual bool read(sMesh* malha, char* filename);
private:
	bool reader(ifstream &pf, sMesh* malha);
};
template <class _Traits> ofWrlReader<_Traits>::ofWrlReader()
{
}
template <class _Traits> ofWrlReader<_Traits>::~ofWrlReader()
{
}
template <class _Traits> bool ofWrlReader<_Traits>::read(sMesh* malha, char *filename)
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
template <class _Traits> bool ofWrlReader<_Traits>::reader(ifstream &pf, sMesh* malha)
{
	char auxstr[255];
	space coords[3];
	int j, k;
	int auxvtx[3];
	char c;
	OF_ASSERT(auxstr);
	
	while( (! pf.eof())&&(strcmp(auxstr,"point") != 0) )
		pf >> auxstr;
	
	if(! pf.eof())
	{
		pf >> c;
		
		if( c == '[')
		{
		
			while ( c != ']')
			{
				for(j = 0; j < 3; j++)
					pf >> coords[j];
	
				malha->addVertex(coords);
				
				pf >> c;
			}
		}
		else
			return false;
		
		while( (! pf.eof())&&(strcmp(auxstr,"coordIndex") != 0) )
			pf >> auxstr;
		pf >> c;
		if( c == '[' )
		{
			while ( c != ']')
			{
				for(j = 0; j < 3; j++)
					pf >> auxvtx[j];
			
				malha->addCell(auxvtx);
				
				pf >> k;
				pf >> c;				
			}
		}
		else
			return false;
	}
	else 
		return false;
	
	return true;
}
}
#endif
