import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleSolver {
	static Scanner reader = new Scanner(System.in);
	public static String[] DICTIONARY = { "OX", "CAT", "TOY", "AT", "DOG", "CATAPULT", "T" };

	static boolean isWord(String testWord) {
		for (String word : DICTIONARY) {
			if (testWord.equals(word)) {
				return true;
			}
		}
		return false;
	}

	public static int stoi(String number) {
		int returnValue = 0;
		try {
			returnValue = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			System.out.println("sorry invalid input.. program crashing");
			System.exit(0);
		}
		return returnValue;
	}

	public static char[][] constructPuzzle(int length, int height) {
		char[][] returnValue = new char[height][length];
		for (int column = 0; column < height; column++) {
			String str = (String) reader.nextLine();
			if (str.length() == length) {
				returnValue[column] = str.toCharArray();
			} else {
				System.out.println(
						"Hmm.. that line was not the correct length. try inputting that line again; dont forget the length specified was "
								+ length);
				column--;
			}
		}
		return returnValue;
	}

	public static int checkSingleCharacterDuplicate(int row, int column) {
		String newWord = "" + (char) (row + 48) + "," + (char) (column + 48);
		if (!indexDuplicates.contains(newWord)) {
			indexDuplicates.add(newWord);
			return 1;
		}
		return 0;
	}

	public static ArrayList<String> indexDuplicates = new ArrayList<String>();

	public static int FindWords(char[][] puzzle) {
		int length = puzzle[0].length;
		int height = puzzle.length;
		int returnValue = 0;
		String word = null;

		// Horizontal Path FINISHED
		for (int row = 0; row < height; row++) {
			for (int c = 0; c < length; c++) {
				word = "";
				for (int column = c; column < length; column++) {
					word += puzzle[row][column];
					if (isWord(word)) {
						// single character word
						if (word.length() == 1) {
							returnValue += checkSingleCharacterDuplicate((row), (column));
						}
						// multiple character word
						else {
							//System.out.println("Horizontal :" + word);
							returnValue++;
						}
					}
				}
			}
			for (int c = length - 1; c >= 0; c--) {
				word = "";
				for (int column = c; column >= 0; column--) {
					word += puzzle[row][column];
					if (isWord(word)) {
						// single character word
						if (word.length() == 1) {
							returnValue += checkSingleCharacterDuplicate((row), (column));
						}
						// multiple character word
						else {
							//System.out.println("Horizontal Reverse: " + word);
							returnValue++;
						}
					}
				}
			}
		}
		// Vertical Path FINISHED
		for (int column = 0; column < length; column++) {
			for (int r = 0; r < height; r++) {
				word = "";
				for (int row = r; row < height; row++) {
					word += puzzle[row][column];
					if (isWord(word)) {
						// single character word
						if (word.length() == 1) {
							returnValue += checkSingleCharacterDuplicate((row), (column));
						}
						// multiple character word
						else {
							//System.out.println("Vertical :" + word);
							returnValue++;
						}
					}
				}
			}
			for (int r = height - 1; r >= 0; r--) {
				word = "";
				for (int row = r; row >= 0; row--) {
					word += puzzle[row][column];
					if (isWord(word)) {
						// single character word
						if (word.length() == 1) {
							returnValue += checkSingleCharacterDuplicate((row), (column));
						}
						// multiple character word
						else {
							//System.out.println("Vertical Reverse: " + word);
							returnValue++;
						}
					}
				}
			}
		}
		// DR path FINISHED
		for (int row = height - 1; row > 0; row--) {
			for (int c = 0; c < length; c++) {
				word = "";
				for (int column = c; column < length; column++) {
					if (row + column < height) {
						word += puzzle[row + column][column];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((row + column), (column));
							}
							// multiple character word
							else {
								//System.out.println("DR bottom half EXCLUSIVE: " + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		for (int row1 = length - 1; row1 >= 0; row1--) {
			for (int c = 0; c < length; c++) {
				word = "";
				for (int column = c; column < length; column++) {
					if (row1 + column < length && column < height) {
						word += puzzle[column][row1 + column];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((column), (row1 + column));
							}
							// multiple character word
							else {
								//System.out.println("DR top half INCLUSIVE: " + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		// UL path FINISHED
		for (int row = 0; row < height - 1; row++) {
			for (int c = length - 1; c >= 0; c--) {
				word = "";
				for (int column = c; column >= 0; column--) {
					if (row - Math.abs(length - 1 - column) >= 0) {
						word += puzzle[row - Math.abs(length - 1 - column)][column];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((row - Math.abs(length - 1 - column)),
										(column));
							}
							// multiple character word
							else {
								//System.out.println("UL Top half EXCLUSIVE: " + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		for (int row = 0; row < length; row++) {
			for (int c = 0; c < length; c++) {
				word = "";
				for (int column = c; column < length; column++) {
					if (	(row-column 	   ) >=0			&& 
							(height-1 -column  ) >=0			) {
						word 				+= 							puzzle[	(height-1 -column)][row - column];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate(	(height-1 -column), row - column);
							}
							// multiple character word
							else {
								//System.out.println("UL Bottom half INCLUSIVE: " + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		// DL Path FINISHED
		for (int row = 0; row < height; row++) {
			// top half rectangle inclusive
			for (int c = length - 1; c >= 0; c--) {
				word = "";
				for (int column = c; column >= 0; column--) {
					if (row + Math.abs(length - 1 - column) < height) {
						word += puzzle[row + Math.abs(length - 1 - column)][column];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((row + Math.abs(length - 1 - column)),
										(column));
							}
							// multiple character word
							else {
								//System.out.println("DL bottom half INCLUSIVE:" + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		for (int row1 = length - 1; row1 >= 0; row1--) {
			// top half rectangle inclusive
			for (int c = length - 1; c >= 0; c--) {
				word = "";
				for (int column = c; column >= 0; column--) {
					if (length - 1 - column < height && row1 + column - length - 1 < length
							&& row1 + column - length - 1 >= 0) {
						word += puzzle[length - 1 - column][row1 + column - length - 1];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((length - 1 - column),
										(row1 + column - length - 1));
							}
							// multiple character word
							else {
								 //System.out.println("DL top half EXCLUSIVE: " + word);
								 returnValue++;
							}
						}
					}
				}
			}
		}
		// UR PATH FINISHED
		for (int row = length - 1; row >= 0; row--) {
			for (int c = 0; c < length; c++) {
				word = "";
				for (int column = c; column < length; column++) {
					if (row - column >= 0 && row - column < height) {
						word += puzzle[row - column][column];
						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((row - column), (column));
							}
							// multiple character word
							else {
								System.out.println("UR top half INCLUSIVE: " + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		for (int row1 = 0; row1 < length; row1++) {
			for (int c = length - 1; c >= 0; c--) {
				word = "";
				for (int column = c; column > 0; column--) {

					if ((row1 + Math.abs(length - 1 - column)) < length && column < height) {
						word += puzzle[column][row1 + Math.abs(length - 1 - column)];

						if (isWord(word)) {
							// single character word
							if (word.length() == 1) {
								returnValue += checkSingleCharacterDuplicate((column),
										(row1 + Math.abs(length - 1 - column)));
							}
							// multiple character word
							else {
								System.out.println("UR bottom half EXCLUSIVE:" + word);
								returnValue++;
							}
						}
					}
				}
			}
		}
		return returnValue;
	}

	public static void main(String[] args) {

		System.out.println(
				"Hello! Welcome to puzzle solver. You will be asked to input your puzzle's width followed by the height. \n Then you can enter any puzzle you want!(Any puzzle that fits the the specified dimensions that is)\n\n");
		System.out.print("Specify puzzle length: ");
		int length = stoi(reader.nextLine());
		System.out.print("Specify puzzle height: ");
		int height = stoi(reader.nextLine());

		System.out.println("Alright, now feel free to enter your own custom puzzle.");
		char[][] puzzle = constructPuzzle(length, height);
		reader.close();
		System.out.println(FindWords(puzzle));

	}

}
