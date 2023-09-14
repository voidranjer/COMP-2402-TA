package comp2402a1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Part7 {

	/**
	 * Your code goes here - see Part0 for an example
	 * 
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		ArrayList<String[]> blocks = new ArrayList<>();

		int blockLength = 1;
		int currLengthPos = 0;
		String[] block = new String[blockLength];

		for (String line = r.readLine(); line != null; line = r.readLine()) {
			if (currLengthPos >= blockLength) {
				blocks.add(block);
				blockLength++;
				block = new String[blockLength];
				block[0] = line; // possibly check for reset line here
				currLengthPos = 1;
			}

			block[currLengthPos] = line;
			currLengthPos++;
			if (line.compareTo("***reset***") == 0) {
				blocks.add(block);
				blockLength = 1;
				block = new String[blockLength];
				currLengthPos = 1;
			}

		}

		for (int i = blocks.size() - 1; i >= 0; i--) {
			block = blocks.get(i);
			for (int j = 0; j < block.length; j++) {
				w.println(block[j]);
			}
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
