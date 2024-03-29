package com.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerator;
import com.web.entity.CommonVar;

@RestController
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,RequestMethod.POST}, origins="*")
public class TestInfoController {
	
	

	/**
	 *（展示用）获取首页路灯信息
	 */
	@RequestMapping(value="getLightInfo/lightInfo", method=RequestMethod.GET)
	public String[][] lightInfo() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dstr="2020-4-24";
		Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Double en = (double) ((new Date().getTime()-date.getTime())/1000/3600);
		en = en *5.2;
		Calendar calendar = Calendar.getInstance();
		int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
		Double todayEnery = curHour24*5.2;
		Double other = en - todayEnery;
		String[][] result = new String[3][3];
		result[0][0]="52";
		result[0][1]="52";
		result[0][2]="0";	
		result[1][0]="104";
		result[1][1]="103";
		result[1][2]="1";
		result[2][0]= en.toString();
		result[2][1]= todayEnery.toString();
		result[2][2]= other.toString();
		return result;
	}



	/**
	 *（展示用）获取首页7天能耗折线图
	 */
	@RequestMapping(value="getEnergyConsu/getEnergyChart", method=RequestMethod.GET)
	public Double[][] getRecentlyFour() {
		if(CommonVar.SevenDayEnergy == null) {
			Double[][] result = new Double[11][7];
			for(int j = 0; j<7; j++) {
				for(int i=0; i<7; i++) {
					if(i==6) {
						Calendar calendar = Calendar.getInstance();
						int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
						result[j][i] = curHour24 * (Double.parseDouble(String.format("%.1f", Math.random()))+CommonVar.HourEnergy[j]);
					}else {
						result[j][i] = 24.0* (Double.parseDouble(String.format("%.1f", Math.random()))+CommonVar.HourEnergy[j]);
					}
				}
			}
			for(int j = 7; j<9; j++) {
				for(int i=0; i<7; i++) {
					if(i==6) {
						Calendar calendar = Calendar.getInstance();
						int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
						result[j][i] = curHour24 * (Double.parseDouble(String.format("%.1f", Math.random()))/1000+CommonVar.HourEnergy[j]);
					}else {
						result[j][i] = 24.0* (Double.parseDouble(String.format("%.1f", Math.random()))/1000+CommonVar.HourEnergy[j]);
					}
				}
			}
			for(int j = 9; j<11; j++) {
				for(int i=0; i<7; i++) {
					if(i==6) {
						Calendar calendar = Calendar.getInstance();
						int curHour24 = calendar.get(calendar.HOUR_OF_DAY);
						result[j][i] = curHour24 * (Double.parseDouble(String.format("%.1f", Math.random()))/10+CommonVar.HourEnergy[j]);
					}else {
						result[j][i] = 24.0* (Double.parseDouble(String.format("%.1f", Math.random()))/10+CommonVar.HourEnergy[j]);
					}
				}
			}
			CommonVar.SevenDayEnergy = result;
			return result;
		}else {
			return CommonVar.SevenDayEnergy;
		}
	}
	
	


	/**
	 *（展示用）获取首页警告信息
	 */
	@RequestMapping(value="getWarningInfo/warninginfo", method=RequestMethod.GET)
	@ResponseBody
	public Object warninginfo() {
		if(CommonVar.WarningInfoTeam[0] == null) {
			String[] result = new String[4];
			Random random = new Random();
			List<String> param = new ArrayList<String>();
			while(param.size()<4) {
				Integer a=random.nextInt(7);
				if(param.contains(a.toString()))
					continue;
				else
					param.add(a.toString());
			}
			for(int j=0; j<4; j++) {
				result[j]=CommonVar.WarningInfo[Integer.parseInt(param.get(j))];
				CommonVar.WarningInfoTeamIndex[j] = Integer.parseInt(param.get(j));
				result[j] = result[j].replace("待替换图片", CommonVar.Pictrue[Integer.parseInt(param.get(j))]);
				result[j] = result[j].replace("等待替换", CommonVar.dateToString(new Date(new Date().getTime()-j*1000*60*5), new SimpleDateFormat("yyyy-MM-dd HH:mm")));
			}
			CommonVar.WarningInfoTeam = result;
		}else {
			CommonVar.WarningInfoTeam[0] = null;
			CommonVar.WarningInfoTeamIndex[0]=null;
			List<Integer> param = new ArrayList<Integer>();
			Random random = new Random();
			Integer a=null;
			for(int i=1; i<4; i++) {
				param.add(CommonVar.WarningInfoTeamIndex[i]);
			}
			while(true) {
				a = random.nextInt(7);
				if(param.contains(a))
					continue;
				else
					break;
			}
			Integer[] wii = CommonVar.WarningInfoTeamIndex;
			String[] wit = CommonVar.WarningInfoTeam;
			for(int i=0; i<4; i++) {
				if(i == 3) {
					
				}else {
					CommonVar.WarningInfoTeamIndex[i] = wii[i+1];
					CommonVar.WarningInfoTeam[i] = wit[i+1];
				}
			}
			CommonVar.WarningInfoTeamIndex[3]=a;
			CommonVar.WarningInfoTeam[3] = CommonVar.WarningInfo[CommonVar.WarningInfoTeamIndex[3]];
		}
		return CommonVar.WarningInfoTeam;
	}
	

	/**
	 *（展示用）获取首页温度云图
	 */
	@RequestMapping(value="getHeatMap/getHeatMap", method=RequestMethod.GET)
	@ResponseBody
	public String getHeatMap() {
		String result = "[";
		int index = 1;
//		boolean success = true;
		Double value1=null, value2=null;
		for(int i=0;i<666; i++) {
//			while(success) {
				value1 = 121.150000+Double.valueOf(String.format("%.6f", Math.random()/2));
				value2 = 30.900000+Double.valueOf(String.format("%.6f", Math.random()/2));
//				if((value1<121.200000&&value2>31.3) || (value1<121.200000&&value2<31.43) || (value1>121.7&&value2>31.3)) {
//					
//				}else {
//					success = false;
//				}
//			}
//			success = true;
			if(i==13 || i==29 || i==120 || i==134 || i==176 || i==203 || i==229 || i==251 || i==308 || i==337 || i==402 || i==419 || i==440 || i==457 || i==471 || i==490 || i==517|| i==535 || i==576 || i==600 || i==617)
				index=1;
			if(i==665)
				result+="{\"lng\":"+value1+",\"lat\":"+value2+",\"count\":"+index+"}]";
			else
				result+="{\"lng\":"+value1+",\"lat\":"+value2+",\"count\":"+index+"},";
			index++;
		}
		return result;
	}
}
