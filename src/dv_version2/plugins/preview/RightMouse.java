/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2.plugins.preview;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.gephi.graph.api.Node;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.PreviewProperties;
import org.gephi.preview.spi.PreviewMouseListener;
import org.gephi.project.api.Workspace;

/**
 *
 * @author lgw
 */
public final class RightMouse extends Frame implements PreviewMouseListener{
    //@Override
    public void mouseClicked(PreviewMouseEvent event, PreviewProperties properties, Workspace workspace){
        
    }
    private JPopupMenu menu = new JPopupMenu();  
      
    public RightMouse() {  
        this.setBounds(new Rectangle(500,400));  
        this.setLocationRelativeTo(null);  
        this.setVisible(true);  
        menu.setVisible(true);  
        this.RightMouse();  
        this.add(menu);  
          
    }
     public void RightMouse() {  
          JMenuItem mAll, mCopy, mCut, mPaste, mDel;  
          menu = new JPopupMenu();  
          mAll = new JMenuItem("全选(A)");  
          menu.add(mAll);  
          mCopy = new JMenuItem("复制(C)");  
          menu.add(mCopy);  
          mCut = new JMenuItem("剪切(T)");  
          menu.add(mCut);  
          mPaste = new JMenuItem("粘贴(P)");  
          menu.add(mPaste);  
          mDel = new JMenuItem("删除(D)");  
          menu.add(mDel);  
          this.addMouseListener(new MouseAdapter() {  
           public void mouseClicked(Node node,PreviewMouseEvent event) {  
            if (event.button==PreviewMouseEvent.Button.LEFT) {  
             //弹出右键菜单  
             menu.show(RightMouse.this, (int)node.x(),(int)node.y());  
            }  
           }     
          });  
          mAll.addActionListener(new ActionListener() {     
           public void actionPerformed(ActionEvent e) {  
            System.out.println("点击了全选菜单");  
           }  
          });  
            
         }  

    public void mousePressed(PreviewMouseEvent pme, PreviewProperties pp, Workspace wrkspc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseDragged(PreviewMouseEvent pme, PreviewProperties pp, Workspace wrkspc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseReleased(PreviewMouseEvent pme, PreviewProperties pp, Workspace wrkspc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
