#ifndef CMDSHOW_BOUNDARY_HPP_
#define CMDSHOW_BOUNDARY_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfBoundary : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                    TPrint; 
   typedef CommandShowOf<_TPrint>      TCommand;
   
public:

   CmdShowOfBoundary(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i){
         
      this->helpMessage_ = "Boundary";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         if(this->print_->meshHandler_->getCell(0)->getDimension()==2){
            this->print_->Boundaries(magenta,3);
         }else{
            this->print_->BoundariesWireframe(magenta,3);
         }
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
