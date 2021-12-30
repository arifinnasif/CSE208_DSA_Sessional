#include <bits/stdc++.h>

using namespace std;

struct Node {
	int v;
	int cost;

	Node(int arg_v, int arg_cost) {
		v = arg_v;
		cost = arg_cost;
	}

	bool operator<(const Node other_node) const {
		return cost > other_node.cost;
	}
};

int dijkstra(int n, int m, vector<vector<pair<int, int>>> adj, int from, int to, vector<int> & path) {
	vector<int> parent(n, -1);
	vector<int> d(n, INT_MAX);
	vector<bool> used(n, false);

	d[from] = 0;

	priority_queue<Node> pq;

	pq.push(Node(from, 0));
//	used[from] = true;

	while(!pq.empty()) {
		Node node = pq.top(); pq.pop();

		if(used[node.v]) {
			continue;
		}

		for(pair<int, int> p : adj[node.v]) {
			if(d[p.first] > node.cost + p.second) {
				d[p.first] = node.cost + p.second;
				parent[p.first] = node.v;
				pq.push(Node(p.first, d[p.first]));
			}
		}
		used[node.v] = true;
	}

	path.clear();

	for (int i = to; i != -1; i = parent[i]) {
		path.push_back(i);
	}

	reverse(path.begin(), path.end());

	return d[to];
}
