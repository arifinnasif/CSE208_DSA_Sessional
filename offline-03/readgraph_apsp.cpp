#ifndef READGRAPH_APSP_CPP_
#define READGRAPH_APSP_CPP_

#include <bits/stdc++.h>

using namespace std;

int NUMBER_OF_NODES;
int NUMBER_OF_EDGES;


vector<vector<int>> readgraph_APSP(string input_file) {
	if(input_file != "stdin")
		freopen(input_file.c_str(), "r", stdin);

	cin>>NUMBER_OF_NODES>>NUMBER_OF_EDGES;

	vector<vector<int>> adj(NUMBER_OF_NODES /*rowcount*/, vector<int>(NUMBER_OF_NODES /*columncount*/, INT_MAX));

	for(int i = 0; i < NUMBER_OF_NODES; i++) {
		adj[i][i] = 0;
	}

	int u;
	int v;
	int w;

	for(int i = 0; i < NUMBER_OF_EDGES; i++) {
		cin>>u>>v>>w;

		adj[u-1][v-1] = w;
	}

	return adj;
}


vector<vector<int>> readgraph_APSP() {
	return readgraph_APSP("stdin");
}


#endif
