package beans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UserImg {
	private String imgTitle ;
	private FileInputStream imgData;
	private FileOutputStream imgDataOut;
    private BufferedReader bufferedReader;
    private InputStream inputStream;
	
	 public void setImgTitle(String imgTitle) {
		 this.imgTitle = imgTitle;
	 }
	 public String getImgTitle() {
		 return imgTitle;
	 }
	 public void setImgData(FileInputStream imgData) {
		 this.imgData = imgData;
	 }
	 public FileInputStream getImgData() {
		 return imgData;
	 }
	 public void setImgDataOut(FileOutputStream imgDataOut) {
		 this.imgDataOut = imgDataOut;
	 }
	 public FileOutputStream getImgDataOut() {
		 return imgDataOut;
	 }
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
