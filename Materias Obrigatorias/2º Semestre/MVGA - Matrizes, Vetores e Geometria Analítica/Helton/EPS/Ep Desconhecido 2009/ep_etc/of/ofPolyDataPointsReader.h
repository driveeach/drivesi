#ifndef OFPOLYDATAPOINTSREADER_H
#define OFPOLYDATAPOINTSREADER_H

#include "ofMacros.h"
#include "ofList.h"
#include "ofPolygonBoundaries.h"

#define BOUNDSTYPE typename ofPolyDataPointsReader<sMesh>::boundsType
//#define BOUNDSTYPE1 typename ofPolyDataPointsReader<sMesh>::boundsType1

template <class sMesh> class ofPolyDataPointsReader
{
public:
	typedef typename sMesh::sTraits sTraits;
	typedef typename sMesh::space space;
	typedef typename sMesh::ids ids;
	typedef typename sMesh::sVertex sVertex;
	typedef typename sMesh::sCell sCell;
	typedef ofList< ofList<sVertex*>* > boundsType;
	
	
	ofPolyDataPointsReader();
	~ofPolyDataPointsReader();
	
	bool read(char* filename);

	ofList<ofList<sVertex*>*> *getBounds();
	

private:

	void freeBounds(); 
	void freeVertices(); 

	sVertex *vertices;
	ofList<ofList<sVertex*>*> *bounds;
	

};

template <class sMesh> ofPolyDataPointsReader<sMesh>::ofPolyDataPointsReader()
{
	bounds = NULL;
	vertices = NULL;
}

template <class sMesh> ofPolyDataPointsReader<sMesh>::~ofPolyDataPointsReader()
{
	if(vertices)
		delete []vertices;

	if(bounds)
		delete bounds;
}

template <class sMesh> bool ofPolyDataPointsReader<sMesh>::read(char *filename)
{
	OF_ASSERT(filename);
	FILE *pf;
	char auxstr[255];
	int j,idnv;
	ids nv,nc,i,k;
	//space coords[sVertex::getDimension()];
	space coords[3];

	OF_ASSERT(auxstr);
	
	if (!(pf = fopen(filename,"rt")))
	{
		printf("Error reading file %s\n",filename);
		return false;
	}
	
	do
	{
		fscanf(pf,"%s",auxstr);
	}while(strcmp(auxstr,"POINTS") != 0);
	
	fscanf(pf,"%d",&nv);
	fscanf(pf,"%s",auxstr);
	OF_ASSERT(nv > 0);
	
	if(vertices)
		delete vertices;

	if(bounds)
		delete bounds;

	
	

	vertices = new sVertex[nv];
	bounds = new ofList<ofList<sVertex*>*>();

	for (i=0; i<nv; i++)
	{
		fscanf(pf,"%lf %lf %lf",&coords[0],&coords[1],&coords[2]);
		vertices[i].setCoords(coords);
	}

	do
	{
		fscanf(pf,"%s",auxstr);
	}while(strcmp(auxstr,"CELLS") != 0);
	
	fscanf(pf,"%d",&nc);
	fscanf(pf,"%d",&i);
	
	OF_ASSERT(nc >= 0);
	
	ofList< sVertex* > *lista;
	
	
	for (i=0; i<nc; i++)
	{
		fscanf(pf,"%d",&idnv);
		
		OF_ASSERT(idnv > 0);

		lista = new ofList<sVertex*>();
		
		for (j=0; j<idnv; j++)
		{
			fscanf(pf,"%d",&k);
			
			OF_ASSERT((k >= 0)&&(k < nv));

			lista->insert(&vertices[k]);
		}

		bounds->insert(lista);
	}
		
	fclose(pf);
	return true;
}

template <class sMesh> BOUNDSTYPE* ofPolyDataPointsReader<sMesh>::getBounds()
{
	return bounds;
}



template <class sMesh> void ofPolyDataPointsReader<sMesh>::freeBounds()
{
	if(bounds)
	{
		for(int i = 0; i < bounds->getSize(); i++)
			delete bounds->getPos(0);

		delete bounds;
	}
}

template <class sMesh> void ofPolyDataPointsReader<sMesh>::freeVertices()
{
	if(vertices)
		delete []vertices;
}

#undef BOUNDSTYPE

#endif
