/**@author Xunzhi Li
 * cwid : 10457500
 * little-endian format
 * 
 */


import java.util.Arrays;
public class BinaryNumber {
	
	private int data[];
	private boolean overflow = false;
	
//	A constructor for creating a binary number of length length and consisting only of zeros.

	public BinaryNumber(int length) {
		clearOverflow();
		if (length <= 0) {
			throw new NumberFormatException("please input a allowable number as length");
		} else {
			this.data = new int[length];
			for (int i = 0; i < length; i++) {
				data[i] = 0;
			}
		}
	}

//	A constructor for creating a binary number given a string.

	public BinaryNumber(String str) {
		clearOverflow();
		int len = str.length();
		this.data = new int[len];
		for (int i = 0; i < len; i++) {
			int num = Character.getNumericValue(str.charAt(i));
			if (num == 0 || num == 1) {
				data[i] = num;
			} else {
				throw new NumberFormatException("please input the string with all numeral digits ");
			}
		}
	}

	
	
//for determining the length of a binary number.
	public int getLength() {
		return data.length;
	}

	
//	get the real number in the certain inputed index
	public int getDigit(int index) {

		if (index >= data.length || index < 0) {
			throw new NumberFormatException(
					"The value of index cannot be greater than the length of the BinaryNumber or negative");
		}
		return data[index];
	}

// An operation for transforming a binary number to its decimal notation
	public int toDecimal() {
		int decimal_result = 0;
		for (int i = 0; i < data.length; i++) {
			decimal_result += data[i] * Math.pow(2, i);
		}
		return decimal_result;
	}

	private void reallocate(int[] k, int n) {
		data = Arrays.copyOf(k, n);
	}

//shift operation
	public void shiftR(int amount) {
		int pre_length = data.length;
		int[] arr = data;
		reallocate(data, amount + pre_length);
		for (int i = 0; i < amount + pre_length; i++) {
			if (i < amount) {
				data[i] = 0;
			}else {
				data[i] = arr[i-amount];
			}

		}
	}

//  add two binary_numbers
	public void add(BinaryNumber aBinaryNumber) {
		if (aBinaryNumber.data.length != (data.length)) {
			throw new NumberFormatException("The lengths of the binary numbers do not coincide !");
		} else {
//			The lengths of the two  binary numbers
			int number = data.length;
//			copy a same array for calculation
			int[] arr = data;
//			c is parameter which only can be 0 or 1 as a added part for next  digit.
			int c = 0;
			for (int i = 0; i < number; i++) {
//				calculate sum of two binary_numbers in one digit.
				int total = arr[i] + aBinaryNumber.data[i] + c;
				
				if (total >= 2) {
//					if i == number-1 ,that means loop goes to the final digit adding.
//					and total >=2 ,we can know the result would have a longer length (digits) after adding.then we set oveflow = true
					
					if(i == number-1) {
						overflow = true;
					}else {
						c = 1;
						data[i] = total - 2;
					}
				} else {
					c = 0;
					data[i] = total;
				}
			}
		}

	}

	public String toString() {
		if(overflow == false) {
			String Transformed_String = "";
			for (int i = 0; i < data.length; i++) {
				Transformed_String += data[i];
			}
			return Transformed_String;
		}else {
			return "Overflow";
		}

	}
	public void clearOverflow() {
		overflow = false;
	}

}
