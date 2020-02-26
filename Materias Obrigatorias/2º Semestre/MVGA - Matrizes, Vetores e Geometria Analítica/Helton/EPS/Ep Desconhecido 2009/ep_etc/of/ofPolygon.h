#ifndef OFPOLYGON_H
#define OFPOLYGON_H
#include "ofList.h"
namespace of
{
#define SVERTEX typename ofPolygon<_Traits>::sVertex
template <class _Traits> class ofPolygon
{
public:
	typedef typename _Traits::sVertex sVertex;
	
	ofPolygon();
	~ofPolygon();
	
	int addVertex(sVertex *v);
	int getNumberOfVertices();
	
	sVertex *getVertex(int index);
	
	sVertex *swapVertex(int index, sVertex *v);
	
private:
	ofList<sVertex*> points;
};
template <class _Traits> ofPolygon<_Traits>::ofPolygon()
{
}
template <class _Traits> ofPolygon<_Traits>::~ofPolygon()
{
}
template <class _Traits> int ofPolygon<_Traits>::addVertex(sVertex *v)
{
	OF_ASSERT(v);
	
	points.insert(v);
	
	return (points.size()-1);
}
template <class _Traits> int ofPolygon<_Traits>::getNumberOfVertices()
{
	return points.size();
}
template <class _Traits> SVERTEX* ofPolygon<_Traits>::getVertex(int index)
{
	return points.pos(index);
}
template <class _Traits> SVERTEX* ofPolygon<_Traits>::swapVertex(int index, sVertex *v)
{
	OF_ASSERT((index >= 0)&&(index < points.size()));
	sVertex *temp = points.pos(index);
	
	points.erase(index);
	points.insert(v, index);
	
	return temp;
}
#undef SVERTEX
}
#endif 
