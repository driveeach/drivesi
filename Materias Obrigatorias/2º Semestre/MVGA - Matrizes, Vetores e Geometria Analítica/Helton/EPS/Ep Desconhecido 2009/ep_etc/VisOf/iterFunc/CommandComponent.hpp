#ifndef COMMAND_COMPONENT_HPP_
#define COMMAND_COMPONENT_HPP_

#include <iostream>

class CommandComponent{

protected:
   typedef unsigned char      TKey;

public:
   
   
   virtual
   void
   Execute()=0;
   
   virtual
   ostream &
   Help(ostream &out_io = std::cout)=0;


   virtual
   bool 
   Keyboard(TKey key){return false;} //Implemented by a Commands class

   
   virtual
   void
   NextMode(){} //Implemented by a Command class
};

#endif /*COMMAND_COMPONENT_HPP_*/
