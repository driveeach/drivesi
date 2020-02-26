#ifndef CMDWRITE_ORIENT_SUB_MESH_HPP_
#define CMDWRITE_ORIENT_SUB_MESH_HPP_

#include "CommandShowOf.hpp"

#include "OrientMesh.hpp"

#include "ofVtkWriterSubMesh.h"
#include "ofVtkWriterOriented.h"
#include "ofVtkReader.h"

template<typename _TPrint>
class CmdWriteOrientSubMesh : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                  TPrint; 
   typedef CommandShowOf<TPrint>     TCmdShow;
   
   typedef typename TPrint::TMesh::traits                   TTraits;
   typedef typename TPrint::TMeshHandler                    TMeshHandler;
   
   typedef OrientMesh<typename TMeshHandler::TType>         TOrientMesh;
   
public:

   std::auto_ptr<TOrientMesh>          orientMesh_;  
   TMeshHandler                       &meshHandler_;
   
public:

   CmdWriteOrientSubMesh(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCmdShow(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "write orient subMesh";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         
         this->orientMesh_ = std::auto_ptr<TOrientMesh>(new TOrientMesh);
         
         orientMesh_->Orient(&this->meshHandler_);
         
         WriteOriented();
         
         this->currentMode_ = 0;
      }
   }; 

   
   void
   WriteOriented(){
      
      ofVtkWriterSubMesh<TTraits>      writer;
      ofVtkWriterOriented<TTraits>     writerOriented;
      ofVtkReader<TTraits>             reader;
      
      
      std::list<TOrientMesh::TCells>::iterator ishell;
      std::vector<int>                         orientedShell;
      std::vector<int>::iterator               icell;
      
            
      orientedShell.clear();
      

      for(ishell = this->orientMesh_->shells.begin(); ishell != this->orientMesh_->shells.end(); ++ishell){
      
         if(ishell->size() > 30){            
            for(icell = (*ishell).begin(); icell != (*ishell).end(); ++icell)
//               if(orientMesh_->nonOrientedCells_.find(*icell) == orientMesh_->nonOrientedCells_.end())
                  orientedShell.push_back(*icell);
         }
      }

      
      writer.SetCellsToWrite(&orientedShell);
      writer.write(&this->meshHandler_, "OrientedMesh.vtk");
      
      delete &this->meshHandler_;
      this->meshHandler_.Set(new TMesh());
      
      reader.read(&this->meshHandler_, "OrientedMesh.vtk"); 
      
//      this->print_->mesh_ = this->mesh_;
      
      ofVtkWriterOriented<TTraits> writerO;
      writerO.write(&this->meshHandler_, "OrientedMesh.vtk");
      
      delete &this->meshHandler_;
      this->meshHandler_.Set(new TMesh());
      
      reader.read(&this->meshHandler_, "OrientedMesh.vtk"); 
      
//      this->print_->mesh_ = this->mesh_;
      
      cout<<"oriented mesh writen"<<endl;
      
//      this->print_->meshHandler_.Set(this->mesh_);
      
   }
   
};

#endif /*CMDSHOWMESH_HPP_*/
