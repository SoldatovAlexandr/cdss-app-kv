package cpu

import data.model.UITooth
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

object CpuCalculator {

    fun calculate(teeth: List<UITooth>): CPU {
        return CPU(teeth)
    }

    fun calculateForUI(teeth: List<UITooth>, dateOfBirth: String): CPUcpUI {
        return CPUcpUI(CPU(teeth), dateOfBirth)
    }
}

class CPUcpUI(
    cpu: CPU,
    dateOfBirth: String
) {
    private var total = "0"
    private var C = "0"
    private var P = "0"
    private var U = "0"
    private var c = "0"
    private var p = "0"
    private var hasTemp = false
    private var age = 0

    private var cpu = 0
    private var cpucp = 0
    private var ageByteType = ""

    init {
        total = cpu.getIndexCPUcp().toString()
        C = (cpu.caries + cpu.fillingWithCaries).toString()
        P = (cpu.fillingWithoutCaries + cpu.sealedFissure).toString()
        U = (cpu.removalCaries + cpu.removalOtherReason + cpu.veneer).toString()
        c = (cpu.cariesTemp + cpu.fillingWithCariesTemp).toString()
        p = (cpu.fillingWithoutCariesTemp + cpu.sealedFissureTemp).toString()
        hasTemp = cpu.hasTemp()
        age = calculateAge(dateOfBirth)
        this.cpu = cpu.getIndexCPU()
        this.cpucp = cpu.getIndexCPUcp()
        ageByteType = if(cpu.isAllTemp()){
            "Временный"
        } else if(cpu.hasTemp()){
            "Смешанный"
        } else {
            "Постоянный"
        }
    }

    fun getAgeByteType(): String {
        return ageByteType
    }

    fun getDeltaCPU(lastCpuValue: Int): String {
        return (cpu - lastCpuValue).toString();
    }

    fun format(): String {
        return if (!hasTemp) {
            "Индекс КПУ = $total (К=${C}; П=${P}; У=${U})";
        } else {
            "КПУ+кп = $total (К=${C}; П=${P}; У=${U}) + (к=${c}; п=${p}) "
        }
    }

    fun formatCariesActivity(): String {
        val first = "1 степень (компенсированная форма)"
        val second = "2 степень (субкомпенсированная форма)"
        val third = "3 степень (декомпенсированная форма)"

        return if (age < 11) {
            if (cpucp < 6) {
                first
            } else if (cpucp < 8) {
                second
            } else {
                third
            }
        } else if (age < 15) {
            if (cpucp < 5) {
                first
            } else if (cpucp < 8) {
                second
            } else {
                third
            }
        } else {
            if (cpu < 7) {
                first
            } else if (cpu < 9) {
                second
            } else {
                third
            }
        }
    }


    private fun calculateAge(dateOfBirth: String): Int {
        val localDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        return Period.between(LocalDate.now(), localDate).years
    }
}

class CPU(
    teeth: List<UITooth>
) {

    var intact = 0
    var caries = 0
    var fillingWithCaries = 0
    var fillingWithoutCaries = 0
    var removalCaries = 0
    var removalOtherReason = 0
    var sealedFissure = 0
    var veneer = 0
    var impacted = 0
    var notRegistered = 0

    var intactTemp = 0
    var cariesTemp = 0
    var fillingWithCariesTemp = 0
    var fillingWithoutCariesTemp = 0
    var removalCariesTemp = 0
    var sealedFissureTemp = 0
    var veneerTemp = 0

    fun isAllTemp(): Boolean {
        val sum = intact + caries + fillingWithCaries + fillingWithoutCaries + removalCaries +
                removalOtherReason + sealedFissure + veneer + impacted + notRegistered
        return sum == 0
    }

    init {
        for (tooth in teeth) {
            when (tooth.value) {
                "0" -> {
                    intact++
                }

                "1" -> {
                    caries++
                }

                "2" -> {
                    fillingWithCaries++
                }

                "3" -> {
                    fillingWithoutCaries++
                }

                "4" -> {
                    removalCaries++
                }

                "5" -> {
                    removalOtherReason++
                }

                "6" -> {
                    sealedFissure++
                }

                "7" -> {
                    veneer++
                }

                "8" -> {
                    impacted++
                }

                "9" -> {
                    notRegistered++
                }

                "А" -> {
                    intactTemp++
                }

                "B" -> {
                    cariesTemp++
                }

                "C" -> {
                    fillingWithCariesTemp++
                }

                "D" -> {
                    fillingWithoutCariesTemp++
                }

                "E" -> {
                    removalCariesTemp++
                }

                "F" -> {
                    sealedFissureTemp++
                }

                "G" -> {
                    veneerTemp++
                }

            }
        }
    }

    fun hasTemp(): Boolean {
        return (cariesTemp + fillingWithCariesTemp) + (fillingWithoutCariesTemp + sealedFissureTemp) + intactTemp > 0
    }

    //КПУ=К(1,2)+П(3,6)+У(4,5,7)+кп=к(B,C)+п(D,F)
    fun getIndexCPUcp(): Int {
        return caries + fillingWithCaries +
                (fillingWithoutCaries + sealedFissure) +
                (removalCaries + removalOtherReason + veneer) +
                (cariesTemp + fillingWithCariesTemp) +
                (fillingWithoutCariesTemp + sealedFissureTemp)
    }

    //КПУ=К(1,2)+П(3,6)+У(4,5,7)+кп=к(B,C)+п(D,F)
    fun getIndexCPU(): Int {
        return caries + fillingWithCaries +
                (fillingWithoutCaries + sealedFissure) +
                (removalCaries + removalOtherReason + veneer)
    }
}
