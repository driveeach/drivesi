#ifndef CMDSHOW_ORIENT_MESH_HPP_
#define CMDSHOW_ORIENT_MESH_HPP_

#include "CommandShowOf.hpp"

#include "OrientMesh.hpp"

template<typename _TPrint>
class CmdShowOfOrientMesh : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint              TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
   typedef typename TPrint::TMesh::traits                   TTraits;
   typedef typename TPrint::TMeshHandler                    TMeshHandler;
   
   typedef OrientMesh<typename TMeshHandler::TType>         TOrientMesh;
   
public:

   std::auto_ptr<TOrientMesh>               orientMesh_;  
   TMeshHandler                            &meshHandler_;
   
public:

   CmdShowOfOrientMesh(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "orient Mesh";
      
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
      
         this->orientMesh_ = std::auto_ptr<TOrientMesh>(new TOrientMesh);
      
         orientMesh_->Orient(&this->meshHandler_);   
         
         typename TTraits::sCell      *cell;
         of::ofVertexStarIteratorSurface<TTraits> isv(&this->meshHandler_);
         
         TOrientMesh::TNonOrientedCells::iterator inoc;
         
          for(inoc = this->orientMesh_->nonOrientedCells_.begin(); 
             inoc != this->orientMesh_->nonOrientedCells_.end();
             ++inoc){
             
             cell = this->meshHandler_->getCell(*inoc);
             
             this->print_->Face(*inoc, lred);
             this->print_->FaceWireframe(*inoc, red, 4);
         }
         
         for(inoc = this->orientMesh_->nonOrientedCells_.begin(); 
             inoc != this->orientMesh_->nonOrientedCells_.end();
             ++inoc){
             
             cell = this->meshHandler_->getCell(*inoc);

             
             for(int ii=0; ii<3; ++ii){
                
                for(isv.initialize(cell->getVertexId(ii)); isv.notFinish(); ++isv){
                
                     this->print_->Face(*isv, blue);
                     this->print_->FaceWireframe(*isv, lblue, 2);
                }
             }

         }
      }
   }; 

   

   
};

#endif /*CMDSHOWMESH_HPP_*/
