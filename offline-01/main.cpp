#include <bits/stdc++.h>
#include "readgraph_MST.cpp"
#include "edge.cpp"
#include "krus.cpp"
#include "prim.cpp"

using namespace std;

int main(int argc, char ** argv) {
	vector<vector<pair<int, double>>> adj;
	vector<Edge> edges;

	readgraph_MST("mst.in", edges, adj);

	vector<pair<int, int>> mst_kruskal;
	vector<pair<int, int>> mst_prim;
	double cost_kruskal;
	double cost_prim;
	cost_kruskal = kruskal(NUMBER_OF_VERTICES, edges, mst_kruskal);
	cost_prim = prim(0, NUMBER_OF_VERTICES, adj, mst_prim);

	cout<<"Cost of the minimum spanning tree (kruskal) : "<<cost_kruskal<<endl;
	cout<<"Cost of the minimum spanning tree (prim) : "<<cost_prim<<endl;

	cout<<"List of edges selected by Kruskal: {";
	for(int i = 0; i < mst_kruskal.size(); i++) {
		cout<<"("<<mst_kruskal[i].first<<","<<mst_kruskal[i].second<<")";
		if (i != mst_kruskal.size() - 1) {
			cout<<", ";
		} else {
			cout<<"}"<<endl;
		}
	}

	cout<<"List of edges selected by Prim's: {";
	for(int i = 0; i < mst_prim.size(); i++) {
		cout<<"("<<mst_prim[i].first<<","<<mst_prim[i].second<<")";
		if (i != mst_prim.size() - 1) {
			cout<<", ";
		} else {
			cout<<"}"<<endl;
		}
	}
	return 0;
}
