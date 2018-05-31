import csvutils.CsvProcessor;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDatasetBucketsSplitted {

    private static final int CLASSES_COUNT = 2;
    private static final int FEATURES_COUNT = 9;
    private static final int BATCH_SIZE = 95;
    private static final String FILE_ROOT_DIR = "split-dataset-buckets/";

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

//        TicTacToeDataAnalyzer ticTacToeDataAnalyzer = new TicTacToeDataAnalyzer();
//        CsvProcessor csvProcessor = new CsvProcessor();
//
//        List<List<Integer>> lostDataSet = ticTacToeDataAnalyzer.getPositiveOrNegativeDataByClass(0); //332
//        List<List<Integer>> winDataSet = ticTacToeDataAnalyzer.getPositiveOrNegativeDataByClass(1); //626
//
//        List<List<Integer>> dataSet1 = new ArrayList<>();
//        List<List<Integer>> dataSet2 = new ArrayList<>();
//        List<List<Integer>> dataSet3 = new ArrayList<>();
//        List<List<Integer>> dataSet4 = new ArrayList<>();
//        List<List<Integer>> dataSet5 = new ArrayList<>();
//        List<List<Integer>> dataSet6 = new ArrayList<>();
//        List<List<Integer>> dataSet7 = new ArrayList<>();
//        List<List<Integer>> dataSet8 = new ArrayList<>();
//        List<List<Integer>> dataSet9 = new ArrayList<>();
//        List<List<Integer>> dataSet10 = new ArrayList<>();
//
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet1);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet2);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet3);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet4);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet5);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet6);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet7);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet8);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet9);
//        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet10);
//
//        csvProcessor.generateCsvFileFromList(dataSet1, "dataset1.csv");
//        csvProcessor.generateCsvFileFromList(dataSet2, "dataset2.csv");
//        csvProcessor.generateCsvFileFromList(dataSet3, "dataset3.csv");
//        csvProcessor.generateCsvFileFromList(dataSet4, "dataset4.csv");
//        csvProcessor.generateCsvFileFromList(dataSet5, "dataset5.csv");
//        csvProcessor.generateCsvFileFromList(dataSet6, "dataset6.csv");
//        csvProcessor.generateCsvFileFromList(dataSet7, "dataset7.csv");
//        csvProcessor.generateCsvFileFromList(dataSet8, "dataset8.csv");
//        csvProcessor.generateCsvFileFromList(dataSet9, "dataset9.csv");
//        csvProcessor.generateCsvFileFromList(dataSet10, "dataset10.csv");


        DeepLearningGenerator deepLearningGenerator = new DeepLearningGenerator();

        DataSet allDataset1 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset1.csv");

        DataSet allDataset2 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset2.csv");

        DataSet allDataset3 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset3.csv");

        DataSet allDataset4 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset4.csv");

        DataSet allDataset5 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset5.csv");

        DataSet allDataset6 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset6.csv");

        DataSet allDataset7 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset7.csv");

        DataSet allDataset8 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset8.csv");

        DataSet allDataset9 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset9.csv");

        DataSet allDataset10 = deepLearningGenerator
                .generateDataSetByOptions(BATCH_SIZE, CLASSES_COUNT, FEATURES_COUNT, FILE_ROOT_DIR + "dataset10.csv");

        List<DataSet> dataSetsWithout1 = new ArrayList<>(
                Arrays.asList(allDataset2, allDataset3,allDataset4, allDataset5, allDataset6, allDataset7,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout2 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset3,allDataset4, allDataset5, allDataset6, allDataset7,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout3 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset4, allDataset5, allDataset6, allDataset7,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout4 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3, allDataset5, allDataset6, allDataset7,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout5 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3,allDataset4, allDataset6, allDataset7,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout6 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3,allDataset4, allDataset5, allDataset7,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout7 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3,allDataset4, allDataset5, allDataset6,
                        allDataset8, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout8 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3,allDataset4, allDataset5, allDataset6,
                        allDataset7, allDataset9, allDataset10));

        List<DataSet> dataSetsWithout9 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3,allDataset4, allDataset5, allDataset6,
                        allDataset7, allDataset8, allDataset10));

        List<DataSet> dataSetsWithout10 = new ArrayList<>(
                Arrays.asList(allDataset1, allDataset2, allDataset3,allDataset4, allDataset5, allDataset6,
                        allDataset7, allDataset8, allDataset9));

        allDataset1.shuffle(40);
        for (DataSet dataSet : dataSetsWithout1) {
            dataSet.shuffle(40);
        }

        MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
                .iterations(10)
                .activation(Activation.SIGMOID)
                .weightInit(WeightInit.XAVIER)
                .learningRate(0.1)
                .regularization(true).l2(0.0001)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(FEATURES_COUNT).nOut(8)
                        .build())
                .layer(1, new DenseLayer.Builder().nIn(8).nOut(8)
                        .build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(8).nOut(CLASSES_COUNT).build())
                .backprop(true).pretrain(false)
                .build();

        PrintStream out = new PrintStream(new FileOutputStream("src/main/resources/results.txt"));
        System.setOut(out);

        MultiLayerNetwork model = new MultiLayerNetwork(configuration);
        model.init();
        List<Evaluation> evaluations = new ArrayList<>();

        for (int i=0;i<30;i++) {
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset1, dataSetsWithout1));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset2, dataSetsWithout2));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset3, dataSetsWithout3));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset4, dataSetsWithout4));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset5, dataSetsWithout5));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset6, dataSetsWithout6));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset7, dataSetsWithout7));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset8, dataSetsWithout8));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset9, dataSetsWithout9));
            evaluations.add(deepLearningGenerator.getEvaluationData(model, allDataset10, dataSetsWithout10));
        }

        deepLearningGenerator.doSummaryCalculation(evaluations);
    }
}
