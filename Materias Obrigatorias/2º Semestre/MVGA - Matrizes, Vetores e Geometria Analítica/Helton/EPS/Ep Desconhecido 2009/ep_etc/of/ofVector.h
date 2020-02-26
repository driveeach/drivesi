#ifndef OFVECTOR_H
#define OFVECTOR_H
#include "ofMacros.h"
namespace of
{
/**	Base Vector
 * 
 * 	This is a block allocation version of vector with free space management
 */
template <class T, class ids> class ofVector
{
public:
	/**	Constructor
	 * 
	 * 	\param block_size: the size of each block (The maximum capacity of vector if : block_size * block_size)
	 */
	ofVector(ids block_size);
	
	/**	Destructor
	 */
	~ofVector();
	
	/** Return the specified element
	 * 
	 * \param index: position of element
	 */
	T& operator[](ids index);
	
	/**	Return a free position in vector
	 */
	ids getFree();
	/**	Define the number of elements in vector
	 * 
	 * 	(DONT USE)
	 */
	void setSize(ids size);
	
	/**	Return the number of elements in vector
	 */
	ids getSize();
	
	/**	Return the maximum position in vector
	 */
	ids getMaxId();
	/**	Delete a position in vector
	 * 
	 * 	\return true if this position was deleted
	 */
	bool free(ids index);
	
private:
	
	T** vetor;
	int block;
	
	ids  nt, ntmax, first, last;
 	bool hasfree;
};
template <class T, class ids> ofVector<T,ids>::ofVector(ids block_size)
{
	OF_ASSERT(block_size > 0);
	block = block_size;
	
	vetor = new T*[block];
	OF_ASSERT(vetor);
	for(ids off1=0; off1 < block; off1++)
		vetor[off1] = NULL;
		
	nt = ntmax = 0;
	hasfree = false;
}
template <class T, class ids> ofVector<T,ids>::~ofVector()
{
	for(ids off1=0; off1 < block; off1++)
		if(vetor[off1])
			delete [] vetor[off1];
		
		delete [] vetor;
}
template <class T, class ids> T& ofVector<T,ids>::operator [](ids index)
{
	OF_ASSERT(index >= 0);
	if(vetor[index / block])
 		return vetor[index / block][index % block];
	else
	{
		vetor[index / block] = new T[block];
		OF_ASSERT(vetor[index / block]);
		return vetor[index / block][index % block];
	}
}
template <class T, class ids> ids ofVector<T,ids>::getFree()
{
	ids i;
	
	if (!hasfree)
	{
		i = ntmax;
		ntmax++;
	}
	else
	{
		i = first;
		
		if(last == first)
			hasfree = false;
		else
#ifdef OF_THREADS
			first = (*this)[i].getPosition(false);
#else
			first = (*this)[i].getPosition();
#endif
	}
#ifdef OF_THREADS
	(*this)[i].setInMesh(true, false);
#else
	(*this)[i].setInMesh(true);
#endif
	nt++;
	return i;
}
	
template <class T, class ids> void ofVector<T,ids>::setSize(ids size)
{
	if(ntmax < size)
		ntmax = size; 
	
	nt = size;
}
template <class T, class ids> ids ofVector<T,ids>::getSize()
{
	return nt; 
}
template <class T, class ids> ids ofVector<T,ids>::getMaxId()
{
	return ntmax;
}
template <class T, class ids> bool ofVector<T,ids>::free(ids index)
{
	OF_ASSERT(index >= 0);
	
	T* v = &(*this)[index];
	
	OF_ASSERT(v->inMesh());
	if(hasfree)
	{
#ifdef OF_THREADS
		v->setPosition(first, false);
#else
		v->setPosition(first);
#endif
		first = index;
	}
	else
	{
#ifdef OF_THREADS
	v->setInMesh(false, false);
#else
	v->setInMesh(false);
#endif
		hasfree = true;
		first = index;
		last = first;
	}
	nt--;
	
	return true;
}
}
#endif
