import java.util.Arrays;

class Triangle{
    enum Triangle_type{
        RIGHT_ANGLED, EQUILATERAL, ISOSCELES, SCALENE
    }
    public static void main(String[] args){
        Triangle program = new Triangle();
        //tests run if no arguments passed to program
        // if(args.length == 0){
        //     if(program.test(new String[]{"1", "2", "3"}) == Triangle_type.SCALENE){
        //         if(program.test(new String[]{"2000", "2000", "2000"}) == Triangle_type.EQUILATERAL){
        //             if(program.test(new String[]{"3450", "2", "3450"}) == Triangle_type.ISOSCELES){
        //                 if(program.test(new String[]{"37", "684", "685"}) == Triangle_type.RIGHT_ANGLED){
        //                     System.out.println("Tests successful");
        //                     System.exit(1);
        //                 }
        //             }
        //         }
        //     }
        //     program.error("Tests failed");
        // }
        program.run(args);
    }

    Triangle_type run(String[] args){
        int x, y, z;
        Triangle_type type;
        check_args_length(args);
        x = get_int_arg(args[0]);
        y = get_int_arg(args[1]);
        z = get_int_arg(args[2]);
        type = find_triangle_type(x, y, z);
        return type;
    }

    void check_args_length(String[] args){
        if(args.length != 3){
            error("Wrong number of sides");
        }
        return;
    }

    int get_int_arg(String arg){
        int integer_argument = 0;
        try{
            integer_argument = Integer.parseInt(arg);
        }
        catch(Exception e){
            error("Invalid integer. Sides must be integers between 1 and (2^31)-1");
        }
        check_int_arg(integer_argument);
        return integer_argument;
    }

    void check_int_arg(int integer){
        if(integer > 0 && integer <= (Math.pow(2, 31) - 1)){
            return;
        }
        error("Invalid integer. Sides must be integers between 1 and (2^31)-1");
    }

    void error(String message){
        System.out.println(message);
        System.exit(1);
    }

    Triangle_type find_triangle_type(int x, int y, int z){

        int[] ordered_sides = order_sides(x, y, z);
        
        if(is_right_angled(ordered_sides)){
            System.out.println("Right-angled");
            return Triangle_type.RIGHT_ANGLED;
        }
        if(is_equilateral(x, y, z)){
            System.out.println("Equilateral");
            return Triangle_type.EQUILATERAL;
        }
        if(is_isosceles(x, y, z)){
            System.out.println("Isosceles");
            return Triangle_type.ISOSCELES;
        }
        if(is_scalene(x, y, z)){
            System.out.println("Scalene");
            return Triangle_type.SCALENE;
        }
        return null;
    }

    int[] order_sides(int x, int y, int z){
        int[] side_array = null;
        side_array = new int[3];
        side_array[0] = x;
        side_array[1] = y;
        side_array[2] = z;
        Arrays.sort(side_array);
        return side_array;
    }

    boolean is_right_angled(int[] ordered_sides){
        if(Math.pow(ordered_sides[2], 2) == 
            (Math.pow(ordered_sides[1], 2) + 
            (Math.pow(ordered_sides[0], 2)))){
            return true;
        }
        return false;
    }
    boolean is_equilateral(int x, int y, int z){
        if(x == y && y == z){ 
            return true;
        }
        return false;
    }
    boolean is_isosceles(int x, int y, int z){
        if((x == y && y != z) || (x == z && y != z) || (y == z &&  x != z)){
            return true;
        }
        return false;
    }
    boolean is_scalene(int x, int y, int z){
        if(x != y && x != z && y != z){
            return true;
        }
        return false;
    }
    Triangle_type test(String[] test_array){
        Triangle_type type = run(test_array);
        return type;
    }
}