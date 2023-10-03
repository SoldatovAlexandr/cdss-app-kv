package screens.cadet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cpu.CpuCalculator
import data.model.*
import predictor.CatBoostPredictor
import predictor.CheckupFeatures
import ui.NormalText
import ui.dropdown.custom.*
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun CreateCheckout(
    cadet: UiCadet,
    viewModel: CadetViewModel,
    modifier: Modifier,
) {

    Column {
        Icon(
            painterResource("img/ic_close.svg"), "", Modifier.align(Alignment.End).clickable {
                viewModel.setCreatedCheckout(false)
            }.padding(8.dp)
        )
        val lastCheckup by viewModel.lastCheckup.collectAsState()

        val cpuCalculator = CpuCalculator
        val cpuPredictor = CatBoostPredictor

        val date = LocalDateTime.now()
        val checkup by remember { mutableStateOf(lastCheckup.data!!) }
        var cpu by remember { mutableStateOf("0") }

        fun predict() {
            val calculatedCpuIndex = cpuCalculator.calculate(concatenate(checkup.topTeeth, checkup.downTeeth))
            val checkupFeatures = CheckupFeatures(
                //cadet.dateOfBirthday
                10, //todo
                cadet.ethnicGroup,
                cadet.placeOfBirthday,
                cadet.previousPlaceOfLiving,
                cadet.byteType,
                cadet.healthGroup.toInt(),
                if (checkup.levelOfHygiene.isBlank()) 0 else checkup.levelOfHygiene.toInt(),
                if (checkup.erosion.isBlank()) 0 else checkup.erosion.toInt(),
                if (checkup.erosionCount.isBlank()) 0 else checkup.erosionCount.toInt(),
                if (checkup.trauma.isBlank()) 0 else checkup.trauma.toInt(),
                if (checkup.traumaCount.isBlank()) 0 else checkup.traumaCount.toInt(),
                calculatedCpuIndex.intact,
                calculatedCpuIndex.caries,
                calculatedCpuIndex.fillingWithCaries,
                calculatedCpuIndex.fillingWithoutCaries,
                calculatedCpuIndex.removalCaries,
                calculatedCpuIndex.removalOtherReason,
                calculatedCpuIndex.sealedFissure,
                calculatedCpuIndex.veneer,
                calculatedCpuIndex.impacted,
                calculatedCpuIndex.notRegistered,
                calculatedCpuIndex.intactTemp,
                calculatedCpuIndex.cariesTemp,
                calculatedCpuIndex.fillingWithCariesTemp,
                calculatedCpuIndex.fillingWithoutCariesTemp,
                calculatedCpuIndex.removalCariesTemp,
                calculatedCpuIndex.sealedFissureTemp,
                calculatedCpuIndex.veneerTemp,
                if (checkup.tjpSymptoms.isBlank()) 0 else checkup.tjpSymptoms.toInt(),
                if (checkup.tjpClicking.isBlank()) 0 else checkup.tjpClicking.toInt(),
                if (checkup.tjpSoreness.isBlank()) 0 else checkup.tjpSoreness.toInt(),
                if (checkup.tjpLimitedMobility.isBlank()) 0 else checkup.tjpLimitedMobility.toInt(),
                calculatedCpuIndex.getIndex()
            )
            checkup.predictedCpu = "%.3f".format(cpuPredictor.predict(checkupFeatures))
        }

        Row {
            NormalText("Осмотр ${LocalDate.now()}", Modifier.weight(1f))
            NormalText("Индекс КПУ ${cpu}", Modifier.weight(1f))
            NormalText("Увеличение индекса КПУ через год:${checkup.predictedCpu}", Modifier.weight(1f))
        }

        LazyColumn(modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 20.dp)) {

            item {
                Row {
                    Column {
                        NormalText("Состояние зубов", Modifier.padding(top = 16.dp))

                        Row {
                            checkup.topTeeth.forEach { tooth ->
                                ToothDropDownMenu({
                                    tooth.value = it
                                    cpu =
                                        cpuCalculator.calculate(concatenate(checkup.topTeeth, checkup.downTeeth))
                                            .getIndex().toString()
                                    predict()
                                }, tooth.number, tooth.value)
                            }
                        }
                        Row {
                            checkup.downTeeth.forEach { tooth ->
                                ToothDropDownMenu({
                                    tooth.value = it
                                    cpu =
                                        cpuCalculator.calculate(concatenate(checkup.topTeeth, checkup.downTeeth))
                                            .getIndex().toString()
                                    predict()
                                }, tooth.number, tooth.value)
                            }
                        }
                    }
                }
            }

            item {
                Row(Modifier.padding(top = 20.dp)) {
                    Column {
                        FluoroseDropDownMenu({
                            checkup.fluorose = it
                            predict()
                        }, "Флюороз эмали", checkup.fluorose)
                    }
                    Column(Modifier.padding(start = 100.dp)) {
                        LevelOfHygieneDropDownMenu({
                            checkup.levelOfHygiene = it
                            predict()
                        }, "Уровень гигиены ПР(УПС)", checkup.levelOfHygiene)
                    }
                    Column(Modifier.padding(start = 100.dp)) {
                        ByteTypeDropDownMenu({
                            checkup.byteType = it
                            predict()
                        }, "Прикус", checkup.byteType)
                    }
                }
            }

            item {
                Column {
                    NormalText("Состояние тканей парадонта", Modifier.padding(top = 16.dp))

                    Row {
                        checkup.topPeriodontalTissues.forEach { tissues ->
                            PeriodontalTissuesDropDownMenu(
                                {
                                    tissues.value = it
                                    predict()
                                },
                                tissues.number,
                                tissues.value
                            )
                        }
                    }
                    Row {
                        checkup.downPeriodontalTissues.forEach { tissues ->
                            PeriodontalTissuesDropDownMenu({
                                tissues.value = it
                                predict()
                            }, tissues.number, tissues.value)
                        }
                    }
                }
            }

            item {
                Column {
                    NormalText("Пятнистость эмали/гипоплазия", Modifier.padding(top = 16.dp))

                    Row {
                        checkup.enamelSpotting.forEach { tooth ->
                            EnamelSpottingDropDownMenu({
                                tooth.value = it
                                predict()
                            }, tooth.number, tooth.value)
                        }
                    }
                }
            }
            item {
                NormalText("Потеря прикрепления*", Modifier.padding(top = 16.dp))
                NormalText("*(не регистрируется у лиц моложе 15 лет)", Modifier.padding(top = 10.dp))

                Row {
                    checkup.attachmentLoss.forEach { tooth ->
                        AttachmentLossDropDownMenu({
                            tooth.value = it
                            predict()
                        }, tooth.number, tooth.value)
                    }
                }
            }

            item {
                NormalText("Оценка височнонижечелюстного сустава", Modifier.padding(top = 16.dp))

                Row {
                    Column(Modifier.weight(1f)) {
                        TemporomandibularJointDropDownMenu({
                            checkup.tjpClicking = it
                            predict()
                        }, "Щелканье", checkup.tjpClicking)
                    }
                    Column(Modifier.weight(1f)) {
                        TemporomandibularJointDropDownMenu({
                            checkup.tjpClicking = it
                            predict()
                        }, "Симптомы", checkup.tjpClicking)
                    }
                    Column(Modifier.weight(1f)) {
                        TemporomandibularJointDropDownMenu({
                            checkup.tjpSoreness = it
                            predict()
                        }, "Болезненность", checkup.tjpSoreness)
                    }
                    Column(Modifier.weight(1f)) {
                        TemporomandibularJointDropDownMenu(
                            {
                                checkup.tjpLimitedMobility = it
                                predict()
                            },
                            "Ограничение подвижности челюсти",
                            checkup.tjpLimitedMobility
                        )
                    }
                }
            }
            item {
                Row {
                    Column {
                        NormalText("Эрозия зубов", Modifier.padding(top = 16.dp))

                        ErosionDropDownMenu({
                            checkup.erosion = it
                            predict()
                        }, "Состояние", checkup.erosion)

                        OutlinedTextField(
                            value = checkup.erosionCount,
                            onValueChange = { value ->
                                checkup.erosionCount = value.filter {
                                    it.isDigit()
                                }
                                predict()
                            },
                            label = { Text("Количество пораженных зубов") }
                        )
                    }

                    Column(Modifier.padding(start = 100.dp)) {
                        NormalText("Травма зубов", Modifier.padding(top = 16.dp))

                        TraumaDropDownMenu({
                            checkup.trauma = it
                            predict()
                        }, "Состояние", checkup.trauma)

                        OutlinedTextField(
                            value = checkup.traumaCount,
                            onValueChange = { value ->
                                checkup.traumaCount = value.filter { it.isDigit() }
                                predict()
                            },
                            label = { Text("Количество пораженных зубов") }
                        )
                    }
                }
            }

            item {
                Row {
                    Column {
                        NormalText("Состояние", Modifier.padding(top = 16.dp))

                        checkup.oralDamages.forEach { value ->
                            OralDamageStateDropDownMenu(
                                {
                                    value.value = it
                                    predict()
                                },
                                "",
                                value.value
                            )
                        }
                    }

                    Column(Modifier.padding(start = 100.dp)) {
                        NormalText("Локализация", Modifier.padding(top = 16.dp))

                        checkup.oralDamages.forEach { value ->
                            OralDamageLocaleDropDownMenu(
                                {
                                    value.number = it
                                    predict()
                                },
                                "",
                                value.number
                            )
                        }
                    }
                }
            }

            item {
                Button(onClick = {
                    viewModel.createCheckout(cadet.id, checkup, date)
                }, enabled = true) {
                    Text("Сохранить")
                }
            }
        }
    }
}

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}
