package dao;

public class Person {
	private int id = 20181004;			//private : useBean : getProperty , setProperty
	private String name = "ȫ�浿";	
	
	public Person() {}	//�⺻ ������ : ����δ� �������
	
	
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
