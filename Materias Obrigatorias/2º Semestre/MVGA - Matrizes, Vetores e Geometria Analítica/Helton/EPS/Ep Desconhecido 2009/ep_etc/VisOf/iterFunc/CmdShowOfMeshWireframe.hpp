#ifndef CMDSHOWMESHWIREFRAME_HPP_
#define CMDSHOWMESHWIREFRAME_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfMeshWireframe : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint              TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
public:

   CmdShowOfMeshWireframe(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i){
         
         this->helpMessage_ = "Mesh wireframe";
   }
   
public:

   virtual
   void 
   Execute(){
      if(this->currentMode_==1){
         
         if(this->print_->meshHandler_->getCell(0)->getDimension()==2)
            this->print_->Edges(dgrey,1);
         else  
            this->print_->CellsWireframe(dgrey);
      }
   };
   
};

#endif /*CMDSHOWMESH_HPP_*/
