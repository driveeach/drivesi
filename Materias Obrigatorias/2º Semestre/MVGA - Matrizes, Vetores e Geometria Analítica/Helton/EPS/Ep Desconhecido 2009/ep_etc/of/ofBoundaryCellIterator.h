#ifndef OF_BOUNDARYCELLITERATOR_H
#define OF_BOUNDARYCELLITERATOR_H
#include "ofMacros.h"
#include "ofIterator.h"
#include "ofList.h"
#define SCELL typename ofBoundaryCellIterator<_Traits>::sCell
template <class _Traits> class ofBoundaryCellIterator : public ofIterator<typename _Traits::sCell,_Traits>
{
public:
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sSing sSing;
	ofBoundaryCellIterator();
	~ofBoundaryCellIterator();
	bool initialize(sCell* init, int edge, bool cyclical = false);
	bool finish();
	bool notFinish();
	// Sentido anti-horário
	void operator++();
	// Sentido horário
	void operator--();
	sCell *operator->();
	sCell *operator*();
	int getEdge();
private:
	sCell* atu, *markc;
	int status;
	// 0 - iterator sem posicao
	// 1 - iterator em posicao normal
	// 2 - iterator no limite inferior
	// 3 - iterator no limite superior
	int eatu, marke;
	bool circ;
};
template <class _Traits> ofBoundaryCellIterator<_Traits>::ofBoundaryCellIterator()
{
	status = 0;
	atu = NULL;
	eatu = -1;
}
template <class _Traits> ofBoundaryCellIterator<_Traits>::~ofBoundaryCellIterator()
{
}
template <class _Traits> bool ofBoundaryCellIterator<_Traits>::initialize(sCell* init, int edge, bool cyclical)
{
	OF_ASSERT(init && (edge >= 0));
	circ = cyclical;
	atu = init;
	status = 0;
	eatu = edge;
	markc = atu;
	marke = eatu;
	status = 1;
	++(*this);
	return true;
}
template<class _Traits> void ofBoundaryCellIterator<_Traits>::operator++()
{
	sCell *tempc = atu, *ant;
	int tempe = eatu;
	if((status == 1)||(status == 2))
	{
		while(tempc->getMate((tempe+1)%3))
		{
			ant = tempc;
			tempc = ant->getMate((tempe+1)%3);
			tempe = tempc->getMateVertexIndex(ant);		
		}
		eatu = (tempe + 1)%3;
		atu = tempc;
		if((!circ)&&(atu == markc)&&(eatu == marke))
			status = 3;
	}
}
template<class _Traits> void ofBoundaryCellIterator<_Traits>::operator--()
{
	sCell *tempc = atu, *ant;
	int tempe = eatu;
	if((status == 1)||(status == 3))
	{
		while(tempc->getMate((tempe+2)%3))
		{
			ant = tempc;
			tempc = ant->getMate((tempe+2)%3);
			tempe = tempc->getMateVertexIndex(ant);		
		}
		eatu = (tempe + 2)%3;
		atu = tempc;
		if((!circ)&&(atu == markc)&&(eatu == marke))
			status = 2;
	}
}
template<class _Traits> SCELL* ofBoundaryCellIterator<_Traits>::operator->()
{
	return atu;
}
template<class _Traits> SCELL* ofBoundaryCellIterator<_Traits>::operator*()
{
	return atu;
}
template<class _Traits> bool ofBoundaryCellIterator<_Traits>::finish()
{
	return ((status == 2)||(status == 3));
}
template<class _Traits> bool ofBoundaryCellIterator<_Traits>::notFinish()
{
	return ((status == 0)||(status == 1));
}
template<class _Traits> int ofBoundaryCellIterator<_Traits>::getEdge()
{
	if(status != 0)
	{
		return eatu;
	}
	return -1;
}
#undef SCELL
#endif 
