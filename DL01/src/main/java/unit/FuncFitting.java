package unit;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

//http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/index.html?overview-summary.html
public class FuncFitting {
    private int degree;
    private WeightedObservedPoints obs = new WeightedObservedPoints();
    private PolynomialCurveFitter fitter;


    /**
     * 创建一个几阶的多项式来模拟曲线
     *
     * @param degree
     */
    public FuncFitting(int degree) {
        fitter = PolynomialCurveFitter.create(degree);
        this.degree = degree;
    }

    /**
     * 增加数据点
     *
     * @param x
     * @param y
     */
    public void add(double x, double y) {
        obs.add(x, y);
    }

    /**
     * 清空数据点
     */
    public void clear() {
        obs.clear();
    }

    public String getTextStringFormula() {
        StringBuilder sb = new StringBuilder("y=");
        double[] coef = fitter.fit(obs.toList());
        sb.append(coef[0]);
        if (coef[1] > 0) {
            sb.append("+");
        }
        for (int i = 0; i < coef.length; i++) {
            if (coef[i] > 0 && sb.charAt(sb.length() - 1) != '+')
                sb.append("+");
            sb.append(coef[i] + "*" + textPowerString(i));
        }
        return sb.toString();
    }

    /**
     * 生成幂的字符串
     *
     * @param y:幂y
     * @return
     */
    public String textPowerString(int y) {
        return "(X^" + y + ")";
    }

    /**
     * 将拟合后的曲线的从x^0到x^degree的系数输出
     * 例如dggree=3  返回的系数为[3,5,7]则多项式为y=3+5*X+7*X^2
     *
     * @return double[]
     */
    public double[] getCoeffient() {
        return fitter.fit(obs.toList());
    }

    /**
     * 函数计算 x 》 y
     * */
    public double calculateY(double x) {
        double[] coeffient = this.getCoeffient();
        double num = 0;
        for (int i = 0; i < coeffient.length; i++) {
            num += coeffient[i] * Math.pow(x,i);
        }
        return num;
    }

    /**
     * 计算函数最快下降梯度
     */
    public double maxGradient() {
        double maxSlope = 0;
        int nowPoint = 0;
        //计算前十个点的梯度，取梯度最大的点
        for (int i = 1; i <= 10 ; i++) {
            double nowSlope = Math.abs(calculateY(i) - calculateY(i + 1));
            if (nowSlope>maxSlope){
                maxSlope = nowSlope;
                nowPoint = i;
            }
        }
        return nowPoint;
    }
}