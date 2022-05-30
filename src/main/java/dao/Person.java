package dao;

public class Person {
	private int id = 20181004;			//private : useBean : getProperty , setProperty
	private String name = "홍길동";	
	
	public Person() {}	//기본 생성자 : 실행부는 비어있음
	
	
	//Getter/Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};		
	
	
	
}
