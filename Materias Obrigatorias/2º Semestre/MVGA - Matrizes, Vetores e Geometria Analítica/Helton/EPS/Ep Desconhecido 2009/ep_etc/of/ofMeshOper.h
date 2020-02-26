#ifndef OFMESHOPER_H
#define OFMESHOPER_H
#include "ofMacros.h"
#include "ofMesh.h"
namespace of
{
/**	Base Operation Class for ofMesh
 * 
 * 	_Traits must have: ids, sMesh
 */
template<class _Traits> class ofMeshOper
{
public:
	typedef typename _Traits::ids	ids;
	typedef ofMesh<_Traits>			sMesh;
protected:
	/* Constructor */
	ofMeshOper(sMesh *_mesh);
	/* Destrutor */
	~ofMeshOper();
	
	sMesh *mesh;
};
template <class _Traits> ofMeshOper<_Traits>::ofMeshOper(sMesh *_mesh)
{
	OF_ASSERT(_mesh);
	mesh = _mesh;
}
template <class _Traits> ofMeshOper<_Traits>::~ofMeshOper()
{
}
}
#endif
