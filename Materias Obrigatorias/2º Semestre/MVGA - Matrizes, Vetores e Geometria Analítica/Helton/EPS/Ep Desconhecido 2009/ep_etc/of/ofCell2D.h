#ifndef OFCELL2D_H
#define OFCELL2D_H
#include "ofMacros.h"
#include "ofCell.h"
namespace of
{
/** Base class of triangle
 * 
*/
template <class _Traits> class ofCell2D : public ofCell<3,_Traits>
{
public:
	/* Constructor */
	ofCell2D();
	/* Destructor */
	virtual ~ofCell2D();
};
template <class _Traits> ofCell2D<_Traits>::ofCell2D()
{
}
template <class _Traits> ofCell2D<_Traits>::~ofCell2D()
{
}
}
#endif
