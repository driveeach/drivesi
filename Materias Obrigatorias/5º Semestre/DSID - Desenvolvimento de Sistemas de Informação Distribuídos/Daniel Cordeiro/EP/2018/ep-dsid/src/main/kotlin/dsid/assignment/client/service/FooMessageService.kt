package dsid.assignment.client.service

import dsid.assignment.avro.FooProtocol
import dsid.assignment.constants.Technology.GRPC
import dsid.assignment.constants.technologyToUse
import dsid.assignment.proto.FooMessageServiceGrpc
import org.apache.avro.ipc.specific.SpecificRequestor
import dsid.assignment.avro.Foo as AvroFoo
import dsid.assignment.proto.Foo as ProtoFoo

object FooMessageService {
    
    private val grpcService by lazy {  FooMessageServiceGrpc.newBlockingStub(Connection.protobufChannel) }
    private val avroService by lazy {  SpecificRequestor.getClient(FooProtocol::class.java, Connection.avroClient) }
    
    
    fun sendFoo(foo: Foo): Foo {
        return if (technologyToUse == GRPC) {
            Foo(grpcService.sendFoo(foo.toProto()))
        }else {
            Foo(avroService.foo(foo.toAvro()))
        }
    }
    
    data class Foo(val id: Long, val title: String, val body: List<String>, val authors: List<Author>) {
        constructor(proto: ProtoFoo)
                : this(proto.id, proto.title, proto.bodyList, proto.authorsList.map { Author(it.id, it.name, it.surname) })
        
        constructor(avro: AvroFoo)
                : this(avro.id, avro.title, avro.body, avro.authors.map { Author(it.id, it.name, it.surname) })
        
        fun toProto(): ProtoFoo {
            return ProtoFoo.newBuilder().apply {
                id = id
                title = title
                body.forEach { addBody(it) }
                addAllAuthors(authors.map { it.toProto() })
                
            }.build()
        }
        
        fun toAvro(): AvroFoo {
            return AvroFoo(id, title, body, authors.map { it.toAvro() })
        }
    }
    
    data class Author(val id: Long, val name: String, val surname: String) {
        fun toProto(): dsid.assignment.proto.Author {
            return dsid.assignment.proto.Author.newBuilder().apply {
                id = id
                name = name
                surname = surname
            }.build()
        }
        
        fun toAvro(): dsid.assignment.avro.Author {
            return dsid.assignment.avro.Author(id, name, surname)
        }
    }
}