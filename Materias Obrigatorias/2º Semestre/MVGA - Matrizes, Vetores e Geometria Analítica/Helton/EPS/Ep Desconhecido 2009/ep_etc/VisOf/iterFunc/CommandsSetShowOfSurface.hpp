#ifndef CMDS_SET_SHOW_OF_SURFACE_HPP_
#define CMDS_SET_SHOW_OF_SURFACE_HPP_

#include "CommandsSet.hpp"

#include "CmdShowOfMesh.hpp"
#include "CmdShowOfMeshWireframe.hpp"
#include "CmdShowOfBoundary.hpp"
#include "CmdShowOfVerticesIds.hpp"
#include "CmdShowOfCellsIds.hpp"
#include "CmdShowOfEdgeIncrease.hpp"
#include "CmdShowOfEdgeShrink.hpp"
#include "CmdShowOfFaceIncrease.hpp"
#include "CmdShowOfFaceShrink.hpp"
#include "CmdShowOfCellIncrease.hpp"
#include "CmdShowOfCellShrink.hpp"
#include "CmdShowOfElementsIncrease.hpp"
#include "CmdShowOfElementsShrink.hpp"

template<typename _TPrint>
class CommandsSetShowOfSurface : public CommandsSet{
   
public:
   typedef _TPrint                           TPrint;   
   
   
public:

   CommandsSetShowOfSurface(TPrint *print_i){

      RegistryCommand('<', new CmdShowOfElementsShrink<TPrint>(print_i, 2, 0));
      RegistryCommand('>', new CmdShowOfElementsIncrease<TPrint>(print_i, 2, 0));

      RegistryCommand('g', new CmdShowOfEdgeShrink<TPrint>(print_i, 2, 0));
      RegistryCommand('G', new CmdShowOfEdgeIncrease<TPrint>(print_i, 2, 0));
      RegistryCommand('h', new CmdShowOfFaceShrink<TPrint>(print_i, 2, 0));
      RegistryCommand('H', new CmdShowOfFaceIncrease<TPrint>(print_i, 2, 0));
      RegistryCommand('j', new CmdShowOfCellShrink<TPrint>(print_i, 2, 0));
      RegistryCommand('J', new CmdShowOfCellIncrease<TPrint>(print_i, 2, 0));      
      
      RegistryCommand('m', new CmdShowOfMesh<TPrint>(print_i, 2, 0));
      RegistryCommand('w', new CmdShowOfMeshWireframe<TPrint>(print_i, 2, 0));
      RegistryCommand('b', new CmdShowOfBoundary<TPrint>(print_i, 2, 0));
      RegistryCommand('V', new CmdShowOfVerticesIds<TPrint>(print_i, 2, 0));
      RegistryCommand('C', new CmdShowOfCellsIds<TPrint>(print_i, 2, 0));
      

      
   }
     
};

#endif /*INTFUNCSMESHSURFACE_HPP_*/
