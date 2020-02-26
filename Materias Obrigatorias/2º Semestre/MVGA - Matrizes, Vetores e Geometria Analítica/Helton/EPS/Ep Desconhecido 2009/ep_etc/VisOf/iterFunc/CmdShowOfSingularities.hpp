#ifndef CMDSHOW_SINGULARITIES_HPP_
#define CMDSHOW_SINGULARITIES_HPP_



#include "CommandShowOf.hpp"
#include "BreakSingularities.hpp"


template<typename _TPrint>
class CmdShowOfSingularities : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                                      TPrint; 
   
   
   typedef CommandShowOf<TPrint>                                        TCommand;
   typedef BreakSingularities<typename TPrint::TMeshHandler::TType>     TBreakSings;
  
               
public:

    std::auto_ptr<TBreakSings>            breakSings;
    typename TPrint::TMeshHandler         &meshHandler_;   
   
public:

   CmdShowOfSingularities(TPrint *print_i, int nModes_i=1, int mode_i=0)
      :TCommand(print_i, nModes_i, mode_i), 
       meshHandler_(print_i->meshHandler_){
         
      this->helpMessage_ = "Singularities";
   }
   
   
   
public:

   virtual
   void
   Execute(){
      
      if(this->currentMode_ == 1){
         
         this->breakSings = std::auto_ptr<TBreakSings>(new TBreakSings(&meshHandler_));
      
         of::ofVerticesIterator<TTraits> iv(&meshHandler_);        
         of::ofVertexStarIteratorSurface<TTraits> isv(&meshHandler_);
         
         TBreakSings::TSingEdges::iterator  isingedge;
         for(isingedge=this->breakSings->singEdges.begin();
             isingedge!=this->breakSings->singEdges.end(); 
             ++isingedge
            ){
            this->print_->Edge(isingedge->first, isingedge->second, green, 5);
         }
         
         TBreakSings::TSingV::iterator  isingv;
         for(isingv=this->breakSings->singV.begin();
             isingv!=this->breakSings->singV.end(); 
             ++isingv
            ){
            
            if(this->print_->meshHandler_->getVertex(*isingv)->isSingular())
               this->print_->Vertex(*isingv, red, 10);
            else
               this->print_->Vertex(*isingv, blue, 10);
            
            
            //if(*isingv == 5385 || *isingv == 4966 || *isingv == 985 || *isingv == 1152)
            for(isv.initialize(*isingv); isv.notFinish(); ++isv){
               this->print_->FaceWireframe(*isv, orange, 1);
               this->print_->Face(*isv, lorange);
            }
            
         }
         
/*         
         TBreakSings::TSingPartV::iterator  isingpartv;
         for(isingpartv = this->breakSings->singPartV.begin(); 
             isingpartv != this->breakSings->singPartV.end();
             ++isingpartv){
             
             this->print_->FaceWireframe(*isingpartv, cyan, 5);
         }
*/
      }
   }
         
};
      
#endif /*CMDSHOWMESH_HPP_*/
