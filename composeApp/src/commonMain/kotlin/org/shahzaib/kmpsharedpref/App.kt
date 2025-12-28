package org.shahzaib.kmpsharedpref

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import createSharedPref

@Composable
fun App() {
    MaterialTheme {
        val kmpPref = remember { createSharedPref("MyCustomPrefName") }

        // Load saved values from SharedPref
        var textValue by remember { mutableStateOf(kmpPref.getString("text_value", "")) }
        var switchValue by remember { mutableStateOf(kmpPref.getBool("switch_value", false)) }
        var longValue by remember { mutableStateOf(kmpPref.getLong("long_value", 0L)) }
        var floatValue by remember { mutableStateOf(kmpPref.getFloat("float_value", 0f)) }

        // Temporary states for inputs
        var tempText by remember { mutableStateOf(textValue) }
        var tempLong by remember { mutableStateOf(longValue.toString()) }
        var tempFloat by remember { mutableStateOf(floatValue.toString()) }

        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
        ) {
            Text("User Form", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            // Text input
            TextField(
                    value = tempText,
                    onValueChange = { tempText = it },
                    label = { Text("Enter text") },
                    modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Boolean switch
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enable feature", modifier = Modifier.weight(1f))
                Switch(
                        checked = switchValue,
                        onCheckedChange = { switchValue = it }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Long input
            TextField(
                    value = tempLong,
                    onValueChange = { tempLong = it },
                    label = { Text("Enter long value") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Float input
            TextField(
                    value = tempFloat,
                    onValueChange = { tempFloat = it },
                    label = { Text("Enter float value") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Save button
            Button(
                    onClick = {
                        // Save values to SharedPref
                        textValue = tempText
                        kmpPref.saveString("text_value", textValue)

                        switchValue = switchValue
                        kmpPref.saveBool("switch_value", switchValue)

                        longValue = tempLong.toLongOrNull() ?: 0L
                        kmpPref.saveLong("long_value", longValue)

                        floatValue = tempFloat.toFloatOrNull() ?: 0f
                        kmpPref.saveFloat("float_value", floatValue)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Save")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Display saved values
            Text("Saved values:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Text: $textValue")
            Text("Switch: $switchValue")
            Text("Long: $longValue")
            Text("Float: $floatValue")
        }
    }
}

