package org.liege.lab;

import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class RandomizedContractionTest {

    @Test
    public void testRandomizedContraction() throws IOException {

        Map<String, List<String>> adjList = new HashMap<>();
        Set<String> vertices = new HashSet<>();

        List<String> lines = FileUtils.readLines(new File("src/test/resources/kargerMinCut.txt"), "UTF-8");

        RandomizedContraction algo = new RandomizedContraction();

        int minCut = Integer.MAX_VALUE;

        for (int i=0; i<3000; ++i) {
            adjList.clear();
            vertices.clear();
            for (String line : lines) {
                String [] nodes = line.split("\\s+");
                vertices.add(nodes[0]);
                //System.out.println("node: " + nodes[0]);
                List<String> neighbors = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(nodes, 1, nodes.length)));
                adjList.put(nodes[0], neighbors);
            }

            int currentMinCut = algo.getRandomizedContraction(adjList, vertices);
            if (minCut > currentMinCut) {
                minCut = currentMinCut;
                //System.out.println("node: " + currentMinCut);
            }
        }

        System.out.println("Min Cut:" + minCut);
    }
}
