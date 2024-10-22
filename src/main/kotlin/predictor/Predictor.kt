package predictor

interface Predictor {

    fun predict(checkupFeatures: CheckupFeatures): Double

}