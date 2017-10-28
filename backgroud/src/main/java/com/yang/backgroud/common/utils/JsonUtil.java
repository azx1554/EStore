package com.yang.backgroud.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;

public class JsonUtil {

	private static final Logger logger = LogManager.getLogger(JsonUtil.class);

	/**
	 * 性能方面 JackSon > Gson > Json 故这里决定使用JackSon 将Collection转换为Json
	 * 参数是Collection
	 * 
	 * @param collection
	 */
	@SuppressWarnings("deprecation")
	public static String toJson(Collection<Object> collection) {
		/*
		 * 使用Gson的方式 Gson gson = new Gson(); String json = gson.toJson(map);
		 */

		/*
		 * 使用Jackson的方式
		 */
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(gen, collection);
			gen.close();
		} catch (JsonGenerationException e) {
			logger.error("", e);

		} catch (JsonMappingException e) {
			logger.error("", e);

		} catch (IOException e) {
			logger.error("", e);

		}
		String json = sw.toString();

		return json;
	}

	/**
	 * @Title: toJson
	 * @Description: 将对象转换为Json数据
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public static String toJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();

		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(gen, obj);
			gen.close();
		} catch (JsonGenerationException e) {
			logger.error("", e);

		} catch (JsonMappingException e) {
			logger.error("", e);

		} catch (IOException e) {
			logger.error("", e);

		}
		String json = sw.toString();

		return json;
	}

	/**
	 * @Title: toJson
	 * @Description: 将对象转换为Json数据
	 * @param @param obj
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	/*
	 * @SuppressWarnings("deprecation") public static String objectToJson(Object
	 * obj,String filterClass,String ... field) { ObjectMapper mapper = new
	 * ObjectMapper(); StringWriter sw = new StringWriter();
	 * 
	 * try { JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
	 * FilterProvider filters = new
	 * SimpleFilterProvider().addFilter(filterClass,
	 * SimpleBeanPropertyFilter.filterOutAllExcept(field));
	 * mapper.filteredWriter(filters).writeValue(gen, obj); gen.close(); } catch
	 * (JsonGenerationException e) { logger.error("", e);
	 * 
	 * } catch (JsonMappingException e) { logger.error("", e);
	 * 
	 * } catch (IOException e) { logger.error("", e);
	 * 
	 * } String json = sw.toString();
	 * 
	 * return json; }
	 */

	/**
	 * 将MAP转为Json Map参数
	 * 
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String toJson(Map<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(gen, map);
			gen.close();
		} catch (JsonGenerationException e) {
			logger.error("----1------toJson JsonGenerationException:\n" + e);
		} catch (JsonMappingException e) {
			logger.error("----2------toJson JsonMappingException:\n" + e);
		} catch (IOException e) {
			logger.error("----3------toJson IOException:\n" + e);
		}
		String json = sw.toString();

		return json;
	}

	/**
	 * 将Json转换为 类子对象
	 * 
	 * @param json
	 * @param classz
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static Object jsonToPayInfo(String json, Class<?> classz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(json.getBytes(), classz);
	}

	/**
	 * 将JSON字符串 转换为对象
	 * 
	 * @author weiyuanhua
	 * @date 2010-11-18 下午02:52:13
	 * @param jsonStr
	 *            JSON字符串
	 * @param beanClass
	 *            泛型对象
	 * @param field
	 *            对象中需要忽略的属性
	 * @return
	 */
	public static Object jsonToObject(String jsonStr, Class<?> beanClass) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonStr.getBytes(), beanClass);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(jsonStr, Map.class);
	}

	/*
	 * public static void main(String[] args) { BasePayInfo payInfo = null;
	 * payInfo = new CreditPayInfo(); System.out.println(payInfo.getClass());
	 * 
	 * payInfo = new MobilePayInfo(); System.out.println(payInfo.getClass());
	 * 
	 * payInfo = new WegPayInfo(); System.out.println(payInfo.getClass());
	 * 
	 * payInfo = new QueryPayInfo(); System.out.println(payInfo.getClass()); }
	 */

}
