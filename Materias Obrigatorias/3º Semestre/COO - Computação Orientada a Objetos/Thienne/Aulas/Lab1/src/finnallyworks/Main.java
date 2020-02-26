package finnallyworks;


public class Main {
    static int count = 0;
    
    public static void main(String[] args) {
        while (true) {
            try {
                // Post-increment is zero first time:
                if (count++ == 1)
                    throw new MyException();
                if (count == 2)
                    break; // out of "while“
            }
            catch (MyException e){
            	e.printStackTrace();
            }
        }
    }
} 

    

   
