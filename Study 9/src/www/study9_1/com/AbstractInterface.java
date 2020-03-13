package www.study9_1.com;

interface PersonalNumberStorage {
	void addPersonalInfo(String name, String perNum);
	String serchName(String perNum);
}

class PersonalNumInfo{
	String name;
	String number;
	
	PersonalNumInfo(String name, String number){
		this.name = name;
		this.number = number;
	}
	
	String getName(){ return name; }
	String getNumber(){ return number; }
}

class PersonalNumberStorageImpl implements PersonalNumberStorage {
	PersonalNumInfo[] perArr;
	int numOfPerInfo;
	
	public PersonalNumberStorageImpl(int sz){
		perArr = new PersonalNumInfo[sz];
		numOfPerInfo = 0;
	}
	
	@Override
	public void addPersonalInfo(String name, String perNum) {
		perArr[numOfPerInfo++] = new PersonalNumInfo(name, perNum);
	}

	@Override
	public String serchName(String perNum) {
		for(int i = 0; i < numOfPerInfo; i++){
			if(perNum.compareTo(perArr[i].getNumber()) == 0)
				return perArr[i].getName();
		}
		return null;
	}
}

public class AbstractInterface {
	public static void main(String[] args){
		PersonalNumberStorage storage = new PersonalNumberStorageImpl(100);
		storage.addPersonalInfo("김기동", "950000-1122333");
		storage.addPersonalInfo("장산길", "970000-1122334");
		
		System.out.println(storage.serchName("950000-1122333"));
		System.out.println(storage.serchName("970000-1122334"));
	}
}
