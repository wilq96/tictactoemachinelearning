import csvutils.CsvProcessor;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TicTacToeDataAnalyzer ticTacToeDataAnalyzer = new TicTacToeDataAnalyzer();
        CsvProcessor csvProcessor = new CsvProcessor();

        List<List<Integer>> lostDataSet = ticTacToeDataAnalyzer.getPositiveOrNegativeDataByClass(0); //332
        List<List<Integer>> winDataSet = ticTacToeDataAnalyzer.getPositiveOrNegativeDataByClass(1); //626

        List<List<Integer>> dataSet1 = new ArrayList<>();
        List<List<Integer>> dataSet2 = new ArrayList<>();
        List<List<Integer>> dataSet3 = new ArrayList<>();
        List<List<Integer>> dataSet4 = new ArrayList<>();
        List<List<Integer>> dataSet5 = new ArrayList<>();
        List<List<Integer>> dataSet6 = new ArrayList<>();
        List<List<Integer>> dataSet7 = new ArrayList<>();
        List<List<Integer>> dataSet8 = new ArrayList<>();
        List<List<Integer>> dataSet9 = new ArrayList<>();
        List<List<Integer>> dataSet10 = new ArrayList<>();

        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet1);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet2);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet3);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet4);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet5);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet6);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet7);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet8);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet9);
        ticTacToeDataAnalyzer.splitDataToBucket(winDataSet, lostDataSet, dataSet10);

        csvProcessor.generateCsvFileFromList(dataSet1, "dataset1.csv");
        csvProcessor.generateCsvFileFromList(dataSet2, "dataset2.csv");
        csvProcessor.generateCsvFileFromList(dataSet3, "dataset3.csv");
        csvProcessor.generateCsvFileFromList(dataSet4, "dataset4.csv");
        csvProcessor.generateCsvFileFromList(dataSet5, "dataset5.csv");
        csvProcessor.generateCsvFileFromList(dataSet6, "dataset6.csv");
        csvProcessor.generateCsvFileFromList(dataSet7, "dataset7.csv");
        csvProcessor.generateCsvFileFromList(dataSet8, "dataset8.csv");
        csvProcessor.generateCsvFileFromList(dataSet9, "dataset9.csv");
        csvProcessor.generateCsvFileFromList(dataSet10, "dataset10.csv");
    }
}
