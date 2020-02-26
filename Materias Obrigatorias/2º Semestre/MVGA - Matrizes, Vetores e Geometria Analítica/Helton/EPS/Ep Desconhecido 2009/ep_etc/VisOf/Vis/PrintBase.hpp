#ifndef PRINT_BASE_HPP_
#define PRINT_BASE_HPP_

#include <GL/glut.h>
#include <vector>
#include <algorithm>
#include <functional>

#include "../of/of.h"
#include "ColorRGBA.hpp"
#include "Point.hpp"
#include "Point3DUtils.hpp"


class PrintBase
{  
public:	
   typedef Point                     TPoint;
   typedef TPoint                    TVector;
   
   typedef Point3DUtils<TPoint>      TP3DU;
   typedef float                     TReal;
      
public:   
   //---------------------------------------------------------------------
   void 
   setAlpha(float transp)
   {
      if (transp > 0)
         glEnable(GL_BLEND);
      else
         glDisable(GL_BLEND);
   }

   //------------------------------------------------------------------------------
   void
   Text(float x, float y, float z, char *text, const TColorRGB &Color){
      
      glColor3f(Color.R, Color.G, Color.B);
      glRasterPos3f(x,y,z);
      
      for (unsigned int i = 0; i < strlen(text); i++)
         glutBitmapCharacter (GLUT_BITMAP_HELVETICA_12, text[i]);
   
   }

   //------------------------------------------------------------------------------
   void
   Text(TPoint p, char *text, const TColorRGB &Color){
      Text(p[0], p[1], p[2], text, Color);   
   }
   
   //---------------------------------------------------------------------
   //--------------------------------------------------------------------- 
   void Point3D(TReal x, TReal y, TReal z,const TColorRGBA &Color, const int size=5){   
      
        this->setAlpha(Color.A);  

        // Set current drawing color 
        //        R   G    B
        glColor4f(Color.R, Color.G, Color.B, Color.A);
        glPointSize(size);
         
        glBegin(GL_POINTS); 
          glVertex3f(x,y,z);           
        glEnd();
   };
   
   //---------------------------------------------------------------------
   void Point3D(TPoint &p,const TColorRGBA &Color, const int size=5){   
        Point3D(p[0], p[1], p[2], Color, size);
   };
   
   //---------------------------------------------------------------------
   //--------------------------------------------------------------------- 
   void Line(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, const TColorRGBA &Color, const int width=1){   
      
      this->setAlpha(Color.A);  
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glLineWidth(width);
      
      glBegin(GL_LINES);
         glVertex3f(x1,y1,z1);          
         glVertex3f(x2,y2,z2);            
      glEnd();
      
      glLineWidth(1);
   };
   
   void Line(TPoint &p1, TPoint &p2, const TColorRGBA &Color, const int width=1){
      Line(p1[0], p1[1], p1[2],  p2[0], p2[1], p2[2], Color, width);
   }
   
   //---------------------------------------------------------------------
   void Vector(TPoint &p, TVector v, const TColorRGBA &Color, const int width=1){   
        TP3DU::Add(p,v,v);
        Line(p,v,Color, width);
   };
   
   

   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Triangle(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, TReal x3, TReal y3, TReal z3, const TColorRGBA &Color){
 
      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glBegin(GL_POLYGON);
        glVertex3f(x1,y1,z1);          
        glVertex3f(x2,y2,z2);
        glVertex3f(x3,y3,z3);
      glEnd();
        
   };
   
   //---------------------------------------------------------------------
   void Triangle(TPoint &p1, TPoint &p2, TPoint &p3, const TColorRGBA &Color){
      Triangle(p1[0], p1[1], p1[2], p2[0], p2[1], p2[2], p3[0], p3[1], p3[2], Color);   
   }
   
   
   
   //---------------------------------------------------------------------
   void TriangleWireframe(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, TReal x3, TReal y3, TReal z3, const TColorRGBA &Color, const int width=1.0){
 
      this->setAlpha(Color.A);  
      
      glLineWidth(width);
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glBegin(GL_LINE_LOOP);
        glVertex3f(x1,y1,z1);          
        glVertex3f(x2,y2,z2);
        glVertex3f(x3,y3,z3);
      glEnd();
      
      glLineWidth(1);
        
   };
   
   //---------------------------------------------------------------------
   void TriangleWireframe(TPoint &p1, TPoint &p2, TPoint &p3, const TColorRGBA &Color, const int width = 1){
      TriangleWireframe(p1[0], p1[1], p1[2], p2[0], p2[1], p2[2], p3[0], p3[1], p3[2], Color, width);   
   }
 
};        
#endif /*PRINTOF_HPP_*/
