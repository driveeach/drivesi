package dsid.assignment.client.service

import dsid.assignment.avro.StringProtocol
import dsid.assignment.constants.Technology.GRPC
import dsid.assignment.constants.technologyToUse
import dsid.assignment.proto.StringMessage
import dsid.assignment.proto.StringMessageServiceGrpc
import org.apache.avro.ipc.specific.SpecificRequestor

object StringMessageService {
    
    private val grpcService by lazy {  StringMessageServiceGrpc.newBlockingStub(Connection.protobufChannel) }
    private val avroService by lazy { SpecificRequestor.getClient(StringProtocol::class.java, Connection.avroClient) }
    
    fun sendStringMessage(string: String) : String {
        return if (technologyToUse == GRPC) {
            val message = StringMessage.newBuilder().setValue(string).build()
            grpcService.sendString(message).value
        }else {
            avroService.stringmessage(string)
        }
    }
    
}