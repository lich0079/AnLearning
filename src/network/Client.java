/**
 * created on Feb 18, 2010 10:47:02 PM by lich0079@gmail.com
 *
 * Copyright 2001-2010 The Apache Software Foundation.
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
package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import junit.framework.TestCase;

/**
 * @author lich0079@gmail.com
 * 
 */
public class Client extends TestCase {
    public void test1() {
        try {
            InetAddress[] a = InetAddress.getAllByName("localhost");
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }

            //方便的构造byte数组的方法
            String str = "61.135.253.16";
            String[] ipStr = str.split("\\.");// 以"."拆分字符串
            byte[] ipBuf = new byte[4];
            for (int i = 0; i < 4; i++) {
                ipBuf[i] = (byte) (Integer.parseInt(ipStr[i]) & 0xFF);// 调整整数大小。
                // byte的数值范围为-128~127
            }
            InetAddress ia = InetAddress.getByAddress(ipBuf);
            System.out.println(ia.getHostName());//拿不到hostname

            ia = InetAddress.getByName("129.42.60.216");
            System.out.println(ia.getHostName() + " " + ia);//拿不到hostname

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
 // 得到本地ip的机器名
    public static String getHostName() {
            String s = "";

            try {
                    String s1 = "ipconfig /all";
                    Process process = Runtime.getRuntime().exec(s1);
                    BufferedReader bufferedreader = new BufferedReader(
                                    new InputStreamReader(process.getInputStream()));
                    String nextLine;
                    for (String line = bufferedreader.readLine(); line != null; line = nextLine) {
                            nextLine = bufferedreader.readLine();
                            if (line.indexOf("Host Name") <= 0) {
                                    continue;
                            }
                            int i = line.indexOf("Host Name") + 36;
                            s = line.substring(i);
                            break;
                    }
                    bufferedreader.close();
                    process.waitFor();
            } catch (Exception exception) {
                    s = "";
            }
            return s.trim();
    }
    
    //得到远程ip的机器名
    public static String getHostNameByNbtstat(String clientIP) {
            String s = "";
            String sb = clientIP.trim().substring(0, 3);
            //System.out.println("clientIP="+clientIP+"\t"+"截取字符串为："+sb);
            if(sb.equals("127")){
                    //System.out.println("是127.0.0.1");
                    s = getHostName();
            }
            else{
                    //System.out.println("不是本地ip");
                    try {
                            String s1 = "nbtstat -a "+clientIP;
                            Process process = Runtime.getRuntime().exec(s1);
                            BufferedReader bufferedreader = new BufferedReader(
                                            new InputStreamReader(process.getInputStream()));
                            String nextLine;
                            int y = 0;
                            int x = 0;
                            for (String line = bufferedreader.readLine(); line != null; line = nextLine) {
                                    nextLine = bufferedreader.readLine();
                                    
                                    //System.out.println("y= "+y+" nextLine="+nextLine);
                                    
                                    if(y==13){
                                            //System.out.println("此行：-----------------");
                                            //System.out.println(nextLine.indexOf("<00>")+"--------");
                                            s = (nextLine.substring(0, (nextLine.indexOf("<00>")))).toLowerCase();//截取字符串
                                    }
                                    y++;
                            }
                            bufferedreader.close();
                            process.waitFor();
                    } catch (Exception exception) {
                            s = "";
                    }
            }
            return s.trim();
    }

    public static void main(String[] args) {
            String name = getHostNameByNbtstat("61.135.253.16");//server是window可行
            System.out.println("name="+name);
    } 

}
