#ifndef CMDSHOW_VERTEX_STAR_HPP_
#define CMDSHOW_VERTEX_STAR_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfVertexStar : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                  TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
   typedef typename TPrint::TMesh::traits    TTraits;
   typedef typename TPrint::TMeshHandler     TMeshHandler;
   
public:
   TMeshHandler                        &meshHandler_;      
   
public:

   CmdShowOfVertexStar(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "show star of vertex";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         of::ofVertexStarIteratorSurface<TTraits> ivs(&this->meshHandler_);
         for(ivs.initialize(5106); ivs.notFinish(); ++ivs){
            this->print_->Face(*ivs, magenta);
            this->print_->FaceWireframe(*ivs, cyan, 3);
         }
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
