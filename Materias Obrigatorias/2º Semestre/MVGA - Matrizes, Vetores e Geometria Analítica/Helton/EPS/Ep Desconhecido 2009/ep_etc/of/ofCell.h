#ifndef OFCELL_H
#define OFCELL_H
#include "ofMacros.h"
#include "ofBase.h"
namespace of
{
#define IDS typename ofCell<size,_Traits>::ids
/** Base class of cell
 * 
 * 	size is the dimension of cell:
 * 		3 - triangle
 * 		4 - tetrahedron
 * 
 *  _Traits must have typenames: 	ids
*/
template <int size, class _Traits> class ofCell : public ofBase<_Traits>
{
public:
	typedef typename _Traits::ids ids;
	/**	Constructor
	 *  
	 * 	Initialize vertices and mates ids
	 */
	ofCell();
	/**	Destructor
	 */
	virtual ~ofCell();
	/** Return the mate cell id of the specified index
	 * 
	 * 	\param index: position of mate cell
	 */
	ids	getMateId(int index OF_DMUTEXVD);
	/**	Return the vertex id of the specified index
	 * 
	 * 	\param index: position of vertex
	 */
	ids	getVertexId(int index OF_DMUTEXVD);
	/** Define the mate cell id of the specified index
	 * 
	 * 	\param index: position of mate cell
	 * 	\param cell: the mate cell id
	 */
	void setMateId(int index, ids cell OF_DMUTEXVD);
	/** Define the vertex id of the specified index
	 * 
	 * 	\param index: position of vertex
	 * 	\param vertex: the vertex id
	 */
	void setVertexId(int index, ids vertex OF_DMUTEXVD);
	/**	Reset the mate cells ids
	 * 
	 * 	Define -1 for all positions
	 */
	void clearMates(OF_DMUTEXD);
	/**	Return the position of the specified vertex id
	 * 
	 * 	\param vertex: the vertex id
	 */
	int getVertexIndex(ids vertex OF_DMUTEXVD);
	/**	Return the position of the opposite vertex id of the specified mate cell id
	 *	Return the position of the specified mate cell id
	 * 
	 * 	\param cell: the mate cell id
	 */
	int	getMateIndex(ids cell OF_DMUTEXVD);
	/**	Return the opposite cell id of the specified vertex id
	 * 	
	 * 	\param cell: the vertex id
	 */
	ids	getMateVertexId(ids vertex OF_DMUTEXVD);
	/**	Return the opposite vertex id of the specified mate cell id
	 * 	
	 * 	\param cell: the mate cell id
	 */
	ids	getVertexMateId(ids cell OF_DMUTEXVD);
	/**	Return the dimension of this cell
	 */

	
	
	static inline int getDimension(){ return size-1;};
private:
	ids vertices[size];
	ids mates[size];
};
template <int size, class _Traits> ofCell<size,_Traits>::ofCell()
{
#ifdef OF_THREADS
	pthread_mutex_lock(&this->smutex);
#endif
	for(int i = 0; i < size; i++)
	{
		vertices[i] = -1;
		mates[i] = -1;
	}
#ifdef OF_THREADS
	pthread_mutex_unlock(&this->smutex);
#endif
}
template <int size, class _Traits> ofCell<size,_Traits>::~ofCell()
{
}
template <int size, class _Traits> IDS ofCell<size,_Traits>::getMateId(int index OF_DMUTEXV)
{
	OF_ASSERT((index >= 0)&&(index < size));
	OF_LOCK(smutex);
	ids temp = mates[index];
	OF_UNLOCK(smutex);
	return temp;
}
template <int size, class _Traits> IDS ofCell<size,_Traits>::getVertexId(int index OF_DMUTEXV)
{
	OF_ASSERT((index >= 0)&&(index < size));
	OF_LOCK(smutex);
	ids temp = vertices[index];
	OF_UNLOCK(smutex);
	return temp;
}
template <int size, class _Traits> void ofCell<size,_Traits>::setMateId(int index, ids cell OF_DMUTEXV)
{
	OF_ASSERT((index >= 0)&&(index < size));
	OF_LOCK(smutex);
	mates[index] = cell;
	OF_UNLOCK(smutex);
}
template <int size, class _Traits> void ofCell<size,_Traits>::setVertexId(int index, ids vertex OF_DMUTEXV)
{
	OF_ASSERT((index >= 0)&&(index < size));
	OF_LOCK(smutex);
	vertices[index] = vertex;
	OF_UNLOCK(smutex);
}
template <int size, class _Traits> IDS ofCell<size,_Traits>::getMateVertexId(ids vertex OF_DMUTEXV)
{
	for(int i = 0; i < size; i++)
	{
		if (getVertexId(i OF_MUTEXV) == vertex)
			return getMateId(i OF_MUTEXV);
	}
	return -1;
}
template <int size, class _Traits> IDS ofCell<size,_Traits>::getVertexMateId(ids cell OF_DMUTEXV)
{
	for(int i = 0; i < size; i++)
		if (getMateId(i OF_MUTEXV) == cell)
			return getVertexId(i OF_MUTEXV);
	return -1;
}
template <int size, class _Traits> int ofCell<size,_Traits>::getMateIndex(ids cell OF_DMUTEXV)
{
	for(int i = 0; i < size; i++)
		if (getMateId(i OF_MUTEXV) == cell)
			return i;
	return -1;
}
template <int size, class _Traits> void ofCell<size,_Traits>::clearMates(OF_DMUTEX)
{
	for(int i = 0; i < size; i++)
		setMateId(i,-1 OF_MUTEXV);
}
template <int size, class _Traits> int ofCell<size,_Traits>::getVertexIndex(ids vertex OF_DMUTEXV)
{
	for(int i = 0; i < size; i++)
		if (getVertexId(i OF_MUTEXV) == vertex)
			return i;
	return -1;
}

#undef IDS
}
#endif
