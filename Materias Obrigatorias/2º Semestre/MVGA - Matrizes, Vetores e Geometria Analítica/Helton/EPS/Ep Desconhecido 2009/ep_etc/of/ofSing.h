#ifndef OFSING_H
#define OFSING_H
#include "ofMacros.h"
namespace of
{
#define SSING typename ofSing<_Traits>::sSing
#define IDS typename ofSing<_Traits>::ids
/**	Base Class of singular component in vertex
 * 
 * 	_Traits must have: ids, sSing
 */
template <class _Traits> class ofSing
{
public:
	typedef typename _Traits::ids ids;
	
	typedef ofSing<_Traits> sSing;
	/* Constructor */
	ofSing();
	/* Destrutor */
	~ofSing();
	
	/**	Return the next singular component
	 */
	sSing* getNext();
	/**	Define the next singular component
	 */
	void setNext(sSing* _next);
	
	/**	Return the cell of this singular component
	 */
	ids getCell();
	/**	Define the cell of this singular component
	 */
	void setCell(ids _cell);	
	
private:
	ids cell;
	sSing *next;
};
template <class _Traits> ofSing<_Traits>::ofSing()
{
	next = NULL;
}
template <class _Traits> ofSing<_Traits>::~ofSing()
{
}
template <class _Traits> SSING* ofSing<_Traits>::getNext()
{
	return next;
}
template <class _Traits> void ofSing<_Traits>::setNext(sSing* _next)
{
	next = _next;
}
template <class _Traits> IDS ofSing<_Traits>::getCell()
{
	return cell;
}
template <class _Traits> void ofSing<_Traits>::setCell(ids _cell)
{
	OF_ASSERT(_cell >= 0);
	
	cell = _cell;
}
#undef IDS
#undef SSING
}
#endif
