import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws Exception {
        // Чтение из JSON файлов
        HashMap<String, ArrayList> values = mapJsonToMap(args[0]);
        HashMap<String, ArrayList> tests = mapJsonToMap(args[1]);

        // Преобразование и обновление значений
        HashMap<Integer, String> valuesMap = mapFromValues(values.get("values"));
        updateValues((ArrayList<LinkedHashMap>) tests.get("tests"), valuesMap);

        // Запись в новый файл JSON
        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tests);
        FileOutputStream fos = new FileOutputStream(args[2]);
        fos.write(results.getBytes());
    }

    // Чтение файлов JSON в HashMap
    private static HashMap<String, ArrayList> mapJsonToMap(String filepath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, ArrayList> map =
                mapper.readValue(new File(filepath), new TypeReference<HashMap<String, ArrayList>>(){});
        return map;
    }

    // Рекурсивная функция поиска и замены значений "value"
    private static void updateValues(ArrayList<LinkedHashMap> testsArray, HashMap<Integer, String> valuesMap) {
        for (LinkedHashMap<String, Object> map : testsArray) {
            if (map.containsKey("value")) {
                if (valuesMap.get(map.get("id")) != null) map.put("value", valuesMap.get(map.get("id"))); }
            if (map.containsKey("values")) updateValues((ArrayList<LinkedHashMap>) map.get("values"), valuesMap);
        }
    }

    // Создание HashMap {id : "value", ...}
    private static HashMap<Integer, String> mapFromValues(ArrayList<LinkedHashMap> valuesArray) {
        HashMap<Integer, String> valuesMap = new HashMap<>();
        for (LinkedHashMap map : valuesArray) valuesMap.put((int) map.get("id"), (String) map.get("value"));
        return valuesMap;
    }
}