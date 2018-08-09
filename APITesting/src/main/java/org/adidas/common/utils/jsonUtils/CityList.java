package org.adidas.common.utils.jsonUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CityList implements JsonInit {

    public String getId(CityIdentifier identifier, String cityName) {
        if (identifier == CityIdentifier.name) {
            JSONArray jsonArray = getArray(CITY_LIST_PATH);

            for (Object o : jsonArray) {
                JSONObject cityList = (JSONObject) o;
                String city = (String) cityList.get(CityIdentifier.name.name());
                if (cityName.equalsIgnoreCase(city)) {
                    return cityList.get(CityIdentifier.id.name())+"";
                }

            }
        }
        return null;
    }

    public static void main(String[] args) {
        CityList cl = new CityList();
        System.out.println(cl.getId(CityIdentifier.name, "Gorkhā1"));
    }
}
