package kr.co.study.bunjang.component.utils;

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

	public String ObjToBoolean(Object obj) {
		try {
			return obj.toString();
		} catch (NullPointerException e) {
			log.info("ObjUtils - ObjToStringe: return null");
		}
		return null;
	}

	public String ObjToString(Object obj) {
		try {
			return obj.toString();
		} catch (NullPointerException e) {
			log.info("ObjUtils - ObjToStringe: return null");
		}
		return null;
	}

	public Float ObjToFloat(Object obj) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - ObjToFloat: return null");
		}
		return null;
	}

	public Double ObjToDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - ObjToDouble: return null");
		}
		return null;
	}

	public Integer ObjToInt(Object obj) {
		try {
			return Integer.parseInt(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - ObjToInt: return null");
		}
		return null;
	}

	public Long ObjToLong(Object obj) {
		try {
			return Long.parseLong(obj.toString());
		} catch (NullPointerException e) {
			log.info("ObjUtils - ObjToLong: return null");
		}
		return null;
	}
}