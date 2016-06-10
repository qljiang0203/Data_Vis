/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dv_version2.plugins.preview.NodeSearch;


import javax.swing.event.*;

import java.awt.BorderLayout;
import java.util.ArrayList;
import org.gephi.graph.api.Node;
import dv_version2.plugins.preview.MouseListenerTemplate;

import static dv_version2.plugins.preview.PreviewSketch1.refreshLoop;

public class MenuDemo 
{
    //*********************************************************************************************************************************
        
	private Frame frame;                                         //整体框架
        
       // private Frame statisticFrame;                               
        private JPanel  statisticPanel=new JPanel();                //统计显示窗
	private MenuBar menubar;                                   //菜单栏，后续进行补充和完善
	private Menu menu;
	private MenuItem menuitem;
	private FileDialog filedialog;
	private static String filedir;                                    //文件对话框和文件路径
        JPanel statistic=new JPanel();                            //数据统计和布局算法执行的面板
        JPanel excute=new JPanel();
        
        JPanel FirLOJp=new JPanel();
        JPanel FirLOJp0=new JPanel();
        JPanel FirLOJp1=new JPanel();
        JPanel FirLOJp2=new JPanel();
        JPanel FirLOJp3=new JPanel();
        
        JPanel SecLOJp=new JPanel();
        JPanel SecLOJp0=new JPanel();
        JPanel SecLOJp1=new JPanel();
        JPanel SecLOJp2=new JPanel();
        JPanel SecLOJp3=new JPanel();
        JPanel SecLOJp4=new JPanel();
        
        JPanel ThiLOJp=new JPanel();
        JPanel ThiLOJp0=new JPanel();
        JPanel ThiLOJp1=new JPanel();
        JPanel ThiLOJp2=new JPanel();
        JPanel ThiLOJp3=new JPanel();
        JPanel ThiLOJp4=new JPanel();
        
        JLabel label1=new JLabel("斥力强度:");
        JLabel label2=new JLabel("中心引力:");
        JLabel label4=new JLabel("最佳距离:");
        JLabel label5=new JLabel("步比率:");
        JLabel label7=new JLabel("重力:");
        JLabel label8=new JLabel("速度:");
        JLabel label9=new JLabel("面积:");
        
        JSlider  slider1=new JSlider(2000,20000,12000);
        JSlider  slider2=new JSlider(0,300,100);
       
        //private  JButton sure1=new JButton("确定");
        
        JSlider  slider4=new JSlider(0,600,200);
        JSlider  slider5=new JSlider(0,100,80);
        
        //private  JButton sure2=new JButton("确定");
        
        JSlider  slider7=new JSlider(0,50,10);
        JSlider  slider8=new JSlider(0,10,3);
        JSlider  slider9=new JSlider(10000,50000,30000);
        //private  JButton sure3=new JButton("确定");
        
        JScrollBar  scrollbar=new JScrollBar();
        static JTabbedPane workTabbedPane=new JTabbedPane();
        
     // 搜索面板
    	JPanel searchPanel = new JPanel();
    	private JLabel searchLabel = new JLabel("search>>");
    	private JTextField searchField = new JTextField();
    	private JButton go = new JButton("Go");
        
        JSplitPane downSplitPane =new JSplitPane();  
        JSplitPane leftSplitPane =new JSplitPane();//自定义工作区间
        //JScrollPane leftSplitPane =new JScrollPane();
        //leftSplitPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        
        private  JButton ForceAtlas2=new   JButton("ForceAtlas2");
        private  JButton Yifanhu=new JButton("Yifanhu");
        private  JButton FR=new   JButton("FR");  //定义布局算法执行
        
        private  JButton ego=new   JButton("EGO");  
        
        private JButton Direct=new JButton("Direct");
        private JButton unDirect=new JButton("unDirect");         //有向图和无向图数据统计
        private JButton Dj =new JButton("多选"); 
        private JButton Dh =new JButton("确定");
        private Double gravity;
        private Double repulseStrength;
        private Double speed;
        private Float area;
        private Float optimalDistance;
        private Float stepRatio;
        static SingletonObject singletonObject;
        private  JPanel jpanel;
//*********************************************************************************************************************************       
public	MenuDemo()
         {
            
          //  new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);//2、调用算法布局。
              //  new EgoModule(); // 这个我不知道用不用了还。
         //   jpanel = new PreviewModule(singletonObject).display();      
             gravity = 10d;
             repulseStrength = 12000d;
             speed=3d;
             area=30000f;
             optimalDistance=200f;
             stepRatio=80f;
              slider1.setMajorTickSpacing(5000);
              slider1.setMinorTickSpacing(1000);
              slider1.setPaintTicks(true);
              slider1.setPaintLabels(true);
              slider1.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            gravity = (double)slider2.getValue();
                            repulseStrength = (double)slider1.getValue();
                            new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);                    
                            jpanel = new PreviewModule(singletonObject).display();
                            workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                            workTabbedPane.add("ForceAtlasLayout",jpanel);
                            workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("ForceAtlasLayout").getJPanel());
                            
                     }
                }
            });
       //        workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(temp),new Label_closing("ForceAtlasLayout").getJPanel());   
              slider2.setMajorTickSpacing(50);
              slider2.setMinorTickSpacing(10);
              slider2.setPaintTicks(true);
              slider2.setPaintLabels(true);
              slider2.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            gravity = (double)slider2.getValue();
                            repulseStrength = (double)slider1.getValue();
                             new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);                    
                            jpanel = new PreviewModule(singletonObject).display();                      
                            workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                            workTabbedPane.add("ForceAtlasLayout",jpanel);
                            workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("ForceAtlasLayout").getJPanel());
                     }
                }
            });
                  
              slider4.setMajorTickSpacing(100);
              slider4.setMinorTickSpacing(20);
              slider4.setPaintTicks(true);
              slider4.setPaintLabels(true);
              slider4.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            optimalDistance=(float)slider4.getValue();
                            stepRatio=(float)slider5.getValue();
                            new AlgoModule().yiFanHuLayout(singletonObject, optimalDistance,  stepRatio);                    
                            jpanel = new PreviewModule(singletonObject).display();
                            workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                            
                            
                            workTabbedPane.add("YifanHuLayout",jpanel);
                            workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("YifanHuLayout").getJPanel());
                     }
                }
            });
                  
              slider5.setMajorTickSpacing(20);
              slider5.setMinorTickSpacing(5);
              slider5.setPaintTicks(true);
              slider5.setPaintLabels(true);
              slider5.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            optimalDistance=(float)slider4.getValue();
                            stepRatio=(float)slider5.getValue();
                            new AlgoModule().yiFanHuLayout(singletonObject, optimalDistance,  stepRatio);                    
                            jpanel = new PreviewModule(singletonObject).display();
                            workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                            workTabbedPane.add("YifanHuLayout",jpanel);
                            workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("YifanHuLayout").getJPanel());
                     }
                }
            });
                 
              slider7.setMajorTickSpacing(10);
              slider7.setMinorTickSpacing(2);
              slider7.setPaintTicks(true);
              slider7.setPaintLabels(true);
              slider7.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            gravity = (double)slider7.getValue();
                             speed=(double)slider8.getValue();
                             area=(float)slider9.getValue();
                            new AlgoModule().FRLayout(singletonObject, gravity,  speed, area);                    
                            jpanel = new PreviewModule(singletonObject).display(); 
                            workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                             workTabbedPane.add("FruchtermanReingold",jpanel);
                             workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("FruchtermanReingold").getJPanel());
                     }
                }
            });
                  
              slider8.setMajorTickSpacing(2);
              slider8.setMinorTickSpacing(1);
              slider8.setPaintTicks(true);
              slider8.setPaintLabels(true);
              slider8.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            gravity = (double)slider7.getValue();
                             speed=(double)slider8.getValue();
                             area=(float)slider9.getValue();
                              new AlgoModule().FRLayout(singletonObject, gravity,  speed, area);                    
                             jpanel = new PreviewModule(singletonObject).display();  
                             workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                             workTabbedPane.add("FruchtermanReingold",jpanel);
                             workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("FruchtermanReingold").getJPanel());
                     }
                }
            });
                  
              slider9.setMajorTickSpacing(5000);
              //slider9.setMinorTickSpacing(1000);
              slider9.setPaintTicks(true);
              slider9.setPaintLabels(true);
              slider9.addChangeListener(new ChangeListener(){
                    public void stateChanged(ChangeEvent event){
                       JSlider source=(JSlider) event.getSource();
                     if (source.getValueIsAdjusting() != true){
                            gravity = (double)slider7.getValue();
                             speed=(double)slider8.getValue();
                             area=(float)slider9.getValue();
                             new AlgoModule().FRLayout(singletonObject, gravity,  speed, area);                    
                             JPanel jpanel = new PreviewModule(singletonObject).display();
                             workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                             workTabbedPane.add("FruchtermanReingold",jpanel);
                             workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("FruchtermanReingold").getJPanel());
                     }
                }
            });
                
                frameInit();
	}
	private void frameInit()
	{
		frame = new Frame("Data Visualization Ver0.1");
                frame.setBounds(200,200,600,500);
		frame.setLayout(new BorderLayout());
		menubar = new MenuBar();
		menu = new Menu("File");
		menuitem = new MenuItem("fileload");
		filedialog = new FileDialog(frame,"fileload",FileDialog.LOAD);
		frame.setMenuBar(menubar);
                menu.add(menuitem);
		menubar.add(menu);
                
               
                
                Menu editMenu=new Menu("Edit");  
                menubar.add(editMenu);  
                Menu viewMenu=new Menu("WorkSpace");  
                menubar.add(viewMenu);  
   
                Menu sourceMenu=new Menu("Tools");  
                menubar.add(sourceMenu);  
                Menu refactorMenu=new Menu("Window");  
                menubar.add(refactorMenu);   
                Menu helpMenu=new Menu("Help");  
                menubar.add(helpMenu); 
       		                                   
		myEvent();
                buttonEvent();
                //SliderEvent();
                setLayout();
		frame.setVisible(true);               
        }
	 private void buttonEvent()
         {
             ForceAtlas2.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent e)
                         {
                             new AlgoModule().forceAtlasLayout(singletonObject, repulseStrength, gravity);                    
                             jpanel = new PreviewModule(singletonObject).display();
                             workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                             workTabbedPane.add("ForceAtlasLayout",jpanel);
                             workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("ForceAtlas2").getJPanel());
                         }
                     });
            
              Yifanhu.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent e)
                         {  
                            new AlgoModule().yiFanHuLayout(singletonObject, optimalDistance,  stepRatio);                    
                            jpanel = new PreviewModule(singletonObject).display(); 
                            workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                            workTabbedPane.add("YifanHuLayout",jpanel);
                            workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("YifanHuLayout").getJPanel());
                         }
                     }
              );
               FR.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent e)
                         {
                             new AlgoModule().FRLayout(singletonObject, gravity,  speed, area);                    
                             jpanel = new PreviewModule(singletonObject).display();
                             workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                             workTabbedPane.add("FruchtermanReingold",jpanel);
                              jpanel.updateUI();
                             workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("FR").getJPanel());
                         }
                     });
             //多点选择
               Dj.addActionListener(new ActionListener()
               {    
                   public void actionPerformed(ActionEvent e)
                    {     
                   	MouseListenerTemplate mt=new MouseListenerTemplate();
                   	if(mt.getData()==0){
                        mt.setData(1);                                  
                   	}
                   	
                    }
                });

               
               
             //多选确定
               Dh.addActionListener(new ActionListener()
               {    
                   public void actionPerformed(ActionEvent e){
                   MouseListenerTemplate mt=new MouseListenerTemplate();
                   if(mt.getData()==1){
                   		
                   		ArrayList at=mt.getAL();
                   		ArrayList atx=mt.getCox();//获得多选主点集合
                   		//System.out.println(atx):
                   		//Node[] tt=(Node[]) at.get(0);
                   		for(int i=0;i<at.size();i++){
                   			Node[] tt=(Node[]) at.get(i);
                   			for(int j=0;j<tt.length;j++){
                   				Node nn=tt[j];
                   				if(!atx.contains(nn)){
                   				nn.setColor(new Color(255,255,0));  
                   				}      
                   			    refreshLoop.refreshSketch();
                   			}
                   			
                   		}
                   		mt.clearCox();
                   		mt.setData(0);
                   	}
                   
                   
                   }
                });
            
               
       		// 监听搜索按钮
       		go.addActionListener(new ActionListener() {
       			public void actionPerformed(ActionEvent e) {
       				String text = searchField.getText().trim(); // 获取文本框的输入内容（去除两端空格）
       				
       				try {
       					new NodeSearch().nodeSearch(singletonObject, text);
       				} catch (RuntimeException evt) {
       					javax.swing.JOptionPane.showMessageDialog(null, "搜索标识不存在", "提示",
       							javax.swing.JOptionPane.INFORMATION_MESSAGE);
       				}
       			}
       		});
                
       		/*
                ego.addActionListener(new ActionListener()
                     {
                         public void actionPerformed(ActionEvent e)
                         {       
                             
                            String s=JOptionPane.showInputDialog("Enter the ID of the ego point.");                                                 
                            JOptionPane.showMessageDialog(null,"You want to watch"+s ,"number",JOptionPane.INFORMATION_MESSAGE);
                             temp=new new_demo().script(filedir,layoutMethod,"unDirect", Integer.parseInt(s),repulseStrength,gravity,speed,area,optimalDistance,stepRatio);  //调用布局算法返回生成的布局图                                                      
                             workTabbedPane.add("EGO",temp);        //将统计信息面板返回                             
                         }
                     });
                */
               
         }
         /*
         private void SliderEvent(){
            ChangeListener listener;
            listener=new ChangeListener(){
                public void stateChanged(ChangeEvent event){
                     slider1=(JSlider) event.getSource();
                     //获得滑动条当前值。。。
                }
            };
            
         }
         */
//          private void SliderEvent(){
//                ChangeListener listener;
//                listener=new ChangeListener(){
//                    public void stateChanged(ChangeEvent event){
//                       JSlider source=(JSlider) event.getSource();
//                     //获得滑动条当前值。。。
//                     if (source.getValueIsAdjusting() != true){
//                            gravity = (double)slider2.getValue();
//                            repulseStrength = (double)slider1.getValue();
//                            layoutMethod=new String("ForceAtlasLayout");                       
//                            temp=new new_demo().script(filedir,layoutMethod,"unKnown",-1,repulseStrength,gravity,speed,area,optimalDistance,stepRatio);  //调用布局算法返回生成的布局图
//                            workTabbedPane.add("ForceAtlasLayout",temp);
//                     }
//                }
//            };
//          }
         
	private void myEvent()
	{
		menuitem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				filedialog.setVisible(true);
				filedir = filedialog.getDirectory()+filedialog.getFile();
				System.out.println(filedir);
                                singletonObject = new SingletonObject();
                                singletonObject.setFileName(filedir);
                                new AlgoModule().forceAtlasLayout(singletonObject,repulseStrength ,gravity);                    
                                jpanel = new PreviewModule(singletonObject).display();
                                 workTabbedPane.remove(workTabbedPane.getSelectedComponent());
                                 workTabbedPane.add("ForceAtlas",jpanel);
                             
                                 workTabbedPane.setTabComponentAt( workTabbedPane.indexOfComponent(jpanel),new Label_closing("ForceAtlas").getJPanel());
			}
		});
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

	}
	public String getPath()
	{
		return filedir;
	}
	

	public static void main(String[] args) 
	{
                 //1、读取文件，获取各个控制器和模型。
		MenuDemo menuTest=new MenuDemo();
//                singletonObject = new SingletonObject();
          
        
                
	}
        public void setLayout()
        { 
         JPanel centerPanel=new JPanel();   
         centerPanel.setLayout(new java.awt.GridLayout());  
        
       
        leftSplitPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));  
        leftSplitPane.setResizeWeight(0.1);  
        
        JPanel leftPanel=new JPanel();  
        leftPanel.setLayout(new java.awt.GridLayout());  
        JTabbedPane leftTabbedPane=new JTabbedPane(); 
        
        FirLOJp0.setLayout(new BorderLayout());
        FirLOJp0.add(ForceAtlas2,BorderLayout.WEST);
        FirLOJp1.setLayout(new BorderLayout());
        FirLOJp1.add(label1,BorderLayout.WEST);
        FirLOJp1.add(slider1,BorderLayout.CENTER);
        FirLOJp2.setLayout(new BorderLayout());
        FirLOJp2.add(label2,BorderLayout.WEST);
        FirLOJp2.add(slider2,BorderLayout.CENTER);
        FirLOJp3.setLayout(new BorderLayout());
        //FirLOJp3.add(sure1,BorderLayout.WEST);
        
        FirLOJp.setLayout(new BoxLayout(FirLOJp,BoxLayout.Y_AXIS));
        FirLOJp.add(FirLOJp0);
        FirLOJp.add(FirLOJp1);
        FirLOJp.add(FirLOJp2);
        FirLOJp.add(FirLOJp3);
        
        
        SecLOJp0.setLayout(new BorderLayout());
        SecLOJp0.add(Yifanhu,BorderLayout.WEST);
        SecLOJp1.setLayout(new BorderLayout());
        SecLOJp1.add(label4,BorderLayout.WEST);
        SecLOJp1.add(slider4,BorderLayout.CENTER);
        SecLOJp2.setLayout(new BorderLayout());
        SecLOJp2.add(label5,BorderLayout.WEST);
        SecLOJp2.add(slider5,BorderLayout.CENTER);
        SecLOJp3.setLayout(new BorderLayout());
        SecLOJp4.setLayout(new BorderLayout());
        //SecLOJp4.add(sure2,BorderLayout.WEST);
        
        SecLOJp.setLayout(new BoxLayout(SecLOJp,BoxLayout.Y_AXIS));
        SecLOJp.add(SecLOJp0);
        SecLOJp.add(SecLOJp1);
        SecLOJp.add(SecLOJp2);
        SecLOJp.add(SecLOJp3);
        SecLOJp.add(SecLOJp4);
        
        ThiLOJp0.setLayout(new BorderLayout());
        ThiLOJp0.add(FR,BorderLayout.WEST);
        ThiLOJp1.setLayout(new BorderLayout());
        ThiLOJp1.add(label7,BorderLayout.WEST);
        ThiLOJp1.add(slider7,BorderLayout.CENTER);
        ThiLOJp2.setLayout(new BorderLayout());
        ThiLOJp2.add(label8,BorderLayout.WEST);
        ThiLOJp2.add(slider8,BorderLayout.CENTER);
        ThiLOJp3.setLayout(new BorderLayout());
        ThiLOJp3.add(label9,BorderLayout.WEST);
        ThiLOJp3.add(slider9,BorderLayout.CENTER);
        ThiLOJp4.setLayout(new BorderLayout());
         ThiLOJp4.add(Dj,BorderLayout.WEST);
         ThiLOJp4.add(Dh,BorderLayout.EAST);
        //ThiLOJp4.add(sure3,BorderLayout.WEST);
        
        ThiLOJp.setLayout(new BoxLayout(ThiLOJp,BoxLayout.Y_AXIS));
        ThiLOJp.add(ThiLOJp0);
        ThiLOJp.add(ThiLOJp1);
        ThiLOJp.add(ThiLOJp2);
        ThiLOJp.add(ThiLOJp3);
        ThiLOJp.add(ThiLOJp4);
        
        searchPanel.setLayout(new BorderLayout());
		searchPanel.add(searchLabel, BorderLayout.WEST);
		searchPanel.add(searchField, BorderLayout.CENTER);
		searchPanel.add(go, BorderLayout.EAST);
        
        excute.setLayout(new BoxLayout(excute,BoxLayout.Y_AXIS));
        excute.add(FirLOJp);
        excute.add(SecLOJp);
        excute.add(ThiLOJp);
        
        excute.add(searchPanel); // 搜索面板
        //excute.add(ego);
        /*
        statistic.setLayout(new BoxLayout(statistic,BoxLayout.Y_AXIS));
        statistic.add(Direct);
        statistic.add(unDirect);  
        */
        leftTabbedPane.add("Layout",excute);
        //leftTabbedPane.add("Anlynase",statistic);
        //leftTabbedPane.add(scrollbar);
      
        leftPanel.add(leftTabbedPane);  
        leftSplitPane.setLeftComponent(leftPanel);
        
        JPanel rightPanel=new JPanel();  
        rightPanel.setLayout(new java.awt.GridLayout());  
        
        downSplitPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));  
        downSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);  
        downSplitPane.setResizeWeight(0.85);  
        downSplitPane.setTopComponent(workTabbedPane);
        
        JTabbedPane downTabbedPane=new JTabbedPane();  
        downTabbedPane.add("TimeLine Display", new JPanel());  
        downSplitPane.setBottomComponent(downTabbedPane);  
        rightPanel.add(downSplitPane);  
        leftSplitPane.setRightComponent(rightPanel);  
        
        centerPanel.add(leftSplitPane); 
        frame.add(centerPanel, java.awt.BorderLayout.CENTER);  
        
        //底部状态栏  
        JPanel bottomPanel=new JPanel();  
        bottomPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));  
        bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));  
        bottomPanel.add(new JLabel("Data Visualization Ver0.1"));  
        bottomPanel.add(new JProgressBar());  
        bottomPanel.add(new JLabel("INS"));  
        frame.add(bottomPanel, java.awt.BorderLayout.PAGE_END);  
        }
}



         
        
        
         
       
       