import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class HistogramAssignment {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the min number :: ");
        String minString = input.nextLine();
        System.out.print("Enter the max number :: ");
        String maxString = input.nextLine();
        System.out.print("Enter the range :: ");
        String rangeString = input.nextLine();

        validateNumbers(minString,maxString,rangeString);
    }

    private static List<Integer> getValuesFromTextFile(){
        List<Integer> listOfValuesFromInputFile = new ArrayList<>();
        try {
            File myObj = new File("File/input.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            listOfValuesFromInputFile = Arrays.stream(data.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println("Integer list :: "+listOfValuesFromInputFile);
        return listOfValuesFromInputFile;
    }

    private static boolean checkTextFile(int min, int max){
        boolean result = false;
        for (int eachNumber: getValuesFromTextFile()) {
            if (eachNumber < 0 || eachNumber < min || eachNumber > max) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static void validateNumbers(String minimumNumber, String maximumNumber, String rangeNumber){

        List<String> listOfValues = Arrays.asList(minimumNumber,maximumNumber,rangeNumber);

        int min;
        int max;
        int range;

        for (String str : listOfValues) {
            if (str.equalsIgnoreCase(null) || str.equalsIgnoreCase("")) {
                System.out.println("ERROR, you missed one of the values");
                break;
            } else {
                min = Integer.parseInt(minimumNumber);
                max = Integer.parseInt(maximumNumber);
                range = Integer.parseInt(rangeNumber);

                if (max < min) {
                    System.out.println("ERROR, max number is smaller than min number...");
                    break;
                } else if (min % range != 0 || max % range != 0) {
                    System.out.println("ERROR, max or min by not divided by range without remainder...");
                    break;
                } else if (checkTextFile(min,max)) {
                        System.out.println("ERROR, list contains negative number or numbers smaller than min or numbers greater than max...");
                        break;
                } else {
                    int bins = (max - min) / range;
//                    System.out.println("This is bins :: " + bins);
                    List<Integer> counts = new ArrayList<>();
                    for (int i=1; i<= range; i++){
//                        System.out.println("This is i :: " + i);
                        int lowerLimit;
                        int upperLimit;
                        int counter = 0 ;
                        if (i != range) {
                            lowerLimit = min;
//                            System.out.println("lowerLimit :: " + lowerLimit);
                            upperLimit = min + bins - 1;
//                            System.out.println("upperLimit :: " + upperLimit);
                            for (int number  : getValuesFromTextFile()) {
                                if (lowerLimit <= number && upperLimit >= number){
                                    counter++;
                                }
                            }
//                            System.out.println("This is counter :: "+counter);
                            counts.add(counter);
                            min = min + bins;
//                            System.out.println("This is min :: "+min);
                        } else {
                            lowerLimit = min;
//                            System.out.println("lowerLimit :: " + lowerLimit);
                            upperLimit = max;
//                            System.out.println("upperLimit :: " + upperLimit);
                            for (int number  : getValuesFromTextFile()) {
                                if (lowerLimit <= number && upperLimit >= number){
                                    counter++;
                                }
                            }
//                            System.out.println("This is counter :: "+counter);
                            counts.add(counter);
                        }
//                        System.out.println("-------");
                    }
//                    System.out.println(counts);
                    counts.forEach(integer -> System.out.print(integer+" "));
                    break;
                }
            }
        }
    }
}
