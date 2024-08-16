package com.example.calculatorusingcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorusingcomposable.ui.theme.CalculatorUsingComposableTheme
import javax.xml.xpath.XPathExpression

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorUsingComposableTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Display(inputInfix: String){
    Text(
        text = "$inputInfix"
    )
}

@Composable
fun ItemButton(
    symbol: String,
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onContinueClicked,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 4.dp)
    ) {
        Text("$symbol")
    }
}



@Preview(showBackground = true)
@Composable
fun ButtonRows(
    itemsPerColumn: List<String> = List(4) { "$it" },
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
       for (i in 0..4){
           ItemButton("Siu", onContinueClicked = {})
       }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonLists (
    modifier: Modifier = Modifier
) {
    Column (){

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorUsingComposableTheme {
        Greeting("Android")
    }
}