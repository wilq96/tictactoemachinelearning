import csvutils.CsvProcessor;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeDataAnalyzer {

    private CsvProcessor csvProcessor = new CsvProcessor();

    public List<List<Integer>> getPositiveOrNegativeDataByClass(int classMark) {
        List<List<Integer>> rawDataSet = csvProcessor.getEndgameDataSet("tictactoe-dataset.csv");
        List<List<Integer>> resultData = new ArrayList<>();

        rawDataSet.forEach(row -> {
            if (row.get(9) == classMark) {
                resultData.add(new ArrayList<>(row));
            }
        });
        return resultData;
    }

    public void splitDataToBucket(List<List<Integer>> positiveDataSet,
                                  List<List<Integer>> negativeDataSet,
                                  List<List<Integer>> outputList) {
        List<List<Integer>> foundNegativeData = getListsFromDataSetByAmount(33, negativeDataSet);
        List<List<Integer>> foundPositiveData = getListsFromDataSetByAmount(62, positiveDataSet);
        outputList.addAll(foundNegativeData);
        outputList.addAll(foundPositiveData);
    }

    private List<List<Integer>> getListsFromDataSetByAmount(int numberOfLists, List<List<Integer>> inputList) {
        List<List<Integer>> outputList = new ArrayList<>();

        for (int i=0;i<numberOfLists;i++) {
            List<Integer> foundList = inputList.get(i);
            outputList.add(foundList);
        }

        for (int i=0;i<numberOfLists;i++) {
            if (inputList.contains(outputList.get(i))) {
                inputList.remove(outputList.get(i));
            }
        }
        return outputList;
    }
}
