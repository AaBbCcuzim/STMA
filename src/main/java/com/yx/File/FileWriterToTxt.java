package com.yx.File;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JTable;
import javax.swing.JTextArea;


public class FileWriterToTxt {
	
	public static void writeFile(JTextArea textarea,File f) throws IOException {
		FileOutputStream h = new FileOutputStream(f);
		OutputStreamWriter w = new OutputStreamWriter(h,"UTF-8");
			w.write(textarea.getText()+"\r\n");
			w.flush();
		w.close();
	}
	
	public static void writeFile(JTable table,File f) throws IOException {
		FileOutputStream h = new FileOutputStream(f);
		OutputStreamWriter w = new OutputStreamWriter(h,"UTF-8");
			w.flush();
		w.close();
	}
}
