#include "bellmanford.cpp"
#include "readgraph_bellmanford.cpp"

int main(int argc, char ** argv) {
	vector<Edge> edges = readgraph_bellmanford("in");

	vector<int> path;

	int ans = bellmanford(NUMBER_OF_NODES, NUMBER_OF_EDGES, edges, SOURCE, DESTINATION, path);

	if(ans == INT_MIN) {
		cout<<"The graph contains a negative cycle"<<endl;
		return 0;
	}
	
	cout<<"The graph does not contain a negative cycle"<<endl;

	cout<<"Shortest path cost: "<<ans<<endl;

	for(int i = 0; i < path.size(); i++) {
		cout<<path[i];
		if(i != path.size() - 1) cout<<" -> ";
		else cout<<endl;
	}

	return 0;
}
