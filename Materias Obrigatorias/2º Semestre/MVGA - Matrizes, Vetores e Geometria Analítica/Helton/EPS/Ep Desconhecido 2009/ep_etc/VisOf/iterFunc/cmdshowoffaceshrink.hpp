#ifndef CMDSHOW_SHIRINK_FACE_HPP_
#define CMDSHOW_SHIRINK_FACE_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfFaceShrink : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                 TPrint; 
   typedef CommandShowOf<TPrint>    TCommand;
   
   typedef typename TPrint::TTraits    TTraits;
   typedef typename TPrint::TMeshHandler    TMeshHandler;
   
public:
   TMeshHandler                      &meshHandler_;      
   float                              step_;
   
public:

   CmdShowOfFaceShrink(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "shirink face";
      this->step_ = 0.1;
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         this->print_->SetTriaScaleFactor(this->print_->GetTriaScaleFactor()-this->step_);
         this->currentMode_=0;
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
