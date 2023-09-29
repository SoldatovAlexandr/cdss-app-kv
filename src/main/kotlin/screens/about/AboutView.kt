package screens.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.BigText

@Composable
fun AboutView(modifier: Modifier = Modifier, onClose: () -> Unit) {
    Column(modifier) {
        Icon(
            painterResource("img/ic_close.svg"),
            "",
            Modifier.align(Alignment.End).clickable {
                onClose()
            }.padding(8.dp)
        )
        BigText("Программа по предсказательному моделированию", Modifier.padding(8.dp))
    }
}
