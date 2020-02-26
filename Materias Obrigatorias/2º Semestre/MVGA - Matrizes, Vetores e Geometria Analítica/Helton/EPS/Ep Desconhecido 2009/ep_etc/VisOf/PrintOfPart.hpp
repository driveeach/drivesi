#ifndef PRINTOF_PART_HPP_

#define PRINTOF_PART_HPP_

#include "PrintOf.hpp"


using namespace std;

template<class _TMesh, class _TParticle>
class PrintOfPart: public PrintOf<_TMesh>
{
public:

   typedef PrintOf<_TMesh>           TPrintOf;
	
   typedef _TMesh                    TMesh;
   typedef Handler<TMesh>            TMeshHandler;
   typedef typename TMesh::traits    TTraits;
   
   typedef typename TMesh::sVertex   TVertex;
   typedef typename TMesh::sCell     TCell;
   
   typedef _TParticle                TParticle;
   
   typedef Point                     TPoint;
   typedef TPoint                    TVector;
   
   typedef Point3DUtils<TPoint>      TP3DU;
   typedef float                     TReal;
         

   using TPrintOf::Point3D;
   using TPrintOf::TriangleWireframe;
   using TPrintOf::Line;   
     
public:   
   
   PrintOfPart(TMesh *mesh_i):TPrintOf(mesh_i){
      
   };
   
public:   


   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void TriangleData(TVertex *v1, TVertex *v2, TVertex *v3, const TColorRGBA &Color){
      
      TPoint p1,p2,p3;
     
      v1->getParticle()->GetRealPosition(p1);
      v2->getParticle()->GetRealPosition(p2);
      v3->getParticle()->GetRealPosition(p3);
      
      TPrintOf::TPrintBase::PrintTriangle(p1,p2,p3,Color);   
   }
   
   //---------------------------------------------------------------------
   void TriangleWireframeData(TVertex *v1, TVertex *v2, TVertex *v3, const TColorRGBA &Color, const int width=1){
      TPoint p1,p2,p3;
      
      v1->getParticle()->GetRealPosition(p1);
      v2->getParticle()->GetRealPosition(p2);
      v3->getParticle()->GetRealPosition(p3);
      
      TriangleWireframe(p1,p2,p3,Color, width);   
   }
   
      
   //---------------------------------------------------------------------
   //--------------------------------------------------------------------- 
   void VertexData(const TVertex *vertex,const TColorRGBA &Color, const int size=5){
        Point3D(vertex->getParticle()->Position(), Color, size);
   };
   
   
   //--------------------------------------------------------------------- 
   void VerticesData(TMesh *mesh,const TColorRGBA &Color, const int size=5){

        of::ofVerticesIterator<typename TMesh::traits>  iv(this->mesh_);
        for(iv.initialize(); iv.notFinish(); ++iv){
           VertexData(*iv, Color, size);
        }
        
   };
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void EdgeData(TVertex *vertex1, TVertex *vertex2, const TColorRGBA &Color, const int width=5){
      TPoint p1, p2;
      vertex1->getParticle()->GetRealPosition(p1);
      vertex2->getParticle()->GetRealPosition(p2);
      Line(p1, p2, Color, width);
   };
   
   //---------------------------------------------------------------------
   void EdgeData(TParticle *particle1, TParticle *particle2, const TColorRGBA &Color, const int width=5){
      TPoint p1, p2;
      particle1->GetRealPosition(p1);
      particle2->GetRealPosition(p2);
      Line(p1, p2, Color, width);
   };
   
   //---------------------------------------------------------------------
   void EdgeData(TCell *cell, const int index, const TColorRGBA &Color, const int width=1){
      
      assert(cell->getDimension()==2);
      EdgeData(
         this->mesh_->getVertex(cell->getVertexId((index+1)%3)), 
         this->mesh_->getVertex(cell->getVertexId((index+2)%3)),
         Color, 
         width
      ); 
   }
   
   //---------------------------------------------------------------------   
   void EdgesData(TMesh *mesh,const TColorRGBA &Color, const int size=5){

        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        
        for(ic.initialize(); ic.notFinish(); ++ic){
           FaceWirefameData(*ic, Color, size);
        }
        
   };
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   
   void FacesData(TMesh *mesh, const TColorRGBA &Color, const int width=1){
      
        
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==2);
        for(; ic.notFinish(); ++ic){
           FaceData(*ic, Color);            
        }
   }
   
   void FacesWireframeData(TMesh *mesh, const TColorRGBA &Color, const int width=1){
      
        
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        if(ic->getDimension()==2){
           for(; ic.notFinish(); ++ic){
              FaceWireframeData(*ic, Color, width);            
           }
        }else{
           for(; ic.notFinish(); ++ic){
              CellWireframeData(*ic, Color, width);            
           }
        }
   }
   
   void FaceWireframeData(TCell *cell, const TColorRGBA &Color, const int width=1){

      assert(cell->getDimension()==2);

      TriangleData(
         this->mesh_->getVertex(cell->getVertexId(0)),
         this->mesh_->getVertex(cell->getVertexId(1)),
         this->mesh_->getVertex(cell->getVertexId(2)),
         Color, width
      );
   };
   
   //---------------------------------------------------------------------
   void FaceWireframeData(TCell *cell, int index, const TColorRGBA &Color, const int width=1){

      assert(cell->getDimension()==3);

      TriangleWireFrameData(
         this->mesh_->getVertex(cell->getVertexId(index+1)%4),
         this->mesh_->getVertex(cell->getVertexId(index+2)%4),
         this->mesh_->getVertex(cell->getVertexId(index+3)%4),
         Color, width
      );
   };
   
   //---------------------------------------------------------------------
   void FaceData(TCell *cell, const TColorRGBA &Color){
 
      TriangleData(
         this->mesh_->getVertex(cell->getVertexId(0)),
         this->mesh_->getVertex(cell->getVertexId(1)),
         this->mesh_->getVertex(cell->getVertexId(2)),
         Color
      );
   };

   //---------------------------------------------------------------------
   void FaceData(TCell *cell, int index, const TColorRGBA &Color){

      assert(cell->getDimension()==3);
  
      TriangleData(this->mesh_->getVertex(cell->getVertexId((index+1)%4)),
                   this->mesh_->getVertex(cell->getVertexId((index+2)%4)),
                   this->mesh_->getVertex(cell->getVertexId((index+3)%4)),
                   Color);
        
   };
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void CellsWireframeData(TMesh *mesh, const TColorRGBA &Color, const int width=1){
        
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        assert(ic->getDimension()==3);
        for(; ic.notFinish(); ++ic){
           CellWireframeData(*ic, Color, width);            
        }
        
   };
   
   //---------------------------------------------------------------------
   void CellWireframeData(TCell *cell, const TColorRGBA &Color, const int width=1){
        assert(cell->getDimension()==3);
        FaceWireframeData(cell, 0, Color, width);
        FaceWireframeData(cell, 1, Color, width);
        FaceWireframeData(cell, 2, Color, width);
        FaceWireframeData(cell, 3, Color, width);           
   };
   
   
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   
   void BoundariesData(TMesh *mesh,const TColorRGBA &Color, const int width=1){

        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();
        if(ic->getDimension()==2){
           for(ic.initialize(); ic.notFinish(); ++ic){
            
              if(ic->getMateId(0)==-1)
                  EdgeData(*ic, 0, Color, width);
              if(ic->getMateId(1)==-1)
                  EdgeData(*ic, 1, Color, width);
              if(ic->getMateId(2)==-1)
                  EdgeData(*ic, 2, Color, width);
           }
       }else{
           for(ic.initialize(); ic.notFinish(); ++ic){
              if(ic->getMateId(0)==-1)
                  FaceData(*ic, 0, Color);
              if(ic->getMateId(1)==-1)
                  FaceData(*ic, 1, Color);
              if(ic->getMateId(2)==-1)
                  FaceData(*ic, 2, Color);
              if(ic->getMateId(3)==-1)
                  FaceData(*ic, 3, Color);
           }
       }
        
   };
   
   //---------------------------------------------------------------------
   void BoundariesWireframeData(TMesh *mesh,const TColorRGBA &Color, const int width=1){
   	
        of::ofCellsIterator<typename TMesh::traits>  ic(mesh);
        ic.initialize();

        if(ic->getDimension()==2)
           BoundariesData();
        else{ 

           for(ic.initialize(); ic.notFinish(); ++ic){
              if(ic->getMateId(0)==-1)
                  FaceWireframeData(*ic, 0, Color, width);
              if(ic->getMateId(1)==-1)
                  FaceWireframeData(*ic, 1, Color, width);
              if(ic->getMateId(2)==-1)
                  FaceWireframeData(*ic, 2, Color, width);
              if(ic->getMateId(3)==-1)
                  FaceWireframeData(*ic, 3, Color, width);
           }
        }        
   };
   
   //--------------------------------------------------------------------
   //--------------------------------------------------------------------
   void
   Particle(TParticle *p, const TColorRGBA &Color, const int size=5){
      Point3D(p->Position(), Color, size);
   }
      
   void
   ParticleData(TParticle *p, const TColorRGBA &Color, const int size=5){
      TPoint pt;
      p->GetRealPosition(pt);
      Point3D(pt, Color, size);
   }   
   
   //------------------------------------------------------------------------------
   void
   TextId(TParticle  *piParticle, const TColorRGB &Color){
      
      glColor3f(Color.R, Color.G, Color.B);
      typename TParticle::TPoint p;
      piParticle->GetRealPosition(p);
      glRasterPos3f(p[0], p[1], p[2]);
      char text[50];
      sprintf(text, "Id=%d", piParticle->Vertex()->getId()); 
      
      for (unsigned int i = 0; i < strlen(text); i++)
         glutBitmapCharacter (GLUT_BITMAP_HELVETICA_12, text[i]);
   
   }
};


#endif /*PRINTOF_HPP_*/
