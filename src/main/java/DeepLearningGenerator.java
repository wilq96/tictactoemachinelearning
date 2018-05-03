import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.*;
import java.util.List;

public class DeepLearningGenerator {

    public DataSet generateDataSetByOptions(int batchSize, int classesCount, int featuresCount, String filePath) {

        DataSet allData = null;

        try (RecordReader recordReader = new CSVRecordReader(0, ',')) {
            recordReader.initialize(
                    new FileSplit(
                            new ClassPathResource(filePath).getFile()));

            DataSetIterator iterator = new RecordReaderDataSetIterator(
                    recordReader, batchSize, featuresCount, classesCount);
            allData = iterator.next();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allData;
    }

    public Evaluation getEvaluationData(MultiLayerNetwork model, DataSet testData, List<DataSet> trainingData) {
        int classesCount = 2;

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                Evaluating training and test dataset...");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        for (DataSet dataSet : trainingData) {
            model.fit(dataSet);
        }
        model.init();

        for (DataSet trainingDataSet : trainingData) {
            model.fit(trainingDataSet);
        }

        INDArray output = model.output(testData.getFeatureMatrix());

        Evaluation eval = new Evaluation(classesCount);

        eval.eval(testData.getLabels(), output);
        System.out.println(eval.stats());
        return eval;
    }

    public void doSummaryCalculation(List<Evaluation> evaluations) {
        double accuracy = 0;
        double precision = 0;
        double recall = 0;
        double f1Score = 0;
        double numberOfEvaluations = 300;

        for (Evaluation evaluation : evaluations) {
            accuracy += evaluation.accuracy();
            precision += evaluation.precision();
            recall += evaluation.recall();
            f1Score += evaluation.f1();
        }
        System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                      ----- RESULTS -----                               ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(
                        "\nAccuracy: " + accuracy/numberOfEvaluations + "\n" +
                        "Precision: " + precision/numberOfEvaluations + "\n" +
                        "Recall: " + recall/numberOfEvaluations + "\n" +
                        "F1 score: " + f1Score/numberOfEvaluations + "\n"
        );
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
