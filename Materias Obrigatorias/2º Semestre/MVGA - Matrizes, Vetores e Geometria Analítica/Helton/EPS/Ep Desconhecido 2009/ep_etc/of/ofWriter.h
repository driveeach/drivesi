#ifndef OFWRITER_H
#define OFWRITER_H
#include "ofMacros.h"
#include "ofMesh.h"
namespace of
{
//! Modelo dos escritores de arquivos
/*!
Esta classe �abstrata, devendo servir apenas de molde para novas implementa�es de escritores de arquivos.
*/
template <class _Traits>  class ofWriter
{
public:
	typedef ofMesh<_Traits> sMesh;
	//! Construtor
	ofWriter();
	//! Destrutor
	virtual ~ofWriter();
	
	//! Executa a escrita de um arquivo (salva uma malha)
	/*!
	Par�etros de entrada:
	malha : endere� de mem�ia da malha a ser salva.
	filename : nome do arquivo da malha. (destino)
	*/
	virtual bool write(sMesh* malha, char* filename) = 0;
};
template <class _Traits>  ofWriter<_Traits>::ofWriter()
{
}
template <class _Traits>  ofWriter<_Traits>::~ofWriter()
{
}
}
#endif
