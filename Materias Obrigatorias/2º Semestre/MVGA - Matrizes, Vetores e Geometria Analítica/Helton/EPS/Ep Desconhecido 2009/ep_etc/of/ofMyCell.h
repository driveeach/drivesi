#ifndef OFMYCELL_H
#define OFMYCELL_H
#include "ofMacros.h"
#include "ofBase.h"
#include "ofCell2D.h"
namespace of
{
#define IDS typename ofMyCell< _Traits>::ids
//!  Celula modificada para o algoritmo de Ruppert
/** Base class of cell
 * 
 * 	size is the dimension of cell:
 * 		3 - triangle
 * 		4 - tetrahedron
 * 
 *  _Traits must have typenames: 	ids
*/
template <class _Traits> class ofMyCell : public ofCell2D<_Traits>
{
public:
	typedef typename _Traits::ids ids;
	
	/**	Constructor
	 *  
	 * 	Initialize vertices and mates ids
	 */
	ofMyCell();
	/**	Destructor
	 */
	virtual ~ofMyCell(){};
	/** Define the flag of the Cell
	 * 
	 * 	\param index: value of the flag
	 */
	void setFlag(bool f);
	/**	Return the flag of the Cell
	 
	 */
	bool getFlag(){return flag;};
	/** Define if the edge opposite to the vertex  i belongs to the restriction
	 * 
	 * 	\param index: position of vertex
	 * 	\param edge: bool 
	 */
	void setEdgeRestriction(int index, bool b);
	/**	Verify if the opposite edge belongs to the restriction
	 * 
	 * 	\param index: position of vertex
	 */
	bool isEdgeRestriction(int index OF_DMUTEXVD);
	/**	Set all boundary marks as false
	 * 
	 * 
	 */
	void resetBoundaryMarks();
private:
	bool rest[3];
	bool flag;
};



template <  class _Traits> void ofMyCell< _Traits>::setFlag(bool f)
{
	flag=f;
}
template <  class _Traits> ofMyCell< _Traits>::ofMyCell()
{
	int i;
	for (i=0;i<3;i++)
		rest[i]=false;
	flag=false;
}
template <  class _Traits> void ofMyCell< _Traits>::resetBoundaryMarks()
{
	int i;
	for (i=0;i<3;i++)
		rest[i]=false;
	
}
template <  class _Traits> bool ofMyCell< _Traits>::isEdgeRestriction(int index OF_DMUTEXV)
{
	OF_ASSERT((index >= 0)&&(index < 3));
	OF_LOCK(smutex);
	
	bool temp = rest[index];
	
	OF_UNLOCK(smutex);
	
	return temp;
}
template <  class _Traits> void ofMyCell< _Traits>::setEdgeRestriction(int index, bool b)
{
	OF_ASSERT((index >= 0)&&(index < 3));
	OF_LOCK(smutex);
	rest[index] = b;
	
	OF_UNLOCK(smutex);
}
#undef IDS
}
#endif
