#include "dijkstra.cpp"
#include "readgraph_dijkstra.cpp"

int main(int argc, char ** argv) {
	vector<vector<pair<int, int>>> adj = readgraph_dijkstra("in");
	vector<int> path;
	int ans = dijkstra(NUMBER_OF_NODES, NUMBER_OF_EDGES, adj, SOURCE, DESTINATION, path);

	cout<<"Shortest path cost: "<<ans<<endl;

	for(int i = 0; i < path.size(); i++) {
		cout<<path[i];
		if(i != path.size() - 1) cout<<" -> ";
		else cout<<endl;
	}

	return 0;
}
