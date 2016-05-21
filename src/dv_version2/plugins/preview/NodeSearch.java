package dv_version2.plugins.preview;

import dv_version2.PreviewModule;
import dv_version2.SingletonObject;
import java.awt.Color;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.Node;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewMouseEvent;
import org.gephi.preview.api.Vector;
import org.openide.util.Lookup;

/**
 *
 * @author qljiang
 */
public class NodeSearch {

	// 根据搜索框输入的Id获取节点，高亮并移至画布中心
	public void nodeSearch(SingletonObject singletonObject, int nodeId) {

		Workspace workspace = singletonObject.getWorkspace();
		Graph graph = singletonObject.getgraphModel().getGraph();
		graph.readUnlockAll();

		if (nodeId != -1) {
			Node[] temp = new Node[graph.getNodeCount() + 1]; // +1：防止id起始大小不同造成越界异常

			for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph()
					.getNodes()) {
				String sNode = node.getId().toString();
				int iNode = (int) Double.parseDouble(sNode);
				temp[iNode] = node;

			}

			temp[nodeId].setColor(new Color(255, 255, 0)); // 高亮目标节点
			System.out.println(temp[nodeId].getLabel());

			PreviewSketch1 previewSketch = new PreviewModule().getPreviewSketch();
			float x = temp[nodeId].x();
			float y = temp[nodeId].y();
			previewSketch.nodeMoveCenter(nodeId, x, y);

		}
	}
}
