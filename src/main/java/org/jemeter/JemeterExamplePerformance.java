import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.example.Main;
import org.jemeter.MainPerformanceTest;

public class JemeterExamplePerformance {
    public static void main(String[] args) throws InterruptedException {
        // Create the JMeter engine
        StandardJMeterEngine jm = new StandardJMeterEngine();

        // Set some configuration
        String jmeterHome = "\\res\\apache-jmeter-5.6.2";
        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.initLocale();

        // Create a new hash tree to hold our test elements
        HashTree testPlanTree = new HashTree();

        // Create a JavaSampler for the console application
        MainPerformanceTest javaSamplerApp = new MainPerformanceTest();
        javaSamplerApp.setClassname("C:\\Users\\laptopAdmin\\IdeaProjects\\m450\\src\\main\\java\\org\\example\\Main.java");
        // Add any additional parameters or configurations as needed
        javaSamplerApp.setName("Java Request to Console App");
        javaSamplerApp.setProperty(TestElement.TEST_CLASS, JavaSampler.class.getName());
        javaSamplerApp.setProperty(TestElement.GUI_CLASS, JavaTestSamplerGui.class.getName());

        // Create a loop controller
        LoopController loopController = new LoopController();
        loopController.setLoops(10);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.initialize();

        // Create a thread group
        org.apache.jmeter.threads.ThreadGroup threadGroup = new org.apache.jmeter.threads.ThreadGroup();
        threadGroup.setName("Example Thread Group");
        threadGroup.setNumThreads(2);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(loopController);
        threadGroup.setProperty(TestElement.TEST_CLASS, org.apache.jmeter.threads.ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());

        // Create a test plan
        TestPlan testPlan = new TestPlan("Create JMeter Script for Console App Performance");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.setUserDefinedVariables((Arguments) new ArgumentsPanel().createTestElement());

        // Add the test plan to our hash tree, this is the top level of our test
        testPlanTree.add(testPlan);

        // Create another hash tree and add the thread group to our test plan
        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);

        // Add the Java sampler to the hash tree that contains the thread group
        threadGroupHashTree.add(javaSamplerApp);

        // Configure
        jm.configure(testPlanTree);

        // Run
        jm.run();
    }
}
