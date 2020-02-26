package dsid.assignment.client.service

import dsid.assignment.avro.SingleLongProtocol
import dsid.assignment.constants.Technology.GRPC
import dsid.assignment.constants.technologyToUse
import dsid.assignment.proto.SingleLong
import dsid.assignment.proto.SingleLongMessageServiceGrpc
import org.apache.avro.ipc.specific.SpecificRequestor

object SingleLongMessageService {
    
    private val grpcService by lazy { SingleLongMessageServiceGrpc.newBlockingStub(Connection.protobufChannel) }
    private val avroService by lazy{ SpecificRequestor.getClient(SingleLongProtocol::class.java, Connection.avroClient) }
    
    fun sendLongMessage(long: Long): Long {
        return if(technologyToUse == GRPC) {
            val message = SingleLong.newBuilder().setLong(long).build()
            grpcService.sendLong(message).long
        }else {
            avroService.singleLong(long)
        }
    }
}