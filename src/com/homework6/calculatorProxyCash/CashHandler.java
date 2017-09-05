package com.homework6.calculatorProxyCash;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CashHandler implements InvocationHandler{

    private  final  Object delegate;

    public CashHandler(Object delegate) {
        this.delegate = delegate;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        float result = (float)method.invoke(delegate,args);
        System.out.println(args[0]+" "+args[1]+" "+args[2]);
        writeInFile(args,result);
        return result;
    }

    public void writeInFile(Object[] args, float result) {
        String first_operand = Float.toString((Float) args[0]);
        String second_operand = Float.toString((Float) args[1]);
        String operator = (String) args[2];
        String result_text = Float.toString(result);
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\denis\\Documents\\JavaSchool\\1.txt",true);
            fileWriter.write("\n"+operator +"\t" + first_operand
                    +"\t" + second_operand
                    +"\t" + result_text
            );
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
