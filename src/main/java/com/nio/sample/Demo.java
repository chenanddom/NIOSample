package com.nio.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description:一句话的功能说明
 * @Author: chendom
 * @Date: 2019/12/18 13:56
 * @Version 1.0
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("F:\\tmp\\info.txt","rw");
        //获取一个通道
        FileChannel fileChannel = aFile.getChannel();
        //生成缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
//        CharBuffer byteBuffer = CharBuffer.allocate(48);
        //将通道的数据读入缓冲区
        int byteRead = fileChannel.read(byteBuffer);
        while (byteRead!=-1){
            System.out.println("Read "+byteRead);
            //将缓冲区从读模式转成写模式
            byteBuffer.flip();

            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());
            }
            //清空缓冲区,使缓冲区准备好写入
            byteBuffer.clear();
            //将新一批的数据读入缓冲区
            byteRead=fileChannel.read(byteBuffer);
        }
        //关闭通道
        fileChannel.close();
    }
}
