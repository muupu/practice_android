
// http://blog.csdn.net/mazhimazh/article/details/19212829

import java.util.Arrays;

public class ArraycopyTest {  
  
    public static void main(String[] args) {  
        
		testSystemArraycopy();
		testArraysCopyOf();
    }
	
	private static void testSystemArraycopy() {
		System.out.println("---testSystemArraycopy---");  
		String[] strArray = new String[] { "ab", "xy", "mn" };  
        String[] copyArray = new String[4]; // 新数组大小比原数组小,会抛出java.lang.ArrayIndexOutOfBoundsException异常。
        for (int i = 0; i < strArray.length; i++) {  
            System.arraycopy(strArray, 0, copyArray, 0, strArray.length); // 调用系统方法进行深拷贝  
        }  
        printArray(copyArray);  
        System.out.println(strArray == copyArray ? "浅拷贝" : "深拷贝");  // ab  xy  mn  null  深拷贝
	}
	
	// Arrays.copyof内部会调用System.arraycopy
	// System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength)); //将原数组内容拷贝到新数组中,新数组取最小的数组长度 
	private static void testArraysCopyOf() {
		System.out.println("---testArraysCopyOf---");  
		String[] strArray = new String[] { "ab", "xy", "mn" };  
        String[] copyArray = new String[4];    
        for(int i=0;i<strArray.length;i++){    
            copyArray=Arrays.copyOf(strArray, strArray.length);    
        }    
        printArray(copyArray);    
        System.out.println(strArray==copyArray?"浅拷贝":"深拷贝");    // ab  xy  mn  深拷贝
	}
	
    public static void printArray(String[] array) {  
        for (int i = 0; i < array.length; i++) {  
            System.out.print(array[i] + "  ");  
        }  
    }  
  
}  