package uva101;

import static org.junit.Assert.assertEquals;
import static uva101.TestUtils.EMPTY;
import static uva101.TestUtils.buildList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class Boards101TestData {

	@Test
	public void sampleTest() {
		Main.FlatTable table = new Main().new FlatTable(10);

		table.moveOnto(9, 1);
		table.moveOver(8, 1);
		table.moveOver(7, 1);
		table.moveOver(6, 1);
		table.pileOver(8, 6);
		table.pileOver(8, 5);
		table.moveOver(2, 1);
		table.moveOver(4, 9);

		assertSampleBoardResult(table);
	}

	private void assertSampleBoardResult(Main.FlatTable table) {
		assertEquals(buildList(0), table.getBlocksAt(0));

		assertEquals(buildList(1, 9, 2, 4), table.getBlocksAt(1));

		assertEquals(EMPTY, table.getBlocksAt(2));
		assertEquals(buildList(3), table.getBlocksAt(3));

		assertEquals(EMPTY, table.getBlocksAt(4));

		assertEquals(buildList(5, 8, 7, 6), table.getBlocksAt(5));

		assertEquals(EMPTY, table.getBlocksAt(6));
		assertEquals(EMPTY, table.getBlocksAt(7));
		assertEquals(EMPTY, table.getBlocksAt(8));
		assertEquals(EMPTY, table.getBlocksAt(9));
	}

	@Test
	public void directSampleTest() {
		String input = "10\n" + "move 9 onto 1\n" + "move 8 over 1\n"
				+ "move 7 over 1\n" + "move 6 over 1\n" + "pile 8 over 6\n"
				+ "pile 8 over 5\n" + "move 2 over 1\n" + "move 4 over 9\n"
				+ "quit";
		InputStream inputStream = new ByteArrayInputStream(
				input.getBytes(StandardCharsets.UTF_8));

		Main main = new Main(inputStream);
		main.begin();

		assertSampleBoardResult(main.table);
	}

	@Test
	public void sampleTest2() {
		Main.FlatTable table = new Main().new FlatTable(10);

		table = new Main().new FlatTable(24);

		table.moveOnto(9, 1);
		table.moveOnto(23, 22);
		table.pileOver(21, 20);
		table.moveOver(16, 1);
		table.pileOver(8, 6);
		table.pileOver(1, 5);
		table.moveOver(19, 1);
		table.moveOver(18, 19);
		table.moveOnto(10, 0);
		table.moveOver(7, 1);
		table.moveOver(17, 14);
		table.pileOver(6, 1);
		table.pileOver(5, 6);
		table.pileOver(4, 0);
		table.moveOver(3, 0);
		table.moveOver(2, 9);
		table.moveOver(15, 1);
		table.moveOver(13, 17);
		table.moveOver(12, 15);
		table.moveOnto(11, 13);

		table.pileOver(0, 6);
		table.pileOver(14, 5);
		table.pileOver(20, 22);
		table.pileOver(23, 9);

		table.moveOver(5, 9);
		table.moveOnto(19, 2);
		table.pileOnto(3, 2);
		table.pileOnto(0, 0);
		table.moveOnto(23, 14);
		table.moveOver(5, 22);

		assertEquals(buildList(0), table.getBlocksAt(0));
		assertEquals(buildList(1), table.getBlocksAt(1));
		assertEquals(buildList(2), table.getBlocksAt(2));
		assertEquals(buildList(3), table.getBlocksAt(3));
		assertEquals(buildList(4), table.getBlocksAt(4));
		assertEquals(EMPTY, table.getBlocksAt(5));
		assertEquals(buildList(6), table.getBlocksAt(6));
		assertEquals(buildList(7), table.getBlocksAt(7));
		assertEquals(buildList(8), table.getBlocksAt(8));
		assertEquals(buildList(9), table.getBlocksAt(9));
		assertEquals(buildList(10), table.getBlocksAt(10));
		assertEquals(buildList(11), table.getBlocksAt(11));
		assertEquals(buildList(12), table.getBlocksAt(12));
		assertEquals(buildList(13), table.getBlocksAt(13));
		assertEquals(buildList(14), table.getBlocksAt(14));
		assertEquals(buildList(15), table.getBlocksAt(15));
		assertEquals(buildList(16), table.getBlocksAt(16));
		assertEquals(buildList(17), table.getBlocksAt(17));
		assertEquals(buildList(18), table.getBlocksAt(18));
		assertEquals(buildList(19), table.getBlocksAt(19));
		assertEquals(buildList(20), table.getBlocksAt(20));
		assertEquals(buildList(21), table.getBlocksAt(21));
		assertEquals(buildList(22, 5), table.getBlocksAt(22));
		assertEquals(buildList(23), table.getBlocksAt(23));
	}

}