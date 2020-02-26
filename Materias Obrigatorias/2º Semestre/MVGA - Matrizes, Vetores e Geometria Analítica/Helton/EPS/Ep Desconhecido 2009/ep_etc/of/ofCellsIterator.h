#ifndef OFCELLSITERATOR_H
#define OFCELLSITERATOR_H
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofIterator.h"
namespace of
{
#define SCELL typename ofCellsIterator<_Traits>::sCell
#define IDS typename ofCellsIterator<_Traits>::ids
template <class _Traits> class ofCellsIterator : public ofIterator<_Traits>
{
public:
	typedef typename _Traits::sCell 	sCell;
	typedef typename _Traits::ids 		ids;
	typedef ofMesh<_Traits>				sMesh;
	ofCellsIterator(sMesh *_mesh);
	~ofCellsIterator();
	bool initialize(ids init = 0);
	bool finish();
	bool notFinish();
	bool operator++();
	bool operator--();
	sCell *operator->();
	sCell *operator*();
	ids operator&();	
private:
	ids iatu, iinit;
	sCell *atu;
	int status;
	// 0 - iterator sem posicao
	// 1 - iterator em posicao normal
	// 2 - iterator no limite inferior
	// 3 - iterator no limite superior
};
template <class _Traits> ofCellsIterator<_Traits>::ofCellsIterator(sMesh *_mesh)
: ofIterator<_Traits>(_mesh)
{
	iatu = iinit = -1;
	status = 0;
}
template <class _Traits> ofCellsIterator<_Traits>::~ofCellsIterator()
{
}
template <class _Traits> bool ofCellsIterator<_Traits>::initialize(ids init)
{
	OF_ASSERT(init >= 0);
	iinit = init;
	status = 1;
	iatu = -1;
	atu = NULL;
	return ++(*this);
}
template<class _Traits> bool ofCellsIterator<_Traits>::operator++()
{
	if(status == 1)
	{
		if( this->mesh->getNumberOfCells() > 0 )
		{
			if(iatu < 0)
			{
				if( iinit > this->mesh->getCellMaxId() )
				{
					status = 3;
					return false;
				};
				iatu = iinit;
			}
			else
				iatu++;
			atu = this->mesh->getCell(iatu);
			while( (!atu->inMesh()) && (iatu + 1 <= this->mesh->getCellMaxId()) )
			{
				iatu++;
				atu = this->mesh->getCell(iatu);
			}
			if ( !atu->inMesh() )
			{
				status = 3;
				return false;
			}
			OF_ASSERT( atu );
			return true;
		}
		else
		{
			status = 3;
			return false;
		}
	}
	else
		return false;
}
template<class _Traits> bool ofCellsIterator<_Traits>::operator--()
{
	if(status == 1)
	{
		if( this->mesh->getNumberOfCells() > 0 )
		{
			if(iatu < 0)
			{
				if( iinit > this->mesh->getCellMaxId() )
				{
					// status = 3;
					return false;
				};
				iatu = iinit;
			}
			else
			{
				if( iatu == 0 )
				{
					status = 2;
					return false;				
				}
				else
					iatu--;
			}
			atu = this->mesh->getCell(iatu);
			while( (!atu->inMesh()) && (iatu - 1 >= 0) )
			{
				iatu--;
				atu = this->mesh->getCell(iatu);
			}
			if ( !atu->inMesh() )
			{
				status = 2;
				return false;
			}
			OF_ASSERT( atu );
			return true;
		}
		else
		{
			status = 2;
			return false;
		}
	}
	else
		return false;
}
template<class _Traits> SCELL* ofCellsIterator<_Traits>::operator->()
{
	return atu;
}
template<class _Traits> SCELL* ofCellsIterator<_Traits>::operator*()
{
	return atu;
}
template<class _Traits> IDS ofCellsIterator<_Traits>::operator&()
{
	return iatu;
}
template<class _Traits> bool ofCellsIterator<_Traits>::finish()
{
	return ((status == 2)||(status == 3));
}
template<class _Traits> bool ofCellsIterator<_Traits>::notFinish()
{
	return ((status == 0)||(status == 1));
}
#undef SCELL
#undef IDS
}
#endif 
