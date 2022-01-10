# include<bits/stdc++.h>
# define PARENT_NOTSET -1
# define PARENT_NONE INT_MIN

using namespace std;

typedef pair<int, int> PII;

vector<int> parent;
vector<vector<int>> adj;
vector<vector<int>> res_cap;

int bfs(vector<vector<int>> adj, vector<vector<int>> res_cap, int source, int sink, vector<int>& parent) {
	// Initialization
	// Assume parent is initialized before. If not, uncomment following, set n to number of nodes
	// parent.assign(parent.begin(), parent.end(), PARENT_NOTSET);
	//
	parent[source] = PARENT_NONE;
	//
	queue<PII> bfs_queue;
	bfs_queue.push(make_pair(source, INT_MAX));

	while(!bfs_queue.empty()) {
		int u = bfs_queue.front().first;
		int f = bfs_queue.front().second;

		bfs_queue.pop();

		for (int v : adj[u]) {
			if (parent[v] == PARENT_NOTSET && res_cap[u][v] !=0) {
				parent[v] = u;
				int path_flow = min(f, res_cap[u][v]);
				if (v != sink) {
					bfs_queue.push(make_pair(v, path_flow));
				} else {
					return path_flow;
				}
			}
		}
	}

	// No aug path
	//
	return 0;
}


int find_aug(vector<vector<int>> adj, vector<vector<int>> res_cap, int source, int sink, vector<int>& parent) {
	fill(parent.begin(), parent.end(), PARENT_NOTSET);
	parent[source] = PARENT_NONE;
	return bfs(adj, res_cap, source, sink, parent);
}


int get_max_flow(vector<vector<int>> adj, vector<vector<int>> res_cap, int source, int sink) {
	int number_of_nodes = adj.size();
	vector<int> parent(number_of_nodes);
	int net_flow = 0;

	int path_flow = 0;
	while ((path_flow = find_aug(adj, res_cap, source, sink, parent))) {
		net_flow = net_flow + path_flow;

		int i = sink;
		while(i != source) {
			int prev = parent[i];
			if(res_cap[prev][i] != INT_MAX) res_cap[prev][i] -= path_flow;
			if(res_cap[i][prev] != INT_MAX) res_cap[i][prev] += path_flow;
			i = prev;
		}
	}

	return net_flow;
}
