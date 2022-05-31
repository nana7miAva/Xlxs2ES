package Utils;

import java.util.List;
import java.util.Map;

public class ValidUtils {
    private ValidUtils(){}
    public static boolean isNotNull(Object obj) {
        if (obj == null)
            return false;
        else if (obj instanceof List){
            List list = (List)obj;
            if (list.size()==0)
                return false;
        }
        else if (obj instanceof Map){
            Map map = (Map) obj;
            if (map.size()==0)
                return false;
        }else if (obj instanceof String){
            String str = (String)obj;
            if ("".equals(str) || "null".equals(str))
                return false;
        }
        return true;
    }
}
