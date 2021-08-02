package Date;

import java.util.*;

public class DigitUtil {
    public static void main(String[] args) {
        int day =10;
        System.out.println(DigitUtil.convertString(day));
    }

    private static final Map<Integer, String> NUMBNER_MAP = new HashMap<>();

    static {
        NUMBNER_MAP.put(0, "零");
        NUMBNER_MAP.put(1, "一");
        NUMBNER_MAP.put(2, "二");
        NUMBNER_MAP.put(3, "三");
        NUMBNER_MAP.put(4, "四");
        NUMBNER_MAP.put(5, "五");
        NUMBNER_MAP.put(6, "六");
        NUMBNER_MAP.put(7, "七");
        NUMBNER_MAP.put(8, "八");
        NUMBNER_MAP.put(9, "九");
    }

    /**
     * 阿拉伯数字转汉字
     *
     * @param num 目标数字
     * @return
     */
    public static String convertString(int num) {
        if (num == 0) {
            return "零";
        }
        Queue<String> digitQueue = new LinkedList<>();
        digitQueue.add("");
        digitQueue.add("十");
        digitQueue.add("百");
        digitQueue.add("千");
        digitQueue.add("万");
        digitQueue.add("十");
        digitQueue.add("百");
        digitQueue.add("千");
        Queue<Integer> numQueue = new LinkedList<>();
        int temp = num;
        while (temp > 0) {
            int current = temp % 10;
            numQueue.add(current);
            temp /= 10;
        }
        int count = 1;
        Stack<String> resStack = new Stack<>();
        StringBuilder res = new StringBuilder();
        while (!numQueue.isEmpty()) {
            String digit = digitQueue.poll(); // 当前位数
            int front = numQueue.poll(); // 头部数字
            String currentStr = NUMBNER_MAP.get(front);
            if (0 == front) { // 判断零的情况
                if (!resStack.isEmpty() && !"零".equals(resStack.peek())) {
                    resStack.push(currentStr);
                }
            } else {
                if (count == 1) { // 个位数
                    resStack.push(currentStr);
                } else {
                    resStack.push(digit);
                    resStack.push(currentStr);
                }
            }
            // 处理一十X的情况
            if (numQueue.isEmpty() && "百".equals(digitQueue.peek()) && "一".equals(resStack.peek())) {
                resStack.pop();
            }
            // 处理万的情况
            if (count >= 4 && !numQueue.isEmpty()) {
                // 将数字全部弹出
                resStack.push(digitQueue.poll());
                int nextNum = 0;
                int tempDigit = 1;
                while (!numQueue.isEmpty()) {
                    nextNum += numQueue.poll() * tempDigit;
                    tempDigit *= 10;
                }
                String nextNumStr = convertString(nextNum);
                res.append(nextNumStr);
            }
            count++;
        }
        while (!resStack.isEmpty()) {
            res.append(resStack.pop());
        }
        return res.toString();
    }
}

