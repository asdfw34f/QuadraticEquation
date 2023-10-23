package com.example.quadraticequation

import EquationViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun MainView() {
    val equationViewModel: EquationViewModel.EquationViewModel = viewModel()
    val roots by equationViewModel.rootsFlow.collectAsState(initial = Pair(null, null))

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

        TextField(
            value = aString.value,
            onValueChange = {
                if (it.toDoubleOrNull() != null || it == "-") aString.value = it
            },
            label = {
                Text(stringResource(R.string.number_a))
            }
        )
        TextField(
            value = bString.value,
            onValueChange = {
                if (it.toDoubleOrNull() != null || it == "-") bString.value = it
            },
            label = {
                Text(stringResource(R.string.number_b))
            }
        )
        TextField(
            value = cString.value,
            onValueChange = {
                if (it.toDoubleOrNull() != null || it == "-") cString.value = it
            },
            label = {
                Text(stringResource(R.string.number_c))
            }
        )
        Text(result.value)

        Button(onClick = {
            if (aString.value != "" && bString.value != "" && cString.value != ""){

                equationViewModel
                    .setEquation(
                        aString.value.toDouble(),
                        bString.value.toDouble(),
                        cString.value.toDouble()
                    )
                   if (roots.first == roots.second){
                       result.value = "${context.getString(R.string.root1_root2)} ${roots.first}"
                   } else{
                       result.value = "${context.getString(R.string.first_root)} ${roots.first}, ${context.getString(R.string.and_second_root)} ${roots.second}"
                   }
             //   val res = MyMath()
             //       .calculation(
             //           aString.value.toDouble(),
             //           bString.value.toDouble(),
             //           cString.value.toDouble()
             //       )
             //   if (res[0] == res[1]){
             //       result.value = "${context.getString(R.string.root1_root2)} ${res[0]}"
             //   } else{
             //       result.value = "${context.getString(R.string.first_root)} ${res[0]}, ${context.getString(R.string.and_second_root)} ${res[1]}"
             //   }
            }
        }) {
            Text(stringResource(R.string.button_run))
        }
        if (roots.first != null && roots.second != null) {
            ("Roots: ${roots.first}, ${roots.second}")
        } else {
            Text("No real roots")
        }
    }
}