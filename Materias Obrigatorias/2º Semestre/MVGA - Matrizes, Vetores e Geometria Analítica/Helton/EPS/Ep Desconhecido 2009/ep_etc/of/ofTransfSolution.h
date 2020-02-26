#ifndef OFTRANSFSOLUTION_H
#define OFTRANSFSOLUTION_H
#include "ofMacros.h"
#include "ofTetGen3DReader.h"
#include "ofVector.h"
#include "ofVerticesIterator.h"
#include "ofDiDo3D.h"
namespace of
{
#define IDS20 typename ofTransfSolution<_Traits3D>::ids
template <class _Traits3D> class ofTransfSolution
{
public:
	typedef typename _Traits3D::space space;
	typedef typename _Traits3D::ids ids;
	typedef typename _Traits3D::sVertex sVertex;
	typedef typename _Traits3D::sCell sCell;
	typedef typename _Traits3D::sMesh sMesh3D;
	
	//ofTransfSolution();
	//~ofTransfSolution();
	void execute(char *filename1, char *filename2, char *filename3);
	void writeSolution(sMesh3D *mesh,char *filename,bool ok=true);
	
private:
	void readSolution(char *filename);
	sMesh3D *mesh;
	sMesh3D *mesh_new;
	space *solution;
	space *newSolution;
	ofTetGen3DReader<_Traits3D> reader;
};
template <class _Traits3D> void ofTransfSolution<_Traits3D>::readSolution(char *filename)
{
	FILE *pf=fopen(filename,"r");
	int i,nv=mesh->getNumberOfVertices();
	solution=new space[nv];
	for(i=0;i<nv;i++)
	{
		fscanf(pf,"%lf",&solution[i]);
	}
	fclose(pf);
}
template <class _Traits3D> void ofTransfSolution<_Traits3D>::writeSolution(sMesh3D *mesh,char *filename, bool ok)
{
	FILE *pf=fopen(filename,"w");
	int i,nv;
	if(ok)
		nv=mesh_new->getNumberOfVertices();
	else
		nv=mesh->getNumberOfVertices();
	//solution=new space[nv];
	for(i=0;i<nv;i++)
	{
		if (ok)
			fprintf(pf,"%d %lf\n",i, newSolution[i]);
		else
			fprintf(pf,"%lf\n",(double)i);
	}
	fclose(pf);
}
template <class _Traits3D> void ofTransfSolution<_Traits3D>::execute(char *filename1, char *filename2, char *filename3)
{
	char name1[1024];
	char name2[1024];
	char name3[1024];
	char name4[1024];
	sprintf(name1,"%s.1.node",filename1);
	sprintf(name2,"%s.1.ele",filename1);
	sprintf(name3,"%s.1.node",filename2);
	sprintf(name4,"%s.1.ele",filename2);
	this->mesh_new = new sMesh3D(1000);
	this->mesh = new sMesh3D(1000);
	reader.read(mesh,name1,name2);	
	reader.read(mesh_new,name3,name4);
	solution=new space[mesh->getNumberOfVertices()];
	writeSolution(mesh,"solution.dat",false);
	readSolution(filename3);
	
	newSolution=new space[mesh_new->getNumberOfVertices()];
	ofVerticesIterator<_Traits3D> itv(mesh_new);
	ofDido3d<_Traits3D> dido(mesh);
	int j,i=0;
	ids cid,v[4];
	space *alpha=new space[4];
	sCell *c;
	for(itv.initialize();itv.notFinish();++itv)
	{
		cid=dido.findCell(alpha,mesh_new->getVertex(&itv));
		if(cid>=0)
		{
			c=mesh->getCell(cid);
			v[0]=c->getVertexId(0);
			v[1]=c->getVertexId(1);
			v[2]=c->getVertexId(2);
			v[3]=c->getVertexId(3);
			newSolution[i]=0.0;
			for(j=0;j<4;j++)
				newSolution[i]+=alpha[j]*solution[v[j]];
		}
		else
			newSolution[i]=0.0;
		i++;
	}
	writeSolution(mesh_new,"newSolution.dat");
	delete[] alpha;
	delete solution;
	delete[] newSolution;
}
}
#undef IDS20
#endif
