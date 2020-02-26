#ifndef PRINT_BASE_HPP_
#define PRINT_BASE_HPP_

#include <glut.h>
#include <vector>
#include <algorithm>
#include <functional>

#include "of.h"
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
   
protected:

   double         lineScaleFactor_;  //1.0 is no shirinkage and 0.0 the element desappears
   double         triaScaleFactor_;  //1.0 is no shirinkage and 0.0 the element desappears
   double         tetraScaleFactor_; //1.0 is no shirinkage and 0.0 the element desappears

   TPoint         normal;
      
public:
   
   PrintBase(double linefactor=1.0, double triafactor=1.0, double tetrafactor=1.0)
      :lineScaleFactor_(linefactor), 
       triaScaleFactor_(triafactor), 
       tetraScaleFactor_(tetrafactor){
   }       
      
public:
 
   void
   SetScaleFactor(float f){
      
      if(f<0.0)       f=0.0;
      else if(f>1.0)  f=1.0;

      this->lineScaleFactor_ = this->triaScaleFactor_ = this->tetraScaleFactor_ = f; 
   };
   
   void
   SetLineScaleFactor(float f){
      
      if(f<0.0)       f=0.0;
      else if(f>1.0)  f=1.0;

      this->lineScaleFactor_ = f; 
   };

   void
   SetTriaScaleFactor(float f){
      
      if(f<0.0)       f=0.0;
      else if(f>1.0)  f=1.0;

      this->triaScaleFactor_ = f; 
   };
   
   void
   SetTetraScaleFactor(float f){
      
      if(f<0.0)       f=0.0;
      else if(f>1.0)  f=1.0;

      this->tetraScaleFactor_ = f; 
   };
 
   float GetLineScaleFactor() {return this->lineScaleFactor_;}
   float GetTriaScaleFactor() {return this->triaScaleFactor_;}
   float GetTetraScaleFactor(){return this->tetraScaleFactor_;}
   
   
   void
   ScaleLine(TPoint &p1, TPoint &p2,  //input 
              TPoint &pa, TPoint &pb   //output
             ){
      
      static TVector vec;
      TP3DU::GetVector(p1, p2, vec);
      TP3DU::MultiplyBy(0.5*(1.0-this->lineScaleFactor_), vec, vec);
      TP3DU::Add(p1, vec, pa);
      TP3DU::MultiplyBy(-1.0, vec, vec);
      TP3DU::Add(p2, vec, pb); 
   }    
   
   void
   ScaleTria(TPoint &p1, TPoint &p2, TPoint &p3, //input 
              TPoint &pa, TPoint &pb, TPoint &pc  //output
             ){
                  
      static TPoint  baricenter;
      static TVector vec;
      
      TP3DU::Add(p1, p2, baricenter);
      TP3DU::Add(baricenter, p3, baricenter);
      TP3DU::MultiplyBy(0.33333, baricenter, baricenter);
      
      TP3DU::GetVector(p1, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->triaScaleFactor_, vec, vec);
      TP3DU::Add(p1, vec, pa);
      
      TP3DU::GetVector(p2, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->triaScaleFactor_, vec, vec);
      TP3DU::Add(p2, vec, pb);
      
      TP3DU::GetVector(p3, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->triaScaleFactor_, vec, vec);
      TP3DU::Add(p3, vec, pc);
   }    
   
   void
   ScaleTetra(TPoint &p1, TPoint &p2, TPoint &p3, TPoint &p4, //input 
               TPoint &pa, TPoint &pb, TPoint &pc, TPoint &pd  //output
             ){
                  
      static TPoint  baricenter;
      static TVector vec;
      
      TP3DU::Add(p1, p2, baricenter);
      TP3DU::Add(baricenter, p3, baricenter);
      TP3DU::Add(baricenter, p4, baricenter);
      TP3DU::MultiplyBy(0.25, baricenter, baricenter);
      
      TP3DU::GetVector(p1, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->tetraScaleFactor_, vec, vec);
      TP3DU::Add(p1, vec, pa);
      
      TP3DU::GetVector(p2, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->tetraScaleFactor_, vec, vec);
      TP3DU::Add(p2, vec, pb);
      
      TP3DU::GetVector(p3, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->tetraScaleFactor_, vec, vec);
      TP3DU::Add(p3, vec, pc);
      
      TP3DU::GetVector(p4, baricenter, vec);
      TP3DU::MultiplyBy(1.0-this->tetraScaleFactor_, vec, vec);
      TP3DU::Add(p4, vec, pd);
   }  
      
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
   void LineStatic(TPoint &p1, TPoint &p2, const TColorRGBA &Color, const float width=1){
      
      this->setAlpha(Color.A);  
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      glLineWidth(width);
         
      glBegin(GL_LINES);
         glVertex3f(p1[0], p1[1], p1[2]);          
         glVertex3f(p2[0], p2[1], p2[2]);            
      glEnd();  
      
      glLineWidth(1);
   }
   
   //---------------------------------------------------------------------
   void Line(TPoint &p1, TPoint &p2, const TColorRGBA &Color, const float width=1){
      
      this->setAlpha(Color.A);  
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      glLineWidth(width);
      
      TPoint pa, pb;
      ScaleLine(p1, p2, pa, pb);
      
      glBegin(GL_LINES);
         glVertex3f(pa[0], pa[1], pa[2]);          
         glVertex3f(pb[0], pb[1], pb[2]);            
      glEnd();
         
      
      glLineWidth(1);
   }

   //--------------------------------------------------------------------- 
   void LineStatic(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, const TColorRGBA &Color, const float width=1){   
      
      TPoint p1(x1,y1,z1), p2(x2,y2,z2);
      LineStatic(p1,p2, Color, width);
   };
   
   //--------------------------------------------------------------------- 
   void Line(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, const TColorRGBA &Color, const float width=1){   
      
      TPoint p1(x1,y1,z1), p2(x2,y2,z2);
      Line(p1,p2, Color, width);
   };
   
   //---------------------------------------------------------------------
   void Vector(TPoint &p, TVector v, const TColorRGBA &Color, const int width=1){   
        TP3DU::Add(p,v,v);
        LineStatic(p,v,Color, width);
   };
   
   

   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Triangle(TPoint &p1, TPoint &p2, TPoint &p3, const TColorRGBA &Color){

      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);

      TPoint pa, pb, pc;
      ScaleTria(p1, p2, p3, pa, pb, pc);

      /*normal = CreateNormal(p1, p2, p3);     
      
      if(!scrInteractor::Get_Normal_Change())
      {	
	      glNormal3f(normal[0], normal[1], normal[2]);	
      }else
      {
	      glNormal3f(-normal[0], -normal[1], -normal[2]);	
      }*/

      glBegin(GL_POLYGON);
        glVertex3f(pa[0],pa[1],pa[2]);          
        glVertex3f(pb[0],pb[1],pb[2]);
        glVertex3f(pc[0],pc[1],pc[2]);
      glEnd();
         
   }
   
   //---------------------------------------------------------------------
   void TriangleStatic(TPoint &p1, TPoint &p2, TPoint &p3, const TColorRGBA &Color){

      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);

      glBegin(GL_POLYGON);
        glVertex3f(p1[0],p1[1],p1[2]);          
        glVertex3f(p2[0],p2[1],p2[2]);
        glVertex3f(p3[0],p3[1],p3[2]);
      glEnd();
   }

   //---------------------------------------------------------------------
   void Triangle(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, TReal x3, TReal y3, TReal z3, const TColorRGBA &Color){
      TPoint p1, p2, p3;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      Triangle(p1, p2, p3, Color);      
   };   
   
   //---------------------------------------------------------------------
   void TriangleStatic(TReal x1, TReal y1, TReal z1, TReal x2, TReal y2, TReal z2, TReal x3, TReal y3, TReal z3, const TColorRGBA &Color){
      TPoint p1, p2, p3;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      TriangleStatic(p1, p2, p3, Color);      
   };   
   
   //---------------------------------------------------------------------
   void TriangleWireframe(
           TPoint &p1, TPoint &p2, TPoint &p3, 
           const TColorRGBA &Color, const double width=1.0
        ){
 
      this->setAlpha(Color.A);  
      
      glLineWidth(width);
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);

      TPoint pa, pb, pc;
      ScaleTria(p1, p2, p3, pa, pb, pc);
   
      glBegin(GL_LINE_LOOP);
        glVertex3f(pa[0],pa[1],pa[2]);          
        glVertex3f(pb[0],pb[1],pb[2]);
        glVertex3f(pc[0],pc[1],pc[2]);
      glEnd();
      
      glLineWidth(width);   
   };
   
   //---------------------------------------------------------------------
   void TriangleWireframeStatic(
           TPoint &p1, TPoint &p2, TPoint &p3, 
           const TColorRGBA &Color, const double width=1.0
        ){
 
      this->setAlpha(Color.A);  
      
      glLineWidth(width);
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glBegin(GL_LINE_LOOP);
        glVertex3f(p1[0],p1[1],p1[2]);          
        glVertex3f(p2[0],p2[1],p2[2]);
        glVertex3f(p3[0],p3[1],p3[2]);
      glEnd();
      
      glLineWidth(1);
        
   };
   
   //---------------------------------------------------------------------
   void TriangleWireframe(
           TReal x1, TReal y1, TReal z1, 
           TReal x2, TReal y2, TReal z2, 
           TReal x3, TReal y3, TReal z3, 
           const TColorRGBA &Color, const double width = 1
        ){
      TPoint p1, p2, p3;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      TriangleWireframe(p1, p2, p3, Color, width);     
   }
   
   //---------------------------------------------------------------------
   void TriangleWireframeStatic(
           TReal x1, TReal y1, TReal z1, 
           TReal x2, TReal y2, TReal z2, 
           TReal x3, TReal y3, TReal z3, 
           const TColorRGBA &Color, const double width = 1
        ){
      TPoint p1, p2, p3;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      TriangleWireframeStatic(p1, p2, p3, Color, width);     
   }
 
   //---------------------------------------------------------------------
   //---------------------------------------------------------------------
   void Tetrahedron(TPoint &p1, TPoint &p2, TPoint &p3, TPoint &p4, const TColorRGBA &Color){
         
      TPoint pa, pb, pc, pd;
      ScaleTetra(p1, p2, p3, p4, pa, pb, pc, pd);  
      TetrahedronStatic(pa,pb,pc,pd, Color);
      
   }
   
   //---------------------------------------------------------------------
   void TetrahedronStatic(TPoint &p1, TPoint &p2, TPoint &p3, TPoint &p4, const TColorRGBA &Color){

      this->setAlpha(Color.A);  
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
   
      glBegin(GL_POLYGON);
        glVertex3f(p1[0],p1[1],p1[2]);          
        glVertex3f(p2[0],p2[1],p2[2]);
        glVertex3f(p3[0],p3[1],p3[2]);
      glEnd();
      
      glBegin(GL_POLYGON);
        glVertex3f(p1[0],p1[1],p1[2]);
        glVertex3f(p3[0],p3[1],p3[2]);
        glVertex3f(p4[0],p4[1],p4[2]);
      glEnd();
      
      glBegin(GL_POLYGON);
        glVertex3f(p1[0],p1[1],p1[2]);          
        glVertex3f(p4[0],p4[1],p4[2]);
        glVertex3f(p2[0],p2[1],p2[2]);
      glEnd();
      
      glBegin(GL_POLYGON);
        glVertex3f(p2[0],p2[1],p2[2]);          
        glVertex3f(p4[0],p4[1],p4[2]);
        glVertex3f(p3[0],p3[1],p3[2]);
      glEnd();

   }

   //---------------------------------------------------------------------
   void Tetrahedron(
           TReal x1, TReal y1, TReal z1, 
           TReal x2, TReal y2, TReal z2, 
           TReal x3, TReal y3, TReal z3,
           TReal x4, TReal y4, TReal z4, 
           const TColorRGBA &Color
         ){
      TPoint p1, p2, p3, p4;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      p4.Set(x4, y4, z4);
      Tetrahedron(p1, p2, p3, p4, Color);      
   };   
   
   //---------------------------------------------------------------------
   void TetrahedronStatic(
           TReal x1, TReal y1, TReal z1, 
           TReal x2, TReal y2, TReal z2, 
           TReal x3, TReal y3, TReal z3,
           TReal x4, TReal y4, TReal z4, 
           const TColorRGBA &Color
        ){
      TPoint p1, p2, p3, p4;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      p4.Set(x4, y4, z4);
      TetrahedronStatic(p1, p2, p3, p4, Color);      
   };   
   
   //---------------------------------------------------------------------
   void TetrahedronWireframe(
           TPoint &p1, TPoint &p2, TPoint &p3, TPoint &p4, 
           const TColorRGBA &Color, const double width=1.0
        ){
      TPoint pa, pb, pc, pd;
      ScaleTetra(p1, p2, p3, p4, pa, pb, pc, pd);
      TetrahedronWireframeStatic(pa, pb, pc, pd, Color, width);
   };
   
   //---------------------------------------------------------------------
   void TetrahedronWireframeStatic(
           TPoint &p1, TPoint &p2, TPoint &p3, TPoint &p4, 
           const TColorRGBA &Color, const double width=1.0
        ){
 
      this->setAlpha(Color.A);  
      
      glLineWidth(width);
      
      glColor4f(Color.R, Color.G, Color.B, Color.A);
      
      glBegin(GL_LINE_LOOP);
        glVertex3f(p1[0],p1[1],p1[2]);          
        glVertex3f(p2[0],p2[1],p2[2]);
        glVertex3f(p3[0],p3[1],p3[2]);
      glEnd();
      
      glBegin(GL_LINE_LOOP);
        glVertex3f(p4[0],p4[1],p4[2]);          
        glVertex3f(p1[0],p1[1],p1[2]);
      glEnd();
      
      glBegin(GL_LINE_LOOP);
        glVertex3f(p4[0],p4[1],p4[2]);          
        glVertex3f(p2[0],p2[1],p2[2]);
      glEnd();
      
      glBegin(GL_LINE_LOOP);
        glVertex3f(p4[0],p4[1],p4[2]);          
        glVertex3f(p3[0],p3[1],p3[2]);
      glEnd();
      
      
      glLineWidth(1);
        
   };
   
   //---------------------------------------------------------------------
   void TetrahedronWireframe(
           TReal x1, TReal y1, TReal z1, 
           TReal x2, TReal y2, TReal z2, 
           TReal x3, TReal y3, TReal z3,
           TReal x4, TReal y4, TReal z4, 
           const TColorRGBA &Color, const double width = 1
        ){
      TPoint p1, p2, p3, p4;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      p4.Set(x4, y4, z4);
      TetrahedronWireframe(p1, p2, p3, p4, Color, width);     
   }
   
   //---------------------------------------------------------------------
   void TriangleWireframeStatic(
           TReal x1, TReal y1, TReal z1, 
           TReal x2, TReal y2, TReal z2, 
           TReal x3, TReal y3, TReal z3,
           TReal x4, TReal y4, TReal z4, 
           const TColorRGBA &Color, const double width = 1
        ){ 

      TPoint p1, p2, p3, p4;
      p1.Set(x1, y1, z1);
      p2.Set(x2, y2, z2);
      p3.Set(x3, y3, z3);
      p4.Set(x4, y4, z4);
      TetrahedronWireframeStatic(p1, p2, p3, p4, Color, width);     
   }
 

   //---------------------------------------------------------------------
   TPoint 
   CreateNormal(TPoint &p1, TPoint &p2, TPoint &p3)
   {
	   TPoint  n,
              v,
              u;
	   TReal norma;
	   
	   v[0] = p1[0] - p2[0];
	   v[1] = p1[1] - p2[1];
	   v[2] = p1[2] - p2[2];
	   
	   u[0] = p3[0] - p1[0];
	   u[1] = p3[1] - p1[1];
	   u[2] = p3[2] - p1[2];
	   
	   n[0] = u[1] * v[2] - v[1] * u[2];
      n[1] = v[0] * u[2] - u[0] * v[2];
      n[2] = u[0] * v[1] - v[0] * u[1];
	   
	   norma = sqrt(n[0]*n[0] + n[1]*n[1] + n[2]*n[2]);

      n[0] = n[0] / norma;
      n[1] = n[1] / norma;
      n[2] = n[2] / norma;
	   
	   return n;
	   
   };

};        
#endif /*PRINTOF_HPP_*/
