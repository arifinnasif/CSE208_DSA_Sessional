#include <bits/stdc++.h>
#include "readgraph.cpp"
#include "edge.cpp"



double prim(int n, vector<vector<Edge>> adj, vector<Edge> & result) {
	//priority_queue<Edge> q;
	multiset<Edge> q;

	vector<bool> used;
	used.assign(n, false);

	double total_cost = 0.0;
	q.insert({-1,0,0.0});
	for(int i = 0; i < n;) {
		Edge e = *q.begin();
		//cout<<e.u<<" "<<e.v<<" "<<e.w<<endl;
		q.erase(q.begin());
		//cout<<endl<<"q size"<<q.size()<<endl;
		cout<<" edge "<<e.u<<" "<<e.v<<endl;
		if(used[e.v]) continue;
		//cout<<"m";
//		if (e.v != -1) {
		used[e.v] = true;
		i++;
		total_cost = total_cost + e.w;
		if (e.u != -1)
			result.push_back(e);
//		}

		for(Edge neighbour_edge : adj[e.v]) {
			if (!used[neighbour_edge.v]) {
				q.insert(neighbour_edge);
			}
		}
	}

	return total_cost;
}


int main(int argc, char ** argv) {
	auto adj = readedgygraph(false);
	vector<Edge> result;
	double total_cost = prim(NUMBER_OF_VERTICES, adj, result);

	cout<<total_cost<<endl;

	for (auto e : result) {
		cout<<e.u<<" "<<e.v<<endl;
	}

	return 0;
}
