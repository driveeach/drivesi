#ifndef CMDSHOWMESH_HPP_
#define CMDSHOWMESH_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfMesh : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint              TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
public:

   CmdShowOfMesh(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i){
         
         this->helpMessage_ = "Mesh";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         if(this->print_->meshHandler_->getCell(0)->getDimension()==2)
            this->print_->Faces(grey);
         else  
            this->print_->Cells(grey);
      }
   };
   
};

#endif /*CMDSHOWMESH_HPP_*/
