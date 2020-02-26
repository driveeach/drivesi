#ifndef CMDSHOW_VERTICESIDS_HPP_
#define CMDSHOW_VERTICESIDS_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfVerticesIds : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                  TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
public:

   CmdShowOfVerticesIds(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i){
         
      this->helpMessage_ = "vertices' ids";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         this->print_->VerticesIds(blue);  
      }
   };

   
};

#endif /*CMDSHOWMESH_HPP_*/
