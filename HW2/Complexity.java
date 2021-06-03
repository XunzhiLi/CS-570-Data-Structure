/**
 * Name: Xunzhi Li
 * CWID:10457500
 * homework2
 */
package hm2;

public class Complexity {
	public static void main(String[] args) {
		method5(64);
	}
	public static void method1(int n) {
		if (n <= 0) {
			throw new NumberFormatException("please input a positive integer number ");
		} else {
			int counter = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}

	public static void method2(int n) {
		if (n <= 0) {
			throw new NumberFormatException("please input a positive integer number");
		} else {
			int counter = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						System.out.println("Operation " + counter);
						counter++;
					}
				}
			}
		}

	}

	public static void method3(int n) {
		if (n <= 1) {
			throw new NumberFormatException("please input a integer number which is greater than 1");
		} else {
			int counter = 0;
			for (int i = 1; i < n; i *= 2) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}

	public static void method4(int n) {
		if (n <= 1) {
			throw new NumberFormatException("please input a integer number which is greater than 1");
		} else {
			int counter = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j *= 2) {
					System.out.println("Operation " + counter);
					counter++;
				}

			}
		}
	}

	public static void method5(int n) {
		if (n <= 2) {
			throw new NumberFormatException("please input a integer number which is greater than 2");
		} else {
			int counter = 0;
			for (int i = 2; i < n; i = i * i) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}

	};;

//	its time complexity is 2^n
	public static int method6(int n) {
		if(n<0) {
			throw new NumberFormatException("please input a positive integer number");
		}
		else if (n == 0) {
			return 0;
		} 
		else if(n == 1){
			return 1;
		}else {
			return method6(n - 1) + method6(n - 2);
		}
	}
	

}
