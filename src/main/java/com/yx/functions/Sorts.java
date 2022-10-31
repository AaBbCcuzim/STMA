package com.yx.functions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.text.Collator;
import com.yx.beans.Student;

public class Sorts {
	
	public static List<Student> sortUpByScore(List<Student> arlist) {
		Collections.sort(arlist,(s1,s2)->s1.getScore()-s2.getScore());
		return arlist;
	}
	
	public static List<Student> sortDownByScore(List<Student> arlist) {
		Collections.sort(arlist,(s1,s2)->s2.getScore()-s1.getScore());
		return arlist;
	}
	
	public static List<Student> sortUpByName(List<Student> arlist) {
		Collections.sort(arlist, new Comparator<Student>(){

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
                return com.compare(o1.getName(), o2.getName());
			}
			
		});
		return arlist;
	}
	
	public static List<Student> sortDownByName(List<Student> arlist) {
		Collections.sort(arlist, new Comparator<Student>(){

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
                return com.compare(o2.getName(), o1.getName());
			}
			
		});
		return arlist;
	}
	
}
