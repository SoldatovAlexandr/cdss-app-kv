package screens.cadet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cpu.CpuCalculator
import data.model.UiCadet
import predictor.CatBoostPredictor
import predictor.CheckupFeatures
import ui.BigText
import ui.NormalText
import ui.dropdown.DropDownItem
import ui.dropdown.DropDownMenu
import ui.dropdown.custom.*
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.pow

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

        //TODO это кромешный ужас
        val cpuLastValue =
            cpuCalculator.calculate(concatenate(lastCheckup.data!!.topTeeth, lastCheckup.data!!.downTeeth))
                .getIndexCPU()
        val date = LocalDateTime.now()
        val checkup by remember { mutableStateOf(lastCheckup.data!!) }
        var cpu by remember {
            mutableStateOf(
                cpuCalculator.calculateForUI(
                    concatenate(
                        checkup.topTeeth, checkup.downTeeth
                    ), cadet.dateOfBirthday
                )
            )
        }

        var lprDetails by remember {
            mutableStateOf(
                ""
            )
        }

        var height by remember {
            mutableStateOf(
                checkup.height
            )
        }

        var weight by remember {
            mutableStateOf(
                checkup.weight
            )
        }

        var erosionCount by remember {
            mutableStateOf(
                checkup.erosionCount
            )
        }

        var traumaCount by remember {
            mutableStateOf(
                checkup.traumaCount
            )
        }

        var ohisIndex by remember {
            mutableStateOf(
                ""
            )
        }

        var imt by remember {
            mutableStateOf(
                calculateIMT(height, weight)
            )
        }

        fun predict() {
            val calculatedCpuIndex = cpuCalculator.calculate(concatenate(checkup.topTeeth, checkup.downTeeth))
            val checkupFeatures = CheckupFeatures(
                //cadet.dateOfBirthday
                10, //todo
                cadet.ethnicGroup,
                cadet.placeOfBirthday,
                cadet.previousPlaceOfLiving,
                "",/*cadet.byteType,*/
                0,/*cadet.healthGroup.toInt(),*/
                if (checkup.levelOfHygiene.isBlank()) 0 else checkup.levelOfHygiene.toInt(),
                if (checkup.erosion.isBlank()) 0 else checkup.erosion.toInt(),
                if (erosionCount.isBlank()) 0 else erosionCount.toInt(),
                if (checkup.trauma.isBlank()) 0 else checkup.trauma.toInt(),
                if (traumaCount.isBlank()) 0 else traumaCount.toInt(),
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
                calculatedCpuIndex.getIndexCPUcp()
            )
            checkup.predictedCpu = "%.1f".format(cpuPredictor.predict(checkupFeatures))
        }

        fun calculateOhisIndex() {
            var sum = 0.0
            for (tooth in checkup.ohise) {
                sum += tooth.plaque.toInt();
                sum += tooth.stone.toInt();
            }
            val index = (sum / (checkup.ohise.size * 2));
            ohisIndex = "%.1f".format(index)

            checkup.levelOfHygiene = if (index <= 0.6) {
                "0"
            } else if (index <= 1.6) {
                "1"
            } else if (index <= 2.5) {
                "2"
            } else {
                "3"
            }
        }

        predict()
        calculateOhisIndex()

        Row(Modifier.height(50.dp)) {
            Column(Modifier.weight(1f)) {
                NormalText("Осмотр ${LocalDate.now()}")
            }
            Column(Modifier.weight(1f)) {
                BigText("Увеличение индекса КПУ через год: ${checkup.predictedCpu}")
            }
        }

        LazyColumn(modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)) {

            item {
                BigText("Общие данные", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                            Column(Modifier.weight(1f)) {
                                DropDownMenu(
                                    listOf(
                                        DropDownItem("I", "1"), DropDownItem("II", "2"), DropDownItem("III", "3")
                                    ),
                                    { v -> checkup.healthGroup = v },
                                    "Выберите группу здоровья кадета",
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }

                            Column(Modifier.weight(1f)) {
                                OutlinedTextField(value = height, onValueChange = { value ->
                                    height = value.filter {
                                        it.isDigit()
                                    }
                                    imt = calculateIMT(height, weight)
                                }, label = { Text("Рост, см") })
                            }

                            Column(Modifier.weight(1f)) {
                                OutlinedTextField(value = weight, onValueChange = { value ->
                                    weight = value.filter {
                                        it.isDigit()
                                    }
                                    imt = calculateIMT(height, weight)
                                }, label = { Text("Вес, кг") })
                            }
                        }
                        Row(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                            NormalText("Индекс массы тела: $imt")
                        }
                    }
                }
            }

            item {
                BigText("Распространенность и интенсивность кариеса зубов", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Row(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                            Column {
                                Row {
                                    checkup.topTeeth.forEach { tooth ->
                                        ToothDropDownMenu({
                                            tooth.value = it
                                            cpu = cpuCalculator.calculateForUI(
                                                concatenate(checkup.topTeeth, checkup.downTeeth), cadet.dateOfBirthday
                                            )
                                            predict()
                                        }, tooth.number, tooth.value, tooth.number.substring(2).toInt() < 6)
                                    }
                                }
                                Row {
                                    checkup.downTeeth.forEach { tooth ->
                                        ToothDropDownMenu({
                                            tooth.value = it
                                            cpu = cpuCalculator.calculateForUI(
                                                concatenate(checkup.topTeeth, checkup.downTeeth), cadet.dateOfBirthday
                                            )
                                            predict()
                                        }, tooth.number, tooth.value, tooth.number.substring(2).toInt() < 6)
                                    }
                            }
                        }
                    }
                        Row(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                            Column(Modifier.weight(1f)) {
                                NormalText(cpu.format())
                            }
                            Column(Modifier.weight(1f)) {
                                NormalText(
                                    "Степень активности кариеса: ${cpu.formatCariesActivity()}"
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                NormalText("Абсолютный прирост КПУ за год: ${cpu.getDeltaCPU(lastCpuValue = cpuLastValue)}")
                            }
                        }
                    }
                }
            }

            item {
                BigText(
                    "Распространенность и интенсивность некариозных поражений твердых тканей зубов",
                    Modifier.padding(top = 30.dp)
                )

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                        Row {
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

                            Column(Modifier.padding(start = 100.dp)) {
                                NormalText("Флюороз эмали", Modifier.padding(top = 16.dp))

                                FluoroseDropDownMenu({
                                    checkup.fluorose = it
                                    predict()
                                }, "", checkup.fluorose)
                            }
                        }

                        Row(Modifier.padding(top = 16.dp)) {
                            Column {
                                NormalText("Эрозия зубов", Modifier.padding(top = 16.dp))

                                ErosionDropDownMenu({
                                    checkup.erosion = it
                                    predict()
                                }, "Состояние", checkup.erosion)

                                OutlinedTextField(value = erosionCount, onValueChange = { value ->
                                    erosionCount = value.filter {
                                        it.isDigit()
                                    }
                                    predict()
                                }, label = { Text("Количество пораженных зубов") })
                            }

                            Column(Modifier.padding(start = 100.dp)) {
                                NormalText("Травма зубов", Modifier.padding(top = 16.dp))

                                TraumaDropDownMenu({
                                    checkup.trauma = it
                                    predict()
                                }, "Состояние", checkup.trauma)

                                OutlinedTextField(value = traumaCount, onValueChange = { value ->
                                    traumaCount = value.filter { it.isDigit() }
                                    predict()
                                }, label = { Text("Количество пораженных зубов") })
                            }
                        }
                    }
                }
            }

            item {
                BigText("Состояние гигиены полости рта", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                        Row {
                            Column(Modifier.weight(1f)) {
                                NormalText("Оценка зубного налёта", Modifier.padding(top = 16.dp))

                                Row {
                                    checkup.ohise.forEach { value ->
                                        Column {
                                            OHISPlaqueIndexDropDownMenu(
                                                {
                                                    value.plaque = it
                                                    calculateOhisIndex()
                                                }, value.number, value.plaque
                                            )
                                        }
                                    }
                                }
                            }
                            Column(Modifier.weight(1f)) {
                                NormalText("Оценка зубного камня", Modifier.padding(top = 16.dp))

                                Row {
                                    checkup.ohise.forEach { value ->
                                        Column {
                                            OHISStoneIndexDropDownMenu(
                                                {
                                                    value.stone = it
                                                    calculateOhisIndex()
                                                }, value.number, value.stone
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        NormalText("Гигиенический индекс (OHI-S): $ohisIndex ", Modifier.padding(top = 16.dp))
                    }
                }
            }

            item {
                BigText("Распространенность признаков поражения тканей парадонта", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                        Row {
                            checkup.topPeriodontalTissues.forEach { tissues ->
                                PeriodontalTissuesDropDownMenu(
                                    {
                                        tissues.value = it
                                        predict()
                                    }, tissues.number, tissues.value
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
            }

            item {
                BigText("Распространенность заболеваний слизистой оболочки полости рта", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                        Row(Modifier.padding(top = 16.dp)) {
                            Column {
                                NormalText("Состояние", Modifier.padding(top = 16.dp))

                                checkup.oralDamages.forEach { value ->
                                    OralDamageStateDropDownMenu(
                                        {
                                            value.value = it
                                            predict()
                                        }, "", value.value
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
                                        }, "", value.number
                                    )
                                }
                            }
                        }
                    }
                }

            }

            item {
                BigText("Потеря прикрепления*", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {
                        Row {
                            checkup.attachmentLoss.forEach { tooth ->
                                AttachmentLossDropDownMenu({
                                    tooth.value = it
                                    predict()
                                }, tooth.number, tooth.value)
                            }
                        }
                        NormalText("*(не регистрируется у лиц моложе 15 лет)", Modifier.padding(top = 10.dp))
                    }
                }
            }

            item {
                BigText("Оценка состояния прикуса и зубочелюстных аномалий", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {

                        ByteTypeDropDownMenu(
                            { checkup.byteType = it }, checkup.byteType, Modifier, cpu.getAgeByteType()
                        )
                    }
                }
            }

            item {
                BigText("Оценка височнонижечелюстного сустава", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {

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
                                    }, "Ограничение подвижности челюсти", checkup.tjpLimitedMobility
                                )
                            }
                        }
                    }
                }
            }

            item {
                BigText("Нуждаемость в ЛПР", Modifier.padding(top = 30.dp))

                Card(
                    border = BorderStroke(2.dp, Color.Gray), modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)) {

                        Row {
                            LPRneedDropDownMenu(
                                {
                                    checkup.lprNeed = it
                                }, "", "Выберите значение"
                            )
                        }
                        Row(Modifier.padding(top = 20.dp)) {
                            OutlinedTextField(
                                value = lprDetails,
                                onValueChange = { value ->
                                    run {
                                        if (value.length < 255) {
                                            lprDetails = value
                                        }
                                    }
                                },
                                Modifier.padding(vertical = 8.dp).weight(fill = true, weight = 1f),
                                label = { Text("Планируемые мероприятия на следующий прием") },
                            )
                        }
                    }
                }

            }

            item {
                Button(onClick = {
                    checkup.lprDetails = lprDetails
                    checkup.height = height
                    checkup.weight = weight
                    checkup.erosionCount = erosionCount
                    checkup.traumaCount = traumaCount
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

fun calculateIMT(heightS: String, weightS: String): String {
    val height = if (heightS.isNotEmpty()) {
        heightS.toInt()
    } else {
        0
    }
    val weight = if (weightS.isNotEmpty()) {
        weightS.toInt()
    } else {
        0
    }
    val imt = weight / (height / 100.0).pow(2.0)
    return "%.1f".format(imt)
}
