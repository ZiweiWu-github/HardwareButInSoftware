/**
Written By Ziwei Wu
Can convert from decimal to binary and vice versa with 2's complement
Has addition and subtraction "circuits" implemented
**/
public class MotherBoard {

	public static void main(String[] args) {
		int[] s = decimalToBinary(-2,10);
		int[] f = decimalToBinary(10,10);
		int[] g = addOrSub(s,f, 1);
		printOut(g);
		System.out.println(binaryToDecimal(g));
	}
	public static int logicalAnd1Bit(int a, int b) {
		if(a == 1 && b == 1) return 1;
		else return 0;
	}
	public static int logicalOr1Bit(int a, int b) {
		if(a==1 || b==1) return 1;
		else return 0;
	}
	public static int logicalXor1Bit(int a, int b) {
		if((a==1 && b==0) || (a==0 && b==1)) return 1;
		else return 0;
	}
	public static int logicalNot1Bit(int a) {
		if(a==1) return 0;
		else return 1;
	}
	public static int[] fullAdder(int[] a, int[] b, int m) {
		int c = m;
		int[] s = new int[a.length];
		for(int i =0; i<a.length; i++) {
			s[i] = logicalXor1Bit(c, logicalXor1Bit(a[i],b[i]));
			c = logicalOr1Bit(logicalAnd1Bit(a[i], b[i]), logicalAnd1Bit(c, logicalXor1Bit(a[i], b[i])));
		}
		return s;
	}
	public static int[] addOrSub(int[] a, int[] b, int mode) {
		if(mode == 0) {
			return fullAdder(a,b, mode);
		}
		else {
			int[] f = new int[b.length];
			for(int i =0; i<b.length; i++) {
				f[i] = logicalXor1Bit(mode, b[i]);
			}
			return fullAdder(a,f,mode);
		}
	}
	public static int[] decimalToBinary(int aa, int size) {
		int[] s = new int[size];
		int a = abs(aa);
		for(int i =0; i<size; i++) {
			s[i] = a%2;
			a /= 2;
		}
		if(aa<0) {
			s = twosComp(s);
		}
		return s;
	}
	public static int[] twosComp(int[] a) {
		for(int i =0; i<a.length; i++) {
			a[i] = logicalNot1Bit(a[i]);
		}
		a = addOrSub(a, decimalToBinary(1,a.length), 0);
		return a;
	}
	public static int abs(int a) {
		if(a >= 0) return a;
		else return a*=-1; 
	}
	public static int binaryToDecimal(int[] a) {
		int x = 0;
		boolean isNeg = false;
		if(a[a.length-1]==1) {
			isNeg = true;
			a= twosComp(a);
		}
		for(int i =0; i<a.length; i++) {
			if(a[i] != 0) {
				x+=exp(2, i);
			}
		}
		if(isNeg) x*= -1;
		return x;
	}
	public static int exp(int a, int b) {
		int i =1;
		for(int x =0; x<b; x++) {
			i*=a;
		}
		return i;
	}
	public static void printOut(int[] a) {
		String s = "";
		for(int i = a.length-1; i>=0; i--) {
			s+= a[i];
		}
		System.out.println(s);
	}
}
