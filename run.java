import java.io.*;
import java.util.*;
public class run {
	public static void main(String args[]) {
		int c=10;
		Scanner sc=new Scanner(System.in);
		HalifaxMap hf=new HalifaxMap();
		c=sc.nextInt();
		while(c!=0) {
			if(c==1) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				System.out.println(hf.newIntersection(x, y));
			}else if(c==2) {
				int x1=sc.nextInt();
				int y1=sc.nextInt();
				int x2=sc.nextInt();
				int y2=sc.nextInt();
				System.out.println(hf.defineRoad(x1, y1, x2, y2));
			}else if(c==3) {
				hf.print();
			}else if(c==4) {
				int x1=sc.nextInt();
				int y1=sc.nextInt();
				int x2=sc.nextInt();
				int y2=sc.nextInt();
				hf.navigate(x1, y1, x2, y2);
			}
			
			else {
				System.out.println("wrong input");
			}
			c=sc.nextInt();
		}
	}
}
