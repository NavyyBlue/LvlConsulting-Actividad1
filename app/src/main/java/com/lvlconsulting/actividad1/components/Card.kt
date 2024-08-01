package com.lvlconsulting.actividad1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lvlconsulting.actividad1.ui.theme.BackgroundIconColor
import com.lvlconsulting.actividad1.ui.theme.SecondaryColor
import com.lvlconsulting.actividad1.ui.theme.TextColor

@Composable
fun BoardCard(
    iconResId: Int,
    title: String,
    code: String,
    status: String,
    statusColor: Color
) {
    val cutSize = 40.dp
    val cornerRadius = 12.dp

    val density = LocalDensity.current.density
    val cutSizePx = cutSize.value * density
    val radiusPx = cornerRadius.value * density

    val customShape = GenericShape { size, _ ->
        val path = android.graphics.Path().apply {
            addRoundRect(
                android.graphics.RectF(0f, 0f, size.width, size.height),
                radiusPx,
                radiusPx,
                android.graphics.Path.Direction.CW
            )

            addRoundRect(
                android.graphics.RectF(
                    size.width - cutSizePx,
                    0f,
                    size.width,
                    cutSizePx
                ),
                radiusPx,
                radiusPx,
                android.graphics.Path.Direction.CCW
            )
        }

        addPath(path.asComposePath())
    }

    val cardShape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .size(91.23.dp)
                .offset(x = (50).dp, y = 8.dp)
                .clip(RoundedCornerShape(topStart = 10.dp))
                .background(Color.Black.copy(alpha = 0.05f))
        )

        Box(
            modifier = Modifier
                .size(91.23.dp)
                .offset(x = (32).dp, y = 32.dp)
                .rotate(-9.25f)
                .clip(RoundedCornerShape(topStart = 10.dp))
                .background(Color.Black.copy(alpha = 0.05f))
        )


        Card(
            shape = cardShape,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    shadowElevation = 4.dp.toPx()
                    shape = customShape
                    clip = true
                }
                .background(Color.White),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Transparent)
                    .aspectRatio(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(BackgroundIconColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    code,
                    style = MaterialTheme.typography.bodyMedium,
                    color = SecondaryColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = status,
                    color = TextColor,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .background(statusColor)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
