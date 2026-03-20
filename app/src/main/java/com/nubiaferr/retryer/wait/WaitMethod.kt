package com.nubiaferr.retryer.wait

interface WaitMethod {
    fun nextWait(tryNumber: Int): Long
}