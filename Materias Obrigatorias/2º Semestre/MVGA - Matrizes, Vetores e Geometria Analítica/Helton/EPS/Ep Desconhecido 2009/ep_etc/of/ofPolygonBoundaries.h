#ifndef OFPOLYGONBOUNDARIES_H
#define OFPOLYGONBOUNDARIES_H
#include "ofList.h"
#define SVERTEX15 typename ofPolygonBoundaries<size,_Traits>::sVertex
#define SPACE typename ofPolygonBoundaries<size,_Traits>::space
template <int size, class _Traits> class ofPolygonBoundaries
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::sVertex sVertex;
	ofPolygonBoundaries();
	~ofPolygonBoundaries();
	int addBoundary(int numberOfPoints, space **coords);
	void addOnBoundary(int boundary, int position, space *coords);
	int getNumberOfBoundaries();
	int getNumberOfPoints(int boundary);
	void setVertex(int boundary, int pos, sVertex *v);
	sVertex *getVertex(int boundary, int pos);
	space getCoord(int boundary, int pos, int coord);
typedef struct
	{
		sVertex *v;
		space coords[size];
	} node;
	
	typedef struct
	{
		of::ofList<node*> points;
	} bound;
	of::ofList<bound*> bounds;
};

template <int size, class _Traits> ofPolygonBoundaries<size,_Traits>::ofPolygonBoundaries()
{
}
template <int size, class _Traits> ofPolygonBoundaries<size,_Traits>::~ofPolygonBoundaries()
{
	for(int i = 0; i < bounds.getSize(); i++)



		for(int j = 0; j < bounds.getPos(i)->points.getSize(); j++)



			delete bounds.getPos(i)->points.getPos(j);



}







template <int size, class _Traits> int ofPolygonBoundaries<size,_Traits>::addBoundary(int numberOfPoints, space **coords )



{



	int i;



	node *n;



	



	bound *b = new bound;



	



	for(i = 0; i < numberOfPoints; i++)



	{



		n = new node;



		n->v = NULL;



		n->coords[0] = coords[i][0];



		n->coords[1] = coords[i][1];



		



		b->points.insert(n);



	}



	



	bounds.insert(b);



	



	return (bounds.getSize()-1);



}







template <int size, class _Traits> void ofPolygonBoundaries<size,_Traits>::setVertex(int boundary, int pos, sVertex *v)



{



	bounds.getPos(boundary)->points.getPos(pos)->v = v;




	//v->f = boundary + 1.0;



}







template <int size, class _Traits> SVERTEX15 *ofPolygonBoundaries<size,_Traits>::getVertex(int boundary, int pos)



{



	return bounds.getPos(boundary)->points.getPos(pos)->v;



}







template <int size, class _Traits> SPACE ofPolygonBoundaries<size,_Traits>::getCoord(int boundary, int pos, int coord)



{



	return bounds.getPos(boundary)->points.getPos(pos)->coords[coord];



}







template <int size, class _Traits> int ofPolygonBoundaries<size,_Traits>::getNumberOfBoundaries()



{



	return bounds.getSize();



}







template <int size, class _Traits> int ofPolygonBoundaries<size,_Traits>::getNumberOfPoints(int boundary)



{



	return bounds.getPos(boundary)->points.getSize();



}







template <int size, class _Traits> void ofPolygonBoundaries<size,_Traits>::addOnBoundary(int boundary, int position, space *coords)



{



	node *n = new node;



	n->v = NULL;



	n->coords[0] = coords[0];



	n->coords[1] = coords[1];



	



	bounds.getPos(boundary)->points.insert(n,position);



}











#undef SVERTEX15



#undef SPACE







#endif 



