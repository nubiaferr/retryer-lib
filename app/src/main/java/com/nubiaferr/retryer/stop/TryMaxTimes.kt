package com.nubiaferr.retryer.stop

class TryMaxTimes(
    private val maxRetries: Int
) : StopMethod {
    override fun nextTry(tryNumber: Int): Boolean {
        return tryNumber < maxRetries
    }
}