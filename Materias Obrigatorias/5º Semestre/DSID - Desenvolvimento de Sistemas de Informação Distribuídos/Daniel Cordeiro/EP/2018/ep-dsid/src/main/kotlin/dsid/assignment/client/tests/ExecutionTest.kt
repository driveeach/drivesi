package dsid.assignment.client.tests

import dsid.assignment.utils.TimeMeasurer.TimeMeasureResult

interface ExecutionTest {
    
    fun executeTest(): TimeMeasureResult
    
}