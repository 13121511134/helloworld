package puzzle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class PuzzleTest {
//	@Test
	public void printRect() throws IOException{
		String fileName = System.getProperty("user.dir")+"\\src\\puzzle\\"+this.getClass().getSimpleName()+".java";
		fileName = URLDecoder.decode(fileName, "utf-8");
		System.out.println(fileName);
		FileInputStream fis = new FileInputStream(fileName);
		FileReader fr = new FileReader(fileName);
		String readLine=null;//中文读一半
		char[] buffer =new char[50];
		int readByte=0;
		while((readByte=fis.read())!=-1){
		System.out.print((char)readByte);
//		while((readByte=fr.read(buffer))!=-1){
//			System.out.print(new String(buffer,0,readByte));
		}
	}
	@Test
	public void nioTest() throws UnsupportedEncodingException{
		 RandomAccessFile aFile = null;
		 String fileName = System.getProperty("user.dir")+"\\src\\puzzle\\"+this.getClass().getSimpleName()+".java";
		fileName = URLDecoder.decode(fileName, "utf-8");
	        try{
	            aFile = new RandomAccessFile(fileName,"rw");
	            FileChannel fileChannel = aFile.getChannel();
	            ByteBuffer buf = ByteBuffer.allocate(1024);
	 
	            int bytesRead = fileChannel.read(buf);
	            System.out.println(bytesRead);
	 
	            while(bytesRead != -1)
	            {
	                buf.flip();
	                while(buf.hasRemaining())
	                {
	                    System.out.print((char)buf.get());
	                }
	 
	                buf.compact();
	                bytesRead = fileChannel.read(buf);
	            }
	        }catch (IOException e){
	            e.printStackTrace();
	        }
	}
}



