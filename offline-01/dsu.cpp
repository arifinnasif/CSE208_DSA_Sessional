#ifndef DSU_CPP_
#define DSU_CPP_


#include <bits/stdc++.h>

using namespace std;

// <DSU>
class DSU {
	private:
		vector<int> dsu_parent;
		vector<int> dsu_rank;
		
		void makeset(int n) {
			dsu_parent.resize(n);
			dsu_rank.assign(n, 0);
			for (int i = 0; i < n; i++) {
				dsu_parent[i] = i;
			}
		}

	public:
		DSU(int n) {
			makeset(n);
		}
		
		int findset(int v) {
			if(v == dsu_parent[v]) {
				return v;
			} else {
				return findset(dsu_parent[v]);
			}
		}
		
		void unionset(int u, int v) {
			int root_u = findset(u);
			int root_v = findset(v);
		
			if (root_u == root_v) return;
		
			if (dsu_rank[root_u] == dsu_rank[root_v]) {
				dsu_rank[root_u]++;
			}
		
			if (dsu_rank[root_u] < dsu_rank[root_v]) {
				dsu_parent[root_u] = dsu_parent[root_v];
			} else {
				dsu_parent[root_v] = dsu_parent[root_u];
			}
		}
};
// </DSU>

#endif
