package com.example.buttoncheckboxetcinterfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.buttoncheckboxetcinterfaces.ui.theme.ButtonCheckBoxEtcInterfacesTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonCheckBoxEtcInterfacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BotonConDelay()
                }
            }
        }
    }
}

@Composable
fun BotonConDelay() {
    var buttonText by remember { mutableStateOf("Presionar") }
    var showProgress by remember { mutableStateOf(false) }
    var textFieldText by remember { mutableStateOf("") }
    var messageText by remember { mutableStateOf("") } // mensaje ejercicio 2 y 3
    var isChecked by remember { mutableStateOf(false) }
    var message3 by remember { mutableStateOf("") } // mensaje ejercicio 3
    var selected by rememberSaveable{mutableStateOf("Radio1")}
    val coroutineScope = rememberCoroutineScope()
    var isImageClicked by remember { mutableStateOf(true) } // Inicializado en true para mostrar la imagen desde el principio

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BasicTextField(
            value = textFieldText,
            onValueChange = { newValue -> textFieldText = newValue },
            modifier = Modifier.padding(16.dp)
        )

        // Ejercicio 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    buttonText = "Botón presionado"
                    showProgress = true
                    coroutineScope.launch {
                        delay(5000L) // El botón se queda presionado durante 5 segundos
                        showProgress = false
                        buttonText = "Presionar"
                        messageText =
                            "El botón ha sido activado." // Actualiza el mensaje después de cargar el boton
                        message3 = "El checkbox ha sido activado."
                    }
                },
                modifier = Modifier.size(200.dp, 50.dp).fillMaxWidth(),
            ) {
                Text(text = buttonText)
            }

            if (showProgress) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Ejercicio 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = messageText,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


        // Ejercicio 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Activar(El botón presionar debe de pulsarse)",
                modifier = Modifier.padding(4.dp),
                color = Color.Blue
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isChecked) {
            Text(
                text = message3,
                modifier = Modifier.padding(8.dp),
                color = Color.Blue
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Ejercicio 4
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountBox, // Icono
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Ejercicio 5 y 6
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButtonGroup(selected,{selected=it})
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Ejercicio 7


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ){
            if (isImageClicked) {
                ImageSelector()
            }
        }
    }


}
@Composable
fun RadioButtonGroup(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        Text("Selecciona una opción:")
        val options = listOf("Opción 1", "Opción 2", "Opción 3")
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) }
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
fun ImageSelector() {
    var imageIndex by remember { mutableStateOf(0) }
    val images = listOf(
        R.drawable.pepe,
        R.drawable.sad,
        R.drawable.roblox
    )

    Image(
        painter = painterResource(id = images[imageIndex]),
        contentDescription = "Imagen",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                imageIndex = (imageIndex + 1) % images.size
            }
    )
}


@Preview
@Composable
fun PreviewGreeting() {
    BotonConDelay()
}