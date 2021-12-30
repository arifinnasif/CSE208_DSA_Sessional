#ifndef EDGE_CPP_
#define EDGE_CPP_

// <EDGE>

struct Edge {
	int u;
	int v;
	double w;

	bool operator<(const Edge & other) const {
		return w < other.w;
	}
};

// </EDGE>

#endif
