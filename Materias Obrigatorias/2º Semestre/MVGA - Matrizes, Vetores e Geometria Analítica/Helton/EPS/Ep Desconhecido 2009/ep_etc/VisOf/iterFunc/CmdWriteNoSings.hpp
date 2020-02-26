#ifndef CMDWRITE_NO_SINGS_HPP_
#define CMDWRITE_NO_SINGS_HPP_

#include "CommandShowOf.hpp"

#include "BreakSingularities.hpp"

#include "ofVtkWriterNoSings.h"

template<typename _TPrint>
class CmdWriteNoSings : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                  TPrint; 
   typedef CommandShowOf<TPrint>     TCmdShow;
   
   typedef typename TPrint::TMesh::traits       TTraits;
   typedef typename TPrint::TMeshHandler        TMeshHandler;
   
   typedef BreakSingularities<typename TPrint::TMeshHandler::TType>    TBreakSings;
   
public:

   TMeshHandler                         &meshHandler_;
   
   std::auto_ptr<TBreakSings>            breakSings;
   
public:

   CmdWriteNoSings(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCmdShow(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "write no singularities";
      
      
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){

         ofVtkWriterNoSings<TTraits> writer;
         writer.write(&this->meshHandler_, "meshNoSings.vtk");
         
         delete &this->meshHandler_;
         this->meshHandler_.Set(new TMesh());
         
         
         ofVtkReader<TTraits> reader;
         reader.read(&this->meshHandler_, "meshNoSings.vtk"); 
         
         this->print_->meshHandler_ = this->meshHandler_;
         
         this->currentMode_ = 0;
         
      }
   }; 
   
};

#endif /*CMDSHOWMESH_HPP_*/
