#ifndef CMDSHOW_CELL_SHRINK_HPP_
#define CMDSHOW_CELL_SHRINK_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfCellShrink : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                 TPrint; 
   typedef CommandShowOf<TPrint>    TCommand;
   
   typedef typename TPrint::TTraits    TTraits;
   typedef typename TPrint::TMeshHandler    TMeshHandler;
   
public:
   TMeshHandler                       &meshHandler_;      
   float                               step_;
   
public:

   CmdShowOfCellShrink(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "increase cell";
      this->step_ = 0.1;
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         this->print_->SetTetraScaleFactor(this->print_->GetTetraScaleFactor()-this->step_);
         this->currentMode_=0;
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
