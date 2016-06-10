/*
 author :jianqin
 该类为右击点时触发关于点的局部菜单 
 * 
 */





package dv_version2.plugins.preview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.gephi.datalab.api.GraphElementsController;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.openide.util.Lookup;

public class NodePopmenu {
	//声明弹出菜单以及其子菜单项
	public JPopupMenu popupMenu;
	private JMenuItem[]  items;
	public static LinkedList<Node>  egoNodes=new LinkedList<Node>();  //保持被删除的ego点
        public static LinkedList<String> nodesLable=new LinkedList<String>();
        public static LinkedList<Edge[]> egoEdges=new LinkedList<Edge[]>();
        
//public static NodeIterable  egoNeiborsNodes= new NodeIterable();//保存被删除的ego点的邻居节点
	
	
	public   NodePopmenu()
	{

		popupMenu = new JPopupMenu(); // 实例化弹出菜单 
        String[] str = { "delete", "aggregation", "Node3", "Node4", "Node5" }; // 菜单项名称 
        items = new JMenuItem[5]; // 创建5个菜单项 
        
        MenuItemMonitor menuItemMonitor = new MenuItemMonitor(); //初始化一个菜单项的监听器
        //对弹出来的菜单中的每一个菜单项实例化并且添加监听器
        for (int i = 0; i < items.length; i++) { 
            items[i] = new JMenuItem(str[i]); // 实例化菜单项 
            popupMenu.add(items[i]); // 将菜单项添加到菜单中 
            // 设置ActionCommand，方便我们获取下标 ，当该菜单项被触发时，可以通过getActionCommand()获取对应下标
            items[i].setActionCommand(i + ""); 
            // 为各菜单项设置监听 
            items[i].addActionListener(menuItemMonitor); 
        } 
	}
	 //弹出来的
	  private class MenuItemMonitor implements ActionListener { 
		 
	        @Override 
	        //弹出来的全局选项卡的每一个选项的触发相应都在该函数中执行
	        public void actionPerformed(ActionEvent event) { 
	            // 获取String格式的ActionCommand 
//	        	System.out.println("");
	            String strIndex = ((JMenuItem) event.getSource()) .getActionCommand(); 
	            // 将上面取到的String格式的内容变为int类型作为取颜色的下标 
	            int niIndex = Integer.parseInt(strIndex); 
	            // 设置背景色为对应下标的颜色 
	            //根据菜单项中第几个菜单被触发，响应对应的事件
		            if(niIndex==0 && MouseListenerTemplate.nodeclicked!=null)
		            {
		            	//删除点的操作                 
	                    DirectedGraph graph2=Lookup.getDefault().lookup(GraphController.class).getGraphModel().getDirectedGraph();
	                    GraphElementsController gec=Lookup.getDefault().lookup(GraphElementsController.class);    
	                     JOptionPane.showConfirmDialog(null, "Node will be deleted,Continue? ","Delete Node ID "+MouseListenerTemplate.nodeclicked.getId(), JOptionPane.YES_OPTION); 
	                            
//	                            graph2.readLock(); //这行代码很关键，在写图数据之前必须解除对图数据的读同步锁        
	                            graph2.readUnlockAll();
	                            
//	                             gec.deleteNode(MouseListenerTemplate.nodeclicked);   
	                             
//		                    graph2.readUnlock(); //这行代码很关键，在写图数据之前必须解除对图数据的读同步锁    
 
	                            
//	                              System.out.println("被删除点的Id:"+MouseListenerTemplate.nodeclicked.getId());
//	                             egoNodes.add(MouseListenerTemplate.nodeclicked);//将被删除的点加入到链表中
	                             Edge[] edges=graph2.getEdges(MouseListenerTemplate.nodeclicked).toArray();
                                 NodePopmenu.nodesLable.add(MouseListenerTemplate.nodeclicked.getLabel());
                                 NodePopmenu.egoNodes.add(MouseListenerTemplate.nodeclicked);//将被删除的点加入到点局部菜单的链表中
                                 NodePopmenu.egoEdges.add(edges);//将被删除的线也存储起来
//	                             egoNeiborsNodes=graph2.getNeighbors(MouseListenerTemplate.nodeclicked);
//	                             System.out.println("加入到egoNodes中后点的Id:"+egoNodes.getFirst().getId());
                                 graph2.removeNode(MouseListenerTemplate.nodeclicked);
	                             MouseListenerTemplate.nodeclicked=null;
	                             
	                             PreviewSketch1.target.refresh();
	                             PreviewSketch1.refreshLoop.refreshSketch();
	                              return;
	                        
	                     } 
		            //执行点聚合的操作
		            else if(niIndex==1 && MouseListenerTemplate.nodeclicked!=null)
			        {
		            	 
		            	
		            	    DirectedGraph graph2=Lookup.getDefault().lookup(GraphController.class).getGraphModel().getDirectedGraph();
		                    GraphElementsController gec=Lookup.getDefault().lookup(GraphElementsController.class); 
//		                    graph2.readUnlock(); //这行代码很关键，在写图数据之前必须解除对图数据的读同步锁    
		                    graph2.readUnlockAll();  
		                    NodeIterable neigbor=graph2.getNeighbors(MouseListenerTemplate.nodeclicked);
		                     Node[] nodes_neigbor= neigbor.toArray();   
//		                    Iterator<Node> neigbors=neigbor.iterator();
		                    float position_x=MouseListenerTemplate.nodeclicked.x(),position_y=MouseListenerTemplate.nodeclicked.y(),radius=0;
		                    for(int index=0;index<nodes_neigbor.length;index++)
		                    {
		                    	
		                    	position_x+=nodes_neigbor[index].x();
		                    	position_y+=nodes_neigbor[index].y();
		                    	radius+=nodes_neigbor[index].r();
		                    	setEdgeConnection(graph2,nodes_neigbor[index],MouseListenerTemplate.nodeclicked);
		                    	graph2.removeNode(nodes_neigbor[index]);
		                    	
		                    }
		                    position_x=position_x/nodes_neigbor.length;position_y=position_y/nodes_neigbor.length;
		                    MouseListenerTemplate.nodeclicked.setPosition(position_x, position_y);
		                    radius=radius/nodes_neigbor.length;
		                    MouseListenerTemplate.nodeclicked.setR(radius);
		                    
		                    
		                    
		                    
		                    
                            PreviewSketch1.target.refresh();
                            PreviewSketch1.refreshLoop.refreshSketch();
                            MouseListenerTemplate.nodeclicked=null;
                            return; 
			          	
			        }	            
	            }
	      //将原本与node1相连的点现在与node2相连
		    private void setEdgeConnection(Graph graph2,Node node1,Node node2)
		    {
		    	NodeIterable neigbor=graph2.getNeighbors(node1);
		    	Node[] nodes_neigbor=neigbor.toArray();
		    	GraphModel graphModel=graph2.getModel();
		    	
		    	 for(int index=0;index<nodes_neigbor.length;index++)
		    	 {
		    		 Edge edge=graphModel.factory().newEdge(nodes_neigbor[index], node2,false);
		    		
		    	     graph2.addEdge(edge);  
		    	 }
		    }
	      
	           
	        } 
	  
	   
}
