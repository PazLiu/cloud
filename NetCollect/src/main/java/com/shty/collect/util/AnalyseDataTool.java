package com.shty.collect.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shty.collect.domain.lnk.ResultData;

public class AnalyseDataTool {

	public ResultData analyse(String data) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper(); // 转换器
		ResultData resultData = new ResultData();

		String d = data.replace("Volunteers", "volunteers");

		if (null != data) {
			resultData = mapper.readValue(d, ResultData.class);
			// System.out.println(resultData.toString());
		}

		return resultData;
	}

}
