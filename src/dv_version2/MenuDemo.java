/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

//import org.gephi.data.attributes.api.AttributeTable;
//import org.gephi.data.attributes.api.AttributeColumn;
//import org.gephi.data.attributes.api.AttributeModel;
//import org.gephi.data.attributes.api.AttributeOrigin;
//import org.gephi.data.attributes.api.AttributeRow;
//import org.gephi.data.attributes.api.AttributeType;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphModel;

import org.gephi.graph.api.Node;
import org.gephi.statistics.spi.Statistics;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.Progress;
import org.gephi.utils.progress.ProgressTicket;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.openide.util.NbBundle;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.gephi.preview.api.*;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;

import org.gephi.statistics.plugin.Degree;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.types.EdgeColor;

/*
import org.gephi.ranking.api.Ranking;
import org.gephi.ranking.api.RankingController;
import org.gephi.ranking.api.Transformer;
import org.gephi.ranking.plugin.transformer.AbstractColorTransformer;
import org.gephi.ranking.plugin.transformer.AbstractSizeTransformer;
*/
import org.gephi.appearance.plugin.RankingElementColorTransformer;
import org.gephi.appearance.plugin.RankingLabelSizeTransformer;
import org.gephi.appearance.plugin.RankingNodeSizeTransformer;

import org.gephi.statistics.plugin.GraphDistance;
import org.gephi.filters.api.FilterController;
import org.gephi.graph.api.UndirectedGraph;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import org.apache.commons.codec.binary.Base64;
import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.appearance.api.Function;
import org.gephi.filters.api.Query;
import org.gephi.filters.plugin.graph.EgoBuilder;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.GraphView;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;

import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;

import org.openide.util.Lookup;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.spi.PreviewMouseListener;

import dv_version2.plugins.preview.NodeSearch;
import dv_version2.plugins.preview.NodeSearchPanel;
import dv_version2.plugins.preview.PreviewSketch1;

import javax.swing.JSlider;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JScrollPane;
import dv_version2.plugins.preview.PreviewSketch1;
import java.awt.BorderLayout;
import org.gephi.layout.api.LayoutController;

public class MenuDemo {
	// *********************************************************************************************************************************

	private Frame frame; // 整体框架

	// private Frame statisticFrame;
	private JPanel statisticPanel = new JPanel(); // 统计显示窗
	private MenuBar menubar; // 菜单栏，后续进行补充和完善
	private Menu menu; // 菜单
	private MenuItem menuitem; // 菜单项
	private FileDialog filedialog;
	private static String filedir; // 文件对话框和文件路径

	JPanel statistic = new JPanel(); // 数据统计和布局算法执行的面板
	JPanel excute = new JPanel();

	JPanel FirLOJp = new JPanel();
	JPanel FirLOJp0 = new JPanel();
	JPanel FirLOJp1 = new JPanel();
	JPanel FirLOJp2 = new JPanel();
	JPanel FirLOJp3 = new JPanel();

	JPanel SecLOJp = new JPanel();
	JPanel SecLOJp0 = new JPanel();
	JPanel SecLOJp1 = new JPanel();
	JPanel SecLOJp2 = new JPanel();
	JPanel SecLOJp3 = new JPanel();
	JPanel SecLOJp4 = new JPanel();

	JPanel ThiLOJp = new JPanel();
	JPanel ThiLOJp0 = new JPanel();
	JPanel ThiLOJp1 = new JPanel();
	JPanel ThiLOJp2 = new JPanel();
	JPanel ThiLOJp3 = new JPanel();
	JPanel ThiLOJp4 = new JPanel();

	JLabel label1 = new JLabel("斥力强度:");
	JLabel label2 = new JLabel("中心引力:");
	JLabel label4 = new JLabel("最佳距离:");
	JLabel label5 = new JLabel("步比率:");
	JLabel label7 = new JLabel("重力:");
	JLabel label8 = new JLabel("速度:");
	JLabel label9 = new JLabel("面积:");

	JSlider slider1 = new JSlider(2000, 20000, 12000); // 用指定的最小值、最大值和初始值创建一个水平滑块
	JSlider slider2 = new JSlider(0, 300, 100);

	JSlider slider4 = new JSlider(0, 600, 200);
	JSlider slider5 = new JSlider(0, 100, 80);

	JSlider slider7 = new JSlider(0, 50, 10);
	JSlider slider8 = new JSlider(0, 10, 3);
	JSlider slider9 = new JSlider(10000, 50000, 30000);

	JScrollBar scrollbar = new JScrollBar(); // 创建具有下列初始值的垂直滚动条： minimum = 0
												// maximum = 100 value = 0
												// extent = 10

	static JTabbedPane workTabbedPane = new JTabbedPane(); // 创建一个具有默认的
															// JTabbedPane.TOP
															// 选项卡布局的空
															// TabbedPane，此为工作区
															// (一个组件，它允许用户通过单击具有给定标题和/或图标的选项卡，在一组组件之间进行切换)

	// 搜索面板
	JPanel searchPanel = new JPanel();
	private JLabel searchLabel = new JLabel("search>>");
	private JTextField searchField = new JTextField(null);
	private JButton go = new JButton("Go");

	JSplitPane downSplitPane = new JSplitPane(); // 创建一个配置为将其子组件水平排列、无连续布局、为组件使用两个按钮的新
													// JSplitPane(用于分隔两个（只能两个）Component)
	JSplitPane leftSplitPane = new JSplitPane();// 自定义工作区间
	// JScrollPane leftSplitPane =new JScrollPane();
	// leftSplitPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);

	private JButton ForceAtlas2 = new JButton("ForceAtlas2");
	private JButton Yifanhu = new JButton("Yifanhu");
	private JButton FR = new JButton("FR"); // 定义布局算法执行

	private JButton ego = new JButton("EGO");

	private JButton Direct = new JButton("Direct");
	private JButton unDirect = new JButton("unDirect"); // 有向图和无向图数据统计
	private Double gravity;
	private Double repulseStrength;
	private Double speed;
	private Float area;
	private Float optimalDistance;
	private Float stepRatio;
	static SingletonObject singletonObject;
	private JPanel jpanel;

	// *********************************************************************************************************************************
	public MenuDemo() {

		// 斥力强度、最佳距离、步比率、重力、速度和面积的初始值
		repulseStrength = 12000d;

		optimalDistance = 200f;
		stepRatio = 80f;

		gravity = 10d;
		speed = 3d;
		area = 30000f;

		
		slider1.setMajorTickSpacing(5000); // 设置主刻度标记的间隔
		slider1.setMinorTickSpacing(1000); // 设置次刻度标记的间隔
		slider1.setPaintTicks(true); // 在滑块上绘制滑道
		slider1.setPaintLabels(true); // 在滑块上绘制标签
		
		// 添加 ChangeListener 到滑块
		slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					gravity = (double) slider2.getValue();
					repulseStrength = (double) slider1.getValue();
					new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);
					jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());
					workTabbedPane.add("ForceAtlasLayout", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("ForceAtlasLayout").getJPanel());

				}
			}
		});

		
		slider2.setMajorTickSpacing(50);
		slider2.setMinorTickSpacing(10);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		
		slider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					gravity = (double) slider2.getValue();
					repulseStrength = (double) slider1.getValue();
					new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);
					jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());
					workTabbedPane.add("ForceAtlasLayout", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("ForceAtlasLayout").getJPanel());
				}
			}
		});

		
		slider4.setMajorTickSpacing(100);
		slider4.setMinorTickSpacing(20);
		slider4.setPaintTicks(true);
		slider4.setPaintLabels(true);
		
		slider4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					optimalDistance = (float) slider4.getValue();
					stepRatio = (float) slider5.getValue();
					new AlgoModule().yiFanHuLayout(singletonObject, optimalDistance, stepRatio);
					jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());

					workTabbedPane.add("YifanHuLayout", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("YifanHuLayout").getJPanel());
				}
			}
		});

		
		slider5.setMajorTickSpacing(20);
		slider5.setMinorTickSpacing(5);
		slider5.setPaintTicks(true);
		slider5.setPaintLabels(true);
		
		slider5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					optimalDistance = (float) slider4.getValue();
					stepRatio = (float) slider5.getValue();
					new AlgoModule().yiFanHuLayout(singletonObject, optimalDistance, stepRatio);
					jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());
					workTabbedPane.add("YifanHuLayout", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("YifanHuLayout").getJPanel());
				}
			}
		});

		
		slider7.setMajorTickSpacing(10);
		slider7.setMinorTickSpacing(2);
		slider7.setPaintTicks(true);
		slider7.setPaintLabels(true);
		
		slider7.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					gravity = (double) slider7.getValue();
					speed = (double) slider8.getValue();
					area = (float) slider9.getValue();
					new AlgoModule().FRLayout(singletonObject, gravity, speed, area);
					jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());
					workTabbedPane.add("FruchtermanReingold", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("FruchtermanReingold").getJPanel());
				}
			}
		});

		
		slider8.setMajorTickSpacing(2);
		slider8.setMinorTickSpacing(1);
		slider8.setPaintTicks(true);
		slider8.setPaintLabels(true);
		
		slider8.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					gravity = (double) slider7.getValue();
					speed = (double) slider8.getValue();
					area = (float) slider9.getValue();
					new AlgoModule().FRLayout(singletonObject, gravity, speed, area);
					jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());
					workTabbedPane.add("FruchtermanReingold", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("FruchtermanReingold").getJPanel());
				}
			}
		});

		
		slider9.setMajorTickSpacing(5000);
		// slider9.setMinorTickSpacing(1000);
		slider9.setPaintTicks(true);
		slider9.setPaintLabels(true);
		
		slider9.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				if (source.getValueIsAdjusting() != true) {
					gravity = (double) slider7.getValue();
					speed = (double) slider8.getValue();
					area = (float) slider9.getValue();
					new AlgoModule().FRLayout(singletonObject, gravity, speed, area);
					JPanel jpanel = new PreviewModule(singletonObject).display();
					workTabbedPane.remove(workTabbedPane.getSelectedComponent());
					workTabbedPane.add("FruchtermanReingold", jpanel);
					workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
							new Label_closing("FruchtermanReingold").getJPanel());
				}
			}
		});

		frameInit();
	}

	private void frameInit() {
		
		frame = new Frame("Data Visualization Ver0.1");
		frame.setBounds(200, 200, 600, 500);
		frame.setLayout(new BorderLayout()); // 边框布局
		
		menubar = new MenuBar();
		menu = new Menu("File");
		menuitem = new MenuItem("fileload");
		filedialog = new FileDialog(frame, "fileload", FileDialog.LOAD);
		frame.setMenuBar(menubar);
		menu.add(menuitem);
		menubar.add(menu);

		Menu editMenu = new Menu("Edit");
		menubar.add(editMenu);
		
		Menu viewMenu = new Menu("WorkSpace");
		menubar.add(viewMenu);

		Menu sourceMenu = new Menu("Tools");
		menubar.add(sourceMenu);
		
		Menu refactorMenu = new Menu("Window");
		menubar.add(refactorMenu);
		
		Menu helpMenu = new Menu("Help");
		menubar.add(helpMenu);

		myEvent();
		buttonEvent();
		// SliderEvent();
		setLayout();
		
		frame.setVisible(true);
	}

	private void buttonEvent() {
		ForceAtlas2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);
				jpanel = new PreviewModule(singletonObject).display();
				workTabbedPane.remove(workTabbedPane.getSelectedComponent());
				workTabbedPane.add("ForceAtlasLayout", jpanel);
				workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
						new Label_closing("ForceAtlas2").getJPanel());
			}
		});

		Yifanhu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AlgoModule().yiFanHuLayout(singletonObject, optimalDistance, stepRatio);
				jpanel = new PreviewModule(singletonObject).display();
				workTabbedPane.remove(workTabbedPane.getSelectedComponent());
				workTabbedPane.add("YifanHuLayout", jpanel);
				workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
						new Label_closing("YifanHuLayout").getJPanel());
			}
		});

		FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AlgoModule().FRLayout(singletonObject, gravity, speed, area);
				jpanel = new PreviewModule(singletonObject).display();
				workTabbedPane.remove(workTabbedPane.getSelectedComponent());
				workTabbedPane.add("FruchtermanReingold", jpanel);
				jpanel.updateUI();
				workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
						new Label_closing("FR").getJPanel());
			}
		});


		// 监听文本框，限制搜索面板只能输入数字
		searchField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();

				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 关键，屏蔽掉非法输入
				}
			}
		});

		// 监听搜索按钮
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = searchField.getText().trim(); // 获取文本框的输入内容（去除两端空格）
				try {
					int nodeId = Integer.valueOf(text).intValue();
					new NodeSearch().nodeSearch(singletonObject, nodeId);
				} catch (RuntimeException evt) {
					javax.swing.JOptionPane.showMessageDialog(null,"请输入数字！！！","提示",
							javax.swing.JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});


		/*
		 * ego.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { String
		 * s=JOptionPane.showInputDialog("Enter the ID of the ego point.");
		 * JOptionPane.showMessageDialog(null,"You want to watch"
		 * +s,"number",JOptionPane.INFORMATION_MESSAGE); temp=new
		 * new_demo().script(filedir,layoutMethod,"unDirect",Integer.parseInt(s)
		 * ,repulseStrength,gravity,speed,area,optimalDistance,stepRatio);
		 * //调用布局算法返回生成的布局图 workTabbedPane.add("EGO",temp); //将统计信息面板返回 } });
		 */

	}

	/*
	 * private void SliderEvent(){ ChangeListener listener; listener=new
	 * ChangeListener(){ public void stateChanged(ChangeEvent event){
	 * slider1=(JSlider) event.getSource(); //获得滑动条当前值。。。 } };
	 * 
	 * }
	 */
	// private void SliderEvent(){
	// ChangeListener listener;
	// listener=new ChangeListener(){
	// public void stateChanged(ChangeEvent event){
	// JSlider source=(JSlider) event.getSource();
	// //获得滑动条当前值。。。
	// if (source.getValueIsAdjusting() != true){
	// gravity = (double)slider2.getValue();
	// repulseStrength = (double)slider1.getValue();
	// layoutMethod=new String("ForceAtlasLayout");
	// temp=new
	// new_demo().script(filedir,layoutMethod,"unKnown",-1,repulseStrength,gravity,speed,area,optimalDistance,stepRatio);
	// //调用布局算法返回生成的布局图
	// workTabbedPane.add("ForceAtlasLayout",temp);
	// }
	// }
	// };
	// }

	private void myEvent() {
		
		menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filedialog.setVisible(true);
				filedir = filedialog.getDirectory() + filedialog.getFile();
				System.out.println(filedir);
				singletonObject = new SingletonObject();
				singletonObject.setFileName(filedir);
				new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);
				jpanel = new PreviewModule(singletonObject).display();
				workTabbedPane.remove(workTabbedPane.getSelectedComponent());
				workTabbedPane.add("ForceAtlas", jpanel);

				workTabbedPane.setTabComponentAt(workTabbedPane.indexOfComponent(jpanel),
						new Label_closing("ForceAtlas").getJPanel());
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public String getPath() {
		return filedir;
	}

	public static void main(String[] args) {
		
		// 读取文件，获取各个控制器和模型。
		MenuDemo menuTest = new MenuDemo();
		// singletonObject = new SingletonObject();

	}

	public void setLayout() {
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new java.awt.GridLayout());

		leftSplitPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		leftSplitPane.setResizeWeight(0.1);

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new java.awt.GridLayout());
		JTabbedPane leftTabbedPane = new JTabbedPane();

		FirLOJp0.setLayout(new BorderLayout());
		FirLOJp0.add(ForceAtlas2, BorderLayout.WEST);
		FirLOJp1.setLayout(new BorderLayout());
		FirLOJp1.add(label1, BorderLayout.WEST);
		FirLOJp1.add(slider1, BorderLayout.CENTER);
		FirLOJp2.setLayout(new BorderLayout());
		FirLOJp2.add(label2, BorderLayout.WEST);
		FirLOJp2.add(slider2, BorderLayout.CENTER);
		FirLOJp3.setLayout(new BorderLayout());
		// FirLOJp3.add(sure1,BorderLayout.WEST);

		FirLOJp.setLayout(new BoxLayout(FirLOJp, BoxLayout.Y_AXIS));
		FirLOJp.add(FirLOJp0);
		FirLOJp.add(FirLOJp1);
		FirLOJp.add(FirLOJp2);
		FirLOJp.add(FirLOJp3);

		SecLOJp0.setLayout(new BorderLayout());
		SecLOJp0.add(Yifanhu, BorderLayout.WEST);
		SecLOJp1.setLayout(new BorderLayout());
		SecLOJp1.add(label4, BorderLayout.WEST);
		SecLOJp1.add(slider4, BorderLayout.CENTER);
		SecLOJp2.setLayout(new BorderLayout());
		SecLOJp2.add(label5, BorderLayout.WEST);
		SecLOJp2.add(slider5, BorderLayout.CENTER);
		SecLOJp3.setLayout(new BorderLayout());
		SecLOJp4.setLayout(new BorderLayout());
		// SecLOJp4.add(sure2,BorderLayout.WEST);

		SecLOJp.setLayout(new BoxLayout(SecLOJp, BoxLayout.Y_AXIS));
		SecLOJp.add(SecLOJp0);
		SecLOJp.add(SecLOJp1);
		SecLOJp.add(SecLOJp2);
		SecLOJp.add(SecLOJp3);
		SecLOJp.add(SecLOJp4);

		ThiLOJp0.setLayout(new BorderLayout());
		ThiLOJp0.add(FR, BorderLayout.WEST);
		ThiLOJp1.setLayout(new BorderLayout());
		ThiLOJp1.add(label7, BorderLayout.WEST);
		ThiLOJp1.add(slider7, BorderLayout.CENTER);
		ThiLOJp2.setLayout(new BorderLayout());
		ThiLOJp2.add(label8, BorderLayout.WEST);
		ThiLOJp2.add(slider8, BorderLayout.CENTER);
		ThiLOJp3.setLayout(new BorderLayout());
		ThiLOJp3.add(label9, BorderLayout.WEST);
		ThiLOJp3.add(slider9, BorderLayout.CENTER);
		ThiLOJp4.setLayout(new BorderLayout());
		// ThiLOJp4.add(sure3,BorderLayout.WEST);

		ThiLOJp.setLayout(new BoxLayout(ThiLOJp, BoxLayout.Y_AXIS));
		ThiLOJp.add(ThiLOJp0);
		ThiLOJp.add(ThiLOJp1);
		ThiLOJp.add(ThiLOJp2);
		ThiLOJp.add(ThiLOJp3);
		ThiLOJp.add(ThiLOJp4);

		searchPanel.setLayout(new BorderLayout());
		searchPanel.add(searchLabel, BorderLayout.WEST);
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(go, BorderLayout.EAST);

		excute.setLayout(new BoxLayout(excute, BoxLayout.Y_AXIS));
		excute.add(FirLOJp);
		excute.add(SecLOJp);
		excute.add(ThiLOJp);
		excute.add(searchPanel); // 搜索面板
		// excute.add(ego);

		/*
		 * statistic.setLayout(new BoxLayout(statistic,BoxLayout.Y_AXIS));
		 * statistic.add(Direct); statistic.add(unDirect);
		 */
		
		leftTabbedPane.add("Layout", excute);
		// leftTabbedPane.add("Anlynase",statistic);
		// leftTabbedPane.add(scrollbar);

		leftPanel.add(leftTabbedPane);
		leftSplitPane.setLeftComponent(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new java.awt.GridLayout());

		downSplitPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		downSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		downSplitPane.setResizeWeight(0.85);
		downSplitPane.setTopComponent(workTabbedPane);

		JTabbedPane downTabbedPane = new JTabbedPane();
		downTabbedPane.add("TimeLine Display", new JPanel());
		downSplitPane.setBottomComponent(downTabbedPane);
		rightPanel.add(downSplitPane);
		leftSplitPane.setRightComponent(rightPanel);

		centerPanel.add(leftSplitPane);
		frame.add(centerPanel, java.awt.BorderLayout.CENTER);

		// 底部状态栏
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
		bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
		bottomPanel.add(new JLabel("Data Visualization Ver0.1"));
		bottomPanel.add(new JProgressBar());
		bottomPanel.add(new JLabel("INS"));
		frame.add(bottomPanel, java.awt.BorderLayout.PAGE_END);
	}
}
