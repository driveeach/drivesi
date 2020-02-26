#ifndef OFTRAITS_H
#define OFTRAITS_H
#include "ofVertex2D.h"
#include "ofVertex3D.h"
#include "ofMyVertex3D.h"
#include "ofMyCell.h"
#include "ofCell2D.h"
#include "ofCell3D.h"
#include "ofMesh2D.h"
#include "ofMesh3D.h"
#include "ofMeshSurface.h"
#include "ofMeshNOSurface.h"
#include "ofGeometric.h"
#include "ofTopology.h"
#include "ofPolygon.h"
#include "ofMyDelaunay2D.h"
#include "ofMyGeometric.h"
/** Traits class
 */
namespace of
{
	
struct MyofDefault2D
{
	typedef MyofDefault2D						sTraits;
	typedef double								space;
	typedef int									ids;
	typedef pair <ids,ids>                      sEdge;
	typedef ofMyVertex3D<sTraits>				sVertex;
	typedef ofMyCell<sTraits>					sCell;
	typedef ofSing<sTraits>						sSing;
	typedef ofPolygon<sTraits>					sPolygon;
	typedef ofTopology<sTraits>					sTopology;
	typedef ofMesh2D<sTraits>					sOper;
	typedef ofMyDelaunay2D<sTraits>				sDelaunay;
	typedef ofMesh<sTraits>						sMesh;
	typedef ofMyGeometric<sTraits>				sGeometric;
};
struct ofDefault2D
{
	typedef ofDefault2D							sTraits;
	typedef double								space;
	typedef int									ids;
	
	typedef ofVertex2D<sTraits>					sVertex;
	typedef ofCell2D<sTraits>					sCell;
	typedef ofMesh2D<sTraits>					sOper;
	typedef ofGeometric<sTraits>				sGeometric;
	typedef ofTopology<sTraits>					sTopology;
};
struct ofDefaultLong2D
{
	typedef ofDefaultLong2D						sTraits;
	typedef long double							space;
	typedef long 								ids;
	
	typedef ofVertex2D<sTraits>					sVertex;
	typedef ofCell2D<sTraits>					sCell;
	typedef ofMesh2D<sTraits>					sOper;
	typedef ofGeometric<sTraits>				sGeometric;
	typedef ofTopology<sTraits>					sTopology;
};
struct ofDefault3D
{
	typedef ofDefault3D							sTraits;
	typedef double								space;
	typedef int									ids;
	
	typedef ofVertex3D<sTraits>					sVertex;
	typedef ofCell3D<sTraits>					sCell;
	typedef ofMesh3D<sTraits>					sOper;
	typedef ofGeometric<sTraits>				sGeometric;
	typedef ofTopology<sTraits>					sTopology;
};
struct ofDefaultSurface
{
	typedef ofDefaultSurface					sTraits;
	typedef double								space;
	typedef int									ids;
	
	typedef ofVertex3D<sTraits>					sVertex;
	typedef ofCell2D<sTraits>					sCell;
	typedef ofMeshSurface<sTraits>				sOper;
	typedef ofGeometric<sTraits>				sGeometric;
	typedef ofTopology<sTraits>					sTopology;
};
struct ofDefaultNOSurface
{
	typedef ofDefaultNOSurface					sTraits;
	typedef double								space;
	typedef int									ids;
	
	typedef ofVertex3D<sTraits>					sVertex;
	typedef ofCell2D<sTraits>					sCell;
	typedef ofMeshNOSurface<sTraits>			sOper;
	typedef ofGeometric<sTraits>				sGeometric;
	typedef ofTopology<sTraits>					sTopology;
};
}
#endif
