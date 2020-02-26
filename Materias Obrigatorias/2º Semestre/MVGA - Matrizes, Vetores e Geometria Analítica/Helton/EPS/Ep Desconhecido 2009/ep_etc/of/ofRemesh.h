#ifndef OFREMESH_H
#define OFREMESH_H
#include"ofRuppert2D.h"
#include "ofTetGenReader.h"
#include "ofVector.h"
#include "ofVtkWriter.h"
#include "ofPolyWriter.h"
#include "ofTransfSolution.h"
namespace of
{
	template <class _Traits,class _Traits3D> class ofRemesh 
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh2D;
	typedef typename _Traits3D::sMesh sMesh3D;
	typedef typename _Traits::sGeometric sGeometric;
	ofRemesh();
	~ofRemesh();
	sMesh2D* getMesh(){return mesh;};
	void setRuppert(ofRuppert2D<_Traits> *R);
	void readFromFile(char* filename);
	bool readDesloc(char *filename);
	void execute(char *filename1, char *filename2);
	void initialize();
	void refine();
	void findBoundaryVertices();
	void writeDisplacement(ofVector<ids,ids> *ref);	
	void writeDesloc(char *filename1, char *filename2);
	void writeDeslocHeli(char *filename1, char *filename2);
private:
	bool isBoundaryVertex(ids v);
	sMesh2D *mesh;
	sMesh2D *mesh_ruppert;
	sGeometric geom;
	ofRuppert2D<_Traits> *ruppert;
	ofTransfSolution<_Traits3D> transf;
	ofTetGenReader<_Traits> TetReader;
	ofList<ids> boundary_vertices;
	space **vert_desloc;
	int *index;
	int ndesloc;
	space dist;
	ofVtkWriter<_Traits> vtkwriter;
	ofPolyWriter<_Traits> out;
};
	template <class _Traits, class _Traits3D> ofRemesh<_Traits, _Traits3D>::ofRemesh()
	{
		this->mesh = new sMesh2D(1000);
		geom.setMesh(mesh);
		dist=-1.0;
	}
	
	template <class _Traits, class _Traits3D> ofRemesh<_Traits, _Traits3D>::~ofRemesh()
	{
		delete mesh;
	}
	template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::setRuppert(ofRuppert2D<_Traits> *R)
	{
		ruppert=R;
	}
	template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::initialize()
	{
		int i,size;
		
		space coords[3];
		space cx,cy;
		mesh_ruppert=ruppert->getMesh();
		cx=(mesh_ruppert->getMaxX()+mesh_ruppert->getMinX())*0.5;
		cy=(mesh_ruppert->getMaxY()+mesh_ruppert->getMinY())*0.5;
		sVertex *v0,*v1,*v2,*v3,*vtmp;
		ids i0,i1,i2,i3;
		v0=new sVertex;
		v0->setCoord(0,mesh_ruppert->getMinX());
		v0->setCoord(1,mesh_ruppert->getMinY());
		v0->setCoord(2,((cx-v0->getCoord(0))*(cx-v0->getCoord(0)))+((cy-v0->getCoord(1))*(cy-v0->getCoord(1))));
		vtmp=ruppert->getDelaynay()->nearestVertex(v0);
		size=v0->getDimension();
		for(i=0;i<size;i++)
			coords[i]=vtmp->getCoord(i);
		i0=mesh->addVertex(coords);
		v1=new sVertex;
		v1->setCoord(0,mesh_ruppert->getMinX());
		v1->setCoord(1,mesh_ruppert->getMaxY());
		v1->setCoord(2,0.0);
		vtmp=ruppert->getDelaynay()->nearestVertex(v1);
		for(i=0;i<size;i++)
			coords[i]=vtmp->getCoord(i);
		i1=mesh->addVertex(coords);
		v2=new sVertex;
		v2->setCoord(0,mesh_ruppert->getMaxX());
		v2->setCoord(1,mesh_ruppert->getMinY());
		v2->setCoord(2,0.0);
		vtmp=ruppert->getDelaynay()->nearestVertex(v2);
		for(i=0;i<size;i++)
			coords[i]=vtmp->getCoord(i);
		i2=mesh->addVertex(coords);
		v3=new sVertex;
		v3->setCoord(0,mesh_ruppert->getMaxX());
		v3->setCoord(1,mesh_ruppert->getMaxY());
		v3->setCoord(2,0.0);
		vtmp=ruppert->getDelaynay()->nearestVertex(v3);
		for(i=0;i<size;i++)
			coords[i]=vtmp->getCoord(i);
		i3=mesh->addVertex(coords);
		ids t1[3];
		ids t2[3];
		t1[0]=i0;
		t1[1]=i1;
		t1[2]=i2;
		t2[0]=i2;
		t2[1]=i1;
		t2[2]=i3;
		mesh->addCell(t1);
		mesh->addCell(t2);
	}
	template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::refine()
	{
		ids cold[3];
		ids cnew[3];
		ids tc[4][3];
		space **coords;
		sCell **lc;
		sVertex *v,*vtmp;
		v=new sVertex;
		int i,j,k,size,nc=mesh->getNumberOfCells();
		coords=new space*[3];
		for(i=0;i<3;i++)
			coords[i]=new space[3];
		lc=new sCell* [nc];
		for(i=0;i<nc;i++)
			lc[i]=mesh->getCell(i);
		for(i=0;i<nc;i++)
		{
			for(j=0;j<3;j++)
				cold[j]=lc[i]->getVertexId(j);
			mesh->delCell(i);
			for(j=0;j<3;j++)
			{
				//geom.getMiddlePoint(cold[j],cold[(j+1)%3],coords[j]);
				for(k=0;k<3;k++)
					v->setCoord(k,coords[j][k]);
				vtmp=ruppert->getDelaynay()->nearestVertex(v);
				size=v->getDimension();
				for(k=0;k<size;k++)
					coords[j][k]=vtmp->getCoord(k);
				cnew[j]=mesh->addVertex(coords[j]);
			}
			
			tc[0][0]=cold[0]; tc[0][1]=cnew[2]; tc[0][2]=cnew[0];
			tc[1][0]=cnew[0]; tc[1][1]=cnew[2]; tc[1][2]=cold[1];
			tc[2][0]=cold[1]; tc[2][1]=cnew[2]; tc[2][2]=cnew[1];
			tc[3][0]=cnew[1]; tc[3][1]=cnew[2]; tc[3][2]=cold[2];
			for(j=0;j<4;j++)
				mesh->addCell(tc[j]);
		}
	}
	template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::readFromFile(char *filename)
	{
		FILE *pf;
	char str[1024];
	int  i,nPoints,nCell;
	space coords[3];
	space maxX=-100000000.0;
	space maxY=-100000000.0;
	space maxZ=-100000000.0;
	space minX=1000000000.0;
	space minY=1000000000.0;
	space minZ=1000000000.0;
	ids t[3];
	pf=fopen(filename,"r");
	do
	{
		fgets(str, 1024, pf); 
		str[25] = 0;
	}while(strcmp(str, "DATASET UNSTRUCTURED_GRID")!=0);
	fscanf(pf, "%*s %d %*s", &nPoints);
	for(i=0;i<nPoints;i++)
	{
		fscanf(pf,"%lf %lf %lf",&coords[0],&coords[1],&coords[2]);
		if(coords[0]<minX)
			minX=coords[0];
		if(coords[1]<minY)
			minY=coords[1];
		if(coords[2]<minZ)
			minZ=coords[2];
		if(coords[0]>maxX)
			maxX=coords[0];
		if(coords[1]>maxY)
			maxY=coords[1];
		if(coords[2]>maxZ)
			maxZ=coords[2];
		mesh->addVertex(coords);
	}
	fscanf(pf,"%*s %d %*s", &nCell);
	for (i = 0;i<nCell;i++)
	{
		fscanf(pf, "%*d %d %d %d", &t[0], &t[1], &t[2]); 
		mesh->addCell(t);
	}
	fclose(pf);
	mesh->setMinX(minX);
	mesh->setMinY(minY);
	mesh->setMinZ(minZ);
	mesh->setMaxX(maxX);
	mesh->setMaxY(maxY);
	mesh->setMaxZ(maxZ);
	
	}
template <class _Traits, class _Traits3D> bool ofRemesh<_Traits, _Traits3D>::isBoundaryVertex(ids v)
{
	ids cv=mesh->getVertex(v)->getSingCell();
	sCell *c=mesh->getCell(cv);
	int i;
	for(i=1;i<3;i++)
	{
		if(c->getMateId((c->getVertexIndex(v)+i)%3)==-1)
			return true;
	}
	return false;
}
template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::findBoundaryVertices()
{
	ofVerticesIterator<_Traits> itv(mesh);
	
	for(itv.initialize();itv.notFinish();++itv)
	{
		if(isBoundaryVertex(&itv))
			boundary_vertices.insert(&itv);
	}
}
template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::execute(char *filename1, char *filename2)
{
	char name[1024];
	char name1[1024];
	char name2[1024];
	int i=0;
	sVertex *v;
		sprintf(name1,"%s.1.node",filename1);
		//sprintf(name1,"%s",filename2);
		sprintf(name2,"%s.1.face",filename1);
		ofVector<ids,ids> ref(1000);
		delete mesh;
		mesh= new sMesh2D(1000);
		TetReader.read(mesh,name1,name2,&ref);
		readDesloc(filename2);
		//ndesloc=ref.getSize();
		for(i=0;i<ndesloc;i++)
		{
			v=mesh->getVertex(ref[index[i]-1]);
			v->setCoord(0,v->getCoord(0)+vert_desloc[i][0]);
			v->setCoord(1,v->getCoord(1)+vert_desloc[i][1]);
			v->setCoord(2,v->getCoord(2)+vert_desloc[i][2]);
		}
		char frase[50];
		double d=geom.findMedianLenghtEdge(mesh);
		vtkwriter.write(mesh,"malha.vtk",1.0,-1);
		sprintf(frase,"ants malha.vtk %lf output.poly",d*1.0);
		system(frase);
		delete mesh;
		mesh= new sMesh2D(1000);
		readFromFile("OutputMesh.vtk");
			out.write(mesh,"out.poly",1.0);
		system("tetgen -pgqAC out.poly");
			system("tetview out.1.face");
		//transf.execute("out","outdesloc","solution.dat");
		for(i=0;i<ndesloc;i++)
			delete vert_desloc[i];
		delete vert_desloc;
		delete index;
	
}
template <class _Traits, class _Traits3D> bool ofRemesh<_Traits, _Traits3D>::readDesloc(char *filename)
{
	FILE *pf=fopen(filename,"r");
	int i,nv, dummy;
	fscanf(pf,"%d %d %d %d",&nv, &dummy,&dummy,&dummy);
	ndesloc=nv;
	vert_desloc=new space*[nv];
	index=new int[nv];
	for(i=0;i<nv;i++)
	{
		vert_desloc[i]= new space[3];
		fscanf(pf,"%d %lf %lf %lf",&index[i],&vert_desloc[i][0],&vert_desloc[i][1],&vert_desloc[i][2]);
	}
	fclose(pf);
	return true;
}
template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::writeDisplacement(ofVector<ids,ids> *ref)
{
	FILE* pf=fopen("desloc.dat","w");
	findBoundaryVertices();
	int i,j,ind,k;
	int nv=boundary_vertices.size();
	fprintf(pf,"%d 3 0 0\n",nv);
	//ref->getFree();
	int size=ref->getSize();
	for(i=0;i<nv;i++)
	{
		k=boundary_vertices.pos(i);
		for(j=0;j<size;j++)
		{
			if((*ref)[j]==k)
			{
				ind=j+1;
				break;
			}
		}
		if((ind>61)&&(ind<68))
			fprintf(pf,"%d 0.4 0.4 0.4\n",ind);
		else
			fprintf(pf,"%d 0.0 0.0 0.0\n",ind);
	}
	fclose(pf);
}
template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::writeDesloc(char* filename1, char *filename2)
{
	FILE* pf=fopen(filename2,"w");
	char name[1024];
	char name1[1024];
	char name2[1024];
	int i=0,j,ind,k;
	sVertex *v;
	sCell *c;
	sprintf(name1,"%s.1.node",filename1);
	sprintf(name2,"%s.1.face",filename1);
	ofVector<ids,ids> ref(1000);
	delete mesh;
	mesh= new sMesh2D(1000);
	TetReader.read(mesh,name1,name2,&ref);
	boundary_vertices.clear();
	findBoundaryVertices();
	int nv=boundary_vertices.size();
	fprintf(pf,"%d 3 0 0\n",nv);
	//ref->getFree();
	int size=ref.getSize();
	space XMax=-10E10;
	space YMax=-10E10;
		space XMin=10E10;
	space YMin=10E10;
	for(i=0;i<nv;i++)
	{
		k=boundary_vertices.pos(i);
		if(XMax<mesh->getVertex(k)->getCoord(0)) XMax=mesh->getVertex(k)->getCoord(0);
		if(XMin>mesh->getVertex(k)->getCoord(0)) XMin=mesh->getVertex(k)->getCoord(0);
		if(YMax<mesh->getVertex(k)->getCoord(1)) YMax=mesh->getVertex(k)->getCoord(1);
		if(YMin>mesh->getVertex(k)->getCoord(1)) YMin=mesh->getVertex(k)->getCoord(1);
	}
	space mx , my;
	space dx;
	space dy;
	space dz;
	// perpendicular vector
	space p[3];
	sVertex *v_n;
	sVertex *v_p;
	sVertex dv1;
	sVertex dv2;
	sVertex dv3;
	int pos;
	
	for(i=0;i<nv;i++)
	{
				k=boundary_vertices.pos(i);
		for(j=0;j<size;j++)
		{
		if((ref)[j]==k)
			{
				ind=j+1;
				break;
			}
		}
		
		v=mesh->getVertex(k);
		c=mesh->getCell(v->getSingCell());
		pos=c->getVertexIndex(k);
		v_n=mesh->getVertex(c->getVertexId((pos+1)%3));
		v_p=mesh->getVertex(c->getVertexId((pos+2)%3));
		for(j=0;j<3;j++)
			dv1.setCoord(j,v->getCoord(j)-v_n->getCoord(j));
		for(j=0;j<3;j++)
			dv2.setCoord(j,v->getCoord(j)-v_p->getCoord(j));
			
		geom.vecCross(&dv1,&dv2,p[0],p[1],p[2]);
		for(j=0;j<3;j++)
			dv3.setCoord(j,p[j]);
		
			/*dx = 0.2*((rand()%1000)/1000.0)*(mesh->getVertex(k)->getCoord(0)/XMax);
			dy = 0.2*((rand()%1000)/1000.0)*(mesh->getVertex(k)->getCoord(1)/YMax);
			dz = ruppert->fp(mesh->getVertex(k)->getCoord(0)*0.5+dx,mesh->getVertex(k)->getCoord(1)*0.5+dy)-ruppert->fp(mesh->getVertex(k)->getCoord(0)*0.5,mesh->getVertex(k)->getCoord(1)*0.5);*/
			if(mesh->getVertex(k)->getCoord(0)==XMax)
			{
				dx=0.25;
				dy=0.0;
				dz=0.0;
				if((mesh->getVertex(k)->getCoord(0)>1.5)&&(mesh->getVertex(k)->getCoord(0)<2.5))
					dz=0.25;
				fprintf(pf,"%d %lf %lf %lf\n",ind,dx,dy,dz);
			}
			else
				fprintf(pf,"%d %lf %lf %lf\n",ind,0.0,0.0,0.0);
			
				
		
	}
	fclose(pf);
}
template <class _Traits, class _Traits3D> void ofRemesh<_Traits, _Traits3D>::writeDeslocHeli(char* filename1, char *filename2)
{
	FILE* pf=fopen(filename2,"w");
	char name[1024];
	char name1[1024];
	char name2[1024];
	int i=0,j,ind,k;
	sVertex *v;
	sVertex pm;
	sCell *c;
	sprintf(name1,"%s.1.node",filename1);
	sprintf(name2,"%s.1.face",filename1);
	ofVector<ids,ids> ref(1000);
	delete mesh;
	mesh= new sMesh2D(1000);
	TetReader.read(mesh,name1,name2,&ref);
	boundary_vertices.clear();
	findBoundaryVertices();
	int nv=boundary_vertices.size();
	fprintf(pf,"%d 3 0 0\n",nv);
	//ref->getFree();
	int size=ref.getSize();
	space XMax=-10E10;
	space YMax=-10E10;
		space XMin=10E10;
	space YMin=10E10;
	for(i=0;i<nv;i++)
	{
		k=boundary_vertices.pos(i);
		if(XMax<mesh->getVertex(k)->getCoord(0)) XMax=mesh->getVertex(k)->getCoord(0);
		if(XMin>mesh->getVertex(k)->getCoord(0)) XMin=mesh->getVertex(k)->getCoord(0);
		if(YMax<mesh->getVertex(k)->getCoord(1)) YMax=mesh->getVertex(k)->getCoord(1);
		if(YMin>mesh->getVertex(k)->getCoord(1)) YMin=mesh->getVertex(k)->getCoord(1);
	}
	
	space mx , my;
	
	mx=(XMin+XMax)*0.5;
	my=(YMin+YMax)*0.5;
	pm.setCoord(0,XMax);
	pm.setCoord(1,my);
	pm.setCoord(2,0.0);
	if((YMax-my)>dist)
		dist=YMax-my;
	space dx;
	space dy;
	space dz;
	// perpendicular vector
	space p[3];
	space disty;
	sVertex *v_n;
	sVertex *v_p;
	sVertex dv1;
	sVertex dv2;
	sVertex dv3;
	int pos;
	
	for(i=0;i<nv;i++)
	{
				k=boundary_vertices.pos(i);
		for(j=0;j<size;j++)
		{
		if((ref)[j]==k)
			{
				ind=j+1;
				break;
			}
		}
		
		v=mesh->getVertex(k);
		c=mesh->getCell(v->getSingCell());
		pos=c->getVertexIndex(k);
		v_n=mesh->getVertex(c->getVertexId((pos+1)%3));
		v_p=mesh->getVertex(c->getVertexId((pos+2)%3));
		for(j=0;j<3;j++)
			dv1.setCoord(j,v->getCoord(j)-v_n->getCoord(j));
		for(j=0;j<3;j++)
			dv2.setCoord(j,v->getCoord(j)-v_p->getCoord(j));
			
		geom.vecCross(&dv1,&dv2,p[0],p[1],p[2]);
		for(j=0;j<3;j++)
			dv3.setCoord(j,p[j]);
		
			dx = p[0];
			dy = p[1];
			dz = p[2];									
			if(mesh->getVertex(k)->getCoord(0)==XMax)
			{
				dx=0.25;
				disty=geom.dist(v,&pm)/dist;
				dy=((1-disty)*my+disty*p[1])*0.3;
				dz=disty*p[2]*0.3;
				
				//if((mesh->getVertex(k)->getCoord(0)>1.5)&&(mesh->getVertex(k)->getCoord(0)<2.5))
					//dz=0.25;
				fprintf(pf,"%d %lf %lf %lf\n",ind,dx,dy,dz);
			}
			else
				fprintf(pf,"%d %lf %lf %lf\n",ind,0.0,0.0,0.0);
			
				
		
	}
	fclose(pf);
}
}
#endif
