#ifndef CMDSHOW_XXX _HPP_
#define CMDSHOW_XXX _HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOf XXX : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                  TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
   typedef typename TPrint::TMesh::traits    TTraits;
   typedef typename TPrint::TMeshHandler     TMeshHandler;
   
public:
   TMeshHandler                        &meshHandler_;      
   
public:

   CmdShowOf XXX(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = XXX;
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         XXX
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
