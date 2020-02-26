#include "of.h"
#include "ofOffPointsReader.h"
#include "Handler.hpp" 
#include "GL_Interactor.h"
#include "ColorRGBA.hpp"
#include "Cores.h"
#include "Point.hpp"
#include "printof.hpp"


#include "CommandComponent.hpp"
#include "MyCommands.hpp"

#include "ofVertexStarIteratorSurfaceVertex.h"

#ifdef HAVE_CONFIG_H
#include <config.h>
#endif
#include <list>
#include <cstdlib>
#include <stdio.h>
#include <iostream>

#include <glut.h>
#include <time.h>

clock_t start_insert;
clock_t end_insert;
clock_t start_print;
clock_t end_print;



using namespace std;
using namespace of;

//Define o tamanho da tela.
scrInteractor *Interactor = new scrInteractor(800, 600);



/* [EP] FOI REMOVIDO DAQUI E INSERIDO EM 'GL_Interactor.h'

//Define a malha a ser usada.
typedef of::MyofDefault2D TTraits;
typedef of::ofMesh<TTraits> TMesh;
TMesh *malha;
Handler<TMesh> meshHandler;

typedef PrintOf<TTraits> TPrintOf;

TPrintOf *Print;

typedef MyCommands<TPrintOf> TMyCommands;
typedef CommandComponent TAllCommands;

ofVtkWriter<TTraits> writer;
TAllCommands *allCommands;

*/

//##################################################################//

////////////////////////////////////////////////////////////////////////
int type = 3;
//CASO 1 EXECUTA CRUST
//CASO 2 EXECUTA BETA-SKELETON
//CASO 3 EXECUTA ARVORE
////////////////////////////////////////////////////////////////////////

//[EP] ----VARIAVEL QUE CONTROLA O ID DO TRIANGULO INICIAL DE BUSCA----/
int id_init = 0;

////coordenadas do ponto clicado
double xp = 0.0;
double yp = 0.0;
double bar[4];

//[EP] ----REALIZA BUSCA EXAUSTIVA PARA IDENTIFICAR DEFINIR O TRIANGULO DE INICIO----/
//Obs.: Poderia ser feita mudança na estrutura de Cell para incluir um campo visitada
//e utilizar o algoritmo mais eficiente do EP



void getInicio()
{
        int i;
	    for (i = 0; i < malha->getNumberOfCells(); i++)
	    {
            double xp = Interactor->getPXD(); //coordenada x
            double yp = Interactor->getPYD(); //coordenada y
            double bar1, bar2, bar3; //coordenadas baricentricas
            bar1 = bar2 = bar3 = -1; //inicializar valores das coordenadas baricentricas arbitrariamente
            double xa, ya, xb, yb, xc, yc; //coordenadas dos pontos do triangulo ABC
            //OBTER AS COORDENADAS DOS PONTOS QUE FORMAM UM TRIANGULO----//
            xa = malha->getVertex(malha->getCell(i)->getVertexId(0))->getCoord(0);
            ya = malha->getVertex(malha->getCell(i)->getVertexId(0))->getCoord(1);
            xb = malha->getVertex(malha->getCell(i)->getVertexId(1))->getCoord(0);
            yb = malha->getVertex(malha->getCell(i)->getVertexId(1))->getCoord(1);
            xc = malha->getVertex(malha->getCell(i)->getVertexId(2))->getCoord(0);
            yc = malha->getVertex(malha->getCell(i)->getVertexId(2))->getCoord(1);
            //CALCULAR AS AREAS DOS TRIANGULOS
            double areaABC = 0.5*((xa*yb)-(ya*xb)+(ya*xc)-(xa*yc)+(xb*yc)-(yb*xc));
            double areaPBC = 0.5*((xp*yb)-(yp*xb)+(yp*xc)-(xp*yc)+(xb*yc)-(yb*xc));
            double areaAPC = 0.5*((xa*yp)-(ya*xp)+(ya*xc)-(xa*yc)+(xp*yc)-(yp*xc));
            double areaABP = 0.5*((xa*yb)-(ya*xb)+(ya*xp)-(xa*yp)+(xb*yp)-(yb*xp));
            //DETERMINAR AS COORDENDAS BARICENTRICAS
            bar1 = areaPBC/areaABC;
            bar2 = areaAPC/areaABC;
            bar3 = areaABP/areaABC;
            
            //ATUALIZA E ENCERRA SE ENCONTRAR TRIANGULO NO PONTO CLICADO
            if (bar1 > 0 && bar2 > 0 && bar3 > 0)
            {
               id_init = i;
               return;
            }
            //CLIQUE FORA DO MAPA
            else 
            {
                 id_init = 0;
            }
        }
}

void bari(double xp, double yp, int id)
{
         double xa, ya, xb, yb, xc, yc; //coordenadas dos pontos do triangulo ABC
         
         //OBTER AS COORDENADAS DOS PONTOS QUE FORMAM UM TRIANGULO----//
         xa = malha->getVertex(malha->getCell(id)->getVertexId(0))->getCoord(0);
         ya = malha->getVertex(malha->getCell(id)->getVertexId(0))->getCoord(1);
         xb = malha->getVertex(malha->getCell(id)->getVertexId(1))->getCoord(0);
         yb = malha->getVertex(malha->getCell(id)->getVertexId(1))->getCoord(1);
         xc = malha->getVertex(malha->getCell(id)->getVertexId(2))->getCoord(0);
         yc = malha->getVertex(malha->getCell(id)->getVertexId(2))->getCoord(1);

         //CALCULAR AS AREAS DOS TRIANGULOS
         double areaABC = 0.5*((xa*yb)-(ya*xb)+(ya*xc)-(xa*yc)+(xb*yc)-(yb*xc));
         double areaPBC = 0.5*((xp*yb)-(yp*xb)+(yp*xc)-(xp*yc)+(xb*yc)-(yb*xc));
         double areaAPC = 0.5*((xa*yp)-(ya*xp)+(ya*xc)-(xa*yc)+(xp*yc)-(yp*xc));
         double areaABP = 0.5*((xa*yb)-(ya*xb)+(ya*xp)-(xa*yp)+(xb*yp)-(yb*xp));

         //DETERMINAR AS COORDENDAS BARICENTRICAS
         bar[1] = areaPBC/areaABC;
         bar[2] = areaAPC/areaABC;
         bar[3] = areaABP/areaABC;
         
         if (bar[1] > 0 && bar[2] > 0 && bar[3] > 0)
         {
             bar[0] = 1.0; //TRUE            
         }
         else
         {
             bar[0] = 0.0; //FALSE
         }
}

void EPREC(double xp, double yp, int id, int prev, int visitado[])
{
     cout << id <<"," <<visitado[id] <<"-";
     if (prev == -1) Print->Face(malha->getCell(id), yellow);
     else visitado[id] = 1;
     Print->Face(malha->getCell(id), dgreen);
     bari(xp, yp, id);
     if (bar[0] == 1.0)
     {
        return;
     }
     else
     {
         if (bar[1] < bar[2] && bar[1] < bar[3]) //p encontra-se ao lado da aresta BC
         {
            if (malha->getCell(id)->getMateId(0) != -1) EPREC(xp, yp, malha->getCell(id)->getMateId(0), id, visitado);
         }
         else if (bar[2] < bar[1] && bar[2] < bar[3])
         {
              if (malha->getCell(id)->getMateId(1) != -1) EPREC(xp, yp, malha->getCell(id)->getMateId(1), id, visitado);
         }
         else
         {
             if (malha->getCell(id)->getMateId(2) != -1) EPREC(xp, yp, malha->getCell(id)->getMateId(2), id, visitado);
         }
     }
     cout << id <<"," <<visitado[id] <<"-";
}

//[EP] ----METODO QUE RESOLVE O PROBLEMA PROPOSTO-----------------------------//
void EP(double xp, double yp){
    
    int id = id_init;
    bari(xp, yp, id);
    while (bar[0] != 1.0)
    {
          Print->Face(malha->getCell(id), dgreen);
          int prox, menor;
          {
              //menor: BC = 0, AC = 1, AB =2
              if (bar[1] < bar[2] && bar[1] < bar[3])
              {
                 menor = 0; //p encontra-se ao lado da aresta BC
                 prox = malha->getCell(id)->getMateId(0);
              }
             
              if (bar[2] < bar[1] && bar[2] < bar[3])
              {
                 menor = 1; //p encontra-se ao lado da aresta AC
                 prox = malha->getCell(id)->getMateId(1);
              }
              if (bar[3] < bar[1] && bar[3] < bar[2])
              {
                 menor = 2; //p encontra-se ao lado da aresta AB
                 prox = malha->getCell(id)->getMateId(2);
              }
              
              //VERIFICAR SE CHEGOU NA FRONTEIRA
              if (prox == -1)
              {
                 
                 break;
              }
              else
              {
                  id = prox;
                  bari(xp, yp, id);
              }
          }
    }
    Print->Face(malha->getCell(id), red);
}




//----[EP] MÉTODO QUE DESENHA NA TELA----//
void RenderScene(void){
	allCommands->Execute();
	
	//[EP] OBS: o que aparece primeiro é pintado por cima
	Print->Vertices(malha,blue,3);


    //[EP] ----METODOS DE IMPRESSAO QUE FORAM UTILIZADOS NO DESENVOLVIMENTO----/
    /*
	/----IMPRIME OS NUMEROS DOS VERTICES E TRIANGULOS----/
    Print->CellsIds(malha, green); //Imprime o ID de todas as células
	Print->VerticesIds(malha, blue); //Imprime os IDs dos vértices
    
    /----EXEMPLOS DE IMPRESSAO DE ARESTAS----/
    Print->Edge(malha->getVertex(0), malha->getVertex(1), blue, 10.0);
    Print->Edge(1, 2, blue, 10.0);
    
	/----METODO QUE PINTA AS FACES DOS TRIÂNGULOS DAS FRONTEIRAS----/
	//Obs.: Fronteiras são os triângulos cujo MateId de alguma das arestas é igual a -1
	int i;
	for(i = 0; i < malha->getNumberOfCells(); i++)
	{
          if(malha->getCell(i)->getMateId(0) == -1)
          {
              Print->Face(malha->getCell(i), red);
              continue;
          }
          if(malha->getCell(i)->getMateId(1) == -1)
          {
              Print->Face(malha->getCell(i), red);
              continue;
          }
          if(malha->getCell(i)->getMateId(2) == -1)
          {
              Print->Face(malha->getCell(i), red);
              continue;
          }
    }
    /----PEGAR O ID DO VERTICE N DA CELULA #----/
    //Obs.: cada celula/triangulo tem possui os vertices 0, 1 e 2;
    int m = malha->getCell(255)->getVertexId(0); //Peguei O ID do Vértice 0 da célula 255
    /----ROTINA DE IMPRESSÃO DAS COORDENADAS A, B, C DE UM TRIANGULO DADO - EX.: 255----/
    int j;
    int k = 0;
    for (j = 0; j < 6; j++)
    {
        k = j/2;
        int d = malha->getCell(255)->getVertexId(k);
        cout<< d <<"," <<ct[j] <<endl;
    }
    */
    //[FIM]
    
    //[EP] ----CHAMA METODO EXAUSTIVO PARA ATUALIZAR TRIANGULO INICIAL----//
    if (Interactor->getMouseRight() == true)
       getInicio();
    //[EP] ----IMPRIME CAMINHO POR COORDENADAS BARICENTRICAS----//
    //----VERIFICAR SE AS COORDENADAS MUDARAM----/
    if (xp != Interactor->getPX() || yp != Interactor->getPY())
    {
       static int mapa[malha->getNumberOfCells()];
       for (int i = 0; i < malha->getNumberOfCells(); i++)
       {
           mapa[i] = 0;
       }
       bar[0] = 0.0;
       EPREC(Interactor->getPX(), Interactor->getPY(), id_init, -1, mapa);
    }


	Print->FacesWireframe(malha,grey,3);
	glFinish();
	glutSwapBuffers();
}


void eh_menor(int& menor1, int& menor2, double bar1, double bar2, double bar3)
{
     if (bar1 < bar2 && bar1 < bar3)
     {
       if (bar2 < bar3)
       {
          menor1 = 0;
          menor2 = 1;
       }
       else
       {
           menor1 = 0;
           menor2 = 2;
       }
    }
    else if (bar2 < bar1 && bar2 < bar3)
     {
          if (bar1 < bar3)
          {
             menor1 = 1;
             menor2 = 0;
          }
          else
          {
              menor1 = 1;
              menor2 = 2;
          }
     }
}




void HandleKeyboard(unsigned char key, int x, int y){	
	
	
	
	double coords[3];
	char *xs[10];
	allCommands->Keyboard(key);
	
	switch (key) {

		case 'e':
			exit(1);
		break;
		case 'v':
			coords[0]=x;
			coords[1]=-y;
			coords[2]=0.0;
			malha->addVertex(coords);
		break;
		case 's':
			
			
		break;

		case 'd':
			//RESERVADO PARA O MÉTODO QUE VAI ALTERAR O ID DO TRIANGULO INICIAL
			
		break;
	

	}
    
	Interactor->Refresh_List();
	glutPostRedisplay();

}

using namespace std;

int main(int argc, char *argv[])
{
  ofRuppert2D<MyofDefault2D> ruppert;
  ofPoints2DReader<MyofDefault2D> reader;
  ofVtkWriter<MyofDefault2D> writer;
  Interactor->setDraw(RenderScene);
	meshHandler.Set(new TMesh());
    reader.readOffFile("Brasil.off");
    ruppert.execute2D(reader.getLv(),reader.getLids(),true);
    //writer.write(ruppert.getMesh(),"out.vtk",reader.getNorma(),ruppert.getNumberOfInsertedVertices());
  
  meshHandler = ruppert.getMesh();
  malha = ruppert.getMesh();
  
  
  Print = new TPrintOf(meshHandler);

	allCommands = new TMyCommands(Print, Interactor);

	double a,x1,x2,y1,y2,z1,z2; 

	of::ofVerticesIterator<TTraits> iv(&meshHandler);
	

	iv.initialize();
	x1 = x2 = iv->getCoord(0);
	y1 = y2 = iv->getCoord(1);
	z1 = z2 = iv->getCoord(2);
	
	

	for (iv.initialize(); iv.notFinish(); ++iv){
        iv->setCoord(1, -iv->getCoord(1)); //[EP] ----DESINVERTE O MAPA DO BRASIL
		if(iv->getCoord(0) < x1) x1 = a = iv->getCoord(0);
		if(iv->getCoord(0) > x2) x2 = a = iv->getCoord(0);
		if(iv->getCoord(1) < y1) y1 = a = iv->getCoord(1);
		if(iv->getCoord(1) > y2) y2 = a = iv->getCoord(1);
		if(iv->getCoord(2) < z1) z1 = a = iv->getCoord(2);
		if(iv->getCoord(2) > z2) z2 = a = iv->getCoord(2);
	}

	double maxdim;
	maxdim = fabs(x2 - x1);
	if(maxdim < fabs(y2 - y1)) maxdim = fabs(y2 - y1);
	if(maxdim < fabs(z2 - z1)) maxdim = fabs(z2 - z1);

    //[EP] ----MUDA A POSIÇÃO E ZOOM INICIAIS DO MAPA PARA CENTRALIZAR MAIS
	maxdim *= 0.4;
	Point center((x1+x2)/2.0, (y1+y2)/1.2, (y1+y2)/2.0 );
	Interactor->Init(center[0]-maxdim, center[0]+maxdim,
					center[1]-maxdim, center[1]+maxdim,
					center[2]-maxdim, center[2]+maxdim);

	AddKeyboard(HandleKeyboard);

	allCommands->Help(std::cout);
	std::cout<< std::endl<< "Press \"?\" key for help"<<std::endl<<std::endl;
	double t;
	
	Init_Interactor();

  
  return EXIT_SUCCESS;
}
