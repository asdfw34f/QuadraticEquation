package com.example.quadraticequation

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class MyMathUnitTest {
    @Test
    fun calculate_assert1(){
        val root1: BigDecimal = BigDecimal(0.25).setScale(
            2,
            RoundingMode.HALF_EVEN
        )
        val root2: BigDecimal = BigDecimal(-1).setScale(
            2,
            RoundingMode.HALF_EVEN
        )

        assertEquals(listOf(root1, root2), MyMath().calculation(4.0, 3.0, -1.0))
    }

    @Test
    fun calculate_assert2(){
        val root1: BigDecimal = BigDecimal(-1.79).setScale(
            2,
            RoundingMode.HALF_EVEN
        )
        val root2: BigDecimal = BigDecimal(2.79).setScale(
            2,
            RoundingMode.HALF_EVEN
        )

        assertEquals(listOf(root1, root2), MyMath().calculation(-2.0, 2.0, -11.0))
    }

    @Test
    fun calculate_assert3(){
        val root1: BigDecimal = BigDecimal(0.53).setScale(
            2,
            RoundingMode.HALF_EVEN
        )
        val root2: BigDecimal = BigDecimal(-0.41).setScale(
            2,
            RoundingMode.HALF_EVEN
        )

        assertEquals(listOf(root1, root2), MyMath().calculation(9.0, -1.0, 2.0))
    }
}