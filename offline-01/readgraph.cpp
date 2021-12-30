#ifndef READGRAPH_CPP_
#define READGRAPH_CPP_

// <readgraph>

#include <iostream>
#include <vector>
#include "edge.cpp"

using namespace std;

int NUMBER_OF_VERTICES;
int NUMBER_OF_EDGES;

vector< vector <int>> readgraph(bool is_directed = false) {
	int n, e;
	cin>>n>>e;

	NUMBER_OF_VERTICES = n;
	NUMBER_OF_EDGES = e;

	vector<vector<int>> adj(n);

	int a, b;
	for(int i = 0; i < e; i++) {
		cin>>a>>b; //this line causes seg fault
		adj[a].push_back(b);

		if(!is_directed) {
			adj[b].push_back(a);
		}
	}

	return adj;
}


vector<Edge> readedges(bool is_weighted = true) {
	int n, e;
	cin>>n>>e;

	NUMBER_OF_VERTICES = n;
	NUMBER_OF_EDGES = e;

	vector<Edge> edges;

	int u;
	int v;
	double w = 0.0;
	for(int i = 0; i < e ; i++) {
		if (is_weighted)
			cin>>u>>v>>w;
		else
			cin>>u>>v;
		edges.push_back({u,v,w});
	}

	return edges;
}

vector<vector<Edge>> readedgygraph(bool is_directed = false) {
	int n, e;
	cin>>n>>e;

	NUMBER_OF_VERTICES = n;
	NUMBER_OF_EDGES = e;

	vector<vector<Edge>> adj(n);

	int u;
	int v;
	double w;
	for(int i = 0; i < e; i++) {
		cin>>u>>v>>w;
		adj[u].push_back({u,v,w});

		if (!is_directed) {
			adj[v].push_back({v,u,w});
		}
	}

	return adj;
}

// </readgraph>

#endif
