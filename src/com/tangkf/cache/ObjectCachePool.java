/*
 * Copyright (c) 2005-2008 旭鸣软件
 * All rights reserved. 
 */
/*
 * File：ObjectCachePool.java
 * History:
 *       2009-12-11: Initially created, 汤垲�?.
 */
package com.tangkf.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

/**
 * <p>
 * 对象缓冲�?
 * 1. 用来缓存对象
 * 2. 高�?�读取缓存对�?
 * 3. 可以自动管理维护缓存对象
 * 4. 自动统计缓存的命中率等相关参�?
 * </p>
 * @author 汤垲�?
 */
public class ObjectCachePool<K,V> {
	private boolean cleaning= false;	//正在清理
	
	private boolean isAutoClean	= true;	//是否自动清理
	
	private int maxSize		= 1000;	//�?大数�?
	private int minSize		= 100;	//�?小数�?
	private int criticalSize= 900;	//清理临界�?
	private int maxCleanSize= 100;	//每次清理数量
	
	private int maxFreeTime	= 900;	//�?大空闲时间，大于此空闲时间的对象将被列入被清理的范围
	private double minAccFrq= 0.01;	//对象的最小访问平�?,小于此�?�的对象将被列入被清理的范围 �?/小时
	private int minAccNum	= 2;	//对象允许的最小访问次数，小于此�?�的对象将被列入被清理范�?
	
	private int accessNum	= 0;	//总访问次�?
	private int accSucNum	= 0;	//访问成功次数
	private int cleanNum	= 0;	//清理次数
	
	private int stateTimes	= 60;	//间隔多少秒输出一次状态信�?
	
	public final Date startTime	= new Date();	//启动时间
	private IMonitor monitor	= null;			//系统监视�?
	private Timer systimer	= new Timer();
	private TimerTask stask	= null;
	
	private Map<K,V> pool	= null;
	private Map<K,OInfo> poolInfo	= null;
	
	/**
	 * 创建�?个具有自动管理功能的对象缓存�?
	 * @param maxSize 池的�?大容�?
	 * @param minSize 池的�?小容�?
	 */
	public ObjectCachePool(int maxSize,int minSize){
		this.maxSize	= maxSize;
		this.minSize	= minSize;
		this.criticalSize= maxSize-minSize;
		this.pool		= new HashMap<K, V>(criticalSize);
		this.poolInfo	= new HashMap<K, OInfo>(criticalSize);
		
		//创建默认的监视器
		this.monitor	= new IMonitor(){
			public void sendCleanInfo(int forNum, int cleanSize, int cleanTime,
					int currentSize, int cleanNum) {
				System.err.println( "--------------\r\n"+
						" 轮询次数�?"+forNum + "\r\n" +
						" 清除数量�?"+cleanSize + 
						" 清除耗时�?"+cleanTime + "\r\n" +
						" 使用空间�?"+currentSize + "/" + getMaxSize() + "\r\n" +
						" 清理次数�?"+cleanNum + "\r\n"
						+"--------------\r\n");
			}
			public void sendStateInfo(Date startTime, int currentSize,
					int accSucNum, int accessNum, int cleanNum) {
				System.err.println("--------------\r\n"+
						" 访问次数�?"+accessNum + "\r\n" +
						" 成功次数�?"+accSucNum + "\r\n" +
						" 使用空间�?"+currentSize + "/" + getMaxSize() + "\r\n" +
						" 清理次数�?"+cleanNum+"\r\n" +
						"--------------\r\n");
			}
		};
		
		//创建状�?�监视任�?
		this.stask		= new TimerTask(){
			public void run() {
				if(monitor!=null){
					monitor.sendStateInfo(startTime, pool.size(), accSucNum, accessNum, cleanNum);
				}
			}
		};
	}
	
	/**
	 * 从缓存池中返回一个对�?
	 * @author 汤垲�? 2009-12-11
	 * @param key
	 * @return
	 */
	public V get(Object key){
		this.addAccNum();
		OInfo oi	= this.poolInfo.get(key);
		V o	= this.pool.get(key);
		if(oi!=null && o!=null){
			oi.addAccessNum();
			oi.setLastInTime(new Date());
			oi.setRemove(false);	//重置删除标记
			this.addAccSuc();
		}else{
			o	= null;
		}
		return o;
	}
	
	/**
	 * 访问次数增加�?�?
	 * @author 汤垲�? 2009-12-14
	 */
	private void addAccNum(){
		if(this.accessNum>=Integer.MAX_VALUE){
			this.accessNum	= 1;
		}
		this.accessNum++;
	}
	
	/**
	 * 成功访问次数增加�?�?
	 * @author 汤垲�? 2009-12-14
	 */
	private void addAccSuc(){
		if(this.accSucNum>=Integer.MAX_VALUE){
			this.accSucNum	= 1;
		}
		this.accSucNum++;
	}
	
	/**
	 * 主动删除�?个缓存对象，删除的对象不会立即被清除，二是被列入清理状�??
	 * @author 汤垲�? 2009-12-14
	 * @param key
	 * @return
	 */
	public V remove(K key){
		OInfo oi	= this.poolInfo.get(key);
		V o	= this.pool.get(key);
		if(oi!=null && o!=null){
			oi.setRemove(true);	//打上删除标记
		}else{
			o	= null;
		}
		return o;
	}
	
	/**
	 * 将对象放入缓存池�?
	 * @author 汤垲�? 2009-12-11
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key,V value){
		if(this.isAutoClean && !this.cleaning && pool.size()>=this.getCriticalSize()){
		//大于临界�? & 自动清理 & 没有处在清理�?
			clean();
		}
		if(key!=null && value!=null){
			OInfo oi	= new OInfo();
			this.poolInfo.put(key, oi);
			return this.pool.put(key, value);
		}
		return null;
	}
	
	/**
	 * 启动监视�?
	 * @author 汤垲�? 2009-12-11
	 */
	public void startMonitor(){
		systimer.schedule(stask, this.stateTimes*1000, this.stateTimes*1000);
	}
	
	/**
	 * 停止监视�?
	 * @author 汤垲�? 2009-12-11
	 */
	public void stopMonitor(){
		systimer.cancel();
	}
	
	/**
	 * 设置系统监视�?
	 * @param monitor
	 */
	public void setMonitor(IMonitor monitor){
		this.monitor	= monitor;
	}
	
	/**
	 * 执行清理任务，始终不会对工作区进行迭代操�?
	 */
	public synchronized void clean(){
		cleaning	= true;
		Timer timer	= new Timer();
		TimerTask task	= new TimerTask(){
			public void run() {
				try {
					cleanNum++;
					int forNum = 0,cleanSize = 0,ctime = 0;
					long a	= System.currentTimeMillis();
					
					//对访问信息对象进行迭代操�?
					Iterator<Entry<K,OInfo>> it	= poolInfo.entrySet().iterator();
					while(it.hasNext()){
						Entry<K,OInfo> en	= it.next();
						Object key	= en.getKey();
						OInfo oi	= en.getValue();
						//删除标记 or 空闲时间超限 or 访问频率过低 or 总访问次数过�? 都将被清除出缓存
						if(oi.isRemove() || oi.getFreeTime()>maxFreeTime || oi.getAccFrequency()<minAccFrq || oi.getAccessNum()<minAccNum){
							it.remove();
							pool.remove(key);	//始终不迭代工作区
							cleanSize++;
							if(cleanSize>maxCleanSize) break;
						}
						forNum++;
					}
					long b	= System.currentTimeMillis();
					ctime	= (int)(b-a);
					
					if(monitor!=null){
						monitor.sendCleanInfo(forNum, cleanSize, ctime, pool.size(),cleanNum);
					}
					
				} catch (RuntimeException e) {
					e.printStackTrace();
				} finally{
					//清理工作结束，清理状态归�?
					cleaning	= false;
				}
			}
		};
		timer.schedule(task, 3);	//执行清理工作
	}

	/**
	 * 返回是否自动清理属�??
	 * @return 属�?�isAutoClean的�??.
	 */
	public boolean isAutoClean() {
		return this.isAutoClean;
	}

	/**
	 * 设置是否自动清理
	 * @param isAutoClean 属�?�isAutoClean.
	 */
	public void setAutoClean(boolean isAutoClean) {
		this.isAutoClean = isAutoClean;
	}

	/**
	 * 返回缓存池最大空�?
	 * @return 属�?�maxSize的�??.
	 */
	public int getMaxSize() {
		return this.maxSize;
	}

	/**
	 * 设置缓存池最大空�?
	 * @param maxSize 属�?�maxSize.
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 返回�?小空�?
	 * @return 属�?�minSize的�??.
	 */
	public int getMinSize() {
		return this.minSize;
	}

	/**
	 * 设置�?小空�?
	 * @param minSize 属�?�minSize.
	 */
	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	/**
	 * 返回临界点尺�?
	 * @return 属�?�criticalSize的�??.
	 */
	public int getCriticalSize() {
		return this.criticalSize;
	}

	/**
	 * 设置临界点尺�?
	 * @param criticalSize 属�?�criticalSize.
	 */
	public void setCriticalSize(int criticalSize) {
		this.criticalSize = criticalSize;
	}


	/**
	 * 每次清理数量
	 * @return 属�?�maxCleanSize的�??.
	 */
	public int getMaxCleanSize() {
		return this.maxCleanSize;
	}

	/**
	 * 每次清理数量
	 * @param maxCleanSize 属�?�maxCleanSize.
	 */
	public void setMaxCleanSize(int maxCleanSize) {
		this.maxCleanSize = maxCleanSize;
	}

	/**
	 * �?大空闲时间，大于此空闲时间的对象将被列入被清理的范围
	 * @return 属�?�maxFreeTime的�??.
	 */
	public int getMaxFreeTime() {
		return this.maxFreeTime;
	}

	/**
	 * �?大空闲时间，大于此空闲时间的对象将被列入被清理的范围
	 * @param maxFreeTime 属�?�maxFreeTime.
	 */
	public void setMaxFreeTime(int maxFreeTime) {
		this.maxFreeTime = maxFreeTime;
	}

	/**
	 * 对象的最小访问平�?,小于此�?�的对象将被列入被清理的范围  �?/小时
	 * @return 属�?�minAccFrq的�??.
	 */
	public double getMinAccFrq() {
		return this.minAccFrq;
	}

	/**
	 * 对象的最小访问平�?,小于此�?�的对象将被列入被清理的范围  �?/小时
	 * @param minAccFrq 属�?�minAccFrq.
	 */
	public void setMinAccFrq(double minAccFrq) {
		this.minAccFrq = minAccFrq;
	}

	/**
	 * 对象允许的最小访问次数，小于此�?�的对象将被列入被清理范�?
	 * @return 属�?�minAccNum的�??.
	 */
	public int getMinAccNum() {
		return this.minAccNum;
	}

	/**
	 * 对象允许的最小访问次数，小于此�?�的对象将被列入被清理范�?
	 * @param minAccNum 属�?�minAccNum.
	 */
	public void setMinAccNum(int minAccNum) {
		this.minAccNum = minAccNum;
	}

	/**
	 * 总访问次�?
	 * @return 属�?�accessNum的�??.
	 */
	public int getAccessNum() {
		return this.accessNum;
	}

	/**
	 * 访问成功次数
	 * @return 属�?�accSucNum的�??.
	 */
	public int getAccSucNum() {
		return this.accSucNum;
	}
	
	/**
	 * 间隔多少秒输出一次状态信�?
	 * @return 属�?�stateTimes的�??.
	 */
	public int getStateTimes() {
		return this.stateTimes;
	}

	/**
	 * 间隔多少秒输出一次状态信�?
	 * @param stateTimes 属�?�stateTimes.
	 */
	public void setStateTimes(int stateTimes) {
		this.stateTimes = stateTimes;
	}
	
	/**
	 * 记录对象访问信息
	 * @author 汤垲�?
	 */
	class OInfo{
		//首次访问时间
		public Date firstInTime;
		//�?近访问时�?
		public Date lastInTime;
		//访问计数
		public int accessNum	= 1;
		
		//是否被删�?
		public boolean isRemove	= false;
		
		public OInfo(){
			Date now	= new Date();
			firstInTime	= now;
			lastInTime	= now;
			isRemove	= false;
		}
		
		/**
		 * 返回当前时间与最近访问时间的时间差：毫秒
		 */
		public long getFreeTime(){
			return System.currentTimeMillis()-this.lastInTime.getTime();
		}
		
		/**
		 * 返回入缓存时间长短：毫秒
		 */
		public long getCacheTime(){
			return this.lastInTime.getTime()-this.firstInTime.getTime();
		}
		
		/**
		 * 返回访问频率 �?/小时
		 */
		public double getAccFrequency(){
			long ctime	= this.getCacheTime();
			if(ctime<1) ctime	= 1; 
			return (double)this.accessNum/(double)(ctime/3600000);
		}

		/**
		 * 返回首次访问时间
		 */
		public Date getFirstInTime() {
			return this.firstInTime;
		}
		
		public void addAccessNum(){
			if(this.accessNum>=Integer.MAX_VALUE){
				this.accessNum	= 1;
			}else{
				this.accessNum++;
			}
		}

		/**
		 * 返回访问次数
		 */
		public int getAccessNum() {
			return this.accessNum;
		}

		/**
		 * 设置�?近访问时�?
		 */
		public void setLastInTime(Date lastInTime) {
			this.lastInTime = lastInTime;
		}

		/**
		 * @return 属�?�isRemove的�??.
		 */
		public boolean isRemove() {
			return this.isRemove;
		}

		/**
		 * @param isRemove 属�?�isRemove.
		 */
		public void setRemove(boolean isRemove) {
			this.isRemove = isRemove;
		}
	}
}
