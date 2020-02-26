package dsid.assignment.client.tests

import dsid.assignment.client.service.EightLongMessageService
import dsid.assignment.client.service.EightLongMessageService.EightLongs
import dsid.assignment.client.service.SingleLongMessageService
import dsid.assignment.utils.TimeMeasurer
import dsid.assignment.utils.TimeMeasurer.TimeMeasureResult

class LongTest(amountOfLongs: Int) : ExecutionTest {
    
    private val executor: ExecutionTest = if (amountOfLongs == 1) {
        SingleLongTest
    }else {
        EightLongTest
    }
    
    override fun executeTest(): TimeMeasureResult {
        return executor.executeTest()
    }
    
    object SingleLongTest : ExecutionTest {
        override fun executeTest(): TimeMeasureResult {
            return TimeMeasurer.measureExecution {
                SingleLongMessageService.sendLongMessage(42L)
            }
        }
    
    }
    
    object EightLongTest : ExecutionTest {
        
        override fun executeTest(): TimeMeasureResult {
            return TimeMeasurer.measureExecution {
                EightLongMessageService.sendEightLongs(EightLongs(1, 2, 3, 4, 5, 6, 7, 8))
            }
        }
    
    }
}