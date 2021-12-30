#include <bits/stdc++.h>

using namespace std;

vector<vector<int>> flwar(vector<vector<int>> adj) {
	int n = adj.size();

	for(int i = 0; i < n; i ++) {
		if (n != adj[i].size()) {
			cerr<<"adj[][] size error on "<<i<<endl;
			exit(1);
		}
	}

	for(int k = 0; k < n; k++) {

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(adj[i][k] != INT_MAX && adj[k][j] != INT_MAX) {
					if(adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
	}

	return adj;
}
