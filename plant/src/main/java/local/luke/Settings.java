package local.luke;

public class Settings {

    private static final String DELIMITER = "\t";
    private static final String FILE_NAME_INPUT = "plant/resources/kvetiny.txt";
    private static final String FILE_NAME_OUTPUT = "plant/resources/kvetiny-output.txt";
    private static final String FILE_NAME_WRONG_DATE = "plant/resources/kvetiny-spatne-datum.txt";
    private static final String FILE_NAME_WRONG_FREQUENCY = "plant/resources/kvetiny-spatne-frekvence.txt";

    public static String getDelimiter() {
        return DELIMITER;
    }

    public static String getFileNameImput() {
        return FILE_NAME_INPUT;
    }

    public static String getFileNameOutput() {
        return FILE_NAME_OUTPUT;
    }

    public static String getFileNameWrongDate() {
        return FILE_NAME_WRONG_DATE;
    }

    public static String getFileNameWrongFrequency() {
        return FILE_NAME_WRONG_FREQUENCY;
    }
}
