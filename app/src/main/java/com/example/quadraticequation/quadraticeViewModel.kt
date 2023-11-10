package com.example.quadraticequation

import android.app.Application
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import kotlin.coroutines.coroutineContext

class QuadraticeViewModel constructor(): ViewModel() {

    
    fun searchRoot(guadratice:QuadraticeModel): Pair<BigDecimal, BigDecimal> {
        return MyMath().calculation(guadratice.a, guadratice.b, guadratice.c)
    }
}