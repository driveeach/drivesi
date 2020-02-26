#ifdef OF_THREADS
#ifndef OFTREADER_H
#define OFTREADER_H
#include "ofMacros.h"
#include "ofMesh.h"
namespace of
{
	
//! Modelo dos leitores de arquivos
/*!
*/
template <class sTraits> class ofTReader
{
public:
	typedef typename sTraits::ids ids;
	
	typedef ofMesh<sTraits> sMesh;
	//! Construtor
	ofTReader(int threads_vertices, int threads_cells);
	//! Destrutor
	virtual ~ofTReader();
	void setNumberOfThreads(int threads_vertices, int threads_cells);
	
	//! Executa a leitura de um arquivo
	/*!
	Par???metros de entrada:
	malha : endere???o de mem???ria de destino da malha a ser carregada. J??? deve estar alocado.
	filename : nome do arquivo da malha.
	*/
	virtual bool read(sMesh* malha, char* xml) = 0;
protected:
	struct ofTReaderData
	{
		char *str;
	
		ids id;
		ids start;
		ids end;
		sMesh *malha;
		ofXmlParser *config;
		
	};
	int nthreadsv, nthreadsc;
};
template <class sTraits> ofTReader<sTraits>::ofTReader(int threads_vertices, int threads_cells)
{
	setNumberOfThreads(threads_vertices, threads_cells);
}
template <class sTraits> ofTReader<sTraits>::~ofTReader()
{
}
template <class sTraits> void ofTReader<sTraits>::setNumberOfThreads(int threads_vertices, int threads_cells)
{
	OF_ASSERT(threads_vertices > 0);
	OF_ASSERT(threads_cells > 0);
	nthreadsv = threads_vertices;
	nthreadsc = threads_cells;
}
}
#endif
#endif
