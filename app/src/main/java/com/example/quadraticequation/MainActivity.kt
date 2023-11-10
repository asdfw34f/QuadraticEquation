package com.example.quadraticequation

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quadraticequation.ui.theme.QuadraticEquationTheme
import java.lang.Math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticEquationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .width(300.dp)
                        .height(950.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainView(
    vm: QuadraticeViewModel = viewModel()
) {

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = aString.value,
            onValueChange = {
                if (it.toDoubleOrNull() != null || it == "-") aString.value = it
            },
            label = {
                Text(stringResource(R.string.number_a))
            }
        )

        OutlinedTextField(
            value = bString.value,
            onValueChange = {
                if (it.toDoubleOrNull() != null || it == "-") bString.value = it
            },
            label = {
                Text(stringResource(R.string.number_b))
            }
        )
/*
  Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = if ((it.toIntOrNull() != null) || (it == "-") || it.isNullOrBlank()) it  else number1},
            label = { Text(text = "Number 1") }
        )
 */
        OutlinedTextField(
            value = cString.value,
            onValueChange = {
                if (it.toDoubleOrNull() != null || it == "-") cString.value = it
            },
            label = {
                Text(stringResource(R.string.number_c))
            }
        )
        Spacer(modifier = Modifier )
        Text(result.value)

        Button(onClick = {
            if (aString.value != "" && bString.value != "" && cString.value != "") {
                 val res = vm.searchRoot(
                    QuadraticeModel(
                        aString.value.toDouble(),
                        bString.value.toDouble(),
                        cString.value.toDouble()
                    )
                )

                result.value = if (res.first == res.second) {
                    "${context.getString(R.string.root1_root2)} ${res.second}"
                } else {
                    "${context.getString(R.string.first_root)} ${res.second}, ${
                        context.getString(
                            R.string.and_second_root
                        )
                    } ${res.first}"
                }
            }
        }) {
            Text(stringResource(R.string.button_run))
        }
    }
}