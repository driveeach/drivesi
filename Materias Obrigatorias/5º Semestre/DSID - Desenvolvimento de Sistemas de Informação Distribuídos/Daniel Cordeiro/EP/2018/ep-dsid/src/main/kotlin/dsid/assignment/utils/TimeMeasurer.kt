package dsid.assignment.utils

import com.google.common.base.Stopwatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MILLISECONDS

object TimeMeasurer {
    
    data class TimeMeasureResult(val mean: Double, val standardDeviation: Double) {
        override fun toString(): String {
            return "Mean: ${"%.3f".format(mean)} Standard Deviation: ${"%.3f".format(standardDeviation)}"
        }
    }
    
    
    fun measureExecution(timesToExecute: Int = 100,
                         timeUnit: TimeUnit = MILLISECONDS,
                         block: () -> Unit) : TimeMeasureResult {
        
        val timeList = mutableListOf<Long>()
        
        for(i in 0 until timesToExecute) {
            val executionTime = timedExecute(timeUnit, block)
            timeList.add(executionTime)
        }
        
        val mean = timeList.calculateMean()
        val standardDeviation = timeList.calculateStandardDeviation(mean)
        
        return TimeMeasureResult(mean, standardDeviation)
    }
    
    private fun timedExecute(timeUnit: TimeUnit, block: () -> Unit) : Long {
        val stopwatch = Stopwatch.createStarted()
        block()
        stopwatch.stop()
        
        return stopwatch.elapsed(timeUnit)
        
    }
    
    private fun List<Long>.withoutMaximaAndMinima(): MutableList<Long> {
        return toMutableList().apply {
            removeMax()
            removeMin()
        }
    }
    
    private fun List<Long>.calculateStandardDeviation(mean: Double): Double {
        
        //Accumulates into accumulator the values of [(element - mean) ^ 2]
        val totalDeviation =  withoutMaximaAndMinima().fold(0.0) { accumulator: Double, next: Long ->
            accumulator + Math.pow(next - mean, 2.0)
        }
        
        val averageDeviation = totalDeviation / size
        return Math.sqrt(averageDeviation)
    }
    
    
    private fun List<Long>.calculateMean(): Double {
        
        return this.withoutMaximaAndMinima().average()
    }
    
    private fun MutableList<Long>.removeMax() {
        remove(max())
    }
    
    private fun MutableList<Long>.removeMin() {
        remove(min())
    }
    
}