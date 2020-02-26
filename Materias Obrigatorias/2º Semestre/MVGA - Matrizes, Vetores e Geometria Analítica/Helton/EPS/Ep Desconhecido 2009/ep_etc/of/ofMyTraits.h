#ifndef OFMYTRAITS_H
#define OFMYTRAITS_H
/** Traits class
 */
#include "ofVertex2D.h"
#include "ofMyVertex3D.h"
#include "ofCell2D.h"
#include "ofCell3D.h"
#include "ofMesh2D.h"
#include "ofMesh3D.h"
#include "ofMeshSurface.h"
#include "ofMeshNOSurface.h"
#include "ofGeometric.h"
#include "ofTopology.h"
namespace of
{
/*struct MyofDefault2D
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
};*/
struct MyofDefault3D
{
	typedef MyofDefault3D						sTraits;
	typedef double								space;
	typedef int									ids;
	typedef pair <ids,ids>                      sEdge;
	typedef ofVertex3D<sTraits>					sVertex;
	typedef ofCell3D<sTraits>					sCell;
	typedef ofSing<sTraits>						sSing;
	typedef ofPolygon<sTraits>					sPolygon;
	typedef ofTopology<sTraits>					sTopology;
	typedef ofMesh3D<sTraits>					sOper;
	typedef ofMesh<sTraits>						sMesh;
	typedef ofMyGeometric<sTraits>				sGeometric;
};
}
#endif
