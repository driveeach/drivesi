#include "TrackBall.h"
#include <iostream>
#include <math.h>

/*====================== TrackBall  ==================================*/

/*===================================================================*/
TrackBall::TrackBall(float p1x, float p1y, float p2x, float p2y, bool lock_x, bool lock_y)
{
    float axis[3];  /* Axis of rotation */
    float ang_phi;  /* angle to rotate */

    float p1[3], 
		  p2[3], 
		  d[3];
    float t;

    this->size = TRACKBALLSIZE;

	auxVector::vecZero(this->currentQuat);  // rotation 0 in the beginning
    
	this->currentQuat[3] = 1.0;

    if (p1x == p2x && p1y == p2y) 
   		return; 
    if (lock_x)
	{
		auxVector::vecSet(p1, 0, p1y, this->Project (this->size, p1x, p1y));
		auxVector::vecSet(p2, 0, p2y, this->Project(this->size, p2x, p2y));

		auxVector::vecSet(p1, 0, p1y, this->Project (this->size, p1x, p1y));
		auxVector::vecSet(p2, 0, p2y, this->Project(this->size, p2x, p2y));
	}
	else if (lock_y)
	{
		auxVector::vecSet(p1, p1x, 0, this->Project (this->size, p1x, p1y));
		auxVector::vecSet(p2, p2x, 0, this->Project(this->size, p2x, p2y));

		auxVector::vecSet(p1, p1x, 0, this->Project (this->size, p1x, p1y));
		auxVector::vecSet(p2, p2x, 0, this->Project(this->size, p2x, p2y));
	}
	else
	{
		auxVector::vecSet(p1, p1x, p1y, this->Project (this->size, p1x, p1y));
		auxVector::vecSet(p2, p2x, p2y, this->Project(this->size, p2x, p2y));

		auxVector::vecSet(p1, p1x, p1y, this->Project (this->size, p1x, p1y));
		auxVector::vecSet(p2, p2x, p2y, this->Project(this->size, p2x, p2y));
	}

	auxVector::vecCross_Prod(p2, p1, axis);
	
	auxVector::vecSub(p1, p2, d);
    
	t = (float) (auxVector::vecLength(d) / (2.0 * this->size));

    if (t > 1.0) 
		t = 1.0;
    
	if (t < -1.0) 
		t = -1.0;
	
	ang_phi = (float) (2.0 * asin(t));

    this->Axis_Quat (axis, ang_phi, this->currentQuat);
}


/*===================================================================*/
TrackBall::TrackBall()
{
    this->size = TRACKBALLSIZE;

	auxVector::vecZero (this->currentQuat);
    this->currentQuat[3] = 1.0;
}

/*===================================================================*/
TrackBall::~TrackBall()
{

}


/*===================================================================*/
void TrackBall::Axis_Quat(float axis[3], float ang_phi, float quat[4])
{
	auxVector::vecNormalize (axis);
	auxVector::vecCopy(axis, quat);

	auxVector::vecScale(quat,(float)sin(ang_phi/2.0));

    quat[3] = (float) cos(ang_phi/2.0);
}

/*===================================================================*/
float TrackBall::Project(float radius, float x, float y)
{
    float d, 
		  t, 
		  z;

    d = (float) sqrt(x * x + y * y);

    if (d < radius * 0.70710678118654752440) 
	{    /* Inside sphere */
        z = (float) sqrt(radius * radius - d*d);
    } 
	else 
	{           /* On hyperbola */
        t = (float) (radius / 1.41421356237309504880);
        z = t*t / d;
    }
    return z;
}

/*===================================================================*/
void TrackBall::Add_Quats(float quat[4])
{
    float temp1[4], 
		  temp2[4], 
		  temp3[4],
          tempf[4];

	auxVector::vecZero(this->currentQuat);  // rotation 0 in the beginning
	this->currentQuat[3] = 1.0;

	auxVector::vecCopy(quat,temp1);
    auxVector::vecScale(temp1, this->currentQuat[3]);

    auxVector::vecCopy(this->currentQuat, temp2);
    auxVector::vecScale(temp2, quat[3]);

    auxVector::vecCross_Prod(this->currentQuat, quat, temp3);
    auxVector::vecAdd(temp1, temp2, tempf);

    auxVector::vecAdd(temp3, tempf, tempf);

    tempf[3] = quat[3] * this->currentQuat[3] - auxVector::vecDot_Prod(quat, this->currentQuat);

    this->currentQuat[0] = tempf[0];
    this->currentQuat[1] = tempf[1];
    this->currentQuat[2] = tempf[2];
    this->currentQuat[3] = tempf[3];

    this->quatNormalize (this->currentQuat);

}


/*===================================================================*/
void TrackBall::quatNormalize(float quat[4])
{
    int i;
    float mag;

    mag = (quat[0] * quat[0] + quat[1] * quat[1] + quat[2] * quat[2] + quat[3] * quat[3]);
    
	for (i = 0; i < 4; i++) 
		quat[i] /= mag;
}

/*===================================================================*/
void TrackBall::Build_Matrix()
{
	this->matrix[0][0] = (float) (1.0 - 2.0 * (this->currentQuat[1] * this->currentQuat[1] + this->currentQuat[2] * this->currentQuat[2]));
	this->matrix[0][1] = (float) (2.0 * (this->currentQuat[0] * this->currentQuat[1] - this->currentQuat[2] * this->currentQuat[3]));
	this->matrix[0][2] = (float) (2.0 * (this->currentQuat[2] * this->currentQuat[0] + this->currentQuat[1] * this->currentQuat[3]));
	this->matrix[0][3] = 0.0f;

	this->matrix[1][0] = (float) (2.0 * (this->currentQuat[0] * this->currentQuat[1] + this->currentQuat[2] * this->currentQuat[3]));
	this->matrix[1][1] = (float) (1.0 - 2.0 * (this->currentQuat[2] * this->currentQuat[2] + this->currentQuat[0] * this->currentQuat[0]));
	this->matrix[1][2] = (float) (2.0 * (this->currentQuat[1] * this->currentQuat[2] - this->currentQuat[0] * this->currentQuat[3]));
	this->matrix[1][3] = 0.0f;

	this->matrix[2][0] = (float) (2.0 * (this->currentQuat[2] * this->currentQuat[0] - this->currentQuat[1] * this->currentQuat[3]));
	this->matrix[2][1] = (float) (2.0 * (this->currentQuat[1] * this->currentQuat[2] + this->currentQuat[0] * this->currentQuat[3]));
	this->matrix[2][2] = (float) (1.0 - 2.0 * (this->currentQuat[1] * this->currentQuat[1] + this->currentQuat[0] * this->currentQuat[0]));
	this->matrix[2][3] = 0.0f;

	this->matrix[3][0] = 0.0f;
	this->matrix[3][1] = 0.0f;
	this->matrix[3][2] = 0.0f;
	this->matrix[3][3] = 1.0f;

}


/*===================================================================*/
void TrackBall::setTrackBallSize(float size)
{
	this->size = size;
}

/*===================================================================*/
float * TrackBall::getQuats() const 
{
	return (float *) this->currentQuat;
}


/*===================================================================*/
void TrackBall::getMatrix( float matrix[][4] ) 
{
	this->Build_Matrix();

	for ( int i = 0; i < 4; i ++ )
		for ( int j = 0; j < 4; j ++ )
			matrix[i][j] = this->matrix[i][j];

}

/*===================================================================*/
float *TrackBall::getMatrix() 
{
	this->Build_Matrix();

	return (float *) this->matrix;
}

/*===================================================================*/
void TrackBall::Init()
{
    auxVector::vecZero(this->currentQuat);
    this->currentQuat[3] = 1.0;
}

/*===================================================================*/
void TrackBall::Reset()
{
	auxVector::vecZero(this->currentQuat);  // rotation 0 in the beginning
	this->currentQuat[3] = 1.0;

}




/*====================== auxVector  ==================================*/

/*===================================================================*/
void auxVector::vecZero(float * vector)
{
    vector[0] = 0.0;
    vector[1] = 0.0;
    vector[2] = 0.0;
}

/*===================================================================*/
void auxVector::vecSet(float * vector, float x, float y, float z)
{
    vector[0] = x;
    vector[1] = y;
    vector[2] = z;
}

/*===================================================================*/
void auxVector::vecSub(const float * src1, const float * src2, float * dst)
{
    dst[0] = src1[0] - src2[0];
    dst[1] = src1[1] - src2[1];
    dst[2] = src1[2] - src2[2];
}

/*===================================================================*/
void auxVector::vecCopy(const float *vector1, float *vector2)
{
    int i;
    for (i = 0 ; i < 3 ; i++)
        vector2[i] = vector1[i];
}

/*===================================================================*/
void auxVector::vecCross_Prod(const float *v1, const float *v2, float *cross)
{
//    float temp[3];

    cross[0] = (v1[1] * v2[2]) - (v1[2] * v2[1]);
    cross[1] = (v1[2] * v2[0]) - (v1[0] * v2[2]);
    cross[2] = (v1[0] * v2[1]) - (v1[1] * v2[0]);

//    vcopy(temp, cross);
}

/*===================================================================*/
float auxVector::vecLength(const float * vector)
{
    return ((float) sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]));
}

/*===================================================================*/
void auxVector::vecScale(float * vector, float div)
{
    vector[0] *= div;
    vector[1] *= div;
    vector[2] *= div;
}

/*===================================================================*/
void auxVector::vecNormalize(float * vector)
{
    vecScale(vector, 1.0f/vecLength(vector));
}

/*===================================================================*/
float auxVector::vecDot_Prod(const float * vector1, const float * vector2)
{
    return (vector1[0] * vector2[0] + vector1[1] * vector2[1] + vector1[2] * vector2[2]);
}

/*===================================================================*/
void auxVector::vecAdd(const float * src1, const float * src2, float * dst)
{
    dst[0] = src1[0] + src2[0];
    dst[1] = src1[1] + src2[1];
    dst[2] = src1[2] + src2[2];
}


/*====================== auxMatrix  ==================================*/

/*===================================================================*/
void auxMatrix::Zero_Matrix(float * matrix)
{
	int i, j;
	for (i = 0; i < 4; i ++ )
		for (j = 0; j < 4; j ++ )
		{
			if (i == j)
				matrix[(i * 4) + j] = 1.0;
			else
				matrix[(i * 4) + j] = 0.0;
		}
}

/*===================================================================*/
void auxMatrix::Copy_Matrix(float * src, float * dest)
{
	for ( int i = 0; i < 16; i ++ )
	{
		src[i] = dest[i];
	}
}


/*===================================================================*/
void auxMatrix::Mult_Matrix(float ** matrix_a, float * matrix_b)
{
	float * tmp = new float[16];

	tmp[0] = (*matrix_a)[0] * matrix_b[0] + (*matrix_a)[1] * matrix_b[4] + (*matrix_a)[2] * matrix_b[8];
	tmp[1] = (*matrix_a)[0] * matrix_b[1] + (*matrix_a)[1] * matrix_b[5] + (*matrix_a)[2] * matrix_b[9];
	tmp[2] = (*matrix_a)[0] * matrix_b[2] + (*matrix_a)[1] * matrix_b[6] + (*matrix_a)[2] * matrix_b[10];
	tmp[3] = 0.0;
	tmp[4] = (*matrix_a)[4] * matrix_b[0] + (*matrix_a)[5] * matrix_b[4] + (*matrix_a)[6] * matrix_b[8];
	tmp[5] = (*matrix_a)[4] * matrix_b[1] + (*matrix_a)[5] * matrix_b[5] + (*matrix_a)[6] * matrix_b[9];
	tmp[6] = (*matrix_a)[4] * matrix_b[2] + (*matrix_a)[5] * matrix_b[6] + (*matrix_a)[6] * matrix_b[10];
	tmp[7] = 0.0f;
	tmp[8] = (*matrix_a)[8] * matrix_b[0] + (*matrix_a)[9] * matrix_b[4] + (*matrix_a)[10] * matrix_b[8];
	tmp[9] = (*matrix_a)[8] * matrix_b[1] + (*matrix_a)[9] * matrix_b[5] + (*matrix_a)[10] * matrix_b[9];
	tmp[10] = (*matrix_a)[8] * matrix_b[2] + (*matrix_a)[9] * matrix_b[6] + (*matrix_a)[10] * matrix_b[10];
	tmp[11] = 0.0f;
	tmp[12] = (*matrix_a)[12] * matrix_b[0] + (*matrix_a)[13] * matrix_b[4] + (*matrix_a)[14] * matrix_b[8] + matrix_b[12];
	tmp[13] = (*matrix_a)[12] * matrix_b[1] + (*matrix_a)[13] * matrix_b[5] + (*matrix_a)[14] * matrix_b[9] + matrix_b[13];
	tmp[14] = (*matrix_a)[12] * matrix_b[2] + (*matrix_a)[13] * matrix_b[6] + (*matrix_a)[14] * matrix_b[10] + matrix_b[14];
	tmp[15] = 1.0;
	
	delete (*matrix_a);

	(*matrix_a) = tmp;

}

/*===================================================================*/
// utilizando o metodo de Gauss Jordan
/*===================================================================*/
double * auxMatrix::Invert_Matrix(double * matrix)
{
	double matrix_l[16],
	  	  matrix_u[16],
		  matrix_y[16],
		  * resultado = new double[16];

	matrix_u[0]  = matrix[0];   // elemento (1,1)
	matrix_u[1]  = matrix[1];   // elemento (1,2)
	matrix_u[2]  = matrix[2];   // elemento (1,3)
	matrix_u[3]  = matrix[3];   // elemento (1,4)

	matrix_l[0]  = 1;   // elemento (1,1)
	matrix_l[5]  = 1;   // elemento (2,2)
	matrix_l[10] = 1;   // elemento (3,3)
	matrix_l[15] = 1;   // elemento (4,4)

	matrix_l[4]  = matrix[4]/matrix_u[0];   // elemento (2,1)
	matrix_l[8]  = matrix[8]/matrix_u[0];   // elemento (3,1)
	matrix_l[12] = matrix[12]/matrix_u[0];  // elemento (4,1)

	matrix_u[5]  = matrix[5] - (matrix_l[4] * matrix_u[1]);   // elemento (2,2)
	matrix_u[6]  = matrix[6] - (matrix_l[4] * matrix_u[2]);   // elemento (2,3)
	matrix_u[7]  = matrix[7] - (matrix_l[4] * matrix_u[3]);   // elemento (2,4)

	matrix_l[9]  = (matrix[9] -(matrix_l[8] * matrix_u[1]))/ matrix_u[5];   // elemento (3,2)
	matrix_l[13] = (matrix[13] -(matrix_l[12] * matrix_u[1]))/ matrix_u[5];   // elemento (4,2)

	matrix_u[10]  = matrix[10] - (matrix_l[8] * matrix_u[2] + matrix_l[9] * matrix_u[6]);   // elemento (3,3)
	matrix_u[11]  = matrix[11] - (matrix_l[8] * matrix_u[3] + matrix_l[9] * matrix_u[7]);   // elemento (3,4)

	matrix_l[14] = (matrix[14] -(matrix_l[12] * matrix_u[3] + matrix_l[13] * matrix_u[7]))/ matrix_u[10];   // elemento (4,3)

	matrix_u[15]  = matrix[15] - (matrix_l[12] * matrix_u[3] + matrix_l[13] * matrix_u[7] + matrix_l[14] * matrix_u[11]);   // elemento (4,4)


	// matriz y
	matrix_y[0]  = 1;   		  									// elemento (1,1)
	matrix_y[4]  = -matrix_l[4];   									// elemento (2,1)
	matrix_y[8]  = -matrix_l[8] - (matrix_l[9] * matrix_l[4]);  	// elemento (3,1)
	matrix_y[12]  = -matrix_l[12] -
		(matrix_l[13] * matrix_l[4] + matrix_l[14] * matrix_y[8]);  // elemento (4,1)

	matrix_y[1]  = 0;   											// elemento (1,2)
	matrix_y[5]  = 1; 												// elemento (2,2)
	matrix_y[9]  = -matrix_l[9]; 									// elemento (3,2)
	matrix_y[13]  = -matrix_l[13] - (matrix_l[14] * matrix_l[9]); 	// elemento (4,2)

	matrix_y[2]  = 0;   											// elemento (1,3)
	matrix_y[6]  = 0; 												// elemento (2,3)
	matrix_y[10] = 1; 												// elemento (2,3)
	matrix_y[14]  = -matrix_l[14]; 									// elemento (4,3)

	matrix_y[3]  = 0;   											// elemento (1,4)
	matrix_y[7]  = 0; 												// elemento (2,4)
	matrix_y[11] = 0; 												// elemento (3,4)
	matrix_y[15] = 1; 												// elemento (4,4)


	// matriz resultado
	resultado[12]= matrix_y[12]/matrix_u[15];						// elemento (4,1)
	resultado[13]= matrix_y[13]/matrix_u[15];						// elemento (4,2)
	resultado[14]= matrix_y[14]/matrix_u[15];  						// elemento (4,3)
	resultado[15]= 1/matrix_u[15];    								// elemento (4,4)

	resultado[8]= (matrix_y[8] - matrix_u[11] * resultado[12])/matrix_u[10];	// elemento (3,1)
	resultado[9]= (matrix_y[9] - matrix_u[11] * resultado[13])/matrix_u[10];	// elemento (3,2)
	resultado[10]= (1 - matrix_u[11] * resultado[14])/matrix_u[10];			// elemento (3,3)
	resultado[11]= -(matrix_u[11] * resultado[15])/matrix_u[10];		    // elemento (3,4)

	resultado[4]= (matrix_y[4] - (matrix_u[6] * resultado[8] + matrix_u[7] * resultado[12]))/matrix_u[5];	// elemento (2,1)
	resultado[5]= (1 - matrix_u[6] * resultado[9] - matrix_u[7] * resultado[13])/matrix_u[5];			// elemento (2,2)
	resultado[6]= -(matrix_u[6] * resultado[10] + matrix_u[7] * resultado[14])/matrix_u[5];				// elemento (2,3)
	resultado[7]= -(matrix_u[6] * resultado[11] + matrix_u[7] * resultado[15])/matrix_u[5];				// elemento (2,4)

	resultado[0]= (1 - (matrix_u[1] * resultado[4] + matrix_u[2] * resultado[8] + matrix_u[3] * resultado[12]))/matrix_u[0];	// elemento (1,1)
	resultado[1]= -(matrix_u[1] * resultado[5] + matrix_u[2] * resultado[9] + matrix_u[3] * resultado[13])/matrix_u[0];		// elemento (1,2)
	resultado[2]= -(matrix_u[1] * resultado[6] + matrix_u[2] * resultado[10] + matrix_u[3] * resultado[14])/matrix_u[0];	// elemento (1,3)
	resultado[3]= -(matrix_u[1] * resultado[7] + matrix_u[2] * resultado[11] + matrix_u[3] * resultado[15])/matrix_u[0];	// elemento (1,4)

	return resultado;

}

/*===================================================================*/
void auxMatrix::Print_Matrix (float * matrix)
{
  int i;

  for (i = 0; i < 4; i++)
  {
      printf("%4.3f %4.3f %4.3f %4.3f\n", matrix[(i * 4) + 0], matrix[(i * 4) + 1], matrix[(i * 4) + 2], matrix[(i * 4) + 3]);
  }
}

/*===================================================================*/
void auxMatrix::Print_Matrix2 (double * matrix)
{
  int i;

  for (i = 0; i < 4; i++)
  {
      printf("%4.3f %4.3f %4.3f %4.3f\n", matrix[(i * 4) + 0], matrix[(i * 4) + 1], matrix[(i * 4) + 2], matrix[(i * 4) + 3]);
  }
}


/*===================================================================*/
double * auxMatrix::Mult_Matrix_Point (double * matrix, double * point)
{
	double * result = new double[3];
	
	result[0] = matrix[0]*point[0] + matrix[1]*point[1] + matrix[2]*point[2] + matrix[3];
	result[1] = matrix[4]*point[0] + matrix[5]*point[1] + matrix[6]*point[2] + matrix[7];
	result[2] = matrix[8]*point[0] + matrix[9]*point[1] + matrix[10]*point[2] + matrix[11];
	
	return result;
}
