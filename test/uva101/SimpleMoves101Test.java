package uva101;

import static org.junit.Assert.assertEquals;
import static uva101.TestUtils.EMPTY;
import static uva101.TestUtils.buildList;

import org.junit.Before;
import org.junit.Test;

public class SimpleMoves101Test {

	private Main.FlatTable table;

	private Integer block0 = Integer.valueOf(0);
	private Integer block1 = Integer.valueOf(1);
	private Integer block2 = Integer.valueOf(2);
	private Integer block3 = Integer.valueOf(3);

	@Before
	public void setupBoard() {
		table = new Main().new FlatTable(4);
	}

	@Test
	public void eachPositionWithSingleBlock() {
		assertEquals(block0, table.getBlocksAt(0).get(0));
		assertEquals(block1, table.getBlocksAt(1).get(0));
		assertEquals(block2, table.getBlocksAt(2).get(0));
		assertEquals(block3, table.getBlocksAt(3).get(0));
	}

	@Test
	public void pileOverSingleBlock() {
		table.pileOver(block0, block1);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(buildList(block1, block0), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3), table.getBlocksAt(3));
	}

	@Test
	public void pileOverTwoBlocks() {
		table.pileOver(block0, block1);
		table.pileOver(block1, block2);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(EMPTY, table.getBlocksAt(1));
		assertEquals(buildList(block2, block1, block0), table.getBlocksAt(2));
		assertEquals(buildList(block3), table.getBlocksAt(3));
	}

	@Test
	public void pileOverTwoBlocksDontMoveAll() {
		table.pileOver(block0, block1);
		table.pileOver(block1, block2);
		table.pileOver(block1, block3);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(EMPTY, table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3, block1, block0), table.getBlocksAt(3));
	}

	@Test
	public void pileOntoSingleBlock() {
		table.pileOnto(block0, block1);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(buildList(block1, block0), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3), table.getBlocksAt(3));
	}

	@Test
	public void pileOntoWithMoves() {
		table.pileOnto(block0, block1);
		table.pileOnto(block2, block3);
		table.pileOnto(block1, block3);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(EMPTY, table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3, block1, block0), table.getBlocksAt(3));
	}

	@Test
	public void moveOverSingleBlock() {
		table.moveOver(block0, block1);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(buildList(block1, block0), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3), table.getBlocksAt(3));
	}

	@Test
	public void moveOverWithMoves() {
		table.moveOver(block0, block1);
		table.moveOver(block2, block3);
		table.moveOver(block3, block1);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(buildList(block1, block0, block3), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(EMPTY, table.getBlocksAt(3));
	}

	@Test
	public void moveOntoSingleBlock() {
		table.moveOnto(block0, block1);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(buildList(block1, block0), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3), table.getBlocksAt(3));
	}

	@Test
	public void moveOntoWithMoves() {
		table.moveOnto(block0, block1);
		table.moveOnto(block2, block3);
		table.moveOnto(block3, block1);

		assertEquals(buildList(block0), table.getBlocksAt(0));
		assertEquals(buildList(block1, block3), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(EMPTY, table.getBlocksAt(3));
	}

	@Test
	public void invalidCommandSameBlock() {
		table.moveOnto(block0, block0);

		assertEquals(block0, table.getBlocksAt(0).get(0));
		assertEquals(block1, table.getBlocksAt(1).get(0));
		assertEquals(block2, table.getBlocksAt(2).get(0));
		assertEquals(block3, table.getBlocksAt(3).get(0));
	}

	@Test
	public void invalidCommandSamePosition() {
		table.moveOnto(block0, block1);
		table.moveOnto(block1, block0);

		assertEquals(EMPTY, table.getBlocksAt(0));
		assertEquals(buildList(block1, block0), table.getBlocksAt(1));
		assertEquals(buildList(block2), table.getBlocksAt(2));
		assertEquals(buildList(block3), table.getBlocksAt(3));
	}

}
