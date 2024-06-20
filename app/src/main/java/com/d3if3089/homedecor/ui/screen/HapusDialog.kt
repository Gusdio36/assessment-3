package com.d3if3089.homedecor.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d3if3089.homedecor.R
import com.d3if3089.homedecor.ui.component.RegularText
import com.d3if3089.homedecor.model.Decoration
import com.d3if3089.homedecor.network.Api
import com.d3if3089.homedecor.ui.theme.CustomBeigeBG
import com.d3if3089.homedecor.ui.theme.CustomGrayText
import com.d3if3089.homedecor.ui.theme.CustomRedText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HapusDialog(
    data: Decoration,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = cardColors(containerColor = CustomBeigeBG)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RegularText(text = "Delete this post?", color = CustomGrayText)
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Api.getImageUrl(data.image_id))
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image ${data.nama_dekorasi}",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.loading_img),
                    error = painterResource(id = R.drawable.broken_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                    onClick = { onConfirmation() },
                    modifier = Modifier.padding(8.dp),
                    border = BorderStroke(1.dp, CustomRedText)
                ) {
                    RegularText(text = "Delete", color = CustomRedText)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
//    HapusDialog(onDismissRequest = { /*TODO*/ }) {
//
//    }
}
