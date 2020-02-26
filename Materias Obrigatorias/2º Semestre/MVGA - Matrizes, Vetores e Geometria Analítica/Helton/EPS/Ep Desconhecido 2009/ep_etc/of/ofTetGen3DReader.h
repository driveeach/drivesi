#ifndef OFTETGEN3DREADER_H
#define OFTETGEN3DREADER_H
#include "ofMacros.h"
#include "ofVector.h"
#include "classes.h"
#include"BinTree.h"
namespace of
{
template <class _Traits> class ofTetGen3DReader 
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh;
	
	ofTetGen3DReader ();
	~ofTetGen3DReader ();
	
	/**  Funcao leitora do arquivo
	* Dados de entrada.
	* malha
	* nome do arquivo
	*/
	bool read(sMesh* malha, char* filename,char *filename2);
	
private:
	bool reader(ifstream &pf1,ifstream &pf2, sMesh* malha);
	bool reader1(ifstream &pf1,ifstream &pf2, sMesh* malha);
};
template <class _Traits> ofTetGen3DReader <_Traits>::ofTetGen3DReader ()
{
}
template <class _Traits> ofTetGen3DReader <_Traits>::~ofTetGen3DReader ()
{
}
template <class _Traits> bool ofTetGen3DReader <_Traits>::read(sMesh* malha, char *filename1, char* filename2)
{
	OF_ASSERT(filename1);
	OF_ASSERT(filename2);
	OF_ASSERT(malha);	
	
	ifstream pf1(filename1);
	ifstream pf2(filename2);
	
	if( (pf1.is_open())&&(pf2.is_open()))
	{
			if(! reader1(pf1,pf2, malha))
				return false;
		
			pf1.close();
			pf2.close();
	}
	else
		return false;
	
	return true;
}
template <class _Traits> bool ofTetGen3DReader <_Traits>::reader(ifstream &pf1, ifstream &pf2, sMesh* malha)
{
	char auxstr[255];
	space **coords;
	int *index;	
	int i, nv, j,  k, idnv,l;
	int auxvtx[4];
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
		
		index = new int[nv];
	
		int ni;
		
		for (i=0; i<nv; i++)
		{
			coords[i]= new space[3];
			
			
			pf1 >> ni;
			for(j = 0; j < 3; j++)
				pf1 >> coords[i][j];
			malha->addVertex(coords[i]);
			
		}
		
		
	}
	else
		return false;
	int reg;
	if (! pf2.eof())
	{
		
		pf2 >> nc;
		pf2 >> l;
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
			pf2 >> l;           //! digit of region
			
			
				malha->addCell(auxvtx);
			}
		
			
		}
	
	else
		return false;
	for(i=0;i<nv;i++)
	{
		delete coords[i];
	}
	delete coords;
	
	
	return true;
}
template <class _Traits> bool ofTetGen3DReader <_Traits>::reader1(ifstream &pf1, ifstream &pf2, sMesh* malha)
{
	char auxstr[255];
	space **coords;
	int *index;	
	int i, nv, j,  k, idnv,l;
	int auxvtx[4];
	OF_ASSERT(auxstr);
	CBinTree<Cvertice*>   tv;
	
	Cvertice              *v1;
	Cvertice              *vaux;
	
	
	int dim,nc;
	
	if(! pf1.eof())
	{
		pf1 >> nv;
		pf1 >> dim;
		pf1 >> k;
		pf1 >> l;
		
		OF_ASSERT(nv > 0);
		coords=new space*[nv];
		
		index = new int[nv];
	
		int ni;
		
		for (i=0; i<nv; i++)
		{
			coords[i]= new space[3];
			
			
			pf1 >> ni;
			for(j = 0; j < 3; j++)
				pf1 >> coords[i][j];
			//malha->addVertex(coords[i]);
		}
		for (i=0; i<nv; i++)
		{
			v1 = new Cvertice;
			v1->x=coords[i][0];
			v1->y=coords[i][1];
			v1->z=coords[i][2];
			v1->id=-1;
			vaux =tv.InsertB(v1); 
			if (vaux->id!=-1){
				printf("repated vertex\n");
			}
			else{
			v1->id=i;
			printf("%d\t",i);
			malha->addVertex(coords[i]);
				
			}
		}
		
		tv.Destroy();
	}
	else
		return false;
	int reg;
	if (! pf2.eof())
	{
		
		pf2 >> nc;
		pf2 >> l;
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
			pf2 >> l;           //! digit of region
				printf("%d\n",i);
			
				malha->addCell(auxvtx);
			}
		
			
		}
	
	else
		return false;
	for(i=0;i<nv;i++)
	{
		delete coords[i];
	}
	delete coords;
	
	
	return true;
}
}
#endif
