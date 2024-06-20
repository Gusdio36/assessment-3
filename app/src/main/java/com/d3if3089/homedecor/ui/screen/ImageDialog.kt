package com.d3if3089.homedecor.ui.screen

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.d3if3089.homedecor.ui.component.Poppins
import com.d3if3089.homedecor.ui.component.RegularText
import com.d3if3089.homedecor.ui.theme.CustomBeigeBG
import com.d3if3089.homedecor.ui.theme.CustomGrayText
import com.d3if3089.homedecor.ui.theme.CustomGreenText

@Composable
fun ImageDialog(
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String, String) -> Unit
) {
    var namaDekorasi by remember { mutableStateOf("") }
    var tipeDekorasi by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = cardColors(containerColor = CustomBeigeBG, contentColor = CustomGrayText)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                OutlinedTextField(
                    value = namaDekorasi,
                    onValueChange = { namaDekorasi = it },
                    label = {
                        RegularText(text = "Nama Dekorasi")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    textStyle = TextStyle(color = CustomGrayText, fontFamily = Poppins)
                )
                OutlinedTextField(
                    value = tipeDekorasi,
                    onValueChange = { tipeDekorasi = it },
                    label = {
                        RegularText(text = "Tipe Dekorasi")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    textStyle = TextStyle(color = CustomGrayText, fontFamily = Poppins)
                )
                OutlinedTextField(
                    value = harga,
                    onValueChange = { harga = it },
                    label = {
                        RegularText(text = "Harga")
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.padding(top = 8.dp),
                    textStyle = TextStyle(color = CustomGrayText, fontFamily = Poppins)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                    border = BorderStroke(1.dp, CustomGrayText)
                ) {
                    RegularText(text = "Cancel", color = CustomGrayText)
                }
                OutlinedButton(
                    onClick = { onConfirmation(namaDekorasi, tipeDekorasi, harga) },
                    enabled = namaDekorasi.isNotEmpty() && tipeDekorasi.isNotEmpty() && harga.isNotEmpty(),
                    modifier = Modifier.padding(8.dp),
                    border = BorderStroke(
                        1.dp,
                        if (namaDekorasi.isNotEmpty() && tipeDekorasi.isNotEmpty() && harga.isNotEmpty()) CustomGreenText else CustomGrayText
                    )
                ) {
                    if (namaDekorasi.isNotEmpty() && tipeDekorasi.isNotEmpty() && harga.isNotEmpty())
                        RegularText(text = "Save", color = CustomGreenText)
                    else
                        RegularText(text = "Save", color = CustomGrayText)

                }
            }
        }
    }
}

@Composable
fun RadiOptions(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
