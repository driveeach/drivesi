#ifndef OFPLANE_H
#define OFPLANE_H
namespace of
{
#define SPACE typename ofPlane<_Traits>::space
template <class _Traits> class ofPlane{
	
public:
		typedef typename _Traits::space space;
		typedef typename _Traits::sVertex sVertex;
		typedef typename _Traits::sCell sCell;
		typedef typename _Traits::ids ids;
		typedef typename _Traits::sMesh sMesh;
		
	
	ofPlane();
	~ofPlane();
	ofPlane(ids ic,sMesh *mesh);
	ofPlane(space a, space b, space c, space d);
	ofPlane(space px,space py,space pz, space dx,space dy,space dz);
	
	
	
	void vecCross(sVertex *v1,sVertex *v2, space &x, space &y, space &z);
	void projection(space px,space py, space pz, space &x, space &y, space &z);
	
	 space get_A();
	 space get_B();
	 space get_C();
	 space get_D();
	 void set_A(space x){ A=x;}
	 void set_B(space x){ B=x;}
	 void set_C(space x){C=x;}
	 void set_D(space x){ D=x;}
private:
	space A;
	space B;
	space C;
	space D;
};
template <class _Traits> ofPlane<_Traits>::ofPlane()
{
}
template <class _Traits> ofPlane<_Traits>::~ofPlane()
{
}
template <class _Traits> ofPlane<_Traits>::ofPlane(space a, space b, space c, space d)
{
	A=a;B=b;C=c;D=d;
}
template <class _Traits> ofPlane<_Traits>::ofPlane(space px,space py,space pz, space dx,space dy,space dz)
{
		A = dx; B = dy; C = dz; D = -dx*px - dy*py - dz*pz;
}
template <class _Traits> ofPlane<_Traits>::ofPlane(ids ic,sMesh *mesh)
{
	sCell *c=mesh->getCell(ic);
	int i;
	sVertex *p[3];
	sVertex v1,v2;
	for(i=0;i<3;i++)
		p[i]=mesh->getVertex(c->getVertexId(i));
	for(i=0;i<3;i++)
	{
		v1.setCoord(i,p[1]->getCoord(i)-p[0]->getCoord(i));
		v2.setCoord(i,p[2]->getCoord(i)-p[1]->getCoord(i));
	}
	space dx,dy,dz;
	vecCross(&v1,&v2,dx,dy,dz);
	space norma=sqrt(dx*dx+dy*dy+dz*dz);
	if(norma==0.0)
		norma=1.0;
	dx=dx/norma;
	dy=dy/norma;
	dz=dz/norma;
	A = dx; B = dy; C = dz; D = -dx*p[0]->getCoord(0) - dy*p[0]->getCoord(1) - dz*p[0]->getCoord(2);
}
template <class _Traits> void ofPlane<_Traits>::vecCross(sVertex *v1,sVertex *v2, space &x, space &y, space &z)
{
	x=v1->getCoord(1)*(v2->getCoord(2)) - v1->getCoord(2)*(v2->getCoord(1));
    y=v1->getCoord(2)*(v2->getCoord(0)) - v1->getCoord(0)*(v2->getCoord(2));
    z=v1->getCoord(0)*(v2->getCoord(1)) - v1->getCoord(1)*(v2->getCoord(0));
}
template <class _Traits> SPACE ofPlane<_Traits>::get_A()
{
	return A;
}
template <class _Traits> SPACE ofPlane<_Traits>::get_B()
{
	return B;
}
template <class _Traits> SPACE ofPlane<_Traits>::get_C()
{
	return C;
}
template <class _Traits> SPACE ofPlane<_Traits>::get_D()
{
	return D;
}
template <class _Traits> void ofPlane<_Traits>::projection(space px,space py, space pz, space &x, space &y, space &z)
{
		// the equation of the plane is Ax+By+Cz+D=0
	  // the normal direction is (A,B,C)
	  // the projected point is p-lambda(A,B,C) where
	  // A(x-lambda A) + B(y-lambda B) + C(z-lambda C) + D = 0
	  space num = A*px + B*py + C*pz + D;
	  space den = A*A + B*B + C*C;
	  space lambda = num / den;
	  x = px - lambda * A;
	  y = py - lambda * B;
	  z = pz - lambda * C;
	}
#undef SPACE
}
#endif
