package predictor

import ai.catboost.CatBoostModel
import java.io.File

object CatBoostPredictor : Predictor {

    override fun predict(checkupFeatures: CheckupFeatures): Double {
        val file = File("model.cbm")

        CatBoostModel.loadModel(file.absolutePath).use {
            val predictions = it.predict(prepare(checkupFeatures), it.featureNames)
            return predictions.get(0, 0)
        }
    }
}

fun prepare(checkupFeatures: CheckupFeatures): FloatArray {
    //'cpu', 'health_group', 'sum_hygiene_index'
    return floatArrayOf(
        checkupFeatures.cpu.toFloat(),
        checkupFeatures.healthGroup.toFloat(),
        checkupFeatures.sumHygieneIndex.toFloat()
        /*checkupFeatures.age.toFloat(),
        0.0f, //todo
        0.0f, //todo
        0.0f, //todo
        0.0f, //todo
        checkupFeatures.healthGroup.toFloat(),
        checkupFeatures.sumHygieneIndex.toFloat(),
        checkupFeatures.toothErosion.toFloat(),
        checkupFeatures.toothErosionCount.toFloat(),
        checkupFeatures.dentalTrauma.toFloat(),
        checkupFeatures.dentalTraumaCount.toFloat(),
        checkupFeatures.numIntact.toFloat(),
        checkupFeatures.numCaries.toFloat(),
        checkupFeatures.numFillingWithCaries.toFloat(),
        checkupFeatures.numFillingWithoutCaries.toFloat(),
        checkupFeatures.numRemovalCaries.toFloat(),
        checkupFeatures.numRemovalOtherReason.toFloat(),
        checkupFeatures.numSealedFissure.toFloat(),
        checkupFeatures.numVeneer.toFloat(),
        checkupFeatures.numImpacted.toFloat(),
        checkupFeatures.numNotRegistered.toFloat(),
        checkupFeatures.numIntactTemp.toFloat(),
        checkupFeatures.numCariesTemp.toFloat(),
        checkupFeatures.numFillingWithCariesTemp.toFloat(),
        checkupFeatures.numFillingWithoutCariesTemp.toFloat(),
        checkupFeatures.numRemovalCariesTemp.toFloat(),
        checkupFeatures.numSealedFissureTemp.toFloat(),
        checkupFeatures.numVeneerTemp.toFloat(),
        checkupFeatures.jointSymptoms.toFloat(),
        checkupFeatures.jointClicking.toFloat(),
        checkupFeatures.jointSoreness.toFloat(),
        checkupFeatures.jointLimitation.toFloat(),
        checkupFeatures.cpu.toFloat()*/
    )
}
