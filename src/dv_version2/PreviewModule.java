/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.api.RenderTarget;
import org.gephi.preview.types.DependantOriginalColor;
import dv_version2.plugins.preview.PreviewSketch1;

/**
 *
 * @author Administrator
 */

public class PreviewModule {

	PreviewModel model;
	PreviewController previewController;
	public static PreviewSketch1 previewSketch;

	public PreviewModule(){
		super();
	}

	public PreviewModule(SingletonObject singletonobject) {

		this.model = singletonobject.getPreviewModel();
		this.previewController = singletonobject.getpreviewController();

		// 预览效果配置,通过PreviewModel设置展示的参数，如是否显示节点上的标签、边的颜色粗细等
		model.getProperties().putValue(PreviewProperty.SHOW_NODE_LABELS, Boolean.TRUE);
		// model.getProperties().putValue(PreviewProperty.EDGE_COLOR, new
		// EdgeColor(Color.BLACK));
		model.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.BLACK));
		model.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE); // 边是否弯曲
		model.getProperties().putValue(PreviewProperty.EDGE_OPACITY, 30f); // 边的透明度
		model.getProperties().putValue(PreviewProperty.EDGE_RADIUS, 20f);
		// model.getProperties().putValue(PreviewProperty.NODE_LABEL_OUTLINE_OPACITY,0.0);
		model.getProperties().putValue(PreviewProperty.EDGE_LABEL_OUTLINE_COLOR,
				new DependantOriginalColor(Color.BLACK));
		model.getProperties().putValue(PreviewProperty.EDGE_THICKNESS, 1f);
		model.getProperties().putValue(PreviewProperty.NODE_LABEL_FONT,
				model.getProperties().getFontValue(PreviewProperty.NODE_LABEL_FONT).deriveFont(8));

		previewController.refreshPreview(); // 进入refreshPreview（）方法可以发现是更新Workspace对象，也就是更新其中的数据

	}

	public JPanel display() {

		G2DTarget target = (G2DTarget) previewController.getRenderTarget(RenderTarget.G2D_TARGET); // 渲染目标
		previewSketch = new PreviewSketch1(target);
		previewController.refreshPreview();
		previewSketch.resetZoom();

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); // 边界布局

		panel.add(previewSketch, BorderLayout.CENTER);
		panel.setVisible(true);

		return panel;
	}

	public PreviewSketch1 getPreviewSketch(){
		
		return previewSketch;
	}

}
