class Field{
	private String name;
	public Field(String name){
		this.name = name + " ";
	}
	public String getName(){
		return name;
	}
	public void editName(String newName){
		name = newName;
	}
}