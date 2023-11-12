package com.example.quadraticequation.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.quadraticequation.Models.Roots
import com.example.quadraticequation.MyMath
import kotlinx.coroutines.flow.MutableStateFlow

    class EquationViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
        @OptIn(SavedStateHandleSaveableApi::class)
        var a by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(""))
        }
            private set

        @OptIn(SavedStateHandleSaveableApi::class)
        var b by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(""))
        }
            private set

        @OptIn(SavedStateHandleSaveableApi::class)
        var c by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(""))
        }
            private set

        val resultState = savedStateHandle.getStateFlow(key = RESULT_STATE_KEY, initialValue = "")

        fun onResultChange(result: String) {
            savedStateHandle.set(key = RESULT_STATE_KEY, value = result)
        }

        fun update(newA: TextFieldValue = a, newB: TextFieldValue = b, newC: TextFieldValue = c) {
            a = newA
            b = newB
            c = newC
        }

        var roots: MutableStateFlow<Roots> = MutableStateFlow(
            Roots("", "")
        )

        fun getRoots() {
            val res = MyMath().calculation(
                a.text.toDouble(),
                b.text.toDouble(),
                c.text.toDouble()
            )
            roots.value.x1 = "${res[0]}"
            roots.value.x2 = "${res[1]}"
        }

        companion object {
            private const val RESULT_STATE_KEY = "resultState"
        }
    }