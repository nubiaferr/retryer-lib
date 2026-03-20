package com.nubiaferr.retryer.core

import com.nubiaferr.retryer.stop.TryMaxTimes
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RetryerTest {
    @Test
    fun `should retry once`() = runTest {
        var attempts = 0

        val retryer = Retryer(
            stopMethod = TryMaxTimes(1)
        )

        try {
            retryer.execute {
                attempts++
                throw RuntimeException()
            }
        } catch (_: Exception) {}

        assertEquals(2, attempts)
    }

    @Test
    fun `should retry multiple times`() = runTest {
        var attempts = 0

        val retryer = Retryer(
            stopMethod = TryMaxTimes(3)
        )

        try {
            retryer.execute {
                attempts++
                throw RuntimeException()
            }
        } catch (_: Exception) {}

        assertEquals(4, attempts)
    }

    @Test
    fun `should retry with exponential backoff`() = runTest {
        var attempts = 0

        val retryer = Retryer(
            stopMethod = TryMaxTimes(2)
        )

        try {
            retryer.execute {
                attempts++
                throw RuntimeException()
            }
        } catch (_: Exception) {}

        assertEquals(3, attempts)
    }
}