package com.nubiaferr.retryer.wait

class FixedWait(
    private val waitMillis: Long
) : WaitMethod {
    override fun nextWait(tryNumber: Int): Long = waitMillis
}