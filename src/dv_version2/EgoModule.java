/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv_version2;

import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.Query;
import org.gephi.filters.plugin.graph.EgoBuilder;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.GraphView;
import org.gephi.graph.api.Node;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

/**
 *
 * @author tang
 */
public class EgoModule {
    
    public void egoNode(SingletonObject singletonobject,DirectedGraph graph, int egoNodeID)
    {   
        FilterController filterController = singletonobject.getfilterController();
        Workspace workspace = singletonobject.getWorkspace();
        GraphModel graphModel = singletonobject.getgraphModel();
        if(egoNodeID!=-1)
       {
             EgoBuilder.EgoFilter egoFilter = new EgoBuilder.EgoFilter();
             Node [] temp=new Node[graph.getNodeCount()];
         int i=0;
         for (Node node : Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace).getGraph().getNodes()) 
         {
               temp[i++]=node;
         } 
         egoFilter.setPattern(temp[egoNodeID].getLabel()); //Regex accepted
         egoFilter.setDepth(1);
         Query queryEgo = filterController.createQuery(egoFilter);
         GraphView viewEgo = filterController.filter(queryEgo);
         graphModel.setVisibleView(viewEgo);    //Set the filter result as the visible view
        }
    }
    
}
