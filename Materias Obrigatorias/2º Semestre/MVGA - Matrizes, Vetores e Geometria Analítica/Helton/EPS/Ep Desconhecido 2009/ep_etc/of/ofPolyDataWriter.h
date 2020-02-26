#ifndef OFPOLYDATAWRITER_H
#define OFPOLYDATAWRITER_H
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofWriter.h"
namespace of
{
//! Salva malhas em arquivo do tipo VTK sem dados
template <class _Traits>  class ofPolyDataWriter : public ofWriter<_Traits>
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	
	typedef ofMesh<_Traits> sMesh;
	
	ofPolyDataWriter();
	~ofPolyDataWriter();
	
	virtual bool write(sMesh* malha, char* filename);
private:
	bool writer(ofstream &pf, sMesh *malha);
};
template <class _Traits>  ofPolyDataWriter<_Traits>::ofPolyDataWriter()
{
}
template <class _Traits>  ofPolyDataWriter<_Traits>::~ofPolyDataWriter()
{
}
template <class _Traits> bool ofPolyDataWriter<_Traits>::write(sMesh* malha, char *filename)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);	
	
	ofstream pf(filename);
	
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
template <class _Traits> bool ofPolyDataWriter<_Traits>::writer(ofstream &pf, sMesh* malha)
{
	int j;
	
	pf << "# vtk DataFile Version 1.0" << endl;
	pf << "Mesh from of" << endl;
	pf << "ASCII" << endl;
	pf << "DATASET UNSTRUCTURED_GRID" << endl;
	pf << "POINTS " << malha->getNumberOfVertices() << " float" << endl;
	
	
	ofVerticesIterator<_Traits> itv(malha);
	
	for(itv.initialize(); itv.notFinish(); ++itv)
	{
		if (sVertex::getDimension() == 2)
			pf << setprecision(15) << itv->getCoord(0) << " " << setprecision(15) << itv->getCoord(1) << " 0.0" << endl;
		else if (sVertex::getDimension() == 3)
			pf << setprecision(15) << itv->getCoord(0) << " " << setprecision(15) << itv->getCoord(1) << " " << setprecision(15) << itv->getCoord(2) << endl;
	}
	
	pf << endl << "POLYGONS " << malha->getNumberOfCells() << " " << (sCell::getDimension() + 2)*malha->getNumberOfCells() << endl;
	ofCellsIterator<_Traits> itc(malha);
	
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		pf << sCell::getDimension() + 1; 
	
		for(j = 0; j <= sCell::getDimension(); j++)
			pf << " " << itc->getVertexId(j);
		pf << endl; 
	}
	
//	pf << endl << "CELL_TYPES " << malha->getNumberOfCells() << endl;
//	
//	for (i=0; i<malha->getNumberOfCells(); i++)
//		pf << "5 ";
	
	return true;
}
}
#endif
