
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stage5Tests extends StageTest {
  @DynamicTest
  CheckResult testA() {
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
    if(p.size()<8){
      throw new WrongAnswer("Your output is not formatted correctly. It should be as follows:"+
              "\"Welcome to BMI Calculator"+
              " What would you like to do?"+
              "A. Convert weight to kilogram(kg)"+
              "B. Convert height to meters(m)"+
              "C. Calculate BMI"+
              "D. Check weight goal  "+
              "E. Sort BMI   "+
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
    if (!p.get(6).trim().contains("E. Sort BMI") ){
      throw new WrongAnswer("Menu E should be 'E. Sort BMI'");
    }
    if (!p.get(7).trim().contains("X. Exit") ){
      throw new WrongAnswer("Menu F should be 'X. Exit'");
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
          {"E"},
          {"e"},
          {"X"},
          {"x"}
  };
  @DynamicTest(data = "correctMenu")
  CheckResult testB( String message) {
    //TestCorrectMenu
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
        case "e": if(pr.isFinished()){
          throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
        } else if(!output.trim().equals("Enter a list of name, height, weight separated by space and each item in the list separated by space or comma or semicolon")){
          throw new WrongAnswer("On entering menu 'E' option, your program should print 'Enter a list of name, height, weight separated by space and each item in the list separated by space or comma or semicolon'");
        }
          break;
        case "x":
          if(!pr.isFinished()){
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
  CheckResult testC( String message) {
    //TestCorrectMenuA
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuA(message,pr2);
    return CheckResult.correct();
  }

  @DynamicTest(data = "correctInputForA")
  CheckResult testD( String message) {
    //TestIncorrectMenuA
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuA(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testE( String message) {
    //TestCorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuB(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testF( String message) {
    //TestIncorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuB(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testG( String message) {
    //TestCorrectMenuC
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuC(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testH( String message) {
    //TestCorrectMenuCSingleInput
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuCSingleInput(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testI( String message) {
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
  CheckResult testJ( String message) {
    //TestIncorrectMenuCSingle2ndInvalidInput
    TestedProgram pr2 = new TestedProgram();
    pr2.start();


    testCorrectMenuCSingle2ndInputInvalid(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testK( String message) {
    //TestCorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuD(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testL( String message) {
    //TestIncorrectMenuB
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuD(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testM( String message) {
    //TestCorrectMenuE
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuE(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult testN( String message) {
    //TestIncorrectMenuE
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuE(message,pr2);
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
        }else if(!output.startsWith(calculateBMIAndClassify(w,h))){
          throw new WrongAnswer("Your program should output '"+calculateBMIAndClassify(w,h)+"'");
        }/*else if(input.contains("lb")&&!output.startsWith("Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase())))){
          throw new WrongAnswer("Your program should output 'Weight of "+input.trim()+" is "+df.format(convertLB(input.toLowerCase()))+"'");
        }*/
      }

    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testCorrectMenuDDD(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String[] correctInputForA =
              {"  1000g   120cm  ","1000 g 1.2m","5.6   ft 72   kg","72kg 5.6ft"," 72kg   5.6ft","72kg 5.6  ft","72kg 5.6ft","92kg 5.6ft","82kg 5.6ft","72000g 180cm","69kg 1.5m"};
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
              {"1009g 120","1000  1.2m","5.6ft 72k g","72 5.6","72t 5.6ft","72kg 5.6y","72ft 5.6ft","72","72f","one"};
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
          throw new WrongAnswer("Your program is accepting invalid input. Display 'Invalid input! Enter your height in centimeters(cm), inches(in), feet(ft) or meters and weight in grams(g), pounds(lb) or kilograms(kg)’'");
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
  private void testCorrectMenuE(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String correctInputForE =
              "oloo 98kg 1.8m mark 89kg 6ft; wairimu 1.2m 40kg; mbivi 1.6m 56kg mary 1.2m 98kg alex 5.8ft 53kg, Tommy 5.8ft 48kg; ray 98kg 1.6m, Seith 5.8ft 57.5kg  hellen 66in 120 lb";
      pr.execute("E");
      String output = pr.execute(correctInputForE);
      if(output.contains("Invalid input")){
        throw new WrongAnswer("'" +correctInputForE+ "' is a valid input");
      }else if(!output.startsWith(sort(correctInputForE))){
        throw new WrongAnswer("Your program should output '"+sort(correctInputForE)+"'");
      }
    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private void testIncorrectMenuE(String input, TestedProgram pr){
    if(!pr.isFinished()){
      DecimalFormat df = new DecimalFormat("0.00");
      String incorrectInputForE =
              "oloo 98kg 1.8m mark 89kg 6ft; wairimu 1.2m 40kg; jeff; mbivi 1.6m 56kg mary 1.2m 98kg alex 5.8ft 53kg, Tommy 5.8ft 48kg; ray 98kg 1.6m, Seith 5.8ft 57.5kg  hellen 66in 120 lb";
      pr.execute("E");

      String output = pr.execute(incorrectInputForE);
      if(!output.contains("Invalid input")){
        throw new WrongAnswer("Your program is accepting invalid input. Your program should display 'Invalid input! Enter a list of name, height, weight separated by space and each item in the list separated by space or comma or semicolon'");
      }
    }else{
      throw new WrongAnswer("Your program ended prematurely. Your program should terminate on Exit command");
    }
  }
  private static String sort(String list){
    StringBuffer sortedList =new StringBuffer();
    Pattern patternBMIWithNo =Pattern.compile("(([A-Z]*[a-z]*)+\\s+[-+]?[0-9]+(\\.[0-9]+)?\\s*(ft|cm|in|m)\\s*[-+]?[0-9]+(\\.[0-9]*)?\\s*(lb|g|kg))|(([A-Z]*[a-z]*)+\\s+[-+]?[0-9]+(\\.[0-9]*)?\\s*(lb|g|kg)\\s*[-+]?[0-9]+(\\.[0-9]+)?\\s*(ft|cm|in|m))(\\s*[;,]*)", Pattern.CASE_INSENSITIVE);

    Pattern heightPattern =Pattern.compile("[-+]?[0-9]+(\\.[0-9]+)?\\s*(ft|cm|in|m)", Pattern.CASE_INSENSITIVE);
    Pattern weightPattern =Pattern.compile("[-+]?[0-9]+(\\.[0-9]*)?\\s*(lb|g|kg)", Pattern.CASE_INSENSITIVE);
    Pattern namePattern =Pattern.compile("([A-Z]*[a-z]*)+", Pattern.CASE_INSENSITIVE);

    Matcher matcherList = patternBMIWithNo.matcher(list);

    int n = 0;
    ArrayList<Person> ar = new ArrayList<Person>();
    while (matcherList.find()) {
      n++;
      String personString = list.substring(matcherList.start(), matcherList.end());
      Matcher personMatcher = namePattern.matcher(personString);
      personMatcher.find();
      String name = personString.substring(personMatcher.start(), personMatcher.end());

      Matcher weightMatcher = weightPattern.matcher(personString);
      weightMatcher.find();
      float pWeight = convertWeight(personString.substring(weightMatcher.start(), weightMatcher.end()));

      Matcher heightMatcher = heightPattern.matcher(personString);
      heightMatcher.find();
      float pHeight = convertHeight(personString.substring(heightMatcher.start(), heightMatcher.end()));

      ar.add(new Person(name, pHeight, pWeight));
    }
    Collections.sort(ar, new SortBMI());
    for (int i = 0; i < ar.size(); i++) {
      sortedList.append((i+1)+". "+ar.get(i)+"\n");
    }
    return sortedList.toString();
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
  private static String calculateBMIAndClassify(float weight, float height){
    DecimalFormat df = new DecimalFormat("0.00");
    float BMI=calculateBMI(weight,  height);
    String results="";
    String category="";
    if(BMI<=16){
      category="Severe Thinness";
    }else if(BMI>16&&BMI<=17){
      category=" Moderate Thinness";
    }else if(BMI>17&&BMI<=18.5){
      category="Mild Thinness";
    }else if(BMI>18.5&&BMI<=25){
      category="Normal";
    }else if(BMI>25&&BMI<=30){
      category=" Overweight";
    }else if(BMI>30&&BMI<=35){
      category="Obese Class I";
    }else if(BMI>35&&BMI<=40){
      category="Obese Class II";
    }else if(BMI>40){
      category="Obese Class III";
    }
    if(BMI<18.5f){
      results="Your BMI is "+df.format(BMI)+" in "+category+" category, gain between "+calculateGainOrLose( BMI,height )+" to your body weight.";
    }else if(BMI<=25.0f){
      results="Your BMI is "+df.format(BMI)+" in "+category+" category, maintain your current body weight.";
    }else if(BMI>25f){
      results="Your BMI is "+df.format(BMI)+" in "+category+" category, lose between "+calculateGainOrLose( BMI,height )+" of your body weight.";
    }
    return results;
  }
  private static  String calculateGainOrLose( float bmi,float height ){
    DecimalFormat df = new DecimalFormat("0.00");
    if(bmi<18.5){
      return df.format((18.5f-bmi)*(height*height))+"kg - "+df.format((25f-bmi)*(height*height))+"kg";
    }else{
      return df.format((bmi-25f)*(height*height))+"kg - "+df.format((bmi-18.5f)*(height*height))+"kg";
    }
  }
}
class Person{
  String name="";
  float height;
  float weight;
  float BMI;
  String category = "";
  String kgsToNormalWeight = "";
  private static final DecimalFormat df = new DecimalFormat("0.00");
  Person(String name,float height, float weight){
    this.name = name;
    this.height = height;
    this.weight = weight;
    BMI=calculateBMI(weight, height);
    category = calculateBMIAndClassify(weight, height);
    kgsToNormalWeight=loseOrGain();
  }
  private  String loseOrGain(){
    String results="";
    if(BMI<18.5f){
      results="Gain between "+calculateGainOrLose( BMI,height );
    }else if(BMI<=25.0f){
      results="";
    }else if(BMI>25f){
      results="Lose between "+calculateGainOrLose( BMI,height );
    }
    return results;
  }
  private  String calculateGainOrLose( float bmi,float height ){
    if(bmi<18.5){
      return df.format((18.5f-bmi)*(height*height))+"kg - "+df.format((25f-bmi)*(height*height))+"kg";
    }else{
      return df.format((bmi-25f)*(height*height))+"kg - "+df.format((bmi-18.5f)*(height*height))+"kg";
    }
  }
  public String toString(){

    return name+" "+df.format(BMI)+" "+category+" "+kgsToNormalWeight;
  }
  private float calculateBMI(float weight, float height){
    return weight/(height*height);
  }
  private String calculateBMIAndClassify(float weight, float height){
    float BMI=calculateBMI(weight,  height);
    String category="";
    if(BMI<=16){
      category="Severe Thinness";
    }else if(BMI>16&&BMI<=17){
      category=" Moderate Thinness";
    }else if(BMI>17&&BMI<=18.5){
      category="Mild Thinness";
    }else if(BMI>18.5&&BMI<=25){
      category="Normal";
    }else if(BMI>25&&BMI<=30){
      category=" Overweight";
    }else if(BMI>30&&BMI<=35){
      category="Obese Class I";
    }else if(BMI>35&&BMI<=40){
      category="Obese Class II";
    }else if(BMI>40){
      category="Obese Class III";
    }
    return category;
  }
}
class SortBMI implements Comparator<Person> {
  public int compare(Person a, Person b){
    if(a.BMI==b.BMI){
      return 0;
    }else if(a.BMI>b.BMI){
      return 1;
    }else{
      return -1;
    }
  }
}