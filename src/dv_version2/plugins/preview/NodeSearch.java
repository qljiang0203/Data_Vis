package dv_version2.plugins.preview;

import java.awt.Color;
import java.util.HashMap;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import dv_version2.PreviewModule;
import dv_version2.SingletonObject;

/**
 *
 * @author qljiang
 */
public class NodeSearch {

	public void nodeSearch(SingletonObject singletonObject, String nodeLabel) {
		MouseListenerTemplate mt = new MouseListenerTemplate();
		Workspace workspace = singletonObject.getWorkspace();
		Graph graph = singletonObject.getgraphModel().getGraph();
		graph.readUnlockAll();

		HashMap<String, Node> map = new HashMap<String, Node>();

		for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph()
				.getNodes()) {

			map.put(node.getLabel(), node); // 存储键值对

		}


		Node node = map.get(nodeLabel); // 通过键获得值
		
		if (!mt.node1.containsKey(node.getId())) {

			mt.node1.put(node.getId().toString(), node);
			mt.color.put(node.getId().toString(), node.getColor());
		}


		node.setColor(new Color(255, 255, 0)); // 高亮目标节点
		
		float x = node.x();
		float y = node.y();
		PreviewSketch1 previewSketch = new PreviewModule().getPreviewSketch();
		previewSketch.nodeMoveCenter(x, y);

	}
}
