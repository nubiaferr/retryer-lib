package com.nubiaferr.retryer.stop

interface StopMethod {
    fun nextTry(tryNumber: Int): Boolean
}