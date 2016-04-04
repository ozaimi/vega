/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.tomcat.web;


import net.bull.javamelody.MonitoredWithSpring;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.*;
import sample.tomcat.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sample.tomcat.service.UserCache;


import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

@MonitoredWithSpring
@RestController
@RequestMapping("/control")
public class SampleController {

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping("/")
	@ResponseBody
	public String helloWorld() {
		return this.helloWorldService.getHelloMessage();
	}


	@RequestMapping(value={"/{id}"},method = {RequestMethod.GET})
	public @ResponseBody
	String invoke(@PathVariable("id") String id){
		//UserCache.init();
		Properties p = readProperties();
		String data = invokeUrl(p.getProperty(id));


		return data;
	}

	private Properties readProperties(){
		try {
			File file = new File("param.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String invokeUrl(String url){


		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header

		HttpResponse response = null;
		try {
			response = client.execute(request);


		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line).append("\n");
		}
			return result.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
