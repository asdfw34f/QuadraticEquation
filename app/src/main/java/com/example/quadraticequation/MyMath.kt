package com.example.quadraticequation

import androidx.compose.ui.res.stringResource
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sqrt

class MyMath {
    private fun Determinant(a: Double, b: Double, c: Double):Double = b * b - 4.0 * a * c

    fun calculation(a: Double, b: Double,c: Double):String{
        val root1: BigDecimal
        val root2: BigDecimal

        val myDeterminant = Determinant(a, b, c)

        if (myDeterminant > 0) {
            root1 = BigDecimal((-b + sqrt(myDeterminant)) / (2 * a)).setScale(
                2,
                RoundingMode.HALF_EVEN
            )
            root2 = BigDecimal((-b - sqrt(myDeterminant)) / (2 * a)).setScale(
                2,
                RoundingMode.HALF_EVEN
            )
            return "${R.string.first_root} $root1, ${R.string.and_second_root} $root2"
        } else if (myDeterminant == 0.0) {
            root2 = BigDecimal(-b / (2 * a)).setScale(2, RoundingMode.HALF_EVEN)
            root1 = root2
            return "${R.string.root1_root2} $root1"
        } else {
            val realPart = BigDecimal(-b / (2 * a)).setScale(2, RoundingMode.HALF_EVEN)
            val imaginaryPart =
                BigDecimal(sqrt(-myDeterminant) / (2 * a)).setScale(
                    2,
                    RoundingMode.HALF_EVEN
                )
            return "${R.string.first_root} $realPart+$imaginaryPart ${R.string.and_second_root} $realPart-$imaginaryPart"
        }
    }
}