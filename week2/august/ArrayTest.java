class ArrayTeset {  
    public static void main(String args[]) {
      int number_of_rows = 6;
      int number_of_columns = 5;
      int arr[][] = new int[number_of_rows][number_of_columns];

      for (int i = 0; i < number_of_rows; i++) {
          for (int j = 0; j < number_of_columns; j++) {
              System.out.print("[" + i + "," + j + "]:" + arr[i][j] + " ");
          }
          System.out.println();
      }
    }
  }  