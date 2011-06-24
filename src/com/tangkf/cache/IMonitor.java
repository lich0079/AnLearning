/*
 * Copyright (c) 2005-2008 旭鸣软件
 * All rights reserved. 
 */
/*
 * File：Monitor.java
 * History:
 *       2009-12-11: Initially created, 汤垲�?.
 */
package com.tangkf.cache;

import java.util.Date;

/**
 * <p>
 * 缓存监视器接�?
 * </p>
 * @author 汤垲�?
 */
public interface IMonitor {
	
	/**
	 * 状�?�信息�?�交
	 * @author 汤垲�? 2009-12-11
	 * @param startTime 启动时间
	 * @param currentSize 当前数量
	 * @param accSucNum 成功访问次数
	 * @param accessNum 访问总次�?
	 * @param cleanNum 清理次数
	 */
	public void sendStateInfo(final Date startTime,final int currentSize,final int accSucNum,final int accessNum,final int cleanNum);
	
	/**
	 * 清理信息递交
	 * @author 汤垲�? 2009-12-11
	 * @param forNum 循环次数
	 * @param cleanSize 清理数量
	 * @param cleanTime 清理耗时 毫秒
	 * @param currentSize 清理后当前数�?
	 * @param cleanNum 清理次数
	 */
	public void sendCleanInfo(final int forNum,final int cleanSize,final int cleanTime,final int currentSize,final int cleanNum);
}
