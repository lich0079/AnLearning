/**
 * created on Feb 19, 2010 2:23:24 AM by lich0079@gmail.com
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
package nio;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lich0079@gmail.com
 * 
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        ByteBuffer echoBuffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss = ssc.socket();
        InetSocketAddress address = new InetSocketAddress(10000);
        ss.bind(address);

        SelectionKey keyMain = ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(keyMain);
        System.out.println("Going to listen on 10000");
        while (true) {
            int num = selector.select();
            System.out.println("num : " + num + "  keys " + selector.keys().size());
            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();

            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                System.out.println(key);
                if (key.isAcceptable()) {
                    // Accept the new connection
                    ServerSocketChannel ssc2 = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc2.accept();
                    sc.configureBlocking(false);

                    // Add the new connection to the selector
                    SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                    it.remove();

                    System.out.println("Got connection from " + sc);
                } else if (key.isReadable()) {
                    // Read the data
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer output = (ByteBuffer) key.attachment( );
                    Charset latin1 = Charset.forName("ISO-8859-1");
                    CharsetDecoder decoder = latin1.newDecoder();
                    CharsetEncoder encoder = latin1.newEncoder();
                    CharBuffer cb = decoder.decode(output);
                    System.out.print(cb.toString());
                    // InputStream is=new SocketInputStream(sc);
                    // DataInputStream dataInputStream=new DataInputStream(is);
                    // String a=dataInputStream.readUTF();
                    // System.out.println(a);
                    // Echo data
                    int bytesEchoed = 0;
//                    while (true) {
//                        echoBuffer.clear();
//                        int r = sc.read(echoBuffer);
//                        Charset latin1 = Charset.forName("ISO-8859-1");
//                        CharsetDecoder decoder = latin1.newDecoder();
//                        CharsetEncoder encoder = latin1.newEncoder();
//                        // 下面这句是对CharBuffer进行了写的操作 并执行了flip
//                        CharBuffer cb = decoder.decode(echoBuffer);
//                        System.out.print(cb.toString());
//
//                        if (r <= 0) {
//                            break;
//                        }
//
//                        echoBuffer.flip();
//                        
////                        CharBuffer cb1 = decoder.decode(buffer1);
////                        System.out.print(cb1.toString());
//                        // Charset GBK = Charset.forName("GBK");
//                        // CharsetDecoder decoderGBK = GBK.newDecoder();
//                        // int size = rep.getBytes().length;
//                        // ByteBuffer bb = ByteBuffer.allocate(size);
//                        // bb.put(rep.getBytes());
//                        // decoderGBK.decode(bb);
//                        // System.out.println(decoderGBK.decode(bb).toString());
//
//                        // CharBuffer cb22 = decoder.decode(bb);
//                        // System.out.println("------");
//                        // System.out.println(cb22.toString());
//                        
////                        sc.finishConnect();
//                        bytesEchoed += r;
//
//                    }
                    String rep = "HTTP/1.1 200 OK\r\n\r\nDate: Mon, 15 Sep 2003 21:06:50 GMT\r\n\r\nServer: Apache/2.0.40 (Red Hat Linux)\r\n\r\nLast-Modified: Tue, 15 Apr 2003 17:28:57 GMT\r\n\r\nConnection: close\r\n\r\nContent-Type: text/html; charset=ISO-8859-1\r\n\r\nContent-length: 107\r\n\r\n\r\naaaa";

                    byte[] data = rep.getBytes("ISO-8859-1");
                    ByteBuffer buffer1 = ByteBuffer.wrap(data);
                    System.out.println("---");
                    sc.write(buffer1);
                    sc.close();
                    System.out.println("Echoed " + bytesEchoed + " from " + sc);
                    
                    it.remove();
                }

            }

            // System.out.println( "going to clear" );
            // selectedKeys.clear();
            // System.out.println( "cleared" );
        }

    }
}

class SocketInputStream extends InputStream {
    final int BUFFER_SIZE = 8200;
    private ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    private SocketChannel channel;
    private boolean blocking = false;
    private boolean isClosed = false;
    private volatile boolean dataAvailable = false;

    SocketInputStream(SocketChannel channel) {
        this.channel = channel;
        buffer.limit(0);
    }

    public int available() {
        return buffer.remaining();
    }

    public void mark(int readlimit) {
        buffer.mark();
    }

    public boolean markSupported() {
        return true;
    }

    public void reset() {
        buffer.reset();
    }

    public synchronized int read() throws IOException {
        if (!checkAvailable(1)) {
            block(1);
        }
        return buffer.get();
    }

    private boolean checkAvailable(int nbyte) throws IOException {
        if (isClosed) {
            throw new ClosedChannelException();
        }
        return buffer.remaining() >= nbyte;
    }

    private int fill(int nbyte) throws IOException {
        int rem = nbyte;
        int read = 0;
        boolean eof = false;
        byte[] oldData = null;
        if (buffer.remaining() > 0) {
            // should rarely happen, so short-lived GC shouldn't hurt
            // as much as allocating a long-lived buffer for this
            oldData = new byte[buffer.remaining()];
            buffer.get(oldData);
        }
        buffer.clear();
        if (oldData != null) {
            buffer.put(oldData);
        }
        while (rem > 0) {
            int count = channel.read(buffer);
            if (count < 0) {
                eof = true;
                break;
            } else if (count == 0) {
                break;
            }
            read += count;
            rem -= count;
        }
        buffer.flip();
        return eof ? -1 : read;
    }

    synchronized boolean readAvailable() {
        if (blocking) {
            dataAvailable = true;
            notify();
        } else if (dataAvailable) {
        } else {
            int nr = 0;

            try {
                nr = fill(1);
            } catch (ClosedChannelException cce) {
                nr = -1;
            } catch (IOException iex) {
                nr = -1; // Can't handle this yet
            }
            if (nr < 0) {
                isClosed = true;
                notify();
                return false;
            } else if (nr == 0) {
            }
        }
        return true;
    }

    public int read(byte[] data) throws IOException {
        return read(data, 0, data.length);
    }

    public synchronized int read(byte[] data, int offset, int len) throws IOException {
        int olen = len;
        while (!checkAvailable(len)) {
            int avail = buffer.remaining();
            if (avail > 0) {
                buffer.get(data, offset, avail);
            }
            len -= avail;
            offset += avail;
            block(len);
        }
        buffer.get(data, offset, len);
        return olen;
    }

    private void block(int len) throws IOException {
        if (len <= 0) {
            return;
        }
        if (!dataAvailable) {
            blocking = true;
            try {
                wait(100);
            } catch (InterruptedException iex) {
            }
            blocking = false;
        }
        if (dataAvailable) {
            dataAvailable = false;
            if (fill(len) < 0) {
                isClosed = true;
            }
        }
    }
}
