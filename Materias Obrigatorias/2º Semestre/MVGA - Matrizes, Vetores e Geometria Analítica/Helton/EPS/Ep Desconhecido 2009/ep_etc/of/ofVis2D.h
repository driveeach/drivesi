#ifndef OFVIS2D_H
#define OFVIS2D_H
#include <GL/glut.h>
#include "ofMesh.h"
namespace of
{
class ofVis2DGlutParameters
{
public:
	int nt;
	float **v;
	int Resx, Resy, window;
	bool flagshade;
	float CMx, CMy, DCMx, DCMy, BminX, BminY, BmaxX, BmaxY, Scalef;
	
	bool isaloc;
};
ofVis2DGlutParameters ofVis2DCore;
class ofVis2DGlut
{
public:
	ofVis2DGlut()
	{
		ofVis2DCore.isaloc = false;
		ofVis2DCore.Scalef = 1.0;
		ofVis2DCore.flagshade = true;
		ofVis2DCore.nt = 0;
	};
	
	~ofVis2DGlut()
	{
		daloc();
	};
	
	void setWindow(int winX, int winY)
	{
		ofVis2DCore.Resx = winX;
		ofVis2DCore.Resy = winY;
	};
	
	void setAmbient(float _DCMx, float _DCMy, float _BminX, float _BminY, float _BmaxX, float _BmaxY)
	{
		ofVis2DCore.CMx = ofVis2DCore.DCMx = _DCMx;
		ofVis2DCore.CMy = ofVis2DCore.DCMy = _DCMy;
		ofVis2DCore.BminX = _BminX;
		ofVis2DCore.BminY = _BminY;
		ofVis2DCore.BmaxX = _BmaxX;
		ofVis2DCore.BmaxY = _BmaxY;
	};
	
	void setNumberOfTriangles(int triangles)
	{
		ofVis2DCore.nt = 0;
		aloc(triangles);
	}
	void addTriangle(float x1, float y1, float x2, float y2, float x3, float y3)
	{
		ofVis2DCore.v[0][ofVis2DCore.nt] = x1;
		ofVis2DCore.v[1][ofVis2DCore.nt] = y1;
		ofVis2DCore.v[2][ofVis2DCore.nt] = x2;
		ofVis2DCore.v[3][ofVis2DCore.nt] = y2;
		ofVis2DCore.v[4][ofVis2DCore.nt] = x3;
		ofVis2DCore.v[5][ofVis2DCore.nt++] = y3;
	};
	
	static void init()
	{
		int p[1] = { 1 };
		char *pc[1] = { "ofVis2D" };
		
	    glutInit(p, pc);
	    glutInitWindowSize(ofVis2DCore.Resx, ofVis2DCore.Resy);
	    glutInitWindowPosition(50, 50);
	    glutInitDisplayMode(GLUT_RGB | GLUT_DEPTH );
		
	    ofVis2DCore.window = glutCreateWindow(pc[0]);
		
	    glutReshapeFunc(reshape);
	    
		glutDisplayFunc(display);
	
	    glutKeyboardFunc(keyboard);
	    glutMouseFunc(mouse);
		//glutPassiveMotionFunc(move);
			
	    glutMainLoop();
	};
private:
	void daloc()
	{
		if(ofVis2DCore.isaloc)
		{
			delete []ofVis2DCore.v[0];
			delete []ofVis2DCore.v[1];
			delete []ofVis2DCore.v[2];
			delete []ofVis2DCore.v[3];
			delete []ofVis2DCore.v[4];
			delete []ofVis2DCore.v[5];
			delete []ofVis2DCore.v;
		}
	};
	void aloc(int triangles)
	{
		daloc();
		
		ofVis2DCore.isaloc = true;
		ofVis2DCore.v = new float*[6];
		ofVis2DCore.v[0] = new float[triangles];
		ofVis2DCore.v[1] = new float[triangles];
		ofVis2DCore.v[2] = new float[triangles];
		ofVis2DCore.v[3] = new float[triangles];
		ofVis2DCore.v[4] = new float[triangles];
		ofVis2DCore.v[5] = new float[triangles];
	};
	
	static void display()
	{
		glClearColor(1.0f,1.0f,1.0f,1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-(ofVis2DCore.BmaxX - ofVis2DCore.BminX),(ofVis2DCore.BmaxX - ofVis2DCore.BminX),-(ofVis2DCore.BmaxY - ofVis2DCore.BminY),(ofVis2DCore.BmaxY - ofVis2DCore.BminY),-1.0,1.0);
		
		if (!ofVis2DCore.flagshade)
		{
			glPolygonMode(GL_BACK,GL_LINE);
			glPolygonMode(GL_FRONT,GL_LINE);
		}
		else
		{
			glPolygonMode(GL_BACK,GL_FILL);
			glPolygonMode(GL_FRONT,GL_FILL);
		}
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(-ofVis2DCore.CMx,-ofVis2DCore.CMy, 0.0);
		glScalef(ofVis2DCore.Scalef,ofVis2DCore.Scalef,0.0);
		
		glColor3f(0.0f,0.0f,0.0f);
		
		for(int i = 0; i < ofVis2DCore.nt; i++)
		{
			glBegin(GL_POLYGON);
			glVertex2f(ofVis2DCore.v[0][i],ofVis2DCore.v[1][i]);
			glVertex2f(ofVis2DCore.v[2][i],ofVis2DCore.v[3][i]);
			glVertex2f(ofVis2DCore.v[4][i],ofVis2DCore.v[5][i]);
			glEnd();
		}
	
		glFlush();
	}
	
	static void keyboard(unsigned char key, int x, int y)
	{
		switch (key) 
		{
	    case 'w':
	        ofVis2DCore.flagshade = false;
	        break;
			
	    case 's':
	        ofVis2DCore.flagshade = true;
	        break;
			
	    case 'r':
	        ofVis2DCore.CMx = ofVis2DCore.DCMx;
	        ofVis2DCore.CMy = ofVis2DCore.DCMy;
	        ofVis2DCore.Scalef = 1.0;
	        break;
	
	    //case 27:
	        //glutDestroyWindow(ofVis2DCore.window);
		}
		
		display();
	}
	static void mouse(int button,int state,int x,int y)
	{
		switch(button)
		{
	    case GLUT_LEFT_BUTTON:
			if (state == GLUT_DOWN)
			{
				ofVis2DCore.CMx += 2.0*(ofVis2DCore.BmaxX - ofVis2DCore.BminX)*x/ofVis2DCore.Resx - (ofVis2DCore.BmaxX - ofVis2DCore.BminX);
				ofVis2DCore.CMy += 2.0*(ofVis2DCore.BmaxY - ofVis2DCore.BminY)*(ofVis2DCore.Resy - y)/ofVis2DCore.Resy - (ofVis2DCore.BmaxY - ofVis2DCore.BminY) ;
				if(ofVis2DCore.Scalef < 5)
					 ofVis2DCore.Scalef += 0.5;
				else if(ofVis2DCore.Scalef < 10)
					 ofVis2DCore.Scalef += 2;
				else if(ofVis2DCore.Scalef < 20)
					 ofVis2DCore.Scalef += 5;
				else
					ofVis2DCore.Scalef += 10;
			}
			break;
			
	    case GLUT_RIGHT_BUTTON:
			if (state == GLUT_DOWN)
			{
				ofVis2DCore.CMx += 2.0*(ofVis2DCore.BmaxX - ofVis2DCore.BminX)*x/ofVis2DCore.Resx - (ofVis2DCore.BmaxX - ofVis2DCore.BminX);
				ofVis2DCore.CMy += 2.0*(ofVis2DCore.BmaxY - ofVis2DCore.BminY)*(ofVis2DCore.Resy - y)/ofVis2DCore.Resy - (ofVis2DCore.BmaxY - ofVis2DCore.BminY) ;
				if(ofVis2DCore.Scalef > 0.5)
					ofVis2DCore.Scalef -= 0.5;
				else 
					ofVis2DCore.Scalef -= 0.01;
			}
			break;
			
	    case GLUT_MIDDLE_BUTTON:
	        if (state == GLUT_DOWN)
	        {
	        }
	        break;
		}
		
		display();
	}
	static void reshape(int width,  int height)
	{
		glViewport(0, 0, (GLint) width, (GLint) height);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
};
template <class _Traits> class ofVis2D
{
public:
	
	typedef typename _Traits::space space;
	typedef typename _Traits::sVertex sVertex;
	typedef ofMesh<_Traits> sMesh;
	
	ofVis2D(int winX = 800, int winY = 600);
	~ofVis2D();
	
	void view(sMesh *_mesh);
	
private:
	sMesh *mesh;
	void loadMesh();
	
	ofVis2DGlut ofVis2DCore;
};
template <class _Traits> ofVis2D<_Traits>::ofVis2D(int winX, int winY)
{
	OF_ASSERT(winX > 0);
	OF_ASSERT(winY > 0);
	
	ofVis2DCore.setWindow(winX,winY);
	
	mesh = NULL;
}
template <class _Traits> ofVis2D<_Traits>::~ofVis2D()
{
}
template <class _Traits> void ofVis2D<_Traits>::view(sMesh *_mesh)
{
	OF_ASSERT(_mesh);
	mesh = _mesh;
	
	loadMesh();
	
	ofVis2DCore.init();
}
template <class _Traits> void ofVis2D<_Traits>::loadMesh()
{
	space DCMx, DCMy, BminX, BminY, BmaxX, BmaxY;
	sVertex *v0, *v1, *v2;
	ofCellsIterator<_Traits> itc(mesh);
	ofVerticesIterator<_Traits> itv(mesh);
	itv.initialize();
	// iniciando para quardar coord. inferior do Bbox
	
	BminX = itv->getCoord(0);
	BminY = itv->getCoord(1);
	
	// iniciando para quardar coord. superior do Bbox
	
	BmaxX = itv->getCoord(0);
	BmaxY = itv->getCoord(1);
	
	for(++itv; itv.notFinish(); ++itv)
	{
		if (BminX > itv->getCoord(0))
			BminX = itv->getCoord(0);
		if (BminY > itv->getCoord(1))
			BminY = itv->getCoord(1);
	
		if (BmaxX < itv->getCoord(0))
			BmaxX = itv->getCoord(0);
		if (BmaxY < itv->getCoord(1))
			BmaxY = itv->getCoord(1);
	}
		
	DCMx = (BminX + BmaxX)/2.0;
	DCMy = (BminY + BmaxY)/2.0;
	ofVis2DCore.setAmbient(DCMx,DCMy,BminX,BminY,BmaxX,BmaxY);
	ofVis2DCore.setNumberOfTriangles(mesh->getNumberOfCells());
	for(itc.initialize(); itc.notFinish(); ++itc)
	{
		v0 = mesh->getVertex(itc->getVertexId(0));
		v1 = mesh->getVertex(itc->getVertexId(1));
		v2 = mesh->getVertex(itc->getVertexId(2));
		
		ofVis2DCore.addTriangle(v0->getCoord(0), v0->getCoord(1), v1->getCoord(0), v1->getCoord(1), v2->getCoord(0), v2->getCoord(1));
	}
	
	
}
}
#endif
