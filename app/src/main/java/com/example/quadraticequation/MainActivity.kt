package com.example.quadraticequation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.quadraticequation.ViewModels.EquationViewModel
import com.example.quadraticequation.ui.theme.QuadraticEquationTheme
import java.lang.Math.*


class MainActivity : ComponentActivity() {
    private val vm by viewModels<EquationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticEquationTheme {
                Surface(

                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(vm)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(vm:EquationViewModel) {

    val modifier = Modifier
        .fillMaxSize()

    val context = LocalContext.current

    val result = remember {
        mutableStateOf("")
    }
    val aString = remember {
        mutableStateOf("")
    }
    val bString = remember {
        mutableStateOf("")
    }
    val cString = remember {
        mutableStateOf("")
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

            OutlinedTextField(
                value = aString.value,
                onValueChange = {
                    if (it.toDoubleOrNull() != null || it == "-") {
                        vm.equation.value.a = it.toDouble()
                        aString.value = it
                    }
                },
                label = {
                    Text(stringResource(R.string.number_a))
                },
                singleLine = true,
                shape = Shapes().large,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor= Color.Blue, // цвет при получении фокуса
                    unfocusedBorderColor = Color.Black  // цвет при отсутствии фокуса
                )
            )
            OutlinedTextField(
                value = bString.value,
                onValueChange = {
                    if (it.toDoubleOrNull() != null || it == "-") {
                        vm.equation.value.b = it.toDouble()
                        bString.value = it
                    }
                },
                label = {
                    Text(stringResource(R.string.number_b))
                },
                singleLine = true,
                shape = Shapes().large,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor= Color.Blue, // цвет при получении фокуса
                    unfocusedBorderColor = Color.Black  // цвет при отсутствии фокуса
                )
            )
            OutlinedTextField(
                value = cString.value,
                onValueChange = {
                    if (it.toDoubleOrNull() != null || it == "-") {
                        vm.equation.value.c = it.toDouble()
                        cString.value = it
                    }
                },
                label = {
                    Text(stringResource(R.string.number_c))
                },
                singleLine = true,
                shape = Shapes().large,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor= Color.Blue, // цвет при получении фокуса
                    unfocusedBorderColor = Color.Black  // цвет при отсутствии фокуса
                )
            )

            Button(onClick = {
                if (aString.value != "" && bString.value != "" && cString.value != "") {

                    vm.getRoots()
                    if (vm.roots.value.x1 == vm.roots.value.x2) {
                        result.value = "${context.getString(R.string.root1_root2)} ${vm.roots.value.x2}"
                    } else {
                        result.value =
                            "${context.getString(R.string.first_root)} ${vm.roots.value.x1}, ${
                                context.getString(R.string.and_second_root)
                            } ${vm.roots.value.x2}"
                    }
                }
            },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(stringResource(R.string.button_run))
            }
            Text(result.value)
    }
}