package arbitraryTest.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import util.LoggerUtil;
/**
 * 模拟动态代理
 * @author Administrator
 *
 */
public class Proxy {
	
	private static String proxyName = "Proxy$1";
	public static <T> T newProxyInstance(ClassLoader classLoader,Class<T> clazz,InvocationHandler handler) throws IllegalArgumentException{
		if(!clazz.isInterface()){
			throw new IllegalArgumentException();
		}
		String packageName= "arbitraryTest.proxy";
		String sourceHead = "package "+packageName+";\n"+
				"public class "+proxyName+" implements "+clazz.getName()+"{\n"+
					"\tprivate InvocationHandler target;\n"+
					"\tpublic "+proxyName+"(InvocationHandler target){\n"+
						"\t\tthis.target = target;\n"+
					"\t}\n";
		String sourceFoot = "}";
		StringBuilder sourceBuild = new StringBuilder();
		//代理类字符串生成begin
		sourceBuild.append(sourceHead);
		Method [] methods = clazz.getMethods();
		//每个方法生成代理
		for(Method method:methods){
			Class returnType = method.getReturnType();
			boolean isVoidReturn = returnType.isAssignableFrom(java.lang.Void.TYPE);
			Parameter[] parameters = method.getParameters();
			sourceBuild.append("\t@Override\n");
			sourceBuild.append("\tpublic ");
			sourceBuild.append(returnType.getName());
			sourceBuild.append(" "+method.getName()+"( ");
			for(Parameter param:parameters){
				sourceBuild.append(param.getParameterizedType().getTypeName()+" ").append(param.getName()).append(",");
			}
			sourceBuild.deleteCharAt(sourceBuild.length()-1);
			sourceBuild.append(") {\n").append("\t\ttry {\n\t\t\t");
			if(!isVoidReturn){
				sourceBuild.append("return (").append(returnType.getName())
				.append(")");
			}
			sourceBuild.append("target.invoke(this,").append(clazz.getName()+".class")
						.append(".getMethod(\"").append(method.getName()+"\",");
			sourceBuild.append("new Class[]{ ");
			for(Class clz :method.getParameterTypes()){
				sourceBuild.append(clz.getName()+".class,");
			}
			sourceBuild.deleteCharAt(sourceBuild.length()-1);
			sourceBuild.append(" })");
			for(Parameter param:parameters){
				sourceBuild.append(",").append(param.getName());
			}
			sourceBuild.append(");\n");
			sourceBuild.append("\t\t} catch (Throwable e) {\n")
						.append("\t\t\te.printStackTrace();\n\t\t}\n");
			if(!isVoidReturn){
				sourceBuild.append("\t\treturn null;\n");
			}
			sourceBuild.append("\t}\n\n");
			
		}
		sourceBuild.append(sourceFoot);//代理类字符串生成end
		
		//代理类位置
		StringBuilder fileNameBuild = new StringBuilder();
		fileNameBuild.append(System.getProperty("user.dir")).append("\\src\\").append(packageName.replace(".", "\\"))
			.append("\\").append(proxyName).append(".java");
		
//生成java source
		buildJavaFile(sourceBuild.toString(),fileNameBuild.toString());
		
		//编译
		compile(fileNameBuild.toString());
		
		T proxy = null;
		//加载并newInstance
		proxy = ProxyFactory.getInstance(clazz,handler);
		return proxy;
	}
	
	public static void main(String[] args) {
		Object obj = new Tank();
		InvocationHandler handler = new LogHandler(obj);
		Movable proxy = Proxy.newProxyInstance(null, Movable.class,handler);
		proxy.move(4,6);
	}
	
	
	//生成java文件
	private static void buildJavaFile(String source,String fileName){
		try {
			byte[] bytes= source.getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			File proxyJavaFile =new File(fileName);
			FileOutputStream fis= new FileOutputStream(proxyJavaFile);
			FileChannel channel = fis.getChannel();
			buffer.put(bytes);
			buffer.flip();
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//编译
	private static void compile(String fileName){
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr =  compiler.getStandardFileManager(null, null, null);
		Iterable fileObjects = fileMgr.getJavaFileObjects(fileName);
		CompilationTask task = compiler.getTask(null, fileMgr, null, null, null, fileObjects);
		task.call();
	}
	/**
	 * 工厂获取代理类
	 * @author Administrator
	 *
	 */
	 private static class ProxyFactory{
		public static <T> T getInstance(Class<T> clazz,InvocationHandler h){
			T proxy= null;
			try {
				URL[]urls=new URL[]{new URL("file:/"+System.getProperty("user.dir")+"/src/")};
				URLClassLoader loader = new URLClassLoader(urls);
				Class proxyclazz =loader.loadClass("arbitraryTest.proxy"+"."+proxyName);
				Constructor constructor = proxyclazz.getConstructor(InvocationHandler.class);
				proxy = (T)constructor.newInstance(h);
			} catch (Exception e1) {
				e1.printStackTrace();
				LoggerUtil.info("Class Proxy1 Not Found");
			}
			return proxy;
		}
	}
}
