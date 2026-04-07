package design;

import java.util.Arrays;

//2069. 模拟行走机器人 II
class Robot {

    // 只差一点 ai做的

    private int width, height, pos;
    private boolean moved; // 标记是否已经移动过（用于判断起点方向）

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.pos = 0;
        this.moved = false;
    }

    public void step(int num) {
        if (num > 0) moved = true;
        int mod = 2 * (width + height) - 4; // 边界上不重复的格子总数
        pos = (pos + num) % mod;
    }

    public int[] getPos() {

        if (pos < width) {                     // 东边（下边）
            return new int[]{pos, 0};
        } else if (pos < width + height - 1) { // 北边（右边）
            return new int[]{width - 1, pos - width + 1};
        } else if (pos < 2 * width + height - 2) { // 西边（上边）
            int x = width - 2 - (pos - (width + height - 1));
            return new int[]{x, height - 1};
        } else {                               // 南边（左边）
            int y = height - 2 - (pos - (2 * width + height - 2));
            return new int[]{0, y};
        }
    }

    public String getDir() {
        if (pos == 0 && moved) return "South";
        if (pos < width) return "East";
        else if (pos < width + height - 1) return "North";
        else if (pos < 2 * width + height - 2) return "West";
        else return "South";
    }

    public static void main() {
        Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
        robot.step(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
        robot.step(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
        System.out.println(Arrays.toString(robot.getPos())); // 返回 [4, 0]
        System.out.println(robot.getDir()); // 返回 "East"
        robot.step(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
        // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
        // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
        robot.step(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 北 （不是朝西）。
        robot.step(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
        // 然后，移动 4 步到 (1, 2) ，并朝西。
        System.out.println(Arrays.toString(robot.getPos())); // 返回 [1, 2]
        System.out.println(robot.getDir()); // 返回 "West"


//        System.out.println(Arrays.toString(robot.getPos()));
//        System.out.println(robot.getDir());
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
