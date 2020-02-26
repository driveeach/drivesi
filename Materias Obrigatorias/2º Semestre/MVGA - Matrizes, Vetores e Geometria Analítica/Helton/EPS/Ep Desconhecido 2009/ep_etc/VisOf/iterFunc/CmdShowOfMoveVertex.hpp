#ifndef CMDSHOW_MOVE_VERTEX_HPP_
#define CMDSHOW_MOVE_VERTEX_HPP_

#include "CommandShowOf.hpp"

template<typename _TPrint>
class CmdShowOfMoveVertex : public CommandShowOf<_TPrint>{
public:
   typedef  _TPrint                  TPrint; 
   typedef CommandShowOf<TPrint>     TCommand;
   
   typedef typename TPrint::TMesh::traits    TTraits;
   typedef typename TPrint::TMeshHandler     TMeshHandler;
   
public:
   TMeshHandler                        &meshHandler_;   
   int                                  vertexDegree_;   
   
public:

   CmdShowOfMoveVertex(TPrint *print_i, int nModes_i=1, int mode_i=0, int degree=3)
      :TCommand(print_i, nModes_i, mode_i), meshHandler_(print_i->meshHandler_), vertexDegree_(degree){
         
      this->helpMessage_ = "move vertices of degree iqual to 3";
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         of::ofVertexStarIteratorSurface<TTraits> ivs(&this->meshHandler_);
         of::ofVerticesIterator<TTraits> iv(&this->meshHandler_);
         typename TTraits::sVertex *vertex;
         
         typename TPrint::TPoint  p;
         int count;
         
         for(iv.initialize(); iv.notFinish(); ++iv){
            
            count=0;
            for(ivs.initialize(&iv); ivs.notFinish(); ++ivs){
               ++count;
            }
            
            if(count == this->vertexDegree_){
               
               p.Zero();
               count = 0;
               for(ivs.initialize(&iv); ivs.notFinish(); ++ivs){
                  
                  for(int ii=0; ii<3; ++ii)
                     if(ivs->getVertexId(ii) != &iv){
                        vertex = this->meshHandler_->getVertex(ivs->getVertexId(ii));
                        p[0] += vertex->getCoord(0);
                        p[1] += vertex->getCoord(1);
                        p[2] += vertex->getCoord(2);
                        ++count;
                     }
               }
               
               iv->setCoord(0,p[0]/count);
               iv->setCoord(1,p[1]/count);
               iv->setCoord(2,p[2]/count);   
            }
         }
         
         this->currentMode_ = 0;
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
