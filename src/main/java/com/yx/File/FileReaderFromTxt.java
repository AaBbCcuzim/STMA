package com.yx.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.yx.beans.Student;

public class FileReaderFromTxt {
	
	public static List<Student> loadDataSet(String pathname) throws FileNotFoundException {
		File f =new File(pathname);
		Scanner sc =new Scanner(f,"UTF-8");
		String line=null;
		List<Student> arlist =new ArrayList<Student>();
		while(sc.hasNextLine()) {
			line=sc.nextLine();
			String[] flds=line.split(",");  //  \s+
			Student s=new Student(flds[0],flds[1],flds[2],flds[3],Integer.parseInt(flds[4]),Integer.parseInt(flds[5]));
			arlist.add(s);
		}
		return arlist;
	}
	
}
