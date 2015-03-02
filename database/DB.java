class DB{
	public static void main(String[] args) {
		DB program = new DB();
		program.run();
	}
	public void run(){
		Record test_record = new Record();
		test_record.addField("Rob", 1);
		String test_record_field = test_record.getField(1);
		System.out.println(test_record_field);

		test_record.addField("Rib", 1);
		test_record_field = test_record.getField(1);
		System.out.println(test_record_field);

		test_record.addField("TAM", 2);
		test_record_field = test_record.getField(2);
		System.out.println(test_record_field);

		Table test_table = new Table("Dogs");
		test_table.addColumn("Name", 1);
	}
}
