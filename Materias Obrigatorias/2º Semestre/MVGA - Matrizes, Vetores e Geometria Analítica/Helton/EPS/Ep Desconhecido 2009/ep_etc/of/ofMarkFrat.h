#ifndef OFMARKFRAT_H
#define OFMARKFRAT_H
#include "ofMacros.h"
#include<stdio.h>
namespace of
{
	
class ofMarkFrat
{
public:
	ofMarkFrat(){};
	virtual ~ofMarkFrat(){};
	bool execute(char *offfilename, char *meshfilename)
		{
		FILE *offFile;
		FILE *meshFilein;
		FILE *meshFileout;
		int nv,i,at,t1,t2,t3,t4;
		double x,y,z;
		char aux[20];
		char auxstr[255];
		char name[255];
		offFile =fopen(offfilename,"r");
		fscanf(offFile,"%c",auxstr);
		fscanf(offFile,"%d",&nv);
		n_vert=nv-8;
		fclose(offFile);
		for(i=0;i<(int)(strlen(meshfilename)-7);i++)
		{
			name[i]=meshfilename[i];
		}
		
		sprintf(aux,".mark");
		name[i]=aux[0];
		name[i+1]=aux[1];
		name[i+2]=aux[2];
		name[i+3]=aux[3];
		name[i+4]=aux[4];
		name[i+5]=meshfilename[i+1];
		name[i+6]=meshfilename[i+2];
		name[i+7]=meshfilename[i+3];
		name[i+8]=meshfilename[i+4];
		name[i+9]=meshfilename[i+5];
		name[i+10]=meshfilename[i+6];
		name[i+11]=meshfilename[i+7];
		
		meshFilein=fopen(meshfilename,"r");
		meshFileout=fopen(name,"w");
		do
	{
		fscanf(meshFilein,"%s",auxstr);
	}while(strcmp(auxstr,"Vertices") != 0);
	 fprintf(meshFileout,"MeshVersionFormatted 1\n \n");
	fprintf(meshFileout,"Dimension\n");
	fprintf(meshFileout,"3\n\n\n");
	fprintf(meshFileout,"# Set of mesh vertices\n");
	fprintf(meshFileout,"Vertices\n");
	fscanf(meshFilein,"%d",&nv);
	fprintf(meshFileout,"%d\n",nv);
	for(i=0;i<nv;i++)
	{
		fscanf(meshFilein,"%lf %lf %lf %d",&x,&y,&z,&at);
		fprintf(meshFileout,"%lf %lf %lf %d\n",x,y,z,at);
	}
	fprintf(meshFileout,"\n");
	fprintf(meshFileout,"# Set of Triangles\n");
	fprintf(meshFileout,"Triangles\n");
	do
	{
		fscanf(meshFilein,"%s",auxstr);
	}while(strcmp(auxstr,"Triangles") != 0);
	fscanf(meshFilein,"%s",auxstr);
	fscanf(meshFilein,"%d",&nv);
	fprintf(meshFileout,"%d\n",nv);
	for(i=0;i<nv;i++)
	{
		fscanf(meshFilein,"%d %d %d %d",&t1,&t2,&t3,&at);
		if((t1<n_vert)&&(t2<n_vert)&&(t3<n_vert))
			fprintf(meshFileout,"%d %d %d 1\n",t1,t2,t3);
		else
			fprintf(meshFileout,"%d %d %d %d\n",t1,t2,t3,at);
	}
	fprintf(meshFileout,"\n");
	fprintf(meshFileout,"# Set of Tetrahedra\n");
	fprintf(meshFileout,"Tetrahedra\n");
	do
	{
		fscanf(meshFilein,"%s",auxstr);
	}while(strcmp(auxstr,"Tetrahedra") != 0);
	fscanf(meshFilein,"%s",auxstr);
	fscanf(meshFilein,"%d",&nv);
	fprintf(meshFileout,"%d\n",nv);
	for(i=0;i<nv;i++)
	{
		fscanf(meshFilein,"%d %d %d %d %d",&t1,&t2,&t3,&t4,&at);
		
		fprintf(meshFileout,"%d %d %d %d %d\n",t1,t2,t3,t4,at);
	}
	fprintf(meshFileout,"\n");
	fprintf(meshFileout,"Corners\n");
	fscanf(meshFilein,"%s",auxstr);
	fscanf(meshFilein,"%d",&nv);
	fprintf(meshFileout,"%d\n",nv);
	for(i=0;i<nv;i++)
	{
		fscanf(meshFilein,"%d",&t1);
		
		fprintf(meshFileout,"%d\n",t1);
	}
	fprintf(meshFileout,"\n");
	fprintf(meshFileout,"Edges\n");
	fscanf(meshFilein,"%s",auxstr);
	fscanf(meshFilein,"%d",&nv);
	fprintf(meshFileout,"%d\n",nv);
	for(i=0;i<nv;i++)
	{
		fscanf(meshFilein,"%d %d %d",&t1,&t2,&at);
		
		fprintf(meshFileout,"%d %d %d\n",t1,t2,at);
	}
	fprintf(meshFileout,"\n");
	fprintf(meshFileout,"End\n");
		fclose(meshFilein);
		fclose(meshFileout);
		return true;
	};
private:
	int n_vert;
	
};
	
}
#endif
