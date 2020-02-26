package dsid.assignment.client.tests

import dsid.assignment.client.service.FooMessageService
import dsid.assignment.client.service.FooMessageService.Author
import dsid.assignment.client.service.FooMessageService.Foo
import dsid.assignment.utils.TimeMeasurer
import dsid.assignment.utils.TimeMeasurer.TimeMeasureResult
import java.util.*

object FooTest : ExecutionTest {
    
    override fun executeTest(): TimeMeasureResult {
        val foo = generateFoo()
        return TimeMeasurer.measureExecution {
            FooMessageService.sendFoo(foo)
        }
    }
    
    val random = Random()
    
    private fun generateFoo(): Foo {
        val id = random.nextLong()
        val title = randomStringOfSize(120)
        
        val body = mutableListOf<String>()
        for (i in 1..6) {
            body.add(randomStringOfSize(1_000))
        }
     
        val authorList = mutableListOf<Author>()
        for (i in 1..random.nextInt(20)) {
            authorList.add(Author(random.nextLong(), randomStringOfSize(100), randomStringOfSize(100)))
        }
        
        return Foo(id, title, body, authorList)
    }
    
    private fun randomStringOfSize(size: Int): String {
        val possibilities = "abcdefghijklmnopqrstuvxwyz"
        
        val builder = StringBuilder()
        for (i in 1..size) {
            builder.append(possibilities.toList().shuffled().first())
        }
        return builder.toString()
    }
}