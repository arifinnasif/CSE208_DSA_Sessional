#include <bits/stdc++.h>
#include "edge.cpp"

using namespace std;


int bellmanford(int n, int m, vector<Edge> edges, int from, int to, vector<int>& path) {
	vector<int> parent(n, -1);
	vector<int> d(n, INT_MAX);

	d[from] = 0;

	for(int i = 0; i < n-1; i++) {
		for(Edge e : edges) {
			if (d[e.u] < INT_MAX && d[e.v] > d[e.u] + e.w) {
				d[e.v] = d[e.u] + e.w;
				parent[e.v] = e.u;
			}
		}
	}

	path.clear();

	for(Edge e : edges) {
		if (d[e.v] > d[e.u] + e.w) {
			return INT_MIN;
		}
	}

	for(int i = to; i != -1; i = parent[i]) {
		path.push_back(i);
	}

	reverse(path.begin(), path.end());
	
	return d[to];
}

