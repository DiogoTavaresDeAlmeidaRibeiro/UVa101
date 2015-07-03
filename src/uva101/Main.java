package uva101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * According to UVa rules, class must be called Main and submitted in a single
 * file
 *
 * @author DiogoRibeiro
 */
public class Main {

	class FlatTable {

		private List<List<Integer>> board;

		public FlatTable(int size) {
			super();
			board = new ArrayList<>(size);
			initializeBoard(size);
		}

		private void initializeBoard(int size) {
			for (int i = 0; i < size; i++) {
				ArrayList<Integer> position = new ArrayList<Integer>();
				position.add(i);
				board.add(position);
			}

		}

		public int size() {
			return board.size();
		}

		public void writeBoard() {
			for (int i = 0; i < size(); i++) {
				System.out.print(i + ":");

				for (Integer block : board.get(i)) {
					System.out.print(" " + block);
				}

				System.out.println();
			}

		}

		public List<Integer> getBlocksAt(int position) {
			return board.get(position);
		}

		public void pileOver(int blockA, int blockB) {
			if (!isValidCommand(blockA, blockB)) {
				return;
			}

			int positionA = findBlockPosition(blockA);
			int positionB = findBlockPosition(blockB);

			List<Integer> stackA = getStack(blockA, positionA);

			board.get(positionB).addAll(stackA);
			board.get(positionA).removeAll(stackA);
		}

		private boolean isValidCommand(int blockA, int blockB) {
			if (blockA == blockB) {
				return false;
			}

			int positionA = findBlockPosition(blockA);
			int positionB = findBlockPosition(blockB);

			return positionA != positionB;
		}

		private List<Integer> getStack(int block, int position) {
			int indexOfBlock = board.get(position).indexOf(block);

			return board.get(position).subList(indexOfBlock,
					board.get(position).size());
		}

		private int findBlockPosition(int block) {
			for (int position = 0; position < board.size(); position++) {
				if (this.getBlocksAt(position).contains(block)) {
					return position;
				}
			}

			throw new RuntimeException("Must find block");
		}

		public void pileOnto(Integer blockA, Integer blockB) {
			if (!isValidCommand(blockA, blockB)) {
				return;
			}

			int positionA = findBlockPosition(blockA);
			int positionB = findBlockPosition(blockB);

			List<Integer> stackA = getStack(blockA, positionA);
			resetBlocksOnTop(blockB, positionB);

			board.get(positionB).addAll(stackA);
			board.get(positionA).removeAll(stackA);
		}

		private void resetBlocksOnTop(Integer block, int position) {
			List<Integer> blockStack = getStack(block, position);
			List<Integer> onTopOfBLock = blockStack.subList(1,
					blockStack.size());
			if (onTopOfBLock.size() > 0) {
				resetBlocks(onTopOfBLock);
			}
		}

		private void resetBlocks(List<Integer> blocks) {
			// A copy is made to avoid concurrent modification exception
			List<Integer> copy = new ArrayList<>(blocks);
			for (int block : copy) {
				int blockPosition = findBlockPosition(block);

				int indexOfBlock = board.get(blockPosition).indexOf(block);
				board.get(blockPosition).remove(indexOfBlock);
				board.get(block).add(block);
			}
		}

		public void moveOver(Integer blockA, Integer blockB) {
			if (!isValidCommand(blockA, blockB)) {
				return;
			}

			int positionA = findBlockPosition(blockA);
			int positionB = findBlockPosition(blockB);

			resetBlocksOnTop(blockA, positionA);

			board.get(positionB).add(blockA);
			board.get(positionA).remove(blockA);
		}

		public void moveOnto(Integer blockA, Integer blockB) {
			if (!isValidCommand(blockA, blockB)) {
				return;
			}

			int positionA = findBlockPosition(blockA);
			int positionB = findBlockPosition(blockB);

			resetBlocksOnTop(blockA, positionA);
			resetBlocksOnTop(blockB, positionB);

			board.get(positionB).add(blockA);
			board.get(positionA).remove(blockA);
		}

	}

	private InputStream inputStream;
	FlatTable table;

	public Main(InputStream in) {
		inputStream = in;
	}

	public Main() {
	}

	public static void main(String[] args) {
		Main main101 = new Main(System.in);
		main101.begin();
	}

	public void begin() {
		String line;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream))) {

			while (true) {
				line = br.readLine();

				if (line == null) {
					break;
				}

				if (line.equals("quit")) {
					table.writeBoard();
					break;
				}

				if (isInteger(line)) {
					table = new FlatTable(Integer.valueOf(line));
					continue;
				}

				processBlockManipulationCommand(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processBlockManipulationCommand(String line) {
		StringTokenizer tokenizer = new StringTokenizer(line);

		String command = tokenizer.nextToken();
		int blockA = Integer.parseInt(tokenizer.nextToken());
		String subCommand = tokenizer.nextToken();
		int blockB = Integer.parseInt(tokenizer.nextToken());

		if (command.equals("move") && subCommand.equals("onto")) {
			table.moveOnto(blockA, blockB);
		}
		if (command.equals("move") && subCommand.equals("over")) {
			table.moveOver(blockA, blockB);
		}
		if (command.equals("pile") && subCommand.equals("onto")) {
			table.pileOnto(blockA, blockB);
		}
		if (command.equals("pile") && subCommand.equals("over")) {
			table.pileOver(blockA, blockB);
		}
	}

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
