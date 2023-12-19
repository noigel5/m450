package org.jemeter;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.config.Arguments;
import org.example.Main;

public class MainPerformanceTest extends AbstractJavaSamplerClient {


    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = new Arguments();
        // Define any parameters your test needs here
        return defaultParameters;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        // Perform any setup required before the test here
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch

        try {
            // Run your test here
            Main.main(new String[]{});
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful(true);
            result.setResponseMessage("Successfully performed action");
        } catch (Exception e) {
            result.sampleEnd(); // stop stopwatch
            result.setSuccessful(false);
            result.setResponseMessage("Exception: " + e);
        }

        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        // Perform any cleanup after the test here
    }

}