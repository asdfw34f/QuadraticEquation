package com.example.quadraticequation.ViewModels

import androidx.lifecycle.ViewModel
import com.example.quadraticequation.Models.EquationModel
import com.example.quadraticequation.Models.Roots
import com.example.quadraticequation.MyMath
import kotlinx.coroutines.flow.MutableStateFlow

    class EquationViewModel : ViewModel() {

        val equation: MutableStateFlow<EquationModel> = MutableStateFlow(
            EquationModel()
        )
        val roots: MutableStateFlow<Roots> = MutableStateFlow(
            Roots("","")
        )

        fun getRoots(){
            val res = MyMath().calculation(
                equation.value.a,
                equation.value.b,
                equation.value.c
            )
            roots.value.x1 = "${res[0]}"
            roots.value.x2 = "${res[1]}"
        }
    }