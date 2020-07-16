package com.restassured;

import static io.restassured.RestAssured.*;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.pojoclass.ExcelUtils;
import com.pojoclass.Root;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;


public class Test1_BasicFeatures {


@Test
	public void compareWeatherReports() throws InvalidFormatException, IOException {
		
	

		RestAssured.baseURI = "https://samples.openweathermap.org/";
		Root res = given().queryParam("q", "London,UK")
				.queryParam("appid", "b1b15e88fa797225412429c1c50c122a1").expect().defaultParser(Parser.JSON)
				.when().get("data/2.5/history/city").then().extract().body().as(Root.class);
		
		ExcelUtils.readExcel(res);
		
	}


	
}
