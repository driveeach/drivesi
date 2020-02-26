#ifndef COMMANDSHOWOF_HPP_
#define COMMANDSHOWOF_HPP_

#include <string>
#include "Command.hpp"

template<typename _TPrint>
class CommandShowOf: public Command{

public:
   typedef  Command       TCommand;
   typedef  _TPrint       TPrint;  
    
public:   
   TPrint     *print_;
   
public:
   CommandShowOf(TPrint *print_i, int nModes_i=2, int mode_i=0)
      :TCommand(nModes_i, mode_i),
       print_(print_i){
   }
};

#endif /*CMDSHOWOF_HPP_*/
