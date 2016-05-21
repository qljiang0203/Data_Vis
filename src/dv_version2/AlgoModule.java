/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;

/**
 *
 * @author qljiang
 */
public class AlgoModule {
	
	double gravity;
	double repulseStrength;
	double speed;
	float area;
	float optimalDistance;
	float stepRatio;

	AlgoModule() {
		
		gravity = 30d;
		repulseStrength = 12000d;
		speed = 1d;
		area = 10000f;
		optimalDistance = 200f;
		stepRatio = 500f;
	}

	public void forceAtlasLayout(SingletonObject singletonobject, double repulseStrength, double gravity) {

		GraphModel graphModel = singletonobject.getgraphModel();
		ForceAtlasLayout secondLayout = new ForceAtlasLayout(null);
		secondLayout.setGraphModel(graphModel);
		secondLayout.resetPropertiesValues();
		// 重新调节布局参数
		secondLayout.setRepulsionStrength(repulseStrength);
		secondLayout.setAdjustSizes(true);
		secondLayout.setGravity(gravity);
		secondLayout.setSpeed(100d);
		secondLayout.setConverged(false);
		secondLayout.setOutboundAttractionDistribution(true);
		// 进行迭代
		secondLayout.initAlgo();
		for (int i = 0; i < 2000 && secondLayout.canAlgo(); i++) {
			secondLayout.goAlgo();
		}
		secondLayout.endAlgo();

	}

	public void yiFanHuLayout(SingletonObject singletonobject, float optimalDistance, float stepRatio) {

		GraphModel graphModel = singletonobject.getgraphModel();

		YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
		layout.setGraphModel(graphModel);
		layout.resetPropertiesValues();
		layout.setOptimalDistance(optimalDistance);
		layout.setStepRatio(stepRatio / 100);
		layout.initAlgo();
		for (int i = 0; i < 2000 && layout.canAlgo(); i++) {
			layout.goAlgo();
		}
		layout.endAlgo();

	}

	public void FRLayout(SingletonObject singletonobject, double gravity, double speed, float area) {
		GraphModel graphModel = singletonobject.getgraphModel();
		FruchtermanReingold thirdLayout = new FruchtermanReingold(null);
		thirdLayout.setGraphModel(graphModel);
		thirdLayout.resetPropertiesValues();
		thirdLayout.setGravity(gravity);
		thirdLayout.setSpeed(speed);
		thirdLayout.setArea(area);
		thirdLayout.initAlgo();
		for (int i = 0; i < 2000 && thirdLayout.canAlgo(); i++) {
			thirdLayout.goAlgo();
		}
		thirdLayout.endAlgo();
	}
}
