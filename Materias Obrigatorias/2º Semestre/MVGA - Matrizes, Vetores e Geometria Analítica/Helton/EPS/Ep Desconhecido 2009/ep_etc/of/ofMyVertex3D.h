#ifndef OFMYVERTEX3D_H
#define OFMYVERTEX3D_H
#include "ofMacros.h"
#include "ofVertex3D.h"
namespace of
{
/** Base class of 3D vertex
 * 
*/
template <class _Traits> class ofMyVertex3D : public ofVertex3D<_Traits>
{
public:
	typedef typename _Traits::space space;
	/* Constructor */
	ofMyVertex3D();	
	/* Destructor */
	~ofMyVertex3D();

	ofMyVertex3D(space x, space y, space z);
	//usado no Crust para identificar se pertence a primeira triangulação de delaunay
	bool first;

	unsigned int edgeAtt;
	//diz se o vertice é um ponto de max, min, ou nd
	unsigned int type;
	//armazena os 8 vizinhos do vertice
	int neighbor[8];
	//diz se ja foi usado ou não em alguma aresta como um ponto pode constar em até duas arestas, seu valor maximo deverá se 2
	unsigned int set;
	
	void setType(int t){type = t;};
	void setSet(int ok){set = ok;};
	void setFirst(bool ok){first = ok;};
	bool getFirst(){return first;};
};
template <class _Traits> ofMyVertex3D<_Traits>::ofMyVertex3D()
{
	type =0;
	edgeAtt=0;
}
template <class _Traits> ofMyVertex3D<_Traits>::ofMyVertex3D(space x, space y, space z)
{
	this->setCoord(0,x);
	this->setCoord(1,y);
	this->setCoord(2,z);
}
template <class _Traits> ofMyVertex3D<_Traits>::~ofMyVertex3D()
{
}


}
#endif
