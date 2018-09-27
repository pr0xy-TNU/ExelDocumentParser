import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final String FILE_PATH_ENG_WITH_KEYS = "/home/user/Downloads/trakimo_exel.xlsx";
    public static final String FILE_PATH_IT_WITH_KEYS = "/home/user/Downloads/trakimo_exel_italian.xlsx";
    public static final String FILE_PATH_IT_WITH_KEYS_TXT = "/home/user/Downloads/trakimo_italian.txt";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(FILE_PATH_ENG_WITH_KEYS));
        Map<String, String> engWithKeys = parseExcelDocToMap(0, workbook);
        Map<String, String> italyWithEng = parseExcelDocToMap(1, workbook);
        //Map<Integer, XmlAndroidDescriptor> androidDescriptorMap = new HashMap<>();
        Map<String, String> italianWithKey = new HashMap<>();

        engWithKeys.forEach((engKey, engValue) -> {
            italyWithEng.forEach((italiEngKey, italiEngValue) -> {
                if (engValue.equals(italiEngKey)) {
                    italianWithKey.put(engKey, italiEngValue);
                }
            });
        });

        if (!italianWithKey.isEmpty()) {
            createWorkbook("Itelian", italianWithKey);
            System.out.println("creating");
        }
        createTextFileFromMap(FILE_PATH_IT_WITH_KEYS_TXT, italianWithKey);


        //printMap(italianWithKey);
        workbook.close();

    }

    public static void printMap(Map<String, String> map) {
        int counter = 1;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            System.out.printf("%d : %s\t: %s\t\n", counter++, pair.getKey(), pair.getValue());
        }
    }

    public static Map<String, String> parseExcelDocToMap(int sheetId,
                                                         Workbook workbook) throws IOException, InvalidFormatException {

        Sheet sheet = workbook.getSheetAt(sheetId);
        final DataFormatter formatter = new DataFormatter();
        Map<String, String> engWithKeys = new HashMap<>();
        sheet.forEach(row -> {
                    XmlAndroidDescriptor tempEntity = new XmlAndroidDescriptor();
                    String key = formatter.formatCellValue(row.getCell(0));
                    String value = formatter.formatCellValue(row.getCell(1));

                    engWithKeys.put(key, value);

                }
        );

        return engWithKeys;
    }

    public static boolean createWorkbook(String sheetName,
                                         Map<String, String> map) throws IOException {
        boolean isCreatedOk = false;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        /*AtomicInteger counter = new AtomicInteger();
        map.forEach((key, value) -> {
            Row row = sheet.createRow(counter.incrementAndGet());
            row.createCell(0).setCellValue(key);
            row.createCell(1).setCellValue(value);
            System.out.println(counter.incrementAndGet() + " creating row : " + key + " " + value);
        });*/
        int counter = 0;
        for (Map.Entry<String, String> pair : map.entrySet()
        ) {
            Row row = sheet.createRow(counter++);
            row.createCell(0).setCellValue(pair.getKey());
            row.createCell(1).setCellValue(pair.getValue());
            //System.out.println(counter + " creating row : " + pair.getKey() + " " + pair.getValue());
        }

        writeFiele(workbook, "name");
        return isCreatedOk;
    }

    public static void createTextFileFromMap(String fileName, Map<String, String> map) throws IOException {
        StringBuilder content = new StringBuilder();
        String pattern = "<string name=\"%s\">%s</string>";
        for (Map.Entry<String, String> pair : map.entrySet()
        ) {
            content.append(String.format(pattern, pair.getKey(), pair.getValue()));
            content.append("\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content.toString());
        writer.close();
    }

    public static void writeFiele(Workbook workbook, String fileName) throws IOException {

        FileOutputStream stream = new FileOutputStream(FILE_PATH_IT_WITH_KEYS);
        workbook.write(stream);
        stream.close();
    }

    public static class XmlAndroidDescriptor {
        private String italy;
        private String english;
        private String key;

        public XmlAndroidDescriptor(String italy, String english, String key) {
            this.italy = italy;
            this.english = english;
            this.key = key;
        }

        public XmlAndroidDescriptor() {
            //default
        }

        public String getItaly() {
            return italy;
        }

        public void setItaly(String italy) {
            this.italy = italy;
        }

        public String getEnglish() {
            return english;
        }

        public void setEnglish(String english) {
            this.english = english;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
