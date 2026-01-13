package util;

import java.util.Arrays;

public class ArrayStringUtils {

    /**
     * 将字符串转换为二维int数组
     * @param str 格式："[[1,2,3],[4,5,6],[7,8,9]]"
     * @return 二维int数组
     */
    public static int[][] parse2DIntArray(String str) {
        // 去除空格
        str = str.replaceAll("\\s+", "");

        // 验证格式
        if (!str.startsWith("[[") || !str.endsWith("]]")) {
            throw new IllegalArgumentException("Invalid format: " + str);
        }

        // 去掉最外层括号
        str = str.substring(2, str.length() - 2);

        // 分割行
        String[] rows = str.split("\\],\\[");
        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] nums = rows[i].split(",");
            result[i] = new int[nums.length];
            for (int j = 0; j < nums.length; j++) {
                result[i][j] = Integer.parseInt(nums[j]);
            }
        }

        return result;
    }

    /**
     * 将字符串转换为二维String数组
     * @param str 格式："[[a,b,c],[d,e,f]]"
     * @return 二维String数组
     */
    public static String[][] parse2DStringArray(String str) {
        str = str.replaceAll("\\s+", "");

        if (!str.startsWith("[[") || !str.endsWith("]]")) {
            throw new IllegalArgumentException("Invalid format: " + str);
        }

        str = str.substring(2, str.length() - 2);
        String[] rows = str.split("\\],\\[");
        String[][] result = new String[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i].split(",");
        }

        return result;
    }

    /**
     * 将字符串转换为二维char数组
     * @param str 格式："[[a,b,c],[d,e,f]]" 或 "[[1,2,3],[4,5,6]]" 或 "[[#,$,%],[&,*,@]]"
     * @return 二维char数组
     */
    public static char[][] parse2DCharArray(String str) {
        str = str.replaceAll("\\s+", "");

        if (!str.startsWith("[[") || !str.endsWith("]]")) {
            throw new IllegalArgumentException("Invalid format: " + str);
        }

        str = str.substring(2, str.length() - 2);
        String[] rows = str.split("\\],\\[");
        char[][] result = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] chars = rows[i].split(",");
            result[i] = new char[chars.length];
            for (int j = 0; j < chars.length; j++) {
                // 支持单字符，如 'a' 或 "a"
                if (chars[j].length() == 1) {
                    result[i][j] = chars[j].charAt(0);
                } else {
                    throw new IllegalArgumentException(
                            String.format("Invalid char at [%d][%d]: '%s' (must be single character)", i, j, chars[j])
                    );
                }
            }
        }

        return result;
    }

    /**
     * 将字符串转换为二维char数组（针对LeetCode常见格式）
     * 格式："[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]"
     * 或者更简单的格式："[[1,1,0],[0,1,0],[0,1,1]]"（数字字符）
     *
     * @param str 字符串表示的二维char数组
     * @return 二维char数组
     */
    public static char[][] parse2DCharArrayFromLeetCode(String str) {
        str = str.replaceAll("\\s+", "");

        if (!str.startsWith("[[") || !str.endsWith("]]")) {
            throw new IllegalArgumentException("Invalid format: " + str);
        }

        str = str.substring(2, str.length() - 2);
        String[] rows = str.split("\\],\\[");
        char[][] result = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] cells = rows[i].split(",");
            result[i] = new char[cells.length];

            for (int j = 0; j < cells.length; j++) {
                // 处理带引号的情况，如 "1" 或 '1'
                String cell = cells[j];
                if (cell.startsWith("\"") && cell.endsWith("\"") ||
                        cell.startsWith("'") && cell.endsWith("'")) {
                    cell = cell.substring(1, cell.length() - 1);
                }

                if (cell.length() == 1) {
                    result[i][j] = cell.charAt(0);
                } else {
                    throw new IllegalArgumentException(
                            String.format("Invalid char at [%d][%d]: '%s' (must be single character)", i, j, cells[j])
                    );
                }
            }
        }

        return result;
    }

    /**
     * 从字符串解析二维char数组，可配置空格处理
     * @param str 输入字符串
     * @param emptyCellChar 空单元格的默认字符（默认为空格 ' '）
     * @return 二维char数组
     */
    public static char[][] parse2DCharArraySmart(String str, char emptyCellChar) {
        str = str.replaceAll("\\s+", "");

        if (!str.startsWith("[[") || !str.endsWith("]]")) {
            throw new IllegalArgumentException("Invalid format: " + str);
        }

        // 提取内部内容
        String content = str.substring(2, str.length() - 2);

        // 处理空数组的情况
        if (content.isEmpty()) {
            return new char[0][0];
        }

        String[] rows = content.split("\\],\\[");
        char[][] result = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String rowStr = rows[i];

            // 处理空行的情况
            if (rowStr.isEmpty()) {
                result[i] = new char[0];
                continue;
            }

            String[] cells = rowStr.split(",", -1);  // 使用 -1 保留空字符串
            result[i] = new char[cells.length];

            for (int j = 0; j < cells.length; j++) {
                String cell = cells[j];

                // 处理空单元格
                if (cell.isEmpty()) {
                    result[i][j] = emptyCellChar;  // 使用传入的默认字符
                    continue;
                }

                // 去除可能的引号
                if ((cell.startsWith("\"") && cell.endsWith("\"")) ||
                        (cell.startsWith("'") && cell.endsWith("'"))) {
                    cell = cell.substring(1, cell.length() - 1);
                }

                // 验证是单字符
                if (cell.length() == 1) {
                    result[i][j] = cell.charAt(0);
                } else if (cell.equals(" ")) {
                    result[i][j] = ' ';
                } else {
                    throw new IllegalArgumentException(
                            String.format("Invalid char at [%d][%d]: '%s' (must be single character)", i, j, cell)
                    );
                }
            }
        }

        return result;
    }

    // 重载方法，使用默认的空格字符
    public static char[][] parse2DCharArraySmart(String str) {
        return parse2DCharArraySmart(str, ' ');
    }

    /**
     * 将二维数组转换为字符串（反向操作）
     * @param arr 二维int数组
     * @return 字符串表示
     */
    public static String arrayToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("[");
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]);
                if (j < arr[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 将二维char数组转换为字符串
     * @param arr 二维char数组
     * @return 字符串表示
     */
    public static String arrayToString(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("[");
            for (int j = 0; j < arr[i].length; j++) {
                sb.append("\"").append(arr[i][j]).append("\"");
                if (j < arr[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 打印二维char数组（便于查看）
     * @param arr 二维char数组
     */
    public static void printCharArray(char[][] arr) {
        System.out.println("二维char数组 (" + arr.length + "x" + (arr.length > 0 ? arr[0].length : 0) + "):");
        System.out.println("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  [");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("'" + arr[i][j] + "'");
                if (j < arr[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i < arr.length - 1) {
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println("]");
    }

    /**
     * 使用示例
     */
    public static void main(String[] args) {
        System.out.println("=== 测试 int[][] 转换 ===");
        String intInput = "[[4,3,4,10,5,5,9,2],[10,8,2,10,9,7,5,6],[5,8,10,10,10,7,4,2],[5,1,3,1,1,3,1,9],[6,4,10,6,10,9,4,6]]";
        int[][] intArray = parse2DIntArray(intInput);
        System.out.println("int数组行数: " + intArray.length);
        System.out.println("int数组列数: " + intArray[0].length);
        System.out.println("数组内容:");
        for (int[] row : intArray) {
            System.out.println(Arrays.toString(row));
        }
        // 转回字符串
        String output = arrayToString(intArray);
        System.out.println("\n转换回字符串:");
        System.out.println(output);
        System.out.println("是否相等: " + intInput.equals(output));


        System.out.println("\n=== 测试 char[][] 转换 ===");

        // 测试1：数字字符格式
        String charInput1 = "[[1,0,1,0],[0,1,0,1],[1,0,1,0],[0,1,0,1]]";
        char[][] charArray1 = parse2DCharArraySmart(charInput1);
        printCharArray(charArray1);

        // 测试2：带引号的格式
        String charInput2 = "[[\"1\",\"0\",\"1\"],[\"0\",\"1\",\"0\"],[\"1\",\"0\",\"1\"]]";
        char[][] charArray2 = parse2DCharArraySmart(charInput2);
        printCharArray(charArray2);

        // 测试3：字母格式
        String charInput3 = "[[a,b,c],[d,e,f],[g,h,i]]";
        char[][] charArray3 = parse2DCharArraySmart(charInput3);
        printCharArray(charArray3);

        // 测试4：特殊字符格式
        String charInput4 = "[[#,$,%],[&,*,@],[^,(,)]]";
        char[][] charArray4 = parse2DCharArraySmart(charInput4);
        printCharArray(charArray4);

        // 测试5：包含空格的格式
        String charInput5 = "[[1, ,1],[ ,1, ],[1, ,1]]";
        char[][] charArray5 = parse2DCharArraySmart(charInput5);
        printCharArray(charArray5);

        // 测试转换回字符串
        System.out.println("char数组转回字符串:");
        System.out.println(arrayToString(charArray1));
    }
}