import csvutils.CsvProcessor;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeDataAnalyzer {

    private CsvProcessor csvProcessor = new CsvProcessor("tictactoe-dataset.csv");

    public List<List<Integer>> getPositiveOrNegativeDataByClass(int classMark) {
        List<List<Integer>> rawDataSet = csvProcessor.getEndgameDataSet();
        List<List<Integer>> resultData = new ArrayList<>();

        rawDataSet.forEach(row -> {
            if (row.get(9) == classMark) {
                resultData.add(new ArrayList<>(row));
            }
        });
        return resultData;
    }
}
