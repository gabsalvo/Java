public class assignment1 implements Runnable {

 public void run(){
 	int count = 0;
 	for(int i = 0; i <= 10000000; i++){
 		if(i%2 == 0) {
 			count +=1;
 			System.out.printf("%d\n", count);
 		}
 	}
 }

 public static void main(String[] args){
 	if(args.length != 1) {
 		System.out.println("Usage: java assignment1 numThreadToUse");
 		return;
 	}
 	int numTread = Integer.parseInt(args[0]);
 	for(int i = 0; i <= numTread; i++){
 		assignment1 ass = new assignment1();
 	   Thread t = new Thread(ass);
 	   t.start();
 	}
 } 

}