/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;

/**
 *
 * @author tang
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.preview.api.PreviewController;
import org.gephi.filters.api.FilterController;
import java.io.File;
import org.gephi.appearance.api.AppearanceController;
import org.gephi.appearance.api.AppearanceModel;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;



import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.api.LayoutController;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;


import org.openide.util.Lookup;
import org.gephi.preview.api.PreviewModel;

/**
 *
 * @author tang
 */
public class SingletonObject {
        ProjectController pc;        
        GraphModel graphModel;
        PreviewModel model;
        ImportController importController;
        FilterController filterController;
        PreviewController previewController;
        AppearanceController appearanceController;
        AppearanceModel appearanceModel; 
        LayoutController lc;
       Workspace workspace;
        String fileName;
       
        SingletonObject(){
            this.pc = Lookup.getDefault().lookup(ProjectController.class);
             pc.newProject();
            // this.workspace = pc.getCurrentWorkspace();
             this.lc = Lookup.getDefault().lookup(LayoutController.class);
           
             this.previewController = Lookup.getDefault().lookup(PreviewController.class);
             this.filterController = Lookup.getDefault().lookup(FilterController.class);
             this.importController = Lookup.getDefault().lookup(ImportController.class);
             this.model = Lookup.getDefault().lookup(PreviewController.class).getModel();
             this.graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
       
       
 //3.导入数据，使用Container接收，并将数据导入到空间中
      
//Append imported data to GraphAPI
      
     }
         public void importData(){
            Container container = null;
          //  workspace = null;//   这个有问题   去掉就正常输入，  清空就不行。
        try {
       //  File file = new File("D:\\QQrecv\\gephi-toolkit-demos-master\\src\\main\\resources\\org\\gephi\\toolkit\\demos\\LesMiserables.gexf");
           File file = new File(fileName);
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);   //Force UNDIRECTED     
     
        } 
        catch (Exception ex) {
            ex.printStackTrace();
          
        }      
        workspace = pc.getCurrentWorkspace();
       importController.process(container, new DefaultProcessor(), workspace); 
        }
    public void setFileName(String fileName)
     {
         this.fileName = fileName ;
         importData();
     }
      public Workspace getWorkspace()
     {
         return  workspace;
     }
     public GraphModel getgraphModel()
     {
         return  graphModel;
     }
     public PreviewModel getPreviewModel()
     {
         return  model;
     }
     public  ImportController getImportController()
     {
         return   importController;
     }
 
     public FilterController getfilterController()
     {
         return  filterController;
     }
  
     public AppearanceController getappearanceController()
     {
         return  appearanceController;
     }
      public PreviewController getpreviewController()
     {
         return  previewController;
     }
      public  LayoutController getlayoutController()
     {
         return  lc;
     }
     
    
}
