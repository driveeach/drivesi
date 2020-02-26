#ifndef OF_H
#define OF_H
// System includes ---------------------------------------------------------
#include <string>
#include <iostream>
#include <fstream>
#include <iomanip>
#ifdef OF_THREADS
	#include <libpq-fe.h>
	#include <pthread.h>
#endif
#ifdef OF_DEBUG
	#include <assert.h>
#endif 
using namespace std;
// OF includes -------------------------------------------------------------
#include "ofMacros.h"
#include "ofBase.h"
#include "ofVector.h"
#include "ofList.h"
#include "ofKdTree.h"
#include "ofXmlParser.h"
#include "ofVertex.h"
#include "ofSing.h"
#include "ofCell.h"
#include "ofSingularVertex.h"
#include "ofVertex2D.h"
#include "ofVertex3D.h"
#include "ofCell2D.h"
#include "ofCell3D.h"
#include "ofBoundaryCellIterator2D.h"
#include "ofBoundaryCellCIterator2D.h"
#include "ofCellsIterator.h"
#include "ofVerticesIterator.h"
#include "ofVertexStarIterator2D.h"
#include "ofVertexStarIterator3D.h"
#include "ofVertexStarIteratorSurface.h"
#include "ofMesh.h"
#include "ofMeshOper.h"
#include "ofMesh2D.h"
#include "ofMesh3D.h"
#include "ofMeshSurface.h"
#include "ofMeshNOSurface.h"
#include "ofDelaunay2D.h"
#include "ofGeometric.h"
#include "ofTopology.h"
#include "ofBinaryIO.h"
#include "ofReader.h"
#include "ofWriter.h"
#include "ofOfReader.h"
#include "ofvtkreader.h"
#include "ofPolyDataReader.h"
#include "ofOffReader.h"
#include "ofOffWriter.h"
#include "ofWrlReader.h"
#include "ofOfWriter.h"
#include "ofVtkWriter.h"
#include "ofPolyDataWriter.h"
#include "ofTraits.h"
#include "ofTReader.h"
#include "ofPgReader.h"
//Ruppert Includes
#include "ofMyof.h"
#endif
