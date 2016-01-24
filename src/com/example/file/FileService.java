package com.example.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

public class FileService {
	private Context context;
	// ͨ��������������ֵ��ʹ��set���׺��ԣ����Ƽ�
	public FileService(Context context) {
		this.context = context;
	}
	/**
	 * �����ļ����ֻ��Դ��洢��
	 * @param filename �ļ�����
	 * @param content �ļ�����
	 * */
	public void save(String filename, String content) throws Exception {
		// �õ����������
		// Context.MODE_PRIVATE���ļ�Ϊ˽��
		// Context��������һ�����ٵõ��ļ����������ķ���:openFileOuptput
		// �ڶ�������ָ���ļ��Ĳ���ģʽ��˽�в���ģʽ �����������ļ�ֻ�ܱ���Ӧ�÷��ʣ�����Ӧ���޷����ʸ��ļ����������˽��ģʽ�������ļ���д���ļ��е����ݻḲ��˽���ļ��е�����
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		// д������,content.getBytes() �ַ����� --> �ֽ�����
		outStream.write(content.getBytes());
		outStream.close();
	}
	/**
	 * �������ݵ�SD����
	 * */
	public void saveToSDCard(String filename, String content) throws Exception {
		// ʹ��Environment.getExternalStorageDirectory()��ȡ��Android��SD�ĸ�Ŀ¼
		File file = new File(Environment.getExternalStorageDirectory(), filename);
		// �õ��ļ����������ʹ�ã�outputStream�����ֽ�����
		FileOutputStream outStream = new FileOutputStream(file);
		// ʹ��getBytes()���ļ�������ת��Ϊ�ֽ�����
		outStream.write(content.getBytes());
		// �ر��ļ��������
		outStream.close();
	}
	/**
	 * ��ȡ�ļ�����
	 * @param filename �ļ�����
	 * @return �ļ�����
	 * 
	 * */
	public String read(String filename) throws Exception {
		// ʹ���ļ�����������Context�ṩ��һ���õ�����������ķ���
		FileInputStream inStream = context.openFileInput(filename);
		// ��ÿ�ζ��������ݶ�д���ڴ���
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 
		byte[] buffer = new byte[1024];
		int len = 0;	// �õ����ݳ���
		// inStream.read(buffer)��ȡ���������ݴ�С������������ݾͼ�����ȡ
		while((len = inStream.read(buffer)) != -1){
			// �����������ݶ�Ҫд���ڴ���
			outStream.write(buffer, 0, len);
		}
		// data�õ�outStream�е���������
		byte[] data = outStream.toByteArray();
		return new String(data);
	}
}
