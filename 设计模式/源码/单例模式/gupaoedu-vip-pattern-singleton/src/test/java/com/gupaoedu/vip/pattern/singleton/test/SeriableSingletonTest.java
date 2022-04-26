package com.gupaoedu.vip.pattern.singleton.test;

import com.gupaoedu.vip.pattern.singleton.register.EnumSingleton;
import com.gupaoedu.vip.pattern.singleton.seriable.SeriableSingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Tom.
 */
public class SeriableSingletonTest {
    public static void main(String[] args) {

        EnumSingleton s1 = null;
//        SeriableSingleton s1 = null;
//        SeriableSingleton s2 = SeriableSingleton.getInstance();
        EnumSingleton s2 = EnumSingleton.INSTANCE;
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
//            s1 = (SeriableSingleton)ois.readObject();
            s1 = (EnumSingleton) ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
