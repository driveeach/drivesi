#ifndef OFVTKWRITER_H
#define OFVTKWRITER_H
#include "ofMacros.h"
#include "ofWriter.h"
namespace of
{
//! Salva malhas em arquivo do tipo VTK sem dados
template <class _Traits>  class ofVtkWriter 
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh;
	
	ofVtkWriter();
	virtual ~ofVtkWriter();
	
	virtual bool write(sMesh* malha, char* filename,space norma=1.0,int insertedVerices=0);
	bool writePoints(sMesh *malha, char *filename, space norma=1.0);
private:
	bool writer(ofstream &pf, sMesh *malha,space norma=1.0,int insertedVerices=0);
};
template <class _Traits>  ofVtkWriter<_Traits>::ofVtkWriter()
{
}
template <class _Traits>  ofVtkWriter<_Traits>::~ofVtkWriter()
{
}
template <class _Traits> bool ofVtkWriter<_Traits>::write(sMesh* malha, char *filename,space norma, int insertedVerices)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);	
	
	ofstream pf(filename);
	
	if( pf.is_open())
	{
			if(! writer(pf, malha,norma,insertedVerices))
				return false;
		
			pf.close();
	}
	else
		return false;
	
	return true;
}
template <class _Traits> bool ofVtkWriter<_Traits>::writer(ofstream &pf, sMesh* malha,space norma,int insertedVertices)
{
	int i = 0,j;
	
	pf << "# vtk DataFile Version 1.0" << endl;
	pf << "Mesh from of" << endl;
	pf << "ASCII" << endl;
	pf << "DATASET UNSTRUCTURED_GRID" << endl;
	pf << "POINTS " << malha->getNumberOfVertices() << " double" << endl;
	
	
	ofVerticesIterator<_Traits> itv(malha);
	
	for(itv.initialize(); itv.notFinish(); ++itv)
	{
		//if (sVertex::getDimension() == 2)
			pf << setprecision(15) << itv->getCoord(0)*norma << " " << setprecision(15) << itv->getCoord(1)*norma << " " << setprecision(15) << itv->getCoord(2)*norma << endl;
		//else if (sVertex::getDimension() == 3)
			//pf << setprecision(15) << itv->getCoord(0) << " " << setprecision(15) << itv->getCoord(1) << " " << setprecision(15) << itv->getCoord(2) << endl;
	}
	
	pf << endl << "CELLS " << malha->getNumberOfCells() << " " << (sCell::getDimension() + 2)*malha->getNumberOfCells() << endl;
	ofCellsIterator<_Traits> itc(malha);
    int dis;
	if (insertedVertices>4)
		dis=0;
	else
		dis=4-insertedVertices;
	
	if(insertedVertices==-1)
		dis=0;
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		pf << sCell::getDimension() + 1; 
	
		for(j = 0; j <= sCell::getDimension(); j++)
		{
			
			
				pf << " " << itc->getVertexId(j)-dis;
		}
		pf << endl; 
	}
	
	pf << endl << "CELL_TYPES " << malha->getNumberOfCells() << endl;
	
	for (i=0; i<malha->getNumberOfCells(); i++)
		pf << "5 ";
	
	return true;
}

template <class _Traits> bool ofVtkWriter<_Traits>::writePoints(sMesh* malha, char *filename, space norma)
{
	ofstream pf(filename);
	pf << malha->getNumberOfVertices() << " 2 0" << endl;
	ofVerticesIterator<_Traits> itv(malha);
	if( pf.is_open())
	{
		for(itv.initialize(); itv.notFinish(); ++itv)
		{
			pf << setprecision(15) << itv->getCoord(0)*norma << " " << setprecision(15) << itv->getCoord(1)*norma << endl;
		}
		pf.close();
		return true;
		
	}
	else
		return false;


}
}
#endif
