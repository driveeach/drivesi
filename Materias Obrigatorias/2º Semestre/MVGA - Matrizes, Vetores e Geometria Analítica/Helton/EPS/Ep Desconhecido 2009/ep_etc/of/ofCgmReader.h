#ifndef OFCGMPOINTSREADER_H
#define OFCGMPOINTSREADER_H
#include "ofMacros.h"
#include "ofList.h"
namespace of
{
#define SPOLYGON typename ofCgmReader<_Traits>::sPolygon
template <class _Traits> class ofCgmReader
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sPolygon sPolygon;
	ofCgmReader();
	~ofCgmReader();
	bool read(char* filename);
	ofList<sPolygon*> *getBounds();
private:
	ofList<sPolygon*> bounds;
	void freeBounds();
};
template <class _Traits> ofCgmReader<_Traits>::ofCgmReader()
{
}
template <class _Traits> ofCgmReader<_Traits>::~ofCgmReader()
{
	freeBounds();
}
template <class _Traits> void ofCgmReader<_Traits>::freeBounds()
{
	int i, j;
	sPolygon *ptmp;
	sVertex *vtmp;
	for( i = 0; i < bounds.size(); i++)
	{
		ptmp = bounds.pos(i);
		for (j = 0; j < ptmp->getNumberOfVertices(); j++)
		{
			vtmp = ptmp->getVertex(j);
			delete vtmp;	
		}
		delete ptmp;
	}
	bounds.clear();	
}
template <class _Traits> bool ofCgmReader<_Traits>::read(char *filename)
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
	freeBounds();		
	ofList< space > lista;
	sPolygon *poly;
	sVertex *v;
	space temp;
	int i;
	char c;
	do
	{
		fscanf(pf,"%s",auxstr);
	}while(strcmp(auxstr,"polygon") != 0);
	do
	{
		do
		{
			fscanf(pf,"%c",&c);	
			if(c == '(')
			{
				fscanf(pf,"%d",&i);
				temp = i / 10000.0;
				lista.insert(temp);
				fscanf(pf,"%c",&c);
				fscanf(pf,"%d",&i);
				temp = i / 10000.0;
				lista.insert(temp);
			}
		}while(c != ';');
		poly = new sPolygon();		
		for(i = 0; i < lista.size() - 2; i = i + 2)
		{
			v = new sVertex();
			v->setCoord(0,lista.pos(i));
			v->setCoord(1,lista.pos(i+1));
			v->setCoord(2,0.0);
			poly->addVertex(v);
		}
		bounds.insert(poly);
		lista.clear();
		do
		{
			fscanf(pf,"%s",auxstr);
		}while((strcmp(auxstr,"polygon") != 0)&&(strcmp(auxstr,"ENDPIC;") != 0));
	}while((strcmp(auxstr,"ENDPIC;") != 0));
	fclose(pf);
	return true;
}
template <class _Traits> ofList<SPOLYGON*>* ofCgmReader<_Traits>::getBounds()
{
	return &bounds;
}
#undef SPOLYGON
}
#endif
