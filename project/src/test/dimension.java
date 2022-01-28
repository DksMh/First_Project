package test;

public class dimension {
	public static void main(String[] args) {
		String[][] b = { { "John", "Abby" , "Robiln", "Rola"}, 
				{ "Sally", "Tom", "Jack", "Alex" }, 
				{ "3John", "3Abby" , "3Robiln", "3Rola"},
				{ "4John", "4Abby" , "4Robiln", "4Rola"},
		
		};

//	      System.out.println(firstRow(b)); // line 8
		String[] result = firstRow(b);
		for (int i = 0; i < result.length; i++) {
			System.out.print(firstRow(b)[i] + " ");
		}
	}

	public static String[] firstRow(String[][] b) { // String[][] a ==> String[][] b로 바꿈
		String[] name = new String[4]; // a[0].length = b[0].length = 4
		int counter = 0;

//	      for (int row = 0; row < a.length; row++) {
		for (int row = 0; row < 1; row++) { // b[row][col]
			for (int col = 0; col < 4; col++) { // a[row].length = 4
				name[counter++] = b[row][col]; // line 17
			}
		}
		return name;
	}
}