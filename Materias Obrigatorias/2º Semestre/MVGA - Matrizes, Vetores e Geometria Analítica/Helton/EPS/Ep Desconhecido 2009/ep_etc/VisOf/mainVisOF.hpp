#include <assert.h>
#include <iostream>

#include <GL/glut.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <functional>
#include <list>
#include <memory>
#include "GL_Interactor.h"
#include "ColorRGBA.hpp"
#include "Cores.h"

#include "of.h"
#include "ofTraits.h"

#include "Point.hpp"

#include "PrintOf.hpp"

scrInteractor             *Interactor = new scrInteractor(900, 550);

using namespace of;

typedef ofDefaultSurface TTraits;
ofMesh<TTraits> malha;   
ofVtkReader<TTraits> reader;  

typedef PrintOf<ofMesh<TTraits>,ofDefaultSurface>     TPrintOf;
TPrintOf  *Print;


#include "ofVertexStarIteratorSurfaceVertex.h"

//##############################################################################
void RenderScene(void){ 
   
   Print->FacesWireframe(&malha, grey, 1);
   
   Print->Edge(malha.getVertex(0), malha.getVertex(10) , yellow, 3);

   Print->Edge(malha.getCell(20), 0, red, 2);
   Print->Edge(malha.getCell(20), 1, red, 4);
   Print->Edge(malha.getCell(20), 2, red, 6);
   
   Print->Vertex(malha.getCell(30), 0, magenta, 4);
   Print->Vertex(malha.getCell(30), 1, magenta, 8);
   Print->Vertex(malha.getCell(30), 2, magenta, 12);
   
   Print->Face(malha.getCell(10), lgreen);
   Print->FaceWireframe(malha.getCell(10), dgreen, 5);
   
   int cellIdAux, cellId, corner;
   
   cellId = malha.getVertex(0)->getSingCell();
   corner = (malha.getCell(cellId)->getVertexIndex(0)+1)%3;
   
   Print->Vertex(malha.getVertex(malha.getCell(cellId)->getVertexId(corner)), blue , 4);
   Print->Face(malha.getCell(cellId), yellow);
   
   cellIdAux = cellId;
   cellId = malha.getCell(cellId)->getMateId(corner);
   corner= (malha.getCell(cellId)->getMateIndex(cellIdAux) + 2)%3;
   
   Print->Vertex(malha.getVertex(malha.getCell(cellId)->getVertexId(corner)), red , 8);
   Print->Face(malha.getCell(cellId), red);
   

   
	glFinish();
	glutSwapBuffers();
}


void HandleKeyboard(unsigned char key, int x, int y){	
   
   switch (key) {
      case '1' : break;
   }
   
   glutPostRedisplay();
}
 




int main(){

   Interactor->setDraw(RenderScene);
   
   //reader.read(&malha,"chaleira.vtk");
   reader.read(&malha,"sphere.vtk");
   
   Print = new TPrintOf(&malha);
   
   double x1,x2,y1,y2,z1,z2;
   //Interactor->Init(x1,x2,y1,y2,z1,z2);
   Interactor->Init(-10,10,-10,10,-10,10);
   
   AddKeyboard(HandleKeyboard);
   Init_Interactor();
}
