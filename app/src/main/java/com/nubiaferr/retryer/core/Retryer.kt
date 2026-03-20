package com.nubiaferr.retryer.core

import com.nubiaferr.retryer.stop.StopMethod
import com.nubiaferr.retryer.stop.TryMaxTimes
import com.nubiaferr.retryer.wait.FixedWait
import com.nubiaferr.retryer.wait.WaitMethod
import kotlinx.coroutines.delay

class Retryer(
    private val stopMethod: StopMethod = TryMaxTimes(1),
    private val waitMethod: WaitMethod = FixedWait(0)
) {

    suspend fun <T> execute(call: suspend () -> T): T {
        var tryNumber = 0

        while (true) {
            try {
                return call()
            } catch (e: Exception) {
                if (stopMethod.nextTry(tryNumber)) {
                    val waitTime = waitMethod.nextWait(tryNumber)
                    delay(waitTime)
                    tryNumber++
                } else {
                    throw e
                }
            }
        }
    }
}