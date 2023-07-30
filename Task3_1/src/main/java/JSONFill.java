import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFill {
    public static void main(String[] args) throws IOException {
        String testFile = args[0];
        String valFile = args[1];
        
        JsonObject jsonObjectTests = JsonParser.parseReader(new FileReader(testFile)).getAsJsonObject();
        JsonObject jsonObjVals = JsonParser.parseReader(new FileReader(valFile)).getAsJsonObject();
        JsonArray jsonArrayVals = jsonObjVals.get("values").getAsJsonArray();
        JsonArray testsArr = jsonObjectTests.get("tests").getAsJsonArray();

        filling(testsArr, jsonArrayVals);

        FileWriter reportWriter = new FileWriter("report.json");
        reportWriter.write(String.valueOf(jsonObjectTests));
        reportWriter.close();
    }

    public static void filling (JsonArray testsArr, JsonArray vals) {

        //перебираем массивы
        for(JsonElement test : testsArr) {
            JsonObject testObj = test.getAsJsonObject();
            int testID = testObj.get("id").getAsInt();
            for(JsonElement value : vals) {
                JsonObject valObj = value.getAsJsonObject();;
                if(valObj.has("id") && valObj.get("id").getAsInt() == testID){
                    //
                    JsonPrimitive testVal = (JsonPrimitive) valObj.get("value");
                    testObj.add("value", testVal);
                    break;
                }
            }
            if(testObj.has("values")) {
                filling(testObj.get("values").getAsJsonArray(), vals);
            }
        }

    }
}