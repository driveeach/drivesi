#ifndef OFVERTEX2D_H
#define OFVERTEX2D_H
#include "ofMacros.h"
#include "ofSingularVertex.h"
namespace of
{
/** Base class of 2D vertex
 * 
*/
template <class _Traits> class ofVertex2D : public ofSingularVertex<2,_Traits>
{
public:
	typedef typename _Traits::space space;
	/* Constructor */
	ofVertex2D();
	ofVertex2D(space x, space y);
	
	/* Destructor */
	~ofVertex2D();
};
template <class _Traits> ofVertex2D<_Traits>::ofVertex2D()
{
}
template <class _Traits> ofVertex2D<_Traits>::ofVertex2D(space x, space y)
{
	this->setCoord(0,x);
	this->setCoord(1,y);
}
template <class _Traits> ofVertex2D<_Traits>::~ofVertex2D()
{
}
}
#endif
