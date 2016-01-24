package com.example.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

/**
 * 文件操作类
 * */
public class FileService {
	private Context context;
	// 通过构造器给它赋值，使用set容易忽略，不推荐
	public FileService(Context context) {
		this.context = context;
	}
	/**
	 * 保存文件到手机自带存储器
	 * @param filename 文件名称
	 * @param content 文件内容
	 * */
	public void save(String filename, String content) throws Exception {
		// 得到输出流对象
		// Context.MODE_PRIVATE让文件为私有
		// Context给了我们一个快速得到文件输出流对象的方法:openFileOuptput
		// 第二个参数指定文件的操作模式：私有操作模式 创建出来的文件只能被本应用访问，其它应用无法访问该文件，另外采用私有模式创建的文件，写入文件中的内容会覆盖私有文件中的内容
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		// 写入数据,content.getBytes() 字符数据 --> 字节数据
		outStream.write(content.getBytes());
		outStream.close();
	}
	/**
	 * 保存数据到SD卡上
	 * */
	public void saveToSDCard(String filename, String content) throws Exception {
		// 使用Environment.getExternalStorageDirectory()获取出Android的SD的根目录
		File file = new File(Environment.getExternalStorageDirectory(), filename);
		// 得到文件的输出流，使用：outputStream传入字节数据
		FileOutputStream outStream = new FileOutputStream(file);
		// 使用getBytes()把文件的内容转换为字节数据
		outStream.write(content.getBytes());
		// 关闭文件的输出流
		outStream.close();
	}
	/**
	 * 读取文件内容
	 * @param filename 文件名称
	 * @return 文件内容
	 * 
	 * */
	public String read(String filename) throws Exception {
		// 使用文件的输入流，Context提供了一个得到输入流对象的方法
		FileInputStream inStream = context.openFileInput(filename);
		// 把每次读出的数据都写到内存中
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 
		byte[] buffer = new byte[1024];
		int len = 0;	// 得到数据长度
		// inStream.read(buffer)获取读到的数据大小，如果还有数据就继续读取
		while((len = inStream.read(buffer)) != -1){
			// 读到多少数据都要写到内存中
			outStream.write(buffer, 0, len);
		}
		// data得到outStream中的所有数据
		byte[] data = outStream.toByteArray();
		return new String(data);
	}
}
