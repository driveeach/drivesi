#ifndef OFVTKPOINTSREADER_H
#define OFVTKPOINTSREADER_H

#include "ofMacros.h"
#include "ofReader.h"

template <class sMesh> class ofVtkPointsReader : public ofReader<sMesh>
{
public:
	typedef typename sMesh::space space;
	typedef typename sMesh::ids ids;
	typedef typename sMesh::sVertex sVertex;
	typedef typename sMesh::sCell sCell;
	
	ofVtkPointsReader();
	~ofVtkPointsReader();
	
	virtual bool read(sMesh* malha, char* filename);
};

template <class sMesh> ofVtkPointsReader<sMesh>::ofVtkPointsReader()
{
}

template <class sMesh> ofVtkPointsReader<sMesh>::~ofVtkPointsReader()
{
}

template <class sMesh> bool ofVtkPointsReader<sMesh>::read(sMesh* malha, char *filename)
{
	OF_ASSERT(filename);
	OF_ASSERT(malha);
	FILE *pf;
	char auxstr[255];
	ids nv,i;
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
	
	for (i=0; i<nv; i++)
	{
		fscanf(pf,"%lf %lf %lf",&coords[0],&coords[1],&coords[2]);

		malha->addVertex(coords);
	}
		
	fclose(pf);
	return true;
}
#endif
