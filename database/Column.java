class Column{

	private String name;
	private int columnNumber;
	
	//Column constructor
	public Column (String name, int columnNumber){
		this.name = name;
		this.columnNumber = columnNumber;
	}

	public String getName(){
		return name;
	}

	public int getColummnNumber(){
		return columnNumber;
	}
}