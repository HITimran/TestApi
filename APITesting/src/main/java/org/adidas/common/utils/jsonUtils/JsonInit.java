package org.adidas.common.utils.jsonUtils;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public interface JsonInit {

    Logger log = Logger.getLogger(JsonInit.class.getName());
    String CITY_LIST_PATH="src\\main\\resources\\city.list.json";

   default JSONArray getArray(String path)
   {
       JSONParser parser = new JSONParser();
       JSONArray jsonArray=null;
       try {
            jsonArray = (JSONArray) parser.parse(new FileReader(
                    path )  );
       } catch (Exception e) {
           e.printStackTrace();
       }
       return jsonArray;
   }


}
