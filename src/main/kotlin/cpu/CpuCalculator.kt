package cpu

import data.model.UITooth

object CpuCalculator {

    fun calculate(teeth: List<UITooth>): CPU {
        return CPU(teeth);
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

    //КПУ=К(1,2)+П(3,6)+У(4,5,7)+кп=к(B,C)+п(D,F)
    fun getIndex(): Int {
        return caries + fillingWithCaries +
                (fillingWithoutCaries + sealedFissure) +
                (removalCaries + removalOtherReason + veneer) +
                (cariesTemp + fillingWithCariesTemp) +
                (fillingWithoutCariesTemp + sealedFissureTemp)
    }
}
