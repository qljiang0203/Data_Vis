/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;

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
 * @author qljiang
 */

public class SingletonObject {

	// 项目、工作区
	ProjectController pc;
	Workspace workspace;

	// 各个模型
	PreviewModel model;
	GraphModel graphModel;
	AppearanceModel appearanceModel;

	// 各个控制器
	PreviewController previewController;
	ImportController importController;
	FilterController filterController;
	AppearanceController appearanceController;
	LayoutController layoutController;

	String fileName;

	public SingletonObject() {

		/**
		 * 通过Lookup全局类，得到各个对象的操作模型和控制器
		 */
		// 创建一个项目和工作空间是必须的，是后续工作的前提
		this.pc = Lookup.getDefault().lookup(ProjectController.class);
		pc.newProject();
		this.workspace = pc.getCurrentWorkspace(); // Workspace是一个内部顶级容器，类似一个数据中心，所有的数据最终要汇集到数据中心中，包括图形的结构、如何渲染、布局的添加等

		this.previewController = Lookup.getDefault().lookup(PreviewController.class);
		this.importController = Lookup.getDefault().lookup(ImportController.class);
		this.layoutController = Lookup.getDefault().lookup(LayoutController.class);
		this.filterController = Lookup.getDefault().lookup(FilterController.class);

		this.model = Lookup.getDefault().lookup(PreviewController.class).getModel(); // PreviewModel等于获得了一个如何进行预览展现的操作入口
		this.graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(); // GraphModel好比存放整个图形元素的容器，包括节点、边、标签等信息

	}

	public void importData() {

		Container container = null;
		
		try {

			File file = new File(fileName);
			container = importController.importFile(file); // 导入数据，使用Container容器接收
			container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED); // DIRECTED /UNDIRECTED /MIXED

		} catch (Exception ex) {
			ex.printStackTrace();
			return;

		}

		// Append imported data to GraphAPI， 使用导入接口控制器将Container加载到工作空间Workspace中
		importController.process(container, new DefaultProcessor(), workspace);
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		importData();
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public GraphModel getgraphModel() {
		return graphModel;
	}

	public PreviewModel getPreviewModel() {
		return model;
	}

	public ImportController getImportController() {
		return importController;
	}

	public FilterController getfilterController() {
		return filterController;
	}

	public AppearanceController getappearanceController() {
		return appearanceController;
	}

	public PreviewController getpreviewController() {
		return previewController;
	}

	public LayoutController getlayoutController() {
		return layoutController;
	}

}
