// modified to support the SHE structure
#include "scrInteractor.h"

struct Coord
{     
   double x;
   double y;
   double z;
};

extern scrInteractor * Interactor;
//extern sheInteractor * Interactor;
void(*functionKey) (unsigned char key, int x, int y) = NULL;

/*======== Main Window Functions ============================================*/

void Display()
{
	Interactor->Display();
}
		
void Reshape(int width, int height)
{
	Interactor->Reshape(width, height);
}

void MouseMotion(int x, int y)
{
	Interactor->MouseMotion(x, y);
}
	
void Mouse(int button, int state, int x, int y)
{
	Interactor->Mouse(button, state, x, y);
}

void Keyboard(unsigned char key, int x, int y)
{
	if (!Interactor->Keyboard(key, x, y) && (functionKey != NULL))
		functionKey(key, x, y);
}

void AddKeyboard(void (* func)(unsigned char key, int x, int y))
{
	functionKey = func;
}

/*======== Sub Window Functions ========================================*/

void subReshape(int width, int height)
{
	Interactor->subReshape(width, height);
}

void subMouse(int button, int state, int x, int y)
{
	Interactor->subMouse(button, state, x, y);
}

void subDisplay ()
{
	Interactor->subDisplay();
}


//----------------------------------------------------------------------------------------
void Init_Interactor()//TPrint * myTPrint)
{

	// Interactor.setDraw must be set here,
	// and then the init function with extents must be called

	glShadeModel(GL_SMOOTH);
	
	glutDisplayFunc(Display);

	glutReshapeFunc(Reshape);

	glutKeyboardFunc(Keyboard);

	glutMouseFunc(Mouse);
	
	glutMotionFunc(MouseMotion);

	Interactor->subInit();

	glutDisplayFunc(subDisplay);

	glutReshapeFunc(subReshape);

	glutMouseFunc(subMouse);

 glutPostRedisplay();
  
// Interactor->AddTPrint(myTPrint);
 
	glutMainLoop();
}
/*===================================================================*/
/*===================================================================*/
/*===================================================================*/
class TColor
{
	public :
		TColor();
		
		void setColor(unsigned int index);
		void setMax(unsigned int max);


	private :
		unsigned int max_color;
		float each_color;
		float displacement;

};


/*===================================================================*/
TColor::TColor()
{
	// default value for max color
	this->max_color = 40;
	this->each_color = 10;
	this->displacement = 0.1;

}


/*===================================================================*/
void TColor::setMax(unsigned int max) 
{ 
	this->max_color = max; 
	this->each_color = (int)max/4;
	this->displacement = 1.0/this->each_color;

}

/*===================================================================*/
/*  Color Pallet --   BBBRRRRGGGGBBB*/
void TColor::setColor(unsigned int index)
{
	float 
		B1 = 1.0, 
		R = 0.0, 
		G = 0.0, 
		B2 = 0.0;
	int result = (int)(index / (3 * this->each_color));
	int aux    = index % (int)(3 * this->each_color);


	if (result == 1)
	{
		B2 = aux * this->displacement;
		G = 1.0 - B2;
		R = 1.0 - B2;
		B1 = 0.0;


	}
	else 
	{

		result = (int)(index / (2 * this->each_color));
		aux    = index % (int)(2 * this->each_color);
		if (result >= 1)
		{
			G = (aux - B2) * this->displacement;
			R = 1.0;
			B1 = 0.0;
		}
		else 
		{
			result = (int)(index / this->each_color);
			aux    = (index % (int)this->each_color);
			G = 0.0;

			if (result == 1)
			{
				R = aux * this->displacement;;
				B1 = 0.0;
				G = 1.0 - R;
			}
			else
			{
				B1 = 1.0 - ((float)aux * this->displacement);
				G = 1.0 - B1;
			}

		}
	}
	glColor3f(R, G, B1 + B2);
	
}

/*===================================================================*/

void TriangleColor(Coord coord1, int index1, Coord coord2, int index2, Coord coord3, int index3, int max_colors)
{
	TColor color;
	color.setMax(max_colors);
	
	glBegin(GL_TRIANGLES);
		color.setColor(index1);
		glVertex3f(coord1.x, coord1.y, coord1.z);

		color.setColor(index2);
		glVertex3f(coord2.x, coord2.y, coord2.z);

		color.setColor(index3);
		glVertex3f(coord3.x, coord3.y, coord3.z);

	 glEnd();
}
