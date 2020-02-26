#ifndef CMD_PRINT_SCREEN_IMAGE_HPP_
#define CMD_PRINT_SCREEN_IMAGE_HPP_

#include <string>
#include "..\scrInteractor.h"
#include "Command.hpp"

class CmdPrintScreenImage : public Command{
public:
   typedef Command      TCommand;
   
public:
   scrInteractor     *interactor_;
   
public:

   CmdPrintScreenImage(scrInteractor *interactor, const string filename, int nModes_i=1, int mode_i=0)
      :TCommand(nModes_i, mode_i), interactor_(interactor){
      
      this->interactor_->SetScreenImageFilename(filename);   
      this->helpMessage_ = "write a screen image file ("+filename+"XXX.ppm)" ;
   }
   
public:

   virtual
   void
   Execute(){
      if(this->currentMode_==1){
         this->interactor_->WriteScreenImage();
         this->currentMode_=0;
      }
   }; 

   
};

#endif /*CMDSHOWMESH_HPP_*/
