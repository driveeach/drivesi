public class Main2{
	static int count = 0;

	public static void main(String[] args){
		while(true){
			try{
				//Post-increment is zero first time:
				if(count++ == 0)
					throw new MyException();
				if(count==2)
					break; //Out of "while"
			}catch(MyException e){
				System.out.println("Um erro ocorreu. :s Chatiads");
			}
		}
	}
}