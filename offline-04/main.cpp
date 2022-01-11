# include <bits/stdc++.h>
# include "ff.cpp"

using namespace std;

int main(int argc, char** argv) {
	int number_of_teams;
	cin>>number_of_teams;

	vector<string>			teamname(number_of_teams);
	vector<int>				w(number_of_teams);
	vector<int>				l(number_of_teams);
	vector<int>				r(number_of_teams);
	vector<vector<int>>		g(number_of_teams, vector<int>(number_of_teams, 0));

	for (int i = 0; i < number_of_teams; i++) {
		cin>>teamname[i]>>w[i]>>l[i]>>r[i];
		for (int j = 0; j < number_of_teams; j++) {
			cin>>g[i][j];
		}
	}

	for (int k = 0; k < number_of_teams; k++) {
		int number_of_nodes = (number_of_teams-2)*(number_of_teams-1)/2+(number_of_teams-1)+2;
		vector<vector<int>> adj(number_of_nodes);
		vector<vector<int>> res_cap(number_of_nodes, vector<int>(number_of_nodes, 0));
		int node_index = 0;

		int source_index = node_index;

		int team_index[number_of_teams-1];
		int sink_index=++node_index;
		
		bool is_trivial = false;

		for(int i = 0; i < number_of_teams; i++) {
			if(i == k) continue;
			team_index[i] = ++node_index;

			adj[team_index[i]].push_back(sink_index);
			adj[sink_index].push_back(team_index[i]);
			if (w[k]+r[k] < w[i]) {
				is_trivial = true;
				break;
			}
			res_cap[team_index[i]][sink_index] = w[k]+r[k]-w[i];
		}
		
		if(is_trivial) {
			cout<<teamname[k]<<endl;
			continue;
		}

		int total_out_arc = 0;
		for(int i = 0; i < number_of_teams; i++) {
			for(int j = i+1; j < number_of_teams; j++) {
				if (i == k || j == k) continue;
				
				int game_index = ++node_index;
				adj[source_index].push_back(game_index);
				adj[game_index].push_back(source_index);
				res_cap[source_index][game_index] = g[i][j];

				adj[game_index].push_back(team_index[i]);
				adj[team_index[i]].push_back(game_index);
				res_cap[game_index][team_index[i]] = INT_MAX;
				
				adj[game_index].push_back(team_index[j]);
				adj[team_index[j]].push_back(game_index);
				res_cap[game_index][team_index[j]] = INT_MAX;

				total_out_arc += g[i][j];
			}
		}

		int max_flow = get_max_flow(adj, res_cap, source_index, sink_index);
		
		// cout<<teamname[k]+"'s max_flow = "<<max_flow<<endl;

		if (total_out_arc > max_flow) cout<<teamname[k]<<endl;
	}

	return 0;
}
