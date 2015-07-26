package org.liege.lab;

import java.util.*;

class RandomizedContraction {

    public int getRandomizedContraction(Map<String, List<String>> adjList, Set<String> vertices) {

        while (vertices.size() > 2) {

            List<String> vv = new LinkedList<>(vertices);
            Collections.shuffle(vv);
            String U = vv.get(0);
            List<String> neighborsU = adjList.get(U);

            Collections.shuffle(neighborsU);
            String V = neighborsU.get(0);
            List<String> neighborsV = adjList.get(V);

            while (neighborsV == null || neighborsV.size() == 0) {
                Collections.shuffle(neighborsU);
                V = neighborsU.get(0);
                neighborsV = adjList.get(V);
            }

            String UV = U + "," + V;
            // remove U and V from the graph
            adjList.remove(U);
            adjList.remove(V);
            vertices.remove(U);
            vertices.remove(V);
            // merge U's neighbors and V's neighbors
            neighborsU.addAll(neighborsV);
            while (neighborsU.remove(U));
            while (neighborsU.remove(V));
            adjList.put(UV, neighborsU);
            vertices.add(UV);

            // replace U and V by U,V in the graph
            Iterator<String> itr = vertices.iterator();
            while (itr.hasNext()) {
                String v = itr.next();
                List<String> neighbor = adjList.get(v);
                if (neighbor != null && neighbor.size() > 0) {
                    while (neighbor.remove(U)) {
                        neighbor.add(UV);
                    }
                    while (neighbor.remove(V)) {
                        neighbor.add(UV);
                    }
                } else {
                    itr.remove();
                    adjList.remove(v);
                }
            }
        }

        List<String> merge = adjList.get(vertices.iterator().next());

        return merge.size();
    }

    public int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min)) + min;

        return randomNum;
    }
}
