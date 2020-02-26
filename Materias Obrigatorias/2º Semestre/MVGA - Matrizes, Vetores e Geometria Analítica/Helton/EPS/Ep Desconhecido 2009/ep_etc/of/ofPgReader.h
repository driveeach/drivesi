#ifdef OF_THREADS
#ifndef OFPGREADER_H
#define OFPGREADER_H
#include "ofMacros.h"
#include "ofMesh.h"
#include "ofTReader.h"
namespace of
{
template <class sTraits> class ofPgReader : public ofTReader<sTraits>
{
public:
	typedef typename sTraits::space space;
	typedef typename sTraits::ids ids;
	typedef typename sTraits::sVertex sVertex;
	typedef typename sTraits::sCell sCell;
	
	typedef ofMesh<sTraits> sMesh;
	
	ofPgReader(int threads_vertices = 2, int threads_cells = 2);
	~ofPgReader();
	
	bool read(sMesh* malha, char* xml);
private:
	typedef typename ofTReader<sTraits>::ofTReaderData ofTReaderData;
	static void *threadPoint(ofTReaderData* obj);
	static void *threadCell(ofTReaderData* obj);
};
template <class sTraits> ofPgReader<sTraits>::ofPgReader(int threads_vertices, int threads_cells)
	:ofTReader<sTraits>(threads_vertices, threads_cells)
{
}
template <class sTraits> ofPgReader<sTraits>::~ofPgReader()
{
}
template <class sTraits> bool ofPgReader<sTraits>::read(sMesh* malha, char* xml)
{
	OF_ASSERT(malha);
	OF_ASSERT(xml);
	PGconn     *conn;
	PGresult   *res;
	char conn_str[MAX_BUFFER], buffer[MAX_BUFFER];
	int i,nv,nc;
	
	OF_ASSERT(conn_str);
	OF_ASSERT(buffer);
	ofXmlParser config;
	
	config.read(xml);
	
	sprintf(conn_str,"host=%s port=%s dbname=%s user=%s password=%s",config.getHost(),config.getPort(),
	config.getDataBase(),config.getUser(),config.getPassword());
		
	conn = PQconnectdb(conn_str);	
         
	if (PQstatus(conn) != CONNECTION_OK)
	{
		fprintf(stderr, "Connection to database failed: %s",PQerrorMessage(conn));
		PQfinish(conn);
		return false;		
	}
	cout << "Postgres conectado." << endl;
	
	sprintf(buffer, "SELECT count(*) as num FROM %s", config.getVerticesTable());
	res = PQexec(conn, buffer);
    if (PQresultStatus(res) != PGRES_TUPLES_OK)
    {
    	fprintf(stderr, "SELECT failed: %s", PQerrorMessage(conn));
        PQclear(res);
		PQfinish(conn);
		return false;		
    }
	nv = atoi(PQgetvalue(res, 0, 0));
	OF_ASSERT(nv > 0);
	//cout << "Numero de vertices :" << nv << endl;;
	
	PQclear(res);
  
		
	pthread_t *tid = new pthread_t[this->nthreadsv];
	ofTReaderData *pdobj = new ofTReaderData[this->nthreadsv]();
	OF_ASSERT(tid);
	OF_ASSERT(pdobj);
	ids qtd = nv / this->nthreadsv;
	ids start = 0;
	
	//cout << "Quantidade por thread:" << qtd << endl;
	
	for (i = 0; i < this->nthreadsv; i++)
	{
		pdobj[i].id = i;		
		pdobj[i].str = conn_str;
		pdobj[i].start = start;
		pdobj[i].end = start + qtd;
		if(pdobj[i].end >= nv) pdobj[i].end = nv-1;
		pdobj[i].malha = malha;
		pdobj[i].config = &config;
		
		start += qtd + 1;
		
		pthread_create(&tid[i], NULL,  (void * (*)(void*))&threadPoint, &pdobj[i]);
	}
	
	for (i = 0; i < this->nthreadsv; i++)
		pthread_join(tid[i], NULL);
	
	malha->setNumberOfVertices(nv);
	
	//cout << "Leitura dos vertices concluida." << endl;
	
	delete [] tid;
	delete [] pdobj;
	
	// leitura das celulas
	tid = new pthread_t[this->nthreadsc];
	pdobj = new ofTReaderData[this->nthreadsc]();
	OF_ASSERT(tid);
	OF_ASSERT(pdobj);
	sprintf(buffer, "SELECT count(*) as num FROM %s", config.getCellsTable());
	res = PQexec(conn, buffer);
    if (PQresultStatus(res) != PGRES_TUPLES_OK)
    {
    	fprintf(stderr, "SELECT failed: %s", PQerrorMessage(conn));
        PQclear(res);
		PQfinish(conn);
		return false;
    }
	nc = atoi(PQgetvalue(res, 0, 0));
	OF_ASSERT(nc > 0);
	//cout << "Numero de celulas :" << nc << endl;;
	
	PQclear(res);
	
		
	qtd = nc / this->nthreadsc;
	start = 0;
	
	//cout << "Quantidade por thread:" << qtd << endl;
	
	for (i = 0; i < this->nthreadsc; i++)
	{
		pdobj[i].id = i;		
		pdobj[i].str = conn_str;
		pdobj[i].start = start;
		pdobj[i].end = start + qtd;
		if(pdobj[i].end >= nc) pdobj[i].end = nc - 1;
		pdobj[i].malha = malha;
		pdobj[i].config = &config;
	
		start += qtd + 1;
		
		pthread_create(&tid[i], NULL, (void * (*)(void*)) &threadCell, &pdobj[i]);
	}
	
	for (i = 0; i < this->nthreadsc; i++)
		pthread_join(tid[i], NULL);
	
	malha->setNumberOfCells(nc);
	
	//cout << "Leitura das celulas concluida." << endl;
	delete [] tid;
	delete [] pdobj;
	
	PQfinish(conn);
		
	return true;
}
template <class sTraits> void* ofPgReader<sTraits>::threadPoint(ofTReaderData* obj)
{
	OF_ASSERT(obj);
   	PGconn     *conn;
   	PGresult   *res;
	char buffer[MAX_BUFFER];
	OF_ASSERT(buffer);
	ids start, end, qtd;
	
	conn = PQconnectdb(obj->str);
         
	if (PQstatus(conn) != CONNECTION_OK) 
	{
		fprintf(stderr, "Connection to database failed: %s",PQerrorMessage(conn));
		PQfinish(conn);
		return false;		
	}
	qtd = OF_TREADER_QTDSQL;
	OF_ASSERT(qtd > 0);
	start = obj->start;
	while(start <= obj->end)
	{
		end = start + qtd;
		if(end > obj->end) end = obj->end;
			
		sprintf(buffer, "SELECT * FROM %s WHERE ((%s >= %d) and (%s <= %d))",
		obj->config->getVerticesTable(), obj->config->getVerticesIdField(), start,
		obj->config->getVerticesIdField(), end);
		res = PQexec(conn, buffer);
	
		if (PQresultStatus(res) != PGRES_TUPLES_OK)
		{
			fprintf(stderr, "SELECT failed: %s", PQerrorMessage(conn));
			PQclear(res);
			PQfinish(conn);
			return false;		
		}
		else
		{
			int id_fnum, x_fnum, y_fnum, z_fnum, id, num, i;
			space coords[sVertex::getDimension()];
			
			id_fnum = PQfnumber(res, obj->config->getVerticesIdField());
			x_fnum  = PQfnumber(res, obj->config->getVerticesField(0));
			y_fnum  = PQfnumber(res, obj->config->getVerticesField(1));
			if(sVertex::getDimension() == 3)
				z_fnum  = PQfnumber(res, obj->config->getVerticesField(2));
			
			num = PQntuples(res);
			OF_ASSERT(num > 0);
			for(i = 0; i < num; i++)
			{
				id = atoi(PQgetvalue(res, i, id_fnum));
				coords[0]  = atof(PQgetvalue(res, i, x_fnum));			
				coords[1]  = atof(PQgetvalue(res, i, y_fnum));
				if(sVertex::getDimension() == 3)
					coords[2]  = atof(PQgetvalue(res, i, z_fnum));
				
				//cout << "Adicionando o vertice: " << id << endl;
				obj->malha->addVertex(id,coords);
			}
		}
		start += qtd + 1;
	    PQclear(res);
	}
	PQfinish(conn);
	
	//cout << "Thread " << obj->id << " desconectada." << endl;
	
	//pthread_exit(NULL);
	
	return NULL;
}
template <class sTraits> void* ofPgReader<sTraits>::threadCell(ofTReaderData* obj)
{
	OF_ASSERT(obj);
   	PGconn     *conn;
   	PGresult   *res;
	char buffer[MAX_BUFFER];
	OF_ASSERT(buffer);
	ids start, end, qtd;
	
	conn = PQconnectdb(obj->str);
         
	if (PQstatus(conn) != CONNECTION_OK)
	{
		fprintf(stderr, "Connection to database failed: %s",PQerrorMessage(conn));
		PQfinish(conn);
		return false;		
	}
	
	int id_fnum, v1_fnum, v2_fnum, v3_fnum, v4_fnum;
	ids id, v[sCell::getDimension()], num, i;
	
	qtd = OF_TREADER_QTDSQL;
	OF_ASSERT(qtd > 0);
	start = obj->start;
	while(start <= obj->end)
	{
		end = start + qtd;
		if(end > obj->end) end = obj->end;
			
		sprintf(buffer, "SELECT * FROM %s WHERE ((%s >= %d) and (%s <= %d))",
		obj->config->getCellsTable(), obj->config->getCellsIdField(), start,
		obj->config->getCellsIdField(), end);
		res = PQexec(conn, buffer);
		if (PQresultStatus(res) != PGRES_TUPLES_OK)
		{
			fprintf(stderr, "SELECT failed: %s", PQerrorMessage(conn));
			PQclear(res);
			PQfinish(conn);
			return false;		
		}
		else
		{
			num = PQntuples(res);
			OF_ASSERT(num > 0);
			id_fnum  = PQfnumber(res, obj->config->getCellsIdField());
			v1_fnum  = PQfnumber(res, obj->config->getCellsField(0));
			v2_fnum  = PQfnumber(res, obj->config->getCellsField(1));
			v3_fnum  = PQfnumber(res, obj->config->getCellsField(2));
			if(sCell::getDimension() == 3)
				v4_fnum  = PQfnumber(res, obj->config->getCellsField(3));
			
			
			for(i = 0; i < num; i++)
			{
				id  = atoi(PQgetvalue(res, i, id_fnum));
				v[0]  = atoi(PQgetvalue(res, i, v1_fnum));
				v[1]  = atoi(PQgetvalue(res, i, v2_fnum));
				v[2]  = atoi(PQgetvalue(res, i, v3_fnum));
				if(sCell::getDimension() == 3)
					v[3]  = atoi(PQgetvalue(res, i, v4_fnum));
				OF_ASSERT(v[0] >= 0);
				OF_ASSERT(v[1] >= 0);
				OF_ASSERT(v[2] >= 0);
				if(sCell::getDimension() == 3)
					OF_ASSERT(v[3] >= 0);
				
				//cout << "Adicionando celula " << id << "." << endl;
				obj->malha->addCell(id,v);
				
				//if((id % 100000) == 0)
				//	cout << "Thread " << obj->id << " - cell " << id << endl;
				//sleep(2);
			}
		}		
		start += qtd + 1;
	   	PQclear(res);
	}
	PQfinish(conn);
	
	//cout << "Thread " << obj->id << " desconectada." << endl;
	
	//pthread_exit(NULL);
	return NULL;
}
}
#endif
#endif
