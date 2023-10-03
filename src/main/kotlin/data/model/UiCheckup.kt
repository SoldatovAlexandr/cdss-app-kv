package data.model

class UiCheckup(
    var date: String,
    var fluorose: String,
    var levelOfHygiene: String,
    var tjpClicking: String,
    var tjpSymptoms: String,
    var tjpSoreness: String,
    var tjpLimitedMobility: String,
    var byteType: String,
    var erosion: String,
    var erosionCount: String,
    var trauma: String,
    var traumaCount: String,
    var predictedCpu: String,
    var type: String,

    val topTeeth: List<UITooth>,
    val downTeeth: List<UITooth>,

    val topPeriodontalTissues: List<UIPeriodontalTissues>,
    val downPeriodontalTissues: List<UIPeriodontalTissues>,

    val attachmentLoss: List<UIAttachmentLoss>,
    val enamelSpotting: List<UIEnamelSpotting>,
    val oralDamages: List<UIOralDamages>,
)
