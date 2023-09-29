package predictor

import ai.catboost.CatBoostModel

class CatBoostPredictor : Predictor {

    override fun predict(): Float {

        CatBoostModel.loadModel("model.bin").use {
            val predictions = it.predict(FloatArray(33) { 0.0f }, it.featureNames)
            print(predictions)
            return 0.0f
        }

        //todo что он хочет от меня?
        //return 0.0f
    }
}
