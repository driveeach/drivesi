#ifndef OFOFFPOINTSREADER_H
#define OFOFFPOINTSREADER_H
#include "ofMacros.h"
#include "ofList.h"
#include "ofPolygonBoundaries.h"
#define BOUNDSTYPE typename ofOffPointsReader<sMesh>::boundsType
#define SVERTEX16 typename ofOffPointsReader<sMesh>::sVertex
template <class sMesh> class ofOffPointsReader
{
public:
	typedef typename sMesh::sTraits sTraits;
	typedef typename sMesh::space space;
	typedef typename sMesh::ids ids;
	typedef typename sMesh::sVertex sVertex;
	typedef typename sMesh::sCell sCell;
	typedef of::ofList< of::ofList<sVertex*>* > boundsType;
	
	
	ofOffPointsReader();
	~ofOffPointsReader();
	
	bool read(char* filename);
	of::ofList<of::ofList<sVertex*>*> *getBounds();
	sVertex *getV();
	int getNvertices();
private:
	void freeBounds(); 
	void freeVertices(); 
	sVertex *vertices;
	of::ofList<of::ofList<sVertex*>*> *bounds;
	int nvert;
	
};
template <class sMesh> ofOffPointsReader<sMesh>::ofOffPointsReader()
{
	bounds = NULL;
	vertices = NULL;
}
template <class sMesh> ofOffPointsReader<sMesh>::~ofOffPointsReader()
{
	if(vertices)
		delete []vertices;
	if(bounds)
		delete bounds;
}
template <class sMesh> bool ofOffPointsReader<sMesh>::read(char *filename)
{
	OF_ASSERT(filename);
	FILE *pf;
	char auxstr[255];
	int j,idnv;
	ids nv,ne,i,k;
	//space coords[sVertex::getDimension()];
	space coords[3];
	OF_ASSERT(auxstr);
	
	if (!(pf = fopen(filename,"rt")))
	{
		printf("Error reading file %s\n",filename);
		return false;
	}
	
	
	fscanf(pf,"%s",auxstr);
	
	
	fscanf(pf,"%d",&nv);
	fscanf(pf,"%d %d",&ne);
	OF_ASSERT(nv > 0);
	nvert=nv;
	if(vertices)
		delete vertices;
	if(bounds)
		delete bounds;
	
	
	vertices = new sVertex[nv];
	bounds = new of::ofList<of::ofList<sVertex*>*>();
	for (i=0; i<nv; i++)
	{
		fscanf(pf,"%lf %lf %lf",&coords[0],&coords[1],&coords[2]);
		vertices[i].setCoords(coords);
	}
		
	OF_ASSERT(ne >= 0);
	
	of::ofList< sVertex* > *lista;
	
	
	for (i=0; i<ne; i++)
	{
		fscanf(pf,"%d",&idnv);
		
		OF_ASSERT(idnv > 0);
		lista = new of::ofList<sVertex*>();
		
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
template <class sMesh> BOUNDSTYPE* ofOffPointsReader<sMesh>::getBounds()
{
	return bounds;
}
template <class sMesh> SVERTEX16* ofOffPointsReader<sMesh>::getV()
{
	return vertices;
}
template <class sMesh> int ofOffPointsReader<sMesh>::getNvertices()
{
	return nvert;
}
template <class sMesh> void ofOffPointsReader<sMesh>::freeBounds()
{
	if(bounds)
	{
		for(int i = 0; i < bounds->getSize(); i++)
			delete bounds->getPos(0);
		delete bounds;
	}
}
template <class sMesh> void ofOffPointsReader<sMesh>::freeVertices()
{
	if(vertices)
		delete []vertices;
}
#undef BOUNDSTYPE
#undef SVERTEX16
#endif
