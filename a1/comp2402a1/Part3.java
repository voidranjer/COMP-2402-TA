package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class Part3 {

	/**
	 * Your code goes here - see Part0 for an example
	 * 
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		int lineCount = 0;
		String first1000 = "";
		ArrayDeque<String> latest1000 = new ArrayDeque<String>();

		for (String line = r.readLine(); line != null; line = r.readLine()) {
			lineCount++;

			if (lineCount < 1000)
				continue;

			if (lineCount == 1000) {
				first1000 = line;
			}

			latest1000.addLast(line);

			if (latest1000.size() > 2402) {
				latest1000.removeFirst();
			}

		}

		if (lineCount >= 1000 && lineCount < 2402) {
			w.println(first1000);
		} else if (lineCount >= 2402) {
			for (int i = 0; i < 1000; i++)
				latest1000.removeFirst();
			w.println(latest1000.removeFirst());
		}
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
