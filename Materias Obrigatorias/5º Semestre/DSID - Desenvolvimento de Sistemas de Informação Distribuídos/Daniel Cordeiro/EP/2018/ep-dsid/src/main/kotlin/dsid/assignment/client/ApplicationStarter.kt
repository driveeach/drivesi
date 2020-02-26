package dsid.assignment.client

import dsid.assignment.client.tests.ExecutionTest
import dsid.assignment.client.tests.FooTest
import dsid.assignment.client.tests.LongTest
import dsid.assignment.client.tests.StringTest
import dsid.assignment.client.tests.VoidTest
import dsid.assignment.constants.Technology
import dsid.assignment.constants.serverAddress
import dsid.assignment.constants.serverPort
import dsid.assignment.constants.technologyToUse

/**
 * First Argument: What is being tested.
 * Can be any of [void, singlelong, eightlongs, string1, string2, string4, string8, string16, ..., string1024, foo]
 * foo is a complex protobuf object.
 *
 * Second argument: the server host (defaults to localhost)
 *
 * Third argument: the server port (defaults to 8081)
 *
 * Fourth argument: the technology to use (defaults to GRPC). Can be any of [GRPC, OTHER]
 */
fun main(args: Array<String>) {
    val firstArg = args[0]
    
    val tester = firstArg.mapToTester()
    
    serverAddress = args.getOrElse(1) { _ -> "localhost"}
    serverPort = args.getOrElse(2) {_ -> "8081"}.toInt()
    technologyToUse = Technology.valueOf(args.getOrElse(3){ _ -> "GRPC"})
    
    println("$firstArg -  ${tester.executeTest()}")
    System.exit(0)
}

private fun String.mapToTester(): ExecutionTest {
    return when {
        this == "void" -> VoidTest
        this == "singlelong" -> LongTest(1)
        this == "eightlongs" -> LongTest(8)
        this == "foo" -> FooTest
        
        this.contains("string", true) -> {
            val number = this.filter { it.isDigit() }.toInt()
            
            if (number < 1 || number > 1024 || (number % 2 != 0 && number != 1)) throw IllegalArgumentException("$number must be in [1,1024] and a power of 2")
            
            StringTest(number)
        }
        
        else -> throw IllegalArgumentException("Unknown first argument: $this. First argument must be any of [void, singlelong, eightlongs, string1, string2, string4, string8, string16, ..., string1024, foo]")
    }
}
