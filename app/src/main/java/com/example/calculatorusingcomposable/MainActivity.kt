package com.example.calculatorusingcomposable


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorusingcomposable.ui.theme.CalculatorUsingComposableTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorUsingComposableTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Calculator()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("")}
    var subtitle by remember { mutableStateOf("")}
    val placeholder = "0"

    Column {
        Display(title,subtitle,placeholder)
        ButtonLists(
            onButtonClick = {value ->
                title += value
            }
        )
    }

}

@Composable
fun Display(title: String, subtitle: String, placeholder: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ){
        Text(
            text = if(title.isEmpty()) placeholder else title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.End
        )
        Text(
            text = if(subtitle.isEmpty()) placeholder else subtitle,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.End
        )
    }
    }
}


@Composable
fun ItemButton(
    symbol: String,
    onContinueClicked: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onContinueClicked,

        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
    ) {
        Text("$symbol")
    }
}

@Composable
fun ButtonRows(
    buttons: List<CalculatorButtonData>,
    onButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
       buttons.forEach{ button ->
           ItemButton(
               symbol = button.buttonText ,
               onContinueClicked = { onButtonClick(button.onClickValue) })
       }
    }
}

@Composable
fun ButtonLists (
    onButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var buttonGroups = calculatorButtons.chunked(4)
    Column {
        buttonGroups.forEach{ group ->
            ButtonRows(
                buttons = group,
                onButtonClick = onButtonClick,
                color
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplay(){
    Display(title = "", subtitle = "", placeholder = "0")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorUsingComposableTheme {
        Calculator()
    }
}

data class CalculatorButtonData(val buttonText: String, val onClickValue: String)
private val calculatorButtons = listOf(
    CalculatorButtonData("C", "C"),
    CalculatorButtonData("√", "√"),
    CalculatorButtonData("π", "π"),
    CalculatorButtonData("÷", "÷"),

    CalculatorButtonData("7", "7"),
    CalculatorButtonData("8", "8"),
    CalculatorButtonData("9", "9"),
    CalculatorButtonData("*", "*"),

    CalculatorButtonData("4", "4"),
    CalculatorButtonData("5", "5"),
    CalculatorButtonData("6", "6"),
    CalculatorButtonData("-", "-"),

    CalculatorButtonData("1", "1"),
    CalculatorButtonData("2", "2"),
    CalculatorButtonData("3", "3"),
    CalculatorButtonData("+", "+"),

    CalculatorButtonData(".", "."),
    CalculatorButtonData("0", "0"),
    CalculatorButtonData("=", "=")
)
