#ifndef OFREADER_H
#define OFREADER_H
#include "ofMacros.h"
#include "ofMesh.h"
namespace of
{
//! Modelo dos leitores de arquivos
/*!
Esta classe �abstrata, devendo servir apenas de molde para novas implementa�es de leitores de arquivos.
*/
template <class _Traits> class ofReader
{
public:
	
	typedef ofMesh<_Traits> sMesh;
	ofReader();
	//! Destrutor
	virtual ~ofReader();
	
	//! Executa a leitura de um arquivo
	/*!
	Par�etros de entrada:
	malha : endere� de mem�ia de destino da malha a ser carregada. J�deve estar alocado.
	filename : nome do arquivo da malha.
	*/
	virtual bool read(sMesh* malha, char* filename) = 0;
};
template <class _Traits> ofReader<_Traits>::ofReader()
{
}
template <class _Traits> ofReader<_Traits>::~ofReader()
{
}
}
#endif
