package dsid.assignment.client.service

import dsid.assignment.constants.Technology.GRPC
import dsid.assignment.constants.technologyToUse
import dsid.assignment.proto.Empty
import dsid.assignment.proto.VoidMessageServiceGrpc
import dsid.assignment.proto.VoidProtocol
import org.apache.avro.ipc.specific.SpecificRequestor


object VoidMessageService {
    
    private val grpcService by lazy { VoidMessageServiceGrpc.newBlockingStub(Connection.protobufChannel) }
    private val avroService by lazy { SpecificRequestor.getClient(VoidProtocol::class.java, Connection.avroClient) }
    
    private val empty = Empty.newBuilder().build()
    
    fun sendVoidMessage() {
        if (technologyToUse == GRPC) {
            grpcService.sendVoid(empty)
        }else {
            avroService.voidmessage()
        }
    }
    
}