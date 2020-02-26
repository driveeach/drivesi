#ifndef PRINTOF_HPP_
#define PRINTOF_HPP_

#include "PrintBase.hpp"
#include <memory>

#include "../of/of.h"

using namespace std;

template<class _TMesh>
class PrintOf : public PrintBase
{
public:
   
   typedef PrintBase                 TPrintBase;
	
   typedef _TMesh   TMesh;
   typedef typename TMesh::traits    TTraits;
   
   typedef typename TTraits::ids     TIds;
   
   typedef typename TMesh::sVertex   TVertex;
   typedef typename TMesh::sCell     TCell;
   
   typedef Point                     TPoint;
   typedef TPoint                    TVector;
   
   typedef Point3DUtils<TPoint>      TP3DU;
   typedef float                     TReal;
   
public:
  
   using TPrintBase::Text;
   using TPrintBase::Point3D;
   using TPrintBase::TriangleWireframe;
   using TPrintBase::Triangle; 
   using TPrintBase::Line;
   
   
public:

   TMesh  *mesh_;   
   
   TVertex *vertex_;
   TVertex *vertex1_;
   TVertex *vertex2_;
   TVertex *vertex3_;
   
   std::auto_ptr<of::ofVerticesIterator<TTraits> >    iv;
   std::auto_ptr<of::ofCellsIterator<TTraits> >       ic;   
public:   
   
   PrintOf(TMesh *mesh_i)
      : mesh_(mesh_i){
   };
      
     
   void
   InitializeIterators(){
      
      assert(this->mesh_i);
      
      iv  = std::auto_ptr<of::ofVerticesIterator<TTraits> >(mesh_);   
      ic  = std::auto_ptr<of::ofCellsIterator<TTraits> >(mesh_);
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
   void Vertex(TIds id, const TColorRGBA &Color, const int size=5){
      Vertex(this->mesh_->getVertex(id), Color, size);        
   };
   
   
   //--------------------------------------------------------------------- 
   void Vertices(TMesh *mesh,const TColorRGBA &Color, const int size=5){

        of::ofVerticesIterator<TTraits> iv(mesh);
        for(iv.initialize(); iv.notFinish(); ++iv){
           Vertex(*iv, Color, size);
        }  
   };
   
   //--------------------------------------------------------------------- 
   void Vertices(const TColorRGBA &Color, const int size=5){
        Vertices(this->mesh_, Color, size);
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
      VerticesIds(this->mesh_, Color);
   }
   
 
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Edge(TVertex *vertex1, TVertex *vertex2, const TColorRGBA &Color, const int width=5){

      this->setAlpha(Color.A);  
      
      // Set current drawing color
      //        R   G    B
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glLineWidth(width);
      
      glBegin(GL_LINES);
      
      if(vertex1->getDimension()==2){
           glVertex3f(vertex1->getCoord(0), vertex1->getCoord(1), 0.0);          
           glVertex3f(vertex2->getCoord(0), vertex2->getCoord(1), 0.0);          
      }else{
           glVertex3f(vertex1->getCoord(0), vertex1->getCoord(1), vertex1->getCoord(2));          
           glVertex3f(vertex2->getCoord(0), vertex2->getCoord(1), vertex2->getCoord(2));            
      }
      
      glEnd();
      
      glLineWidth(1);
   };
   
   //--------------------------------------------------------------------- 
   void Edge(TIds va, TIds vb, const TColorRGBA &Color, const int size=5){
      Edge(this->mesh_->getVertex(va), this->mesh_->getVertex(vb), Color, size);        
   };
   
   //---------------------------------------------------------------------
   void Edge(TCell *cell, const int index, const TColorRGBA &Color, const int width=1){
      
      assert(cell->getDimension()==2);
      Edge(this->mesh_->getVertex(cell->getVertexId((index+1)%3)), 
           this->mesh_->getVertex(cell->getVertexId((index+2)%3)),
           Color, 
           width
          ); 
   }
   
   
   //---------------------------------------------------------------------   
   void Edges(TMesh *mesh,const TColorRGBA &Color, const int width=1){

        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        
        if(ic->getDimension() == 2 )
          FacesWireframe(mesh, Color, width);
        
        if(ic->getDimension() == 3 )
          CellsWireframe(mesh, Color, width);
        
   };
   
   //---------------------------------------------------------------------   
   void Edges(const TColorRGBA &Color, const int width=1){
      Edges(this->mesh_, Color, width);        
   };
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   
   void Faces(TMesh *mesh, const TColorRGBA &Color){
      
        
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==2);
        for(; ic.notFinish(); ++ic){
           Face(*ic, Color);            
        }
   }
   
   //---------------------------------------------------------------------
   void Faces(const TColorRGBA &Color){
        Faces(this->mesh_, Color);      
   }
   
   //---------------------------------------------------------------------
   void FacesWireframe(TMesh *mesh, const TColorRGBA &Color, const int width=1){
      
        
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        if(ic->getDimension()==2){
           for(; ic.notFinish(); ++ic){
              FaceWireframe(*ic, Color, width);            
           }
        }else{
           for(; ic.notFinish(); ++ic){
              CellWireframe(*ic, Color, width);            
           }
        }
   }
   
   void FaceWireframe(TCell *cell, const TColorRGBA &Color, const int width=1){

      assert(cell->getDimension()==2);
 
      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glLineWidth(width);
      
      glBegin(GL_LINE_LOOP);
        vertex_ = this->mesh_->getVertex(cell->getVertexId(0));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));          
        vertex_ = this->mesh_->getVertex(cell->getVertexId(1));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
        vertex_ = this->mesh_->getVertex(cell->getVertexId(2));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      glEnd();
      
      glLineWidth(1);
        
   };
   
   
   //---------------------------------------------------------------------
   void FaceWireframe(int cell, const TColorRGBA &Color, const int width=1){
       FaceWireframe(this->mesh_->getCell(cell), Color, width);
   };
   
   //---------------------------------------------------------------------
   void FaceWireframe(TCell *cell, int index, const TColorRGBA &Color, const int width=1){

      assert(cell->getDimension()==3);
 
      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glLineWidth(width);
      
      glBegin(GL_LINE_LOOP);
        vertex_ = this->mesh_->getVertex(cell->getVertexId((index+1)%4));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));          
        vertex_ = this->mesh_->getVertex(cell->getVertexId((index+2)%4));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
        vertex_ = this->mesh_->getVertex(cell->getVertexId((index+3)%4));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      glEnd();
      
      glLineWidth(1);
        
   };
   
   //---------------------------------------------------------------------
   void Face(TCell *cell, const TColorRGBA &Color){

      assert(cell->getDimension()==2);
 
      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glBegin(GL_POLYGON);
        vertex_ = this->mesh_->getVertex(cell->getVertexId(0));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));          
        vertex_ = this->mesh_->getVertex(cell->getVertexId(1));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
        vertex_ = this->mesh_->getVertex(cell->getVertexId(2));
        glVertex3f(vertex_->getCoord(0), vertex_->getCoord(1), vertex_->getCoord(2));
      glEnd();
      
      glLineWidth(1);
        
   };

   //---------------------------------------------------------------------
   void Face(int cellId, const TColorRGBA &Color){
      Face(this->mesh_->getCell(cellId), Color);
   }
   
   //---------------------------------------------------------------------
   void Face(TCell *cell, int index, const TColorRGBA &Color){

      assert(cell->getDimension()==3);
  
      Triangle(this->mesh_->getVertex(cell->getVertexId((index+1)%4)),
               this->mesh_->getVertex(cell->getVertexId((index+2)%4)),
               this->mesh_->getVertex(cell->getVertexId((index+3)%4)),
               Color);       
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
      CellsIds(this->mesh_, Color);
   }
   
   //---------------------------------------------------------------------
   void CellsWireframe(TMesh *mesh, const TColorRGBA &Color, const int width=1){
        
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==3);
        for(; ic.notFinish(); ++ic){
           CellWireframe(*ic, Color, width);            
        }
        
   };
   
   
   //---------------------------------------------------------------------
   void CellWireframe(TCell *cell, const TColorRGBA &Color, const int width=1){
        assert(cell->getDimension()==3);
        FaceWireframe(cell, 0, Color, width);
        FaceWireframe(cell, 1, Color, width);
        FaceWireframe(cell, 2, Color, width);
        FaceWireframe(cell, 3, Color, width);           
   };
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Boundaries(TMesh *mesh,const TColorRGBA &Color, const int width=1){

        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
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
      Boundaries(this->mesh_, Color, width);       
   };
   
   //---------------------------------------------------------------------
   void BoundariesWireframe(TMesh *mesh,const TColorRGBA &Color, const int width=1){

        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
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
      BoundariesWireframe(this->mesh_, Color, width);
   };
   
};


#endif /*PRINTOF_HPP_*/


