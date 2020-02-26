#ifndef CMDWRITE_ORIENT_SUB_MESHES_HPP_
#define CMDWRITE_ORIENT_SUB_MESHES_HPP_

#include "CmdShowOf.hpp"

#include "OrientMesh.hpp"

#include "ofVtkWriterSubMesh.h"
#include "ofVtkWriterOriented.h"
#include "ofVtkReader.h"

template<typename _TPrint>
class CmdWriteOrientSubMeshes : public CmdShowOf<_TPrint>{
public:
   typedef  _TPrint              TPrint; 
   typedef CmdShowOf<TPrint>     TCmdShow;
   
   typedef typename TPrint::TMesh::traits   TTraits;
   typedef typename TPrint::TMeshHandler    TMeshHandler;
   typedef typename TMesh::Handler::TType   TMesh;
   
   typedef OrientMesh<TMesh>                TOrientMesh;
   
public:

   std::auto_ptr<TOrientMesh>          orientMesh_;  
   TMeshHandler                       &meshHandler_;
   
public:

   CmdWriteOrientSubMeshes(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCmdShow(print_i, nModes_i, mode_i), meshHandler_(this->print_->meshHandler_){
         
      this->helpMessage_ = "write orient subMeshes";
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
      ofVtkReader<TTraits>             reader;
      
      
      std::list<TOrientMesh::TCells>::iterator ishell;
      std::vector<int>                         orientedShell;
      std::vector<int>::iterator               icell;
      
      char text[50];
      string filename;
      string id;
      
      int cont = 0;
      for(ishell = this->orientMesh_->shells.begin(); ishell != this->orientMesh_->shells.end(); ++ishell){
         
         orientedShell.clear();
         
         for(icell = (*ishell).begin(); icell != (*ishell).end(); ++icell)
            if(orientMesh_->nonOrientedCells_.find(*icell) == orientMesh_->nonOrientedCells_.end())
               orientedShell.push_back(*icell);
         
         writer.SetCellsToWrite(&orientedShell);
         
         sprintf(text, "%d", cont++);
         id = text;   
         filename = "OrientedShell"+id+".vtk"; 
         
         writer.write(&this->meshHandler_, filename.c_str());
         
         
         cout<<"oriented subMesh writen"<<endl;
         
      }
            
//      this->print_->mesh_ = this->mesh_;
      
   }
   
};

#endif /*CMDSHOWMESH_HPP_*/
