#ifndef CMDSHOW_EDGE_INCREASE_HPP_
#define CMDSHOW_EDGE_INCREASE_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfEdgeIncrease : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                 TPrint; 
   typedef CommandShowOf<TPrint>    TCommand;
   
   typedef typename TPrint::TTraits    TTraits;
   typedef typename TPrint::TMeshHandler     TMeshHandler;
   
public:
   TMeshHandler                      &meshHandler_;      
   float                              step_;
   
public:

   CmdShowOfEdgeIncrease(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "increase edge";
      this->step_ = 0.1;
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         this->print_->SetLineScaleFactor(this->print_->GetLineScaleFactor()+this->step_);
         this->currentMode_=0;
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
