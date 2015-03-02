class Grade{
    double mark = 0.0;

    public static void main(String[] args){
        Grade program = new Grade();
        program.run(args);
    }

    void run(String[] args){
        double mark;
        if (args[0].equals("-gpa")){
            mark = calculate_gpa(args);
            System.out.printf("GPA %.1f", mark);
        }
        else{
            check_valid_input(args);
            mark = calculate_mark(args);
            print_grade_message(mark);
        }
    }

    void check_valid_input(String[] args){
        if(args.length < 1){
            System.out.println("Make sure you enter at least one mark");
            System.exit(1);
        }
        for(int i=0; i<args.length; i++){
            try{
                Double.parseDouble(args[i]);
            }catch(NumberFormatException e){
                System.out.println("At least one of your arguments is not a valid number");
                System.exit(1);
            }
        }
    }

    void check_valid_mark(double mark){
        if(mark < 0 || mark > 100){
            System.out.println("Invalid mark. Must be between 0 and 100");
            System.exit(1);
        }
    }

    double calculate_gpa(String[] args){
        int count = args.length-1;
        double[] mark_array;
        mark_array = new double[count];
        for(int i=0; i<count; i++){
            mark_array[i] = Double.parseDouble(args[i+1]);
        }
        double gpa = calculate_average_mark(mark_array, count);
        gpa = gpa/20;
        return gpa;
    }

    double calculate_mark(String[] args){
        int count = args.length;
        double[] mark_array;
        mark_array = new double[count];
        for(int i=0; i<count; i++){
            mark_array[i] = Double.parseDouble(args[i]);
        }
        double average = calculate_average_mark(mark_array, count);
        return average;
    }

    double calculate_average_mark(double[] array, int size){
        double result = 0;
        for (int i=0; i<size; i++){
            result += array[i];
        }
        result = result / size;
        result = Math.round(result);
        return result;
    }

    void print_grade_message(double mark){
        if(mark >= 0 && mark < 49.5){
            System.out.println("Fail");
        }
        if(mark >= 49.5 && mark < 59.5){
            System.out.println("Pass");
        }
        if(mark >= 59.5 && mark < 69.5){
            System.out.println("Merit");
        }
        if(mark >= 69.5 && mark < 79.5){
            System.out.println("Distinction");
        }
        if(mark >= 79.5 && mark < 89.5){
            System.out.println("Above and Beyond");
        }
        if(mark >= 89.5 && mark < 99.5){
            System.out.println("Publishable");
        }
        if(mark >= 99.5){
            System.out.println("Perfect");
        }
    }
}