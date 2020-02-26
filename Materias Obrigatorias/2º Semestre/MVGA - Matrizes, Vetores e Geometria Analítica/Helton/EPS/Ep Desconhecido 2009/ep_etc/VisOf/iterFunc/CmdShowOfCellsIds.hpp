#ifndef CMDSHOW_CELLSIDS_HPP_
#define CMDSHOW_CELLSIDS_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfCellsIds : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                   TPrint; 
   typedef CommandShowOf<_TPrint>     TCommand;
   
public:

   CmdShowOfCellsIds(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i){
         
      this->helpMessage_ = "cells' ids";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         this->print_->CellsIds(orange);  
      }
   };

   
};

#endif /*CMDSHOWMESH_HPP_*/
