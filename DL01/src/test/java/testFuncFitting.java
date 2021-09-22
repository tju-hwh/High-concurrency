import unit.FuncFitting;

public class testFuncFitting {
    //用来生成数据
    static double y(double x)
    {
        int j = x%2==0?1:-1;
        return 5+4*x+3*x*x+2*x*x*x+(int)(Math.random()*5*j);//代表公式：y=5+4*x+3*(x^2)+2*(x^3)
    }
    public static void main(String[] args) {
        //建立曲线拟合工具类的对象
        FuncFitting funcFitting = new FuncFitting(3);
        //调用添加数据点方法
        for (int i=1;i<1000;i++){
            funcFitting.add(i,y(i));///根据预期函数生成一些数据，真正使用时直接将真实数据一个个添加进去即可
        }
        //输出拟合出的二项式
        System.out.println("文本形式结果：");
        System.out.println(funcFitting.getTextStringFormula());//文本形式
        System.out.println("输出每个系数：");
        for (double d:funcFitting.getCoeffient())
            System.out.print(d+" ");
    }
}
