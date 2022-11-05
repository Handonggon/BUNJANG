package kr.co.study.bunjang.component.utility;

import org.apache.commons.lang3.ObjectUtils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ObjUtils extends ObjectUtils {

	public Object nvl(Object obj, Object nvlObj) {
		if (ObjectUtils.isEmpty(obj))
			return nvlObj;
		return obj;
	}

	public String objToString(Object obj) {
		try {
			return obj.toString();
		} catch (NullPointerException e) {
			log.info("ObjUtils - objToString: return null");
		}
		return null;
	}

	public Boolean objToBoolean(Object obj) {
		try {
			return Boolean.parseBoolean(objToString(obj));
		} catch (NullPointerException e) {
			log.info("ObjUtils - objToBoolean: return null");
		}
		return null;
	}

	public Float objToFloat(Object obj) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - objToFloat: return null");
		}
		return null;
	}

	public Double objToDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - objToDouble: return null");
		}
		return null;
	}

	public Integer objToInt(Object obj) {
		try {
			return Integer.parseInt(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - objToInt: return null");
		}
		return null;
	}

	public Long objToLong(Object obj) {
		try {
			return Long.parseLong(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - objToLong: return null");
		}
		return null;
	}
}