#ifndef OFTETGENREADER_H
#define OFTETGENREADER_H
#include "ofMacros.h"
#include "ofVector.h"
namespace of
{
//! Leitor de formato TetGen
template <class _Traits> class ofTetGenReader 
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh;
	
	ofTetGenReader();
	~ofTetGenReader();
	
	/**  Funcao leitora do arquivo
	* Dados de entrada.
	* malha
	* nome do arquivo
	*/
	bool read(sMesh* malha, char* filename,char *filename2,ofVector<ids,ids> *ref);
	
private:
	bool reader(ifstream &pf1,ifstream &pf2, sMesh* malha,ofVector<ids,ids> *ref);
};
template <class _Traits> ofTetGenReader<_Traits>::ofTetGenReader()
{
}
template <class _Traits> ofTetGenReader<_Traits>::~ofTetGenReader()
{
}
template <class _Traits> bool ofTetGenReader<_Traits>::read(sMesh* malha, char *filename1, char* filename2, ofVector<ids,ids> *ref)
{
	OF_ASSERT(filename1);
	OF_ASSERT(filename2);
	OF_ASSERT(malha);	
	
	ifstream pf1(filename1);
	ifstream pf2(filename2);
	
	if( (pf1.is_open())&&(pf2.is_open()))
	{
			if(! reader(pf1,pf2, malha,ref))
				return false;
		
			pf1.close();
			pf2.close();
	}
	else
		return false;
	
	return true;
}
template <class _Traits> bool ofTetGenReader<_Traits>::reader(ifstream &pf1, ifstream &pf2, sMesh* malha,ofVector<ids,ids> *ref)
{
	char auxstr[255];
	space **coords;
	bool *inserted;
	int *index;
	int i, nv, j,  k, idnv,l;
	int auxvtx[3];
	OF_ASSERT(auxstr);
	
	//while( (! pf1.eof())&&(strcmp(auxstr,"OFF") != 0) )
		//pf >> auxstr;
	int dim,nc;
	
	if(! pf1.eof())
	{
		pf1 >> nv;
		pf1 >> dim;
		pf1 >> k;
		pf1 >> l;
		
		OF_ASSERT(nv > 0);
		coords=new space*[nv];
		inserted = new bool[nv];
		index = new int[nv];
	
		int ni;
		
		for (i=0; i<nv; i++)
		{
			coords[i]= new space[3];
			inserted[i]=false;
			(*ref)[i]=-1;
			pf1 >> ni;
			for(j = 0; j < 3; j++)
				pf1 >> coords[i][j];
			
		}
		ref->setSize(nv);
	}
	else
		return false;
	int reg;
	if (! pf2.eof())
	{
		int ninserted=0;
		pf2 >> nc;
		pf2 >> l;
		OF_ASSERT(nc >= 0);
		
		
		for (i=0; i<nc; i++)
		{
			pf2 >> idnv;
		
			
		
			for (j=0; j<sCell::getDimension() + 1 ; j++)
			{
				pf2 >> k;
				OF_ASSERT((k-1 >= 0)&&(k-1 < nv));
				auxvtx[j] = k-1;
			}
			pf2 >> reg;
			if (reg==1)
			{
				for (j=0; j<sCell::getDimension() + 1 ; j++)
				{
				
					if(inserted[auxvtx[j]]==false)
					{
						inserted[auxvtx[j]]=true;
						malha->addVertex(coords[auxvtx[j]]);
						index[auxvtx[j]]=ninserted;
						(*ref)[auxvtx[j]]=ninserted;
						auxvtx[j]=ninserted;
						
						ninserted++;
					}
					else
					{
						auxvtx[j]=index[auxvtx[j]];
						
					}
				}
				malha->addCell(auxvtx);
			}
		
			
		}
	}
	else
		return false;
	for(i=0;i<nv;i++)
	{
		delete coords[i];
	}
	delete coords;
	delete inserted;
	delete index;
	return true;
}
}
#endif
