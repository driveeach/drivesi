#ifndef COMMANDS_SET_HPP_
#define COMMANDS_SET_HPP_

#include "CommandComponent.hpp"
#include <vector>

class CommandsSet: public CommandComponent{
   
public:

   typedef CommandComponent                                TOneCommand;
    typedef std::vector<std::pair<TKey, TOneCommand*> >    TKeyCommands;
   
public:

   TKeyCommands                                   keyCommands_;

public:
   
   virtual
   bool 
   Keyboard(TKey key){
      
      if(key == '?'){
         Help(std::cout);
         return true;
      }

      TKeyCommands::iterator im;
      for(im = this->keyCommands_.begin(); im!=this->keyCommands_.end(); ++im){
         if(im->first == key){
            im->second->NextMode();
            return true;
         }else if(im->first == ' '){
            if(im->second->Keyboard(key))
              return true;
         }
      }
  
      return false;
        
   };
   
   virtual
   void
   Execute(){
      
      TKeyCommands::iterator ic    = this->keyCommands_.begin();
      TKeyCommands::iterator icEnd = this->keyCommands_.end();
      
      for(;ic != icEnd; ++ic){
         ic->second->Execute();
      }
   }
   
   virtual
   ostream &
   Help(ostream &out_io = std::cout){
      
      TKeyCommands::iterator im    = this->keyCommands_.begin();
      TKeyCommands::iterator imEnd = this->keyCommands_.end();
      
      for(;im != imEnd; ++im){
         if(im->first==' '){
            im->second->Help(out_io);
         }else{
            out_io << im->first << "\t ";
            im->second->Help(out_io);
            out_io << std::endl;
         }
      }
       
      return out_io;
   }
   

public:

   void
   RegistryCommand(TKey key_i, TOneCommand *cmd_i){
      this->keyCommands_.push_back(std::make_pair(key_i, cmd_i));
   };
   
};

#endif /*COMMANDS_SET_HPP_*/
