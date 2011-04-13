package monaaco.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestGlobal extends TestCase {

	public TestGlobal(String nombre) {
		super (nombre);
	}
	
	public static void main(String args[]){
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTestSuite(BasicPlayerTest.class);
		suite.addTestSuite(BotonAvanzadoTest.class);
		suite.addTestSuite(SongInfoInterfazTest.class);
		suite.addTestSuite(SongInterfazTest.class);
		//suite.addTestSuite(TrackTest.class);
		return suite;
	}
}
