#ifndef OFBASE_H
#define OFBASE_H
#include "ofMacros.h"
namespace of
{
#define IDS typename ofBase<_Traits>::ids
/** Base Class for all elements of mesh
 * 
 *  _Traits must have ids typename
 * 
*/
template <class _Traits> class ofBase
{
public:
	typedef typename _Traits::ids ids;
	/** Constructor
	 * 
	 *  Initialize the mutex;
	 *  Set this element as out of mesh
	 */
	ofBase();
	/** Destructor
	 * 
	 *  Destroy mutex
	 */
	virtual ~ofBase();
	/** Check if this element is in mesh
	 * 
	 *  \return true, if this element is in mesh
	 */
	bool inMesh(OF_DMUTEXD);
	/** Set if this element is in mesh
	 * 
	 *  \param value: the status of this element
	 */
	void setInMesh(bool value OF_DMUTEXVD);
	/** Set the next free element in vector
	 * 
	 *  This position is stored in same place of flags
	 * 
	 *  \param position: the index of next free position
	 */
	void setPosition(ids position OF_DMUTEXVD);
	/** Get the next free element
	 * 
	 *  \return Return the next free element, if was not define, returned value is negative.
	 */
	ids  getPosition(OF_DMUTEXD);
#ifdef OF_THREADS
	/** Lock the element mutex
	 *  
	 *  Used with OF_THREADS define
	 */
	void lock();
	/** Unlock the element mutex
	 *  
	 *  Used with OF_THREADS define
	 */
	void unlock();
	/** Try lock the element mutex
	 *  
	 *  Used with OF_THREADS define
	 */
	int  trylock();
#endif
protected:
	OF_FLAGS_TYPE flags;
#ifdef OF_THREADS
	pthread_mutex_t smutex;
#endif
};
template <class _Traits> ofBase<_Traits>::ofBase()
{
#ifdef OF_THREADS
	pthread_mutex_init(&smutex,0);
	pthread_mutex_lock(&smutex);
#endif
	flags = (OF_FLAGS_TYPE) 0;
#ifdef OF_THREADS
	pthread_mutex_unlock(&smutex);
#endif
}
template <class _Traits> ofBase<_Traits>::~ofBase()
{
#ifdef OF_THREADS
	pthread_mutex_destroy(&smutex);
#endif
}
template <class _Traits> bool ofBase<_Traits>::inMesh(OF_DMUTEX)
{
	OF_LOCK(smutex);
	bool temp = (flags & (1 << OF_FLAGS_INMESH)) != 0;
	OF_UNLOCK(smutex);
	return temp;
}
template <class _Traits> void ofBase<_Traits>::setInMesh(bool value OF_DMUTEXV)
{
	OF_LOCK(smutex);
	if(value)
		flags |= (1 << OF_FLAGS_INMESH);
	else
		flags &= ~(1 << OF_FLAGS_INMESH);
	OF_UNLOCK(smutex);
}
template <class _Traits> void ofBase<_Traits>::setPosition(ids position OF_DMUTEXV)
{
	OF_LOCK(smutex);
	flags = position;
	OF_UNLOCK(smutex);
}
template <class _Traits> IDS ofBase<_Traits>::getPosition(OF_DMUTEX)
{
	OF_LOCK(smutex);
	ids temp = flags;
	OF_UNLOCK(smutex);
	return temp;
}
#ifdef OF_THREADS
template <class _Traits> void ofBase<_Traits>::lock()
{
	pthread_mutex_lock(&smutex);
}
template <class _Traits> void ofBase<_Traits>::unlock()
{
	pthread_mutex_unlock(&smutex);
}
template <class _Traits> int ofBase<_Traits>::trylock()
{
	return pthread_mutex_trylock(&smutex);
}
#endif
#undef IDS
}
#endif
