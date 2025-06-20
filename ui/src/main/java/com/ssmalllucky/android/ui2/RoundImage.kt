package com.ssmalllucky.android.ui2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * @ClassName RoundImage
 * @Author ssmalllucky
 * @Date 2025/6/20
 * @Description 带圆角的 Image 视图
 */
@Composable
fun RoundImage(
    modifier: Modifier = Modifier,
    imageBitmap: ImageBitmap,
    type: ImageType = ImageType.Oval,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    cornerRadius: Dp = 10.dp,
    leftTopCorner: Dp = 0.dp,
    rightTopCorner: Dp = 0.dp,
    rightBottomCorner: Dp = 0.dp,
    leftBottomCorner: Dp = 0.dp
) {
    val shape: Shape = when (type) {
        ImageType.Circle -> CircleShape
        ImageType.Round -> RoundedCornerShape(cornerRadius)
        ImageType.Oval -> CustomOvalShape()
    }

    val customShape = if (type == ImageType.Round &&
        (leftTopCorner != 0.dp || rightTopCorner != 0.dp || leftBottomCorner != 0.dp || rightBottomCorner != 0.dp)
    ) {
        RoundedCornerShape(
            topStart = leftTopCorner,
            topEnd = rightTopCorner,
            bottomEnd = rightBottomCorner,
            bottomStart = leftBottomCorner
        )
    } else shape

    Box(
        modifier = modifier
            .clip(customShape)
            .border(borderWidth, borderColor, customShape)
            .background(Color.Transparent)
    ) {
        Image(
            bitmap = imageBitmap,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * 图片类型
 */
enum class ImageType {
    Circle, Round, Oval
}

/**
 * 自定义的椭圆形状（OvalShape）
 */
fun CustomOvalShape(): Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline = Outline.Generic(Path().apply {
        addOval(Rect(Offset.Zero, size))
    })
}
