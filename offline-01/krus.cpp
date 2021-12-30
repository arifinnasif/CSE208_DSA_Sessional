#ifndef KRUS_CPP_
#define KRUS_CPP_

#include <bits/stdc++.h>

#include "edge.cpp"
#include "readgraph.cpp"
#include "dsu.cpp"

using namespace std;




double kruskal(int n, vector<Edge> edges, vector<pair<int, int>> & mst) {
	double total_cost = 0;
	DSU dsu(n);

	sort(edges.begin(), edges.end());
	for(Edge e : edges) {
		if(dsu.findset(e.u) == dsu.findset(e.v)) continue;

		total_cost = total_cost + e.w;
		mst.push_back(make_pair(e.u, e.v));
		dsu.unionset(e.u, e.v);
	}

	return total_cost;
}


/*

int main(int argc, char ** argv) {
	auto edges = readedges();
	vector<Edge> result;
	double cost = kruskal(NUMBER_OF_VERTICES, edges, result);

	cout<<cost<<endl;
	for(auto e : result) {
		cout<<e.u<<" "<<e.v<<endl;
	}
	return 0;
}
*/

#endif
