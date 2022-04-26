package cn.itcast.aop.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 添加@EnableAspectJAutoProxy这个注解，是因为这个增强类是作用在serveice上面的，
 * springmvc.xml中配置的是作用在controller上面的
 * 所以这里需要这个注解开启动态代理
 *
 * 添加@Order注解，是因为事务管理器也是作用在serverice上的，里面要用到DataSource，
 * 得保证在那之前从这里先确认具体用哪个DataSource，值越低加载优先级越高，就先加载这里了
 */
@Component
@Aspect
@EnableAspectJAutoProxy
@Order(-9999)
public class DataSourceAspect {


    @Before("execution(* cn.itcast.service.*.*(..))")
    public void beforeExecute(JoinPoint joinPoint){

        String name = joinPoint.getSignature().getName();
        System.out.println("------> 拦截的方法名 : " + name);

        for (String key : ChooseDataSource.METHOD_TYPE_MAP.keySet()) {
            for (String type : ChooseDataSource.METHOD_TYPE_MAP.get(key)) {
                if(name.startsWith(type)){
                    DataSourceHandler.putDataSource(key);
                    System.out.println("---------> 获取当前使用的数据库连接池 : " + key);
                    break;
                }
            }
        }

    }


}
