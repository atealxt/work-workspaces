/*
a array have some int value(+-),find out a sub continued array to be the max value.
 */

/*
 我只写了穷举法，还有更好的：
 */

/*1
 
以下步骤要记下标

12， -8， 5， 66， -21， 0 ，35， -44，7

一、将相邻为正或负的数合并，并剔除0，得到正负相邻的数，结果：

12， -8， 71， -21， 35， -44，7

二、从两头向内收缩，两位一组（一正一负），如果和为负，剔除，结果：

12， -8， 71， -21， 35

三、从两头向内收缩，四位一组（两正两负），如果和为负，剔除，结果：

12， -8， 71， -21， 35(操作无变化)

四、六位一组、八位一组、继续。。。

最后是这样：
12， -8， 71， -21， 35

按下标，还原回去，得到

12， -8， 5， 66， -21， 0 ，35
 
 */

/*2
 
这就是个简单的典型的“动态规划”题目。

数组：x1,x2,x3,……xn
思路，首先是寻找最优子结构，这点要依靠一些经验规则。
这里的最优子结构就是
f(i)定义为以xi结尾的最大字串和。
那么f(i+1)就可以考虑
如果xi+1大于等于0或者f(i) + xi+1 > 0，那么f(i+1) = f(i) + xi+1；
如果f(i) + xi+1  <= 0,那么f(i+1) = 0；

查：DP的典型结构
 */

package test_intelligence;

/**
 *
 * @author Administrator
 */
public class Test_array {
    //answer is {12， -8， 5， 66， -21， 0 ，35} sum is 89;
    private int array[]={12,-8,5,66,-21,0,35,-44,7};
    
    //o(n^3).........
    public void doSearch(){
       int maxvalue=0,iTem=0,start=0,end=0;
       for(int i=0;i<array.length-1;i++){
           for(int j=i+1;j<array.length;j++){
               iTem=0;
               //System.out.println(i+" "+j);
               for(int m=i;m<=j;m++){
                   iTem+=array[m];
               }
               if(iTem>=maxvalue) {
                   maxvalue=iTem;  
                   start=i;end=j;
               }
           }
           //System.out.println(iTem);
       } 
       System.out.println(maxvalue + " start: " + start + " end: " + end);
    }

}









