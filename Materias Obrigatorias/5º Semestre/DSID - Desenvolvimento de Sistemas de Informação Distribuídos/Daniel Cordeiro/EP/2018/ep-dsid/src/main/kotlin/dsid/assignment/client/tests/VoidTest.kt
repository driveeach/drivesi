package dsid.assignment.client.tests

import dsid.assignment.client.service.VoidMessageService
import dsid.assignment.utils.TimeMeasurer
import dsid.assignment.utils.TimeMeasurer.TimeMeasureResult

object VoidTest : ExecutionTest {
    
    override fun executeTest(): TimeMeasureResult {
        
        return TimeMeasurer.measureExecution {
            VoidMessageService.sendVoidMessage()
        }
    }
    
}