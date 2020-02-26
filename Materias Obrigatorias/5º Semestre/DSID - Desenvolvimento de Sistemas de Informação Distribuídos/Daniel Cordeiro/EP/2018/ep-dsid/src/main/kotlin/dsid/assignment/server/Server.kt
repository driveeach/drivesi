package dsid.assignment.server

import dsid.assignment.avro.AllProtocols
import dsid.assignment.constants.Technology
import dsid.assignment.constants.Technology.GRPC
import dsid.assignment.proto.EightLong
import dsid.assignment.proto.EightLongMessageServiceGrpc
import dsid.assignment.proto.Empty
import dsid.assignment.proto.Foo
import dsid.assignment.proto.FooMessageServiceGrpc
import dsid.assignment.proto.SingleLong
import dsid.assignment.proto.SingleLongMessageServiceGrpc
import dsid.assignment.proto.StringMessage
import dsid.assignment.proto.StringMessageServiceGrpc
import dsid.assignment.proto.VoidMessageServiceGrpc
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import org.apache.avro.ipc.NettyServer
import org.apache.avro.ipc.specific.SpecificResponder
import java.net.InetSocketAddress


object VoidServiceImpl : VoidMessageServiceGrpc.VoidMessageServiceImplBase() {
    
    
    override fun sendVoid(request: Empty, responseObserver: StreamObserver<Empty>?) {
        responseObserver?.onNext(request)
        responseObserver?.onCompleted()
    }
}

object SingleLongServiceImpl : SingleLongMessageServiceGrpc.SingleLongMessageServiceImplBase() {
    

    
    override fun sendLong(request: SingleLong, responseObserver: StreamObserver<SingleLong>?) {
        responseObserver?.onNext(request)
        responseObserver?.onCompleted()
    }
}

object EightLongServiceImpl : EightLongMessageServiceGrpc.EightLongMessageServiceImplBase() {
    
    override fun sendLong(request: EightLong, responseObserver: StreamObserver<SingleLong>?) {
        responseObserver?.onNext(SingleLong.newBuilder().setLong(request.firstLong).build())
        responseObserver?.onCompleted()
    }
}

object StringMessageServiceImpl : StringMessageServiceGrpc.StringMessageServiceImplBase() {
    
    
    override fun sendString(request: StringMessage, responseObserver: StreamObserver<StringMessage>?) {
        responseObserver?.onNext(request)
        responseObserver?.onCompleted()
    }
}


object FooMessageServiceImpl : FooMessageServiceGrpc.FooMessageServiceImplBase() {
    

    
    override fun sendFoo(request: Foo, responseObserver: StreamObserver<Foo>?) {
        responseObserver?.onNext(request)
        responseObserver?.onCompleted()
    }
}

object AllProtocolsImpl : AllProtocols {
    override fun eightLong(first: Long,
                           second: Long,
                           third: Long,
                           fourth: Long,
                           fifth: Long,
                           sixth: Long,
                           seventh: Long,
                           eighth: Long
    ): Long {
        return first
    }
    
    override fun foo(foo: dsid.assignment.avro.Foo): dsid.assignment.avro.Foo {
        return foo
    }
    
    override fun singleLong(l: Long): Long {
        return l
    }
    
    override fun stringmessage(value: String): String {
        return value
    }
    
    override fun voidmessage(): Void? {
        return null
    }
    
}



/**
 * First Argument: The port to run the server. Defaults to 8081
 *
 * Second Argument: What Technology is being launched. Defaults to GRPC. Can be any of [GRPC, OTHER]
 */
fun main(args: Array<String>) {
    val port = args.getOrElse(0) { _ -> "8081" }.toInt()
    val tech = Technology.valueOf(args.getOrElse(1) { _ -> "GRPC" })
    
    
    if (tech == GRPC) {
        val server = ServerBuilder.forPort(port)
                .addService(VoidServiceImpl)
                .addService(SingleLongServiceImpl)
                .addService(EightLongServiceImpl)
                .addService(StringMessageServiceImpl)
                .addService(FooMessageServiceImpl)
                .build()
    
        println("Starting server for GRPC")
        server.start()
        println("Server started for GRPC")
        Runtime.getRuntime().addShutdownHook(Thread { println("JVM shutdown - Server Shutdown") })
        server.awaitTermination()
    }else {
        val server = NettyServer(SpecificResponder(AllProtocols.PROTOCOL, AllProtocolsImpl), InetSocketAddress(port))
        
        println("Starting server for AVRO")
        server.start()
        println("Server started for AVRO")
        server.join()
    }
}