package csvutils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class CsvProcessor {

        private static final int X = 2;
        private static final int O = 3;
        private static final int B = 4;
        private static final int POSITIVE = 1;
        private static final int NEGATIVE = 0;

        private static final String COMMA_DELIMITER = ",";
        private static final String NEW_LINE_SEPARATOR = "\n";

        private String csvFileName;

        public CsvProcessor(String csvFileName) {
            this.csvFileName = csvFileName;
        }

        public void generateCsvFileWithTransformedDataSetToNumbers() {
            try {
                FileWriter writer = new FileWriter(
                        "src/main/resources/tictactoe-transformed-dataset.csv",false);

                for (List<Integer> row : getEndgameDataSet()) {
                    for (Integer number : row) {
                        writer.append(number.toString());
                        if (row.indexOf(number) < row.size()-1) {
                            writer.append(COMMA_DELIMITER);
                        }
                    }
                    writer.append(NEW_LINE_SEPARATOR);
                }
                writer.flush();
                writer.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }

        private List<List<String>> getRawCsvData() {
            BufferedReader br = null;
            String line;
            String cvsSplitBy = ",";
            List<List<String>> readData = new ArrayList<>();

            try {
                br = new BufferedReader(new InputStreamReader(
                        this.getClass().getResourceAsStream("/" + this.csvFileName)));

                while ((line = br.readLine()) != null) {
                    String[] nextRecord = line.split(cvsSplitBy);
                    List<String> row = Arrays.asList(nextRecord);
                    readData.add(row);
                }

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return readData;
        }

        private List<List<Integer>> getEndgameDataSet() {
            List<List<String>> rawData = getRawCsvData();
            List<List<Integer>> convertedSignsToNumbers = new ArrayList<>();
            List<Integer> temporaryDynamicNumberList = new ArrayList<>();

            for (List<String> row : rawData) {
                for (String sign : row) {
                    temporaryDynamicNumberList = validateSign(sign, temporaryDynamicNumberList);
                }

                convertedSignsToNumbers.add(temporaryDynamicNumberList);
                temporaryDynamicNumberList = new ArrayList<>();
            }

            return convertedSignsToNumbers;
        }

        private List<Integer> validateSign(String sign, List<Integer> numbers) {
            switch (sign) {
                case "x":
                    numbers.add(X);
                    break;

                case "o":
                    numbers.add(O);
                    break;

                case "b":
                    numbers.add(B);
                    break;

                case "positive":
                    numbers.add(POSITIVE);
                    break;

                case "negative":
                    numbers.add(NEGATIVE);
                    break;
            }
            return numbers;
        }
    }