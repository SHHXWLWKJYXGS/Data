package com.web.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonVar {
	public static final Double[] HourEnergy = {5.2, 3.7, 7.2, 2.1, 1.9, 6.3, 3.3, 0.0011, 0.0012, 0.7, 0.2};  
	public static Double[][] SevenDayEnergy = null;
	public static String[] WarningInfo = {"{\"url\": \"待替换图片\", \"equipment\": \"井盖丢失\", \"number\":\"M-1003\", \"text\":\"等待替换\", \"address\": \"东经101.9574252E 北纬30.75441654N\",\"content\":\"发生位移\",\"level\":\"1级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"丁元英\"}",
			"{\"url\": \"待替换图片\", \"equipment\": \"井盖丢失\", \"number\":\"M-1004\", \"text\":\"等待替换\", \"address\": \"东经102.5812611E 北纬30.96131752N\",\"content\":\"发生位移\",\"level\":\"2级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"林宇峰\"}",
			"{\"url\": \"待替换图片\", \"equipment\": \"灯杆倾斜\", \"number\":\"M-1005\", \"text\":\"等待替换\", \"address\": \"东经103.0174556E 北纬31.26443654N\",\"content\":\"发生位移\",\"level\":\"2级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"叶晓明\"}",
			"{\"url\": \"待替换图片\", \"equipment\": \"路灯损毁\", \"number\":\"M-1006\", \"text\":\"等待替换\", \"address\": \"东经102.3661857E 北纬31.17323759N\",\"content\":\"发生位移\",\"level\":\"2级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"刘 冰\"}",
			"{\"url\": \"待替换图片\", \"equipment\": \"路灯离线\", \"number\":\"M-1007\", \"text\":\"等待替换\", \"address\": \"东经102.9034716E 北纬31.22753601N\",\"content\":\"发生位移\",\"level\":\"2级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"小 丹\"}",
			"{\"url\": \"待替换图片\", \"equipment\": \"路灯老化\", \"number\":\"M-1008\", \"text\":\"等待替换\", \"address\": \"东经102.3574652E 北纬31.36223155N\",\"content\":\"发生位移\",\"level\":\"2级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"韩楚风\"}",
			"{\"url\": \"待替换图片\", \"equipment\": \"垃圾桶满溢\", \"number\":\"M-1009\", \"text\":\"等待替换\", \"address\": \"东经101.9524151E 北纬31.42345358N\",\"content\":\"发生位移\",\"level\":\"3级\",\"Treatment\":\"请通知相关人员 紧急处理！\",\"related\":\"肖亚文\"}",};
	public static String[] Pictrue= {":8080/picture/lost1.jpg",":8080/picture/lost2.jpg",":8080/picture/break1.jpg",":8080/picture/break2.jpg",":8080/picture/break3.jpg",":8080/picture/break4.jpg",":8080/picture/full1.jpg",};
	public static String[] WarningInfoTeam = new String[4];
	public static Integer[] WarningInfoTeamIndex = new Integer[4];
	
	
	
	public static Integer LightOneDayCountTimes = 48;
	public static final Integer LightPort = 6789;
	/** 和粤控设备建立TCP长连接指令 */
	public static byte[] BuildTcpMsg = {(byte)0xfe, (byte)0x6a, (byte)0x6b, (byte)0x1d, (byte)0xd2, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x07, (byte)0xfe, (byte)0x0f, (byte)0xce, (byte)0xfa, (byte)0x02, (byte)0x04, (byte)0x18, (byte)0x27, (byte)0x63, (byte)0xa6, (byte)0x14, (byte)0x1a, (byte)0x85, (byte)0xff, (byte)0x1a, (byte)0xf3, (byte)0xee, (byte)0xee};
	/** 控制粤控设备开灯指令 */
	public static byte[] TurnOnLightMsg = {(byte)0xfe, (byte)0x6a, (byte)0x6b, (byte)0x1d, (byte)0xd2, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x07, (byte)0xfe, (byte)0x0f, (byte)0xce, (byte)0xfa, (byte)0x02, (byte)0x04, (byte)0x11, (byte)0x13, (byte)0x63, (byte)0xa6, (byte)0x14, (byte)0x1a, (byte)0x85, (byte)0xff, (byte)0x1a, (byte)0xf3, (byte)0xee, (byte)0xee};
	/** 控制粤控设备关灯指令 */
	public static byte[] TurnOffLightMsg = {(byte)0xfe, (byte)0x6a, (byte)0x6b, (byte)0x1d, (byte)0xd2, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x07, (byte)0xfe, (byte)0x0f, (byte)0xce, (byte)0xfa, (byte)0x02, (byte)0x04, (byte)0x11, (byte)0x14, (byte)0x63, (byte)0xa6, (byte)0x14, (byte)0x1a, (byte)0x85, (byte)0xff, (byte)0x1a, (byte)0xf3, (byte)0xee, (byte)0xee};
	public static final String LocalIpAddress = "39.99.166.20";
	public static Map<String,String> LightOperate = new HashMap<String,String>();
	public static Map<String,String[]> Light = new HashMap<String,String[]>();
	
	
	
	public static synchronized String setLightOperate(String key, String value) {
		if(CommonVar.LightOperate.get(key) != null){
			return "您操作太频繁了，请稍等";
		}else {
			CommonVar.LightOperate.put(key, value);
			if(value.equals("开灯"))
				return "开灯";
			else
				return "关灯";
		}
	}
	
	public static synchronized String setLight(String key, String[] value) {
		if(CommonVar.Light.get(key)!=null) {
			return "请勿重复操作";
		}else {
			CommonVar.Light.put(key, value);
			return "操作成功";
		}
	}

	
	/**
	 * 获取本机用来远程操作智慧照明设备的端口
	 * @return
	 */
	public static byte[] getLightPort() {
		short s= CommonVar.LightPort.shortValue();//原数ABCD二个字节
		byte result[]=new byte[2];//准备两个字节
		result[0]=(byte)(s>>>8);//获得高位字节
		result[1]=(byte)(s&0xff); //获得低位字节
		return result;
	}
	
	/**
	 * 获取本机IP地址
	 * @return
	 */
	public static byte[] getLocalIpAddress() {
		String[] param = CommonVar.LocalIpAddress.split("\\.");
		byte[] result = new byte[4];
		for(int i=0; i<param.length; i++) {
			result[i]=(byte) Integer.parseInt(param[i]);
		}
		return result;
	}
	
	
	public static String dateToString(Date date, DateFormat dateFormat) {
		return dateFormat.format(date);
	}

	public static String formatDateToString(Date date) {
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return myFmt2.format(date);
	}
}
