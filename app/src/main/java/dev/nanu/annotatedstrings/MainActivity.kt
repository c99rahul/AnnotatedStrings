package dev.nanu.annotatedstrings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.nanu.annotatedstrings.ui.theme.AnnotatedStringsTheme
import dev.nanu.annotatedstrings.ui.theme.GreatVibes

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AnnotatedStringsTheme {
        Surface(color = MaterialTheme.colors.background) {
          Box(
            modifier = Modifier.fillMaxSize()
          ) {

            // Add an image that acts as a background
            Image(ImageBitmap.imageResource(
              id = R.drawable.hello_i_m_nik_z1d_lp8sjui_unsplash),
              contentDescription = "Background Image",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )

            // Content column
            Column(
              modifier = Modifier
                .fillMaxSize()
                .background(
                  brush = Brush.verticalGradient(
                    colors = listOf(
                      Color.Transparent, // Starting color of the gradient
                      MaterialTheme.colors.surface.copy(alpha = 0.4f) // Ending color of the gradient
                    )
                  )
                ),
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Bottom
            ) {

              // Get/define placeholder values
              val photoOwner = "Hello I'm Nik"
              val photoHost = "Unsplash"

              // Get the resource template
              val photoCredits = stringResource(id = R.string.photo_credits_msg, photoOwner, photoHost)

              // Find the starting point of placeholder values
              val photoOwnerStart = photoCredits.indexOf(photoOwner)
              val phMsgStart = photoCredits.indexOf(photoHost)

              // Add style annotations to our placeholder values
              val photoCreditsStyles = listOf(
                AnnotatedString.Range(
                  SpanStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                  ),
                  start = photoOwnerStart,
                  end = photoOwnerStart + photoOwner.length
                ),
                AnnotatedString.Range(
                  SpanStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontFamily = GreatVibes // Custom font for photoHost
                  ),
                  start = phMsgStart,
                  end = phMsgStart + photoHost.length,
                )
              )

              // Finally display the template with finished placeholder values
              Card(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(40.dp, 60.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp
              ) {
                Text(
                  text = AnnotatedString(text = photoCredits, spanStyles = photoCreditsStyles),
                  color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                  fontSize = 21.sp,
                  textAlign = TextAlign.Center,
                )
              }
            }
          }
        }
      }
    }
  }
}