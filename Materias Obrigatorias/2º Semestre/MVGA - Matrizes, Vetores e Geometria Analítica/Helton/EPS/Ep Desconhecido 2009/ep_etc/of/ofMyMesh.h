#ifndef OFMYMESH_H
#define OFMYMESH_H
#include "ofMacros.h"
#include "ofVector.h"
#include "ofMesh.h"
namespace of
{
#define SPACE15 typename ofMyMesh<_Traits>::space
/**	My Mesh Class
 * 
 * 	This class must be initialized with elements types (vertices and cells) and the operation class.
 * 	Operation class depends of the vertices and cells dimensions.
 *  
 * 	_Traits must have: space, ids, sVertex, sCell, sOper
 */
	template <class _Traits> class ofMyMesh : public ofMesh<_Traits>
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sOper sOper;
	
	/**	Constructor
	 * 
	 * 	\param block_size: size of vector blocks. The maximum size of vector is block_size * block_size
	 */
	ofMyMesh(ids block_size = 5000);
	
	/* Destructor */
	~ofMyMesh();

	
	
 private:
	
	
	

#ifdef OF_THREADS
 	pthread_mutex_t vertices_mutex;
 	pthread_mutex_t cells_mutex;
#endif
};


template <class _Traits> ofMyMesh<_Traits>::ofMyMesh(ids block_size)
{	
	
}



template <class _Traits> ofMyMesh<_Traits>::~ofMyMesh()
{

}



#undef SPACE15
}
#endif
