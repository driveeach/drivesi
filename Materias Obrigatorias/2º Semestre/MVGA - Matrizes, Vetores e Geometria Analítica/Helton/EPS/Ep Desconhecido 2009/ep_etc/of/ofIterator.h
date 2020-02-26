#ifndef OFITERATOR_H
#define OFITERATOR_H
#include "ofMacros.h"
#include "ofMesh.h"
namespace of
{
//! Iterator da estrutura of
/*!
*/
template<class _Traits> class ofIterator
{
public:
	typedef ofMesh<_Traits> sMesh;

protected:
	//! Construtor
	ofIterator(sMesh *_mesh);
	//! Destrutor
	~ofIterator();

	sMesh *mesh;
};

template <class _Traits> ofIterator<_Traits>::ofIterator(sMesh *_mesh)
{
	OF_ASSERT(_mesh);
	mesh = _mesh;
}

template <class _Traits> ofIterator<_Traits>::~ofIterator()
{
}

}
#endif
