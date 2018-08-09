package org.adidas.common.globalLibrary;


import org.adidas.common.utils.HttpResponseCode;

import static java.util.Objects.isNull;

public class Get {

    public static HttpResponseCode getResponse(int code)
    {
        for (HttpResponseCode hrc:
                HttpResponseCode.values()) {
            if(hrc.getCode()==code)
            {return hrc;}
        }
        return null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

}
