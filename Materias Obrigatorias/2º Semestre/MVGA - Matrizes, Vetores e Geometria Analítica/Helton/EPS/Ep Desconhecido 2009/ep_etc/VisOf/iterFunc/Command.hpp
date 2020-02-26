#ifndef COMMAND_HPP_
#define COMMAND_HPP_

#include "CommandComponent.hpp"
#include <string>

class Command: public CommandComponent{

public:
   int        currentMode_;
   int        numModes_;
   
   string     helpMessage_;
   
public:
   Command(int nModes_i=2, int mode_i=0)
      :numModes_(nModes_i), 
       currentMode_(mode_i){
   }
   
public:
   void
   Mode(int mode_i){
      this->currentMode_ = mode_i;
   };  
   
   
   int
   Mode(){return this->currentMode_;}
   
   
   void
   NextMode(){
      this->currentMode_ = (this->currentMode_+1)%this->numModes_;      
   }



public:

   virtual
   ostream &
   Help(ostream &out_io){
      out_io <<"  ("<< this->currentMode_<<"/"<< this->numModes_-1<<") "<< this->helpMessage_.c_str();
      
      return out_io;
   }
   

   
public:

   virtual
   void
   Execute()=0;   
};



template<typename _TPrint>
ostream &
operator <<(ostream & out_io, Command *cmd_i){
   return cmd_i->Help(out_io);
}



#endif /*COMMAND_HPP_*/
