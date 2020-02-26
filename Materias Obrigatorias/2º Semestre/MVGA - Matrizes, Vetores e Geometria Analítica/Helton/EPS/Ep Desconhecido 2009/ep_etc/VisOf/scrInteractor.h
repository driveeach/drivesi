#ifndef _SCR_INTERACTOR
#define _SCR_INTERACTOR


//************** Includes ***************************
#include "TrackBall.h"
#include <glut.h>
#include <gl\gl.h>
#include <string>
//************** Defines de valores e funcoes ***************************

enum render_mode{RUSER , RWIRE, RPOINT, RSHADING, RSHADING_WIRE};

enum view_mode{VIEW_FREE, VIEW_TOP, VIEW_BOTTOM, VIEW_LEFT, VIEW_RIGHT, VIEW_NEAR, VIEW_FAR};

#define BTN_RENDER  100
#define BTN_USER    101
#define BTN_RESET   102
#define BTN_X_AXIS	103
#define BTN_Y_AXIS	104
#define BTN_Z_AXIS	105
#define BTN_COLOR   106 // muda a cor do BG
#define BTN_CHANGE_NORMAL 107 // muda o sinal da normal
#define BTN_CENTROIDE 108 // calcula o centroide


#define BLACK 200
#define WHITE 201
#define USER 202

#define SUB_SIZE 15
#define MAX(X, Y)\
		(X) > (Y) ? (X) : (Y)
#define MIN(X, Y)\
		(X) < (Y) ? (X) : (Y)



//*********************************** glButton *****************************************************************
class glButton
{
	public :
		glButton()	{ this->state = false; }
		void    init(auxRect<float> edge_values, float * clr_text, float * clr_btn, char * text);
		void    setState ()							 { this->state = !this->state; }
		void    setColor (float * edge, float * btn);
		void    setLabel (char * text)				 { strcpy(this->label, text); }
		void    setVisible(bool visible)             { this->visible = visible; }
		
		bool    getState ()							 { return this->state; }

		char *  getLabel ()							 { return this->label; }

		void    Draw();
        
     
        
        

	protected :
		void    Draw_Label();
		
		auxRect<float> edge;
		bool	state;
		bool    visible;
		float * text_color;
        float * btn_color;
		char  *	label;
};


//*********************************** scrIterator *****************************************************************



class scrInteractor
{
	public :
     
	   
		scrInteractor(int width, int height);		
		virtual ~scrInteractor(){} // Destrutor virtual para poder usar a função virtual para calcular o centróide

			
			
        bool Init(float xmin, float xmax, float ymin, float ymax, float near_z, float far_z);
		
        bool Init (auxRect<float> rec_extents, float near_z, float far_z);

		void Display();

		void setDraw(void (* func)());

		void Refresh_List();

		void Reshape(int width, int height);

		void MouseMotion(int x, int y);
		
		void Mouse(int button, int state, int x, int y);

		bool Keyboard(unsigned char key, int x, int y);

		void subDisplay();

		void subReshape(int width, int height);

		void subMouse(int button, int state, int x, int y);

		void subInit();
		
		float * getTranslation();
		
		void Init_Menu();

		void Calc_Rotation();
      
        void setBGColor(); // muda os valores das variaveis bg_color e bg_state de acordo com o estado atual do BG
        
        int getBGState(); // verifica o estado atual do BG
        
        float getBGColor(int rgb); // pega os valores de cada uma das componentes RGB, de acordo com a variavel int rgb
      
        void setUserBGColor(); // le valores passados pelo usuario e modifica o valor do vetor bg_color
        
        void Change_BG_Color(); // modifica a cor do BG
      
		static void Set_Centroide(float x, float y, float z); // Estatico porque é usado na VisPrint.
		
		static bool Get_Normal_Change(); // função que devolve a normal, usada no main.
	
		
        void SetScreenImageFilename(std::string filename);
        std::string& GetScreenImageFilename();
        bool WriteScreenImage();
		
		
		//[EP] ----VARIAVEIS QUE RETORNAM COORDENADAS DO CLIQUE DO MOUSE----
		double getPX(); //captura x esquerdo
		double getPY(); //captura y esquerdo
		double getPXD(); //captura x direito
		double getPYD(); //captura y direito
		bool getMouseLeft();  //captura se clique esquerdo
		bool getMouseRight(); //captura se clique direito
		//[FIM]
		
		
	protected :
	
		/*Criar uma variavel centroide para armazenar o centroide =)*/
		// terminar de programar o calculo do centroide.
	
		static float* Centroide; // estatico pois é usado em um metodo estatico
	
        int bg_state; // estado atual da cor do BG
      
        float *bg_color; // vetor que armazena os valores RGB para o BG

		static bool Normal_Change; // variavel usada para dizer quando o sinal deve ser mudado ou não
	
		void Reset();

		void setRender_Mode();

		void setView_Mode();

		void Draw_String(char * text);

		void Draw_Axis();

		void Init_List();
	
		void Calc_Scale();

		void Calc_Translation();

		void Initial_Translation ();

		void Init_Lights();

		void setLights(bool lights_on);

		void ScreenToPoint(auxPoint<int> mouse, double point[3]);

		void ScreenToPoint_Axis(auxPoint<int> mouse, double point[3]);

		int  getObject_Id(int x, int y);

		void (*functionDraw) ();
	
		void Display_Point();
	
		auxRect<int> rec_screen; // coordinates of screen
		auxRect<float> rec_initial;

		auxPoint<int> pt_mouse;
		auxPoint<int> pt_mouse_old;

		bool mouse_left;
		bool mouse_right;
		bool shift_pressed;
		bool user_button;
		
		float val_tx;
		float val_ty;
		float val_tz;

		double  far_z;
		double  near_z;
		double  aux_z;
		
		double  initial_tx;
		double  initial_ty;
		double  initial_tz;

		double model_view_matrix[16];
		
		unsigned short int point_size;

		double scale;

		float * vec_translation;
		
		float * matrix;

		float * axis_matrix;
		
		int view_list;

		int main_winid;
		int sub_winid;

        std::string   screenFilename;
        int           screenFilenameNumber;

		glButton * buttons;

		render_mode render;

		TrackBall trackball;
		
};


 
#endif
