#ifndef OFPOINTS2DREADER_H
#define OFPOINTS2DREADER_H
/**
* Esta classe retorna uma lista de ponteiros para vertices
* e uma lista de arestas que representa a conexao entre os
* vertices
*/
#include "ofMacros.h"
#include "ofList.h"
namespace of
{
#define SPOLYGON typename ofPoints2DReader<_Traits>::sPolygon
#define SVERTEX10 typename ofPoints2DReader<_Traits>::sVertex
#define IDS11 typename ofPoints2DReader<_Traits>::ids
//! Classe que le as curvas que representam as fraturas: Formato .off
template <class _Traits> class ofPoints2DReader
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sPolygon sPolygon;
	ofPoints2DReader();
	~ofPoints2DReader();

	/**
	* funcao que le o arquivo de curvas
	* dados de entrada: nome do arquivo
	*/
	bool read(char* filename);
	bool readVerticesEdges(char *filename);
	bool readOffFile(char *filename);
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
template <class _Traits> ofPoints2DReader<_Traits>::ofPoints2DReader()
{
}
template <class _Traits> ofPoints2DReader<_Traits>::~ofPoints2DReader()
{
	//freeBounds();
	//lv.clear();
}
template <class _Traits> void ofPoints2DReader<_Traits>::freeBounds()
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
template <class _Traits> bool ofPoints2DReader<_Traits>::read(char *filename)
{
	OF_ASSERT(filename);
	FILE *pf;
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
	int i,nv,d;
	ofList<ids> *b_id;
	//fscanf(pf,"%s",auxstr);
	fscanf(pf,"%d %d",&nv,&d);
	nVertices=nv;
	for(i=0;i<nv;i++)
	{
		fscanf(pf,"%lf %lf",&x,&y);
		if(d==3)
			fscanf(pf,"%lf",&z);
		v = new sVertex;
		if(fabs(x)>norma)
			norma=fabs(x);
		if(fabs(y)>norma)
			norma=fabs(y);
		v->setCoord(0,x);
		v->setCoord(1,y);
		v->setCoord(2,0.0);

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
	for(i=0;i<nv-1;i++)
	{
		b_id= new ofList<ids>();

			//fscanf(pf,"%d",&vi);
			b_id->insert(i+4);
			b_id->insert(i+5);
			lids.insert(b_id);
	}
	b_id->insert(nv+3);
	b_id->insert(4);
	lids.insert(b_id);
	fclose(pf);
	return true;
}

template <class _Traits> bool ofPoints2DReader<_Traits>::readVerticesEdges(char *filename)
{
	OF_ASSERT(filename);
	FILE *pf;
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
	int i,nv,d,ne;
	ofList<ids> *b_id;
	//fscanf(pf,"%s",auxstr);
	fscanf(pf,"%d %d %d",&nv,&d,&ne);
	nVertices=nv;
	for(i=0;i<nv;i++)
	{
		fscanf(pf,"%lf %lf",&x,&y);
		if(d==3)
			fscanf(pf,"%lf",&z);
		v = new sVertex;
		if(fabs(x)>norma)
			norma=fabs(x);
		if(fabs(y)>norma)
			norma=fabs(y);
		v->setCoord(0,x);
		v->setCoord(1,y);
		v->setCoord(2,0.0);

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
	int v1,v2;
	for(i=0;i<ne;i++)
	{
		b_id= new ofList<ids>();

			fscanf(pf,"%d %d",&v1,&v2);
			b_id->insert(v1+4);
			b_id->insert(v2+4);
			lids.insert(b_id);
	}
	
	fclose(pf);
	return true;
}


//DA LOAD NO MAPA

template <class _Traits> bool ofPoints2DReader<_Traits>::readOffFile(char *filename)
{
	OF_ASSERT(filename);
	FILE *pf; //arquivo
	OF_ASSERT(auxstr);
	if (!(pf = fopen(filename,"rt")))
	{
		printf("Error reading file %s\n",filename);
		return false;
	}
	//freeBounds();
    space norma=0.0;
	sVertex *v;
	space x,y,z,xmin=1000.0;
    space ymin=1000.0;
	int i,nv,d,ne;
	ofList<ids> *b_id;
	char auxstr[10];
	
	fscanf(pf,"%s",auxstr);
	fscanf(pf,"%d %d %d",&nv,&ne,&d);
	nVertices=nv;
	for(i=0;i<nv;i++)
	{
		fscanf(pf,"%lf %lf %lf",&x,&y,&z);
	
		v = new sVertex;
		if(fabs(x)>norma)
			norma=fabs(x);
		if(x<xmin)
           xmin=x;
		if(fabs(y)>norma)
			norma=fabs(y);
		if(y<ymin)
           ymin=y;
           

		v->setCoord(0,x);
		v->setCoord(1,y);
		v->setCoord(2,0.0);

		lv.insert(v);
	}

	int v1,v2,d1;
	for(i=0;i<ne;i++)
	{
		b_id= new ofList<ids>();

			fscanf(pf,"%d %d %d",&d1,&v1,&v2);
			b_id->insert(v1+4);
			b_id->insert(v2+4);
			lids.insert(b_id);
	}
	
	fclose(pf);
	return true;
}

template <class _Traits> int ofPoints2DReader<_Traits>::getNv()
{
	return nVertices;
}
template <class _Traits> ofList<SPOLYGON*>* ofPoints2DReader<_Traits>::getBounds()
{
	return &this->bounds;
}
#undef SPOLYGON
#undef SVERTEX10
#undef IDS11
}
#endif
