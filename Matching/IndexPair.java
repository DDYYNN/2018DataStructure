
/**
 * substring의 위치 순서쌍(index pair)을 저장하는 클래스.
 * {@code LinkdedList}의 각 노드를 구성한다.
 */
public class IndexPair{
	private int strIdx;		// 몇 번째 줄의 substring인가?
	private int charIdx;	// 몇 번째 char에서 시작하는 substring인가?

	public IndexPair(int l, int r){
		strIdx = l;
		charIdx = r;
	}

	@Override
	public String toString(){
		return "(" + strIdx + ", " + charIdx + ")";
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndexPair other = (IndexPair) obj;
		return (strIdx == other.strIdx) && (charIdx == other.charIdx);
	}

	/**
	 * 인접한 바로 다음 위치(adjacent index)를 반환하는 함수.
	 * 즉, {@code this}가 {@code (i, j)}이면,
	 * {@code this.adjIndexPair}는 {@code (i, j+1)}를 반환한다.
	 * <p>pattern matching({@link DBProcessor#matchingList})에 이용된다.
	 */
	public IndexPair adjIndexPair(){
		return new IndexPair(strIdx, charIdx + 1);
	}
}
