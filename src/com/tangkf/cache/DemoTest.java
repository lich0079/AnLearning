/*
 * Copyright (c) 2005-2008 旭鸣软件
 * All rights reserved. 
 */
/*
 * File：DemoTest.java
 * History:
 *       2009-12-11: Initially created, 汤垲�?.
 */
package com.tangkf.cache;

import java.util.Date;

/**
 * @author 汤垲�?
 */
public class DemoTest {
	private ObjectCachePool<String,String> ocp	= new ObjectCachePool<String,String>(100,50);

	public static void main(String[] args) {
		new DemoTest().test01();
	}

	public void test01(){
		ocp.setStateTimes(20);
		ocp.setMonitor(new Monitor());
		ocp.startMonitor();
		for(int i=0;i<100;i++){
		ocp.put("fdsa"+i, "fdaf");
		ocp.get("fdsa"+i);
		ocp.put("2"+i, "fdaf");
		ocp.get("fdsa"+i);
		ocp.put("3"+i, "fdaf");
		ocp.get("2"+i);
		}
	}
	
	class Monitor implements IMonitor{

		public void sendCleanInfo(int forNum, int cleanSize, int cleanTime,
				int currentSize, int cleanNum) {
			System.err.println(
					" 轮询次数�?"+forNum + " 清除数量�?"+cleanSize + 
					" 清除耗时�?"+cleanTime + " 使用空间�?"+currentSize +
					" 清理次数�?"+cleanNum);
		}

		public void sendStateInfo(Date startTime, int currentSize, 
				int accSucNum, int accessNum, int cleanNum) {
			System.err.println(" 访问次数�?"+accessNum + " 成功次数�?"+accSucNum + 
					" 使用空间�?"+currentSize +
					" 清理次数�?"+cleanNum);
		}
	}
}