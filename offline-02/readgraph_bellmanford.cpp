#ifndef READGRAPH_BELLMANFORD_CPP_
#define READGRAPH_BELLMANFORD_CPP_

#include <bits/stdc++.h>
#include "edge.cpp"

using namespace std;

int NUMBER_OF_NODES;
int NUMBER_OF_EDGES;

int SOURCE;
int DESTINATION;

vector<Edge> readgraph_bellmanford(string filename) {
	if(filename.compare("stdin") != 0)
		freopen(filename.c_str(), "r", stdin);
	cin>>NUMBER_OF_NODES>>NUMBER_OF_EDGES;

	vector<Edge> edges;

	int u;
	int v;
	int w;

	for(int i = 0; i < NUMBER_OF_EDGES; i++) {
		cin>>u>>v>>w;
		edges.push_back({u,v,w});
	}

	cin>>SOURCE>>DESTINATION;

	return edges;
}


#endif
