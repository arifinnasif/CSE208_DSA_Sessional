#ifndef NODE_CPP_
#define NODE_CPP_

struct Node { // just to make a priority queue that prioritize on w. could make it a pair. but that would remove the prioritization sceme.
	Node() {

	}

	Node(int arg_v, double arg_w) {
		v = arg_v;
		w = arg_w;
	}

	int		v;
	double	w;
};

bool operator<(const Node node1, const Node node2) {
	return node1.w > node2.w;
}

#endif
