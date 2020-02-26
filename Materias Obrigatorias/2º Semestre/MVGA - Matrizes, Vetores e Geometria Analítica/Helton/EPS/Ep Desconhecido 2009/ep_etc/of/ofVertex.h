#ifndef OFVERTEX_H
#define OFVERTEX_H
#include "ofMacros.h"
#include "ofBase.h"
namespace of
{
#define SPACE typename ofVertex<size,_Traits>::space
/** Base class of vertex
 * 
 * 	size is the dimension of vertex:
 * 		2 - 2D vertex (x,y)
 * 		3 - 3D vertex (x,y,z)
 * 
 *  _Traits must have typenames: 	space 
*/
template <int size, class _Traits> class ofVertex : public ofBase<_Traits>
{
public:
	
	typedef typename _Traits::space space;
	
	/* Constructor */
	ofVertex();
	/* Destructor */
	virtual ~ofVertex();
	
	/**	Return the specified coordinate
	 * 
	 * 	\param pos: index of coordinate
	 */
	space getCoord(int pos OF_DMUTEXVD);
	/**	Return a vector of all coordinates
	 */
	space *getCoords(OF_DMUTEXD);
	
	/**	Define the specified coordinate value
	 * 
	 * 	\param pos: index of coordinate
	 * 	\param value: coordinate value
	 */
	void setCoord(int pos, space value OF_DMUTEXVD);
	/**	Define all coordinates values
	 * 
	 * 	\param values: vector with coordinates values
	 */
	void setCoords(space* values OF_DMUTEXVD);
	
	/**	Return the dimension of the vertex	
	 */
	static inline int getDimension(){ return size;};
	
private:
	space coords[size];
};
template <int size, class _Traits> ofVertex<size,_Traits>::ofVertex()
{
}
template <int size, class _Traits> ofVertex<size,_Traits>::~ofVertex()
{
}
template <int size, class _Traits> SPACE ofVertex<size,_Traits>::getCoord(int pos OF_DMUTEXV)
{
	space c;
	
	OF_ASSERT((pos>=0) && (pos < size));
	
	OF_LOCK(smutex);
	c = coords[pos];
	OF_UNLOCK(smutex);
	
	return c;	
}
template <int size, class _Traits> SPACE* ofVertex<size,_Traits>::getCoords(OF_DMUTEX)
{
	space *c;
	
	OF_LOCK(smutex);
	c = coords;
	OF_UNLOCK(smutex);
	
	return c;	
}
template <int size, class _Traits> void ofVertex<size,_Traits>::setCoord(int pos, space value OF_DMUTEXV)
{
	OF_ASSERT((pos>=0) && (pos < size));
	OF_LOCK(smutex);
	coords[pos] = value;
	OF_UNLOCK(smutex);
}
template <int size, class _Traits> void ofVertex<size,_Traits>::setCoords(space *values OF_DMUTEXV)
{
	OF_LOCK(smutex);
	for(int i = 0; i < size; i++)
		coords[i] = values[i];
	OF_UNLOCK(smutex);
}
#undef SPACE
}
#endif
