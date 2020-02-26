#ifndef OFOFWRITER_H
#define OFOFWRITER_H
#include <fstream>
#include <iomanip>
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofBinaryIO.h"
#include "ofWriter.h"
#include "ofVerticesIterator.h"
#include "ofCellsIterator.h"
using namespace std;
namespace of
{
template <class _Traits> class ofOfWriter : public ofWriter<_Traits>
{
public:
	typedef typename _Traits::space 	space;
	typedef typename _Traits::ids 		ids;
	typedef typename _Traits::sVertex 	sVertex;
	typedef typename _Traits::sCell 	sCell;
	
	typedef ofMesh<_Traits> 			sMesh;
	typedef ofBinaryIO<_Traits> 		sBinaryIO;
	
	ofOfWriter();
	~ofOfWriter();
	
	virtual bool write(sMesh* malha, char* filename);
private:
	sBinaryIO io;
	
	void writeVersion(ofstream &pf);
	void writeHeader(ofstream &pf, ids nv, ids nc);
	
	bool writer(ofstream &pf, sMesh* malha);
};
template <class _Traits> ofOfWriter<_Traits>::ofOfWriter()
{
}
template <class _Traits> ofOfWriter<_Traits>::~ofOfWriter()
{
}
template <class _Traits> bool ofOfWriter<_Traits>::write(sMesh* malha, char *filename)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);	
	
	ofstream pf(filename, ios_base::out | ios_base::binary | ios_base::trunc);
	
	if( pf.is_open())
	{
			if(! writer(pf, malha))
				return false;
		
			pf.close();
	}
	else
		return false;
	
	return true;
}
template <class _Traits> void ofOfWriter<_Traits>::writeVersion(ofstream &pf)
{
	pf.write("OF-BINARY-V1.001", 16*sizeof(char));
}
template <class _Traits> void ofOfWriter<_Traits>::writeHeader(ofstream &pf, ids nv, ids nc)
{
	io.idsStore(pf, sizeof(space) );
	io.idsStore(pf, sizeof(ids) );
	io.idsStore(pf, sVertex::getDimension() );
	io.idsStore(pf, sCell::getDimension() );
	io.idsStore(pf, nv);
	io.idsStore(pf, nc);
}
template <class _Traits> bool ofOfWriter<_Traits>::writer(ofstream &pf, sMesh* malha)
{
	int i, nv, j, nc;
	ofVector<ids,ids> nvids(malha->getBlockSize());
	ofVector<ids,ids> ncids(malha->getBlockSize());
	writeVersion(pf);
	
	nv = malha->getNumberOfVertices();
	nc = malha->getNumberOfCells();
	
	writeHeader(pf, nv, nc);
		
	ofVerticesIterator<_Traits> itv(malha);
	i = 0;
	for(itv.initialize(); itv.notFinish(); ++itv)
	{
		for(j = 0; j < sVertex::getDimension(); j++)
			io.spaceStore(pf, itv->getCoord(j));
		
		nvids[&itv] = i++;
	}
	ofCellsIterator<_Traits> itc(malha);
	i = 0;
	for(itc.initialize(); itc.notFinish(); ++itc)
		nvids[&itv] = i++;	
	
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		for(j = 0; j <= sCell::getDimension(); j++)
			io.idsStore(pf, nvids[itc->getVertexId(j)]);
		for(j = 0; j <= sCell::getDimension(); j++)
			if(itc->getMateId(j) >=0 )
				io.idsStore(pf, ncids[itc->getMateId(j)]);
			else
				io.idsStore(pf, -1);
	}
	
	for(itv.initialize(); itv.notFinish(); ++itv)
	{
		io.idsStore(pf,itv->getNumberOfSings());
		
		for(j=0; j < itv->getNumberOfSings(); j++)
			io.idsStore(pf,itv->getSingCell(j));
	}	
	
	return true;
}
}
#endif
