package comp2402a5;
// Thanks to Pat Morin for the skeleton of this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;

public class SnakesAndLadders {
	/**
	 * Your code goes here
	 * 
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */

	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		int N = Integer.parseInt(r.readLine());

		final int START_INDEX = 1;
		final int END_INDEX = N * N;

		AdjacencyLists g = new AdjacencyLists(END_INDEX + START_INDEX);
		// Graph g = new AdjacencyLists(N + 1);

		for (int i = START_INDEX; i <= END_INDEX; i++) {
			g.addEdge(i - 1, i);
		}

		for (String line = r.readLine(); line != null; line = r.readLine()) {
			String[] splitted = line.split(" ");
			int u = Integer.parseInt(splitted[0]), v = Integer.parseInt(splitted[1]);

			// ladder
			// if (u < v) {
			// g.removeEdge(u, u + 1);
			// g.addEdge(u, v);
			// }

			// snake
			// else {
			// g.removeEdge(u, u + 1);
			// g.addEdge(u, v);
			// }

			/*
			 * "u - 1" and not "u" here is important. should jump before reaching the target
			 * node, otherwise it would have been too late.
			 */
			// if (u != 1)
			// g.removeEdge(u - 1, 0);
			g.addEdge(u, v);
		}

		int shortestDist = Algorithms.bfs(g, START_INDEX, END_INDEX);
		w.println((int) Math.ceil(shortestDist / 6.0));
	}

	/**
	 * The driver. Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call
	 * doIt.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop - start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
