#ifndef PRINTOF_HPP_
#define PRINTOF_HPP_

#include "PrintBase.hpp"
#include <memory>
#include<list>///
#include "ofList.h"///
//#include "tree.h"///

#include "of.h"
#include "ofCellsIterator.h"
//#include "types.h"
#include "Handler.hpp"


using namespace std;

template<class _Traits>
class PrintOf : public PrintBase
{
public:
   
   typedef PrintBase                 TPrintBase;
	
   typedef _Traits       TTraits;
   typedef typename TTraits::sMesh         TMesh;

   
   
   typedef typename TTraits::ids     TIds;
   typedef typename TTraits::space   space;
   
   typedef typename TTraits::sVertex   TVertex;
   typedef typename TTraits::sCell     TCell;
   
	

   typedef Handler<TMesh>            TMeshHandler;
   typedef Point                     TPoint;
   typedef TPoint                    TVector;
   
   typedef Point3DUtils<TPoint>      TP3DU;
   typedef float                     TReal;
   /////////////////////////////
	typedef typename _Traits::sGeometric sGeometric;
	sGeometric geom;
   /////////////////////////////

   
public:
  
   using TPrintBase::Text;
   using TPrintBase::Point3D;
   using TPrintBase::Line;
   using TPrintBase::TriangleWireframe;
   using TPrintBase::Triangle; 
   using TPrintBase::TetrahedronWireframe;
   using TPrintBase::Tetrahedron;    
   
public:

   TMeshHandler   meshHandler_;   
   
   TVertex *vertex_;
   TVertex *vertex1_;
   TVertex *vertex2_;
   TVertex *vertex3_;
   
   std::auto_ptr<of::ofVerticesIterator<TTraits> >    iv;
   std::auto_ptr<of::ofCellsIterator<TTraits> >       ic;   
public:   
   
   PrintOf(TMeshHandler &meshHandler_i)
      : meshHandler_(meshHandler_i){
   };
      
     
   void
   InitializeIterators(){
      
      assert(&this->meshHandler_);
      
      iv  = std::auto_ptr<of::ofVerticesIterator<TTraits> >(&meshHandler_);   
      ic  = std::auto_ptr<of::ofCellsIterator<TTraits> >(&meshHandler_);
   };
   
public:   



   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Triangle (TVertex *v1, TVertex *v2, TVertex *v3, const TColorRGBA &Color){
     
      Triangle(v1->getCoord(0), v1->getCoord(1), v1->getCoord(2),
               v2->getCoord(0), v2->getCoord(1), v2->getCoord(2),
               v3->getCoord(0), v3->getCoord(1), v3->getCoord(2),
               Color);   
   } 
   
   //---------------------------------------------------------------------
   void Tetrahedron (TVertex *v1, TVertex *v2, TVertex *v3, TVertex *v4, const TColorRGBA &Color){
     
      Tetrahedron(
         v1->getCoord(0), v1->getCoord(1), v1->getCoord(2),
         v2->getCoord(0), v2->getCoord(1), v2->getCoord(2),
         v3->getCoord(0), v3->getCoord(1), v3->getCoord(2),
         v4->getCoord(0), v4->getCoord(1), v4->getCoord(2),
         Color
      );   
   } 
   
   //---------------------------------------------------------------------
   void TetrahedronWireframe (
           TVertex *v1, TVertex *v2, TVertex *v3, TVertex *v4, 
           const TColorRGBA &Color, const float width=1
        ){
     
      TetrahedronWireframe(
         v1->getCoord(0), v1->getCoord(1), v1->getCoord(2),
         v2->getCoord(0), v2->getCoord(1), v2->getCoord(2),
         v3->getCoord(0), v3->getCoord(1), v3->getCoord(2),
         v4->getCoord(0), v4->getCoord(1), v4->getCoord(2),
         Color, width
      );   
   } 

   //---------------------------------------------------------------------
  /* void BoundaryEdges(TMesh *mesh,const TColorRGBA &Color, const int size=5){

       ofCellsIterator<TTraits>  ic(mesh);
        int i;
        for(ic.initialize(); ic.notFinish(); ++ic){
			for(i=0;i<3;i++)
			{
				if(ic->getMateId(i)==-1)
				{
					Edge(*ic,i,Color,size);
				}
			}
           
        }
        
   };*/
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
   /*void CrustEdges(TMesh *mesh,const TColorRGBA &Color, const int size=5){

       ofCellsIterator<TTraits>  ic(mesh);
        int i;
		
        for(ic.initialize(); ic.notFinish(); ++ic){
			
			for(i=0;i<3;i++)
			{
				if((mesh->getVertex(ic->getVertexId(i)))->getFirst()== true && (mesh->getVertex(ic->getVertexId((i+1)%3)))->getFirst()== true)
					Edge(*ic,(i+2)%3,Color,size);
			}
           
        }
        
   };
   
   void BetaEdges(TMesh *mesh,const TColorRGBA &Color, const int size=5){

       ofCellsIterator<TTraits>  ic(mesh);
        int i,mateid;
		double c1,c2,edge;
		space center[3];
		double beta = 1.7;
		geom.setMesh(mesh);
		start_print = clock();
        for(ic.initialize(); ic.notFinish(); ++ic){
			geom.getCircuncircle(mesh->getCell(&ic),center);
			c1=geom.dist(mesh->getVertex(ic->getVertexId(0))->getCoords(),center);
			
			for(i=0;i<3;i++)
			{
				edge=geom.dist(mesh->getVertex(ic->getVertexId((i+1)%3)),mesh->getVertex(ic->getVertexId((i+2)%3)));
				if(ic->getMateId(i)==-1)
				{
					
					if(c1>(beta*0.5)*edge)
					{
						Edge(*ic,(i)%3,Color,size);
					}
				}
				else
				{
					geom.getCircuncircle(mesh->getCell(ic->getMateId(i)),center);
					c2=geom.dist(mesh->getVertex(ic->getVertexId((i+1)%3))->getCoords(),center);
					if((c1>(beta*0.5)*edge)&&(c2>(beta*0.5)*edge))
						Edge(*ic,(i)%3,Color,size);

				}
				
				
			}
           
        }
		end_print= clock();
        
   };*/

      /*void MaxMinEdges(TMesh *mesh, list<edge> *listEdge,const TColorRGBA &Color,const int size=5){

        int i;
		int tam=listEdge->size();
		std::list<edge>::iterator it;
		edge aux;
		
		for(it=listEdge->begin();it!=listEdge->end();++it){
			
			
			Edge(mesh->getVertex(it->v1),mesh->getVertex(it->v2),Color,size);
			           
        }
        
   };*/
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //--------------------------------------------------------------------- 
   void Vertex(TVertex *vertex, const TColorRGBA &Color, const int size=5){
        
        this->setAlpha(Color.A);  

        // Set current drawing color 
        //        R   G    B
        glColor4f(Color.R, Color.G, Color.B, Color.A);
        glPointSize(size);
         
        glBegin(GL_POINTS); 
        if(vertex->getDimension()==2)
          glVertex3f(vertex->getCoord(0), vertex->getCoord(1), 0.0);
        else
          glVertex3f(vertex->getCoord(0), vertex->getCoord(1), vertex->getCoord(2));           
        glEnd();
   };
   
   //--------------------------------------------------------------------- 

  /* void StarVertex(TMesh *mesh,int id, const TColorRGBA &Color){
        
        

        
        
        ofVertexStarIteratorSurface<TTraits>  it(mesh);
         
        for(it.initialize(id); it.notFinish(); ++it)
		{
			//Triangle(this->meshHandler_->getVertex(it->getVertexId(0)),this->meshHandler_->getVertex(it->getVertexId(1)),this->meshHandler_->getVertex(it->getVertexId(2)),Color);
			Edge(this->meshHandler_->getVertex(it->getVertexId(0)),this->meshHandler_->getVertex(it->getVertexId(1)),Color,10);
			Edge(this->meshHandler_->getVertex(it->getVertexId(1)),this->meshHandler_->getVertex(it->getVertexId(2)),Color,10);
			Edge(this->meshHandler_->getVertex(it->getVertexId(2)),this->meshHandler_->getVertex(it->getVertexId(0)),Color,10);
		}
   };*/
   
   //--------------------------------------------------------------------- 
   void Vertex(TIds id, const TColorRGBA &Color, const int size=5){
      Vertex(this->meshHandler_->getVertex(id), Color, size);        
   };
   
   
   //--------------------------------------------------------------------- 
   //
   void Vertices(TMesh *mesh,const TColorRGBA &Color, const int size=5){

        of::ofVerticesIterator<TTraits> iv(mesh);
		int i=0;
        for(iv.initialize(); iv.notFinish(); ++iv){
			/*if(i==181)
				Vertex(*iv,llblue,size+3);

			else*/ if(iv->type==0)
				Vertex(*iv, Color, size);
			else if(iv->type==1)
					Vertex(*iv, orange, size+1);
			else
				Vertex(*iv, lcyan2, size+2);
			i++;
		}  
   };
   
   //--------------------------------------------------------------------- 
   void Vertices(const TColorRGBA &Color, const int size=5){
        Vertices(&this->meshHandler_, Color, size);
   };

  
   //--------------------------------------------------------------------- 
   void VerticesIds(TMesh *mesh, const TColorRGBA &Color){
        
        char text[50];
        int id=0;
        
        of::ofVerticesIterator<TTraits> iv(mesh);
        for(iv.initialize(); iv.notFinish(); ++iv){
           sprintf(text, "%d", &iv);
           Text(iv->getCoord(0), iv->getCoord(1), iv->getCoord(2), text, Color);
        }     
   };
   
   //--------------------------------------------------------------------- 
   void VerticesIds(const TColorRGBA &Color){
      VerticesIds(&this->meshHandler_, Color);
   }
   
 
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Edge(TVertex *vertex1, TVertex *vertex2, const TColorRGBA &Color, const float width=5){
      
      if(vertex1->getDimension()==2){
           Line(vertex1->getCoord(0), vertex1->getCoord(1), 0.0, 
                vertex2->getCoord(0), vertex2->getCoord(1), 0.0,
                Color, 
                width);          
      }else{
           Line(vertex1->getCoord(0), vertex1->getCoord(1), vertex1->getCoord(2),          
                vertex2->getCoord(0), vertex2->getCoord(1), vertex2->getCoord(2),
                Color,
                width);            
      }
   };
   
   //--------------------------------------------------------------------- 
   void Edge(TIds va, TIds vb, const TColorRGBA &Color, const float size=5){
      Edge(this->meshHandler_->getVertex(va), this->meshHandler_->getVertex(vb), Color, size);        
   };
   
   //---------------------------------------------------------------------
   void Edge(TCell *cell, const int index, const TColorRGBA &Color, const float width=1){
      
      assert(cell->getDimension()==2);
      Edge(this->meshHandler_->getVertex(cell->getVertexId((index+1)%3)), 
           this->meshHandler_->getVertex(cell->getVertexId((index+2)%3)),
           Color, 
           width
          ); 
   }
   
   
   //---------------------------------------------------------------------   
   void Edges(TMesh *mesh,const TColorRGBA &Color, const float width=1){

        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        
        if(ic->getDimension() == 2 )
          FacesWireframe(mesh, Color, width);
        
        
        if(ic->getDimension() == 3 )
          CellsWireframe(mesh, Color, width);
        
   };
   
   //---------------------------------------------------------------------   
   void Edges(const TColorRGBA &Color, const float width=1){
      Edges(&this->meshHandler_, Color, width);        
   };
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   
   void Faces(TMesh *mesh, const TColorRGBA &Color){
      
        
        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==2);
        for(; ic.notFinish(); ++ic){
           Face(*ic, Color);            
        }
   }
   
   //---------------------------------------------------------------------
   void Faces(const TColorRGBA &Color){
        Faces(&this->meshHandler_, Color);      
   }
   
   
   //---------------------------------------------------------------------
   void FacesWireframe(TMesh *mesh, const TColorRGBA &Color, const float width=1){
      
        
        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        if(ic->getDimension()==2){
           for(; ic.notFinish(); ++ic){
              FaceWireframe(*ic, Color, width);            
           }
        }else{
           for(; ic.notFinish(); ++ic){
              FaceWireframe(*ic, 0, Color, width);
              FaceWireframe(*ic, 1, Color, width);
              FaceWireframe(*ic, 2, Color, width);
              FaceWireframe(*ic, 3, Color, width);            
           }
        }
   }
   
   //---------------------------------------------------------------------
   void FacesWireframe(const TColorRGBA &Color, const float width=1){
      FacesWireframe(*this->meshHandler, Color, width);
   }
   //---------------------------------------------------------------------
   void FaceWireframe(TCell *cell, const TColorRGBA &Color, const float width=1){

      assert(cell->getDimension()==2);
      
      TPoint p1, p2, p3;
 
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId(0));
      p1.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));          
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId(1));
      p2.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId(2));
      p3.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
     
      TriangleWireframe(p1, p2, p3, Color, width);
      
   };
   
   
   //---------------------------------------------------------------------
   void FaceWireframe(int cell, const TColorRGBA &Color, const int width=1){
       FaceWireframe(this->meshHandler_->getCell(cell), Color, width);
   };
   
   //---------------------------------------------------------------------
   void FaceWireframe(TCell *cell, int index, const TColorRGBA &Color, const float width=1){

      assert(cell->getDimension()==3);
      
      TPoint p1, p2, p3;
      
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId((index+1)%4));
      p1.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));          
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId((index+2)%4));
      p2.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId((index+3)%4));
      p3.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      
      TriangleWireframe(p1, p2, p3, Color, width);
        
   };
   
   //---------------------------------------------------------------------
   void Face(TCell *cell, const TColorRGBA &Color){

      assert(cell->getDimension()==2);
      
      TPoint p1, p2, p3;
      
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId(0));
      p1.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));          
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId(1));
      p2.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      vertex_ = this->meshHandler_->getVertex(cell->getVertexId(2));
      p3.Set(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      
      Triangle(p1, p2, p3, Color);  
   };

   //---------------------------------------------------------------------
   void Face(int cellId, const TColorRGBA &Color){
      Face(this->meshHandler_->getCell(cellId), Color);
   }
   
   //---------------------------------------------------------------------
   void Face(TCell *cell, int index, const TColorRGBA &Color){

      assert(cell->getDimension()==3);
  
      Triangle(
         this->meshHandler_->getVertex(cell->getVertexId((index+1)%4)),
         this->meshHandler_->getVertex(cell->getVertexId((index+2)%4)),
         this->meshHandler_->getVertex(cell->getVertexId((index+3)%4)),
         Color
      );       
   };
   
      
   //---------------------------------------------------------------------   
   //---------------------------------------------------------------------
   template<typename _TIterator>
   void VertexStar(int vertexId, const TColorRGBA &Color){
      
      _TIterator isv(this->mesh_);
      
         for(isv.initialize(vertexId); isv.notFinish(); ++isv)
            Face(*isv, Color);
   }
   
   //---------------------------------------------------------------------
   template<typename _TIterator>
   void VertexStarWireframe(_TIterator isv, const TColorRGBA &Color, float width){
         for(; isv.notFinish(); ++isv)
            FaceWireframe(*isv, Color, width);        
   }
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void CellsIds(TMesh *mesh, const TColorRGBA &Color){
        
        char text[50];
        TPoint centroid;
        
        
        of::ofCellsIterator<TTraits> ic(mesh);
        ic.initialize();
        
        double den = (ic->getDimension()==2)?3:4;
        
        for(ic.initialize(); ic.notFinish(); ++ic){
          
           
           centroid.Zero();
          
           centroid[0] += mesh->getVertex(ic->getVertexId(0))->getCoord(0);
           centroid[1] += mesh->getVertex(ic->getVertexId(0))->getCoord(1);
           centroid[2] += mesh->getVertex(ic->getVertexId(0))->getCoord(2);
           
           centroid[0] += mesh->getVertex(ic->getVertexId(1))->getCoord(0);
           centroid[1] += mesh->getVertex(ic->getVertexId(1))->getCoord(1);
           centroid[2] += mesh->getVertex(ic->getVertexId(1))->getCoord(2);
           
           centroid[0] += mesh->getVertex(ic->getVertexId(2))->getCoord(0);
           centroid[1] += mesh->getVertex(ic->getVertexId(2))->getCoord(1);
           centroid[2] += mesh->getVertex(ic->getVertexId(2))->getCoord(2);
           
           if(ic->getDimension()==3){
              centroid[0] += mesh->getVertex(ic->getVertexId(3))->getCoord(0);
              centroid[1] += mesh->getVertex(ic->getVertexId(3))->getCoord(1);
              centroid[2] += mesh->getVertex(ic->getVertexId(3))->getCoord(2);
           }
           
           centroid[0] /= den;
           centroid[1] /= den;
           centroid[2] /= den;
           
           sprintf(text, "%d", &ic);
           Text(centroid, text, Color);
        }     
   };
   
   //---------------------------------------------------------------------
   void CellsIds(const TColorRGBA &Color){
      CellsIds(&this->meshHandler_, Color);
   }

   //---------------------------------------------------------------------
   void Cells(TMesh *mesh, const TColorRGBA &Color){
        
        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==3);
        for(; ic.notFinish(); ++ic){
           Cell(*ic, Color);            
        }
        
   };

   //---------------------------------------------------------------------
   void Cells(const TColorRGBA &Color){
      Cells(&this->meshHandler_, Color);
   }
   
   
   //---------------------------------------------------------------------
   void Cell(TCell *cell, const TColorRGBA &Color){
        assert(cell->getDimension()==3);
        Tetrahedron(this->meshHandler_->getVertex(cell->getVertexId(0)),
                    this->meshHandler_->getVertex(cell->getVertexId(1)),
                    this->meshHandler_->getVertex(cell->getVertexId(2)),
                    this->meshHandler_->getVertex(cell->getVertexId(3)),
                    Color);
          
   };
   
   //---------------------------------------------------------------------
   void CellsWireframe(TMesh *mesh, const TColorRGBA &Color, const float width=1){
        
        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==3);
        for(; ic.notFinish(); ++ic){
           CellWireframe(*ic, Color, width);            
        }
        
   };

   //---------------------------------------------------------------------
   void CellsWireframe(const TColorRGBA &Color, const float width=1){
      CellsWireframe(&this->meshHandler_, Color, width);
   }
   
   
   //---------------------------------------------------------------------
   void CellWireframe(TCell *cell, const TColorRGBA &Color, const float width=1){
        assert(cell->getDimension()==3);
        TetrahedronWireframe(this->meshHandler_->getVertex(cell->getVertexId(0)),
                             this->meshHandler_->getVertex(cell->getVertexId(1)),
                             this->meshHandler_->getVertex(cell->getVertexId(2)),
                             this->meshHandler_->getVertex(cell->getVertexId(3)),
                             Color, width);
          
   };
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Boundaries(TMesh *mesh,const TColorRGBA &Color, const int width=1){

        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        if(ic->getDimension()==2){
           for(ic.initialize(); ic.notFinish(); ++ic){
            
              if(ic->getMateId(0)==-1)
                  Edge(*ic, 0, Color, width);
              if(ic->getMateId(1)==-1)
                  Edge(*ic, 1, Color, width);
              if(ic->getMateId(2)==-1)
                  Edge(*ic, 2, Color, width);
           }
       }else{
           for(ic.initialize(); ic.notFinish(); ++ic){
              if(ic->getMateId(0)==-1)
                  Face(*ic, 0, Color);
              if(ic->getMateId(1)==-1)
                  Face(*ic, 1, Color);
              if(ic->getMateId(2)==-1)
                  Face(*ic, 2, Color);
              if(ic->getMateId(3)==-1)
                  Face(*ic, 3, Color);
           }
       }
        
   };
   
      //---------------------------------------------------------------------
   void Boundaries(const TColorRGBA &Color, const int width=1){
      Boundaries(&this->meshHandler_, Color, width);       
   };
   
   //---------------------------------------------------------------------
   void BoundariesWireframe(TMesh *mesh,const TColorRGBA &Color, const int width=1){

        of::ofCellsIterator<TTraits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==3);
        for(ic.initialize(); ic.notFinish(); ++ic){
           if(ic->getMateId(0)==-1)
               FaceWireframe(*ic, 0, Color, width);
           if(ic->getMateId(1)==-1)
               FaceWireframe(*ic, 1, Color, width);
           if(ic->getMateId(2)==-1)
               FaceWireframe(*ic, 2, Color, width);
           if(ic->getMateId(3)==-1)
               FaceWireframe(*ic, 3, Color, width);
        }
         
   };
   
   //---------------------------------------------------------------------
   void BoundariesWireframe(const TColorRGBA &Color, const int width=1){
      BoundariesWireframe(&this->meshHandler_, Color, width);
   };
   
};


#endif /*PRINTOF_HPP_*/


