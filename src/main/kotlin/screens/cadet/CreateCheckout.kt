package screens.cadet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.model.UiCadet
import ui.NormalText
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun CreateCheckout(
    cadet: UiCadet,
    viewModel: CadetViewModel,
    modifier: Modifier
) {

    Column {
        Icon(
            painterResource("img/ic_close.svg"), "", Modifier.align(Alignment.End).clickable {
                viewModel.setCreatedCheckout(false)
            }.padding(8.dp)
        )

        var date = LocalDateTime.now().toString()

        var tooth17 by remember { mutableStateOf("0") }
        var tooth16 by remember { mutableStateOf("0") }
        var tooth15 by remember { mutableStateOf("0") }
        var tooth14 by remember { mutableStateOf("0") }
        var tooth13 by remember { mutableStateOf("0") }
        var tooth12 by remember { mutableStateOf("0") }
        var tooth11 by remember { mutableStateOf("0") }

        var tooth27 by remember { mutableStateOf("0") }
        var tooth26 by remember { mutableStateOf("0") }
        var tooth25 by remember { mutableStateOf("0") }
        var tooth24 by remember { mutableStateOf("0") }
        var tooth23 by remember { mutableStateOf("0") }
        var tooth22 by remember { mutableStateOf("0") }
        var tooth21 by remember { mutableStateOf("0") }

        var tooth47 by remember { mutableStateOf("0") }
        var tooth46 by remember { mutableStateOf("0") }
        var tooth45 by remember { mutableStateOf("0") }
        var tooth44 by remember { mutableStateOf("0") }
        var tooth43 by remember { mutableStateOf("0") }
        var tooth42 by remember { mutableStateOf("0") }
        var tooth41 by remember { mutableStateOf("0") }

        var tooth37 by remember { mutableStateOf("0") }
        var tooth36 by remember { mutableStateOf("0") }
        var tooth35 by remember { mutableStateOf("0") }
        var tooth34 by remember { mutableStateOf("0") }
        var tooth33 by remember { mutableStateOf("0") }
        var tooth32 by remember { mutableStateOf("0") }
        var tooth31 by remember { mutableStateOf("0") }

        var fluorose by remember { mutableStateOf("0") }
        var levelOfHygiene by remember { mutableStateOf("0") }

        var cpu by remember { mutableStateOf("0") }

        Row {
            NormalText("Осмотр ${LocalDate.now()}", Modifier.weight(1f))
            NormalText("Индекс КПУ ${cpu}", Modifier.weight(1f))
        }

        LazyColumn(modifier.fillMaxWidth()) {

            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
                Row {
                    Column {
                        NormalText("Состояние зубов", Modifier.padding(top = 16.dp))

                        Row {
                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("17", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth17,
                                    onValueChange = { tooth17 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("16", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth16,
                                    onValueChange = { tooth16 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("15", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth15,
                                    onValueChange = { tooth15 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("14", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth14,
                                    onValueChange = { tooth14 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("13", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth13,
                                    onValueChange = { tooth13 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("12", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth12,
                                    onValueChange = { tooth12 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("11", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth11,
                                    onValueChange = { tooth11 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("21", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth21,
                                    onValueChange = { tooth21 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("22", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth22,
                                    onValueChange = { tooth22 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("23", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth23,
                                    onValueChange = { tooth23 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("24", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth24,
                                    onValueChange = { tooth24 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("25", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth25,
                                    onValueChange = { tooth25 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("26", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth26,
                                    onValueChange = { tooth26 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                NormalText("27", Modifier.padding(top = 16.dp))
                                TextField(
                                    tooth27,
                                    onValueChange = { tooth27 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                            }
                        }

                        Row {
                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth47,
                                    onValueChange = { tooth47 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("47", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth46,
                                    onValueChange = { tooth46 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("46", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth45,
                                    onValueChange = { tooth45 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("45", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth44,
                                    onValueChange = { tooth44 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("44", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth43,
                                    onValueChange = { tooth43 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("43", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth42,
                                    onValueChange = { tooth42 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("42", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth41,
                                    onValueChange = { tooth41 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("41", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth31,
                                    onValueChange = { tooth31 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("31", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth32,
                                    onValueChange = { tooth32 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("32", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth33,
                                    onValueChange = { tooth33 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("33", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth34,
                                    onValueChange = { tooth34 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("34", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth35,
                                    onValueChange = { tooth35 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("35", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth36,
                                    onValueChange = { tooth36 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("36", Modifier.padding(top = 16.dp))
                            }

                            Column(Modifier.padding(start = 8.dp)) {
                                TextField(
                                    tooth37,
                                    onValueChange = { tooth37 = it },
                                    Modifier.padding(vertical = 8.dp).size(50.dp)
                                )
                                NormalText("37", Modifier.padding(top = 16.dp))
                            }
                        }
                    }
                    Column {
                        /*NormalText(
                            """
                            Постоянные (Временные):
                            0 (A) - Интактный 
                            1 (В) - Кариес
                            2 (С) - Пломба, с кариесом
                            3 (D) - Пломба, без кариеса 
                            4 (E) - Удаление из-за осложнений кариеса 
                            5 - Удаление по другим причинам 
                            6 (F) - Герметизированная фиссура 
                            7 (G) - Опорный зуб мостовидн. протез/коронка, винир 
                            8 - Не прорезавшийся зуб 
                            9 - Не регистрируется
                        """.trimMargin(), Modifier.padding(top = 16.dp)
                        )*/
                    }
                }
            }

            item {
                Box(Modifier.fillMaxWidth().height(1.dp).background(Color.LightGray))
                Row {
                    Column {
                        NormalText("Флюороз эмали", Modifier.padding(top = 16.dp))
                        TextField(
                            fluorose,
                            onValueChange = { fluorose = it },
                            Modifier.padding(vertical = 8.dp).size(50.dp)
                        )
                    }

                    Column(Modifier.padding(start = 100.dp)) {
                        NormalText("Уровень гигиены ПР(УПС)", Modifier.padding(top = 16.dp))
                        TextField(
                            levelOfHygiene,
                            onValueChange = { levelOfHygiene = it },
                            Modifier.padding(vertical = 8.dp).size(50.dp)
                        )
                    }
                }
            }
        }

        /*Button(onClick = {

        }, enabled = true) {
            Text("Создать")
        }*/
    }
}
