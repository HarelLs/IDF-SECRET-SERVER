
import java.util.*;

public class JewIO
{
	static Random rnd = new Random();
	public static void main(String[] args) {
		int[] nurx = new int[30];
		int xmax = 500;
		int a = 0;
		int gen = 0;
		for (int i = 0; i < nurx.length; i++) {
			nurx[i] = rnd.nextInt(1000 - 1) +1;
		}
		while(a == 0) {
			System.out.println("the courent Gen: " + gen);
			gen++;
			for (int i = 0; i < nurx.length; i++) {
				int mid = 0;
				int minmid = 0;
				while(nurx[i] != xmax) {

					if (nurx[i] - xmax < xmax){
						mid = nurx[i];
					}

					if (nurx[i] - xmax > xmax) {
						minmid = nurx[i];
					}

					break;
				}
				if(mid != 0) {
					if (mid - xmax > xmax) {
						nurx[i] = mid - 10 ;
					}
					if (mid - xmax < xmax) {
						nurx[i] = mid + 10;
					}
				}

				if(minmid != 0) {
				//	nurx[i] = minmid - 1000;
				}

				if(nurx[i] == xmax) {
					System.out.println("done.");
					System.out.println(nurx[i] + " is " + xmax );
					System.out.println("Final Gen: " + gen + " the Child: [" + i + "]");
					a = 1;
					break;
				}
				
				//System.out.println(nurx[i] + " != " + mid +" != " + minmid + " to " + xmax +" gen: " + gen );
			}
			
		}
		
	}


}
