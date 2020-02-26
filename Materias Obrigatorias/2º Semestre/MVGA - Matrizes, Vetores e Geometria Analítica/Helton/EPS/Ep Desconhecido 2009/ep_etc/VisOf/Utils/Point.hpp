#ifndef ___POINT_H_
#define ___POINT_H_

#include <iostream>

class Point{
   
public:
   typedef  double           TPCoord;


public:
  TPCoord pt[3];

public:
  Point(){pt[0]=0.0; pt[1]=0.0; pt[2]=0.0;};
  Point(TPCoord x, TPCoord y, TPCoord z){pt[0]=x; pt[1]=y; pt[2]=z;};
  TPCoord &operator[](char i){return pt[i];};
  
  void
  Set(TPCoord x, TPCoord y, TPCoord z){pt[0]=x; pt[1]=y; pt[2]=z;};
  
  Point &operator=(Point &p){
     pt[0]=p.pt[0]; pt[1]=p.pt[1]; pt[2]=p.pt[2];
     return *this;
  };
  
  void Zero(){pt[0] = 0.0; pt[1] = 0.0; pt[2] = 0.0;};
  
  void Print(){std::cout << "(" << pt[0] << ", " << pt[1] << ", " << pt[2] << ")";}; 
};

#endif
