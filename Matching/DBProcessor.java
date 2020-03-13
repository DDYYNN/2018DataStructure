import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * command 입력을 공통적으로 처리하는 클래스. 파일 입력, 슬롯 출력, 패턴 검색을 담당한다.
 *
 */
public class DBProcessor {
	private HashTable DB;
	private final int PATTERN_LENGTH = 6; // substring의 길이

	public DBProcessor(HashTable DB) {
		this.DB = DB;
	}

	//////////////////////////////////////////////////////////////////////////////
	/**
	 * input 파일을 읽어들인 후 각 줄마다 6-character substring 단위로 DB에 기록한다.
	 */
	public HashTable readFile(String input) throws IOException {
		File file = new File(input);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		int strIdx = 0;
		String line;
		while ((line = br.readLine()) != null) {
			strIdx++;
			parseEachLine(line, strIdx); // strIdx번째 줄(String)을 DB에 기록한다.
		}
		return DB; // 기록 완료 후 DB 반환
	}

	private void parseEachLine(String line, int strIdx) {
		for (int charIdx = 0; charIdx <= line.length() - PATTERN_LENGTH; charIdx++) {
			String s = line.substring(charIdx, charIdx + PATTERN_LENGTH);
			IndexPair p = new IndexPair(strIdx, charIdx + 1); // charIdx는 1에서부터
																// 시작.
			DB.put(s, p);
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	/**
	 * input번째 slot의 모든 substring을 출력한다.
	 */
	public void printSlotStrings(String input) {
		DB.printSlotItems(Integer.parseInt(input));
	}

	//////////////////////////////////////////////////////////////////////////////
	/**
	 * Pattern이 들어있는 모든 위치(index)를 찾아서 출력한다.
	 */
	public void searchPattern(String input) throws Exception {
		ArrayList<IndexPair> matchingList = matchingList(input); // 핵심부분
		String result = "";
		for (IndexPair p : matchingList) {
			result += (p.toString() + " ");
		}
		System.out.println(result.trim()); // 끝 공백 제거
	}

	/**
	 * {@code input}이 등장하는 모든 index를 찾는다.
	 * <p>
	 * {@code input}을 6개의 문자로 구성된 substring들로 쪼개서 각 substring마다 매칭되는 index
	 * list들을 찾은 후, 각 index가 하나의 pattern으로 연결되어 있는지를 확인한다.
	 * 
	 * @param input
	 * @return List of all indexes where {@code input} pattern appears. If no
	 *         index exists, return a {@code dumbList} : (0, 0)
	 *
	 */

	private ArrayList<IndexPair> matchingList(String input) {
		ArrayList<LinkedList<IndexPair>> partiallyMatchedList = new ArrayList<>();
		// input의 조각들이 매칭된 위치들
		ArrayList<IndexPair> fullyMatchedPairs = new ArrayList<>();
		// input 전체가 매칭된 위치들

		ArrayList<IndexPair> dumbList = new ArrayList<>();
		dumbList.add(new IndexPair(0, 0)); // pattern match에 실패하면 (0, 0) 반환.

		int n = input.length() - PATTERN_LENGTH + 1; // input은 n개의 6-character
														// substring으로 쪼개진다.
		for (int i = 0; i < n; i++) { // n개의 substring에 대해서 searching 시도.
			String key = input.substring(i, i + PATTERN_LENGTH);
			LinkedList<IndexPair> keyMatchingPairs = DB.searchList(key);
			if (keyMatchingPairs == null) // 중간에 하나라도 안 들어있으면 종료한다.
				return dumbList;
			partiallyMatchedList.add(keyMatchingPairs);
		}
		for (IndexPair p : partiallyMatchedList.get(0)) { // p : pattern이 시작된다고
															// 예상되는 위치들
			boolean isFullyMatched = true;
			IndexPair adjacency = p;
			for (int i = 1; i < n; i++) { // 모든 input의 substring들에 대하여
				LinkedList<IndexPair> adjList = partiallyMatchedList.get(i);
				adjacency = adjacency.adjIndexPair(); // 인접 index
				if (!adjList.contains(adjacency)) // (i,j)가 매칭되면 (i,j+1)도 매칭되어야
													// 한다.
					isFullyMatched = false;
			}
			if (isFullyMatched)
				fullyMatchedPairs.add(p); // 모든 n개의 substring이 다 matching되는
											// index만 저장.
		}

		return fullyMatchedPairs.isEmpty() ? dumbList : fullyMatchedPairs; // 하나도
																			// 없으면
																			// dumbList
																			// 반환.
	}
}
