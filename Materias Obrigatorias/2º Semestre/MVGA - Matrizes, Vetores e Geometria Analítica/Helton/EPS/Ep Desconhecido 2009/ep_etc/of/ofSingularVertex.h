#ifndef OFSINGULARVERTEX_H
#define OFSINGULARVERTEX_H
#include "ofMacros.h"
#include "ofVertex.h"
#include "ofSing.h"
namespace of
{
#define SPACE typename ofSingularVertex<size,_Traits>::space
#define SSING typename ofSingularVertex<size,_Traits>::sSing
#define IDS typename ofSingularVertex<size,_Traits>::ids
/** Base class of singular vertex
 * 
 *  _Traits must have typenames: ids, space, sSing
*/
template <int size, class _Traits> class ofSingularVertex : public ofVertex<size,_Traits>
{
public:
	
	typedef typename _Traits::ids ids;
	typedef typename _Traits::space space;
	
	typedef ofSing<_Traits> sSing;
	
	/* Constructor */
	ofSingularVertex();
	/* Destructor */
	~ofSingularVertex();
	
	/** Return true if this vertex is singular
	 */
	bool isSingular(OF_DMUTEXD);
	/** Return the number of sSing (number of singular components)
	 */
	int	getNumberOfSings(OF_DMUTEXD);
	
	/** Return the first cell of each singular component
	 * 
	 *  \param pos: index of singular component
	 *  \return one cell of 'pos' singular component
	 */
	ids	getSingCell(int pos = 0 OF_DMUTEXVD);
	/** Set a singular component
	 * 
	 *  \param 	pos: index of singular component
	 * 			cell: cell to be set
	 *  \return true if 'pos' exist
	 * 
	 *  For add a singular component use addSing
	 */
	bool setSingCell(int pos, ids cell OF_DMUTEXVD);
	/** Add a singular component
	 * 
	 *  \param cell: cell to be add
	 *  \return index of added singular component
	 */
	int addSing(ids cell OF_DMUTEXVD);
	/** Delete a singular component
	 * 
	 *  \param pos: index of singular component
	 *  \return true if successful
	 */
	bool delSing(int pos OF_DMUTEXVD);
	/* Delete all singular components
	 */
	void clearSings(OF_DMUTEXD);
	/** Return the first singular component
	 */
	sSing* getSing(OF_DMUTEXD);
	
	/** Return the position of the specified cell in list of singular components
	 */
	int inSings(ids cell OF_DMUTEXVD);
	
private:
	sSing* sings;
};
template <int size, class _Traits> ofSingularVertex<size,_Traits>::ofSingularVertex()
{
#ifdef OF_THREADS
	pthread_mutex_lock(&this->smutex);
#endif
	
	sings = NULL;
#ifdef OF_THREADS
	pthread_mutex_unlock(&this->smutex);
#endif
}
template <int size, class _Traits> ofSingularVertex<size,_Traits>::~ofSingularVertex()
{
	clearSings();
}
template <int size, class _Traits> bool ofSingularVertex<size,_Traits>::isSingular(OF_DMUTEX)
{
	return (getNumberOfSings(OF_MUTEX) >= 2);
}
template <int size, class _Traits> int ofSingularVertex<size,_Traits>::getNumberOfSings(OF_DMUTEX)
{
	OF_LOCK(smutex);
	
	sSing* s = sings;
	int i = 0;
	
	while (s)
	{
		s = s->getNext();
		i++;
	}
	
	OF_UNLOCK(smutex);
	return i;
}
template <int size, class _Traits> IDS ofSingularVertex<size,_Traits>::getSingCell(int pos OF_DMUTEXV)
{
	OF_ASSERT(pos >= 0);
	OF_LOCK(smutex);
	sSing* s = sings;
	ids c = -1;
	int i = 0;
	
	while ((s)&&(i < pos))
	{
		s = s->getNext();
		i++;
	}
	
	if (s)
		c = s->getCell();
	OF_UNLOCK(smutex);
	
	return c;
}
template <int size, class _Traits> bool ofSingularVertex<size,_Traits>::setSingCell(int pos, ids cell OF_DMUTEXV)
{
	OF_ASSERT(pos >= 0);
	OF_ASSERT(cell >= 0);
	OF_LOCK(smutex);
	sSing* s = sings;
	
	int i = -1;
	
	while ((s)&&(i < pos))
	{
		i++;
		if (i == pos)
		{
			s->setCell(cell);
			OF_UNLOCK(smutex);
			return true;
		}
		s = s->getNext();
	}
	
	OF_UNLOCK(smutex);
	return false;
}
template <int size, class _Traits> int ofSingularVertex<size,_Traits>::addSing(ids cell OF_DMUTEXV)
{
	OF_ASSERT(cell >= 0);
	OF_LOCK(smutex);
	sSing *s = sings, *ss = NULL;
	bool sair = false;
	int pos = 0;
	
	if (s)
	{
		while (!sair)
		{
			if(s->getCell() == cell)
				sair = true;
			else
			{
				if (!s->getNext())
				{
					ss = new sSing();
					OF_ASSERT(ss);
					
					s->setNext(ss);
					sair = true;
				}
				s = s->getNext();
				pos++;
			}
		}
	}
	else
	{
		ss = new sSing();
		OF_ASSERT(ss);
		sings = ss;
	}
	if (ss != NULL)
		ss->setCell(cell);
	
	OF_UNLOCK(smutex);
	
	return pos;
}
template <int size, class _Traits> bool ofSingularVertex<size,_Traits>::delSing(int pos OF_DMUTEXV)
{
	OF_ASSERT(pos >= 0);
	OF_LOCK(smutex);
	sSing* s = sings;
	sSing* ant = NULL;
	
	int i = -1;
	
	while ((s)&&(i < pos))
	{
		i++;
		if (i == pos)
		{
			if(ant)
				ant->setNext(s->getNext());
			else
				sings = s->getNext();
			
			delete s;
			OF_UNLOCK(smutex);
			
			return true;
		}
		ant = s;
		s = s->getNext();
	}
	
	OF_UNLOCK(smutex);
	return false;
}
template <int size, class _Traits> void ofSingularVertex<size,_Traits>::clearSings(OF_DMUTEX)
{
#ifdef OF_THREADS
	if(mutex)
		OF_LOCK(smutex);
#endif
	
	for(int i = (getNumberOfSings(OF_MUTEXFALSE)-1); i >=0; i--)
		delSing(i OF_MUTEXFALSEV);
	
#ifdef OF_THREADS
	if(mutex)
		OF_UNLOCK(smutex);
#endif
}
template <int size, class _Traits> SSING* ofSingularVertex<size,_Traits>::getSing(OF_DMUTEX)
{
	OF_LOCK(smutex);
	
	sSing* s = sings;
	
	OF_UNLOCK(smutex);
	return s;
}
template <int size, class _Traits> int ofSingularVertex<size,_Traits>::inSings(ids cell OF_DMUTEXV)
{
	OF_LOCK(smutex);
	
	sSing* s = sings;
	int i = 0;
	
	while ((s)&&(s->getCell() != cell))
	{
		s = s->getNext();
		i++;
	}
	OF_UNLOCK(smutex);
	
	if(s)
		return i;
	else
		return (-1);
}
#undef SPACE
#undef SSING
#undef IDS
}
#endif
