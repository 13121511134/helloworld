package arbitraryTest.proxy;
public class Proxy$1 implements arbitraryTest.proxy.test.Singable{
	private InvocationHandler target;
	public Proxy$1(InvocationHandler target){
		this.target = target;
	}
	@Override
	public java.lang.String sing() {
		try {
			return (java.lang.String)target.invoke(this,arbitraryTest.proxy.test.Singable.class.getMethod("sing",new Class[]{ }));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public java.lang.String sing( java.lang.String arg0) {
		try {
			return (java.lang.String)target.invoke(this,arbitraryTest.proxy.test.Singable.class.getMethod("sing",new Class[]{ java.lang.String.class }),arg0);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void openMouse() {
		try {
			target.invoke(this,arbitraryTest.proxy.test.Singable.class.getMethod("openMouse",new Class[]{ }));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}