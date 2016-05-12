/*
Copyright 2008-2010 Gephi
Authors : Mathieu Bastian <mathieu.bastian@gephi.org>
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

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.preview.api.*;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import dv_version2.plugins.preview.PreviewSketch;
import dv_version2.plugins.preview.PreviewSketch1;
import org.openide.util.Lookup;

/**
 *
 * @author Mathieu Bastian
 */
public class PreviewJFrame {

    public void script() {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        final Workspace workspace = pc.getCurrentWorkspace();

        //Import file
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        Container container;
        try {
            File file = new File(getClass().getResource("/org/gephi/toolkit/demos/LesMiserables.gexf").toURI());
            container = importController.importFile(file);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        //Preview configuration
        final PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
        PreviewModel previewModel = previewController.getModel();
        previewModel.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS, Boolean.TRUE);
        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.WHITE));
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 50);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_RADIUS, 10f);
        previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.BLACK);

        //New Processing target, get the PApplet
        G2DTarget target = (G2DTarget) previewController.getRenderTarget(RenderTarget.G2D_TARGET);
        final PreviewSketch1 previewSketch1 = new PreviewSketch1(target);
       // previewSketch1.resetZoom();
        previewController.refreshPreview();
        /*
        int index1=0;
        int index2=0;
         for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes())
         {
             node.setPosition(index1, index2);
             index1++;
             index2++;
         }
         previewController.refreshPreview();
        */
       // previewSketch.resetZoom();

        //Add the applet to a JFrame and display
        JFrame frame = new JFrame("Test Preview");
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(previewSketch1, BorderLayout.CENTER);
        
        //通过按钮更新
        /*
          Button bt=new Button("更新");
           bt.addActionListener(new ActionListener()     //添加按钮的活动监听器
		{
                   public void actionPerformed(ActionEvent e)
			{
				
                             int index1=0;
                             int index2=0;
                            for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes())
                            {
                                node.setPosition(index1, index2);
                                index1++;
                                index2++;
                              }
                             
                      // previewSketch.refreshLoop.refreshSketch();
                      previewController.refreshPreview();
				
			}
		}
		); 
        
           frame.add(bt,BorderLayout.EAST);
        */
        frame.setSize(1024, 768);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        PreviewJFrame previewJFrame = new PreviewJFrame();
        previewJFrame.script();
    }

}
