import java.util.ArrayList;

public class GradeAverage {
    public static void main(String[] args){
        double sum = 0;
        ArrayList<Integer> grades = new ArrayList<Integer>();
        for(int i = 0; i < args.length; i++) grades.add(Integer.parseInt(args[i]));
        grades.sort(null);
        
        for(int i = 2; i < grades.size(); i++) sum += grades.get(i);
        double average = sum / (grades.size() - 2);

        System.out.printf("Your adjusted grade average is: %.2f", average);
    }
}
