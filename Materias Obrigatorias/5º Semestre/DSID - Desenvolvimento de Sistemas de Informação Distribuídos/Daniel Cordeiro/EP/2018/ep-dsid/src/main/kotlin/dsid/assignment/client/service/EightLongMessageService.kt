package dsid.assignment.client.service

import dsid.assignment.avro.EightLongProtocol
import dsid.assignment.constants.Technology.GRPC
import dsid.assignment.constants.technologyToUse
import dsid.assignment.proto.EightLong
import dsid.assignment.proto.EightLongMessageServiceGrpc
import org.apache.avro.ipc.specific.SpecificRequestor

object EightLongMessageService {
    
    private val grpcService by lazy { EightLongMessageServiceGrpc.newBlockingStub(Connection.protobufChannel) }
    private val avroService by lazy { SpecificRequestor.getClient(EightLongProtocol::class.java, Connection.avroClient) }
    
    data class EightLongs(val first: Long,
                          val second: Long,
                          val third: Long,
                          val fourth: Long,
                          val fifth: Long,
                          val sixth: Long,
                          val seventh: Long,
                          val eighth: Long) {
        fun toProto(): EightLong {
            return EightLong.newBuilder().apply {
                firstLong = first
                secondLong = second
                thirdLong = third
                fourthLong = fourth
                fifthLong = fifth
                sixthLong = sixth
                seventhLong = seventh
                eighthLong = eighth
            }.build()
        }
        
    }
    
    fun sendEightLongs(longs: EightLongs): Long {
        return if (technologyToUse == GRPC) {
            grpcService.sendLong(longs.toProto()).long
        }else {
            longs.run {
                avroService.eightLong(first, second, third, fourth, fifth, sixth, seventh, eighth)
            }
        }
    }
}