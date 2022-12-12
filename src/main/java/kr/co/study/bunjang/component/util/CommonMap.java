package kr.co.study.bunjang.component.util;

import java.util.HashMap;
import java.util.List;

import kr.co.study.bunjang.component.utility.ObjUtils;

public class CommonMap extends HashMap<String, Object> {
    
    public String getString(String key) {
        return ObjUtils.objToString(get(key));
    }

    public boolean getBoolean(String key) {
        return ObjUtils.objToBoolean(get(key));
    }

    public Float getFloat(String key) {
        return ObjUtils.objToFloat(get(key));
    }

    public Double getDouble(String key) {
        return ObjUtils.objToDouble(get(key));
    }

    public Integer getInt(String key) {
        return ObjUtils.objToInt(get(key));
    }

    public Long getLong(String key) {
        return ObjUtils.objToLong(get(key));
    }

    public List<?> getList(String key) {
        return ObjUtils.objToList(get(key));
    }
}
