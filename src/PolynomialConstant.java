import java.math.BigInteger;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class PolynomialConstant {

    static class Point {
        BigInteger x, y;

        Point(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {

        String content = Files.readString(Path.of("input.json"));

        List<String> testcases = splitTestCases(content);

        int caseNum = 1;

        for (String json : testcases) {

            if (!json.contains("\"keys\"")) continue;

            int k = extractK(json);
            List<Point> points = extractPoints(json);

            if (points.size() < k) {
                System.out.println("Testcase " + caseNum++);
                System.out.println("Error: k = " + k + 
                                   ", but only " + points.size() + " points found");
                System.out.println();
                continue;
            }

            // Sort points by x (important)
            points.sort(Comparator.comparing(p -> p.x));

            List<Point> selected = new ArrayList<>(points.subList(0, k));

            BigInteger constant = computeConstant(selected);

            System.out.println("Testcase " + caseNum++);
            System.out.println("Constant term (c): " + constant);
            System.out.println();
        }
    }

    static List<String> splitTestCases(String content) {

        List<String> cases = new ArrayList<>();

        int braceCount = 0;
        StringBuilder current = new StringBuilder();

        for (char ch : content.toCharArray()) {

            if (ch == '{') braceCount++;
            if (ch == '}') braceCount--;

            current.append(ch);

            if (braceCount == 0 && current.length() > 0) {
                cases.add(current.toString());
                current.setLength(0);
            }
        }

        return cases;
    }

    static int extractK(String json) {

        Pattern p = Pattern.compile("\"k\"\\s*:\\s*(\\d+)");
        Matcher m = p.matcher(json);

        if (m.find()) return Integer.parseInt(m.group(1));

        throw new RuntimeException("k not found");
    }

    static List<Point> extractPoints(String json) {

        List<Point> pts = new ArrayList<>();

        Pattern p = Pattern.compile(
            "\"(\\d+)\"\\s*:\\s*\\{\\s*\"base\"\\s*:\\s*\"(\\d+)\"\\s*,\\s*\"value\"\\s*:\\s*\"([0-9a-zA-Z]+)\""
        );

        Matcher m = p.matcher(json);

        while (m.find()) {

            BigInteger x = new BigInteger(m.group(1));
            int base = Integer.parseInt(m.group(2));
            String value = m.group(3);

            BigInteger y = new BigInteger(value, base);

            pts.add(new Point(x, y));
        }

        return pts;
    }

    static BigInteger computeConstant(List<Point> pts) {

        BigInteger resultNum = BigInteger.ZERO;
        BigInteger resultDen = BigInteger.ONE;

        for (int i = 0; i < pts.size(); i++) {

            BigInteger xi = pts.get(i).x;
            BigInteger yi = pts.get(i).y;

            BigInteger termNum = yi;
            BigInteger termDen = BigInteger.ONE;

            for (int j = 0; j < pts.size(); j++) {

                if (i == j) continue;

                BigInteger xj = pts.get(j).x;

                termNum = termNum.multiply(xj.negate());
                termDen = termDen.multiply(xi.subtract(xj));
            }

            resultNum = resultNum.multiply(termDen)
                    .add(termNum.multiply(resultDen));

            resultDen = resultDen.multiply(termDen);
        }

        return resultNum.divide(resultDen);
    }
}