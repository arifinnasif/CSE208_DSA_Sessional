#ifndef PRIM_CPP_
#define PRIM_CPP_

#include <bits/stdc++.h>
#include "node.cpp"
// #include "readgraph_MST.cpp"

using namespace std;

typedef pair<int, double> P_ID; // u -> v with weight w gets stored as (v, w)

/*
vector<vector<P_ID>> readgraph() {
	int n, e;

	cin>>n>>e;

	vector<vector<P_ID>> _adj(n);

	int		u;
	int		v;
	double	w;

	for(int i = 0; i < e; i++) {
		cin>>u>>v>>w;
		_adj[u].push_back(make_pair(v,w));
		_adj[v].push_back(make_pair(u,w));
	}

	return _adj;
}
*/


double prim(int start_node, int n, vector<vector<P_ID>> adj, vector<pair<int, int>> & mst) {
	// int n = adj.size();

	vector<int> parent;
	vector<double> cost;
	vector<bool> used;

	double ans = 0;

	parent.assign(n, -1);
	cost.assign(n, INT_MAX);
	used.assign(n, false);

	cost[start_node] = 0;

	// priority_queue<Node, vector<Node>, greater<Node>> pq; // use this only if operator< is defined as <;

	priority_queue<Node> pq;
	cost[start_node] = 0;
	pq.push(Node(start_node, 0));

	while(!pq.empty()) {
		Node node_v = pq.top(); pq.pop();

		if(used[node_v.v]) continue;

		used[node_v.v] = true;
/**
 * used[i] is only true if i is in the tree. that is all the edges adjacent to it are checked for better
 * cost and (parent[i], i) is the light edge in a cut
 */

		ans += cost[node_v.v];
		// ans += node_v.w;
		if(parent[node_v.v] != -1)
			mst.push_back(make_pair(parent[node_v.v], node_v.v));

		for(P_ID u : adj[node_v.v]) {
			if(!used[u.first] && u.second < cost[u.first]) {
				cost[u.first] = u.second;
				parent[u.first] = node_v.v;
				pq.push(Node(u.first, u.second));
			}
		}
	}

	return ans;
}

/*

int main() {
	vector<vector<P_ID>> adj;

	adj = readgraph();
	vector<pair<int, int>> mst;

	double ans = prim(0, adj, mst);

	cout<<ans<<endl;

	for(pair<int, int> e : mst) {
		cout<<e.first<<" "<<e.second<<endl;
	}

	return 0;
}
*/

#endif
