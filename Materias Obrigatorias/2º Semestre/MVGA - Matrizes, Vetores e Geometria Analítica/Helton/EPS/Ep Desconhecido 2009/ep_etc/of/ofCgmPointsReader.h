#ifndef OFCGMPOINTSREADER_H
#define OFCGMPOINTSREADER_H
#include "ofMacros.h"
#include "ofList.h"
#include "ofPolygonBoundaries.h"
#define BOUNDSTYPE typename ofCgmPointsReader<sMesh>::boundsType
template <class sMesh> class ofCgmPointsReader
{
public:
	typedef typename sMesh::sTraits sTraits;
	typedef typename sMesh::space space;
	typedef typename sMesh::ids ids;
	typedef typename sMesh::sVertex sVertex;
	typedef typename sMesh::sCell sCell;
	typedef ofPolygonBoundaries<2,sTraits> boundsType;
	ofCgmPointsReader();
	~ofCgmPointsReader();
	bool read(char* filename);
	boundsType *getBounds();
private:
	boundsType *bounds;
};
template <class sMesh> ofCgmPointsReader<sMesh>::ofCgmPointsReader()
{
	bounds = NULL;
}
template <class sMesh> ofCgmPointsReader<sMesh>::~ofCgmPointsReader()
{
	if(bounds)
		delete bounds;
}
template <class sMesh> bool ofCgmPointsReader<sMesh>::read(char *filename)
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
	if(bounds)
		delete bounds;
	bounds = new boundsType();
	ofList< space > lista;
	space temp, **coords;
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
				temp = i / 100.0;
				lista.insert(temp);
				fscanf(pf,"%c",&c);
				fscanf(pf,"%d",&i);
				temp = i  / 100.0;
				lista.insert(temp);
			}
		}while(c != ';');
		coords = new space*[lista.getSize()/2 - 1];
		for(i = 0; i < lista.getSize() - 2; i = i + 2)
		{
			coords[i/2] = new space[2];
			coords[i/2][0] = lista.getPos(i);
			coords[i/2][1] = lista.getPos(i+1);
		}
		bounds->addBoundary(lista.getSize()/2 - 1,coords);
		lista.clear();
		delete coords;
		do
		{
			fscanf(pf,"%s",auxstr);
		}while((strcmp(auxstr,"polygon") != 0)&&(strcmp(auxstr,"ENDPIC;") != 0));
	}while((strcmp(auxstr,"ENDPIC;") != 0));
	fclose(pf);
	return true;
}
template <class sMesh> BOUNDSTYPE* ofCgmPointsReader<sMesh>::getBounds()
{
	return bounds;
}
#undef BOUNDSTYPE
#endif
