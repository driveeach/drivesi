#ifndef OFVERTEX3D_H
#define OFVERTEX3D_H
#include "ofMacros.h"
#include "ofSingularVertex.h"
namespace of
{
/** Base class of 3D vertex
 * 
*/
template <class _Traits> class ofVertex3D : public ofSingularVertex<3,_Traits>
{
public:
	typedef typename _Traits::space space;
	/* Constructor */
	ofVertex3D();
	ofVertex3D(space x, space y, space z);
	
	/* Destructor */
	~ofVertex3D();
};
template <class _Traits> ofVertex3D<_Traits>::ofVertex3D()
{
}
template <class _Traits> ofVertex3D<_Traits>::ofVertex3D(space x, space y, space z)
{
	this->setCoord(0,x);
	this->setCoord(1,y);
	this->setCoord(2,z);
}
template <class _Traits> ofVertex3D<_Traits>::~ofVertex3D()
{
}
}
#endif
