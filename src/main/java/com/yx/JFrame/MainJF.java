package com.yx.JFrame;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.yx.File.FileReaderFromTxt;
import com.yx.File.FileWriterToTxt;
import com.yx.beans.Student;
import com.yx.functions.Sorts;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.RowSorter;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;

public class MainJF extends JFrame {

	private int num = 0; 
	private JPanel contentPane;
	private JFileChooser jfc;
	private File file;
	private File[] files;
	private List<Student> ar;
	private Vector<File> fs = new Vector<>();
	private JTable table;
	private JTextArea textarea;
	private JDialog dialog;
	private Object [][] object;
	private String [] columnNames = {"学号","姓名","性别","学院","成绩","年龄"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJF frame = new MainJF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJF() {
		setResizable(false);
		setTitle("Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 884, 36);
		contentPane.add(toolBar);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 34, 187, 604);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(186, 34, 698, 468);
		contentPane.add(scrollPane_1);
		
		
		JList list = new JList();
		list.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		list.setFixedCellHeight(30);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					 int index = list.getSelectedIndex();    //已选项的下标
                     Object obj = list.getModel().getElementAt(index);  //取出数据
                     try {
                    	 //if(!ar.isEmpty()) ar.clear();
						 ar = FileReaderFromTxt.loadDataSet(obj.toString());

						 object = new Object[ar.size()][6];
						 int cnt = 0;
						 for(Student s:ar) {
							 object[cnt][0] = s.getId();
							 object[cnt][1] = s.getName();
							 object[cnt][2] = s.getGender();
							 object[cnt][3] = s.getSchool();
							 object[cnt][4] = s.getScore();
							 object[cnt][5] = s.getAge();
							 cnt++;
						 }
						 TableModel tableModel = new DefaultTableModel(object, columnNames);
						 table = new JTable(object,columnNames);
						 RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
						 table.setRowSorter(rowSorter);
						 scrollPane_1.setViewportView(table);
						
					 } catch (FileNotFoundException e1) {
						 e1.printStackTrace();
					 }
				}
			}
		});
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(186, 501, 698, 137);
		contentPane.add(scrollPane_2);
		
				
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("打开文件");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
				} catch (Exception e1) {
					 e1.printStackTrace();
				}
				jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File("."));
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.setMultiSelectionEnabled(true);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
		        jfc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
		        
		        int result = jfc.showOpenDialog(contentPane);
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		            files =jfc.getSelectedFiles();
		            for(File f:files) {
		            	fs.add(f);
		            }
		            list.setListData(fs);
		        }
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_9 = new JMenu("导出");
		mnNewMenu.add(mnNewMenu_9);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("导出结果");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					UIManager.setLookAndFeel(lookAndFeel);
				} catch (Exception e1) {
					 e1.printStackTrace();
				}
				
				jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File("."));
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.setMultiSelectionEnabled(true);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
		        jfc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
		        
		        int result = jfc.showOpenDialog(contentPane);
		        
		        if (result == JFileChooser.APPROVE_OPTION) {
		            file =jfc.getSelectedFile();
		            try {
						FileWriterToTxt.writeFile(textarea,file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
		        }
			}
		});
		mnNewMenu_9.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("导出表格");
//		mntmNewMenuItem_6.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
//					UIManager.setLookAndFeel(lookAndFeel);
//				} catch (Exception e1) {
//					 e1.printStackTrace();
//				}
//				
//				jfc = new JFileChooser();
//				jfc.setCurrentDirectory(new File("."));
//				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//				jfc.setMultiSelectionEnabled(true);
//				jfc.addChoosableFileFilter(new FileNameExtensionFilter("zip(*.zip, *.rar)", "zip", "rar"));
//		        jfc.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
//		        
//		        int result = jfc.showOpenDialog(contentPane);
//		        
//		        if (result == JFileChooser.APPROVE_OPTION) {
//		            file =jfc.getSelectedFile();
//		            try {
//						FileWriterToTxt.writeFile(table,file);
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//		        }
//			}
//		});
		mnNewMenu_9.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_1 = new JMenu("编辑");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("属性");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("学号");
		mnNewMenu_2.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("姓名");
		mnNewMenu_2.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("升序");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Student> a3 = Sorts.sortUpByName(ar);
				 object = new Object[a3.size()][6];
				 int cnt = 0,k = 0;
				 for(Student s:a3) {
					 object[cnt][0] = s.getId();
					 object[cnt][1] = s.getName();
					 object[cnt][2] = s.getGender();
					 object[cnt][3] = s.getSchool();
					 object[cnt][4] = s.getScore();
					 object[cnt][5] = s.getAge();
					 cnt++;
				 }
				 TableModel tableModel = new DefaultTableModel(object, columnNames);
				 table = new JTable(object,columnNames);
				 RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
				 table.setRowSorter(rowSorter);
				 scrollPane_1.setViewportView(table);
				 
				 textarea = new JTextArea();
				 textarea.append("前十名学生信息：\r\n");
				 for(Student sx:a3) {
					 if(k==10) break;
					 textarea.append(">"+k+":"+sx.getId()+" "+sx.getName()+" "+sx.getGender()+" "+sx.getSchool()+" "+sx.getScore()+" "+sx.getAge()+"\r\n");
					 k++;
				 }
				 textarea.append("\r\n机电学院学生信息：\r\n");
				 for(Student sx:a3) {
					 if(sx.getSchool().equalsIgnoreCase("机电学院"))
					 	textarea.append(">"+sx.getId()+" "+sx.getName()+" "+sx.getGender()+" "+sx.getSchool()+" "+sx.getScore()+" "+sx.getAge()+"\r\n");
				 }
				 
				 scrollPane_2.setViewportView(textarea);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("降序");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Student> a4 = Sorts.sortDownByName(ar);
				 object = new Object[a4.size()][6];
				 int cnt = 0,k = 0;
				 for(Student s:a4) {
					 object[cnt][0] = s.getId();
					 object[cnt][1] = s.getName();
					 object[cnt][2] = s.getGender();
					 object[cnt][3] = s.getSchool();
					 object[cnt][4] = s.getScore();
					 object[cnt][5] = s.getAge();
					 cnt++;
				 }
				 TableModel tableModel = new DefaultTableModel(object, columnNames);
				 table = new JTable(object,columnNames);
				 RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
				 table.setRowSorter(rowSorter);
				 scrollPane_1.setViewportView(table);
				 
				 textarea = new JTextArea();
				 textarea.append("前十名学生信息：\r\n");
				 for(Student sx:a4) {
					 if(k==10) break;
					 textarea.append(">"+k+":"+sx.getId()+" "+sx.getName()+" "+sx.getGender()+" "+sx.getSchool()+" "+sx.getScore()+" "+sx.getAge()+"\r\n");
					 k++;
				 }
				 textarea.append("\r\n机电学院学生信息：\r\n");
				 for(Student sx:a4) {
					 if(sx.getSchool().equalsIgnoreCase("机电学院"))
					 	textarea.append(">"+sx.getId()+" "+sx.getName()+" "+sx.getGender()+" "+sx.getSchool()+" "+sx.getScore()+" "+sx.getAge()+"\r\n");
				 }
				 
				 scrollPane_2.setViewportView(textarea);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_5 = new JMenu("性别");
		mnNewMenu_2.add(mnNewMenu_5);
		
		JMenu mnNewMenu_6 = new JMenu("学院");
		mnNewMenu_2.add(mnNewMenu_6);
		
		JMenu mnNewMenu_7 = new JMenu("成绩");
		mnNewMenu_2.add(mnNewMenu_7);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("升序");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 List<Student> a1 = Sorts.sortUpByScore(ar);
				 object = new Object[a1.size()][6];
				 int cnt = 0,j1 = 0,j2 = 0,j3 = 0,j4 = 0;
				 for(Student s:a1) {
					 object[cnt][0] = s.getId();
					 object[cnt][1] = s.getName();
					 object[cnt][2] = s.getGender();
					 object[cnt][3] = s.getSchool();
					 object[cnt][4] = s.getScore();
					 object[cnt][5] = s.getAge();
					 cnt++;
					 if(s.getScore()<70) 
						 j1++;
					 else if(s.getScore()<80)
						 j2++;
					 else if(s.getScore()<85)
						 j3++;
					 else
						 j4++;
				 }
				 TableModel tableModel = new DefaultTableModel(object, columnNames);
				 table = new JTable(object,columnNames);
				 RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
				 table.setRowSorter(rowSorter);
				 scrollPane_1.setViewportView(table);
				 
				 textarea = new JTextArea();
				 textarea.append("统计结果\r\n");
				 textarea.append(">不及格人数："+j1+"\r\n");
				 textarea.append(">及格人数："+j2+"\r\n");
				 textarea.append(">良好人数："+j3+"\r\n");
				 textarea.append(">优秀人数："+j4+"\r\n");
				 scrollPane_2.setViewportView(textarea);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("降序");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Student> a2 = Sorts.sortDownByScore(ar);
				 object = new Object[a2.size()][6];
				 int cnt = 0,j1 = 0,j2 = 0,j3 = 0,j4 = 0;
				 for(Student s:a2) {
					 object[cnt][0] = s.getId();
					 object[cnt][1] = s.getName();
					 object[cnt][2] = s.getGender();
					 object[cnt][3] = s.getSchool();
					 object[cnt][4] = s.getScore();
					 object[cnt][5] = s.getAge();
					 cnt++;
					 if(s.getScore()<70) 
						 j1++;
					 else if(s.getScore()<80)
						 j2++;
					 else if(s.getScore()<85)
						 j3++;
					 else
						 j4++;
				 }
				 TableModel tableModel = new DefaultTableModel(object, columnNames);
				 table = new JTable(object,columnNames);
				 RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
				 table.setRowSorter(rowSorter);
				 scrollPane_1.setViewportView(table);
				 
				 textarea = new JTextArea();
				 textarea.append("统计结果\r\n");
				 textarea.append(">不及格人数："+j1+"\r\n");
				 textarea.append(">及格人数："+j2+"\r\n");
				 textarea.append(">良好人数："+j3+"\r\n");
				 textarea.append(">优秀人数："+j4+"\r\n");
				 scrollPane_2.setViewportView(textarea);
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_8 = new JMenu("年龄");
		mnNewMenu_2.add(mnNewMenu_8);
				
		JButton btnNewButton = new JButton("随机排列");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Collections.shuffle(ar);
				
				object = new Object[ar.size()][6];
				int cnt = 0;
				for(Student s:ar) {
					object[cnt][0] = s.getId();
					object[cnt][1] = s.getName();
					object[cnt][2] = s.getGender();
					object[cnt][3] = s.getSchool();
					object[cnt][4] = s.getScore();
					object[cnt][5] = s.getAge();
					cnt++;
				}
				TableModel tableModel = new DefaultTableModel(object, columnNames);
				table = new JTable(object,columnNames);
				RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
				table.setRowSorter(rowSorter);
				scrollPane_1.setViewportView(table);
			}
		});
		btnNewButton.setToolTipText("随机排序");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("统计(人数)");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					String inputContent = JOptionPane.showInputDialog(null,"输入数量:");
					num = Integer.parseInt(inputContent);
					
					int cnt = 0,j1 = 0,j2 = 0,j3 = 0,j4 = 0;
					textarea = new JTextArea();
					for(Student s:ar) {
						 if(cnt==num) break;
						 textarea.append(">"+cnt+":"+s.getId()+" "+s.getName()+" "+s.getGender()+" "+s.getSchool()+" "+s.getScore()+" "+s.getAge()+"\r\n");
						 cnt++;
						 if(s.getScore()<70) 
							 j1++;
						 else if(s.getScore()<80)
							 j2++;
						 else if(s.getScore()<85)
							 j3++;
						 else
							 j4++;
					 }
					 	 
					 textarea.append("统计结果\r\n");
					 textarea.append(">及格率："+(double)(1.0*j2/(j1+j2+j3+j4))+"\r\n");
					 textarea.append(">良好率："+(double)(1.0*j3/(j1+j2+j3+j4))+"\r\n");
					 textarea.append(">优秀率："+(double)(1.0*j4/(j1+j2+j3+j4))+"\r\n");
					 scrollPane_2.setViewportView(textarea);
			}
		});
		btnNewButton_1.setToolTipText("统计(人数)");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("统计(学院)");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String inputContent = JOptionPane.showInputDialog(null,"输入学院:");
				
				int cnt = 0,j1 = 0,j2 = 0,j3 = 0,j4 = 0,sum = 0;
				textarea = new JTextArea();
				textarea.append("统计结果\r\n");
				for(Student s:ar) {
					 if(cnt==10) break;
					 if(s.getSchool().equals(inputContent)){
						 textarea.append(">"+cnt+":"+s.getId()+" "+s.getName()+" "+s.getGender()+" "+s.getSchool()+" "+s.getScore()+" "+s.getAge()+"\r\n");
						 sum++;
					 }
					 cnt++;
				 }
				 	 
				 textarea.append("->"+inputContent+"人数："+sum+"\r\n");
				 scrollPane_2.setViewportView(textarea);
			}
		});
		btnNewButton_2.setToolTipText("统计(学院)");
		toolBar.add(btnNewButton_2);
	}
}
