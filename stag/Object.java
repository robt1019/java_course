class Object{
	String name;
	public Object(String object_name){
		name = object_name;
	}
	public String pick_up(){
		String temp = name;
		name = "Nothing";
		return temp;
	}
}