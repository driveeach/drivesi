package dsid.assignment.client.tests

import dsid.assignment.client.service.StringMessageService
import dsid.assignment.utils.TimeMeasurer
import dsid.assignment.utils.TimeMeasurer.TimeMeasureResult

class StringTest(private val stringSize: Int) : ExecutionTest {
    
    override fun executeTest(): TimeMeasureResult {
        val string = createString()
        
        return TimeMeasurer.measureExecution {
            StringMessageService.sendStringMessage(string)
        }
    }
    
    private fun createString(): String {
        val builder = StringBuilder()
        
        for(i in 0 until stringSize) {
            builder.append("abcdefghijklmnopqrstuvxwyz".randomChar())
        }
        
        return builder.toString()
    }
    
    
    private fun String.randomChar(): Char {
        return this.toCharArray().toList().shuffled().first()
    }
}
