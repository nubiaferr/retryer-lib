package com.nubiaferr.retryer.wait

class ExponentialBackoff(
    private val baseMillis: Long
) : WaitMethod {
    override fun nextWait(tryNumber: Int): Long {
        return baseMillis * (1L shl tryNumber)
    }
}