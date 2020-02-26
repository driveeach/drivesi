#ifndef _TRACKBALL
#define _TRACKBALL

#define TRACKBALLSIZE  (0.78375f)	// radius - constant value set after tests

//*********************************** TrackBall *****************************************************************
class TrackBall  
{
	public:
		// constructor of the trackball
		TrackBall();

		// constructor of the trackball
		TrackBall(float sx, float sy, float ex, float ey, bool lock_x, bool lock_y);

		// destructor of the trackball
		~TrackBall(); 

		// init the values of the trackball
		void Init();
		
		// set the value of the size variable
		void setTrackBallSize(float size);

		// add a quaternion
		void Add_Quats(float delta_quat[4]);

		// set the scale values
		void setScale (float scale);

		void setTranslate (float tx, float ty);

		float * getQuats() const;

		float * getMatrix();

		void getMatrix( float [][4] );

		void Reset();

	private:
		float currentQuat[4];         // current quaternion
		float size;			// track ball size 
		float matrix[4][4];

		// normalize a quaternion
		void quatNormalize(float quat[4]);

		// build the rotation matrix of current quaternion
		void Build_Matrix();

		// given an axis and an angle phi, calculates a quaternion
		void Axis_Quat(float axis[3], float ang_phi, float quat[4]);

		// project a (x,y) pointer in a sphere
		static float Project(float radius, float x, float y);

};


//*********************************** auxVector *****************************************************************
class auxVector
{
	public :
		// zero the x, y and z values of a vector
		static void vecZero(float * vector);
	
		// set the x, y  and z values of a vector
		static void vecSet(float * vector, float x, float y, float z);

		// add 2 vectors and return the result in dst
		static void vecAdd(const float *src1, const float *src2, float *dst);
		
		// subtract 2 vectors and return the result in dst
		static void vecSub(const float *src1, const float *src2, float *dst);
		
		// copy vector1 in vector2
		static void vecCopy(const float *vector1, float *vector2);

		// calculates the cross product of two vector
		static void vecCross_Prod(const float *v1, const float *v2, float *cross);

		// calculates the lenght of a vector
		static float vecLength(const float *vector);

		// executes the scale operation in a vector
		static void vecScale(float * vector, float div);
		
		// normalize a vector
		static void vecNormalize(float * vector);
		
		// calculates the dot product of two vector
		static float vecDot_Prod(const float *v1, const float *v2);

};

//*********************************** auxMatrix *****************************************************************
class auxMatrix
{
	public:

		static void Zero_Matrix(float * matrix);

		static void Copy_Matrix(float * src, float * dest);
	
		static void Mult_Matrix(float ** matrix_a, float * matrix_b);
	
		static double * Invert_Matrix(double * matrix);

		static double * Mult_Matrix_Point(double * matrix, double * point);
	
		static void Print_Matrix(float * matrix);
		static void Print_Matrix2(double * matrix);
		
};

//*********************************** auxRect *****************************************************************
template <class T> class auxPoint   // an auxiliary class to represent points
{
	public:
      auxPoint(T x = 0, T y = 0)    
	  {  
		  this->x = x;
		  this->y = y;
	  }
      
	  
	  auxPoint<T>&  operator = (const auxPoint<T>& outro)
      {
      
		  this->x   = outro.x;
		  this->y   = outro.y;
		 
		  return *this;                 
	  }

	  void  setValues(T x, T y)
	  {
		  this->x = x;
		  this->y = y;
	  }


      T   getX()     const {  return this->x; }
      T   getY()     const {  return this->y; }
	  bool  getZero()  const {  return (this->x == this->y == 0); }

	public:
      T x,
	    y;
};


//*********************************** auxRect *****************************************************************

template <class T> class auxRect   // an auxiliary class to represent rectangles
{
	public:
      auxRect(T left = 0, T right = 0, T top = 0, T bottom = 0)    
	  {  
		  this->left = left;
		  this->top = top;
		  this->right = right;
		  this->bottom = bottom; 
	  }
      
	  
      void setValues(T left = 0, T right = 0, T top = 0, T bottom = 0)
	  {
		  this->left = left;
		  this->top = top;
		  this->right = right;
		  this->bottom = bottom; 
	  }

	  auxRect<T>&  operator = (const auxRect<T>& outro)
      {
		  this->left = outro.left;
		  this->top = outro.top;
		  this->right = outro.right;
		  this->bottom = outro.bottom; 
		 
		  return *this;                 
	  }

      void setUpperValues(T vleft = 0, T vtop = 0)
	  {
		  this->left = vleft;
		  this->top = vtop;
	  }

      void setDownValues(T vright = 0, T vbottom = 0)
	  {
		  this->right = vright;
		  this->bottom = vbottom; 
	  }

      T   Width()     const {  return (this->right - this->left); }
      T   Height()    const {  return (this->bottom - this->top); }

      T   getCenterX()  const {  return ((this->right + this->left)/2); }
      T   getCenterY()  const {  return ((this->bottom + this->top)/2); }

	public:
      T left,
	    top,
	    right,
	    bottom;
};

#endif
