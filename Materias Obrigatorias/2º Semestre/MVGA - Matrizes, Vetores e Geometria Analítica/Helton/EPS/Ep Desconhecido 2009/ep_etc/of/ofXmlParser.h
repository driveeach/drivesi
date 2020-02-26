#ifndef OFXMLPARSER_H
#define OFXMLPARSER_H
#include "ofMacros.h"
namespace of
{
	
class ofXmlParser
{
public:
	
	ofXmlParser()
	{
		host = port = user = pass = db = 
		vtable = ctable = vid = cid = NULL;
		vertices = coords = NULL;
		vdim = cdim = 0;		
	};
	~ofXmlParser()
	{
		if(host) delete [] host; if(port) delete [] port; if(user) delete [] user;
		if(pass) delete [] pass; if(db) delete [] db; if(vtable) delete [] vtable;
		if(ctable) delete [] ctable; if(vid) delete [] vid; if(cid) delete [] cid;
		
		int i;
		if(coords)
		{
			for(i = 0; i < vdim; i++)
				if(coords[i]) delete [] coords[i];
			
			delete [] coords;
		}
		if(vertices)
		{
			for(i = 0; i < cdim; i++)
				if(vertices[i]) delete [] vertices[i];
			
			delete [] vertices;
		}
	};
	
	bool read(char *arquivo)
	{
		FILE *pf; char *buffer, *p, sep[] = " <>\n";
		int status = 0, i, vtemp = 0, ctemp = 0;	long lSize;	bool ler;
				
		if (!(pf = fopen(arquivo,"rt")))
		{
			printf("Error reading file %s\n",arquivo);
			return false;
		}
		
		fseek(pf , 0 , SEEK_END); lSize = ftell(pf); rewind(pf);
	
		OF_ASSERT(lSize > 0);
		
		buffer = new char[lSize];
		OF_ASSERT(buffer);
		fread(buffer,1,lSize,pf); fclose(pf);
			
		// status
		// 0 : sem comando
		// 1 : TES_XML
		// 2 : database
		// 3 : tables
		// 4 : vertices
		// 5 : cells
		
		p = strtok(buffer, sep);
		while(p != NULL)
		{		
			ler = true;
			
			if(status == 0)
			{
				if(strcmp(p,"OF_XML") == 0) status = 1;
			}
			else if(status == 1)
			{
				if(strcmp(p,"database") == 0) status = 2;
				else if(strcmp(p,"/OF_XML") == 0) status = 0;
			}
			else if(status == 2)
			{
				if(strcmp(p,"name") == 0) ler = attrib(db, strtok(NULL, sep));
				else if(strcmp(p,"host") == 0) ler = attrib(host, strtok(NULL, sep));
				else if(strcmp(p,"port") == 0) ler = attrib(port, strtok(NULL, sep));
				else if(strcmp(p,"user") == 0) ler = attrib(user, strtok(NULL, sep));
				else if(strcmp(p,"password") == 0) ler = attrib(pass, strtok(NULL, sep));
				else if(strcmp(p,"tables") == 0) status = 3;
				else if(strcmp(p,"/database") == 0) status = 1;
			}
			else if(status == 3)
			{
				if(strcmp(p,"vertices") == 0) status = 4;
				else if(strcmp(p,"cells") == 0) status = 5;
				else if(strcmp(p,"/tables") == 0) status = 2;
			}
			else if(status == 4)
			{
				if(strcmp(p,"name") == 0) ler = attrib(vtable, strtok(NULL, sep));
				else if(strcmp(p,"id") == 0) ler = attrib(vid, strtok(NULL, sep));
				else if(strcmp(p,"dimension") == 0)
				{
					ler = attribint(vdim, strtok(NULL, sep));
					if(vdim > 0)
					{
						coords = (char**) new char*[vdim];
						for(i = 0; i < vdim; i++)
							coords[i] = NULL;
					}
				}
				else if(strcmp(p,"coord") == 0)
				{
					OF_ASSERT(vtemp < vdim);
					ler = attrib(coords[vtemp++], strtok(NULL, sep));
				}
				else if(strcmp(p,"/vertices") == 0)	status = 3;
			}
			else if(status == 5)
			{
				if(strcmp(p,"name") == 0) ler = attrib(ctable, strtok(NULL, sep));
				else if(strcmp(p,"id") == 0) ler = attrib(cid, strtok(NULL, sep));
				else if(strcmp(p,"dimension") == 0)
				{
					ler = attribint(cdim, strtok(NULL, sep));
					if(cdim > 0)
					{
						vertices = (char**) new char*[cdim];
						for(i = 0; i < cdim; i++)
							vertices[i] = NULL;
					}
				}
				else if(strcmp(p,"vertex") == 0)
				{
					OF_ASSERT(ctemp < cdim);
					ler = attrib(vertices[ctemp++], strtok(NULL, sep));
				}
				else if(strcmp(p,"/cells") == 0) status = 3;
			}
			
			if(ler)	p = strtok (NULL, " <>\n");
		}
		
		delete [] buffer;
		
		if((status == 0)&&
			(host)&&(port)&&(db)&&(user)&&(pass)&&
			(vtable)&&(vid)&&(vdim > 0)&&
			(ctable)&&(cid)&&(cdim > 0))
			return true;
		else
			return false;
	};
	
	
	char* getHost() {return host;};
	char* getPort() {return port;};
	char* getUser() {return user;};
	char* getPassword() {return pass;};
	char* getDataBase() {return db;};
	char* getVerticesTable() {return vtable;};
	char* getCellsTable() {return ctable;};
	char* getVerticesIdField() {return vid;};
	int   getVerticesDimension() {return vdim;};
	char* getVerticesField(int dim) {return coords[dim];};
	char* getCellsIdField() {return cid;};
	int   getCellsDimension() {return cdim;};
	char* getCellsField(int dim) {return vertices[dim];};
	
private:
	bool attrib(char *&orig, char *src)
	{	
		int tam; char *temp;
		
		if(*src != '/')
		{
			tam = strlen(src);
			if(tam > 0)
			{
				temp = new char[tam + 1];
				OF_ASSERT(temp);
				if(temp)
				{
					strcpy(temp, src);					
					if(orig) delete [] orig;					
					orig = temp;					
					return true;
				}
			}				
		}
		return false;
	};
	bool attribint(int &orig, char *src)
	{	
		if(*src != '/')
		{
			orig = atoi(src);
			return true;
		}
		return false;
	};
	char *host, *port, *user, *pass, *db, 
	*vtable, *ctable, *vid, *cid, **vertices, **coords;
	int vdim, cdim;
};
}
#endif
