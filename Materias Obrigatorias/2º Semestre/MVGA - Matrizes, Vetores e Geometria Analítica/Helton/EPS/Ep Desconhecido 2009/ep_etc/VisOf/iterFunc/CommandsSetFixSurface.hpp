#ifndef CMDS_SET_FIX_SURFACE_HPP_
#define CMDS_SET_FIX_SURFACE_HPP_

#include "CommandsSet.hpp"

#include "CmdShowOfSingularities.hpp"
#include "CmdShowOfOrientMesh.hpp"
#include "CmdWriteOrientMesh.hpp"
#include "CmdWriteNoSings.hpp"
#include "CmdWriteOrientSubMesh.hpp" 
#include "CmdShowOfVertexStar.hpp"
#include "CmdShowOfMoveVertex.hpp"

template<typename _TPrint>
class CommandsSetFixSurface : public CommandsSet{
   
public:
   typedef _TPrint                           TPrint;   
   
   
public:

   CommandsSetFixSurface(TPrint *print_i){
      
      RegistryCommand('3', new CmdShowOfMoveVertex<TPrint>(print_i, 2, 0));
      RegistryCommand('4', new CmdShowOfMoveVertex<TPrint>(print_i, 2, 0, 4));
      RegistryCommand('O', new CmdWriteOrientMesh<TPrint>(print_i, 2, 0));
      RegistryCommand('D', new CmdWriteOrientSubMesh<TPrint>(print_i, 2, 0));
      RegistryCommand('S', new CmdWriteNoSings<TPrint>(print_i, 2, 0));
      RegistryCommand('s', new CmdShowOfSingularities<TPrint>(print_i, 2, 0));
      RegistryCommand('o', new CmdShowOfOrientMesh<TPrint>(print_i, 2, 0));
      RegistryCommand('v', new CmdShowOfVertexStar<TPrint>(print_i, 2, 0));
   }
     
};

#endif /*CMDS_SET_FIX_SURFACE_HPP_*/
