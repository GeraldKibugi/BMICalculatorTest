
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stage3Tests extends StageTest {
  @DynamicTest
  CheckResult test1() {
    //programStartTest
    TestedProgram pr = new TestedProgram();
    String output = pr.start("BMI Calculator");
    String[] p1 = output.trim().replace(System.getProperty("line.separator")+System.getProperty("line.separator"),"\n").split("\n");
    ArrayList<String> p = new ArrayList<>();
    for (String s : p1) {
      if (s.trim().length() != 0) {
        p.add(s);
      }
    }
    if(p.size()<5){
      throw new WrongAnswer("Your output is not formatted correctly. It should be as follows:"+
                    "Welcome to BMI Calculator"+
                    "What would you like to do?"+
                    "A. Convert weight to kilogram(kg)"+
                    "B. Convert height to meters(m)"+
                    "C. Calculate BMI"+
                    "D. Check weight goal   "+
                    "X. Exit'\"");
    }
    if(!p.get(0).toLowerCase().trim().contains("Welcome to BMI Calculator".toLowerCase())){
      throw new WrongAnswer("Your output should contain the application title " +
              "\"Welcome to BMI Calculator\"");
    }
    if (!p.get(1).toLowerCase().trim().contains("what would you like to do?")){
      throw new WrongAnswer("Your menu title should be 'What would you like to do?'");
    }
    if (!p.get(2).trim().contains("A. Convert weight to kilogram(kg)") ){
      throw new WrongAnswer("Menu A should be 'A. Convert weight to kilogram(kg)'");
    }
    if (!p.get(3).trim().contains("B. Convert height to meters(m)") ){
      throw new WrongAnswer("Menu B should be 'B. Convert height to meters(m)'");
    }
    if (!p.get(4).trim().contains("C. Calculate BMI") ){
      throw new WrongAnswer("Menu C should be 'C. Calculate BMI'");
    }
    if (!p.get(5).trim().contains("D. Check weight goal") ){
      throw new WrongAnswer("Menu D should be 'D. Check weight goal'");
    }
    if (!p.get(6).trim().contains("X. Exit") ){
      throw new WrongAnswer("Menu E should be 'X. Exit'");
    }
    return CheckResult.correct();
  }
  Object[][] correctMenu =  {
          {"a"},
          {"A"},
          {"b"},
          {"B"},
          {"c"},
          {"C"},
          {"d"},
          {"D"},
          {"X"},
          {"x"}
  };
  @DynamicTest(data = "correctMenu")
  CheckResult TestCorrectMenu( String message) {
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenu(message,pr2);
    return CheckResult.correct();
  }
  private void testCorrectMenu(String input, TestedProgram pr){
    if(!pr.isFinished()){
      String output = pr.execute(input);
      if(output.contains("Invalid Command.")){
        throw new WrongAnswer("'" +input+ "' is a valid command");
      }
      switch (input.toLowerCase()){
        case "a":
          if(pr.isFinished()){
            throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
          }else if(!output.trim().equals("Enter weight in grams(g) or pounds(lb)")){
            throw new WrongAnswer("On entering menu 'A' option, your program should print 'Enter weight in grams(g) or pounds(lb)' and wait for input.");
          }
          break;
        case "b":
          if(pr.isFinished()){
            throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
          } else if(!output.trim().equals("Enter height in centimeters(cm), inches(in) or feet(ft)")){
            throw new WrongAnswer("On entering menu 'B' option, your program should print 'Enter height in centimeters(cm), inches(in) or feet(ft)' and wait for input.");
          }
          break;
        case "c": if(pr.isFinished()){
          throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
        } else if(!output.trim().equals("Enter your height in centimeters(cm), inches(in), feet(ft) or meters and weight in grams(g), pounds(lb) or kilograms(kg)")){
          throw new WrongAnswer("On entering menu 'C' option, your program should print 'Enter your height in centimeters(cm), inches(in), feet(ft) or meters and weight in grams(g), pounds(lb) or kilograms(kg)' and wait for input.");
        }
          break;
        case "d": if(pr.isFinished()){
          throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
        } else if(!output.trim().equals("Enter your height in centimeters(cm), inches(in), feet(ft) or meters")){
          throw new WrongAnswer("On entering menu 'D' option, your program should print 'Enter your height in centimeters(cm), inches(in), feet(ft) or meters");
        }
          break;
        case "x": if(!pr.isFinished()){
          throw new WrongAnswer("Your program should terminate on Exit command");
        }else if(!output.contains("Bye.")){
          throw new WrongAnswer("Your program should print 'Bye.' before terminating.");
        }
          break;
      }
    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  Object[][] correctInputForA =  {
          {"a"}
  };
  @DynamicTest(data = "correctInputForA")
  CheckResult Test2( String message) {
    //TestCorrectMenuA
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuA(message,pr2);
    return CheckResult.correct();
  }

  @DynamicTest(data = "correctInputForA")
  CheckResult Test4( String message) {
    //TestIncorrectMenuA
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuA(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult Test5( String message) {
    //TestCorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuB(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult Test6( String message) {
    //TestIncorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuB(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult Test7( String message) {
    //TestCorrectMenuC
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuC(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult Test8( String message) {
    //TestCorrectMenuCSingleInput
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuCSingleInput(message,pr2);
    return CheckResult.correct();
  }




  @DynamicTest(data = "correctInputForA")
  CheckResult Test9( String message) {
    //TestIncorrectMenuCInvalidInput
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuCInvalidInput(message,pr2);

    return CheckResult.correct();
  }
  Object[][] test10 =  {
          {"1008g 127"},{"1000 g 1.2kg"},{"5.6 ft 72 p"},{"72000g 5.6.0kg"},{"72kg 5.6t"},{"72ft 66in"},{"72kg 1.66m in"}
  };

  @DynamicTest(data = "test10")
  CheckResult Test10( String message) {
    //TestIncorrectMenuCSingle2ndInvalidInput
    TestedProgram pr2 = new TestedProgram();
    pr2.start();


    testCorrectMenuCSingle2ndInputInvalid(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult Test11( String message) {
    //TestCorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuD(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult Test12( String message) {
    //TestIncorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuD(message,pr2);
    return CheckResult.correct();
  }
  /*@DynamicTest(data = "correctMenu")
  CheckResult TestCorrectMenuB( String message) {
      TestedProgram pr2 = new TestedProgram();
      pr2.start();
      testCorrectMenu(message,pr2);
      return CheckResult.correct();
  }*/
  private void testIncorrectMenuA(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"100","1289.67 ","1289.67 Grams","g 100","1000 KG","12.0 lb lb","180lp","180 LB kg","180 lB 3","180 Lb.01","180 pounds","one lb","100 cm","100 in","100 ft","100.98.1 g"};
      pr.execute("a");
      for (String x:correctInputForA) {

        String output = pr.execute(x);
        if(!output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is not valid input");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuA(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"100g","1000 g","1289.67 g","   1289.67 G","100G","1000 G","120 lb","180lb","180 LB ","180 lB","180 Lb","   180 LB","129.89lb   ","123g"};
      for (String x:correctInputForA) {
        pr.execute("a");
        String output = pr.execute(x);
        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if(input.contains("g")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertG(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertG(input.toLowerCase()))+"'");
        }else if(input.contains("lb")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase()))+"'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuC(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"  1000g   120cm  ","1000 g 1.2m","5.6   ft 72   kg","72kg 5.6ft"," 72kg   5.6ft","72kg 5.6  ft","72kg 5.6ft"};
      Pattern patternHeight =Pattern.compile("([-+]?[0-9]*\\.?[0-9]+\\s*(ft|cm|in|m))", Pattern.CASE_INSENSITIVE);
      Pattern patternWeight =Pattern.compile("[-+]?[0-9]+\\.?[0-9]+\\s*(lb|g|kg)", Pattern.CASE_INSENSITIVE);

      for (String x:correctInputForA) {
        pr.execute("C");
        String output = pr.execute(x);
        Matcher matcherBMI2 =patternHeight.matcher(x);
        matcherBMI2.find();
        String heightTemp = x.substring(matcherBMI2.start(),matcherBMI2.end());

        Matcher matcherBMI3 =patternWeight.matcher(x);
        matcherBMI3.find();
        String weightTemp =x.substring(matcherBMI3.start(),matcherBMI3.end());
        float h = convertHeight(heightTemp);
        float w = convertWeight(weightTemp);
        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if(!output.startsWith("Your BMI is "+df.format(calculateBMI(w,h)))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertG(input.toLowerCase()))+"'");
        }else if(input.contains("lb")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase()))+"'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuDDD(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"  1000g   120cm  ","1000 g 1.2m","5.6   ft 72   kg","72kg 5.6ft"," 72kg   5.6ft","72kg 5.6  ft","72kg 5.6ft"};
      Pattern patternHeight =Pattern.compile("([-+]?[0-9]*\\.?[0-9]+\\s*(ft|cm|in|m))", Pattern.CASE_INSENSITIVE);
      Pattern patternWeight =Pattern.compile("[-+]?[0-9]+\\.?[0-9]+\\s*(lb|g|kg)", Pattern.CASE_INSENSITIVE);

      for (String x:correctInputForA) {
        pr.execute("C");
        String output = pr.execute(x);
        Matcher matcherBMI2 =patternHeight.matcher(x);
        matcherBMI2.find();
        String heightTemp = x.substring(matcherBMI2.start(),matcherBMI2.end());

        Matcher matcherBMI3 =patternWeight.matcher(x);
        matcherBMI3.find();
        String weightTemp =x.substring(matcherBMI3.start(),matcherBMI3.end());
        float h = convertHeight(heightTemp);
        float w = convertWeight(weightTemp);
        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if(!output.startsWith("Your BMI is "+df.format(calculateBMI(w,h)))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertG(input.toLowerCase()))+"'");
        }else if(input.contains("lb")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase()))+"'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuCInvalidInput(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"1009g 120","1000  1.2m","5.6ft 72cm","72 5.6","72t 5.6ft","72kg 5.6y","72ft 5.6ft","72","72f","one"};
      Pattern patternHeight =Pattern.compile("([-+]?[0-9]*\\.?[0-9]+\\s*(ft|cm|in|m))", Pattern.CASE_INSENSITIVE);
      Pattern patternWeight =Pattern.compile("[-+]?[0-9]+\\.?[0-9]+\\s*(lb|g|kg)", Pattern.CASE_INSENSITIVE);
      pr.execute("C");
      for (String x:correctInputForA) {

        String output = pr.execute(x);
               /* Matcher matcherBMI2 =patternHeight.matcher(x);
                boolean r=matcherBMI2.find();
                if(true){
                    String heightTemp = x.substring(matcherBMI2.start(),matcherBMI2.end());
                }


                Matcher matcherBMI3 =patternWeight.matcher(x);
                matcherBMI3.find();
                String weightTemp =x.substring(matcherBMI3.start(),matcherBMI3.end());
                float h = convertHeight(heightTemp);
                float w = convertWeight(weightTemp);*/
        if(!output.contains("Invalid input")){
          throw new WrongAnswer("Your program is accepting invalid input. Display 'Invalid input! Enter your height in centimeters(cm), inches(in), feet(ft) or meters and weight in grams(g), pounds(lb) or kilograms(kg)'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuCSingleInput(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"1000g 120cm","1000 g 1.2m","5.6 ft 72 kg","72000g 5.6ft","72kg 5.6ft","72kg 66in","72kg 1.66m"};
      Pattern patternHeight =Pattern.compile("([-+]?[0-9]*\\.?[0-9]+\\s*(ft|cm|in|m))", Pattern.CASE_INSENSITIVE);
      Pattern patternWeight =Pattern.compile("[-+]?[0-9]+\\.?[0-9]+\\s*(lb|g|kg)", Pattern.CASE_INSENSITIVE);

      for (String x:correctInputForA) {
        pr.execute("C");


        int hPos=99,wPos=99;

        Matcher matcherBMI2 =patternHeight.matcher(x);
        matcherBMI2.find();
        String heightTemp = x.substring(matcherBMI2.start(),matcherBMI2.end());
        hPos=matcherBMI2.start();
        Matcher matcherBMI3 =patternWeight.matcher(x);
        matcherBMI3.find();
        String weightTemp =x.substring(matcherBMI3.start(),matcherBMI3.end());
        wPos=matcherBMI3.start();
        String output;
        if(hPos>wPos){
          output = pr.execute(weightTemp);
        }else{
          output = pr.execute(heightTemp);
        }

        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if((hPos>wPos)&&!output.startsWith("Enter your height")){
          throw new WrongAnswer("Your program should output 'Enter your height'");
        }else if((hPos<wPos)&&!output.startsWith("Enter your weight")){
          throw new WrongAnswer("Your program should output 'Enter your weight'");
        }

        if(hPos>wPos){
          output = pr.execute(heightTemp);

        }else{
          output = pr.execute(weightTemp);
        }
        float h = convertHeight(heightTemp);
        float w = convertWeight(weightTemp);
        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if(!output.startsWith("Your BMI is "+df.format(calculateBMI(w,h)))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertG(input.toLowerCase()))+"'");
        }else if(input.contains("lb")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase()))+"'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuCSingle2ndInputInvalid(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
            /*String[] correctInputForA =
                    {"1008g 127","1000 g 1.2kg","5.6 ft 72 t","72000g 5.6.0kg","72kg 5.6ft","72ft 66in","72kg 1.66m in"};*/

      Pattern patternHeight =Pattern.compile("([-+]?[0-9]*\\.?[0-9]+\\s*(ft|cm|in|m))", Pattern.CASE_INSENSITIVE);
      Pattern patternWeight =Pattern.compile("[-+]?[0-9]+\\.?[0-9]+\\s*(lb|g|kg)", Pattern.CASE_INSENSITIVE);
      String x=input;
      // for (String x:correctInputForA) {
      pr.execute("C");
      int hPos=99,wPos=99;

      Matcher matcherBMI2 =patternHeight.matcher(x);
      String heightTemp="";
      if(matcherBMI2.find()){
        heightTemp = x.substring(matcherBMI2.start(),matcherBMI2.end());
        hPos=matcherBMI2.start();
      }
      Matcher matcherBMI3 =patternWeight.matcher(x);
      String weightTemp="";
      if(matcherBMI3.find()) {
        weightTemp = x.substring(matcherBMI3.start(), matcherBMI3.end());
        wPos = matcherBMI3.start();
      }
      // System.out.println("BBBBBBB:"+x+"hPos:"+hPos+"wPos:"+wPos);
      String output;
      if(weightTemp.length()!=0){
        output = pr.execute(weightTemp);
      }else{
        output = pr.execute(heightTemp);
      }

      if(output.contains("Invalid input")){
        throw new WrongAnswer("'" +x+ "' is a valid input");
      }else if((hPos>wPos)&&!output.startsWith("Enter your height")){
        throw new WrongAnswer("Your program should output 'Enter your height'");
      }else if((hPos<wPos)&&!output.startsWith("Enter your weight")){
        throw new WrongAnswer("Your program should output 'Enter your weight'");
      }
      if(weightTemp.length()!=0){
        output = pr.execute(x.replace(weightTemp,""));
      }else{
        output = pr.execute(x.replace(heightTemp,""));
      }
      if(!output.contains("Invalid input")){
        if(hPos>wPos){
          throw new WrongAnswer("Your program should display 'Invalid input! Enter your height in centimeters(cm), inches(in), feet(ft)'");
        }else{
          throw new WrongAnswer("Your program should display 'Invalid input! Enter your weight in grams(g), pounds(lb) or kilograms(kg)'");
        }

      }
      //  }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private static  float calculateBMI(float weight, float height){
    return weight/(height*height);
  }
  private void testCorrectMenuB(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"100in","1000 in","1289.67 ft","1289.67 ft","100cm","1000 cm","120 ft","180cM","180 Cm","180 In","180 iN","180 Ft   ","   129.89fT"};
      for (String x:correctInputForA) {
        pr.execute("b");
        String output = pr.execute(x);
        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if(input.contains("cm")&&!output.startsWith("Height of "+input.trim()+" is "+df.format(convertCM(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Height of "+input.trim()+" is "+df.format(convertCM(input.toLowerCase()))+"'");
        }else if(input.contains("in")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertIN(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Height of "+input.trim()+" is "+df.format(convertIN(input.toLowerCase()))+"'");
        }else if(input.contains("ft")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertFT(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Height of "+input.trim()+" is "+df.format(convertFT(input.toLowerCase()))+"'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuD(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"100in","1000 in","1289.67 ft","1289.67 ft","100cm","1000 cm","120 ft","180cM","180 Cm","180 In","180 iN","180 Ft   ","   129.89fT"};
      for (String x:correctInputForA) {
        pr.execute("D");
        String output = pr.execute(x);
        if(output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is a valid input");
        }else if(!output.startsWith("Healthy weight for a height of "+x.trim()+" is between "+calculateHealthyWeight(convertHeight(x.trim().toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Healthy weight for a height of "+x.trim()+" is between "+calculateHealthyWeight(convertHeight(x.trim().toLowerCase()))+"'");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testIncorrectMenuB(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"100","1000","1289.67","1289.67 g","100lb","1000feet","120 ft cm","180 meters","two Cm","180 yard","180 inches","ft 180 ","cm 129.89fT","0.234.34ft"};
      pr.execute("b");
      for (String x:correctInputForA) {
        String output = pr.execute(x);
        if(!output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is not a valid input");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testIncorrectMenuD(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"100","1000","1289.67","1289.67 g","100lb","1000feet","120 ft cm","180 meters","two Cm","180 yard","180 inches","ft 180 ","cm 129.89fT","0.234.34ft"};
      pr.execute("D");
      for (String x:correctInputForA) {
        String output = pr.execute(x);
        if(!output.contains("Invalid input")){
          throw new WrongAnswer("'" +x+ "' is not a valid input");
        }
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private static float convertWeight(String weightTemp){
    float w=0.0f;
    if(weightTemp.contains("kg")){
      w=Float.parseFloat(weightTemp.replace("kg","").trim());
    } else if(weightTemp.contains("g")){
      w=convertG(weightTemp);
    }else if(weightTemp.contains("lb")){
      w=convertLB(weightTemp);
    }
    return w;
  }
  private static float convertHeight(String  heightTemp){
    float h;
    if(heightTemp.contains("cm")){
      h=convertCM(heightTemp);
    }else if(heightTemp.contains("in")){
      h=convertIN(heightTemp);
    }else if(heightTemp.contains("ft")){
      h=convertFT(heightTemp);
    }else{
      h=Float.parseFloat(heightTemp.replace("m","").trim());
    }
    return h;
  }
  private static  float convertLB(String weight){
    float x= Float.parseFloat(weight.substring(0,weight.indexOf("lb")));
    return x*0.45359237f;
  }
  private static  float convertG(String weight){
    float x= Float.parseFloat(weight.substring(0,weight.indexOf("g")));
    return x/1000f;
  }
  private static  float convertIN(String weight){
    float x= Float.parseFloat(weight.substring(0,weight.indexOf("in")));
    return x*0.0254f;
  }
  private static  float convertCM(String weight){
    float x= Float.parseFloat(weight.substring(0,weight.indexOf("cm")));
    return x/100f;
  }
  private static  float convertFT(String weight){
    float x= Float.parseFloat(weight.substring(0,weight.indexOf("ft")));
    return x*0.3048f;
  }
  private static  String calculateHealthyWeight( float height){
    DecimalFormat df = new DecimalFormat("0.00");
    return df.format(18.5f*(height*height))+"kg - "+df.format(25f*(height*height))+"kg";
  }
}