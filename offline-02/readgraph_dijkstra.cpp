#ifndef READGRAPH_DIJKSTRA_CPP_
#define READGRAPH_DIJKSTRA_CPP_

#include <bits/stdc++.h>

using namespace std;

int NUMBER_OF_NODES;
int NUMBER_OF_EDGES;
int SOURCE;
int DESTINATION;

vector<vector<pair<int, int>>> readgraph_dijkstra(string filename) {
	if(filename.compare("stdin") != 0)
		freopen(filename.c_str(), "r", stdin);
	cin>>NUMBER_OF_NODES>>NUMBER_OF_EDGES;
	vector<vector<pair<int, int>>> adj(NUMBER_OF_NODES);
	int u;
	int v;
	int w;
	for(int i = 0; i < NUMBER_OF_EDGES; i++) {
		cin>>u>>v>>w;
		adj[u].push_back(make_pair(v,w));
	}

	cin>>SOURCE>>DESTINATION;

	return adj;
}

#endif
