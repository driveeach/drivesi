#ifndef OFPOLYWRITER_H
#define OFPOLYWRITER_H
#include "ofMacros.h"
#include "ofWriter.h"
namespace of
{
//! Salva malhas em arquivo .poly
template <class _Traits>  class ofPolyWriter
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh;
	
	ofPolyWriter();
	~ofPolyWriter();
	
	bool write(sMesh* malha, char* filename,space norma=1.0,bool eok=false);
private:
	bool writer(ofstream &pf, sMesh *malha, space norma=1.0);
};
template <class _Traits>  ofPolyWriter<_Traits>::ofPolyWriter()
{
}
template <class _Traits>  ofPolyWriter<_Traits>::~ofPolyWriter()
{
}
template <class _Traits> bool ofPolyWriter<_Traits>::write(sMesh* malha, char *filename, space norma,bool eok)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);	
	
	ofstream pf(filename);
	
	if( pf.is_open())
	{
			if(! writer(pf, malha,norma))
				return false;
		
			pf.close();
	}
	else
		return false;
	
	return true;
}
template <class _Traits> bool ofPolyWriter<_Traits>::writer(ofstream &pf, sMesh* malha, space norma)
{
	int i = 1,j;
	
	pf << "# part 1 node list" << endl;
	
	
	pf << malha->getNumberOfVertices()+8 << " 3 0 0 " << endl;
	
	
	ofVerticesIterator<_Traits> itv(malha);
	
	for(itv.initialize(); itv.notFinish(); ++itv)
	{
	
			pf << i << " " << setprecision(15) << itv->getCoord(0)*norma << " " << setprecision(15) << itv->getCoord(1)*norma << " " << setprecision(15) << itv->getCoord(2)*norma << endl;
			i++;
		
	}
	
	/*pf << i << " "  << malha->getMinX()*norma-3.0 << " " << malha->getMinY()*norma-3.0 << " " << malha->getMinZ()*norma-3.0 << endl;
	i++;
	pf << i << " "  << malha->getMinX()*norma-3.0 << " " << malha->getMaxY()*norma+3.0 << " " << malha->getMinZ()*norma-3.0 << endl;
	i++;
	pf << i << " "  << malha->getMaxX()*norma+3.0 << " " << malha->getMaxY()*norma+3.0 << " " << malha->getMinZ()*norma-3.0 << endl;
	i++;
	pf << i << " "  << malha->getMaxX()*norma+3.0 << " " << malha->getMinY()*norma-3.0 << " " << malha->getMinZ()*norma-3.0 << endl;
	i++;
	pf << i << " "  << malha->getMinX()*norma-3.0 << " " << malha->getMinY()*norma-3.0 << " " << malha->getMaxZ()*norma+3.0 << endl;
	i++;
	pf << i << " "  << malha->getMinX()*norma-3.0 << " " << malha->getMaxY()*norma+3.0 << " " << malha->getMaxZ()*norma+3.0 << endl;
	i++;
	pf << i << " "  << malha->getMaxX()*norma+3.0 << " " << malha->getMaxY()*norma+3.0 << " " << malha->getMaxZ()*norma+3.0 << endl;
	i++;
	pf << i << " "  << malha->getMaxX()*norma+3.0 << " " << malha->getMinY()*norma-3.0 << " " << malha->getMaxZ()*norma+3.0 << endl;*/
	
	pf << i << " "  << -10.0 << " " << -10.0 << " " << -10.0 << endl;
	i++;
	pf << i << " "  << -10.0 << " " << +10.0 << " " << -10.0 << endl;
	i++;
	pf << i << " "  << 10.0 << " " << 10.0 << " " << -10.0 << endl;
	i++;
	pf << i << " "  << 10.0 << " " << -10.0 << " " << -10.0 << endl;
	i++;
	pf << i << " "  << -10.0 << " " << -10.0 << " " << 10.0 << endl;
	i++;
	pf << i << " "  << -10.0 << " " << 10.0 << " " << 10.0 << endl;
	i++;
	pf << i << " "  << +10.0 << " " << 10.0 << " " << 10.0 << endl;
	i++;
	pf << i << " "  << 10.0 << " " << -10.0 << " " << 10.0 << endl;
	
	int nv = malha->getNumberOfVertices();
	pf << "# part 2 face list" << endl;
	pf << malha->getNumberOfCells()+12 << " 1" << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+1 <<" "<< nv+2 <<" "<< nv+3 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+1 <<" "<< nv+3 <<" "<< nv+4 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+1 <<" "<< nv+6 <<" "<< nv+2 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+1 <<" "<< nv+5 <<" "<< nv+6 << endl;
    pf <<"1 0 0" << endl;
	pf << "3 " << nv+1 <<" "<< nv+4 <<" "<< nv+8 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+1 <<" "<< nv+5 <<" "<< nv+8 << endl;
    pf <<"1 0 0" << endl;
	pf << "3 " << nv+4 <<" "<< nv+3 <<" "<< nv+8 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+3 <<" "<< nv+7 <<" "<< nv+8 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+3 <<" "<< nv+2 <<" "<< nv+6 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+3 <<" "<< nv+6 <<" "<< nv+7 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+5 <<" "<< nv+7 <<" "<< nv+6 << endl;
	pf <<"1 0 0" << endl;
	pf << "3 " << nv+5 <<" "<< nv+8 <<" "<< nv+7 << endl;
	
	
	
	
	ofCellsIterator<_Traits> itc(malha);
	
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		pf <<"1 0 1"  << endl;
		pf << sCell::getDimension() + 1; 
	
		for(j = 0; j <= sCell::getDimension(); j++)
			pf << " " << itc->getVertexId(j)+1;
		pf << endl; 
	}
	
	
	pf << "# part 3 hole list" << endl;
	pf << "0" << endl;
	pf << "# part 4 region list" << endl;
	pf << "1" << endl;
	pf << "0.0 0.0 0.0 1 1" << endl;
	
	
	return true;
}
}
#endif
