import csvutils.CsvProcessor;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;

    public class MainDataSetTransformationWithoutSplit {

        private static final int CLASSES_COUNT = 2;
        private static final int FEATURES_COUNT = 9;
        private static final String INPUT_CSV_FILENAME = "tictactoe-dataset.csv";
        private static final String NEW_CSV_FILENAME_TO_GENERATE = "tictactoe-transformed-dataset.csv";

        public static void main(String[] args) throws  Exception {

            CsvProcessor csvProcessor = new CsvProcessor();
            csvProcessor.generateCsvFileWithTransformedDataSetToNumbers(INPUT_CSV_FILENAME, NEW_CSV_FILENAME_TO_GENERATE);

            DataSet allData;
            try (RecordReader recordReader = new CSVRecordReader(0, ',')) {
                recordReader.initialize(
                        new FileSplit(
                        new ClassPathResource(NEW_CSV_FILENAME_TO_GENERATE).getFile()));

                DataSetIterator iterator = new RecordReaderDataSetIterator(
                        recordReader, 958, FEATURES_COUNT, CLASSES_COUNT);
                allData = iterator.next();
            }

            System.out.println("Building data model...");

            allData.shuffle(42);

            DataNormalization normalizer = new NormalizerStandardize();
            normalizer.fit(allData);
            normalizer.transform(allData);

            SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
            DataSet trainingData = testAndTrain.getTrain();
            DataSet testData = testAndTrain.getTest();

            MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
                    .iterations(1000)
                    .activation(Activation.TANH)
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

            MultiLayerNetwork model = new MultiLayerNetwork(configuration);
            model.init();
            model.fit(trainingData);

            INDArray output = model.output(testData.getFeatureMatrix());

            Evaluation eval = new Evaluation(CLASSES_COUNT);
            eval.eval(testData.getLabels(), output);
            System.out.println(eval.stats());
        }
    }
