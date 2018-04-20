public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            // TODO
            Edge[] path = new Edge[G.numberOfV()];
            int[] distTo = new int[G.numberOfV()];
            boolean[] closed = new boolean[G.numberOfV()];
            IndexPQ<Integer> Q = new IndexPQ<>(G.numberOfE());

            for (int i = 0; i < distTo.length; i++) {
                distTo[i] = Integer.MAX_VALUE;
            }

            for (int v = 0; v < G.numberOfV(); v++) {
                if (!closed[v])
                {
                    distTo[v] = 0;
                    Q.insert(v,distTo[v]);
                    while (!Q.isEmpty())
                    {
                        int min = Q.delMin();
                        closed[min] = true;

                        for (Edge edge : G.edges(min))
                        {
                            int o = edge.other(min);
                            if (edge.weight() < distTo[o] && !closed[o])
                            {
                                distTo[o] = edge.weight();
                                path[o] = edge;
                                if (!Q.contains(o))
                                {
                                    Q.insert(o,distTo[o]);
                                }
                                else
                                {
                                    Q.decreaseKey(o,distTo[o]);
                                }
                            }
                        }
                    }
                }
            }

            int cost = 0;

            for (Edge edge : path)
            {
                if (edge != null)
                {
                    cost += edge.weight();
                }
            }
            return cost;
        }

    }

