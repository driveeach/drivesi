#ifndef OFCELL3D_H
#define OFCELL3D_H
#include "ofMacros.h"
#include "ofCell.h"
namespace of
{
/** Base class of tetrahedron
 * 
*/
template <class _Traits> class ofCell3D : public ofCell<4,_Traits>
{
public:
	/* Constructor */
	ofCell3D();
	/* Destructor */
	virtual ~ofCell3D();
};
template <class _Traits> ofCell3D<_Traits>::ofCell3D()
{
}
template <class _Traits> ofCell3D<_Traits>::~ofCell3D()
{
}
}
#endif
