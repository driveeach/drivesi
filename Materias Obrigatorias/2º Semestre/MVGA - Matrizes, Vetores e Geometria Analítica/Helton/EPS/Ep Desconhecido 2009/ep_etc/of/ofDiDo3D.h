#ifndef OFDIDO3D_H
#define OFDIDO3D_H
namespace of
#define IDS typename ofDido3d<_Traits>::ids
{
template <class _Traits> class ofDido3d
{
public:
	typedef typename _Traits::space space;
	typedef typename _Traits::ids ids;
	typedef typename _Traits::sVertex sVertex;
	typedef typename _Traits::sCell sCell;
	typedef typename _Traits::sMesh sMesh;
	ofDido3d(sMesh *_mesh);
	~ofDido3d();
	/**
find the cell (in the new mesh)
in which the vertex must be inside
*/
	ids findCell(space *alpha,sVertex *v,ids cid=0);
	ids findCell(space *alpha,space *coords,ids cid=0);
	/**
	solve a linear system by gauss elimination
	*/
	bool solveSystem(int n, space **a, space *x);
private:
	sMesh *mesh;
	
	bool findBaricentricCoordinates(sVertex *v,ids ic, space *alpha,int dim=4);
	bool findBaricentricCoordinates(space *coords,ids ic, space *alpha);
};
template <class _Traits> ofDido3d<_Traits>::ofDido3d(sMesh *_mesh)
{
	OF_ASSERT(_mesh);
	mesh = _mesh;
}
template <class _Traits> ofDido3d<_Traits>::~ofDido3d()
{
}
template<class _Traits> bool ofDido3d<_Traits>::solveSystem(int n, space **a, space *x)
{
	int   i,j,k;
  space  maxi,maxj,aux;
  space *y;
  /* Transformando a matriz em uma matriz triangular */
  /* superior                                        */         
  /* looping do pivo */
  for (k = 0; k < n; k++) {
    /* determinado o maior elemento em valor absoluto */
    /* na coluna k a partir da linha k                */
    j    = k;
    maxj = fabs(a[k][k]);
    for (i = k+1; i <= n; i++) {
      maxi = fabs(a[i][k]);
      if (maxi > maxj) {
	j    = i;
	maxj = maxi;
      } /* if */
    } /* for i */
    /* verificando se o sistema e nao inversivel */
    if (maxj < 1.0e-10) {
      /* sistema nao inversivel */
      return 1;
    } /* if */
    /* escolhendo o pivo */
    if (j != k) {
      y    = a[j];
      a[j] = a[k];
      a[k] = y;
      
    } /* if */
    /* zerando os elementos na coluna k abaixo da linha k */
    for (i = k+1; i <= n; i++) {
      aux = a[i][k]/a[k][k];
      for (j = k+1; j <= n+1; j++) {
	a[i][j] -= aux*a[k][j];
      } /* for j */
    } /* for i */
  } /* for k */
  /* verificando se o sistema e nao inversivel */
  if (fabs(a[n][n]) < 1.0e-10) {
    /* sistema nao inversivel */
    return false;
  } /* if */
  /* resolvendo o sistema triangular superior */
  x[n] = a[n][n+1]/a[n][n];
  
  for (i = n-1; i >= 0; i--) {
    for (j = i+1, aux = 0.0; j <= n; j++) aux += a[i][j]*x[j];
    x[i] = (a[i][n+1]-aux)/a[i][i];
  } /* for i */
  /* retornano que houve sucesso */
  return true;
}
template <class _Traits> bool ofDido3d<_Traits>::findBaricentricCoordinates(sVertex *v,ids ic,space *alpha,int dim)
{
	sCell *c=mesh->getCell(ic);
	
	space **m; //! matrix used to store the system
	int i;		//! Counter
	
	m = new(space*[6]);
	sVertex *vi[4];
	for(i=0;i<dim;i++)
	{
		vi[i]=mesh->getVertex(c->getVertexId(i));
	}
	
   for (i = 0; i < 6; i++)
    	m[i] = new (space[6]);
	//Coluna 1 - matriz m:
	for(i=0;i<dim;i++)
	{
		m[0][i] = 1.0;
		m[1][i] = vi[i]->getCoord(0);
		m[2][i] = vi[i]->getCoord(1);
		m[3][i] = vi[i]->getCoord(2);
	}
	
	//vetor B:
	m[0][dim] = 1.0;
	m[1][dim] = v->getCoord(0);
	m[2][dim] = v->getCoord(1);
	m[3][dim] = v->getCoord(2);  
	
	bool ok = solveSystem(3,m,alpha);
	for(i=0;i<6;i++)
		delete[] m[i];
	delete[] m;
	return ok;
}
template <class _Traits> bool ofDido3d<_Traits>::findBaricentricCoordinates(space *coords,ids ic,space *alpha)
{
	sCell *c=mesh->getCell(ic);
	
	space **m; //! matrix used to store the system
	int i;		//! Counter
	
	m = new(space*[6]);
	sVertex *v1,*v2,*v3,*v4;
	v1=mesh->getVertex(c->getVertexId(0));
	v2=mesh->getVertex(c->getVertexId(1));
	v3=mesh->getVertex(c->getVertexId(2));
	v4=mesh->getVertex(c->getVertexId(3));
   for (i = 0; i < 6; i++)
    	m[i] = new (space[6]);
	//Coluna 1 - matriz m:
	m[0][0] = 1.0;
	m[1][0] = v1->getCoord(0);
	m[2][0] = v1->getCoord(1);
	m[3][0] = v1->getCoord(2);
	
	//Coluna 2 - matriz m:
	m[0][1] = 1.0;
	m[1][1] = v2->getCoord(0);
	m[2][1] = v2->getCoord(1);
	m[3][1] = v2->getCoord(2);
	
	//Coluna 3 - matriz m:
	m[0][2] = 1.0;
	m[1][2] = v3->getCoord(0);
	m[2][2] = v3->getCoord(1);
	m[3][2] = v3->getCoord(2);
	
	//Coluna 4 - matriz m:
	m[0][3] = 1.0;
	m[1][3] = v4->getCoord(0);
	m[2][3] = v4->getCoord(1);
	m[3][3] = v4->getCoord(2);
	
	//vetor B:
	m[0][4] = 1.0;
	m[1][4] = coords[0];
	m[2][4] = coords[1];
	m[3][4] = coords[2];  
	
	bool ok = solveSystem(3,m,alpha);
	for(i=0;i<6;i++)
		delete[] m[i];
	delete[] m;
	return ok;
}
template <class _Traits> IDS ofDido3d<_Traits>::findCell(space *alpha,sVertex *v, ids cid)
{
	
	space tmp;
	bool ok=false;
	ids c=cid;
	int i,k,i1,i2,i3,i4;
	while (!ok)
	{
		tmp=10000000000000.0;
		if(c>=0)
			findBaricentricCoordinates(v,c,alpha);
		else return -1;
		for (i=0;i<4;i++)
		{
			if(alpha[i]<tmp)
			{
				tmp=alpha[i];
				k=i;
			}
		}
		if (tmp>=-0.00000000000001)
			ok=true;
		else
		{
			sCell *cp=mesh->getCell(c);
			c=cp->getMateId(k);
			i1=cp->getMateId(0);
			i2=cp->getMateId(1);
			i3=cp->getMateId(2);
			i4=cp->getMateId(3);
			i4=i4;
		}
	};
	
	return c;
}
template <class _Traits> IDS ofDido3d<_Traits>::findCell(space *alpha,space *coords, ids cid)
{
	
	space tmp;
	bool ok=false;
	ids c=cid;
	int i,k,i1,i2,i3,i4;
	while (!ok)
	{
		tmp=10000000000000.0;
		if(c>=0)
			findBaricentricCoordinates(coords,c,alpha);
		else return -1;
		for (i=0;i<4;i++)
		{
			if(alpha[i]<tmp)
			{
				tmp=alpha[i];
				k=i;
			}
		}
		if (tmp>=-0.00000000000001)
			ok=true;
		else
		{
			sCell *cp=mesh->getCell(c);
			c=cp->getMateId(k);
			i1=cp->getMateId(0);
			i2=cp->getMateId(1);
			i3=cp->getMateId(2);
			i4=cp->getMateId(3);
			i4=i4;
		}
	};
	
	return c;
}
}
#undef IDS
#endif
