#include <bits/stdc++.h>
#include "readgraph_apsp.cpp"
#include "floyd-warshall.cpp"

using namespace std;

void print_mat(vector<vector<int>> result) {
	for(auto row : result) {
		for(int entry : row) {
			if (entry == INT_MAX)
				cout<<"INF ";
			else
				cout<<entry<<" ";
		}
		cout<<endl;
	}
}

int main(int argc, char** argv) {
	vector<vector<int>> adj = readgraph_APSP("infile.txt");

	vector<vector<int>> result = flwar(adj);

	cout<<"Shortest distance matrix"<<endl;
	print_mat(result);

	return 0;
}
