#ifndef OFMYOFFREADER_H
#define OFMYOFFREADER_H
/**
* Esta classe retorna uma lista de ponteiros para vertices
* e uma lista de arestas que representa a conexao entre os
* vertices
*/
#include "ofMacros.h"
#include "ofList.h"
namespace of
{
#define SPOLYGON typename ofMyOffReader<_Traits>::sPolygon
#define SVERTEX10 typename ofMyOffReader<_Traits>::sVertex
#define IDS11 typename ofMyOffReader<_Traits>::ids
//! Classe que le as curvas que representam as fraturas: Formato .off
template <class _Traits> class ofMyOffReader 
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sPolygon sPolygon;
	ofMyOffReader();
	~ofMyOffReader();
	
	/**
	* funcao que le o arquivo de curvas
	* dados de entrada: nome do arquivo
	*/
	bool read(char* filename);
	ofList<sPolygon*> *getBounds();
	ofList<sVertex*> *getLv(){return &lv;};
	int getNv();
	
	ofList<ofList<ids>*> *getLids(){return &lids;};
	space getNorma(){return no;};
private:
	void freeBounds();
	ofList<sVertex*> lv;
	int nVertices;
	ofList<ofList<ids>*> lids;
	space no;
};
template <class _Traits> ofMyOffReader<_Traits>::ofMyOffReader()
{
}
template <class _Traits> ofMyOffReader<_Traits>::~ofMyOffReader()
{
	//freeBounds();
	//lv.clear();
}
template <class _Traits> void ofMyOffReader<_Traits>::freeBounds()
{
	int i;
	sVertex *v;
	for(i=0;i<lv.size();i++)
	{
		v=lv.pos(i);
		if (v)
			delete v;
	}
	//lv.clear();
	
}
template <class _Traits> bool ofMyOffReader<_Traits>::read(char *filename)
{
	OF_ASSERT(filename);
	FILE *pf;
	char auxstr[255];
	OF_ASSERT(auxstr);
	if (!(pf = fopen(filename,"rt")))
	{
		printf("Error reading file %s\n",filename);
		return false;
	}
	//freeBounds();		
	double norma=0.0;
	sVertex *v;
	space  x,y,z;
	int i,j,nv,ne,nve,vi;
	ofList<ids> *b_id;
	fscanf(pf,"%s",auxstr);
	fscanf(pf,"%d %d %d",&nv,&ne, &nve);
	nVertices=nv;
	for(i=0;i<nv;i++)
	{
		fscanf(pf,"%lf %lf %lf",&x,&y,&z);
		v = new sVertex;
		if(fabs(x)>norma)
			norma=fabs(x);
		if(fabs(y)>norma)
			norma=fabs(y);
		v->setCoord(0,x);
		v->setCoord(1,y);
		v->setCoord(2,z);
		lv.insert(v);
	}
	no=1.0;
	if (norma>1.0)
	{
		norma+=1.0;
		no=norma;
		for(i=0;i<nv;i++)
		{
			v=lv.pos(i);
			x=v->getCoord(0)/norma;
			y=v->getCoord(1)/norma;
			v->setCoord(0,x);
			v->setCoord(1,y);
		}
	}
	for(i=0;i<ne;i++)
	{
		b_id= new ofList<ids>();
		fscanf(pf,"%d",&nve);
		for(j=0;j<nve;j++)
		{
			fscanf(pf,"%d",&vi);
			b_id->insert(vi+4);
			//b_id->insert(vi);
		}
		lids.insert(b_id);
	}
	fclose(pf);
	return true;
}
template <class _Traits> int ofMyOffReader<_Traits>::getNv()
{
	return nVertices;
}
template <class _Traits> ofList<SPOLYGON*>* ofMyOffReader<_Traits>::getBounds()
{
	return &this->bounds;
}
#undef SPOLYGON
#undef SVERTEX10
#undef IDS11
}
#endif
