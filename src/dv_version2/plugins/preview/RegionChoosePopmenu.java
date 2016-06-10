package dv_version2.plugins.preview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.gephi.datalab.api.GraphElementsController;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.openide.util.Lookup;









public class RegionChoosePopmenu {
	 public JPopupMenu popupMenu;
	 private JMenuItem[]  items;
     RegionChoosePopmenu()
     {
    	 popupMenu = new JPopupMenu(); // 实例化弹出菜单 
         String[] str = { "delete", "red", "blue", "yellow", "pink" }; // 菜单项名称 
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
     
     private class MenuItemMonitor implements ActionListener { 
    	 
	     public void actionPerformed(ActionEvent event) { 
	         // 获取String格式的ActionCommand 
	         String strIndex = ((JMenuItem) event.getSource()).getActionCommand(); 
	       
	         // 将上面取到的String格式的内容变为int类型作为取颜色的下标 
	         int niIndex = Integer.parseInt(strIndex); 
	         int size_Region=MouseListenerTemplate.regionChooseNodes.size();//NodePopmenu中的egoNodes的元素个数
	         
	         //执行删除操作
	         if(niIndex==0 && size_Region!=0)
	         {
	        	 DirectedGraph graph2=Lookup.getDefault().lookup(GraphController.class).getGraphModel().getDirectedGraph();
                        GraphElementsController gec=Lookup.getDefault().lookup(GraphElementsController.class);    
                
                        graph2.readUnlockAll(); //这行代码很关键，在写图数据之前必须解除对图数据的读同步锁        
//                         System.out.println("删除点之前图形后边的数目。。。。。。。。。。。。。。。"+graph2.getEdgeCount());
//                         通过循环对每个被框选的点删除 
                        Node node=MouseListenerTemplate.regionChooseNodes.poll();
                        while(node!=null)
                        {
                        	 
                                 Node[] nodes=graph2.getNeighbors(node).toArray();
                                 Edge[] edges=graph2.getEdges(node).toArray();
                                 NodePopmenu.nodesLable.add(node.getLabel());
                                 NodePopmenu.egoNodes.add(node);//将被删除的点加入到点局部菜单的链表中
                                 NodePopmenu.egoEdges.add(edges);//将被删除的线也存储起来
                                 graph2.removeNode(node );
                                 node=MouseListenerTemplate.regionChooseNodes.poll();
                        }
                         
                        PreviewSketch1.refreshLoop.refreshSketch();
                           return;
	         }
                 //恢复ego点一级相连接的线
                 
	         if(niIndex==1 && size_Region!=0 )
	         {
	        	 DirectedGraph graph2=Lookup.getDefault().lookup(GraphController.class).getGraphModel().getDirectedGraph();
                       GraphElementsController gec=Lookup.getDefault().lookup(GraphElementsController.class);    
                
                        graph2.readUnlockAll(); //这行代码很关键，在写图数据之前必须解除对图数据的读同步锁        
                         
//                         通过循环对每个被框选的点删除 
                        Node node=MouseListenerTemplate.regionChooseNodes.poll();
                        while(node!=null)
                        {
                        	//对每一个被款选中的点进行遍历，并将其颜色设置为
                        	for (Node node1 :graph2.getNodes())
                        	{
                        		if(node1.getId()==node.getId())
                        		{
                        			node1.setColor(Color.red);
                        		}
                        	}
                        	
                            node=MouseListenerTemplate.regionChooseNodes.poll();
                        }
                         
                         PreviewSketch1.refreshLoop.refreshSketch();
                           return;
	         }
	     }
     }
}
