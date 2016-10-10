package com.alanma.doraemon.utils.fastjson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

public class TestFastJson {

	// private static String content =
	// "{\"abc\":\"呵呵\",\"abb\":\"哈哈\",\"cca\":\"友友\",\"eco\":\"呦呦\",\"bbc\":{\"yyz\":\"露露\",\"xyz\":\"慢慢\",\"yzz\":\"恰恰\"}}";
	private static String content = "{\"custId\":\"CP001201609080000029\",\"messageIds\":[\"2016092300001517\",\"2016092200001450\"]}";

	public static void main(String[] args) {
		// System.out.println(JSONObject.toJSONString(JSONObject.parseObject(content),SerializerFeature.SortField));
		// System.out.println(JSONObject.toJSONString(content,SerializerFeature.SortField));
		// System.out.println(JSONObject.toJSONString(content,SerializerFeature.SortField,SerializerFeature.UseSingleQuotes));
		System.out.println(getDataInMsg(content));
		// LinkedHashMap<String, String> jsonMap = JSON.parseObject(content, new
		// TypeReference<LinkedHashMap<String, String>>() {});
		// for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
		// System.out.println(entry.getKey() + ":" + entry.getValue());
		// }
	}

	private static String getDataInMsg(String inputStr) {

		List<String> dataKeys = new ArrayList<String>();
		JSONObject jsonObj = (JSONObject) JSONObject.parse(inputStr);
		Set<Entry<String, Object>> set = jsonObj.entrySet();
		for (Entry<String, Object> entry : set) {
			dataKeys.add(entry.getKey());
			if (entry.getValue() instanceof JSONObject) {
				String objStr = getDataInMsg(JSONObject.toJSONString(entry.getValue()));
				entry.setValue(JSONObject.parseObject(objStr, Feature.OrderedField));
			}
		}
		Collections.sort(dataKeys);

		return spliceElements(dataKeys, jsonObj);
	}

	private static String spliceElements(List<String> keys, JSONObject jsonObj) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < keys.size(); i++) {
			sb.append("\"");
			sb.append(keys.get(i));
			sb.append("\"");
			sb.append(":");
			if (jsonObj.get(keys.get(i)) instanceof String) {
				sb.append("\"");
			}
			sb.append(jsonObj.get(keys.get(i)));
			if (jsonObj.get(keys.get(i)) instanceof String) {
				sb.append("\"");
			}
			if (i == keys.size() - 1) {
				sb.append("}");
			} else {
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
