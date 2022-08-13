import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Stage1Tests extends StageTest {
  @DynamicTest
  CheckResult programStartTest() {
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
    if (!p.get(4).trim().contains("X. Exit") ){
      throw new WrongAnswer("Menu C should be 'X. Exit'");
    }
    return CheckResult.correct();
  }
  Object[][] correctMenu =  {
          {"a"},
          {"A"},
          {"b"},
          {"B"},
          {"x"},
          {"X"}
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
        case "x":  if(!pr.isFinished()){
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
  CheckResult TestCorrectMenuA( String message) {
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuA(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult TestCorrectMenuB( String message) {
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testCorrectMenuB(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult TestIncorrectMenuA( String message) {
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuA(message,pr2);
    return CheckResult.correct();
  }
  @DynamicTest(data = "correctInputForA")
  CheckResult TestIncorrectMenuB( String message) {
    TestedProgram pr2 = new TestedProgram();
    pr2.start();
    testIncorrectMenuB(message,pr2);
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
              {"100g","1000 g","1289.67 g","1289.67 G","100G","1000 G","120 lb","180lb","180 LB","180 lB","180 Lb","   180 LB","129.89lb   ","123g"};
      for (String x:correctInputForA) {
        pr.execute("a");
        String output = pr.execute(x);
        if(output.contains("Invalid input.")){
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
}