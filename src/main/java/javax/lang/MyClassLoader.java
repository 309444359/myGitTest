package javax.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class MyClassLoader extends ClassLoader {


	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		
		try {
			
			String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
			InputStream is = this.getClass().getResourceAsStream(fileName);
			if(is==null){//类不在当前文件目录下
				return super.loadClass(name);
			}
			byte[] bb = new byte[is.available()];
			is.read(bb);
			System.out.println("使用 MyClassLoader 类加载器");
			Class c = this.defineClass(name, bb, 0, bb.length);
			return c;
			
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}
		
	}

	public static void main(String[] args) throws Exception{
		
		test1();
		//testClassLoader();
	}
	
	
	public static void test1()  throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		MyClassLoader myClassLoader = new MyClassLoader();
		
		String name = "javax.lang.MyClassLoader";
		
		Object obj = myClassLoader.loadClass(name).newInstance();
		
		Object obj2 = Class.forName("javax.lang.MyClassLoader").newInstance();
		
		System.out.println(obj.getClass());
		System.out.println(obj2.getClass());
		System.out.println(myClassLoader.getClass());
		
		System.out.println(obj instanceof com.bluemobi.mavenTest.jvm.classloader.MyClassLoader);
		System.out.println(obj2 instanceof com.bluemobi.mavenTest.jvm.classloader.MyClassLoader);
		
	}
	
	public static void testClassLoader(){
		ClassLoader cl = MyClassLoader.class.getClassLoader();
		System.out.println(cl);
		System.out.println(cl.getParent());
		System.out.println(cl.getParent().getParent());
		
		
		cl = HashMap.class.getClassLoader();
		System.out.println("----->"+cl);
		if(cl==null){
			System.out.println("最上层类加载器： Bootstrap ClassLoader");
		}
	}
	
}
