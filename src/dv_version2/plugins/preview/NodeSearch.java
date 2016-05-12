package dv_version2.plugins.preview;


import dv_version2.SingletonObject;
import java.awt.Color;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

/**
*
* @author qljiang
*/
public class NodeSearch {

	public void nodeSearch(SingletonObject singletonObject,int nodeId){
		
		Workspace workspace = singletonObject.getWorkspace();
		Graph graph = singletonObject.getgraphModel().getGraph();
		graph.readUnlockAll();
		
		/*PreviewController previewController = singletonObject.getpreviewController();
		//previewController.refreshPreview();
		G2DTarget target = (G2DTarget) previewController.getRenderTarget(RenderTarget.G2D_TARGET);
		PreviewSketch1.RefreshLoop r = new PreviewSketch1(target).refreshLoop;
		r.refreshSketch();*/
		
		if(nodeId != -1){
			Node[] temp = new Node[graph.getNodeCount()+1]; // +1：防止id起始大小不同造成越界异常
			
			for(Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()){
				String sNode = node.getId().toString(); 
				temp[(int)Double.parseDouble(sNode)] = node;
				
			}
			
			temp[nodeId].setColor(new Color(255,255,0)); //高亮目标节点
			float x = temp[nodeId].x();
			float y = temp[nodeId].y();
			//float z = temp[nodeId].z();
			
			temp[nodeId].setPosition(0-x, 0-y);
			System.out.println(temp[nodeId].getLabel());
		}
	}
	
}
