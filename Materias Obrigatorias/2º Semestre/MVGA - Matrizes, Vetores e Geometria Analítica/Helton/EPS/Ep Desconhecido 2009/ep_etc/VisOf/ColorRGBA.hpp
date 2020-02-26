#ifndef _COLOR_RGBA_H
#define _COLOR_RGBA_H


class TColorRGB{
 public:

   float R,
         G,
         B; 
 
   TColorRGB(){R = G = B = 0;};
   TColorRGB(const float &NewR,const float &NewG,const float &NewB):R(NewR),G(NewG),B(NewB){};
   void SetColor(const float &NewR,const float &NewG,const float &NewB)
   {   
      R = NewR; G = NewG; B = NewB;
   };
   
};

// **********************************************TColorRGBA*************************************
class TColorRGBA: public TColorRGB{
 public:
   float A;     
 
   TColorRGBA():TColorRGB(){A = 0.0;};
   TColorRGBA(const float &NewR,const float &NewG,const float &NewB, const float &NewA=0.0):TColorRGB(NewR,NewG,NewB),A(NewA){};
   void SetColor(const float &NewR,const float &NewG,const float &NewB, const float &NewA)
   {   
      R = NewR; G = NewG; B = NewB; A = NewA;
   }; 
};
//---------------------------------------------------------------------------------------------

#endif
