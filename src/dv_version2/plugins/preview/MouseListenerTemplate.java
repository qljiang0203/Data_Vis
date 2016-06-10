/*
Copyright 2008-2014 Gephi
Authors : Eduardo Ramos <eduardo.ramos@gephi.org>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package dv_version2.plugins.preview;

import java.awt.Color;
import static java.awt.Color.yellow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphModel;
import org.gephi.preview.api.PreviewMouseEvent.Button;
import static dv_version2.plugins.preview.PreviewSketch1.refreshLoop;


public class MouseListenerTemplate {

	// @Override
	float start_x;
	float start_y;
	// 记录框选的范围
	float region_x1;
	float region_y1;
	float region_x2;
	float region_y2;

	public static LinkedList<Node> regionChooseNodes = new LinkedList<Node>();// 用来记录框选中的所有点的一个list
	public static boolean region = false;// 用来表示是否框选
	public static boolean innode;// 用来指代是否击中点
	public static boolean inedge;// 用来指代是否击中线
	public static Node nodeclicked;// 定义该静态变量，用来指代被点中的点
	public static Edge edgeclicked;// 定义该静态变量，用来指代被点中的线
	public static boolean regionShow = false;
       public static boolean inNode=false;
	PreviewController previewController;
	private PreviewMouseEvent.Button Left; // 左按键事件触发值
	private PreviewMouseEvent.Button Right; // 右按键触发事件
        private boolean dragged=false;
	//static ArrayList co = new ArrayList();
	//private static Boolean setHighlight = false;// 初始时没有任何点被点亮
	//static NodeIterable qq;
	GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

	private float dist;// 点及点的距离
       static ArrayList co=new ArrayList();//单击颜色集合
       static ArrayList cod=new ArrayList();//多选节点集合
       static ArrayList coo=new ArrayList();//多选颜色集合
       static ArrayList cox=new ArrayList();//多选点击点集合
       
      public static HashMap<String,Node> node1 = new HashMap<String,Node>();
       public static HashMap<String,Color> color = new HashMap<String,Color>();
   	
         private static  Boolean setHighlight=false;//鍒濆鏃舵病鏈変换浣曠偣琚偣浜�
            static NodeIterable qq=  NodeIterable.EMPTY;
            static Node[] co1=   qq.toArray();
            Node[] co2;//存储多选节点数组
            private static int dax=0;//多选单选的标识
            public MouseListenerTemplate(){
        	
        	}
            //得到单选节点集合
            public Node[] getCo1(){
            	return co1;
            }
            //得到单选颜色集合
            public  ArrayList getCo(){
        	    return co;
            }
            public void setData(int data){
            	dax=data;
            }
            public int getData(){
            	return dax;
            }
            //多选
            public  ArrayList<Node> getAL(){
            	    return cod;
            }
            //多选
            public  ArrayList getCoo(){
        	    return coo;
            }
            //多选
            public  ArrayList getCox(){
        	    return cox;
            }
            //多选
            public  void clear(){
        	    cod.clear();
        	    coo.clear();
            }
            public  void clearCox(){
        	    cox.clear();
        	   
            }
            
	public void mouseClicked(PreviewMouseEvent event,  Workspace workspace) {
    
     Graph graph=Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph();
      
         
       
     //左键高亮   
        for (Node node : graph.getNodes()) {
           
            if (clickingInNode(node, event)) {
              
            	 if(!node1.containsKey(node.getId())){
    	            	
    	            	node1.put(node.getId().toString(), node);
    	            	color.put(node.getId().toString(), node.getColor());
    	            }
                   if(event.button==Button.LEFT&&dax==1){
                	   cox.add(node);
            	     NodeIterable ppu=graph.getNeighbors(node);
            	     co2=graph.getNeighbors(node).toArray();
            	     cod.add(co2);
            	      Iterator<Node> ccu=ppu.iterator();
            	     
            	    while (ccu.hasNext()) 
                   {
      	                Node n=ccu.next();
      	              if(!node1.containsKey(n.getId())){
         	            	
         	            	node1.put(n.getId().toString(), n);
         	            	color.put(n.getId().toString(), n.getColor());
         	            }
                        coo.add(n.getColor());            
                    }
                  
                 }   
              //如果点击左键
               if(event.button==Button.LEFT&&dax==0)
               {
                   if(setHighlight==false)
                     {
                    NodeIterable pp=graph.getNeighbors(node);
                          co1=graph.getNeighbors(node).toArray();
                    Iterator<Node> cc=pp.iterator();
                    while (cc.hasNext()) 
                     {
       	            Node n=cc.next();
               
                   System.out.println(n.getColor());
       	         if(!node1.containsKey(n.getId())){
    	            	
    	            	node1.put(n.getId().toString(), n);
    	            	color.put(n.getId().toString(), n.getColor());
    	            }
                      co.add(n.getColor());
       	            n.setColor(yellow);   
                      setHighlight=true;
                         refreshLoop.refreshSketch();              
                     }
                      
                  }   
                   
                   else if(setHighlight==true){
                     Iterator<Node> cc2=qq.iterator();
                       int i=0;
                       System.out.println(i);
                          System.out.println(qq);   
                          System.out.println(cc2);  
                          System.out.println("ttttttt"); 
                          for(i=0;i<co1.length;i++){
                       	   Node nn=co1[i];
                              
               	           nn.setColor((Color) co.get(i));  
                                  refreshLoop.refreshSketch();
                          }
                     
                      co.clear();
             
                       NodeIterable pp2=graph.getNeighbors(node);
                       co1=graph.getNeighbors(node).toArray();
                       Iterator<Node> cc3=pp2.iterator();
                       while (cc3.hasNext() ) {
       	            Node n=cc3.next();  
       	            if(!node1.containsKey(n.getId())){
       	            	
       	            	node1.put(n.getId().toString(), n);
       	            	color.put(n.getId().toString(), n.getColor());
       	            }
                      co.add(n.getColor());
       	            n.setColor(yellow);   
       	            refreshLoop.refreshSketch();
              }    
                      
              }
                 
              } 
              

               
            }
        }
        
    //如果框选，记录框的起始点
        if(region)
        {
        	region_x1=event.x;
        	region_y1=event.y;
        	
        }
      
        
    
	}

	public void recoveryEgo() {

	}

	
	public void mousePressed(PreviewMouseEvent event, Workspace workspace) {
		innode = false;
		inedge = false;
                nodeclicked=null;
                edgeclicked=null;
                inNode=false;
		// 找出点击的点
		for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) {
			if (clickingInNode(node, event)) {
				start_x = node.x();// 记录下被点击点的位置
				start_y = node.y();
				innode = true;
				nodeclicked = node;
				break;
			}
		}
		// 找出点击的线
		for (Edge edge : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getEdges()) {
			if (clickingInEdge(edge, event)) {
				inedge = true;
				edgeclicked = edge;
				break;

			}
		}

		// 如果框选，记录框的起始点

		region_x1 = event.x;
		region_y1 = -event.y;
		
	}

	
	public void mouseDragged(PreviewMouseEvent event,Workspace workspace) {
		/*
		 * System.out.println("dragged开始"); for (Node node :
		 * Lookup.getDefault().lookup(GraphController.class).getGraphModel(
		 * workspace).getGraph().getNodes()) { boolean temp=dragNode(node);
		 * System.out.println(temp); if (temp) { node.setPosition(event.x,
		 * event.y);
		 * 
		 * System.out.println("拖拽进入"); return; } } event.setConsumed(true);
		 * 
		 */
                dragged=true;
		
	}

	
	public void mouseReleased(PreviewMouseEvent event,  Workspace workspace) {
		
		// 计算释放的时候鼠标事件的坐标与点的坐标的距离，如果过小，则不会重置点的坐标
		if (nodeclicked != null)
			dist = (nodeclicked.x() - event.x) * (nodeclicked.x() - event.x)
					+ (-nodeclicked.y() - event.y) * (-nodeclicked.y() - event.y);
		// 判断移动点的距离是否在点的半径以内
		if (innode  ) {
                    
                       //进行点的坐标重置，即移动点
                    if(dist > nodeclicked.size() * nodeclicked.size())
                    {
			nodeclicked.setPosition(event.x, -event.y);
                        
                    }
                        inNode=true;
			innode = false;
			
		}

		// 执行框选操作
		if (region) {
			// 将框选中的点加入到list中
			for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph()
					.getNodes()) {
				if (includeInRegion(node, event)) {
					regionChooseNodes.add(node);
					
				}
			}
			// 框选操作完成后，将region变量恢复
			regionShow = true;

			region = false;
		}

	}

	private boolean includeInRegion(Node node, PreviewMouseEvent event) {
		// 记录框选的终点的坐标
		region_x2 = event.x;
		region_y2 = -event.y;
		// 将所框选的区域颜色改变
		// event.getClass()event.toString()

		System.out.println("框选的终点:" + "(" + region_x2 + "," + region_y2 + ")");
		return node.x() <= region_x2 && node.x() >= region_x1 && node.y() <= region_y1 && node.y() >= region_y2;

	}

	// 判断是否击中线
	private boolean clickingInEdge(Edge edge, PreviewMouseEvent event) {
		float x1 = edge.getSource().x();
		float y1 = edge.getSource().y();
		float x2 = edge.getTarget().x();
		float y2 = edge.getTarget().y();
		float d;

		// 计算点击的位置与线的距离
		if (x1 == x2) {
			d = Math.abs(event.x - x1);
		} else if (y1 == y2) {
			d = Math.abs(-y1 - event.y);
		} else {
			float k = (y2 - y1) / (x2 - x1);
			float b = y1 - (y2 - y1) * x1 / (x2 - x1);
			d = (float) (Math.abs(k * event.x + event.y + b) / Math.sqrt(k * k + 1));
		}

		return d < 2; // 如果该点离线的距离小于0.8，则表示击中了该线

	}

	private boolean clickingInNode(Node node, PreviewMouseEvent event) {
		float xdiff = node.x() - event.x;
		float ydiff = -node.y() - event.y;// Note that y axis is inverse for
											// node coordinates
		float radius = node.size();

		return xdiff * xdiff + ydiff * ydiff < radius * radius;
	}
}
