package dsid.assignment.client.service

import dsid.assignment.constants.serverAddress
import dsid.assignment.constants.serverPort
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.apache.avro.ipc.NettyTransceiver
import java.net.InetSocketAddress

object Connection {
    
    val protobufChannel: ManagedChannel by lazy {
        ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext(true).build()
    
    }
    
    val avroClient by lazy {
        NettyTransceiver(InetSocketAddress(serverAddress, serverPort))
    }
}